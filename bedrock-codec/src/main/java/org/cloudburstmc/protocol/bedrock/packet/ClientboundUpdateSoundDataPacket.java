package org.cloudburstmc.protocol.bedrock.packet;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.cloudburstmc.protocol.bedrock.data.sound.ServerSoundHandle;
import org.cloudburstmc.protocol.bedrock.data.sound.SoundDataEvent;
import org.cloudburstmc.protocol.common.PacketSignal;

@Data
@EqualsAndHashCode(doNotUseGetters = true)
@ToString(doNotUseGetters = true)
public class ClientboundUpdateSoundDataPacket implements BedrockPacket {

    private ServerSoundHandle serverSoundHandle;
    /**
     * @deprecated since v2168
     */
    private SoundDataEvent soundEvent;
    /**
     * @since v2168
     */
    private Object stop;
    /**
     * @since v2168
     */
    private Object setVolume;
    /**
     * @since v2168
     */
    private Object setPitch;
    /**
     * @since v2168
     */
    private Object fade;
    /**
     * @since v2168
     */
    private Object seekTo;
    /**
     * @since v2168
     */
    private Object pause;
    /**
     * @since v2168
     */
    private Object resume;

    @Override
    public final PacketSignal handle(BedrockPacketHandler handler) {
        return handler.handle(this);
    }

    @Override
    public BedrockPacketType getPacketType() {
        return BedrockPacketType.CLIENTBOUND_UPDATE_SOUND_DATA;
    }

    @Override
    public ClientboundUpdateSoundDataPacket clone() {
        try {
            return (ClientboundUpdateSoundDataPacket) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
    }
}