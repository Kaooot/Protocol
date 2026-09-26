package org.cloudburstmc.protocol.bedrock.codec.v422;

import io.netty.buffer.ByteBuf;
import org.cloudburstmc.protocol.bedrock.codec.ActorDataTypeMap;
import org.cloudburstmc.protocol.bedrock.codec.v419.BedrockCodecHelper_v419;
import org.cloudburstmc.protocol.bedrock.data.misc.RedactableString;
import org.cloudburstmc.protocol.bedrock.data.inventory.ContainerEnumName;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.ItemStackNetId;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.ItemStackRequest;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.ItemStackRequestActionType;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.ItemStackRequestId;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.action.ItemStackRequestCraftRecipeOptionalAction;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.response.ItemStackResponseSlotInfo;
import org.cloudburstmc.protocol.bedrock.data.recipe.RecipeNetId;
import org.cloudburstmc.protocol.common.util.TypeMap;
import org.cloudburstmc.protocol.common.util.VarInts;

public class BedrockCodecHelper_v422 extends BedrockCodecHelper_v419 {

    public BedrockCodecHelper_v422(ActorDataTypeMap entityData, TypeMap<Class<?>> gameRulesTypes,
                                   TypeMap<ItemStackRequestActionType> stackRequestActionTypes, TypeMap<ContainerEnumName> containerEnumNames) {
        super(entityData, gameRulesTypes, stackRequestActionTypes, containerEnumNames);
        this.itemStackRequestActionsVariant = this.itemStackRequestActionsVariant.toBuilder(this.stackRequestActionTypes::getId)
                .add(
                        ItemStackRequestActionType.CRAFT_RECIPE_OPTIONAL,
                        ItemStackRequestCraftRecipeOptionalAction.class,
                        (buffer, helper, type, value) -> this.writeItemStackRequestCraftRecipeOptionalAction(buffer, type, value),
                        (buffer, helper, type) -> this.readItemStackRequestCraftRecipeOptionalAction(buffer, type)
                )
                .build();
    }

    @Override
    public void writeItemStackRequest(ByteBuf buffer, ItemStackRequest request) {
        VarInts.writeInt(buffer, request.getClientRequestId().getID());
        this.writeArray(
                buffer,
                request.getActions(),
                (buf, codecHelper, object) -> this.itemStackRequestActionsVariant.write(buf, codecHelper, null, object)
        );
        this.writeArray(buffer, request.getStringsToFilter(), this::writeString);
    }

    @Override
    public ItemStackRequest readItemStackRequest(ByteBuf buffer) {
        final ItemStackRequest request = new ItemStackRequest();
        request.setClientRequestId(new ItemStackRequestId(VarInts.readInt(buffer)));
        this.readArray(
                buffer,
                request.getActions(),
                (buf, codecHelper) -> this.itemStackRequestActionsVariant.read(buf, codecHelper, null),
                this.getEncodingSettings().maxInventoryActionsOrRequests()
        );
        this.readArray(buffer, request.getStringsToFilter(), (buf, helper) -> helper.readStringMaxLen(buf, 1000));
        return request;
    }

    protected void writeItemStackRequestCraftRecipeOptionalAction(ByteBuf buffer, ItemStackRequestActionType type, ItemStackRequestCraftRecipeOptionalAction action) {
        VarInts.writeUnsignedInt(buffer, action.getRecipeNetId().getRawId());
        buffer.writeIntLE(action.getFilteredStringIndex());
    }

    protected ItemStackRequestCraftRecipeOptionalAction readItemStackRequestCraftRecipeOptionalAction(ByteBuf buffer, ItemStackRequestActionType type) {
        final ItemStackRequestCraftRecipeOptionalAction action = new ItemStackRequestCraftRecipeOptionalAction();
        action.setRecipeNetId(new RecipeNetId(VarInts.readUnsignedInt(buffer)));
        action.setFilteredStringIndex(buffer.readIntLE());
        return action;
    }

    @Override
    protected ItemStackResponseSlotInfo readItemStackResponseSlotInfo(ByteBuf buffer) {
        return new ItemStackResponseSlotInfo(
                buffer.readUnsignedByte(),
                buffer.readUnsignedByte(),
                buffer.readUnsignedByte(),
                new ItemStackNetId(VarInts.readInt(buffer)),
                new RedactableString(this.readString(buffer), ""),
                0
        );
    }

    @Override
    protected void writeItemStackResponseSlotInfo(ByteBuf buffer, ItemStackResponseSlotInfo info) {
        super.writeItemStackResponseSlotInfo(buffer, info);
        this.writeString(buffer, info.getCustomName().getUnredacted());
    }
}