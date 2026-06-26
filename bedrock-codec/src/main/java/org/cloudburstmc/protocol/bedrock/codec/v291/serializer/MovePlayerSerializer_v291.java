package org.cloudburstmc.protocol.bedrock.codec.v291.serializer;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.BedrockPacketSerializer;
import org.cloudburstmc.protocol.bedrock.data.payload.move.MovePlayerTeleportData;
import org.cloudburstmc.protocol.bedrock.data.payload.move.PositionMode;
import org.cloudburstmc.protocol.bedrock.data.payload.move.TeleportationCause;
import org.cloudburstmc.protocol.bedrock.packet.MovePlayerPacket;
import org.cloudburstmc.protocol.common.util.VarInts;

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
            this.writeMovePlayerTeleportData(buffer, packet.getTeleportData());
        }
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, MovePlayerPacket packet) {
        packet.setPlayerRuntimeID(VarInts.readUnsignedLong(buffer));
        packet.setPosition(helper.readVector3f(buffer));
        packet.setRotation(helper.readVector3f(buffer));
        packet.setPositionMode(PositionMode.from(buffer.readUnsignedByte()));
        packet.setOnGround(buffer.readBoolean());
        packet.setRidingRuntimeID(VarInts.readUnsignedLong(buffer));
        if (packet.getPositionMode() == PositionMode.TELEPORT) {
            packet.setTeleportData(this.readMovePlayerTeleportData(buffer));
        }
    }

    protected void writeMovePlayerTeleportData(ByteBuf buffer, MovePlayerTeleportData data) {
        buffer.writeIntLE(data.getTeleportationCause().ordinal());
        buffer.writeIntLE(data.getSourceActorType());
    }

    protected MovePlayerTeleportData readMovePlayerTeleportData(ByteBuf buffer) {
        final MovePlayerTeleportData data = new MovePlayerTeleportData();
        data.setTeleportationCause(TeleportationCause.from(buffer.readIntLE()));
        data.setSourceActorType(buffer.readIntLE());
        return data;
    }
}