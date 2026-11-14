package org.cloudburstmc.protocol.bedrock.packet;

import java.lang.AssertionError;
import java.lang.CloneNotSupportedException;
import java.lang.Override;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.cloudburstmc.protocol.bedrock.data.sound.music.MusicPlaybackState;
import org.cloudburstmc.protocol.bedrock.data.sound.audio.SignedAudioContent;
import org.cloudburstmc.protocol.common.PacketSignal;

@Data
@EqualsAndHashCode(doNotUseGetters = true)
@ToString(doNotUseGetters = true)
public class ClientboundOnlineMusicPlaybackResponsePacket implements BedrockPacket {

    private int requestID;
    private MusicPlaybackState state;
    private SignedAudioContent sharedMetadata;
    private SignedAudioContent playbackContent;

    @Override
    public final PacketSignal handle(BedrockPacketHandler handler) {
        return handler.handle(this);
    }

    @Override
    public BedrockPacketType getPacketType() {
        return BedrockPacketType.CLIENTBOUND_ONLINE_MUSIC_PLAYBACK_RESPONSE;
    }

    @Override
    public ClientboundOnlineMusicPlaybackResponsePacket clone() {
        try {
            return (ClientboundOnlineMusicPlaybackResponsePacket) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
    }
}