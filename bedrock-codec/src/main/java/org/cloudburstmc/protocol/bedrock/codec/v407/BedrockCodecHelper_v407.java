package org.cloudburstmc.protocol.bedrock.codec.v407;

import io.netty.buffer.ByteBuf;
import org.cloudburstmc.protocol.bedrock.codec.ActorDataTypeMap;
import org.cloudburstmc.protocol.bedrock.codec.VariantCodec;
import org.cloudburstmc.protocol.bedrock.codec.v390.BedrockCodecHelper_v390;
import org.cloudburstmc.protocol.bedrock.data.actor.link.ActorLink;
import org.cloudburstmc.protocol.bedrock.data.definitions.ItemDefinition;
import org.cloudburstmc.protocol.bedrock.data.inventory.ContainerEnumName;
import org.cloudburstmc.protocol.bedrock.data.inventory.FullContainerName;
import org.cloudburstmc.protocol.bedrock.data.inventory.ItemData;
import org.cloudburstmc.protocol.bedrock.data.inventory.descriptor.DefaultDescriptor;
import org.cloudburstmc.protocol.bedrock.data.inventory.descriptor.InvalidDescriptor;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.ItemStackNetId;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.ItemStackRequest;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.ItemStackRequestActionType;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.ItemStackRequestId;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.ItemStackRequestSlotInfo;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.action.*;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.response.ItemStackResponseContainerInfo;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.response.ItemStackResponseSlotInfo;
import org.cloudburstmc.protocol.bedrock.data.item.creative.CreativeItemNetId;
import org.cloudburstmc.protocol.bedrock.data.misc.RedactableString;
import org.cloudburstmc.protocol.bedrock.data.recipe.RecipeIngredient;
import org.cloudburstmc.protocol.bedrock.data.recipe.RecipeNetId;
import org.cloudburstmc.protocol.common.util.TypeMap;
import org.cloudburstmc.protocol.common.util.VarInts;

import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.requireNonNull;
import static org.cloudburstmc.protocol.common.util.Preconditions.checkArgument;

public class BedrockCodecHelper_v407 extends BedrockCodecHelper_v390 {

    protected final TypeMap<ItemStackRequestActionType> stackRequestActionTypes;
    protected final TypeMap<ContainerEnumName> containerEnumNames;

    protected VariantCodec<ItemStackRequestActionType> itemStackRequestActionsVariant;

    public BedrockCodecHelper_v407(ActorDataTypeMap entityData, TypeMap<Class<?>> gameRulesTypes,
                                   TypeMap<ItemStackRequestActionType> stackRequestActionTypes, TypeMap<ContainerEnumName> containerEnumNames) {
        super(entityData, gameRulesTypes);
        this.stackRequestActionTypes = stackRequestActionTypes;
        this.containerEnumNames = containerEnumNames;
        this.itemStackRequestActionsVariant = VariantCodec.<ItemStackRequestActionType, ItemStackRequestActionType>builder(
                        this.stackRequestActionTypes::getId,
                        (buffer, helper, type, value) -> buffer.writeByte(value),
                        (buffer, helper, type) -> (int) buffer.readByte()
                )
                .add(
                        ItemStackRequestActionType.TAKE,
                        ItemStackRequestTakeAction.class,
                        (buffer, helper, type, value) -> this.writeItemStackRequestTakeAction(buffer, type, value),
                        (buffer, helper, type) -> this.readItemStackRequestTakeAction(buffer, type)
                )
                .add(
                        ItemStackRequestActionType.PLACE,
                        ItemStackRequestPlaceAction.class,
                        (buffer, helper, type, value) -> this.writeItemStackRequestPlaceAction(buffer, type, value),
                        (buffer, helper, type) -> this.readItemStackRequestPlaceAction(buffer, type)
                )
                .add(
                        ItemStackRequestActionType.SWAP,
                        ItemStackRequestSwapAction.class,
                        (buffer, helper, type, value) -> this.writeItemStackRequestSwapAction(buffer, type, value),
                        (buffer, helper, type) -> this.readItemStackRequestSwapAction(buffer, type)
                )
                .add(
                        ItemStackRequestActionType.DROP,
                        ItemStackRequestDropAction.class,
                        (buffer, helper, type, value) -> this.writeItemStackRequestDropAction(buffer, type, value),
                        (buffer, helper, type) -> this.readItemStackRequestDropAction(buffer, type)
                )
                .add(
                        ItemStackRequestActionType.DESTROY,
                        ItemStackRequestDestroyAction.class,
                        (buffer, helper, type, value) -> this.writeItemStackRequestDestroyAction(buffer, type, value),
                        (buffer, helper, type) -> this.readItemStackRequestDestroyAction(buffer, type)
                )
                .add(
                        ItemStackRequestActionType.CONSUME,
                        ItemStackRequestConsumeAction.class,
                        (buffer, helper, type, value) -> this.writeItemStackRequestConsumeAction(buffer, type, value),
                        (buffer, helper, type) -> this.readItemStackRequestConsumeAction(buffer, type)
                )
                .add(
                        ItemStackRequestActionType.CREATE,
                        ItemStackRequestCreateAction.class,
                        (buffer, helper, type, value) -> this.writeItemStackRequestCreateAction(buffer, type, value),
                        (buffer, helper, type) -> this.readItemStackRequestCreateAction(buffer, type)
                )
                .add(
                        ItemStackRequestActionType.SCREEN_LAB_TABLE_COMBINE,
                        ItemStackRequestLabTableCombineAction.class,
                        (buffer, helper, type, value) -> {
                        },
                        (buffer, helper, type) -> new ItemStackRequestLabTableCombineAction()
                )
                .add(
                        ItemStackRequestActionType.SCREEN_BEACON_PAYMENT,
                        ItemStackRequestBeaconPaymentAction.class,
                        (buffer, helper, type, value) -> this.writeItemStackRequestBeaconPaymentAction(buffer, type, value),
                        (buffer, helper, type) -> this.readItemStackRequestBeaconPaymentAction(buffer, type)
                )
                .add(
                        ItemStackRequestActionType.CRAFT_RECIPE,
                        ItemStackRequestCraftRecipeAction.class,
                        (buffer, helper, type, value) -> this.writeItemStackRequestCraftRecipeAction(buffer, type, value),
                        (buffer, helper, type) -> this.readItemStackRequestCraftRecipeAction(buffer, type)
                )
                .add(
                        ItemStackRequestActionType.CRAFT_RECIPE_AUTO,
                        ItemStackRequestCraftRecipeAutoAction.class,
                        (buffer, helper, type, value) -> this.writeItemStackRequestCraftRecipeAutoAction(buffer, type, value),
                        (buffer, helper, type) -> this.readItemStackRequestCraftRecipeAutoAction(buffer, type)
                )
                .add(
                        ItemStackRequestActionType.CRAFT_CREATIVE,
                        ItemStackRequestCraftCreativeAction.class,
                        (buffer, helper, type, value) -> this.writeItemStackRequestCraftCreativeAction(buffer, type, value),
                        (buffer, helper, type) -> this.readItemStackRequestCraftCreativeAction(buffer, type)
                )
                .add(
                        ItemStackRequestActionType.CRAFT_NON_IMPLEMENTED,
                        ItemStackRequestCraftNonImplementedDeprecatedAction.class,
                        (buffer, helper, type, value) -> {
                        },
                        (buffer, helper, type) -> new ItemStackRequestCraftNonImplementedDeprecatedAction()
                )
                .add(
                        ItemStackRequestActionType.CRAFT_RESULTS,
                        ItemStackRequestCraftResultsDeprecatedAction.class,
                        (buffer, helper, type, value) -> this.writeItemStackRequestCraftResultsDeprecatedAction(buffer, type, value),
                        (buffer, helper, type) -> this.readItemStackRequestCraftResultsDeprecatedAction(buffer, type)
                )
                .build();
    }

    @Override
    public void writeActorLink(ByteBuf buffer, ActorLink actorLink) {
        super.writeActorLink(buffer, actorLink);
        buffer.writeBoolean(actorLink.isPassengerInitiated());
    }

    @Override
    public ActorLink readActorLink(ByteBuf buffer) {
        final ActorLink actorLink = super.readActorLink(buffer);
        actorLink.setPassengerInitiated(buffer.readBoolean());
        return actorLink;
    }

    @Override
    public ItemData readNetItem(ByteBuf buffer) {
        int netId = VarInts.readInt(buffer);
        ItemData item = this.readItem(buffer);
        item.setNetId(netId);
        return item;
    }

    @Override
    public void writeNetItem(ByteBuf buffer, ItemData item) {
        VarInts.writeInt(buffer, item.getNetId());
        this.writeItem(buffer, item);
    }

    @Override
    public void writeItemStackRequest(ByteBuf buffer, ItemStackRequest request) {
        VarInts.writeInt(buffer, request.getClientRequestId().getID());
        this.writeArray(
                buffer,
                request.getActions(),
                (buf, codecHelper, object) -> this.itemStackRequestActionsVariant.write(buf, codecHelper, null, object)
        );
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
        return request;
    }

    protected void writeItemStackRequestSlotInfo(ByteBuf buffer, ItemStackRequestSlotInfo data) {
        this.writeContainerEnumName(buffer, data.getContainerEnumName());
        buffer.writeByte(data.getSlot());
        VarInts.writeInt(buffer, data.getNetIdVariant());
    }

    protected ItemStackRequestSlotInfo readItemStackRequestSlotInfo(ByteBuf buffer) {
        final ItemStackRequestSlotInfo info = new ItemStackRequestSlotInfo();
        info.setContainerEnumName(this.readContainerEnumName(buffer));
        info.setSlot(buffer.readUnsignedByte());
        info.setNetIdVariant(VarInts.readInt(buffer));
        return info;
    }

    protected void writeItemStackRequestTakeAction(ByteBuf buffer, ItemStackRequestActionType type, ItemStackRequestTakeAction action) {
        buffer.writeByte(action.getAmount());
        this.writeItemStackRequestSlotInfo(buffer, action.getSource());
        this.writeItemStackRequestSlotInfo(buffer, action.getDestination());
    }

    protected ItemStackRequestTakeAction readItemStackRequestTakeAction(ByteBuf buffer, ItemStackRequestActionType type) {
        final ItemStackRequestTakeAction action = new ItemStackRequestTakeAction();
        action.setAmount(buffer.readUnsignedByte());
        action.setSource(this.readItemStackRequestSlotInfo(buffer));
        action.setDestination(this.readItemStackRequestSlotInfo(buffer));
        return action;
    }

    protected void writeItemStackRequestPlaceAction(ByteBuf buffer, ItemStackRequestActionType type, ItemStackRequestPlaceAction action) {
        buffer.writeByte(action.getAmount());
        this.writeItemStackRequestSlotInfo(buffer, action.getSource());
        this.writeItemStackRequestSlotInfo(buffer, action.getDestination());
    }

    protected ItemStackRequestPlaceAction readItemStackRequestPlaceAction(ByteBuf buffer, ItemStackRequestActionType type) {
        final ItemStackRequestPlaceAction action = new ItemStackRequestPlaceAction();
        action.setAmount(buffer.readUnsignedByte());
        action.setSource(this.readItemStackRequestSlotInfo(buffer));
        action.setDestination(this.readItemStackRequestSlotInfo(buffer));
        return action;
    }

    protected void writeItemStackRequestSwapAction(ByteBuf buffer, ItemStackRequestActionType type, ItemStackRequestSwapAction action) {
        this.writeItemStackRequestSlotInfo(buffer, action.getSource());
        this.writeItemStackRequestSlotInfo(buffer, action.getDestination());
    }

    protected ItemStackRequestSwapAction readItemStackRequestSwapAction(ByteBuf buffer, ItemStackRequestActionType type) {
        final ItemStackRequestSwapAction action = new ItemStackRequestSwapAction();
        action.setSource(this.readItemStackRequestSlotInfo(buffer));
        action.setDestination(this.readItemStackRequestSlotInfo(buffer));
        return action;
    }

    protected void writeItemStackRequestDropAction(ByteBuf buffer, ItemStackRequestActionType type, ItemStackRequestDropAction action) {
        buffer.writeByte(action.getAmount());
        this.writeItemStackRequestSlotInfo(buffer, action.getSource());
        buffer.writeBoolean(action.isRandomly());
    }

    protected ItemStackRequestDropAction readItemStackRequestDropAction(ByteBuf buffer, ItemStackRequestActionType type) {
        final ItemStackRequestDropAction action = new ItemStackRequestDropAction();
        action.setAmount(buffer.readUnsignedByte());
        action.setSource(this.readItemStackRequestSlotInfo(buffer));
        action.setRandomly(buffer.readBoolean());
        return action;
    }

    protected void writeItemStackRequestDestroyAction(ByteBuf buffer, ItemStackRequestActionType type, ItemStackRequestDestroyAction action) {
        buffer.writeByte(action.getAmount());
        this.writeItemStackRequestSlotInfo(buffer, action.getSource());
    }

    protected ItemStackRequestDestroyAction readItemStackRequestDestroyAction(ByteBuf buffer, ItemStackRequestActionType type) {
        final ItemStackRequestDestroyAction action = new ItemStackRequestDestroyAction();
        action.setAmount(buffer.readUnsignedByte());
        action.setSource(this.readItemStackRequestSlotInfo(buffer));
        return action;
    }

    protected void writeItemStackRequestConsumeAction(ByteBuf buffer, ItemStackRequestActionType type, ItemStackRequestConsumeAction action) {
        buffer.writeByte(action.getAmount());
        this.writeItemStackRequestSlotInfo(buffer, action.getSource());
    }

    protected ItemStackRequestConsumeAction readItemStackRequestConsumeAction(ByteBuf buffer, ItemStackRequestActionType type) {
        final ItemStackRequestConsumeAction action = new ItemStackRequestConsumeAction();
        action.setAmount(buffer.readUnsignedByte());
        action.setSource(this.readItemStackRequestSlotInfo(buffer));
        return action;
    }

    protected void writeItemStackRequestCreateAction(ByteBuf buffer, ItemStackRequestActionType type, ItemStackRequestCreateAction action) {
        buffer.writeByte(action.getResultsIndex());
    }

    protected ItemStackRequestCreateAction readItemStackRequestCreateAction(ByteBuf buffer, ItemStackRequestActionType type) {
        final ItemStackRequestCreateAction action = new ItemStackRequestCreateAction();
        action.setResultsIndex(buffer.readUnsignedByte());
        return action;
    }

    protected void writeItemStackRequestBeaconPaymentAction(ByteBuf buffer, ItemStackRequestActionType type, ItemStackRequestBeaconPaymentAction action) {
        VarInts.writeInt(buffer, action.getPrimaryEffectId());
        VarInts.writeInt(buffer, action.getSecondaryEffectId());
    }

    protected ItemStackRequestBeaconPaymentAction readItemStackRequestBeaconPaymentAction(ByteBuf buffer, ItemStackRequestActionType type) {
        final ItemStackRequestBeaconPaymentAction action = new ItemStackRequestBeaconPaymentAction();
        action.setPrimaryEffectId(VarInts.readInt(buffer));
        action.setSecondaryEffectId(VarInts.readInt(buffer));
        return action;
    }

    protected void writeItemStackRequestCraftRecipeAction(ByteBuf buffer, ItemStackRequestActionType type, ItemStackRequestCraftRecipeAction action) {
        VarInts.writeUnsignedInt(buffer, action.getRecipeNetId().getRawId());
    }

    protected ItemStackRequestCraftRecipeAction readItemStackRequestCraftRecipeAction(ByteBuf buffer, ItemStackRequestActionType type) {
        final ItemStackRequestCraftRecipeAction action = new ItemStackRequestCraftRecipeAction();
        action.setRecipeNetId(new RecipeNetId(VarInts.readUnsignedInt(buffer)));
        return action;
    }

    protected void writeItemStackRequestCraftRecipeAutoAction(ByteBuf buffer, ItemStackRequestActionType type, ItemStackRequestCraftRecipeAutoAction action) {
        VarInts.writeUnsignedInt(buffer, action.getRecipeNetId().getRawId());
    }

    protected ItemStackRequestCraftRecipeAutoAction readItemStackRequestCraftRecipeAutoAction(ByteBuf buffer, ItemStackRequestActionType type) {
        final ItemStackRequestCraftRecipeAutoAction action = new ItemStackRequestCraftRecipeAutoAction();
        action.setRecipeNetId(new RecipeNetId(VarInts.readUnsignedInt(buffer)));
        return action;
    }

    protected void writeItemStackRequestCraftCreativeAction(ByteBuf buffer, ItemStackRequestActionType type, ItemStackRequestCraftCreativeAction action) {
        VarInts.writeUnsignedInt(buffer, action.getCreativeItemNetId().getID());
    }

    protected ItemStackRequestCraftCreativeAction readItemStackRequestCraftCreativeAction(ByteBuf buffer, ItemStackRequestActionType type) {
        final ItemStackRequestCraftCreativeAction action = new ItemStackRequestCraftCreativeAction();
        action.setCreativeItemNetId(new CreativeItemNetId(VarInts.readUnsignedInt(buffer)));
        return action;
    }

    protected void writeItemStackRequestCraftResultsDeprecatedAction(ByteBuf buffer, ItemStackRequestActionType type, ItemStackRequestCraftResultsDeprecatedAction action) {
        this.writeArray(buffer, action.getCraftResults(), this::writeItem);
        buffer.writeByte(action.getNumCrafts());
    }

    protected ItemStackRequestCraftResultsDeprecatedAction readItemStackRequestCraftResultsDeprecatedAction(ByteBuf buffer, ItemStackRequestActionType type) {
        final ItemStackRequestCraftResultsDeprecatedAction action = new ItemStackRequestCraftResultsDeprecatedAction();
        this.readArray(buffer, action.getCraftResults(), this::readItem);
        action.setNumCrafts(buffer.readUnsignedByte());
        return action;
    }

    @Override
    public ContainerEnumName readContainerEnumName(ByteBuf buffer) {
        return this.containerEnumNames.getType(buffer.readByte());
    }

    @Override
    public void writeContainerEnumName(ByteBuf buffer, ContainerEnumName slotType) {
        buffer.writeByte(this.containerEnumNames.getId(slotType));
    }

    @Override
    public RecipeIngredient readIngredient(ByteBuf buffer) {
        int runtimeId = VarInts.readInt(buffer);
        if (runtimeId == 0) {
            // We don't need to read anything extra.
            return RecipeIngredient.EMPTY;
        }
        ItemDefinition definition = this.getItemDefinitions().getDefinition(runtimeId);

        int meta = fromAuxValue(VarInts.readInt(buffer));
        int count = VarInts.readInt(buffer);

        return new RecipeIngredient(new DefaultDescriptor(definition, meta), count);
    }

    @Override
    public void writeIngredient(ByteBuf buffer, RecipeIngredient ingredient) {
        requireNonNull(ingredient, "ingredient is null");
        if (ingredient == RecipeIngredient.EMPTY || ingredient.getDescriptor() == InvalidDescriptor.INSTANCE) {
            VarInts.writeInt(buffer, 0);
            return;
        }

        checkArgument(ingredient.getDescriptor() instanceof DefaultDescriptor, "Descriptor must be of type DefaultDescriptor");
        DefaultDescriptor descriptor = (DefaultDescriptor) ingredient.getDescriptor();

        VarInts.writeInt(buffer, descriptor.getItemId().getRuntimeId());
        VarInts.writeInt(buffer, toAuxValue(descriptor.getAuxValue()));
        VarInts.writeInt(buffer, ingredient.getStackSize());
    }

    @Override
    public void writeItemStackResponseContainer(ByteBuf buffer, ItemStackResponseContainerInfo container) {
        this.writeContainerEnumName(buffer, container.getFullContainerName().getContainerName());
        this.writeArray(buffer, container.getSlots(), this::writeItemStackResponseSlotInfo);
    }

    @Override
    public ItemStackResponseContainerInfo readItemStackResponseContainer(ByteBuf buffer) {
        ContainerEnumName slotType = this.readContainerEnumName(buffer);
        List<ItemStackResponseSlotInfo> itemEntries = new ArrayList<>();
        this.readArray(buffer, itemEntries, this::readItemStackResponseSlotInfo);
        return new ItemStackResponseContainerInfo(new FullContainerName(slotType, null), itemEntries);
    }

    protected ItemStackResponseSlotInfo readItemStackResponseSlotInfo(ByteBuf buffer) {
        return new ItemStackResponseSlotInfo(
                buffer.readUnsignedByte(),
                buffer.readUnsignedByte(),
                buffer.readUnsignedByte(),
                new ItemStackNetId(VarInts.readInt(buffer)),
                new RedactableString("", ""),
                0);
    }

    protected void writeItemStackResponseSlotInfo(ByteBuf buffer, ItemStackResponseSlotInfo info) {
        buffer.writeByte(info.getRequestedSlot());
        buffer.writeByte(info.getSlot());
        buffer.writeByte(info.getAmount());
        VarInts.writeInt(buffer, info.getItemStackNetId().getID());
    }

    protected int fromAuxValue(int value) {
        return value == 0x7fff ? -1 : value;
    }

    protected int toAuxValue(int value) {
        return value == -1 ? 0x7fff : value;
    }
}