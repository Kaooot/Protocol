package org.cloudburstmc.protocol.bedrock.codec.v2207.serializer;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.v2192.serializer.ClientboundAttributeLayerSyncSerializer_v2192;
import org.cloudburstmc.protocol.bedrock.data.camera.EasingType;
import org.cloudburstmc.protocol.bedrock.data.payload.attribute.eas.AttributeLayerData;
import org.cloudburstmc.protocol.bedrock.data.payload.attribute.eas.ConstantAttributeData;
import org.cloudburstmc.protocol.bedrock.data.payload.attribute.eas.EnvironmentAttributeData;
import org.cloudburstmc.protocol.bedrock.data.payload.attribute.eas.EnvironmentAttributePayload;
import org.cloudburstmc.protocol.bedrock.data.payload.attribute.eas.EnvironmentAttributePayloadType;
import org.cloudburstmc.protocol.bedrock.data.payload.attribute.eas.NoiseTransitionAttributeData;
import org.cloudburstmc.protocol.bedrock.data.payload.attribute.eas.NoiseTransitionSettingsData;
import org.cloudburstmc.protocol.bedrock.data.payload.attribute.eas.TransitionAttributeData;
import org.cloudburstmc.protocol.bedrock.data.payload.attribute.eas.TransitionSettingsData;
import org.cloudburstmc.protocol.bedrock.data.payload.common.DimensionType;
import org.cloudburstmc.protocol.common.util.TypeMap;
import org.cloudburstmc.protocol.common.util.VarInts;

/**
 * v2207 restructured {@code EnvironmentAttributeData} into a discriminated {@code Payload} union
 * ({@link ConstantAttributeData} / {@link TransitionAttributeData} / {@link NoiseTransitionAttributeData}).
 * Transition settings gained a {@code ClockName} string and {@code NoiseName} moved out of
 * {@link AttributeLayerData} into the per-attribute noise transition settings.
 *
 * @author Kaooot
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ClientboundAttributeLayerSyncSerializer_v2207 extends ClientboundAttributeLayerSyncSerializer_v2192 {
    public static final ClientboundAttributeLayerSyncSerializer_v2207 INSTANCE = new ClientboundAttributeLayerSyncSerializer_v2207();

    protected static final int SETTINGS_NAME_LENGTH = 128;

    /**
     * {@code easing_function} enum-as-value mapping. The binary values do not follow the {@link EasingType}
     * declaration order, so they are mapped explicitly.
     */
    protected static final TypeMap<EasingType> EASING_FUNCTIONS = TypeMap.builder(EasingType.class)
            .insert(0, EasingType.LINEAR)
            .insert(1, EasingType.SPRING)
            .insert(2, EasingType.EASE_IN_QUAD)
            .insert(3, EasingType.EASE_OUT_QUAD)
            .insert(4, EasingType.EASE_IN_OUT_QUAD)
            .insert(5, EasingType.EASE_IN_CUBIC)
            .insert(6, EasingType.EASE_OUT_CUBIC)
            .insert(7, EasingType.EASE_IN_OUT_CUBIC)
            .insert(8, EasingType.EASE_IN_QUART)
            .insert(9, EasingType.EASE_OUT_QUART)
            .insert(10, EasingType.EASE_IN_OUT_QUART)
            .insert(11, EasingType.EASE_IN_QUINT)
            .insert(12, EasingType.EASE_OUT_QUINT)
            .insert(13, EasingType.EASE_IN_OUT_QUINT)
            .insert(14, EasingType.EASE_IN_SINE)
            .insert(15, EasingType.EASE_OUT_SINE)
            .insert(16, EasingType.EASE_IN_OUT_SINE)
            .insert(17, EasingType.EASE_IN_EXPO)
            .insert(18, EasingType.EASE_OUT_EXPO)
            .insert(19, EasingType.EASE_IN_OUT_EXPO)
            .insert(20, EasingType.EASE_IN_CIRC)
            .insert(21, EasingType.EASE_OUT_CIRC)
            .insert(22, EasingType.EASE_IN_OUT_CIRC)
            .insert(23, EasingType.EASE_IN_BOUNCE)
            .insert(24, EasingType.EASE_OUT_BOUNCE)
            .insert(25, EasingType.EASE_IN_OUT_BOUNCE)
            .insert(26, EasingType.EASE_IN_BACK)
            .insert(27, EasingType.EASE_OUT_BACK)
            .insert(28, EasingType.EASE_IN_OUT_BACK)
            .insert(29, EasingType.EASE_IN_ELASTIC)
            .insert(30, EasingType.EASE_OUT_ELASTIC)
            .insert(31, EasingType.EASE_IN_OUT_ELASTIC)
            .build();

    @Override
    protected void writeAttributeLayerData(ByteBuf buffer, BedrockCodecHelper helper, AttributeLayerData data) {
        helper.writeString(buffer, data.getName());
        VarInts.writeInt(buffer, data.getDimension().getValue());
        this.writeAttributeLayerSettings(buffer, helper, data.getSettings());
        helper.writeArray(buffer, data.getAttributes(), this::writeEnvironmentAttributeData);
    }

    @Override
    protected AttributeLayerData readAttributeLayerData(ByteBuf buffer, BedrockCodecHelper helper) {
        final AttributeLayerData data = new AttributeLayerData();
        data.setName(helper.readStringMaxLen(buffer, NAME_LENGTH));
        data.setDimension(DimensionType.from(VarInts.readInt(buffer)));
        data.setSettings(this.readAttributeLayerSettings(buffer, helper));
        helper.readArray(buffer, data.getAttributes(), this::readEnvironmentAttributeData, 1024);
        return data;
    }

    @Override
    protected void writeEnvironmentAttributeData(ByteBuf buffer, BedrockCodecHelper helper, EnvironmentAttributeData data) {
        helper.writeString(buffer, data.getAttributeName());
        final EnvironmentAttributePayload payload = data.getPayload();
        VarInts.writeUnsignedInt(buffer, payload.getType().ordinal());
        switch (payload.getType()) {
            case CONSTANT:
                this.writeConstantAttributeData(buffer, helper, (ConstantAttributeData) payload);
                break;
            case TRANSITION:
                this.writeTransitionAttributeData(buffer, helper, (TransitionAttributeData) payload);
                break;
            case NOISE_TRANSITION:
                this.writeNoiseTransitionAttributeData(buffer, helper, (NoiseTransitionAttributeData) payload);
                break;
        }
    }

    @Override
    protected EnvironmentAttributeData readEnvironmentAttributeData(ByteBuf buffer, BedrockCodecHelper helper) {
        final EnvironmentAttributeData data = new EnvironmentAttributeData();
        data.setAttributeName(helper.readStringMaxLen(buffer, 128));
        final EnvironmentAttributePayloadType type = EnvironmentAttributePayloadType.from(VarInts.readUnsignedInt(buffer));
        switch (type) {
            case CONSTANT:
                data.setPayload(this.readConstantAttributeData(buffer, helper));
                break;
            case TRANSITION:
                data.setPayload(this.readTransitionAttributeData(buffer, helper));
                break;
            case NOISE_TRANSITION:
                data.setPayload(this.readNoiseTransitionAttributeData(buffer, helper));
                break;
        }
        return data;
    }

    protected void writeConstantAttributeData(ByteBuf buffer, BedrockCodecHelper helper, ConstantAttributeData payload) {
        this.writeAttributeData(buffer, helper, payload.getAttribute());
    }

    protected ConstantAttributeData readConstantAttributeData(ByteBuf buffer, BedrockCodecHelper helper) {
        final ConstantAttributeData payload = new ConstantAttributeData();
        payload.setAttribute(this.readAttributeData(buffer, helper));
        return payload;
    }

    protected void writeTransitionAttributeData(ByteBuf buffer, BedrockCodecHelper helper, TransitionAttributeData payload) {
        this.writeAttributeData(buffer, helper, payload.getFromAttribute());
        this.writeAttributeData(buffer, helper, payload.getToAttribute());
        this.writeTransitionSettings(buffer, helper, payload.getSettings());
    }

    protected TransitionAttributeData readTransitionAttributeData(ByteBuf buffer, BedrockCodecHelper helper) {
        final TransitionAttributeData payload = new TransitionAttributeData();
        payload.setFromAttribute(this.readAttributeData(buffer, helper));
        payload.setToAttribute(this.readAttributeData(buffer, helper));
        payload.setSettings(this.readTransitionSettings(buffer, helper));
        return payload;
    }

    protected void writeNoiseTransitionAttributeData(ByteBuf buffer, BedrockCodecHelper helper, NoiseTransitionAttributeData payload) {
        this.writeAttributeData(buffer, helper, payload.getFromAttribute());
        this.writeAttributeData(buffer, helper, payload.getToAttribute());
        this.writeNoiseTransitionSettings(buffer, helper, payload.getSettings());
    }

    protected NoiseTransitionAttributeData readNoiseTransitionAttributeData(ByteBuf buffer, BedrockCodecHelper helper) {
        final NoiseTransitionAttributeData payload = new NoiseTransitionAttributeData();
        payload.setFromAttribute(this.readAttributeData(buffer, helper));
        payload.setToAttribute(this.readAttributeData(buffer, helper));
        payload.setSettings(this.readNoiseTransitionSettings(buffer, helper));
        return payload;
    }

    protected void writeTransitionSettings(ByteBuf buffer, BedrockCodecHelper helper, TransitionSettingsData settings) {
        VarInts.writeUnsignedInt(buffer, settings.getTotalTransitionTicks());
        VarInts.writeUnsignedInt(buffer, settings.getCurrentTransitionTicks());
        VarInts.writeInt(buffer, EASING_FUNCTIONS.getId(settings.getEasing()));
        helper.writeString(buffer, settings.getClockName());
    }

    protected TransitionSettingsData readTransitionSettings(ByteBuf buffer, BedrockCodecHelper helper) {
        final TransitionSettingsData settings = new TransitionSettingsData();
        this.readTransitionSettings(buffer, helper, settings);
        return settings;
    }

    protected void writeNoiseTransitionSettings(ByteBuf buffer, BedrockCodecHelper helper, NoiseTransitionSettingsData settings) {
        this.writeTransitionSettings(buffer, helper, settings);
        VarInts.writeUnsignedInt(buffer, settings.getLocalTransitionTicks());
        helper.writeString(buffer, settings.getNoiseName());
        this.writeNoiseAlignment(buffer, helper, settings.getNoiseAlignment());
    }

    protected NoiseTransitionSettingsData readNoiseTransitionSettings(ByteBuf buffer, BedrockCodecHelper helper) {
        final NoiseTransitionSettingsData settings = new NoiseTransitionSettingsData();
        this.readTransitionSettings(buffer, helper, settings);
        settings.setLocalTransitionTicks(VarInts.readUnsignedInt(buffer));
        settings.setNoiseName(helper.readStringMaxLen(buffer, SETTINGS_NAME_LENGTH));
        settings.setNoiseAlignment(this.readNoiseAlignment(buffer, helper));
        return settings;
    }

    private void readTransitionSettings(ByteBuf buffer, BedrockCodecHelper helper, TransitionSettingsData settings) {
        settings.setTotalTransitionTicks(VarInts.readUnsignedInt(buffer));
        settings.setCurrentTransitionTicks(VarInts.readUnsignedInt(buffer));
        settings.setEasing(EASING_FUNCTIONS.getType(VarInts.readInt(buffer)));
        settings.setClockName(helper.readStringMaxLen(buffer, SETTINGS_NAME_LENGTH));
    }
}
