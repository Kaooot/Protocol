package org.cloudburstmc.protocol.bedrock.codec.v291.serializer;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.BedrockPacketSerializer;
import org.cloudburstmc.protocol.bedrock.packet.UpdateEquipPacket;
import org.cloudburstmc.protocol.common.util.VarInts;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UpdateEquipSerializer_v291 implements BedrockPacketSerializer<UpdateEquipPacket> {
    public static final UpdateEquipSerializer_v291 INSTANCE = new UpdateEquipSerializer_v291();


    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, UpdateEquipPacket packet) {
        buffer.writeByte(packet.getContainerId());
        buffer.writeByte(packet.getType());
        VarInts.writeInt(buffer, packet.getSize());
        VarInts.writeLong(buffer, packet.getEntityUniqueId());
        helper.writeTag(buffer, packet.getTag());
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, UpdateEquipPacket packet) {
        packet.setContainerId(buffer.readUnsignedByte());
        packet.setType(buffer.readUnsignedByte());
        packet.setSize(VarInts.readInt(buffer));
        packet.setEntityUniqueId(VarInts.readLong(buffer));
        packet.setTag(helper.readTag(buffer, NbtMap.class));
    }
}
