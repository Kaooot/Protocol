package org.cloudburstmc.protocol.bedrock.codec.v2259.serializer;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.BedrockPacketSerializer;
import org.cloudburstmc.protocol.bedrock.packet.ServerboundRequestOnlineMusicPlaybackPacket;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ServerboundRequestOnlineMusicPlaybackSerializer_v2259 implements BedrockPacketSerializer<ServerboundRequestOnlineMusicPlaybackPacket> {
    public static final ServerboundRequestOnlineMusicPlaybackSerializer_v2259 INSTANCE = new ServerboundRequestOnlineMusicPlaybackSerializer_v2259();

    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, ServerboundRequestOnlineMusicPlaybackPacket packet) {
        buffer.writeIntLE(packet.getRequestID());
        helper.writeString(buffer, packet.getSoundEventName());
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, ServerboundRequestOnlineMusicPlaybackPacket packet) {
        packet.setRequestID(buffer.readIntLE());
        packet.setSoundEventName(helper.readString(buffer));
    }
}