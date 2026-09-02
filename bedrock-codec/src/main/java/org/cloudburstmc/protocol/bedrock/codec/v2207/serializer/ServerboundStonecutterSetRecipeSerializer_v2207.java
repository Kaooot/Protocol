package org.cloudburstmc.protocol.bedrock.codec.v2207.serializer;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.BedrockPacketSerializer;
import org.cloudburstmc.protocol.bedrock.packet.ServerboundStonecutterSetRecipePacket;
import org.cloudburstmc.protocol.common.util.VarInts;

/**
 * @author Kaooot
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ServerboundStonecutterSetRecipeSerializer_v2207 implements BedrockPacketSerializer<ServerboundStonecutterSetRecipePacket> {
    public static final ServerboundStonecutterSetRecipeSerializer_v2207 INSTANCE = new ServerboundStonecutterSetRecipeSerializer_v2207();

    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, ServerboundStonecutterSetRecipePacket packet) {
        buffer.writeByte(packet.getContainerId());
        VarInts.writeInt(buffer, packet.getRecipeIndex());
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, ServerboundStonecutterSetRecipePacket packet) {
        packet.setContainerId(buffer.readUnsignedByte());
        packet.setRecipeIndex(VarInts.readInt(buffer));
    }
}
