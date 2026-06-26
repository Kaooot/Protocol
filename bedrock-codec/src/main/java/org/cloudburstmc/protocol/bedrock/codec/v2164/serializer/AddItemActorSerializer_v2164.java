package org.cloudburstmc.protocol.bedrock.codec.v2164.serializer;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.v291.serializer.AddItemActorSerializer_v291;
import org.cloudburstmc.protocol.bedrock.packet.AddItemActorPacket;
import org.cloudburstmc.protocol.common.util.VarInts;

/**
 * @author Kaooot
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AddItemActorSerializer_v2164 extends AddItemActorSerializer_v291 {
    public static final AddItemActorSerializer_v2164 INSTANCE = new AddItemActorSerializer_v2164();

    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, AddItemActorPacket packet) {
        VarInts.writeLong(buffer, packet.getTargetActorID());
        VarInts.writeUnsignedLong(buffer, packet.getTargetRuntimeID());
        helper.writeNetworkItemStackDescriptor(buffer, packet.getItem());
        helper.writeVector3f(buffer, packet.getPosition());
        helper.writeVector3f(buffer, packet.getVelocity());
        helper.writeActorData(buffer, packet.getEntityData());
        buffer.writeBoolean(packet.isFromFishing());
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, AddItemActorPacket packet) {
        packet.setTargetActorID(VarInts.readLong(buffer));
        packet.setTargetRuntimeID(VarInts.readUnsignedLong(buffer));
        packet.setItem(helper.readNetworkItemStackDescriptor(buffer));
        packet.setPosition(helper.readVector3f(buffer));
        packet.setVelocity(helper.readVector3f(buffer));
        helper.readActorData(buffer, packet.getEntityData());
        packet.setFromFishing(buffer.readBoolean());
    }
}