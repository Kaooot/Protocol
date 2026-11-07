package org.cloudburstmc.protocol.bedrock.packet;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.cloudburstmc.protocol.bedrock.data.sound.audio.AudioContentRegistrationEntry;
import org.cloudburstmc.protocol.common.PacketSignal;

import java.util.List;

@Data
@EqualsAndHashCode(doNotUseGetters = true)
@ToString(doNotUseGetters = true)
public class ServerboundRegisterAudioContentPacket implements BedrockPacket {

    private final List<AudioContentRegistrationEntry> registrations = new ObjectArrayList<>();

    @Override
    public final PacketSignal handle(BedrockPacketHandler handler) {
        return handler.handle(this);
    }

    @Override
    public BedrockPacketType getPacketType() {
        return BedrockPacketType.SERVERBOUND_REGISTER_AUDIO_CONTENT;
    }

    @Override
    public ServerboundRegisterAudioContentPacket clone() {
        try {
            return (ServerboundRegisterAudioContentPacket) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
    }
}