package org.cloudburstmc.protocol.bedrock.codec.v291.serializer;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.BedrockPacketSerializer;
import org.cloudburstmc.protocol.bedrock.data.skin.SkinImage;
import org.cloudburstmc.protocol.bedrock.data.skin.SerializedSkin;
import org.cloudburstmc.protocol.bedrock.packet.PlayerSkinPacket;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PlayerSkinSerializer_v291 implements BedrockPacketSerializer<PlayerSkinPacket> {
    public static final PlayerSkinSerializer_v291 INSTANCE = new PlayerSkinSerializer_v291();

    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, PlayerSkinPacket packet) {
        helper.writeUuid(buffer, packet.getUuid());
        SerializedSkin skin = packet.getSerializedSkin();
        helper.writeString(buffer, skin.getID());
        helper.writeString(buffer, packet.getLocalizedNewSkinName());
        helper.writeString(buffer, packet.getLocalizedOldSkinName());
        skin.getImageData().checkLegacySkinSize();
        helper.writeByteArray(buffer, skin.getImageData().getImage());
        skin.getCapeImageData().checkLegacyCapeSize();
        helper.writeByteArray(buffer, skin.getCapeImageData().getImage());
        helper.writeString(buffer, skin.getResourcePatch());
        helper.writeString(buffer, skin.getGeometryData());
        buffer.writeBoolean(skin.isPremium());
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, PlayerSkinPacket packet) {
        packet.setUuid(helper.readUuid(buffer));
        String skinId = helper.readString(buffer);
        packet.setLocalizedNewSkinName(helper.readString(buffer));
        packet.setLocalizedOldSkinName(helper.readString(buffer));
        SkinImage skinData = SkinImage.of(helper.readByteArray(buffer, SkinImage.SKIN_PERSONA_SIZE));
        SkinImage capeData = SkinImage.of(64, 32, helper.readByteArray(buffer, SkinImage.SINGLE_SKIN_SIZE));
        String geometryName = helper.readString(buffer);
        String geometryData = helper.readString(buffer);
        boolean premium = buffer.readBoolean();
        packet.setSerializedSkin(SerializedSkin.builder()
                .ID(skinId)
                .imageData(skinData)
                .capeImageData(capeData)
                .resourcePatch(geometryName)
                .geometryData(geometryData)
                .isPremium(premium)
                .build());
    }
}
