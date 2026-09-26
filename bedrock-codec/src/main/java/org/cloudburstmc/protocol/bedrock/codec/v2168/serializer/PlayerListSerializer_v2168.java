package org.cloudburstmc.protocol.bedrock.codec.v2168.serializer;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.BedrockPacketSerializer;
import org.cloudburstmc.protocol.bedrock.codec.VariantCodec;
import org.cloudburstmc.protocol.bedrock.data.connection.BuildPlatform;
import org.cloudburstmc.protocol.bedrock.data.player.list.PlayerListAddEntry;
import org.cloudburstmc.protocol.bedrock.data.player.list.PlayerListPacketType;
import org.cloudburstmc.protocol.bedrock.data.player.list.PlayerListRemoveEntry;
import org.cloudburstmc.protocol.bedrock.packet.PlayerListPacket;
import org.cloudburstmc.protocol.common.util.VarInts;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PlayerListSerializer_v2168 implements BedrockPacketSerializer<PlayerListPacket> {
    public static final PlayerListSerializer_v2168 INSTANCE = new PlayerListSerializer_v2168();

    protected static final int MAX_ENTRIES = 1000;

    protected final VariantCodec<PlayerListPacket> entryVariant = VariantCodec.<PlayerListPacketType, PlayerListPacket>builder(PlayerListPacketType::ordinal)
            .prefix(
                    (buffer, helper, owner, value) -> buffer.writeByte(PlayerListPacketType.from(value).getLegacyId()),
                    (buffer, helper, owner) -> (int) buffer.readUnsignedByte()
            )
            .add(
                    PlayerListPacketType.REMOVE,
                    PlayerListRemoveEntry.class,
                    this::writePlayerListRemoveEntry,
                    this::readPlayerListRemoveEntry
            )
            .add(
                    PlayerListPacketType.ADD,
                    PlayerListAddEntry.class,
                    this::writePlayerListAddEntry,
                    this::readPlayerListAddEntry
            )
            .build();

    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, PlayerListPacket packet) {
        helper.writeArray(
                buffer,
                packet.getEntries(),
                (buf, codecHelper, entry) -> this.entryVariant.write(buf, codecHelper, packet, entry)
        );
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, PlayerListPacket packet) {
        helper.readArray(
                buffer,
                packet.getEntries(),
                (buf, codecHelper) -> this.entryVariant.read(buf, codecHelper, packet),
                MAX_ENTRIES
        );
    }

    protected void writePlayerListRemoveEntry(ByteBuf buffer, BedrockCodecHelper helper, PlayerListPacket packet, PlayerListRemoveEntry entry) {
        helper.writeUuid(buffer, entry.getUuid());
    }

    protected PlayerListRemoveEntry readPlayerListRemoveEntry(ByteBuf buffer, BedrockCodecHelper helper, PlayerListPacket packet) {
        final PlayerListRemoveEntry entry = new PlayerListRemoveEntry();
        entry.setUuid(helper.readUuid(buffer));
        return entry;
    }

    protected void writePlayerListAddEntry(ByteBuf buffer, BedrockCodecHelper helper, PlayerListPacket packet, PlayerListAddEntry entry) {
        helper.writeUuid(buffer, entry.getUuid());
        VarInts.writeLong(buffer, entry.getActorUniqueID());
        helper.writeString(buffer, entry.getPlayerName());
        helper.writeString(buffer, entry.getXblXUID());
        helper.writeString(buffer, entry.getPlatformOnlineID());
        buffer.writeIntLE(entry.getBuildPlatform().getId());
        helper.writeSkin(buffer, entry.getSerializedSkin());
        buffer.writeBoolean(entry.isTeacher());
        buffer.writeBoolean(entry.isHost());
        buffer.writeBoolean(entry.isSubClient());
        buffer.writeIntLE(entry.getPlayerColor());
    }

    protected PlayerListAddEntry readPlayerListAddEntry(ByteBuf buffer, BedrockCodecHelper helper, PlayerListPacket packet) {
        final PlayerListAddEntry entry = new PlayerListAddEntry();
        entry.setUuid(helper.readUuid(buffer));
        entry.setActorUniqueID(VarInts.readLong(buffer));
        entry.setPlayerName(helper.readString(buffer));
        entry.setXblXUID(helper.readString(buffer));
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