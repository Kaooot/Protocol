package org.cloudburstmc.protocol.bedrock.codec.v291.serializer;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.BedrockPacketSerializer;
import org.cloudburstmc.protocol.bedrock.data.payload.inventory.transaction.InventoryTransaction;
import org.cloudburstmc.protocol.bedrock.data.payload.inventory.transaction.ItemReleaseActionType;
import org.cloudburstmc.protocol.bedrock.data.payload.inventory.transaction.ItemUseActionType;
import org.cloudburstmc.protocol.bedrock.data.payload.inventory.transaction.ItemUseOnActorActionType;
import org.cloudburstmc.protocol.bedrock.data.payload.inventory.transaction.data.*;
import org.cloudburstmc.protocol.bedrock.packet.InventoryTransactionPacket;
import org.cloudburstmc.protocol.common.util.VarInts;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class InventoryTransactionSerializer_v291 implements BedrockPacketSerializer<InventoryTransactionPacket> {
    public static final InventoryTransactionSerializer_v291 INSTANCE = new InventoryTransactionSerializer_v291();

    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, InventoryTransactionPacket packet) {
        this.writeInventoryTransactionVariant(buffer, helper, packet.getTransaction());
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, InventoryTransactionPacket packet) {
        packet.setTransaction(this.readInventoryTransactionVariant(buffer, helper));
    }

    protected void writeInventoryTransactionVariant(ByteBuf buffer, BedrockCodecHelper helper, InventoryTransactionData transaction) {
        VarInts.writeUnsignedInt(buffer, transaction.getType().ordinal());
        helper.writeInventoryTransactions(buffer, transaction.getActions());

        switch (transaction.getType()) {
            case NORMAL:
            case MISMATCH:
                break;
            case ITEM_USE:
                this.writeItemUseInventoryTransaction(buffer, helper, (ItemUseInventoryTransaction) transaction);
                break;
            case ITEM_USE_ON_ACTOR:
                this.writeItemUseOnActorInventoryTransaction(buffer, helper, (ItemUseOnActorInventoryTransaction) transaction);
                break;
            case ITEM_RELEASE:
                this.writeItemReleaseInventoryTransaction(buffer, helper, (ItemReleaseInventoryTransaction) transaction);
                break;
        }
    }

    protected InventoryTransactionData readInventoryTransactionVariant(ByteBuf buffer, BedrockCodecHelper helper) {
        final InventoryTransactionDataType type = InventoryTransactionDataType.values()[VarInts.readUnsignedInt(buffer)];
        final InventoryTransaction actions = new InventoryTransaction();

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
                data = this.readItemUseInventoryTransaction(buffer, helper);
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

    protected void writeItemUseInventoryTransaction(ByteBuf buffer, BedrockCodecHelper helper, ItemUseInventoryTransaction transaction) {
        VarInts.writeUnsignedInt(buffer, transaction.getActionType().ordinal());
        helper.writeVector3i(buffer, transaction.getPosition());
        VarInts.writeInt(buffer, transaction.getFace());
        VarInts.writeInt(buffer, transaction.getSlot());
        helper.writeItem(buffer, transaction.getItem());
        helper.writeVector3f(buffer, transaction.getFromPosition());
        helper.writeVector3f(buffer, transaction.getClickPosition());
    }

    protected ItemUseInventoryTransaction readItemUseInventoryTransaction(ByteBuf buffer, BedrockCodecHelper helper) {
        final ItemUseInventoryTransaction transaction = new ItemUseInventoryTransaction();
        transaction.setActionType(ItemUseActionType.from(VarInts.readUnsignedInt(buffer)));
        transaction.setPosition(helper.readVector3i(buffer));
        transaction.setFace(VarInts.readInt(buffer));
        transaction.setSlot(VarInts.readInt(buffer));
        transaction.setItem(helper.readItem(buffer));
        transaction.setFromPosition(helper.readVector3f(buffer));
        transaction.setClickPosition(helper.readVector3f(buffer));
        return transaction;
    }

    protected void writeItemUseOnActorInventoryTransaction(ByteBuf buffer, BedrockCodecHelper helper, ItemUseOnActorInventoryTransaction transaction) {
        VarInts.writeUnsignedLong(buffer, transaction.getRuntimeId());
        VarInts.writeUnsignedInt(buffer, transaction.getActionType().ordinal());
        VarInts.writeInt(buffer, transaction.getSlot());
        helper.writeItem(buffer, transaction.getItem());
        helper.writeVector3f(buffer, transaction.getFromPosition());
        helper.writeVector3f(buffer, transaction.getHitPosition());
    }

    protected ItemUseOnActorInventoryTransaction readItemUseOnActorInventoryTransaction(ByteBuf buffer, BedrockCodecHelper helper) {
        final ItemUseOnActorInventoryTransaction transaction = new ItemUseOnActorInventoryTransaction();
        transaction.setRuntimeId(VarInts.readUnsignedLong(buffer));
        transaction.setActionType(ItemUseOnActorActionType.from(VarInts.readUnsignedInt(buffer)));
        transaction.setSlot(VarInts.readInt(buffer));
        transaction.setItem(helper.readItem(buffer));
        transaction.setFromPosition(helper.readVector3f(buffer));
        transaction.setHitPosition(helper.readVector3f(buffer));
        return transaction;
    }

    protected void writeItemReleaseInventoryTransaction(ByteBuf buffer, BedrockCodecHelper helper, ItemReleaseInventoryTransaction transaction) {
        VarInts.writeUnsignedInt(buffer, transaction.getActionType().ordinal());
        VarInts.writeInt(buffer, transaction.getSlot());
        helper.writeItem(buffer, transaction.getItem());
        helper.writeVector3f(buffer, transaction.getFromPosition());
    }

    protected ItemReleaseInventoryTransaction readItemReleaseInventoryTransaction(ByteBuf buffer, BedrockCodecHelper helper) {
        final ItemReleaseInventoryTransaction transaction = new ItemReleaseInventoryTransaction();
        transaction.setActionType(ItemReleaseActionType.from(VarInts.readUnsignedInt(buffer)));
        transaction.setSlot(VarInts.readInt(buffer));
        transaction.setItem(helper.readItem(buffer));
        transaction.setFromPosition(helper.readVector3f(buffer));
        return transaction;
    }
}