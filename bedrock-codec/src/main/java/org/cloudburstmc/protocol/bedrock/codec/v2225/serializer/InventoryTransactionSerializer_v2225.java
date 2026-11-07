package org.cloudburstmc.protocol.bedrock.codec.v2225.serializer;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.v2193.serializer.InventoryTransactionSerializer_v2193;
import org.cloudburstmc.protocol.bedrock.data.inventory.transaction.*;
import org.cloudburstmc.protocol.bedrock.data.player.HandSlot;
import org.cloudburstmc.protocol.bedrock.packet.InventoryTransactionPacket;
import org.cloudburstmc.protocol.common.util.VarInts;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class InventoryTransactionSerializer_v2225 extends InventoryTransactionSerializer_v2193 {
    public static final InventoryTransactionSerializer_v2225 INSTANCE = new InventoryTransactionSerializer_v2225();

    @Override
    protected void writeItemUseOnActorInventoryTransaction(ByteBuf buffer, BedrockCodecHelper helper, InventoryTransactionPacket packet, ItemUseOnActorInventoryTransaction transaction) {
        helper.writeInventoryTransactions(buffer, transaction.getActions());
        VarInts.writeUnsignedLong(buffer, transaction.getRuntimeId());
        VarInts.writeInt(buffer, transaction.getActionType().ordinal());
        VarInts.writeInt(buffer, transaction.getSlot());
        buffer.writeByte(transaction.getHand().ordinal());
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
        transaction.setHand(HandSlot.from(buffer.readUnsignedByte()));
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
        buffer.writeByte(transaction.getHand().ordinal());
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
        transaction.setHand(HandSlot.from(buffer.readUnsignedByte()));
        return transaction;
    }
}