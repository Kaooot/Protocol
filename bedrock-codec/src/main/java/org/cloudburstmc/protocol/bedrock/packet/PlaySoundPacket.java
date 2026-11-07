package org.cloudburstmc.protocol.bedrock.packet;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.cloudburstmc.protocol.bedrock.data.sound.PlaySoundPacketPayload;
import org.cloudburstmc.protocol.common.PacketSignal;

@Data
@EqualsAndHashCode(doNotUseGetters = true)
@ToString(doNotUseGetters = true)
public class PlaySoundPacket implements BedrockPacket {

    private PlaySoundPacketPayload payload;

    @Override
    public final PacketSignal handle(BedrockPacketHandler handler) {
        return handler.handle(this);
    }

    @Override
    public BedrockPacketType getPacketType() {
        return BedrockPacketType.PLAY_SOUND;
    }

    @Override
    public PlaySoundPacket clone() {
        try {
            return (PlaySoundPacket) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
    }
}