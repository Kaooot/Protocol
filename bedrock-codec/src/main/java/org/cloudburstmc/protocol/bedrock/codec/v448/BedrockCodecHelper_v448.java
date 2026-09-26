package org.cloudburstmc.protocol.bedrock.codec.v448;

import io.netty.buffer.ByteBuf;
import org.cloudburstmc.protocol.bedrock.codec.ActorDataTypeMap;
import org.cloudburstmc.protocol.bedrock.codec.v440.BedrockCodecHelper_v440;
import org.cloudburstmc.protocol.bedrock.data.inventory.ContainerEnumName;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.ItemStackRequestActionType;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.action.ItemStackRequestCraftRecipeAutoAction;
import org.cloudburstmc.protocol.common.util.TypeMap;

public class BedrockCodecHelper_v448 extends BedrockCodecHelper_v440 {
    public BedrockCodecHelper_v448(ActorDataTypeMap entityData, TypeMap<Class<?>> gameRulesTypes,
                                   TypeMap<ItemStackRequestActionType> stackRequestActionTypes, TypeMap<ContainerEnumName> containerEnumNames) {
        super(entityData, gameRulesTypes, stackRequestActionTypes, containerEnumNames);
    }

    @Override
    protected void writeItemStackRequestCraftRecipeAutoAction(ByteBuf buffer, ItemStackRequestActionType type, ItemStackRequestCraftRecipeAutoAction action) {
        super.writeItemStackRequestCraftRecipeAutoAction(buffer, type, action);
        buffer.writeByte(action.getTimesCrafted());
    }

    @Override
    protected ItemStackRequestCraftRecipeAutoAction readItemStackRequestCraftRecipeAutoAction(ByteBuf buffer, ItemStackRequestActionType type) {
        final ItemStackRequestCraftRecipeAutoAction action = super.readItemStackRequestCraftRecipeAutoAction(buffer, type);
        action.setTimesCrafted(buffer.readUnsignedByte());
        return action;
    }
}