package org.cloudburstmc.protocol.bedrock.codec.v557;

import io.netty.buffer.ByteBuf;
import org.cloudburstmc.protocol.bedrock.codec.ActorDataTypeMap;
import org.cloudburstmc.protocol.bedrock.codec.v554.BedrockCodecHelper_v554;
import org.cloudburstmc.protocol.bedrock.data.ability.AbilitiesIndex;
import org.cloudburstmc.protocol.bedrock.data.actor.PropertySyncData;
import org.cloudburstmc.protocol.bedrock.data.actor.PropertySyncFloatEntry;
import org.cloudburstmc.protocol.bedrock.data.actor.PropertySyncIntEntry;
import org.cloudburstmc.protocol.bedrock.data.inventory.ContainerEnumName;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.ItemStackRequestActionType;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.action.ItemStackRequestCraftRecipeAutoAction;
import org.cloudburstmc.protocol.bedrock.data.text.TextProcessingEventOrigin;
import org.cloudburstmc.protocol.common.util.TypeMap;
import org.cloudburstmc.protocol.common.util.VarInts;

public class BedrockCodecHelper_v557 extends BedrockCodecHelper_v554 {

    public BedrockCodecHelper_v557(ActorDataTypeMap entityData, TypeMap<Class<?>> gameRulesTypes,
                                   TypeMap<ItemStackRequestActionType> stackRequestActionTypes, TypeMap<ContainerEnumName> containerEnumNames,
                                   TypeMap<AbilitiesIndex> abilities, TypeMap<TextProcessingEventOrigin> textProcessingEventOrigins) {
        super(entityData, gameRulesTypes, stackRequestActionTypes, containerEnumNames, abilities, textProcessingEventOrigins);
    }

    @Override
    public void writePropertySyncData(ByteBuf buffer, PropertySyncData propertySyncData) {
        writeArray(buffer, propertySyncData.getIntEntriesList(), (byteBuf, property) -> {
            VarInts.writeUnsignedInt(byteBuf, property.getPropertyIndex());
            VarInts.writeInt(byteBuf, property.getData());
        });
        writeArray(buffer, propertySyncData.getFloatEntriesList(), (byteBuf, property) -> {
            VarInts.writeUnsignedInt(byteBuf, property.getPropertyIndex());
            byteBuf.writeFloatLE(property.getData());
        });
    }

    @Override
    public void readPropertySyncData(ByteBuf buffer, PropertySyncData propertySyncData) {
        readArray(buffer, propertySyncData.getIntEntriesList(), byteBuf -> {
            final PropertySyncIntEntry entry = new PropertySyncIntEntry();
            entry.setPropertyIndex(VarInts.readUnsignedInt(byteBuf));
            entry.setData(VarInts.readInt(byteBuf));
            return entry;
        });
        readArray(buffer, propertySyncData.getFloatEntriesList(), byteBuf -> {
            final PropertySyncFloatEntry entry = new PropertySyncFloatEntry();
            entry.setPropertyIndex(VarInts.readUnsignedInt(byteBuf));
            entry.setData(byteBuf.readFloatLE());
            return entry;
        });
    }

    @Override
    protected void writeItemStackRequestCraftRecipeAutoAction(ByteBuf buffer, ItemStackRequestActionType type, ItemStackRequestCraftRecipeAutoAction action) {
        super.writeItemStackRequestCraftRecipeAutoAction(buffer, type, action);
        this.writeArray(buffer, action.getIngredients(), this::writeIngredient);
    }

    @Override
    protected ItemStackRequestCraftRecipeAutoAction readItemStackRequestCraftRecipeAutoAction(ByteBuf buffer, ItemStackRequestActionType type) {
        final ItemStackRequestCraftRecipeAutoAction action = super.readItemStackRequestCraftRecipeAutoAction(buffer, type);
        this.readArray(buffer, action.getIngredients(), ByteBuf::readUnsignedByte, this::readIngredient);
        return action;
    }
}