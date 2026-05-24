package org.cloudburstmc.protocol.bedrock.codec.v649.serializer;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.v486.serializer.LevelChunkSerializer_v486;
import org.cloudburstmc.protocol.bedrock.data.payload.common.DimensionType;
import org.cloudburstmc.protocol.bedrock.packet.LevelChunkPacket;
import org.cloudburstmc.protocol.common.util.VarInts;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LevelChunkSerializer_v649 extends LevelChunkSerializer_v486 {
    public static final LevelChunkSerializer_v649 INSTANCE = new LevelChunkSerializer_v649();

    @Override
    protected void writeChunkLocation(ByteBuf buffer, LevelChunkPacket packet) {
        super.writeChunkLocation(buffer, packet);
        VarInts.writeInt(buffer, packet.getDimension().getValue());
    }

    @Override
    protected void readChunkLocation(ByteBuf buffer, LevelChunkPacket packet) {
        super.readChunkLocation(buffer, packet);
        packet.setDimension(DimensionType.from(VarInts.readInt(buffer)));
    }
}
