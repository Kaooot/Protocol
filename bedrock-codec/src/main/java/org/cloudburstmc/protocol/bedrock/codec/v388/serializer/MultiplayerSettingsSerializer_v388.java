package org.cloudburstmc.protocol.bedrock.codec.v388.serializer;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.BedrockPacketSerializer;
import org.cloudburstmc.protocol.bedrock.data.MultiplayerSettingsPacketType;
import org.cloudburstmc.protocol.bedrock.packet.MultiplayerSettingsPacket;
import org.cloudburstmc.protocol.common.util.VarInts;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class MultiplayerSettingsSerializer_v388 implements BedrockPacketSerializer<MultiplayerSettingsPacket> {

    public static final MultiplayerSettingsSerializer_v388 INSTANCE = new MultiplayerSettingsSerializer_v388();

    private static final MultiplayerSettingsPacketType[] VALUES = MultiplayerSettingsPacketType.values();

    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, MultiplayerSettingsPacket packet) {
        VarInts.writeInt(buffer, packet.getType().ordinal());
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, MultiplayerSettingsPacket packet) {
        packet.setType(VALUES[VarInts.readInt(buffer)]);
    }
}
