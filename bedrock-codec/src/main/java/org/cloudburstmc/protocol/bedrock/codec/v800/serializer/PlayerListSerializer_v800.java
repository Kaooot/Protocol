package org.cloudburstmc.protocol.bedrock.codec.v800.serializer;

import io.netty.buffer.ByteBuf;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.v649.serializer.PlayerListSerializer_v649;
import org.cloudburstmc.protocol.bedrock.data.payload.list.PlayerListAddEntry;

public class PlayerListSerializer_v800 extends PlayerListSerializer_v649 {

    public static final PlayerListSerializer_v800 INSTANCE = new PlayerListSerializer_v800();

    @Override
    protected void writePlayerListAddEntry(ByteBuf buffer, BedrockCodecHelper helper, PlayerListAddEntry entry) {
        super.writePlayerListAddEntry(buffer, helper, entry);
        buffer.writeIntLE(entry.getPlayerColor());
    }

    @Override
    protected PlayerListAddEntry readPlayerListAddEntry(ByteBuf buffer, BedrockCodecHelper helper) {
        final PlayerListAddEntry entry = super.readPlayerListAddEntry(buffer, helper);
        entry.setPlayerColor(buffer.readIntLE());
        return entry;
    }
}