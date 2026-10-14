package org.cloudburstmc.protocol.bedrock.codec.v1001.serializer;

import io.netty.buffer.ByteBuf;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.v407.serializer.InventoryTransactionSerializer_v407;
import org.cloudburstmc.protocol.bedrock.data.inventory.transaction.*;
import org.cloudburstmc.protocol.bedrock.packet.InventoryTransactionPacket;
import org.cloudburstmc.protocol.common.util.VarInts;

public class InventoryTransactionSerializer_v1001 extends InventoryTransactionSerializer_v407 {
    public static final InventoryTransactionSerializer_v1001 INSTANCE = new InventoryTransactionSerializer_v1001();

    protected InventoryTransactionSerializer_v1001() {
        this.inventoryTransactionVariant = this.inventoryTransactionVariant.toBuilder(
                        InventoryTransactionType::ordinal,
                        (buffer, helper, owner, value) ->
                                helper.writeOptionalNull(buffer, value, VarInts::writeUnsignedInt),
                        (buffer, helper, owner) -> helper.readOptional(buffer, 0, VarInts::readUnsignedInt)
                )
                .prefix(
                        (buffer, helper, owner, value) -> buffer.writeBoolean(true),
                        (buffer, helper, owner) -> {
                            buffer.readBoolean(); // transaction has value
                            return -1;
                        }
                )
                .build();
    }

    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, InventoryTransactionPacket packet) {
        this.writeLegacyRequestId(buffer, helper, packet.getLegacyRequestID());
        final boolean legacySetSlotsHasValue = packet.getLegacyRequestID().getID() < -1 && (packet.getLegacyRequestID().getID() & 1) == 0;
        buffer.writeBoolean(legacySetSlotsHasValue);
        if (legacySetSlotsHasValue) {
            helper.writeArray(buffer, packet.getLegacySetItemSlots(), this::writeLegacySetSlot);
        }
        this.inventoryTransactionVariant.write(buffer, helper, packet, packet.getTransaction());
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, InventoryTransactionPacket packet) {
        packet.setLegacyRequestID(this.readLegacyRequestId(buffer, helper));
        // negative, even values are valid
        final boolean legacySetSlotsHasValue = buffer.readBoolean();
        if (legacySetSlotsHasValue) {
            helper.readArray(buffer, packet.getLegacySetItemSlots(), this::readLegacySetSlot);
        }
        packet.setTransaction(this.inventoryTransactionVariant.read(buffer, helper, packet));
    }

    @Override
    protected void writeItemUseOnActorInventoryTransaction(ByteBuf buffer, BedrockCodecHelper helper, InventoryTransactionPacket packet, ItemUseOnActorInventoryTransaction transaction) {
        helper.writeInventoryTransactions(buffer, transaction.getActions());
        VarInts.writeUnsignedLong(buffer, transaction.getRuntimeId());
        VarInts.writeInt(buffer, transaction.getActionType().ordinal());
        VarInts.writeInt(buffer, transaction.getSlot());
        helper.writeNetworkItemStackDescriptor(buffer, transaction.getItem());
        helper.writeVector3f(buffer, transaction.getFromPosition());
        helper.writeVector3f(buffer, transaction.getHitPosition());
    }

    @Override
    protected ItemUseOnActorInventoryTransaction readItemUseOnActorInventoryTransaction(ByteBuf buffer, BedrockCodecHelper helper, InventoryTransactionPacket packet) {
        final InventoryTransaction actions = new InventoryTransaction();
        helper.readInventoryTransactions(buffer, actions);
        final ItemUseOnActorInventoryTransaction transaction = new ItemUseOnActorInventoryTransaction();
        transaction.setActions(actions);
        transaction.setRuntimeId(VarInts.readUnsignedLong(buffer));
        transaction.setActionType(ItemUseOnActorActionType.from(VarInts.readInt(buffer)));
        transaction.setSlot(VarInts.readInt(buffer));
        transaction.setItem(helper.readNetworkItemStackDescriptor(buffer));
        transaction.setFromPosition(helper.readVector3f(buffer));
        transaction.setHitPosition(helper.readVector3f(buffer));
        return transaction;
    }

    @Override
    protected void writeItemReleaseInventoryTransaction(ByteBuf buffer, BedrockCodecHelper helper, InventoryTransactionPacket packet, ItemReleaseInventoryTransaction transaction) {
        helper.writeInventoryTransactions(buffer, transaction.getActions());
        VarInts.writeInt(buffer, transaction.getActionType().ordinal());
        VarInts.writeInt(buffer, transaction.getSlot());
        helper.writeNetworkItemStackDescriptor(buffer, transaction.getItem());
        helper.writeVector3f(buffer, transaction.getFromPosition());
    }

    @Override
    protected ItemReleaseInventoryTransaction readItemReleaseInventoryTransaction(ByteBuf buffer, BedrockCodecHelper helper, InventoryTransactionPacket packet) {
        final InventoryTransaction actions = new InventoryTransaction();
        helper.readInventoryTransactions(buffer, actions);
        final ItemReleaseInventoryTransaction transaction = new ItemReleaseInventoryTransaction();
        transaction.setActions(actions);
        transaction.setActionType(ItemReleaseActionType.from(VarInts.readInt(buffer)));
        transaction.setSlot(VarInts.readInt(buffer));
        transaction.setItem(helper.readNetworkItemStackDescriptor(buffer));
        transaction.setFromPosition(helper.readVector3f(buffer));
        return transaction;
    }
}