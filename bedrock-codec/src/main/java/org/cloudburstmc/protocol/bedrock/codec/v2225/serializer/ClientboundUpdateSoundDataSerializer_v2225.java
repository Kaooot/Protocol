package org.cloudburstmc.protocol.bedrock.codec.v2225.serializer;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.v2168.serializer.ClientboundUpdateSoundDataSerializer_v2168;
import org.cloudburstmc.protocol.bedrock.packet.ClientboundUpdateSoundDataPacket;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ClientboundUpdateSoundDataSerializer_v2225 extends ClientboundUpdateSoundDataSerializer_v2168 {
    public static final ClientboundUpdateSoundDataSerializer_v2225 INSTANCE = new ClientboundUpdateSoundDataSerializer_v2225();

    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, ClientboundUpdateSoundDataPacket packet) {
        helper.writeServerSoundHandle(buffer, packet.getServerSoundHandle());
        this.writeSoundData(buffer, helper, packet, packet.getEvent());
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, ClientboundUpdateSoundDataPacket packet) {
        packet.setServerSoundHandle(helper.readServerSoundHandle(buffer));
        packet.setEvent(this.readSoundData(buffer, helper, packet));
    }
}