package org.cloudburstmc.protocol.bedrock.codec.v800.serializer;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.BedrockPacketSerializer;
import org.cloudburstmc.protocol.bedrock.data.player.ControlScheme;
import org.cloudburstmc.protocol.bedrock.packet.ClientboundControlSchemeSetPacket;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ClientboundControlSchemeSetSerializer_v800 implements BedrockPacketSerializer<ClientboundControlSchemeSetPacket> {

    public static final ClientboundControlSchemeSetSerializer_v800 INSTANCE = new ClientboundControlSchemeSetSerializer_v800();

    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, ClientboundControlSchemeSetPacket packet) {
        buffer.writeByte(packet.getControlScheme().ordinal());
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, ClientboundControlSchemeSetPacket packet) {
        packet.setControlScheme(ControlScheme.from(buffer.readUnsignedByte()));
    }
}