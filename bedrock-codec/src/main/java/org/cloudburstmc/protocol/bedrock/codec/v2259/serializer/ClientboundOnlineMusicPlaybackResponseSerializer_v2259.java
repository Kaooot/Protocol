package org.cloudburstmc.protocol.bedrock.codec.v2259.serializer;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.BedrockPacketSerializer;
import org.cloudburstmc.protocol.bedrock.data.sound.music.MusicPlaybackState;
import org.cloudburstmc.protocol.bedrock.packet.ClientboundOnlineMusicPlaybackResponsePacket;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ClientboundOnlineMusicPlaybackResponseSerializer_v2259 implements BedrockPacketSerializer<ClientboundOnlineMusicPlaybackResponsePacket> {
    public static final ClientboundOnlineMusicPlaybackResponseSerializer_v2259 INSTANCE = new ClientboundOnlineMusicPlaybackResponseSerializer_v2259();

    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, ClientboundOnlineMusicPlaybackResponsePacket packet) {
        buffer.writeIntLE(packet.getRequestID());
        helper.writeString(buffer, packet.getState().getSerializeName());
        helper.writeSignedAudioContent(buffer, packet.getSharedMetadata());
        helper.writeSignedAudioContent(buffer, packet.getPlaybackContent());
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, ClientboundOnlineMusicPlaybackResponsePacket packet) {
        packet.setRequestID(buffer.readIntLE());
        packet.setState(MusicPlaybackState.fromName(helper.readString(buffer)));
        packet.setSharedMetadata(helper.readSignedAudioContent(buffer));
        packet.setPlaybackContent(helper.readSignedAudioContent(buffer));
    }
}