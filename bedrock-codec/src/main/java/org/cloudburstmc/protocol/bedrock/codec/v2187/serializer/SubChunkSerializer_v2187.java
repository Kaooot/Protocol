package org.cloudburstmc.protocol.bedrock.codec.v2187.serializer;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.v2168.serializer.SubChunkSerializer_v2168;
import org.cloudburstmc.protocol.common.util.VarInts;

/**
 * @author Kaooot
 */
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class SubChunkSerializer_v2187 extends SubChunkSerializer_v2168 {
    public static final SubChunkSerializer_v2187 INSTANCE = new SubChunkSerializer_v2187();

    protected static final int HEIGHT_MAP_ARRAY_LENGTH = 16;

    @Override
    protected void writeHeightMap(ByteBuf buffer, ByteBuf heightMap) {
        for (int column = 0; column < HEIGHT_MAP_ARRAY_LENGTH; column++) {
            VarInts.writeUnsignedInt(buffer, HEIGHT_MAP_ARRAY_LENGTH);
            buffer.writeBytes(
                    heightMap,
                    heightMap.readerIndex() + column * HEIGHT_MAP_ARRAY_LENGTH,
                    HEIGHT_MAP_ARRAY_LENGTH
            );
        }
    }

    @Override
    protected ByteBuf readHeightMap(ByteBuf buffer) {
        final ByteBuf heightMap = buffer.alloc().buffer(HEIGHT_MAP_LENGTH);
        for (int column = 0; column < HEIGHT_MAP_ARRAY_LENGTH; column++) {
            final int length = VarInts.readUnsignedInt(buffer);
            buffer.readBytes(heightMap, length);
        }
        return heightMap;
    }
}