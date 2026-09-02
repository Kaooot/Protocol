package org.cloudburstmc.protocol.bedrock.codec.v2207.serializer;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.v2192.serializer.InventoryTransactionSerializer_v2192;
import org.cloudburstmc.protocol.bedrock.data.HandSlot;
import org.cloudburstmc.protocol.bedrock.data.payload.inventory.transaction.ItemReleaseActionType;
import org.cloudburstmc.protocol.bedrock.data.payload.inventory.transaction.ItemUseOnActorActionType;
import org.cloudburstmc.protocol.bedrock.data.payload.inventory.transaction.data.ItemReleaseInventoryTransaction;
import org.cloudburstmc.protocol.bedrock.data.payload.inventory.transaction.data.ItemUseOnActorInventoryTransaction;
import org.cloudburstmc.protocol.common.util.VarInts;

/**
 * @author Kaooot
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class InventoryTransactionSerializer_v2207 extends InventoryTransactionSerializer_v2192 {
    public static final InventoryTransactionSerializer_v2207 INSTANCE = new InventoryTransactionSerializer_v2207();

    @Override
    protected void writeItemUseOnActorInventoryTransaction(ByteBuf buffer, BedrockCodecHelper helper, ItemUseOnActorInventoryTransaction transaction) {
        VarInts.writeUnsignedLong(buffer, transaction.getRuntimeId());
        VarInts.writeInt(buffer, transaction.getActionType().ordinal());
        VarInts.writeInt(buffer, transaction.getSlot());
        buffer.writeByte(transaction.getHand().ordinal());
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
        transaction.setHand(HandSlot.from(buffer.readUnsignedByte()));
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
        buffer.writeByte(transaction.getHand().ordinal());
    }

    @Override
    protected ItemReleaseInventoryTransaction readItemReleaseInventoryTransaction(ByteBuf buffer, BedrockCodecHelper helper) {
        final ItemReleaseInventoryTransaction transaction = new ItemReleaseInventoryTransaction();
        transaction.setActionType(ItemReleaseActionType.from(VarInts.readInt(buffer)));
        transaction.setSlot(VarInts.readInt(buffer));
        transaction.setItem(helper.readNetworkItemStackDescriptor(buffer));
        transaction.setFromPosition(helper.readVector3f(buffer));
        transaction.setHand(HandSlot.from(buffer.readUnsignedByte()));
        return transaction;
    }
}