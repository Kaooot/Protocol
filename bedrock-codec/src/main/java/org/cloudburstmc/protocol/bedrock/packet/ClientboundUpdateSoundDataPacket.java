package org.cloudburstmc.protocol.bedrock.packet;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.cloudburstmc.protocol.bedrock.annotation.Added;
import org.cloudburstmc.protocol.bedrock.annotation.Removed;
import org.cloudburstmc.protocol.bedrock.data.sound.ServerSoundHandle;
import org.cloudburstmc.protocol.bedrock.data.sound.SoundDataEvent;
import org.cloudburstmc.protocol.bedrock.data.sound.SoundDataEventType;
import org.cloudburstmc.protocol.common.PacketSignal;

@Data
@EqualsAndHashCode(doNotUseGetters = true)
@ToString(doNotUseGetters = true)
public class ClientboundUpdateSoundDataPacket implements BedrockPacket {

    private ServerSoundHandle serverSoundHandle;
    @Deprecated
    @Removed("26.40")
    private SoundDataEventType soundEvent;
    @Added("26.40")
    @Deprecated
    @Removed("26.60")
    private SoundDataEvent stop;
    @Added("26.40")
    @Deprecated
    @Removed("26.60")
    private SoundDataEvent setVolume;
    @Added("26.40")
    @Deprecated
    @Removed("26.60")
    private SoundDataEvent setPitch;
    @Added("26.40")
    @Deprecated
    @Removed("26.60")
    private SoundDataEvent fade;
    @Added("26.40")
    @Deprecated
    @Removed("26.60")
    private SoundDataEvent seekTo;
    @Added("26.40")
    @Deprecated
    @Removed("26.60")
    private SoundDataEvent pause;
    @Added("26.40")
    @Deprecated
    @Removed("26.60")
    private SoundDataEvent resume;
    @Added("26.60")
    private SoundDataEvent event;

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