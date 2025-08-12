package org.cloudburstmc.protocol.bedrock.codec.v388.serializer;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.v291.serializer.MoveActorDeltaSerializer_v291;
import org.cloudburstmc.protocol.bedrock.packet.MoveActorDeltaPacket;
import org.cloudburstmc.protocol.bedrock.packet.MoveActorDeltaPacket.Flag;
import org.cloudburstmc.protocol.common.util.TriConsumer;
import org.cloudburstmc.protocol.common.util.VarInts;

import java.util.Set;

@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class MoveActorDeltaSerializer_v388 extends MoveActorDeltaSerializer_v291 {

    public static final MoveActorDeltaSerializer_v388 INSTANCE = new MoveActorDeltaSerializer_v388();

    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, MoveActorDeltaPacket packet) {
        VarInts.writeUnsignedLong(buffer, packet.getData().getActorRuntimeID());

        int flagsIndex = buffer.writerIndex();
        buffer.writeShortLE(0); // flags

        // For some reason, the bitflags are preset to 0xFFFF and removed if not used.
        int flags = 0xFFFF;
        for (Flag flag : FLAGS) {
            if (!packet.getFlags().contains(flag)) {
                flags &= ~(1 << flag.ordinal());
                continue;
            }

            TriConsumer<ByteBuf, BedrockCodecHelper, MoveActorDeltaPacket> writer = this.writers.get(flag);
            if (writer != null) {
                writer.accept(buffer, helper, packet);
            }
        }

        // Go back to flags and set them
        buffer.setShortLE(flagsIndex, flags);
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, MoveActorDeltaPacket packet) {
        packet.getData().setActorRuntimeID(VarInts.readUnsignedLong(buffer));
        int flags = buffer.readUnsignedShortLE();
        Set<Flag> flagSet = packet.getFlags();

        for (Flag flag : FLAGS) {
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
