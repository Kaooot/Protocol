package org.cloudburstmc.protocol.bedrock.codec.v2259.serializer;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.BedrockPacketSerializer;
import org.cloudburstmc.protocol.bedrock.data.sound.music.MusicPlaybackFailureEntry;
import org.cloudburstmc.protocol.bedrock.data.sound.music.MusicPlaybackState;
import org.cloudburstmc.protocol.bedrock.packet.ClientboundMusicPlaybackFailurePacket;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ClientboundMusicPlaybackFailureSerializer_v2259 implements BedrockPacketSerializer<ClientboundMusicPlaybackFailurePacket> {
    public static final ClientboundMusicPlaybackFailureSerializer_v2259 INSTANCE = new ClientboundMusicPlaybackFailureSerializer_v2259();

    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, ClientboundMusicPlaybackFailurePacket packet) {
        helper.writeArray(buffer, packet.getFailures(), this::writeMusicPlaybackFailureEntry);
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, ClientboundMusicPlaybackFailurePacket packet) {
        helper.readArray(buffer, packet.getFailures(), this::readMusicPlaybackFailureEntry);
    }

    protected void writeMusicPlaybackFailureEntry(ByteBuf buffer, BedrockCodecHelper helper, MusicPlaybackFailureEntry entry) {
        helper.writeString(buffer, entry.getState().getSerializeName());
        helper.writeString(buffer, entry.getDescription());
    }

    protected MusicPlaybackFailureEntry readMusicPlaybackFailureEntry(ByteBuf buffer, BedrockCodecHelper helper) {
        final MusicPlaybackFailureEntry entry = new MusicPlaybackFailureEntry();
        entry.setState(MusicPlaybackState.fromName(helper.readString(buffer)));
        entry.setDescription(helper.readString(buffer));
        return entry;
    }
}