package org.cloudburstmc.protocol.bedrock.codec.v291.serializer;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.BedrockPacketSerializer;
import org.cloudburstmc.protocol.bedrock.data.payload.list.PlayerListAddEntry;
import org.cloudburstmc.protocol.bedrock.data.payload.list.PlayerListEntry;
import org.cloudburstmc.protocol.bedrock.data.payload.list.PlayerListPacketType;
import org.cloudburstmc.protocol.bedrock.data.payload.list.PlayerListRemoveEntry;
import org.cloudburstmc.protocol.bedrock.data.skin.ImageData;
import org.cloudburstmc.protocol.bedrock.data.skin.Skin;
import org.cloudburstmc.protocol.bedrock.packet.PlayerListPacket;
import org.cloudburstmc.protocol.common.util.VarInts;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PlayerListSerializer_v291 implements BedrockPacketSerializer<PlayerListPacket> {
    public static final PlayerListSerializer_v291 INSTANCE = new PlayerListSerializer_v291();


    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, PlayerListPacket packet) {
        final boolean add = packet.getEntries().get(0).getPacketType().equals(PlayerListPacketType.ADD);
        buffer.writeByte(add ? 0 : 1);
        helper.writeArray(buffer, packet.getEntries(), this::writePlayerListEntryVariant);
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, PlayerListPacket packet) {
        final int id = buffer.readUnsignedByte();
        final PlayerListPacketType packetType = id == 0 ? PlayerListPacketType.ADD : PlayerListPacketType.REMOVE;
        helper.readArray(buffer, packet.getEntries(), (buf, codecHelper) ->
                this.readPlayerListEntryVariant(buf, codecHelper, packetType));
    }

    protected void writePlayerListEntryVariant(ByteBuf buffer, BedrockCodecHelper helper, PlayerListEntry entry) {
        switch (entry.getPacketType()) {
            case REMOVE:
                this.writePlayerListRemoveEntry(buffer, helper, (PlayerListRemoveEntry) entry);
                break;
            case ADD:
                this.writePlayerListAddEntry(buffer, helper, (PlayerListAddEntry) entry);
                break;
        }
    }

    protected PlayerListEntry readPlayerListEntryVariant(ByteBuf buffer, BedrockCodecHelper helper, PlayerListPacketType packetType) {
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

        Skin skin = entry.getSkin();
        helper.writeString(buffer, skin.getSkinId());
        skin.getSkinData().checkLegacySkinSize();
        helper.writeByteArray(buffer, skin.getSkinData().getImage());
        skin.getCapeData().checkLegacyCapeSize();
        helper.writeByteArray(buffer, skin.getCapeData().getImage());
        helper.writeString(buffer, skin.getGeometryName());
        helper.writeString(buffer, skin.getGeometryData());

        helper.writeString(buffer, entry.getXblXUID());
        helper.writeString(buffer, entry.getPlatformOnlineID());
    }

    protected PlayerListAddEntry readPlayerListAddEntry(ByteBuf buffer, BedrockCodecHelper helper) {
        final PlayerListAddEntry entry = new PlayerListAddEntry();
        entry.setUuid(helper.readUuid(buffer));
        entry.setActorUniqueID(VarInts.readLong(buffer));
        entry.setPlayerName(helper.readString(buffer));

        String skinId = helper.readString(buffer);
        ImageData skinData = ImageData.of(helper.readByteArray(buffer));
        ImageData capeData = ImageData.of(64, 32, helper.readByteArray(buffer));
        String geometryName = helper.readString(buffer);
        String geometryData = helper.readString(buffer);

        entry.setSkin(Skin.of(skinId, "", skinData, capeData, geometryName, geometryData, false));
        entry.setXblXUID(helper.readString(buffer));
        entry.setPlatformOnlineID(helper.readString(buffer));
        return entry;
    }
}