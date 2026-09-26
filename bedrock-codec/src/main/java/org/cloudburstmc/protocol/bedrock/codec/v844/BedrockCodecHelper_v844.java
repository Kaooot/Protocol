package org.cloudburstmc.protocol.bedrock.codec.v844;

import io.netty.buffer.ByteBuf;
import org.cloudburstmc.protocol.bedrock.codec.ActorDataTypeMap;
import org.cloudburstmc.protocol.bedrock.codec.v776.BedrockCodecHelper_v776;
import org.cloudburstmc.protocol.bedrock.data.ability.AbilitiesIndex;
import org.cloudburstmc.protocol.bedrock.data.world.GameRule;
import org.cloudburstmc.protocol.bedrock.data.inventory.ContainerEnumName;
import org.cloudburstmc.protocol.bedrock.data.text.TextProcessingEventOrigin;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.ItemStackRequestActionType;
import org.cloudburstmc.protocol.common.util.Preconditions;
import org.cloudburstmc.protocol.common.util.TypeMap;
import org.cloudburstmc.protocol.common.util.VarInts;

public class BedrockCodecHelper_v844 extends BedrockCodecHelper_v776 {

    public BedrockCodecHelper_v844(ActorDataTypeMap entityData, TypeMap<Class<?>> gameRulesTypes, TypeMap<ItemStackRequestActionType> stackRequestActionTypes,
                                   TypeMap<ContainerEnumName> containerEnumNames, TypeMap<AbilitiesIndex> abilities, TypeMap<TextProcessingEventOrigin> textProcessingEventOrigins) {
        super(entityData, gameRulesTypes, stackRequestActionTypes, containerEnumNames, abilities, textProcessingEventOrigins);
    }

    @Override
    public void writeGameRule(ByteBuf buffer, GameRule gameRule) {
        Preconditions.checkNotNull(buffer, "buffer");
        Preconditions.checkNotNull(gameRule, "gameRule");

        Object value = gameRule.getRuleValue();
        int id = this.gameRuleType.getId(value.getClass());

        writeString(buffer, gameRule.getRuleName());
        buffer.writeBoolean(gameRule.isRuleCanBeModified());
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

    @Override
    public GameRule readGameRule(ByteBuf buffer) {
        Preconditions.checkNotNull(buffer, "buffer");

        String name = readString(buffer);
        boolean editable = buffer.readBoolean();
        int type = VarInts.readUnsignedInt(buffer);

        switch (type) {
            case 1:
                return new GameRule(name, editable, buffer.readBoolean());
            case 2:
                return new GameRule(name, editable, buffer.readIntLE());
            case 3:
                return new GameRule(name, editable, buffer.readFloatLE());
        }
        throw new IllegalStateException("Invalid gamerule type received");
    }

    @Override
    public void writeGameRuleInStartGame(ByteBuf buffer, GameRule gameRule) {
        Preconditions.checkNotNull(buffer, "buffer");
        Preconditions.checkNotNull(gameRule, "gameRule");

        Object value = gameRule.getRuleValue();
        int id = this.gameRuleType.getId(value.getClass());

        writeString(buffer, gameRule.getRuleName());
        buffer.writeBoolean(gameRule.isRuleCanBeModified());
        VarInts.writeUnsignedInt(buffer, id);
        switch (id) {
            case 1:
                buffer.writeBoolean((boolean) value);
                break;
            case 2:
                VarInts.writeInt(buffer, (int) value);
                break;
            case 3:
                buffer.writeFloatLE((float) value);
                break;
        }
    }

    @Override
    public GameRule readGameRuleInStartGame(ByteBuf buffer) {
        Preconditions.checkNotNull(buffer, "buffer");

        String name = readString(buffer);
        boolean editable = buffer.readBoolean();
        int type = VarInts.readUnsignedInt(buffer);

        switch (type) {
            case 1:
                return new GameRule(name, editable, buffer.readBoolean());
            case 2:
                return new GameRule(name, editable, VarInts.readInt(buffer));
            case 3:
                return new GameRule(name, editable, buffer.readFloatLE());
        }
        throw new IllegalStateException("Invalid gamerule type received");
    }
}
