package org.cloudburstmc.protocol.bedrock.packet;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.cloudburstmc.protocol.common.PacketSignal;

@Data
@EqualsAndHashCode(doNotUseGetters = true)
@ToString(doNotUseGetters = true)
public class ServerboundRequestOnlineMusicPlaybackPacket implements BedrockPacket {

    private int requestID;
    private String soundEventName;

    @Override
    public final PacketSignal handle(BedrockPacketHandler handler) {
        return handler.handle(this);
    }

    @Override
    public BedrockPacketType getPacketType() {
        return BedrockPacketType.SERVERBOUND_REQUEST_ONLINE_MUSIC_PLAYBACK;
    }

    @Override
    public ServerboundRequestOnlineMusicPlaybackPacket clone() {
        try {
            return (ServerboundRequestOnlineMusicPlaybackPacket) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
    }
}