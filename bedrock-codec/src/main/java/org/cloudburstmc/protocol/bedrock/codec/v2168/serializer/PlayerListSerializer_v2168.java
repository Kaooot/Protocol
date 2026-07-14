package org.cloudburstmc.protocol.bedrock.codec.v2168.serializer;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.BedrockPacketSerializer;
import org.cloudburstmc.protocol.bedrock.data.BuildPlatform;
import org.cloudburstmc.protocol.bedrock.data.payload.list.PlayerListAddEntry;
import org.cloudburstmc.protocol.bedrock.data.payload.list.PlayerListEntry;
import org.cloudburstmc.protocol.bedrock.data.payload.list.PlayerListPacketType;
import org.cloudburstmc.protocol.bedrock.data.payload.list.PlayerListRemoveEntry;
import org.cloudburstmc.protocol.bedrock.packet.PlayerListPacket;
import org.cloudburstmc.protocol.common.util.VarInts;

/**
 * @author Kaooot
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PlayerListSerializer_v2168 implements BedrockPacketSerializer<PlayerListPacket> {
    public static final PlayerListSerializer_v2168 INSTANCE = new PlayerListSerializer_v2168();

    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, PlayerListPacket packet) {
        helper.writeArray(buffer, packet.getEntries(), this::writePlayerListEntryVariant);
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, PlayerListPacket packet) {
        helper.readArray(buffer, packet.getEntries(), this::readPlayerListEntryVariant);
    }

    protected void writePlayerListEntryVariant(ByteBuf buffer, BedrockCodecHelper helper, PlayerListEntry entry) {
        VarInts.writeUnsignedInt(buffer, entry.getPacketType().ordinal());
        buffer.writeByte(entry.getPacketType().getLegacyId());
        switch (entry.getPacketType()) {
            case REMOVE:
                this.writePlayerListRemoveEntry(buffer, helper, (PlayerListRemoveEntry) entry);
                break;
            case ADD:
                this.writePlayerListAddEntry(buffer, helper, (PlayerListAddEntry) entry);
                break;
        }
    }

    protected PlayerListEntry readPlayerListEntryVariant(ByteBuf buffer, BedrockCodecHelper helper) {
        final PlayerListPacketType packetType = PlayerListPacketType.from(VarInts.readUnsignedInt(buffer));
        buffer.readUnsignedByte();
        switch (packetType) {
            case REMOVE:
                return this.readPlayerListRemoveEntry(buffer, helper);
            case ADD:
                return this.readPlayerListAddEntry(buffer, helper);
            default:
                throw new IllegalStateException("Received invalid PlayerListPacketType");
        }
    }

    protected void writePlayerListRemoveEntry(ByteBuf buffer, BedrockCodecHelper helper, PlayerListRemoveEntry entry) {
        helper.writeUuid(buffer, entry.getUuid());
    }

    protected PlayerListRemoveEntry readPlayerListRemoveEntry(ByteBuf buffer, BedrockCodecHelper helper) {
        final PlayerListRemoveEntry entry = new PlayerListRemoveEntry();
        entry.setUuid(helper.readUuid(buffer));
        return entry;
    }

    protected void writePlayerListAddEntry(ByteBuf buffer, BedrockCodecHelper helper, PlayerListAddEntry entry) {
        helper.writeUuid(buffer, entry.getUuid());
        VarInts.writeLong(buffer, entry.getActorUniqueID());
        helper.writeString(buffer, entry.getPlayerName());
        helper.writeString(buffer, entry.getXblXUID());
        helper.writeString(buffer, entry.getPlatformOnlineID());
        buffer.writeIntLE(entry.getBuildPlatform().getId());
        helper.writeSerializedSkin(buffer, entry.getSerializedSkin());
        buffer.writeBoolean(entry.isTeacher());
        buffer.writeBoolean(entry.isHost());
        buffer.writeBoolean(entry.isSubClient());
        buffer.writeIntLE(entry.getPlayerColor());
    }

    protected PlayerListAddEntry readPlayerListAddEntry(ByteBuf buffer, BedrockCodecHelper helper) {
        final PlayerListAddEntry entry = new PlayerListAddEntry();
        entry.setUuid(helper.readUuid(buffer));
        entry.setActorUniqueID(VarInts.readLong(buffer));
        entry.setPlayerName(helper.readString(buffer));
        entry.setXblXUID(helper.readString(buffer));
        entry.setPlatformOnlineID(helper.readString(buffer));
        entry.setBuildPlatform(BuildPlatform.from(buffer.readIntLE()));
        entry.setSerializedSkin(helper.readSerializedSkin(buffer));
        entry.setTeacher(buffer.readBoolean());
        entry.setHost(buffer.readBoolean());
        entry.setSubClient(buffer.readBoolean());
        entry.setPlayerColor(buffer.readIntLE());
        return entry;
    }
}