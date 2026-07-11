package org.cloudburstmc.protocol.bedrock.codec.v2168.serializer;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.v1001.serializer.ClientboundUpdateSoundSerializer_v1001;
import org.cloudburstmc.protocol.bedrock.data.payload.sound.*;
import org.cloudburstmc.protocol.bedrock.packet.ClientboundUpdateSoundDataPacket;
import org.cloudburstmc.protocol.common.util.VarInts;

/**
 * @author Kaooot
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ClientboundUpdateSoundDataSerializer_v2168 extends ClientboundUpdateSoundSerializer_v1001 {
    public static final ClientboundUpdateSoundDataSerializer_v2168 INSTANCE = new ClientboundUpdateSoundDataSerializer_v2168();

    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, ClientboundUpdateSoundDataPacket packet) {
        helper.writeServerSoundHandle(buffer, packet.getServerSoundHandle());
        helper.writeOptionalNull(buffer, packet.getStop(), this::writeSoundData);
        helper.writeOptionalNull(buffer, packet.getSetVolume(), this::writeSoundData);
        helper.writeOptionalNull(buffer, packet.getSetPitch(), this::writeSoundData);
        helper.writeOptionalNull(buffer, packet.getFade(), this::writeSoundData);
        helper.writeOptionalNull(buffer, packet.getSeekTo(), this::writeSoundData);
        helper.writeOptionalNull(buffer, packet.getPause(), this::writeSoundData);
        helper.writeOptionalNull(buffer, packet.getResume(), this::writeSoundData);
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, ClientboundUpdateSoundDataPacket packet) {
        packet.setServerSoundHandle(helper.readServerSoundHandle(buffer));
        packet.setStop(helper.readOptional(buffer, null, this::readSoundData));
        packet.setSetVolume(helper.readOptional(buffer, null, this::readSoundData));
        packet.setSetPitch(helper.readOptional(buffer, null, this::readSoundData));
        packet.setFade(helper.readOptional(buffer, null, this::readSoundData));
        packet.setSeekTo(helper.readOptional(buffer, null, this::readSoundData));
        packet.setPause(helper.readOptional(buffer, null, this::readSoundData));
        packet.setResume(helper.readOptional(buffer, null, this::readSoundData));
    }

    protected void writeSoundData(ByteBuf buffer, BedrockCodecHelper helper, SoundData soundData) {
        VarInts.writeUnsignedInt(buffer, soundData.getType().ordinal());
        switch (soundData.getType()) {
            case SET_VOLUME:
                buffer.writeFloatLE(((SetVolume) soundData).getVolume());
                break;
            case SET_PITCH:
                buffer.writeFloatLE(((SetPitch) soundData).getPitch());
                break;
            case FADE:
                final Fade fade = (Fade) soundData;
                buffer.writeFloatLE(fade.getDuration());
                buffer.writeFloatLE(fade.getTargetVolume());
                break;
            case SEEK_TO:
                buffer.writeFloatLE(((SeekTo) soundData).getSeconds());
                break;
        }
    }

    protected SoundData readSoundData(ByteBuf buffer, BedrockCodecHelper helper) {
        final SoundDataEvent event = SoundDataEvent.from(VarInts.readUnsignedInt(buffer));
        switch (event) {
            case STOP:
                return new Stop();
            case SET_VOLUME:
                final SetVolume setVolume = new SetVolume();
                setVolume.setVolume(buffer.readFloatLE());
                return setVolume;
            case SET_PITCH:
                final SetPitch setPitch = new SetPitch();
                setPitch.setPitch(buffer.readFloatLE());
                return setPitch;
            case FADE:
                final Fade fade = new Fade();
                fade.setDuration(buffer.readFloatLE());
                fade.setTargetVolume(buffer.readFloatLE());
                return fade;
            case SEEK_TO:
                final SeekTo seekTo = new SeekTo();
                seekTo.setSeconds(buffer.readFloatLE());
                return seekTo;
            case PAUSE:
                return new Pause();
            case RESUME:
                return new Resume();
            default:
                throw new IllegalArgumentException("invalid sound data for ClientboundUpdateSoundDataPacket");
        }
    }
}