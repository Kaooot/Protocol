package org.cloudburstmc.protocol.bedrock.codec.v924.serializer;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.v712.serializer.ServerboundDiagnosticsSerializer_v712;
import org.cloudburstmc.protocol.bedrock.data.MemoryCategory;
import org.cloudburstmc.protocol.bedrock.data.MemoryCategoryCounter;
import org.cloudburstmc.protocol.bedrock.packet.ServerboundDiagnosticsPacket;

/**
 * @author Kaooot
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ServerboundDiagnosticsSerializer_v924 extends ServerboundDiagnosticsSerializer_v712 {
    public static final ServerboundDiagnosticsSerializer_v924 INSTANCE = new ServerboundDiagnosticsSerializer_v924();

    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, ServerboundDiagnosticsPacket packet) {
        super.serialize(buffer, helper, packet);
        helper.writeArray(buffer, packet.getMemoryCategoryValues(), this::writeMemoryCategoryCounter);
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, ServerboundDiagnosticsPacket packet) {
        super.deserialize(buffer, helper, packet);
        helper.readArray(buffer, packet.getMemoryCategoryValues(), this::readMemoryCategoryCounter);
    }

    protected void writeMemoryCategoryCounter(ByteBuf buffer, BedrockCodecHelper helper, MemoryCategoryCounter counter) {
        buffer.writeByte(counter.getCategory().ordinal());
        buffer.writeLongLE(counter.getCurrentBytes());
    }

    protected MemoryCategoryCounter readMemoryCategoryCounter(ByteBuf buffer, BedrockCodecHelper helper) {
        final MemoryCategoryCounter counter = new MemoryCategoryCounter();
        counter.setCategory(MemoryCategory.from(buffer.readUnsignedByte()));
        counter.setCurrentBytes(buffer.readLongLE());
        return counter;
    }
}