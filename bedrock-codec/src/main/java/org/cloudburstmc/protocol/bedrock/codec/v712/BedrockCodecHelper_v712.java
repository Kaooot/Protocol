package org.cloudburstmc.protocol.bedrock.codec.v712;

import io.netty.buffer.ByteBuf;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import org.cloudburstmc.protocol.bedrock.codec.ActorDataTypeMap;
import org.cloudburstmc.protocol.bedrock.codec.v575.BedrockCodecHelper_v575;
import org.cloudburstmc.protocol.bedrock.data.AbilitiesIndex;
import org.cloudburstmc.protocol.bedrock.data.ActorLinkType;
import org.cloudburstmc.protocol.bedrock.data.actor.ActorLink;
import org.cloudburstmc.protocol.bedrock.data.inventory.ContainerEnumName;
import org.cloudburstmc.protocol.bedrock.data.inventory.FullContainerName;
import org.cloudburstmc.protocol.bedrock.data.inventory.descriptor.RecipeIngredient;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.ItemStackRequestSlotInfo;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.TextProcessingEventOrigin;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.action.*;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.response.ItemStackResponseContainerInfo;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.response.ItemStackResponseSlotInfo;
import org.cloudburstmc.protocol.bedrock.data.payload.crafting.RecipeNetId;
import org.cloudburstmc.protocol.common.util.TypeMap;
import org.cloudburstmc.protocol.common.util.VarInts;

import java.util.ArrayList;
import java.util.List;

public class BedrockCodecHelper_v712 extends BedrockCodecHelper_v575 {

    public BedrockCodecHelper_v712(ActorDataTypeMap entityData, TypeMap<Class<?>> gameRulesTypes, TypeMap<ItemStackRequestActionType> stackRequestActionTypes, TypeMap<ContainerEnumName> containerSlotTypes, TypeMap<AbilitiesIndex> abilities, TypeMap<TextProcessingEventOrigin> textProcessingEventOrigins) {
        super(entityData, gameRulesTypes, stackRequestActionTypes, containerSlotTypes, abilities, textProcessingEventOrigins);
    }

    @Override
    public void writeActorLink(ByteBuf buffer, ActorLink actorLink) {
        super.writeActorLink(buffer, actorLink);
        buffer.writeFloatLE(actorLink.getVehicleAngularVelocity());
    }

    @Override
    public ActorLink readActorLink(ByteBuf buffer) {
        return new ActorLink(
                VarInts.readLong(buffer),
                VarInts.readLong(buffer),
                ActorLinkType.from(buffer.readUnsignedByte()),
                buffer.readBoolean(),
                buffer.readBoolean(),
                buffer.readFloatLE()
        );
    }

    @Override
    protected void writeRequestActionData(ByteBuf byteBuf, ItemStackRequestAction action) {
        if (action.getType().equals(ItemStackRequestActionType.CRAFT_RECIPE)) {
            VarInts.writeUnsignedInt(byteBuf, ((RecipeItemStackRequestAction) action).getRecipeNetId().getRawId());
            byteBuf.writeByte(((RecipeItemStackRequestAction) action).getNumberOfRequestedCrafts());
        } else if (action.getType().equals(ItemStackRequestActionType.CRAFT_CREATIVE)) {
            VarInts.writeUnsignedInt(byteBuf, ((CraftCreativeAction) action).getCreativeItemNetId());
            byteBuf.writeByte(((CraftCreativeAction) action).getNumberOfRequestedCrafts());
        } else if (action.getType().equals(ItemStackRequestActionType.CRAFT_REPAIR_AND_DISENCHANT)) {
            VarInts.writeUnsignedInt(byteBuf, ((CraftGrindstoneAction) action).getRecipeNetId().getRawId());
            byteBuf.writeByte(((CraftGrindstoneAction) action).getNumberOfRequestedCrafts());
            VarInts.writeInt(byteBuf, ((CraftGrindstoneAction) action).getRepairCost());
        } else if (action.getType().equals(ItemStackRequestActionType.CRAFT_RECIPE_AUTO)) {
            VarInts.writeUnsignedInt(byteBuf, ((AutoCraftRecipeAction) action).getRecipeNetId().getRawId());
            byteBuf.writeByte(((AutoCraftRecipeAction) action).getNumberOfRequestedCrafts()); // since v712
            byteBuf.writeByte(((AutoCraftRecipeAction) action).getTimesCrafted());
            List<RecipeIngredient> ingredients = ((AutoCraftRecipeAction) action).getIngredients();
            byteBuf.writeByte(ingredients.size());
            writeArray(byteBuf, ingredients, this::writeIngredient);
        } else if (action.getType().equals(ItemStackRequestActionType.CRAFT_LOOM)) {
            this.writeString(byteBuf, ((CraftLoomAction) action).getPatternNameId());
            byteBuf.writeByte(((CraftLoomAction) action).getNumCrafts());
        } else {
            super.writeRequestActionData(byteBuf, action);
        }
    }

    @Override
    protected ItemStackRequestAction readRequestActionData(ByteBuf byteBuf, ItemStackRequestActionType type) {
        if (type.equals(ItemStackRequestActionType.CRAFT_RECIPE)) {
            return new CraftRecipeAction(new RecipeNetId(VarInts.readUnsignedInt(byteBuf)), byteBuf.readByte());
        } else if (type.equals(ItemStackRequestActionType.CRAFT_CREATIVE)) {
            return new CraftCreativeAction(VarInts.readUnsignedInt(byteBuf), byteBuf.readByte());
        } else if (type.equals(ItemStackRequestActionType.CRAFT_REPAIR_AND_DISENCHANT)) {
            return new CraftGrindstoneAction(new RecipeNetId(VarInts.readUnsignedInt(byteBuf)), byteBuf.readByte(), VarInts.readInt(byteBuf));
        } else if (type.equals(ItemStackRequestActionType.CRAFT_RECIPE_AUTO)) {
            int recipeNetworkId = VarInts.readUnsignedInt(byteBuf);
            int numberOfRequestedCrafts = byteBuf.readUnsignedByte(); // since v712
            int timesCrafted = byteBuf.readUnsignedByte();
            List<RecipeIngredient> ingredients = new ObjectArrayList<>();
            this.readArray(byteBuf, ingredients, ByteBuf::readUnsignedByte, this::readIngredient);
            return new AutoCraftRecipeAction(new RecipeNetId(recipeNetworkId), timesCrafted, ingredients, numberOfRequestedCrafts);
        } else if (type.equals(ItemStackRequestActionType.CRAFT_LOOM)) {
            String patternId = this.readString(byteBuf);
            int timesCrafted = byteBuf.readUnsignedByte();
            return new CraftLoomAction(patternId, timesCrafted);
        } else {
            return super.readRequestActionData(byteBuf, type);
        }
    }

    @Override
    protected ItemStackRequestSlotInfo readStackRequestSlotInfo(ByteBuf buffer) {
        FullContainerName containerName = this.readFullContainerName(buffer);
        return new ItemStackRequestSlotInfo(
                containerName.getContainerName(),
                buffer.readUnsignedByte(),
                VarInts.readInt(buffer),
                containerName
        );
    }

    @Override
    protected void writeStackRequestSlotInfo(ByteBuf buffer, ItemStackRequestSlotInfo data) {
        this.writeFullContainerName(buffer, data.getFullContainerName());
        buffer.writeByte(data.getSlot());
        VarInts.writeInt(buffer, data.getStackNetworkId());
    }

    @Override
    public void writeItemStackResponseContainer(ByteBuf buffer, ItemStackResponseContainerInfo container) {
        this.writeFullContainerName(buffer, container.getFullContainerName());
        this.writeArray(buffer, container.getSlots(), this::writeItemEntry);
    }

    @Override
    public ItemStackResponseContainerInfo readItemStackResponseContainer(ByteBuf buffer) {
        FullContainerName containerName = this.readFullContainerName(buffer);
        List<ItemStackResponseSlotInfo> itemEntries = new ArrayList<>();
        this.readArray(buffer, itemEntries, this::readItemEntry);
        return new ItemStackResponseContainerInfo(containerName.getContainerName(), itemEntries, containerName);
    }

    @Override
    public void writeFullContainerName(ByteBuf buffer, FullContainerName containerName) {
        this.writeContainerEnumName(buffer, containerName.getContainerName());
        buffer.writeIntLE(containerName.getDynamicID() == null ? 0 : containerName.getDynamicID());
    }

    @Override
    public FullContainerName readFullContainerName(ByteBuf buffer) {
        return new FullContainerName(this.readContainerEnumName(buffer), buffer.readIntLE());
    }
}