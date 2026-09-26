package org.cloudburstmc.protocol.bedrock.codec.v361.serializer;

import io.netty.buffer.ByteBuf;
import it.unimi.dsi.fastutil.longs.LongList;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.BedrockPacketSerializer;
import org.cloudburstmc.protocol.bedrock.packet.ClientCacheBlobStatusPacket;
import org.cloudburstmc.protocol.common.util.VarInts;

import java.util.function.LongConsumer;

import static org.cloudburstmc.protocol.common.util.Preconditions.checkArgument;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ClientCacheBlobStatusSerializer_v361 implements BedrockPacketSerializer<ClientCacheBlobStatusPacket> {
    public static final ClientCacheBlobStatusSerializer_v361 INSTANCE = new ClientCacheBlobStatusSerializer_v361();

    protected static final int MAX_ITEMS = 4095;

    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, ClientCacheBlobStatusPacket packet) {
        LongList missingIds = packet.getMissingIds();
        LongList foundIds = packet.getFoundIds();
        VarInts.writeUnsignedInt(buffer, missingIds.size());
        VarInts.writeUnsignedInt(buffer, foundIds.size());

        missingIds.forEach((LongConsumer) buffer::writeLongLE);
        foundIds.forEach((LongConsumer) buffer::writeLongLE);
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, ClientCacheBlobStatusPacket packet) {
        final int missingIdsLength = VarInts.readUnsignedInt(buffer);
        checkArgument(missingIdsLength <= MAX_ITEMS, "Tried to read %s Missing Ids but maximum is %s", missingIdsLength, MAX_ITEMS);

        final int foundIdsLength = VarInts.readUnsignedInt(buffer);
        checkArgument(foundIdsLength <= MAX_ITEMS, "Tried to read %s Found Ids but maximum is %s", foundIdsLength, MAX_ITEMS);

        final LongList missingIds = packet.getMissingIds();
        for (int i = 0; i < missingIdsLength; i++) {
            missingIds.add(buffer.readLongLE());
        }

        final LongList foundId = packet.getFoundIds();
        for (int i = 0; i < foundIdsLength; i++) {
            foundId.add(buffer.readLongLE());
        }
    }
}