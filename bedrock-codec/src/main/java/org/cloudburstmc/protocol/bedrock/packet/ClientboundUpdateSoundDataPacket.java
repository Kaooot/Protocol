package org.cloudburstmc.protocol.bedrock.packet;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.cloudburstmc.protocol.bedrock.data.ServerSoundHandle;
import org.cloudburstmc.protocol.bedrock.data.payload.sound.SoundData;
import org.cloudburstmc.protocol.bedrock.data.payload.sound.SoundDataEvent;
import org.cloudburstmc.protocol.common.PacketSignal;

/**
 * @author Kaooot
 */
@Data
@EqualsAndHashCode(doNotUseGetters = true)
@ToString(doNotUseGetters = true)
public class ClientboundUpdateSoundDataPacket implements BedrockPacket {

    private ServerSoundHandle serverSoundHandle;
    /**
     * @deprecated since v2164
     */
    private SoundDataEvent soundEvent;
    /**
     * @since v2164
     */
    private SoundData stop;
    /**
     * @since v2164
     */
    private SoundData setVolume;
    /**
     * @since v2164
     */
    private SoundData setPitch;
    /**
     * @since v2164
     */
    private SoundData fade;
    /**
     * @since v2164
     */
    private SoundData seekTo;
    /**
     * @since v2164
     */
    private SoundData pause;
    /**
     * @since v2164
     */
    private SoundData resume;

    @Override
    public PacketSignal handle(BedrockPacketHandler handler) {
        return handler.handle(this);
    }

    @Override
    public BedrockPacketType getPacketType() {
        return BedrockPacketType.CLIENTBOUND_UPDATE_SOUND_DATA;
    }

    @Override
    public BedrockPacket clone() {
        try {
            return (ClientboundUpdateSoundDataPacket) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
    }
}