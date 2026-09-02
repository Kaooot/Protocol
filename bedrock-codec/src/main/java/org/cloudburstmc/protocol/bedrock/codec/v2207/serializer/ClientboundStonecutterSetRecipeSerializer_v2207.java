package org.cloudburstmc.protocol.bedrock.codec.v2207.serializer;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.BedrockPacketSerializer;
import org.cloudburstmc.protocol.bedrock.packet.ClientboundStonecutterSetRecipePacket;
import org.cloudburstmc.protocol.common.util.VarInts;

/**
 * @author Kaooot
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ClientboundStonecutterSetRecipeSerializer_v2207 implements BedrockPacketSerializer<ClientboundStonecutterSetRecipePacket> {
    public static final ClientboundStonecutterSetRecipeSerializer_v2207 INSTANCE = new ClientboundStonecutterSetRecipeSerializer_v2207();

    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, ClientboundStonecutterSetRecipePacket packet) {
        VarInts.writeLong(buffer, packet.getPlayerId());
        buffer.writeByte(packet.getContainerId());
        VarInts.writeInt(buffer, packet.getRecipeIndex());
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, ClientboundStonecutterSetRecipePacket packet) {
        packet.setPlayerId(VarInts.readLong(buffer));
        packet.setContainerId(buffer.readUnsignedByte());
        packet.setRecipeIndex(VarInts.readInt(buffer));
    }
}
