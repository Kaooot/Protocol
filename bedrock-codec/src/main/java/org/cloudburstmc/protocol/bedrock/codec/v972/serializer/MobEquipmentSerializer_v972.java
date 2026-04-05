package org.cloudburstmc.protocol.bedrock.codec.v972.serializer;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.v291.serializer.MobEquipmentSerializer_v291;
import org.cloudburstmc.protocol.bedrock.packet.MobEquipmentPacket;
import org.cloudburstmc.protocol.common.util.VarInts;

/**
 * @author Kaooot
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MobEquipmentSerializer_v972 extends MobEquipmentSerializer_v291 {
    public static final MobEquipmentSerializer_v972 INSTANCE = new MobEquipmentSerializer_v972();

    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, MobEquipmentPacket packet) {
        VarInts.writeUnsignedLong(buffer, packet.getTargetRuntimeID());
        helper.writeNetworkItemStackDescriptor(buffer, packet.getItem());
        buffer.writeByte(packet.getSlot());
        buffer.writeByte(packet.getSelectedSlot());
        buffer.writeByte(packet.getContainerId());
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, MobEquipmentPacket packet) {
        packet.setTargetRuntimeID(VarInts.readUnsignedLong(buffer));
        packet.setItem(helper.readNetworkItemStackDescriptor(buffer));
        packet.setSlot(buffer.readUnsignedByte());
        packet.setSelectedSlot(buffer.readUnsignedByte());
        packet.setContainerId(buffer.readByte());
    }
}