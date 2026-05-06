package org.cloudburstmc.protocol.bedrock.codec.v986.serializer;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.BedrockPacketSerializer;
import org.cloudburstmc.protocol.bedrock.data.payload.sound.SoundDataEvent;
import org.cloudburstmc.protocol.bedrock.packet.ClientboundUpdateSoundDataPacket;

/**
 * @author Kaooot
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ClientboundUpdateSoundSerializer_v986 implements BedrockPacketSerializer<ClientboundUpdateSoundDataPacket> {
    public static final ClientboundUpdateSoundSerializer_v986 INSTANCE = new ClientboundUpdateSoundSerializer_v986();

    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, ClientboundUpdateSoundDataPacket packet) {
        helper.writeServerSoundHandle(buffer, packet.getServerSoundHandle());
        helper.writeString(buffer, packet.getSoundEvent().name());
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, ClientboundUpdateSoundDataPacket packet) {
        packet.setServerSoundHandle(helper.readServerSoundHandle(buffer));
        packet.setSoundEvent(SoundDataEvent.valueOf(helper.readString(buffer).toUpperCase()));
    }
}