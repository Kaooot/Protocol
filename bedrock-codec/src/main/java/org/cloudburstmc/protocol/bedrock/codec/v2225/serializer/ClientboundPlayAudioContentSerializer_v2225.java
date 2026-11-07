package org.cloudburstmc.protocol.bedrock.codec.v2225.serializer;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.BedrockPacketSerializer;
import org.cloudburstmc.protocol.bedrock.data.sound.audio.AudioContentPlaybackType;
import org.cloudburstmc.protocol.bedrock.packet.ClientboundPlayAudioContentPacket;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ClientboundPlayAudioContentSerializer_v2225 implements BedrockPacketSerializer<ClientboundPlayAudioContentPacket> {
    public static final ClientboundPlayAudioContentSerializer_v2225 INSTANCE = new ClientboundPlayAudioContentSerializer_v2225();

    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, ClientboundPlayAudioContentPacket packet) {
        helper.writeSignedAudioContent(buffer, packet.getSharedMetadata());
        helper.writeSignedAudioContent(buffer, packet.getPlaybackContent());
        buffer.writeByte(packet.getPlaybackType().ordinal());
        helper.writePlaySoundPacketPayload(buffer, packet.getPlaySound());
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, ClientboundPlayAudioContentPacket packet) {
        packet.setSharedMetadata(helper.readSignedAudioContent(buffer));
        packet.setPlaybackContent(helper.readSignedAudioContent(buffer));
        packet.setPlaybackType(AudioContentPlaybackType.from(buffer.readUnsignedByte()));
        packet.setPlaySound(helper.readPlaySoundPacketPayload(buffer));
    }
}