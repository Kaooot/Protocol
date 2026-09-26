package org.cloudburstmc.protocol.bedrock.codec.v291.serializer;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.BedrockPacketSerializer;
import org.cloudburstmc.protocol.bedrock.data.player.list.PlayerListAddEntry;
import org.cloudburstmc.protocol.bedrock.data.player.list.PlayerListPacketType;
import org.cloudburstmc.protocol.bedrock.data.player.list.PlayerListRemoveEntry;
import org.cloudburstmc.protocol.bedrock.data.skin.SerializedSkin;
import org.cloudburstmc.protocol.bedrock.data.skin.SkinImage;
import org.cloudburstmc.protocol.bedrock.packet.PlayerListPacket;
import org.cloudburstmc.protocol.common.util.VarInts;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PlayerListSerializer_v291 implements BedrockPacketSerializer<PlayerListPacket> {
    public static final PlayerListSerializer_v291 INSTANCE = new PlayerListSerializer_v291();

    protected static final int MAX_ENTRIES = 1000;

    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, PlayerListPacket packet) {
        buffer.writeByte(
                packet.getEntries().get(0) instanceof PlayerListRemoveEntry ?
                        PlayerListPacketType.REMOVE.getLegacyId() : PlayerListPacketType.ADD.getLegacyId()
        );
        helper.writeArray(buffer, packet.getEntries(), this::writePlayerListEntry);
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, PlayerListPacket packet) {
        final PlayerListPacketType packetType = PlayerListPacketType.fromLegacy(buffer.readUnsignedByte());
        helper.readArray(buffer, packet.getEntries(), (buf, codecHelper) ->
                this.readPlayerListEntry(buf, codecHelper, packetType), MAX_ENTRIES);
    }

    protected void writePlayerListEntry(ByteBuf buffer, BedrockCodecHelper helper, Object entry) {
        if (entry instanceof PlayerListRemoveEntry) {
            this.writePlayerListRemoveEntry(buffer, helper, (PlayerListRemoveEntry) entry);
        } else {
            this.writePlayerListAddEntry(buffer, helper, (PlayerListAddEntry) entry);
        }
    }

    protected Object readPlayerListEntry(ByteBuf buffer, BedrockCodecHelper helper, PlayerListPacketType packetType) {
        return packetType.equals(PlayerListPacketType.REMOVE) ?
                this.readPlayerListRemoveEntry(buffer, helper) :
                this.readPlayerListAddEntry(buffer, helper);
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

        SerializedSkin skin = entry.getSerializedSkin();
        helper.writeString(buffer, skin.getID());
        skin.getImageData().checkLegacySkinSize();
        helper.writeByteArray(buffer, skin.getImageData().getImage());
        skin.getCapeImageData().checkLegacyCapeSize();
        helper.writeByteArray(buffer, skin.getCapeImageData().getImage());
        helper.writeString(buffer, skin.getResourcePatch());
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
        SkinImage skinData = SkinImage.of(helper.readByteArray(buffer));
        SkinImage capeData = SkinImage.of(64, 32, helper.readByteArray(buffer));
        String geometryName = helper.readString(buffer);
        String geometryData = helper.readString(buffer);

        entry.setSerializedSkin(SerializedSkin.builder()
                .ID(skinId)
                .imageData(skinData)
                .capeImageData(capeData)
                .resourcePatch(geometryName)
                .geometryData(geometryData)
                .build());
        entry.setXblXUID(helper.readString(buffer));
        entry.setPlatformOnlineID(helper.readString(buffer));
        return entry;
    }
}
