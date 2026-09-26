package org.cloudburstmc.protocol.bedrock.codec.v2168.serializer;

import io.netty.buffer.ByteBuf;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.v390.serializer.PlayerSkinSerializer_v390;
import org.cloudburstmc.protocol.bedrock.packet.PlayerSkinPacket;

public class PlayerSkinSerializer_v2168 extends PlayerSkinSerializer_v390 {

    public static final PlayerSkinSerializer_v2168 INSTANCE = new PlayerSkinSerializer_v2168();

    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, PlayerSkinPacket packet) {
        helper.writeUuid(buffer, packet.getUuid());
        helper.writeSkin(buffer, packet.getSerializedSkin());
        helper.writeString(buffer, packet.getLocalizedNewSkinName());
        helper.writeString(buffer, packet.getLocalizedOldSkinName());
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, PlayerSkinPacket packet) {
        packet.setUuid(helper.readUuid(buffer));
        packet.setSerializedSkin(helper.readSkin(buffer));
        packet.setLocalizedNewSkinName(helper.readString(buffer));
        packet.setLocalizedOldSkinName(helper.readString(buffer));
    }
}
