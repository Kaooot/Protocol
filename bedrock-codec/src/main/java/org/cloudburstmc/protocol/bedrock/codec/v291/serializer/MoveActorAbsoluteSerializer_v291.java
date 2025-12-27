package org.cloudburstmc.protocol.bedrock.codec.v291.serializer;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.math.vector.Vector3f;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.BedrockPacketSerializer;
import org.cloudburstmc.protocol.bedrock.data.actor.MoveActorAbsoluteData;
import org.cloudburstmc.protocol.bedrock.packet.MoveActorAbsolutePacket;
import org.cloudburstmc.protocol.common.util.VarInts;

import static org.cloudburstmc.protocol.common.util.Preconditions.checkNotNull;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MoveActorAbsoluteSerializer_v291 implements BedrockPacketSerializer<MoveActorAbsolutePacket> {
    public static final MoveActorAbsoluteSerializer_v291 INSTANCE = new MoveActorAbsoluteSerializer_v291();

    private static final int FLAG_ON_GROUND = 0x1;
    private static final int FLAG_TELEPORTED = 0x2;
    private static final int FLAG_FORCE_MOVE = 0x4;

    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, MoveActorAbsolutePacket packet) {
        VarInts.writeUnsignedLong(buffer, packet.getMoveData().getActorRuntimeID());
        int flags = 0;
        if (packet.getMoveData().isOnGround()) {
            flags |= FLAG_ON_GROUND;
        }
        if (packet.getMoveData().isTeleported()) {
            flags |= FLAG_TELEPORTED;
        }
        if (packet.getMoveData().isForceMove()) {
            flags |= FLAG_FORCE_MOVE;
        }
        buffer.writeByte(flags);
        helper.writeVector3f(buffer, packet.getMoveData().getPos());
        this.writeByteRotation(buffer, helper, packet.getMoveData().getRotation());
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, MoveActorAbsolutePacket packet) {
        final MoveActorAbsoluteData moveData = new MoveActorAbsoluteData();
        moveData.setActorRuntimeID(VarInts.readUnsignedLong(buffer));
        int flags = buffer.readUnsignedByte();
        moveData.setOnGround((flags & FLAG_ON_GROUND) != 0);
        moveData.setTeleported((flags & FLAG_TELEPORTED) != 0);
        moveData.setForceMove((flags & FLAG_FORCE_MOVE) != 0);
        moveData.setPos(helper.readVector3f(buffer));
        moveData.setRotation(this.readByteRotation(buffer, helper));
        packet.setMoveData(moveData);
    }

    // Helpers

    protected Vector3f readByteRotation(ByteBuf buffer, BedrockCodecHelper helper) {
        float pitch = helper.readByteAngle(buffer);
        float yaw = helper.readByteAngle(buffer);
        float roll = helper.readByteAngle(buffer);
        return Vector3f.from(pitch, yaw, roll);
    }

    protected void writeByteRotation(ByteBuf buffer, BedrockCodecHelper helper, Vector3f rotation) {
        checkNotNull(rotation, "rotation");
        helper.writeByteAngle(buffer, rotation.getX());
        helper.writeByteAngle(buffer, rotation.getY());
        helper.writeByteAngle(buffer, rotation.getZ());
    }
}
