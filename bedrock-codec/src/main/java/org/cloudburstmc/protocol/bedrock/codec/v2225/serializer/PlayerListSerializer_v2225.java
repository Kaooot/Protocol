package org.cloudburstmc.protocol.bedrock.codec.v2225.serializer;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.v2168.serializer.PlayerListSerializer_v2168;
import org.cloudburstmc.protocol.bedrock.data.connection.BuildPlatform;
import org.cloudburstmc.protocol.bedrock.data.player.list.PlayerListAddEntry;
import org.cloudburstmc.protocol.bedrock.packet.PlayerListPacket;
import org.cloudburstmc.protocol.common.util.VarInts;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PlayerListSerializer_v2225 extends PlayerListSerializer_v2168 {
    public static final PlayerListSerializer_v2225 INSTANCE = new PlayerListSerializer_v2225();

    @Override
    protected void writePlayerListAddEntry(ByteBuf buffer, BedrockCodecHelper helper, PlayerListPacket packet, PlayerListAddEntry entry) {
        helper.writeUuid(buffer, entry.getUuid());
        VarInts.writeLong(buffer, entry.getActorUniqueID());
        helper.writeString(buffer, entry.getPlayerName());
        helper.writeString(buffer, entry.getXblXUID());
        helper.writeString(buffer, entry.getPlayFabID());
        helper.writeString(buffer, entry.getPlatformOnlineID());
        buffer.writeIntLE(entry.getBuildPlatform().getId());
        helper.writeSkin(buffer, entry.getSerializedSkin());
        buffer.writeBoolean(entry.isTeacher());
        buffer.writeBoolean(entry.isHost());
        buffer.writeBoolean(entry.isSubClient());
        buffer.writeIntLE(entry.getPlayerColor());
    }

    @Override
    protected PlayerListAddEntry readPlayerListAddEntry(ByteBuf buffer, BedrockCodecHelper helper, PlayerListPacket packet) {
        final PlayerListAddEntry entry = new PlayerListAddEntry();
        entry.setUuid(helper.readUuid(buffer));
        entry.setActorUniqueID(VarInts.readLong(buffer));
        entry.setPlayerName(helper.readString(buffer));
        entry.setXblXUID(helper.readString(buffer));
        entry.setPlayFabID(helper.readString(buffer));
        entry.setPlatformOnlineID(helper.readString(buffer));
        entry.setBuildPlatform(BuildPlatform.from(buffer.readIntLE()));
        entry.setSerializedSkin(helper.readSkin(buffer));
        entry.setTeacher(buffer.readBoolean());
        entry.setHost(buffer.readBoolean());
        entry.setSubClient(buffer.readBoolean());
        entry.setPlayerColor(buffer.readIntLE());
        return entry;
    }
}