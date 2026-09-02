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
        this.writeSoundData(buffer, helper, packet.getStop());
        this.writeSoundData(buffer, helper, packet.getSetVolume());
        this.writeSoundData(buffer, helper, packet.getSetPitch());
        this.writeSoundData(buffer, helper, packet.getFade());
        this.writeSoundData(buffer, helper, packet.getSeekTo());
        this.writeSoundData(buffer, helper, packet.getPause());
        this.writeSoundData(buffer, helper, packet.getResume());
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, ClientboundUpdateSoundDataPacket packet) {
        packet.setServerSoundHandle(helper.readServerSoundHandle(buffer));
        packet.setStop(this.readSoundData(buffer, helper));
        packet.setSetVolume(this.readSoundData(buffer, helper));
        packet.setSetPitch(this.readSoundData(buffer, helper));
        packet.setFade(this.readSoundData(buffer, helper));
        packet.setSeekTo(this.readSoundData(buffer, helper));
        packet.setPause(this.readSoundData(buffer, helper));
        packet.setResume(this.readSoundData(buffer, helper));
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