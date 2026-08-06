package org.cloudburstmc.protocol.bedrock.codec.v2181.serializer;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.v2168.serializer.MoveActorDeltaSerializer_v2168;
import org.cloudburstmc.protocol.bedrock.data.payload.move.MoveActorDeltaData;
import org.cloudburstmc.protocol.common.util.VarInts;

/**
 * @author Kaooot
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MoveActorDeltaSerializer_v2181 extends MoveActorDeltaSerializer_v2168 {
    public static final MoveActorDeltaSerializer_v2181 INSTANCE = new MoveActorDeltaSerializer_v2181();

    @Override
    protected void writeMoveActorDeltaData(ByteBuf buffer, BedrockCodecHelper helper, MoveActorDeltaData data) {
        super.writeMoveActorDeltaData(buffer, helper, data);
        VarInts.writeUnsignedLong(buffer, data.getTicks());
    }

    @Override
    protected MoveActorDeltaData readMoveActorDeltaData(ByteBuf buffer, BedrockCodecHelper helper) {
        final MoveActorDeltaData data = super.readMoveActorDeltaData(buffer, helper);
        data.setTicks(VarInts.readUnsignedLong(buffer));
        return data;
    }
}