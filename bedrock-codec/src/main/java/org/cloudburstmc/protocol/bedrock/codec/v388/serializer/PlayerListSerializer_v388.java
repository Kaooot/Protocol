package org.cloudburstmc.protocol.bedrock.codec.v388.serializer;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.v291.serializer.PlayerListSerializer_v291;
import org.cloudburstmc.protocol.bedrock.data.BuildPlatform;
import org.cloudburstmc.protocol.bedrock.data.payload.list.PlayerListAddEntry;
import org.cloudburstmc.protocol.common.util.VarInts;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PlayerListSerializer_v388 extends PlayerListSerializer_v291 {
    public static final PlayerListSerializer_v388 INSTANCE = new PlayerListSerializer_v388();

    @Override
    protected void writePlayerListAddEntry(ByteBuf buffer, BedrockCodecHelper helper, PlayerListAddEntry entry) {
        helper.writeUuid(buffer, entry.getUuid());
        VarInts.writeLong(buffer, entry.getActorUniqueID());
        helper.writeString(buffer, entry.getPlayerName());
        helper.writeString(buffer, entry.getXblXUID());
        helper.writeString(buffer, entry.getPlatformOnlineID());
        buffer.writeIntLE(entry.getBuildPlatform().getId());
        helper.writeSkin(buffer, entry.getSkin());
        buffer.writeBoolean(entry.isTeacher());
        buffer.writeBoolean(entry.isHost());
    }

    @Override
    protected PlayerListAddEntry readPlayerListAddEntry(ByteBuf buffer, BedrockCodecHelper helper) {
        final PlayerListAddEntry entry = new PlayerListAddEntry();
        entry.setUuid(helper.readUuid(buffer));
        entry.setActorUniqueID(VarInts.readLong(buffer));
        entry.setPlayerName(helper.readString(buffer));
        entry.setXblXUID(helper.readString(buffer));
        entry.setPlatformOnlineID(helper.readString(buffer));
        entry.setBuildPlatform(BuildPlatform.from(buffer.readIntLE()));
        entry.setSkin(helper.readSkin(buffer));
        entry.setTeacher(buffer.readBoolean());
        entry.setHost(buffer.readBoolean());
        return entry;
    }
}