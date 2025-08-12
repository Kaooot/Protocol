package org.cloudburstmc.protocol.bedrock.codec.v589.serializer;

import io.netty.buffer.ByteBuf;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.v575.serializer.UnlockedRecipesSerializer_v575;
import org.cloudburstmc.protocol.bedrock.packet.UnlockedRecipesPacket;
import org.cloudburstmc.protocol.bedrock.packet.UnlockedRecipesPacket.UnlockedRecipesPacketType;

public class UnlockedRecipesSerializer_v589 extends UnlockedRecipesSerializer_v575 {

    protected static final UnlockedRecipesPacketType[] ACTIONS = UnlockedRecipesPacketType.values();

    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, UnlockedRecipesPacket packet) {
        buffer.writeIntLE(packet.getType().ordinal());
        helper.writeArray(buffer, packet.getUnlockedRecipesList(), helper::writeString);
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, UnlockedRecipesPacket packet) {
        packet.setType(ACTIONS[buffer.readIntLE()]);
        helper.readArray(buffer, packet.getUnlockedRecipesList(), helper::readString);
    }
}
