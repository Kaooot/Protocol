package org.cloudburstmc.protocol.bedrock.codec.v2168.serializer;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.v1001.serializer.ClientboundUpdateSoundDataSerializer_v1001;
import org.cloudburstmc.protocol.bedrock.data.sound.*;
import org.cloudburstmc.protocol.bedrock.packet.ClientboundUpdateSoundDataPacket;
import org.cloudburstmc.protocol.common.util.VarInts;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ClientboundUpdateSoundDataSerializer_v2168 extends ClientboundUpdateSoundDataSerializer_v1001 {

    public static final ClientboundUpdateSoundDataSerializer_v2168 INSTANCE = new ClientboundUpdateSoundDataSerializer_v2168();

    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, ClientboundUpdateSoundDataPacket packet) {
        helper.writeServerSoundHandle(buffer, packet.getServerSoundHandle());
        this.writeSoundData(buffer, helper, packet, packet.getStop());
        this.writeSoundData(buffer, helper, packet, packet.getSetVolume());
        this.writeSoundData(buffer, helper, packet, packet.getSetPitch());
        this.writeSoundData(buffer, helper, packet, packet.getFade());
        this.writeSoundData(buffer, helper, packet, packet.getSeekTo());
        this.writeSoundData(buffer, helper, packet, packet.getPause());
        this.writeSoundData(buffer, helper, packet, packet.getResume());
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, ClientboundUpdateSoundDataPacket packet) {
        packet.setServerSoundHandle(helper.readServerSoundHandle(buffer));
        packet.setStop(this.readSoundData(buffer, helper, packet));
        packet.setSetVolume(this.readSoundData(buffer, helper, packet));
        packet.setSetPitch(this.readSoundData(buffer, helper, packet));
        packet.setFade(this.readSoundData(buffer, helper, packet));
        packet.setSeekTo(this.readSoundData(buffer, helper, packet));
        packet.setPause(this.readSoundData(buffer, helper, packet));
        packet.setResume(this.readSoundData(buffer, helper, packet));
        packet.setStop(this.readSoundData(buffer, helper, packet));
    }

    protected void writeSoundData(ByteBuf buffer, BedrockCodecHelper helper, ClientboundUpdateSoundDataPacket packet, Object event) {
        final SoundDataEventType type;
        if (event instanceof Stop) {
            type = SoundDataEventType.STOP;
        } else if (event instanceof SetVolume) {
            type = SoundDataEventType.SET_VOLUME;
        } else if (event instanceof SetPitch) {
            type = SoundDataEventType.SET_PITCH;
        } else if (event instanceof Fade) {
            type = SoundDataEventType.FADE;
        } else if (event instanceof SeekTo) {
            type = SoundDataEventType.SEEK_TO;
        } else if (event instanceof Pause) {
            type = SoundDataEventType.PAUSE;
        } else if (event instanceof Resume) {
            type = SoundDataEventType.RESUME;
        } else {
            return;
        }
        VarInts.writeUnsignedInt(buffer, type.ordinal());
        buffer.writeByte(type.ordinal());
        switch (type) {
            case SET_VOLUME:
                this.writeSetVolume(buffer, helper, packet, (SetVolume) event);
                break;
            case SET_PITCH:
                this.writeSetPitch(buffer, helper, packet, (SetPitch) event);
                break;
            case FADE:
                this.writeFade(buffer, helper, packet, (Fade) event);
                break;
            case SEEK_TO:
                this.writeSeekTo(buffer, helper, packet, (SeekTo) event);
                break;
            case STOP:
            case PAUSE:
            case RESUME:
                break;
        }
    }

    protected Object readSoundData(ByteBuf buffer, BedrockCodecHelper helper, ClientboundUpdateSoundDataPacket packet) {
        final SoundDataEventType type = SoundDataEventType.from(VarInts.readUnsignedInt(buffer));
        buffer.readUnsignedByte();
        switch (type) {
            case STOP:
                return new Stop();
            case SET_VOLUME:
                return this.readSetVolume(buffer, helper, packet);
            case SET_PITCH:
                return this.readSetPitch(buffer, helper, packet);
            case FADE:
                return this.readFade(buffer, helper, packet);
            case SEEK_TO:
                return this.readSeekTo(buffer, helper, packet);
            case PAUSE:
                return new Pause();
            case RESUME:
                return new Resume();
        }
        return null;
    }

    protected void writeSetVolume(ByteBuf buffer, BedrockCodecHelper helper, ClientboundUpdateSoundDataPacket packet, SetVolume setVolume) {
        buffer.writeFloatLE(setVolume.getVolume());
    }

    protected SetVolume readSetVolume(ByteBuf buffer, BedrockCodecHelper helper, ClientboundUpdateSoundDataPacket packet) {
        final SetVolume setVolume = new SetVolume();
        setVolume.setVolume(buffer.readFloatLE());
        return setVolume;
    }

    protected void writeSetPitch(ByteBuf buffer, BedrockCodecHelper helper, ClientboundUpdateSoundDataPacket packet, SetPitch setPitch) {
        buffer.writeFloatLE(setPitch.getPitch());
    }

    protected SetPitch readSetPitch(ByteBuf buffer, BedrockCodecHelper helper, ClientboundUpdateSoundDataPacket packet) {
        final SetPitch setPitch = new SetPitch();
        setPitch.setPitch(buffer.readFloatLE());
        return setPitch;
    }

    protected void writeFade(ByteBuf buffer, BedrockCodecHelper helper, ClientboundUpdateSoundDataPacket packet, Fade fade) {
        buffer.writeFloatLE(fade.getDuration());
        buffer.writeFloatLE(fade.getTargetVolume());
    }

    protected Fade readFade(ByteBuf buffer, BedrockCodecHelper helper, ClientboundUpdateSoundDataPacket packet) {
        final Fade fade = new Fade();
        fade.setDuration(buffer.readFloatLE());
        fade.setTargetVolume(buffer.readFloatLE());
        return fade;
    }

    protected void writeSeekTo(ByteBuf buffer, BedrockCodecHelper helper, ClientboundUpdateSoundDataPacket packet, SeekTo seekTo) {
        buffer.writeFloatLE(seekTo.getSeconds());
    }

    protected SeekTo readSeekTo(ByteBuf buffer, BedrockCodecHelper helper, ClientboundUpdateSoundDataPacket packet) {
        final SeekTo seekTo = new SeekTo();
        seekTo.setSeconds(buffer.readFloatLE());
        return seekTo;
    }
}