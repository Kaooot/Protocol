package org.cloudburstmc.protocol.bedrock.codec.v843.serializer;

import io.netty.buffer.ByteBuf;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.BedrockPacketSerializer;
import org.cloudburstmc.protocol.bedrock.data.GameRuleData;
import org.cloudburstmc.protocol.bedrock.packet.GameRulesChangedPacket;
import org.cloudburstmc.protocol.common.util.TypeMap;
import org.cloudburstmc.protocol.common.util.VarInts;

/**
 * @author Kaooot
 */
public class GameRulesChangedSerializer_v843 implements BedrockPacketSerializer<GameRulesChangedPacket> {
    protected final TypeMap<Class<?>> gameRuleType;

    public GameRulesChangedSerializer_v843(TypeMap<Class<?>> gameRuleType) {
        this.gameRuleType = gameRuleType;
    }

    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, GameRulesChangedPacket packet) {
        helper.writeArray(buffer, packet.getRulesData(), this::writeGameRule);
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, GameRulesChangedPacket packet) {
        helper.readArray(buffer, packet.getRulesData(), this::readGameRule);
    }

    protected void writeGameRule(ByteBuf buffer, BedrockCodecHelper helper, GameRuleData<?> gameRule) {
        final Object value = gameRule.getValue();
        final int id = this.gameRuleType.getId(value.getClass());

        helper.writeString(buffer, gameRule.getName());
        buffer.writeBoolean(gameRule.isEditable());
        VarInts.writeUnsignedInt(buffer, id);

        switch (id) {
            case 1:
                buffer.writeBoolean((boolean) value);
                break;
            case 2:
                buffer.writeIntLE((int) value);
                break;
            case 3:
                buffer.writeFloatLE((float) value);
                break;
        }
    }

    protected GameRuleData<?> readGameRule(ByteBuf buffer, BedrockCodecHelper helper) {
        final String name = helper.readString(buffer);
        final boolean editable = buffer.readBoolean();
        final int type = VarInts.readUnsignedInt(buffer);

        switch (type) {
            case 1:
                return new GameRuleData<>(name, editable, buffer.readBoolean());
            case 2:
                return new GameRuleData<>(name, editable, buffer.readIntLE());
            case 3:
                return new GameRuleData<>(name, editable, buffer.readFloatLE());
        }
        throw new IllegalStateException("Invalid gamerule type received");
    }
}