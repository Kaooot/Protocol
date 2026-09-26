package org.cloudburstmc.protocol.bedrock.codec.v2192.serializer;

import io.netty.buffer.ByteBuf;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.v1001.serializer.ClientboundAttributeLayerSyncSerializer_v1001;
import org.cloudburstmc.protocol.bedrock.data.attribute.EnvironmentAttributeData;
import org.cloudburstmc.protocol.bedrock.data.structure.NoiseAlignment;
import org.cloudburstmc.protocol.bedrock.data.structure.NoiseAlignmentType;
import org.cloudburstmc.protocol.bedrock.packet.ClientboundAttributeLayerSyncPacket;
import org.cloudburstmc.protocol.common.util.VarInts;

public class ClientboundAttributeLayerSyncSerializer_v2192 extends ClientboundAttributeLayerSyncSerializer_v1001 {

    public static final ClientboundAttributeLayerSyncSerializer_v2192 INSTANCE = new ClientboundAttributeLayerSyncSerializer_v2192();

    @Override
    protected void writeEnvironmentAttributeData(ByteBuf buffer, BedrockCodecHelper helper, ClientboundAttributeLayerSyncPacket packet, EnvironmentAttributeData data) {
        super.writeEnvironmentAttributeData(buffer, helper, packet, data);
        this.writeNoiseAlignment(buffer, data.getNoiseAlignment());
    }

    @Override
    protected EnvironmentAttributeData readEnvironmentAttributeData(ByteBuf buffer, BedrockCodecHelper helper, ClientboundAttributeLayerSyncPacket packet) {
        final EnvironmentAttributeData data = super.readEnvironmentAttributeData(buffer, helper, packet);
        data.setNoiseAlignment(this.readNoiseAlignment(buffer));
        return data;
    }

    protected void writeNoiseAlignment(ByteBuf buffer, NoiseAlignment noiseAlignment) {
        buffer.writeByte(noiseAlignment.getType().ordinal());
        VarInts.writeUnsignedInt(buffer, noiseAlignment.getValue());
    }

    protected NoiseAlignment readNoiseAlignment(ByteBuf buffer) {
        return new NoiseAlignment(NoiseAlignmentType.from(buffer.readUnsignedByte()), VarInts.readUnsignedInt(buffer));
    }
}
