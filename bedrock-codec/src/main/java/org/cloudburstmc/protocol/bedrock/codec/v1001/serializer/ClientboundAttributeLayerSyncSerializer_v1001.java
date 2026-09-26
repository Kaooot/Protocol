package org.cloudburstmc.protocol.bedrock.codec.v1001.serializer;

import io.netty.buffer.ByteBuf;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.v975.serializer.ClientboundAttributeLayerSyncSerializer_v975;
import org.cloudburstmc.protocol.bedrock.data.attribute.AttributeLayerData;
import org.cloudburstmc.protocol.bedrock.data.attribute.EnvironmentAttributeData;
import org.cloudburstmc.protocol.bedrock.data.world.DimensionType;
import org.cloudburstmc.protocol.bedrock.packet.ClientboundAttributeLayerSyncPacket;
import org.cloudburstmc.protocol.common.util.VarInts;

public class ClientboundAttributeLayerSyncSerializer_v1001 extends ClientboundAttributeLayerSyncSerializer_v975 {

    public static final ClientboundAttributeLayerSyncSerializer_v1001 INSTANCE = new ClientboundAttributeLayerSyncSerializer_v1001();

    @Override
    protected void writeAttributeLayerData(ByteBuf buffer, BedrockCodecHelper helper, ClientboundAttributeLayerSyncPacket packet, AttributeLayerData data) {
        helper.writeString(buffer, data.getName());
        helper.writeOptionalNull(buffer, data.getNoiseName(), helper::writeString);
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
        data.setNoiseName(helper.readOptional(buffer, null, helper::readString));
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
        super.writeEnvironmentAttributeData(buffer, helper, packet, data);
        buffer.writeIntLE(data.getLocalTransitionTicks());
        buffer.writeBoolean(data.isNoiseTransition());
    }

    @Override
    protected EnvironmentAttributeData readEnvironmentAttributeData(ByteBuf buffer, BedrockCodecHelper helper, ClientboundAttributeLayerSyncPacket packet) {
        final EnvironmentAttributeData data = super.readEnvironmentAttributeData(buffer, helper, packet);
        data.setLocalTransitionTicks(buffer.readIntLE());
        data.setNoiseTransition(buffer.readBoolean());
        return data;
    }
}
