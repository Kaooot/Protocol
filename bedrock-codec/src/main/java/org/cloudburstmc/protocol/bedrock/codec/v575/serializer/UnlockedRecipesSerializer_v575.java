package org.cloudburstmc.protocol.bedrock.codec.v575.serializer;

import io.netty.buffer.ByteBuf;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.BedrockPacketSerializer;
import org.cloudburstmc.protocol.bedrock.packet.UnlockedRecipesPacket;

public class UnlockedRecipesSerializer_v575 implements BedrockPacketSerializer<UnlockedRecipesPacket> {

    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, UnlockedRecipesPacket packet) {
        buffer.writeBoolean(packet.getType().equals( UnlockedRecipesPacket.PacketType.NEWLY_UNLOCKED_RECIPES));
        helper.writeArray(buffer, packet.getUnlockedRecipesList(), helper::writeString);
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, UnlockedRecipesPacket packet) {
        packet.setType(
                buffer.readBoolean() ?
                        UnlockedRecipesPacket.PacketType.NEWLY_UNLOCKED_RECIPES :
                        UnlockedRecipesPacket.PacketType.INITIALLY_UNLOCKED_RECIPES
        );
        helper.readArray(buffer, packet.getUnlockedRecipesList(), helper::readString);
    }
}