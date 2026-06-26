package org.cloudburstmc.protocol.bedrock.codec.v419.serializer;

import io.netty.buffer.ByteBuf;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.v388.serializer.MoveActorDeltaSerializer_v388;
import org.cloudburstmc.protocol.bedrock.packet.MoveActorDeltaPacket;
import org.cloudburstmc.protocol.bedrock.packet.MoveActorDeltaPacket.Flag;
import org.cloudburstmc.protocol.common.util.TriConsumer;

public class MoveActorDeltaSerializer_v419 extends MoveActorDeltaSerializer_v388 {

    protected static final TriConsumer<ByteBuf, BedrockCodecHelper, MoveActorDeltaPacket> READER_X =
            (buffer, helper, packet) -> packet.getMoveData().setNewPositionX(buffer.readFloatLE());
    protected static final TriConsumer<ByteBuf, BedrockCodecHelper, MoveActorDeltaPacket> READER_Y =
            (buffer, helper, packet) -> packet.getMoveData().setNewPositionY(buffer.readFloatLE());
    protected static final TriConsumer<ByteBuf, BedrockCodecHelper, MoveActorDeltaPacket> READER_Z =
            (buffer, helper, packet) -> packet.getMoveData().setNewPositionZ(buffer.readFloatLE());

    protected static final TriConsumer<ByteBuf, BedrockCodecHelper, MoveActorDeltaPacket> WRITER_X =
            (buffer, helper, packet) -> buffer.writeFloatLE(packet.getMoveData().getNewPositionX());
    protected static final TriConsumer<ByteBuf, BedrockCodecHelper, MoveActorDeltaPacket> WRITER_Y =
            (buffer, helper, packet) -> buffer.writeFloatLE(packet.getMoveData().getNewPositionY());
    protected static final TriConsumer<ByteBuf, BedrockCodecHelper, MoveActorDeltaPacket> WRITER_Z =
            (buffer, helper, packet) -> buffer.writeFloatLE(packet.getMoveData().getNewPositionZ());

    public static final MoveActorDeltaSerializer_v419 INSTANCE = new MoveActorDeltaSerializer_v419();

    protected MoveActorDeltaSerializer_v419() {
        super();

        this.readers.put(Flag.HAS_X, READER_X);
        this.readers.put(Flag.HAS_Y, READER_Y);
        this.readers.put(Flag.HAS_Z, READER_Z);

        this.writers.put(Flag.HAS_X, WRITER_X);
        this.writers.put(Flag.HAS_Y, WRITER_Y);
        this.writers.put(Flag.HAS_Z, WRITER_Z);
    }
}
