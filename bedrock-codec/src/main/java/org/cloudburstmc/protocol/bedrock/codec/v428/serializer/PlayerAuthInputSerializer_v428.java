package org.cloudburstmc.protocol.bedrock.codec.v428.serializer;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.v419.serializer.PlayerAuthInputSerializer_v419;
import org.cloudburstmc.protocol.bedrock.data.PlayerActionType;
import org.cloudburstmc.protocol.bedrock.data.PlayerAuthInputData;
import org.cloudburstmc.protocol.bedrock.data.PlayerBlockActionData;
import org.cloudburstmc.protocol.bedrock.data.inventory.ContainerId;
import org.cloudburstmc.protocol.bedrock.data.payload.inventory.net.ItemStackLegacyRequestId;
import org.cloudburstmc.protocol.bedrock.data.payload.inventory.transaction.*;
import org.cloudburstmc.protocol.bedrock.data.payload.inventory.transaction.data.ItemUseInventoryTransaction;
import org.cloudburstmc.protocol.bedrock.data.payload.inventory.transaction.data.PackedLegacyItemUseInventoryTransaction;
import org.cloudburstmc.protocol.bedrock.packet.PlayerAuthInputPacket;
import org.cloudburstmc.protocol.common.util.VarInts;

import static java.util.Objects.requireNonNull;

@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class PlayerAuthInputSerializer_v428 extends PlayerAuthInputSerializer_v419 {

    public static final PlayerAuthInputSerializer_v428 INSTANCE = new PlayerAuthInputSerializer_v428();

    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, PlayerAuthInputPacket packet) {
        super.serialize(buffer, helper, packet);

        if (packet.getInputData().contains(PlayerAuthInputData.PERFORM_ITEM_INTERACTION)) {
            this.writePackedLegacyItemUseInventoryTransaction(buffer, helper, packet.getItemUseTransaction());
        }

        if (packet.getInputData().contains(PlayerAuthInputData.PERFORM_ITEM_STACK_REQUEST)) {
            helper.writeItemStackRequest(buffer, packet.getItemStackRequest());
        }

        if (packet.getInputData().contains(PlayerAuthInputData.PERFORM_BLOCK_ACTIONS)) {
            VarInts.writeInt(buffer, packet.getPlayerBlockActions().size());
            for (PlayerBlockActionData actionData : packet.getPlayerBlockActions()) {
                writePlayerBlockActionData(buffer, helper, actionData);
            }
        }
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, PlayerAuthInputPacket packet) {
        super.deserialize(buffer, helper, packet);

        if (packet.getInputData().contains(PlayerAuthInputData.PERFORM_ITEM_INTERACTION)) {
            packet.setItemUseTransaction(this.readPackedLegacyItemUseInventoryTransaction(buffer, helper));
        }

        if (packet.getInputData().contains(PlayerAuthInputData.PERFORM_ITEM_STACK_REQUEST)) {
            packet.setItemStackRequest(helper.readItemStackRequest(buffer));
        }

        if (packet.getInputData().contains(PlayerAuthInputData.PERFORM_BLOCK_ACTIONS)) {
            helper.readArray(buffer, packet.getPlayerBlockActions(), VarInts::readInt, this::readPlayerBlockActionData, helper.getEncodingSettings().maxPlayerBlockActionDataSize());
        }
    }

    protected void writePlayerBlockActionData(ByteBuf buffer, BedrockCodecHelper helper, PlayerBlockActionData actionData) {
        VarInts.writeInt(buffer, actionData.getPlayerActionType().ordinal());
        switch (actionData.getPlayerActionType()) {
            case START_DESTROY_BLOCK:
            case STOP_DESTROY_BLOCK:
            case ABORT_DESTROY_BLOCK:
            case CRACK_BLOCK:
            case PREDICT_DESTROY_BLOCK:
            case CONTINUE_DESTROY_BLOCK:
                helper.writeVector3i(buffer, actionData.getBlockPosition());
                VarInts.writeInt(buffer, actionData.getFacing());
        }
    }

    protected PlayerBlockActionData readPlayerBlockActionData(ByteBuf buffer, BedrockCodecHelper helper) {
        PlayerBlockActionData actionData = new PlayerBlockActionData();
        actionData.setPlayerActionType(PlayerActionType.values()[VarInts.readInt(buffer)]);
        switch (actionData.getPlayerActionType()) {
            case START_DESTROY_BLOCK:
            case STOP_DESTROY_BLOCK:
            case ABORT_DESTROY_BLOCK:
            case CRACK_BLOCK:
            case PREDICT_DESTROY_BLOCK:
            case CONTINUE_DESTROY_BLOCK:
                actionData.setBlockPosition(helper.readVector3i(buffer));
                actionData.setFacing(VarInts.readInt(buffer));
        }
        return actionData;
    }

    protected void writePackedLegacyItemUseInventoryTransaction(ByteBuf buffer, BedrockCodecHelper helper, PackedLegacyItemUseInventoryTransaction transaction) {
        this.writeLegacyRequestId(buffer, helper, transaction.getLegacyRequestID());

        if (transaction.getLegacyRequestID().getID() < -1 && (transaction.getLegacyRequestID().getID() & 1) == 0) {
            helper.writeArray(buffer, transaction.getLegacySetItemSlots(), this::writeLegacySetSlot);
        }
        helper.writeArray(buffer, transaction.getActions(), this::writeInventoryAction);
        this.writeItemUseInventoryTransaction(buffer, helper, transaction.getTransaction());
    }

    protected PackedLegacyItemUseInventoryTransaction readPackedLegacyItemUseInventoryTransaction(ByteBuf buffer, BedrockCodecHelper helper) {
        final PackedLegacyItemUseInventoryTransaction transaction = new PackedLegacyItemUseInventoryTransaction();
        transaction.setLegacyRequestID(this.readLegacyRequestId(buffer, helper));

        if (transaction.getLegacyRequestID().getID() < -1 && (transaction.getLegacyRequestID().getID() & 1) == 0) {
            helper.readArray(buffer, transaction.getLegacySetItemSlots(), this::readLegacySetSlot);
        }

        helper.readArray(buffer, transaction.getActions(), this::readInventoryAction);
        transaction.setTransaction(this.readItemUseInventoryTransaction(buffer, helper));
        return transaction;
    }

    protected void writeLegacyRequestId(ByteBuf buffer, BedrockCodecHelper helper, ItemStackLegacyRequestId id) {
        VarInts.writeInt(buffer, id.getID());
    }

    protected ItemStackLegacyRequestId readLegacyRequestId(ByteBuf buffer, BedrockCodecHelper helper) {
        return new ItemStackLegacyRequestId(VarInts.readInt(buffer));
    }

    protected void writeLegacySetSlot(ByteBuf buffer, BedrockCodecHelper helper, LegacySetSlot slot) {
        helper.writeContainerEnumName(buffer, slot.getContainerEnum());
        helper.writeByteArray(buffer, slot.getSlots());
    }

    protected LegacySetSlot readLegacySetSlot(ByteBuf buffer, BedrockCodecHelper helper) {
        final LegacySetSlot slot = new LegacySetSlot();
        slot.setContainerEnum(helper.readContainerEnumName(buffer));
        slot.setSlots(helper.readByteArray(buffer, 89));
        return slot;
    }

    protected void writeItemUseInventoryTransaction(ByteBuf buffer, BedrockCodecHelper helper, ItemUseInventoryTransaction transaction) {
        VarInts.writeUnsignedInt(buffer, transaction.getActionType().ordinal());
        helper.writeVector3i(buffer, transaction.getPosition());
        VarInts.writeInt(buffer, transaction.getFace());
        VarInts.writeUnsignedInt(buffer, transaction.getSlot());
        helper.writeItem(buffer, transaction.getItem());
        helper.writeVector3f(buffer, transaction.getFromPosition());
        helper.writeVector3f(buffer, transaction.getClickPosition());
        VarInts.writeUnsignedInt(buffer, transaction.getTargetBlockId().getRuntimeId());
    }

    protected ItemUseInventoryTransaction readItemUseInventoryTransaction(ByteBuf buffer, BedrockCodecHelper helper) {
        final ItemUseInventoryTransaction transaction = new ItemUseInventoryTransaction();
        transaction.setActionType(ItemUseActionType.from(VarInts.readUnsignedInt(buffer)));
        transaction.setPosition(helper.readVector3i(buffer));
        transaction.setFace(VarInts.readInt(buffer));
        transaction.setSlot(VarInts.readUnsignedInt(buffer));
        transaction.setItem(helper.readItem(buffer));
        transaction.setFromPosition(helper.readVector3f(buffer));
        transaction.setClickPosition(helper.readVector3f(buffer));
        transaction.setTargetBlockId(helper.getBlockDefinitions().getDefinition(VarInts.readUnsignedInt(buffer)));
        return transaction;
    }

    protected void writeInventoryAction(ByteBuf buffer, BedrockCodecHelper helper, InventoryAction action) {
        this.writeInventorySource(buffer, action.getSource());
        VarInts.writeUnsignedInt(buffer, action.getSlot());
        helper.writeItem(buffer, action.getFromItem());
        helper.writeItem(buffer, action.getToItem());
    }

    protected InventoryAction readInventoryAction(ByteBuf buffer, BedrockCodecHelper helper) {
        final InventoryAction action = new InventoryAction();
        action.setSource(this.readInventorySource(buffer));
        action.setSlot(VarInts.readUnsignedInt(buffer));
        action.setFromItem(helper.readItem(buffer));
        action.setToItem(helper.readItem(buffer));
        return action;
    }

    protected void writeInventorySource(ByteBuf buffer, InventorySource inventorySource) {
        requireNonNull(inventorySource, "InventorySource was null");

        VarInts.writeUnsignedInt(buffer, inventorySource.getSourceType().ordinal());

        switch (inventorySource.getSourceType()) {
            case CONTAINER_INVENTORY:
            case NON_IMPLEMENTED_FEATURE_TODO:
                VarInts.writeInt(buffer, inventorySource.getContainerID());
                break;
            case WORLD_INTERACTION:
                VarInts.writeUnsignedInt(buffer, inventorySource.getBitFlags().ordinal());
                break;
        }
    }

    protected InventorySource readInventorySource(ByteBuf buffer) {
        final InventorySourceType type = InventorySourceType.from(VarInts.readUnsignedInt(buffer));
        final InventorySource source = new InventorySource();
        source.setSourceType(type);

        switch (type) {
            case CONTAINER_INVENTORY:
            case NON_IMPLEMENTED_FEATURE_TODO:
                source.setContainerID(VarInts.readInt(buffer));
                source.setBitFlags(InventorySourceFlags.NO_FLAG);
                break;
            case GLOBAL_INVENTORY:
            case CREATIVE_INVENTORY:
                source.setContainerID(ContainerId.NONE);
                source.setBitFlags(InventorySourceFlags.NO_FLAG);
                break;
            case WORLD_INTERACTION:
                source.setContainerID(ContainerId.NONE);
                source.setBitFlags(InventorySourceFlags.from(VarInts.readUnsignedInt(buffer)));
                break;
        }
        return source;
    }
}