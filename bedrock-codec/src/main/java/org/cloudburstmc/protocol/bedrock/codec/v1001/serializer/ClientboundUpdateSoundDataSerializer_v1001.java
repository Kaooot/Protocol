package org.cloudburstmc.protocol.bedrock.codec.v1001.serializer;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.BedrockPacketSerializer;
import org.cloudburstmc.protocol.bedrock.data.sound.SoundDataEvent;
import org.cloudburstmc.protocol.bedrock.packet.ClientboundUpdateSoundDataPacket;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ClientboundUpdateSoundDataSerializer_v1001 implements BedrockPacketSerializer<ClientboundUpdateSoundDataPacket> {

    public static final ClientboundUpdateSoundDataSerializer_v1001 INSTANCE = new ClientboundUpdateSoundDataSerializer_v1001();

    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, ClientboundUpdateSoundDataPacket packet) {
        helper.writeServerSoundHandle(buffer, packet.getServerSoundHandle());
        buffer.writeIntLE(packet.getSoundEvent().ordinal());
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, ClientboundUpdateSoundDataPacket packet) {
        packet.setServerSoundHandle(helper.readServerSoundHandle(buffer));
        packet.setSoundEvent(SoundDataEvent.from(buffer.readIntLE()));
    }
}
