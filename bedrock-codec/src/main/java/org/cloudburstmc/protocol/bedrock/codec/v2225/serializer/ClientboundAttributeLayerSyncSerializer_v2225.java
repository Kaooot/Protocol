package org.cloudburstmc.protocol.bedrock.codec.v2225.serializer;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.v2193.serializer.ClientboundAttributeLayerSyncSerializer_v2193;
import org.cloudburstmc.protocol.bedrock.data.attribute.*;
import org.cloudburstmc.protocol.bedrock.data.camera.EasingFunction;
import org.cloudburstmc.protocol.bedrock.data.world.DimensionType;
import org.cloudburstmc.protocol.bedrock.packet.ClientboundAttributeLayerSyncPacket;
import org.cloudburstmc.protocol.common.util.VarInts;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ClientboundAttributeLayerSyncSerializer_v2225 extends ClientboundAttributeLayerSyncSerializer_v2193 {
    public static final ClientboundAttributeLayerSyncSerializer_v2225 INSTANCE = new ClientboundAttributeLayerSyncSerializer_v2225();

    protected void writeEnvironmentAttributePayload(ByteBuf buffer, BedrockCodecHelper helper, ClientboundAttributeLayerSyncPacket packet, Object payload) {
        final EnvironmentAttributePayloadType type = ((EnvironmentAttributePayload) payload).getType();
        VarInts.writeUnsignedInt(buffer, type.ordinal());
        switch (type) {
            case CONSTANT:
                this.writeConstantAttributeData(buffer, helper, packet, (ConstantAttributeData) payload);
                break;
            case TRANSITION:
                this.writeTransitionAttributeData(buffer, helper, packet, (TransitionAttributeData) payload);
                break;
            case NOISE_TRANSITION:
                this.writeNoiseTransitionAttributeData(buffer, helper, packet, (NoiseTransitionAttributeData) payload);
                break;
        }
    }

    protected Object readEnvironmentAttributePayload(ByteBuf buffer, BedrockCodecHelper helper, ClientboundAttributeLayerSyncPacket packet) {
        switch (EnvironmentAttributePayloadType.values()[VarInts.readUnsignedInt(buffer)]) {
            case CONSTANT:
                return this.readConstantAttributeData(buffer, helper, packet);
            case TRANSITION:
                return this.readTransitionAttributeData(buffer, helper, packet);
            case NOISE_TRANSITION:
                return this.readNoiseTransitionAttributeData(buffer, helper, packet);
        }
        return null;
    }

    @Override
    protected void writeAttributeLayerData(ByteBuf buffer, BedrockCodecHelper helper, ClientboundAttributeLayerSyncPacket packet, AttributeLayerData data) {
        helper.writeString(buffer, data.getName());
        VarInts.writeInt(buffer, data.getDimension().getValue());
        this.writeAttributeLayerSettings(buffer, helper, data.getSettings());
        helper.writeArray(
                buffer,
                data.getAttributes(),
                (buf, codecHelper, value) -> this.writeEnvironmentAttributeData(buf, codecHelper, packet, value)
        );
    }

    @Override
    protected AttributeLayerData readAttributeLayerData(ByteBuf buffer, BedrockCodecHelper helper, ClientboundAttributeLayerSyncPacket packet) {
        final AttributeLayerData data = new AttributeLayerData();
        data.setName(helper.readStringMaxLen(buffer, NAME_LENGTH));
        data.setDimension(DimensionType.from(VarInts.readInt(buffer)));
        data.setSettings(this.readAttributeLayerSettings(buffer, helper));
        helper.readArray(
                buffer,
                data.getAttributes(),
                (buf, codecHelper) -> this.readEnvironmentAttributeData(buf, codecHelper, packet),
                1024
        );
        return data;
    }

    @Override
    protected void writeEnvironmentAttributeData(ByteBuf buffer, BedrockCodecHelper helper, ClientboundAttributeLayerSyncPacket packet, EnvironmentAttributeData data) {
        helper.writeString(buffer, data.getAttributeName());
        this.writeEnvironmentAttributePayload(buffer, helper, packet, data.getPayload());
    }

    @Override
    protected EnvironmentAttributeData readEnvironmentAttributeData(ByteBuf buffer, BedrockCodecHelper helper, ClientboundAttributeLayerSyncPacket packet) {
        final EnvironmentAttributeData data = new EnvironmentAttributeData();
        data.setAttributeName(helper.readStringMaxLen(buffer, NAME_LENGTH));
        data.setPayload(this.readEnvironmentAttributePayload(buffer, helper, packet));
        return data;
    }

    protected void writeConstantAttributeData(ByteBuf buffer, BedrockCodecHelper helper, ClientboundAttributeLayerSyncPacket packet, ConstantAttributeData data) {
        this.writeAttributeData(buffer, helper, packet,data.getAttribute());
    }

    protected ConstantAttributeData readConstantAttributeData(ByteBuf buffer, BedrockCodecHelper helper, ClientboundAttributeLayerSyncPacket packet) {
        final ConstantAttributeData data = new ConstantAttributeData();
        data.setAttribute(this.readAttributeData(buffer, helper, packet));
        return data;
    }

    protected void writeTransitionAttributeData(ByteBuf buffer, BedrockCodecHelper helper, ClientboundAttributeLayerSyncPacket packet, TransitionAttributeData data) {
        this.writeAttributeData(buffer, helper, packet,data.getFromAttribute());
        this.writeAttributeData(buffer, helper, packet,data.getToAttribute());
        this.writeTransitionSettingsData(buffer, helper, packet, data.getSettings());
    }

    protected TransitionAttributeData readTransitionAttributeData(ByteBuf buffer, BedrockCodecHelper helper, ClientboundAttributeLayerSyncPacket packet) {
        final TransitionAttributeData data = new TransitionAttributeData();
        data.setFromAttribute(this.readAttributeData(buffer, helper, packet));
        data.setToAttribute(this.readAttributeData(buffer, helper, packet));
        data.setSettings(this.readTransitionSettingsData(buffer, helper, packet));
        return data;
    }

    protected void writeTransitionSettingsData(ByteBuf buffer, BedrockCodecHelper helper, ClientboundAttributeLayerSyncPacket packet, TransitionSettingsData data) {
        VarInts.writeUnsignedInt(buffer, data.getTotalTransitionTicks());
        VarInts.writeUnsignedInt(buffer, data.getCurrentTransitionTicks());
        VarInts.writeInt(buffer, data.getEasing().ordinal());
        helper.writeString(buffer, data.getClockName());
    }

    protected TransitionSettingsData readTransitionSettingsData(ByteBuf buffer, BedrockCodecHelper helper, ClientboundAttributeLayerSyncPacket packet) {
        final TransitionSettingsData data = new TransitionSettingsData();
        data.setTotalTransitionTicks(VarInts.readUnsignedInt(buffer));
        data.setCurrentTransitionTicks(VarInts.readUnsignedInt(buffer));
        data.setEasing(EasingFunction.from(VarInts.readInt(buffer)));
        data.setClockName(helper.readStringMaxLen(buffer, NAME_LENGTH));
        return data;
    }

    protected void writeNoiseTransitionAttributeData(ByteBuf buffer, BedrockCodecHelper helper, ClientboundAttributeLayerSyncPacket packet, NoiseTransitionAttributeData data) {
        this.writeAttributeData(buffer, helper, packet,data.getFromAttribute());
        this.writeAttributeData(buffer, helper, packet,data.getToAttribute());
        this.writeNoiseTransitionSettingsData(buffer, helper, packet, data.getSettings());
    }

    protected NoiseTransitionAttributeData readNoiseTransitionAttributeData(ByteBuf buffer, BedrockCodecHelper helper, ClientboundAttributeLayerSyncPacket packet) {
        final NoiseTransitionAttributeData data = new NoiseTransitionAttributeData();
        data.setFromAttribute(this.readAttributeData(buffer, helper, packet));
        data.setToAttribute(this.readAttributeData(buffer, helper, packet));
        data.setSettings(this.readNoiseTransitionSettingsData(buffer, helper, packet));
        return data;
    }

    protected void writeNoiseTransitionSettingsData(ByteBuf buffer, BedrockCodecHelper helper, ClientboundAttributeLayerSyncPacket packet, NoiseTransitionSettingsData data) {
        VarInts.writeUnsignedInt(buffer, data.getTotalTransitionTicks());
        VarInts.writeUnsignedInt(buffer, data.getCurrentTransitionTicks());
        VarInts.writeInt(buffer, data.getEasing().ordinal());
        helper.writeString(buffer, data.getClockName());
        VarInts.writeUnsignedInt(buffer, data.getLocalTransitionTicks());
        helper.writeString(buffer, data.getNoiseName());
        this.writeNoiseAlignment(buffer, data.getNoiseAlignment());
    }

    protected NoiseTransitionSettingsData readNoiseTransitionSettingsData(ByteBuf buffer, BedrockCodecHelper helper, ClientboundAttributeLayerSyncPacket packet) {
        final NoiseTransitionSettingsData data = new NoiseTransitionSettingsData();
        data.setTotalTransitionTicks(VarInts.readUnsignedInt(buffer));
        data.setCurrentTransitionTicks(VarInts.readUnsignedInt(buffer));
        data.setEasing(EasingFunction.from(VarInts.readInt(buffer)));
        data.setClockName(helper.readStringMaxLen(buffer, NAME_LENGTH));
        data.setLocalTransitionTicks(VarInts.readUnsignedInt(buffer));
        data.setNoiseName(helper.readStringMaxLen(buffer, NAME_LENGTH));
        data.setNoiseAlignment(this.readNoiseAlignment(buffer));
        return data;
    }
}