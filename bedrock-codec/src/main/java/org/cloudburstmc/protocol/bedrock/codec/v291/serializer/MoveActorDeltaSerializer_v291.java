package org.cloudburstmc.protocol.bedrock.codec.v291.serializer;

import io.netty.buffer.ByteBuf;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.BedrockPacketSerializer;
import org.cloudburstmc.protocol.bedrock.packet.MoveActorDeltaPacket;
import org.cloudburstmc.protocol.common.util.TriConsumer;
import org.cloudburstmc.protocol.common.util.VarInts;

import java.util.EnumMap;
import java.util.Set;

public class MoveActorDeltaSerializer_v291 implements BedrockPacketSerializer<MoveActorDeltaPacket> {
    protected static final TriConsumer<ByteBuf, BedrockCodecHelper, MoveActorDeltaPacket> READER_DELTA_X =
            (buffer, helper, packet) -> packet.setDeltaX(VarInts.readInt(buffer));
    protected static final TriConsumer<ByteBuf, BedrockCodecHelper, MoveActorDeltaPacket> READER_DELTA_Y =
            (buffer, helper, packet) -> packet.setDeltaY(VarInts.readInt(buffer));
    protected static final TriConsumer<ByteBuf, BedrockCodecHelper, MoveActorDeltaPacket> READER_DELTA_Z =
            (buffer, helper, packet) -> packet.setDeltaZ(VarInts.readInt(buffer));

    protected static final TriConsumer<ByteBuf, BedrockCodecHelper, MoveActorDeltaPacket> READER_PITCH =
            (buffer, helper, packet) -> packet.getData().setRotationX(helper.readByteAngle(buffer));
    protected static final TriConsumer<ByteBuf, BedrockCodecHelper, MoveActorDeltaPacket> READER_YAW =
            (buffer, helper, packet) -> packet.getData().setRotationY(helper.readByteAngle(buffer));
    protected static final TriConsumer<ByteBuf, BedrockCodecHelper, MoveActorDeltaPacket> READER_HEAD_YAW =
            (buffer, helper, packet) -> packet.getData().setRotationYHead(helper.readByteAngle(buffer));

    protected static final TriConsumer<ByteBuf, BedrockCodecHelper, MoveActorDeltaPacket> WRITER_DELTA_X =
            (buffer, helper, packet) -> VarInts.writeInt(buffer, packet.getDeltaX());
    protected static final TriConsumer<ByteBuf, BedrockCodecHelper, MoveActorDeltaPacket> WRITER_DELTA_Y =
            (buffer, helper, packet) -> VarInts.writeInt(buffer, packet.getDeltaY());
    protected static final TriConsumer<ByteBuf, BedrockCodecHelper, MoveActorDeltaPacket> WRITER_DELTA_Z =
            (buffer, helper, packet) -> VarInts.writeInt(buffer, packet.getDeltaZ());

    protected static final TriConsumer<ByteBuf, BedrockCodecHelper, MoveActorDeltaPacket> WRITER_PITCH =
            (buffer, helper, packet) -> helper.writeByteAngle(buffer, packet.getData().getRotationX());
    protected static final TriConsumer<ByteBuf, BedrockCodecHelper, MoveActorDeltaPacket> WRITER_YAW =
            (buffer, helper, packet) -> helper.writeByteAngle(buffer, packet.getData().getRotationY());
    protected static final TriConsumer<ByteBuf, BedrockCodecHelper, MoveActorDeltaPacket> WRITER_HEAD_YAW =
            (buffer, helper, packet) -> helper.writeByteAngle(buffer, packet.getData().getRotationYHead());

    protected static final MoveActorDeltaPacket.Flag[] FLAGS = MoveActorDeltaPacket.Flag.values();

    public static final MoveActorDeltaSerializer_v291 INSTANCE = new MoveActorDeltaSerializer_v291();

    protected final EnumMap<MoveActorDeltaPacket.Flag, TriConsumer<ByteBuf, BedrockCodecHelper, MoveActorDeltaPacket>> readers = new EnumMap<>(MoveActorDeltaPacket.Flag.class);
    protected final EnumMap<MoveActorDeltaPacket.Flag, TriConsumer<ByteBuf, BedrockCodecHelper, MoveActorDeltaPacket>> writers = new EnumMap<>(MoveActorDeltaPacket.Flag.class);

    protected MoveActorDeltaSerializer_v291() {
        this.readers.put(MoveActorDeltaPacket.Flag.HAS_X, READER_DELTA_X);
        this.readers.put(MoveActorDeltaPacket.Flag.HAS_Y, READER_DELTA_Y);
        this.readers.put(MoveActorDeltaPacket.Flag.HAS_Z, READER_DELTA_Z);
        this.readers.put(MoveActorDeltaPacket.Flag.HAS_PITCH, READER_PITCH);
        this.readers.put(MoveActorDeltaPacket.Flag.HAS_YAW, READER_YAW);
        this.readers.put(MoveActorDeltaPacket.Flag.HAS_HEAD_YAW, READER_HEAD_YAW);

        this.writers.put(MoveActorDeltaPacket.Flag.HAS_X, WRITER_DELTA_X);
        this.writers.put(MoveActorDeltaPacket.Flag.HAS_Y, WRITER_DELTA_Y);
        this.writers.put(MoveActorDeltaPacket.Flag.HAS_Z, WRITER_DELTA_Z);
        this.writers.put(MoveActorDeltaPacket.Flag.HAS_PITCH, WRITER_PITCH);
        this.writers.put(MoveActorDeltaPacket.Flag.HAS_YAW, WRITER_YAW);
        this.writers.put(MoveActorDeltaPacket.Flag.HAS_HEAD_YAW, WRITER_HEAD_YAW);
    }

    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, MoveActorDeltaPacket packet) {
        VarInts.writeUnsignedLong(buffer, packet.getData().getActorRuntimeID());

        int flagsIndex = buffer.writerIndex();
        buffer.writeByte(0); // flags

        int flags = 0;
        for (MoveActorDeltaPacket.Flag flag : packet.getFlags()) {
            flags |= 1 << flag.ordinal();

            TriConsumer<ByteBuf, BedrockCodecHelper, MoveActorDeltaPacket> writer = this.writers.get(flag);
            if (writer != null) {
                writer.accept(buffer, helper, packet);
            }
        }

        // Go back to flags and set them
        int currentIndex = buffer.writerIndex();
        buffer.writerIndex(flagsIndex);
        buffer.writeByte(flags);
        buffer.writerIndex(currentIndex);
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, MoveActorDeltaPacket packet) {
        packet.getData().setActorRuntimeID(VarInts.readUnsignedLong(buffer));
        int flags = buffer.readUnsignedByte();
        Set<MoveActorDeltaPacket.Flag> flagSet = packet.getFlags();

        for (MoveActorDeltaPacket.Flag flag : FLAGS) {
            if ((flags & (1 << flag.ordinal())) != 0) {
                flagSet.add(flag);
                TriConsumer<ByteBuf, BedrockCodecHelper, MoveActorDeltaPacket> reader = this.readers.get(flag);
                if (reader != null) {
                    reader.accept(buffer, helper, packet);
                }
            }
        }
    }
}
