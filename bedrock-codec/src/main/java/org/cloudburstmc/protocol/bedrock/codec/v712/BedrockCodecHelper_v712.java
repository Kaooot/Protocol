package org.cloudburstmc.protocol.bedrock.codec.v712;

import io.netty.buffer.ByteBuf;
import org.cloudburstmc.protocol.bedrock.codec.ActorDataTypeMap;
import org.cloudburstmc.protocol.bedrock.codec.v575.BedrockCodecHelper_v575;
import org.cloudburstmc.protocol.bedrock.data.ability.AbilitiesIndex;
import org.cloudburstmc.protocol.bedrock.data.actor.link.ActorLink;
import org.cloudburstmc.protocol.bedrock.data.inventory.ContainerEnumName;
import org.cloudburstmc.protocol.bedrock.data.inventory.FullContainerName;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.ItemStackRequestActionType;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.ItemStackRequestSlotInfo;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.action.*;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.response.ItemStackResponseContainerInfo;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.response.ItemStackResponseSlotInfo;
import org.cloudburstmc.protocol.bedrock.data.recipe.RecipeNetId;
import org.cloudburstmc.protocol.bedrock.data.text.TextProcessingEventOrigin;
import org.cloudburstmc.protocol.common.util.TypeMap;
import org.cloudburstmc.protocol.common.util.VarInts;

import java.util.ArrayList;
import java.util.List;

public class BedrockCodecHelper_v712 extends BedrockCodecHelper_v575 {

    public BedrockCodecHelper_v712(ActorDataTypeMap entityData, TypeMap<Class<?>> gameRulesTypes, TypeMap<ItemStackRequestActionType> stackRequestActionTypes, TypeMap<ContainerEnumName> containerEnumNames, TypeMap<AbilitiesIndex> abilities, TypeMap<TextProcessingEventOrigin> textProcessingEventOrigins) {
        super(entityData, gameRulesTypes, stackRequestActionTypes, containerEnumNames, abilities, textProcessingEventOrigins);
    }

    @Override
    public void writeActorLink(ByteBuf buffer, ActorLink actorLink) {
        super.writeActorLink(buffer, actorLink);
        buffer.writeFloatLE(actorLink.getVehicleAngularVelocity());
    }

    @Override
    public ActorLink readActorLink(ByteBuf buffer) {
        final ActorLink actorLink = super.readActorLink(buffer);
        actorLink.setVehicleAngularVelocity(buffer.readFloatLE());
        return actorLink;
    }

    @Override
    protected void writeItemStackRequestCraftRecipeAction(ByteBuf buffer, ItemStackRequestActionType type, ItemStackRequestCraftRecipeAction action) {
        super.writeItemStackRequestCraftRecipeAction(buffer, type, action);
        buffer.writeByte(action.getNumberOfRequestedCrafts());
    }

    @Override
    protected ItemStackRequestCraftRecipeAction readItemStackRequestCraftRecipeAction(ByteBuf buffer, ItemStackRequestActionType type) {
        final ItemStackRequestCraftRecipeAction action = super.readItemStackRequestCraftRecipeAction(buffer, type);
        action.setNumberOfRequestedCrafts(buffer.readUnsignedByte());
        return action;
    }

    @Override
    protected void writeItemStackRequestCraftRecipeAutoAction(ByteBuf buffer, ItemStackRequestActionType type, ItemStackRequestCraftRecipeAutoAction action) {
        VarInts.writeUnsignedInt(buffer, action.getRecipeNetId().getRawId());
        buffer.writeByte(action.getNumberOfRequestedCrafts());
        buffer.writeByte(action.getTimesCrafted());
        this.writeArray(buffer, action.getIngredients(), this::writeIngredient);
    }

    @Override
    protected ItemStackRequestCraftRecipeAutoAction readItemStackRequestCraftRecipeAutoAction(ByteBuf buffer, ItemStackRequestActionType type) {
        final ItemStackRequestCraftRecipeAutoAction action = new ItemStackRequestCraftRecipeAutoAction();
        action.setRecipeNetId(new RecipeNetId(VarInts.readUnsignedInt(buffer)));
        action.setNumberOfRequestedCrafts(buffer.readUnsignedByte());
        action.setTimesCrafted(buffer.readUnsignedByte());
        this.readArray(buffer, action.getIngredients(), this::readIngredient);
        return action;
    }

    @Override
    protected void writeItemStackRequestCraftCreativeAction(ByteBuf buffer, ItemStackRequestActionType type, ItemStackRequestCraftCreativeAction action) {
        super.writeItemStackRequestCraftCreativeAction(buffer, type, action);
        buffer.writeByte(action.getNumberOfRequestedCrafts());
    }

    @Override
    protected ItemStackRequestCraftCreativeAction readItemStackRequestCraftCreativeAction(ByteBuf buffer, ItemStackRequestActionType type) {
        final ItemStackRequestCraftCreativeAction action = super.readItemStackRequestCraftCreativeAction(buffer, type);
        action.setNumberOfRequestedCrafts(buffer.readUnsignedByte());
        return action;
    }

    @Override
    protected void writeItemStackRequestCraftRepairAndDisenchantAction(ByteBuf buffer, ItemStackRequestActionType type, ItemStackRequestCraftRepairAndDisenchantAction action) {
        VarInts.writeUnsignedInt(buffer, action.getRecipeNetId().getRawId());
        buffer.writeByte(action.getNumberOfRequestedCrafts());
        VarInts.writeInt(buffer, action.getRepairCost());
    }

    @Override
    protected ItemStackRequestCraftRepairAndDisenchantAction readItemStackRequestCraftRepairAndDisenchantAction(ByteBuf buffer, ItemStackRequestActionType type) {
        final ItemStackRequestCraftRepairAndDisenchantAction action = new ItemStackRequestCraftRepairAndDisenchantAction();
        action.setRecipeNetId(new RecipeNetId(VarInts.readUnsignedInt(buffer)));
        action.setNumberOfRequestedCrafts(buffer.readUnsignedByte());
        action.setRepairCost(VarInts.readInt(buffer));
        return action;
    }

    @Override
    protected void writeItemStackRequestCraftLoomAction(ByteBuf buffer, ItemStackRequestActionType type, ItemStackRequestCraftLoomAction action) {
        super.writeItemStackRequestCraftLoomAction(buffer, type, action);
        buffer.writeByte(action.getNumCrafts());
    }

    @Override
    protected ItemStackRequestCraftLoomAction readItemStackRequestCraftLoomAction(ByteBuf buffer, ItemStackRequestActionType type) {
        final ItemStackRequestCraftLoomAction action = super.readItemStackRequestCraftLoomAction(buffer, type);
        action.setNumCrafts(buffer.readUnsignedByte());
        return action;
    }

    @Override
    protected void writeItemStackRequestSlotInfo(ByteBuf buffer, ItemStackRequestSlotInfo data) {
        this.writeFullContainerName(buffer, data.getFullContainerName());
        buffer.writeByte(data.getSlot());
        VarInts.writeInt(buffer, data.getNetIdVariant());
    }

    @Override
    protected ItemStackRequestSlotInfo readItemStackRequestSlotInfo(ByteBuf buffer) {
        final ItemStackRequestSlotInfo info = new ItemStackRequestSlotInfo();
        info.setFullContainerName(this.readFullContainerName(buffer));
        info.setSlot(buffer.readUnsignedByte());
        info.setNetIdVariant(VarInts.readInt(buffer));
        return info;
    }

    @Override
    public void writeItemStackResponseContainer(ByteBuf buffer, ItemStackResponseContainerInfo container) {
        this.writeFullContainerName(buffer, container.getFullContainerName());
        this.writeArray(buffer, container.getSlots(), this::writeItemStackResponseSlotInfo);
    }

    @Override
    public ItemStackResponseContainerInfo readItemStackResponseContainer(ByteBuf buffer) {
        FullContainerName containerName = this.readFullContainerName(buffer);
        List<ItemStackResponseSlotInfo> itemEntries = new ArrayList<>();
        this.readArray(buffer, itemEntries, this::readItemStackResponseSlotInfo);
        return new ItemStackResponseContainerInfo(containerName, itemEntries);
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