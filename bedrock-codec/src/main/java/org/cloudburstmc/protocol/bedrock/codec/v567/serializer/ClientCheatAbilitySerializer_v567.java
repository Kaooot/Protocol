package org.cloudburstmc.protocol.bedrock.codec.v567.serializer;

import io.netty.buffer.ByteBuf;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.BedrockPacketSerializer;
import org.cloudburstmc.protocol.bedrock.packet.ClientCheatAbilityPacket;

public class ClientCheatAbilitySerializer_v567 implements BedrockPacketSerializer<ClientCheatAbilityPacket> {

    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, ClientCheatAbilityPacket packet) {
        helper.writeSerializedAbilitiesData(buffer, packet.getData());
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, ClientCheatAbilityPacket packet) {
        helper.readSerializedAbilitiesData(buffer, packet.getData());
    }
}
