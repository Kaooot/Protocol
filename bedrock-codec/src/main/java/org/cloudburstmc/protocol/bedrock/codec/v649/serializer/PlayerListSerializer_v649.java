package org.cloudburstmc.protocol.bedrock.codec.v649.serializer;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.v390.serializer.PlayerListSerializer_v390;
import org.cloudburstmc.protocol.bedrock.data.payload.list.PlayerListAddEntry;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PlayerListSerializer_v649 extends PlayerListSerializer_v390 {
    public static final PlayerListSerializer_v649 INSTANCE = new PlayerListSerializer_v649();

    @Override
    protected void writePlayerListAddEntry(ByteBuf buffer, BedrockCodecHelper helper, PlayerListAddEntry entry) {
        super.writePlayerListAddEntry(buffer, helper, entry);
        buffer.writeBoolean(entry.isSubClient());
    }

    @Override
    protected PlayerListAddEntry readPlayerListAddEntry(ByteBuf buffer, BedrockCodecHelper helper) {
        final PlayerListAddEntry entry = super.readPlayerListAddEntry(buffer, helper);
        entry.setSubClient(buffer.readBoolean());
        return entry;
    }
}
