package org.cloudburstmc.protocol.bedrock.codec.v448;

import io.netty.buffer.ByteBuf;
import org.cloudburstmc.protocol.bedrock.codec.ActorDataTypeMap;
import org.cloudburstmc.protocol.bedrock.codec.v440.BedrockCodecHelper_v440;
import org.cloudburstmc.protocol.bedrock.data.inventory.ContainerEnumName;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.action.AutoCraftRecipeAction;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.action.ItemStackRequestAction;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.action.ItemStackRequestActionType;
import org.cloudburstmc.protocol.bedrock.data.payload.crafting.RecipeNetId;
import org.cloudburstmc.protocol.common.util.TypeMap;
import org.cloudburstmc.protocol.common.util.VarInts;

import java.util.Collections;

public class BedrockCodecHelper_v448 extends BedrockCodecHelper_v440 {
    public BedrockCodecHelper_v448(ActorDataTypeMap entityData, TypeMap<Class<?>> gameRulesTypes,
                                   TypeMap<ItemStackRequestActionType> stackRequestActionTypes, TypeMap<ContainerEnumName> containerSlotTypes) {
        super(entityData, gameRulesTypes, stackRequestActionTypes, containerSlotTypes);
    }

    @Override
    protected ItemStackRequestAction readRequestActionData(ByteBuf byteBuf, ItemStackRequestActionType type) {
        if (type == ItemStackRequestActionType.CRAFT_RECIPE_AUTO) {
            return new AutoCraftRecipeAction(
                    new RecipeNetId(VarInts.readUnsignedInt(byteBuf)), byteBuf.readUnsignedByte(), Collections.emptyList(), 0
            );
        } else {
            return super.readRequestActionData(byteBuf, type);
        }
    }

    @Override
    protected void writeRequestActionData(ByteBuf byteBuf, ItemStackRequestAction action) {
        super.writeRequestActionData(byteBuf, action);
        if (action.getType() == ItemStackRequestActionType.CRAFT_RECIPE_AUTO) {
            byteBuf.writeByte(((AutoCraftRecipeAction) action).getTimesCrafted());
        }
    }
}
