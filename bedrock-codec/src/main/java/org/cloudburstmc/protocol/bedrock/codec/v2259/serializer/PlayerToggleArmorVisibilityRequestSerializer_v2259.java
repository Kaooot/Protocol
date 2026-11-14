package org.cloudburstmc.protocol.bedrock.codec.v2259.serializer;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.BedrockPacketSerializer;
import org.cloudburstmc.protocol.bedrock.packet.PlayerToggleArmorVisibilityRequestPacket;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PlayerToggleArmorVisibilityRequestSerializer_v2259 implements BedrockPacketSerializer<PlayerToggleArmorVisibilityRequestPacket> {
    public static final PlayerToggleArmorVisibilityRequestSerializer_v2259 INSTANCE = new PlayerToggleArmorVisibilityRequestSerializer_v2259();

    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, PlayerToggleArmorVisibilityRequestPacket packet) {
        buffer.writeBoolean(packet.isHideArmor());
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, PlayerToggleArmorVisibilityRequestPacket packet) {
        packet.setHideArmor(buffer.readBoolean());
    }
}