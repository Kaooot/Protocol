package org.cloudburstmc.protocol.bedrock.codec.v471;

import io.netty.buffer.ByteBuf;
import org.cloudburstmc.protocol.bedrock.codec.ActorDataTypeMap;
import org.cloudburstmc.protocol.bedrock.codec.v465.BedrockCodecHelper_v465;
import org.cloudburstmc.protocol.bedrock.data.inventory.ContainerEnumName;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.ItemStackRequestActionType;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.action.ItemStackRequestCraftLoomAction;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.action.ItemStackRequestCraftRepairAndDisenchantAction;
import org.cloudburstmc.protocol.bedrock.data.recipe.RecipeNetId;
import org.cloudburstmc.protocol.common.util.TypeMap;
import org.cloudburstmc.protocol.common.util.VarInts;

public class BedrockCodecHelper_v471 extends BedrockCodecHelper_v465 {

    public BedrockCodecHelper_v471(ActorDataTypeMap entityData, TypeMap<Class<?>> gameRulesTypes,
                                   TypeMap<ItemStackRequestActionType> stackRequestActionTypes, TypeMap<ContainerEnumName> containerEnumNames) {
        super(entityData, gameRulesTypes, stackRequestActionTypes, containerEnumNames);
        this.itemStackRequestActionsVariant = this.itemStackRequestActionsVariant.toBuilder(this.stackRequestActionTypes::getId)
                .add(
                        ItemStackRequestActionType.CRAFT_REPAIR_AND_DISENCHANT,
                        ItemStackRequestCraftRepairAndDisenchantAction.class,
                        (buffer, helper, type, value) -> this.writeItemStackRequestCraftRepairAndDisenchantAction(buffer, type, value),
                        (buffer, helper, type) -> this.readItemStackRequestCraftRepairAndDisenchantAction(buffer, type)
                )
                .add(
                        ItemStackRequestActionType.CRAFT_LOOM,
                        ItemStackRequestCraftLoomAction.class,
                        (buffer, helper, type, value) -> this.writeItemStackRequestCraftLoomAction(buffer, type, value),
                        (buffer, helper, type) -> this.readItemStackRequestCraftLoomAction(buffer, type)
                )
                .build();
    }

    protected void writeItemStackRequestCraftRepairAndDisenchantAction(ByteBuf buffer, ItemStackRequestActionType type, ItemStackRequestCraftRepairAndDisenchantAction action) {
        VarInts.writeUnsignedInt(buffer, action.getRecipeNetId().getRawId());
        VarInts.writeInt(buffer, action.getRepairCost());
    }

    protected ItemStackRequestCraftRepairAndDisenchantAction readItemStackRequestCraftRepairAndDisenchantAction(ByteBuf buffer, ItemStackRequestActionType type) {
        final ItemStackRequestCraftRepairAndDisenchantAction action = new ItemStackRequestCraftRepairAndDisenchantAction();
        action.setRecipeNetId(new RecipeNetId(VarInts.readUnsignedInt(buffer)));
        action.setRepairCost(VarInts.readInt(buffer));
        return action;
    }

    protected void writeItemStackRequestCraftLoomAction(ByteBuf buffer, ItemStackRequestActionType type, ItemStackRequestCraftLoomAction action) {
        this.writeString(buffer, action.getPatternNameId());
    }

    protected ItemStackRequestCraftLoomAction readItemStackRequestCraftLoomAction(ByteBuf buffer, ItemStackRequestActionType type) {
        final ItemStackRequestCraftLoomAction action = new ItemStackRequestCraftLoomAction();
        action.setPatternNameId(this.readString(buffer));
        return action;
    }
}