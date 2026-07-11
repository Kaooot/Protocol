package org.cloudburstmc.protocol.bedrock.codec.v2168.serializer;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.BedrockPacketSerializer;
import org.cloudburstmc.protocol.bedrock.packet.PlayerSkinPacket;

/**
 * @author Kaooot
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PlayerSkinSerializer_v2168 implements BedrockPacketSerializer<PlayerSkinPacket> {
    public static final PlayerSkinSerializer_v2168 INSTANCE = new PlayerSkinSerializer_v2168();

    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, PlayerSkinPacket packet) {
        helper.writeUuid(buffer, packet.getUuid());
        helper.writeSerializedSkin(buffer, packet.getSerializedSkin());
        helper.writeString(buffer, packet.getLocalizedOldSkinName());
        helper.writeString(buffer, packet.getLocalizedNewSkinName());
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, PlayerSkinPacket packet) {
        packet.setUuid(helper.readUuid(buffer));
        packet.setSerializedSkin(helper.readSerializedSkin(buffer));
        packet.setLocalizedOldSkinName(helper.readString(buffer));
        packet.setLocalizedNewSkinName(helper.readString(buffer));
    }
}