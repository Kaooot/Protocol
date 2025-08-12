package org.cloudburstmc.protocol.bedrock.codec.v440.serializer;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.BedrockPacketSerializer;
import org.cloudburstmc.protocol.bedrock.packet.AddVolumeEntityPacket;
import org.cloudburstmc.protocol.common.util.VarInts;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AddVolumeEntitySerializer_v440 implements BedrockPacketSerializer<AddVolumeEntityPacket> {

    public static final AddVolumeEntitySerializer_v440 INSTANCE = new AddVolumeEntitySerializer_v440();

    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, AddVolumeEntityPacket packet) {
        VarInts.writeUnsignedInt(buffer, packet.getEntityNetworkId());
        helper.writeTag(buffer, packet.getComponents());
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, AddVolumeEntityPacket packet) {
        packet.setEntityNetworkId(VarInts.readUnsignedInt(buffer));
        packet.setComponents(helper.readTag(buffer, NbtMap.class));
    }
}
