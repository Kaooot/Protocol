package org.cloudburstmc.protocol.bedrock.codec.v975.serializer;

import io.netty.buffer.ByteBuf;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.v291.serializer.MoveEntityAbsoluteSerializer_v291;
import org.cloudburstmc.protocol.bedrock.data.actor.MoveActorAbsoluteData;
import org.cloudburstmc.protocol.bedrock.packet.MoveActorAbsolutePacket;
import org.cloudburstmc.protocol.common.util.VarInts;

public class MoveEntityAbsoluteSerializer_v975 extends MoveEntityAbsoluteSerializer_v291 {

    public static final MoveEntityAbsoluteSerializer_v975 INSTANCE = new MoveEntityAbsoluteSerializer_v975();

    private static final int FLAG_ON_GROUND = 0x1;
    private static final int FLAG_TELEPORTED = 0x2;
    private static final int FLAG_FORCE_MOVE = 0x4;
    /**
     * @since v975
     */
    private static final int FLAG_FORCE_COMPLETION = 0x8;

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
        if (packet.getMoveData().isForceCompletion()) {
            flags |= FLAG_FORCE_COMPLETION;
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
        moveData.setForceCompletion((flags & FLAG_FORCE_COMPLETION) != 0);
        moveData.setPos(helper.readVector3f(buffer));
        moveData.setRotation(this.readByteRotation(buffer, helper));
        packet.setMoveData(moveData);
    }
}
