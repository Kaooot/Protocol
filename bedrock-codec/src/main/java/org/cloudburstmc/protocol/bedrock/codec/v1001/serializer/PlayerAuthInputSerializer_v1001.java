package org.cloudburstmc.protocol.bedrock.codec.v1001.serializer;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.v944.serializer.PlayerAuthInputSerializer_v944;
import org.cloudburstmc.protocol.bedrock.data.inventory.ContainerId;
import org.cloudburstmc.protocol.bedrock.data.payload.inventory.net.ItemStackLegacyRequestId;
import org.cloudburstmc.protocol.bedrock.data.payload.inventory.transaction.*;
import org.cloudburstmc.protocol.bedrock.data.payload.inventory.transaction.data.ItemUseInventoryTransaction;
import org.cloudburstmc.protocol.bedrock.data.payload.inventory.transaction.data.PackedLegacyItemUseInventoryTransaction;
import org.cloudburstmc.protocol.common.util.VarInts;

import static java.util.Objects.requireNonNull;

/**
 * @author Kaooot
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PlayerAuthInputSerializer_v1001 extends PlayerAuthInputSerializer_v944 {
    public static final PlayerAuthInputSerializer_v1001 INSTANCE = new PlayerAuthInputSerializer_v1001();

    @Override
    protected void writePackedLegacyItemUseInventoryTransaction(ByteBuf buffer, BedrockCodecHelper helper, PackedLegacyItemUseInventoryTransaction transaction) {
        VarInts.writeInt(buffer, transaction.getLegacyRequestID().getID());
        if (transaction.getLegacyRequestID().getID() < -1 && (transaction.getLegacyRequestID().getID() & 1) == 0) {
            helper.writeArray(buffer, transaction.getLegacySetItemSlots(), (buf, codecHelper, slot) -> {
                codecHelper.writeContainerEnumName(buf, slot.getContainerEnum());
                codecHelper.writeByteArray(buf, slot.getSlots());
            });
        }
        helper.writeArray(buffer, transaction.getActions(), this::writeInventoryAction);
        this.writeItemUseInventoryTransaction(buffer, helper, transaction.getTransaction());
    }

    @Override
    protected PackedLegacyItemUseInventoryTransaction readPackedLegacyItemUseInventoryTransaction(ByteBuf buffer, BedrockCodecHelper helper) {
        final PackedLegacyItemUseInventoryTransaction transaction = new PackedLegacyItemUseInventoryTransaction();
        transaction.setLegacyRequestID(new ItemStackLegacyRequestId(VarInts.readInt(buffer)));
        if (transaction.getLegacyRequestID().getID() < -1 && (transaction.getLegacyRequestID().getID() & 1) == 0) {
            helper.readArray(buffer, transaction.getLegacySetItemSlots(), (buf, codecHelper) -> {
                final LegacySetSlot slot = new LegacySetSlot();
                slot.setContainerEnum(codecHelper.readContainerEnumName(buf));
                slot.setSlots(codecHelper.readByteArray(buf));
                return slot;
            });
        }
        helper.readArray(buffer, transaction.getActions(), this::readInventoryAction, helper.getEncodingSettings().maxInventoryActionsOrRequests());
        transaction.setTransaction(this.readItemUseInventoryTransaction(buffer, helper));
        return transaction;
    }

    @Override
    protected ItemUseInventoryTransaction readItemUseInventoryTransaction(ByteBuf buffer, BedrockCodecHelper helper) {
        final ItemUseInventoryTransaction transaction = new ItemUseInventoryTransaction();
        transaction.setActionType(ItemUseActionType.from(VarInts.readUnsignedInt(buffer)));
        transaction.setTriggerType(ItemUseTriggerType.from(buffer.readUnsignedByte()));
        transaction.setPosition(helper.readVector3i(buffer));
        transaction.setFace(VarInts.readInt(buffer));
        transaction.setSlot(VarInts.readInt(buffer));
        transaction.setItem(helper.readItem(buffer));
        transaction.setFromPosition(helper.readVector3f(buffer));
        transaction.setClickPosition(helper.readVector3f(buffer));
        transaction.setTargetBlockId(helper.getBlockDefinitions().getDefinition(VarInts.readUnsignedInt(buffer)));
        transaction.setClientInteractPrediction(ItemUsePredictedResult.from(buffer.readUnsignedByte()));
        transaction.setClientCooldownState(ItemUseClientCooldownState.from(buffer.readUnsignedByte()));
        return transaction;
    }

    protected void writeInventoryAction(ByteBuf buffer, BedrockCodecHelper helper, InventoryAction action) {
        this.writeInventorySource(buffer, action.getSource());
        VarInts.writeUnsignedInt(buffer, action.getSlot());
        helper.writeItem(buffer, action.getFromItem());
        helper.writeItem(buffer, action.getToItem());
    }

    public InventoryAction readInventoryAction(ByteBuf buffer, BedrockCodecHelper helper) {
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