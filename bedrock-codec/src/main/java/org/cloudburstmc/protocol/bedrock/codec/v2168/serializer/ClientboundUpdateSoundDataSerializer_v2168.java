package org.cloudburstmc.protocol.bedrock.codec.v2168.serializer;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.VariantCodec;
import org.cloudburstmc.protocol.bedrock.codec.v1001.serializer.ClientboundUpdateSoundDataSerializer_v1001;
import org.cloudburstmc.protocol.bedrock.data.sound.*;
import org.cloudburstmc.protocol.bedrock.packet.ClientboundUpdateSoundDataPacket;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ClientboundUpdateSoundDataSerializer_v2168 extends ClientboundUpdateSoundDataSerializer_v1001 {

    public static final ClientboundUpdateSoundDataSerializer_v2168 INSTANCE = new ClientboundUpdateSoundDataSerializer_v2168();

    protected final VariantCodec<ClientboundUpdateSoundDataPacket> soundDataVariant = VariantCodec.<SoundDataEvent, ClientboundUpdateSoundDataPacket>builder(SoundDataEvent::ordinal)
            .add(
                    SoundDataEvent.STOP,
                    Stop.class
            )
            .add(
                    SoundDataEvent.SET_VOLUME,
                    SetVolume.class,
                    this::writeSetVolume,
                    this::readSetVolume
            )
            .add(
                    SoundDataEvent.SET_PITCH,
                    SetPitch.class,
                    this::writeSetPitch,
                    this::readSetPitch
            )
            .add(
                    SoundDataEvent.FADE,
                    Fade.class,
                    this::writeFade,
                    this::readFade
            )
            .add(
                    SoundDataEvent.SEEK_TO,
                    SeekTo.class,
                    this::writeSeekTo,
                    this::readSeekTo
            )
            .add(
                    SoundDataEvent.PAUSE,
                    Pause.class
            )
            .add(
                    SoundDataEvent.RESUME,
                    Resume.class
            )
            .build();

    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, ClientboundUpdateSoundDataPacket packet) {
        helper.writeServerSoundHandle(buffer, packet.getServerSoundHandle());
        this.soundDataVariant.write(buffer, helper, packet, packet.getStop());
        this.soundDataVariant.write(buffer, helper, packet, packet.getSetVolume());
        this.soundDataVariant.write(buffer, helper, packet, packet.getSetPitch());
        this.soundDataVariant.write(buffer, helper, packet, packet.getFade());
        this.soundDataVariant.write(buffer, helper, packet, packet.getSeekTo());
        this.soundDataVariant.write(buffer, helper, packet, packet.getPause());
        this.soundDataVariant.write(buffer, helper, packet, packet.getResume());
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, ClientboundUpdateSoundDataPacket packet) {
        packet.setServerSoundHandle(helper.readServerSoundHandle(buffer));
        packet.setStop(this.soundDataVariant.read(buffer, helper, packet));
        packet.setSetVolume(this.soundDataVariant.read(buffer, helper, packet));
        packet.setSetPitch(this.soundDataVariant.read(buffer, helper, packet));
        packet.setFade(this.soundDataVariant.read(buffer, helper, packet));
        packet.setSeekTo(this.soundDataVariant.read(buffer, helper, packet));
        packet.setPause(this.soundDataVariant.read(buffer, helper, packet));
        packet.setResume(this.soundDataVariant.read(buffer, helper, packet));
        packet.setStop(this.soundDataVariant.read(buffer, helper, packet));
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