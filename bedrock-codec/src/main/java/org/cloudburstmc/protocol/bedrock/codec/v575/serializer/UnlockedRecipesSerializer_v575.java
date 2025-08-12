package org.cloudburstmc.protocol.bedrock.codec.v575.serializer;

import io.netty.buffer.ByteBuf;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.BedrockPacketSerializer;
import org.cloudburstmc.protocol.bedrock.packet.UnlockedRecipesPacket;
import org.cloudburstmc.protocol.bedrock.packet.UnlockedRecipesPacket.UnlockedRecipesPacketType;

public class UnlockedRecipesSerializer_v575 implements BedrockPacketSerializer<UnlockedRecipesPacket> {

    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, UnlockedRecipesPacket packet) {
        buffer.writeBoolean(packet.getType() == UnlockedRecipesPacketType.NEWLY_UNLOCKED);
        helper.writeArray(buffer, packet.getUnlockedRecipesList(), helper::writeString);
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, UnlockedRecipesPacket packet) {
        packet.setType(buffer.readBoolean() ? UnlockedRecipesPacketType.NEWLY_UNLOCKED : UnlockedRecipesPacketType.INITIALLY_UNLOCKED);
        helper.readArray(buffer, packet.getUnlockedRecipesList(), helper::readString);
    }
}
