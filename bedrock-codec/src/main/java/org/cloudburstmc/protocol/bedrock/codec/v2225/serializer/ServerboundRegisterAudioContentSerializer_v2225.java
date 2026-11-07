package org.cloudburstmc.protocol.bedrock.codec.v2225.serializer;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.BedrockPacketSerializer;
import org.cloudburstmc.protocol.bedrock.data.sound.audio.AudioContentRegistrationEntry;
import org.cloudburstmc.protocol.bedrock.packet.ServerboundRegisterAudioContentPacket;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ServerboundRegisterAudioContentSerializer_v2225 implements BedrockPacketSerializer<ServerboundRegisterAudioContentPacket> {
    public static final ServerboundRegisterAudioContentSerializer_v2225 INSTANCE = new ServerboundRegisterAudioContentSerializer_v2225();

    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, ServerboundRegisterAudioContentPacket packet) {
        helper.writeArray(buffer, packet.getRegistrations(), this::writeAudioContentRegistrationEntry);
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, ServerboundRegisterAudioContentPacket packet) {
        helper.readArray(buffer, packet.getRegistrations(), this::readAudioContentRegistrationEntry);
    }

    protected void writeAudioContentRegistrationEntry(ByteBuf buffer, BedrockCodecHelper helper, AudioContentRegistrationEntry entry) {
        helper.writeString(buffer, entry.getAudioContentID());
        helper.writeSignedAudioContent(buffer, entry.getSharedMetadata());
        helper.writeSignedAudioContent(buffer, entry.getServerContent());
        helper.writeSignedAudioContent(buffer, entry.getPlaybackContent());
    }

    protected AudioContentRegistrationEntry readAudioContentRegistrationEntry(ByteBuf buffer, BedrockCodecHelper helper){
        final AudioContentRegistrationEntry entry = new AudioContentRegistrationEntry();
        entry.setAudioContentID(helper.readStringMaxLen(buffer, 36));
        entry.setSharedMetadata(helper.readSignedAudioContent(buffer));
        entry.setServerContent(helper.readSignedAudioContent(buffer));
        entry.setPlaybackContent(helper.readSignedAudioContent(buffer));
        return entry;
    }
}