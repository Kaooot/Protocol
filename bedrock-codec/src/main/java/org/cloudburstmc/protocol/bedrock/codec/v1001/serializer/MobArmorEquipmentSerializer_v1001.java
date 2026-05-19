package org.cloudburstmc.protocol.bedrock.codec.v1001.serializer;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.v712.serializer.MobArmorEquipmentSerializer_v712;
import org.cloudburstmc.protocol.bedrock.packet.MobArmorEquipmentPacket;
import org.cloudburstmc.protocol.common.util.VarInts;

/**
 * @author Kaooot
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MobArmorEquipmentSerializer_v1001 extends MobArmorEquipmentSerializer_v712 {
    public static final MobArmorEquipmentSerializer_v1001 INSTANCE = new MobArmorEquipmentSerializer_v1001();

    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, MobArmorEquipmentPacket packet) {
        VarInts.writeUnsignedLong(buffer, packet.getTargetRuntimeID());
        helper.writeNetworkItemStackDescriptor(buffer, packet.getHead());
        helper.writeNetworkItemStackDescriptor(buffer, packet.getTorso());
        helper.writeNetworkItemStackDescriptor(buffer, packet.getLegs());
        helper.writeNetworkItemStackDescriptor(buffer, packet.getFeet());
        helper.writeNetworkItemStackDescriptor(buffer, packet.getBody());
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, MobArmorEquipmentPacket packet) {
        packet.setTargetRuntimeID(VarInts.readUnsignedLong(buffer));
        packet.setHead(helper.readNetworkItemStackDescriptor(buffer));
        packet.setTorso(helper.readNetworkItemStackDescriptor(buffer));
        packet.setLegs(helper.readNetworkItemStackDescriptor(buffer));
        packet.setFeet(helper.readNetworkItemStackDescriptor(buffer));
        packet.setBody(helper.readNetworkItemStackDescriptor(buffer));
    }
}