package org.cloudburstmc.protocol.bedrock.codec.v503.serializer;

import io.netty.buffer.ByteBuf;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.BedrockPacketSerializer;
import org.cloudburstmc.protocol.bedrock.data.payload.common.DimensionType;
import org.cloudburstmc.protocol.bedrock.packet.AddVolumeEntityPacket;
import org.cloudburstmc.protocol.common.util.VarInts;

public class AddVolumeEntitySerializer_v503 implements BedrockPacketSerializer<AddVolumeEntityPacket> {

    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, AddVolumeEntityPacket packet) {
        VarInts.writeUnsignedInt(buffer, packet.getEntityNetworkId());
        helper.writeTag(buffer, packet.getComponents());
        helper.writeString(buffer, packet.getIdentifier());
        helper.writeString(buffer, packet.getInstanceName());
        helper.writeBlockPosition(buffer, packet.getMinBounds());
        helper.writeBlockPosition(buffer, packet.getMaxBounds());
        VarInts.writeInt(buffer, packet.getDimensionType().getValue());
        helper.writeString(buffer, packet.getEngineVersion());
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, AddVolumeEntityPacket packet) {
        packet.setEntityNetworkId(VarInts.readUnsignedInt(buffer));
        packet.setComponents(helper.readTag(buffer, NbtMap.class));
        packet.setIdentifier(helper.readString(buffer));
        packet.setInstanceName(helper.readString(buffer));
        packet.setMinBounds(helper.readBlockPosition(buffer));
        packet.setMaxBounds(helper.readBlockPosition(buffer));
        packet.setDimensionType(DimensionType.from(VarInts.readInt(buffer)));
        packet.setEngineVersion(helper.readString(buffer));
    }
}
