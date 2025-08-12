package org.cloudburstmc.protocol.bedrock.codec.v291.serializer;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.BedrockPacketSerializer;
import org.cloudburstmc.protocol.bedrock.packet.MovePlayerPacket;
import org.cloudburstmc.protocol.common.util.VarInts;

import static org.cloudburstmc.protocol.bedrock.packet.MovePlayerPacket.PositionMode;
import static org.cloudburstmc.protocol.bedrock.packet.MovePlayerPacket.TeleportationCause;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MovePlayerSerializer_v291 implements BedrockPacketSerializer<MovePlayerPacket> {
    public static final MovePlayerSerializer_v291 INSTANCE = new MovePlayerSerializer_v291();


    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, MovePlayerPacket packet) {
        VarInts.writeUnsignedLong(buffer, packet.getPlayerRuntimeID());
        helper.writeVector3f(buffer, packet.getPosition());
        helper.writeVector3f(buffer, packet.getRotation());
        buffer.writeByte(packet.getPositionMode().ordinal());
        buffer.writeBoolean(packet.isOnGround());
        VarInts.writeUnsignedLong(buffer, packet.getRidingRuntimeID());
        if (packet.getPositionMode() == PositionMode.TELEPORT) {
            buffer.writeIntLE(packet.getTeleportationCause().ordinal());
            buffer.writeIntLE(packet.getSourceActorType());
        }
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, MovePlayerPacket packet) {
        packet.setPlayerRuntimeID(VarInts.readUnsignedLong(buffer));
        packet.setPosition(helper.readVector3f(buffer));
        packet.setRotation(helper.readVector3f(buffer));
        packet.setPositionMode(PositionMode.values()[buffer.readUnsignedByte()]);
        packet.setOnGround(buffer.readBoolean());
        packet.setRidingRuntimeID(VarInts.readUnsignedLong(buffer));
        if (packet.getPositionMode() == PositionMode.TELEPORT) {
            packet.setTeleportationCause(TeleportationCause.from(buffer.readIntLE()));
            packet.setSourceActorType(buffer.readIntLE());
        }
    }
}
