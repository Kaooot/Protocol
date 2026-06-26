package org.cloudburstmc.protocol.bedrock.codec.v2164.serializer;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.v419.serializer.MoveActorDeltaSerializer_v419;
import org.cloudburstmc.protocol.bedrock.data.payload.move.MoveActorDeltaData;
import org.cloudburstmc.protocol.bedrock.packet.MoveActorDeltaPacket;
import org.cloudburstmc.protocol.common.util.VarInts;

/**
 * @author Kaooot
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MoveActorDeltaSerializer_v2164 extends MoveActorDeltaSerializer_v419 {
    public static final MoveActorDeltaSerializer_v2164 INSTANCE = new MoveActorDeltaSerializer_v2164();

    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, MoveActorDeltaPacket packet) {
        this.writeMoveActorDeltaData(buffer, helper, packet.getMoveData());
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, MoveActorDeltaPacket packet) {
        packet.setMoveData(this.readMoveActorDeltaData(buffer, helper));
    }

    protected void writeMoveActorDeltaData(ByteBuf buffer, BedrockCodecHelper helper, MoveActorDeltaData data) {
        VarInts.writeUnsignedLong(buffer, data.getActorRuntimeID());
        helper.writeOptionalNull(buffer, data.getNewPositionX(), ByteBuf::writeFloatLE);
        helper.writeOptionalNull(buffer, data.getNewPositionY(), ByteBuf::writeFloatLE);
        helper.writeOptionalNull(buffer, data.getNewPositionZ(), ByteBuf::writeFloatLE);
        helper.writeOptionalNull(buffer, data.getRotationX(), helper::writeByteAngle);
        helper.writeOptionalNull(buffer, data.getRotationY(), helper::writeByteAngle);
        helper.writeOptionalNull(buffer, data.getRotationYHead(), helper::writeByteAngle);
        buffer.writeBoolean(data.isOnGround());
        buffer.writeBoolean(data.isForceMove());
        buffer.writeBoolean(data.isForceMoveLocalEntity());
        buffer.writeBoolean(data.isForceCompletion());
    }

    protected MoveActorDeltaData readMoveActorDeltaData(ByteBuf buffer, BedrockCodecHelper helper) {
        final MoveActorDeltaData data = new MoveActorDeltaData();
        data.setActorRuntimeID(VarInts.readUnsignedLong(buffer));
        data.setNewPositionX(helper.readOptional(buffer, null, ByteBuf::readFloatLE));
        data.setNewPositionY(helper.readOptional(buffer, null, ByteBuf::readFloatLE));
        data.setNewPositionZ(helper.readOptional(buffer, null, ByteBuf::readFloatLE));
        data.setRotationX(helper.readOptional(buffer, null, helper::readByteAngle));
        data.setRotationY(helper.readOptional(buffer, null, helper::readByteAngle));
        data.setRotationYHead(helper.readOptional(buffer, null, helper::readByteAngle));
        data.setOnGround(buffer.readBoolean());
        data.setForceMove(buffer.readBoolean());
        data.setForceMoveLocalEntity(buffer.readBoolean());
        data.setForceCompletion(buffer.readBoolean());
        return data;
    }
}