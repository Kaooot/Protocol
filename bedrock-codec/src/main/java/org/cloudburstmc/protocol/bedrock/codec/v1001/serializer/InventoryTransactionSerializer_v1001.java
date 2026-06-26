package org.cloudburstmc.protocol.bedrock.codec.v1001.serializer;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.v944.serializer.InventoryTransactionSerializer_v944;
import org.cloudburstmc.protocol.bedrock.data.payload.inventory.transaction.InventoryTransaction;
import org.cloudburstmc.protocol.bedrock.data.payload.inventory.transaction.ItemReleaseActionType;
import org.cloudburstmc.protocol.bedrock.data.payload.inventory.transaction.ItemUseOnActorActionType;
import org.cloudburstmc.protocol.bedrock.data.payload.inventory.transaction.data.*;
import org.cloudburstmc.protocol.bedrock.packet.InventoryTransactionPacket;
import org.cloudburstmc.protocol.common.util.VarInts;

/**
 * @author Kaooot
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class InventoryTransactionSerializer_v1001 extends InventoryTransactionSerializer_v944 {
    public static final InventoryTransactionSerializer_v1001 INSTANCE = new InventoryTransactionSerializer_v1001();

    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, InventoryTransactionPacket packet) {
        this.writeLegacyRequestId(buffer, helper, packet.getLegacyRequestID());
        final boolean legacySetSlotsHasValue = packet.getLegacyRequestID().getID() < -1 && (packet.getLegacyRequestID().getID() & 1) == 0;
        buffer.writeBoolean(legacySetSlotsHasValue);
        if (legacySetSlotsHasValue) {
            helper.writeArray(buffer, packet.getLegacySetItemSlots(), this::writeLegacySetSlot);
        }
        this.writeInventoryTransactionVariant(buffer, helper, packet.getTransaction());
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, InventoryTransactionPacket packet) {
        packet.setLegacyRequestID(this.readLegacyRequestId(buffer, helper));
        // negative, even values are valid
        final boolean legacySetSlotsHasValue = buffer.readBoolean();
        if (legacySetSlotsHasValue) {
            helper.readArray(buffer, packet.getLegacySetItemSlots(), this::readLegacySetSlot);
        }
        packet.setTransaction(this.readInventoryTransactionVariant(buffer, helper));
    }

    protected void writeInventoryTransactionVariant(ByteBuf buffer, BedrockCodecHelper helper, InventoryTransactionData transaction) {
        helper.writeOptionalNull(buffer, transaction.getType().ordinal(), VarInts::writeUnsignedInt);
        buffer.writeBoolean(true); // transactionDataHasValue
        helper.writeInventoryTransactions(buffer, transaction.getActions());
        switch (transaction.getType()) {
            case ITEM_USE:
                helper.writeItemUseInventoryTransaction(buffer, ((ItemUseInventoryTransaction) transaction));
                break;
            case ITEM_USE_ON_ACTOR:
                this.writeItemUseOnActorInventoryTransaction(buffer, helper, ((ItemUseOnActorInventoryTransaction) transaction));
                break;
            case ITEM_RELEASE:
                this.writeItemReleaseInventoryTransaction(buffer, helper, ((ItemReleaseInventoryTransaction) transaction));
                break;
        }
    }

    protected InventoryTransactionData readInventoryTransactionVariant(ByteBuf buffer, BedrockCodecHelper helper) {
        final int ordinal = helper.readOptional(buffer, 0, VarInts::readUnsignedInt);
        final InventoryTransactionDataType type = InventoryTransactionDataType.values()[ordinal];
        final InventoryTransaction actions = new InventoryTransaction();
        final boolean transactionDataHasValue = buffer.readBoolean();

        if (transactionDataHasValue) {
            helper.readInventoryTransactions(buffer, actions);
            final InventoryTransactionData data;
            switch (type) {
                case NORMAL:
                    data = new NormalTransactionData();
                    break;
                case MISMATCH:
                    data = new InventoryMismatchData();
                    break;
                case ITEM_USE:
                    data = helper.readItemUseInventoryTransaction(buffer);
                    break;
                case ITEM_USE_ON_ACTOR:
                    data = this.readItemUseOnActorInventoryTransaction(buffer, helper);
                    break;
                case ITEM_RELEASE:
                    data = this.readItemReleaseInventoryTransaction(buffer, helper);
                    break;
                default:
                    throw new IllegalStateException("Received invalid InventoryTransactionDataType");
            }
            data.setActions(actions);
            return data;
        }
        return null;
    }

    @Override
    protected void writeItemUseOnActorInventoryTransaction(ByteBuf buffer, BedrockCodecHelper helper, ItemUseOnActorInventoryTransaction transaction) {
        VarInts.writeUnsignedLong(buffer, transaction.getRuntimeId());
        VarInts.writeInt(buffer, transaction.getActionType().ordinal());
        VarInts.writeInt(buffer, transaction.getSlot());
        helper.writeNetworkItemStackDescriptor(buffer, transaction.getItem());
        helper.writeVector3f(buffer, transaction.getFromPosition());
        helper.writeVector3f(buffer, transaction.getHitPosition());
    }

    @Override
    protected ItemUseOnActorInventoryTransaction readItemUseOnActorInventoryTransaction(ByteBuf buffer, BedrockCodecHelper helper) {
        final ItemUseOnActorInventoryTransaction transaction = new ItemUseOnActorInventoryTransaction();
        transaction.setRuntimeId(VarInts.readUnsignedLong(buffer));
        transaction.setActionType(ItemUseOnActorActionType.from(VarInts.readInt(buffer)));
        transaction.setSlot(VarInts.readInt(buffer));
        transaction.setItem(helper.readNetworkItemStackDescriptor(buffer));
        transaction.setFromPosition(helper.readVector3f(buffer));
        transaction.setHitPosition(helper.readVector3f(buffer));
        return transaction;
    }

    @Override
    protected void writeItemReleaseInventoryTransaction(ByteBuf buffer, BedrockCodecHelper helper, ItemReleaseInventoryTransaction transaction) {
        VarInts.writeInt(buffer, transaction.getActionType().ordinal());
        VarInts.writeInt(buffer, transaction.getSlot());
        helper.writeNetworkItemStackDescriptor(buffer, transaction.getItem());
        helper.writeVector3f(buffer, transaction.getFromPosition());
    }

    @Override
    protected ItemReleaseInventoryTransaction readItemReleaseInventoryTransaction(ByteBuf buffer, BedrockCodecHelper helper) {
        final ItemReleaseInventoryTransaction transaction = new ItemReleaseInventoryTransaction();
        transaction.setActionType(ItemReleaseActionType.from(VarInts.readInt(buffer)));
        transaction.setSlot(VarInts.readInt(buffer));
        transaction.setItem(helper.readNetworkItemStackDescriptor(buffer));
        transaction.setFromPosition(helper.readVector3f(buffer));
        return transaction;
    }
}