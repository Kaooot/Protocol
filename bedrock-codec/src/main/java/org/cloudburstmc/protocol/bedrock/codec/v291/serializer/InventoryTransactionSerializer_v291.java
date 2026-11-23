package org.cloudburstmc.protocol.bedrock.codec.v291.serializer;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.BedrockPacketSerializer;
import org.cloudburstmc.protocol.bedrock.data.inventory.InventoryMismatchData;
import org.cloudburstmc.protocol.bedrock.data.inventory.transaction.*;
import org.cloudburstmc.protocol.bedrock.packet.InventoryTransactionPacket;
import org.cloudburstmc.protocol.common.util.VarInts;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class InventoryTransactionSerializer_v291 implements BedrockPacketSerializer<InventoryTransactionPacket> {
    public static final InventoryTransactionSerializer_v291 INSTANCE = new InventoryTransactionSerializer_v291();

    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, InventoryTransactionPacket packet) {
        this.writeTransaction(buffer, helper, packet);
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, InventoryTransactionPacket packet) {
        this.readTransaction(buffer, helper, packet);
    }

    protected void writeTransaction(ByteBuf buffer, BedrockCodecHelper helper, InventoryTransactionPacket packet) {
        final InventoryTransactionData transaction = packet.getTransaction();
        final InventoryTransactionType type = transaction.getType();
        this.writeTransactionIndex(buffer, helper, packet, type);
        this.writeTransactionBody(buffer, helper, packet, type, transaction);
    }

    protected void writeTransactionIndex(ByteBuf buffer, BedrockCodecHelper helper, InventoryTransactionPacket packet, InventoryTransactionType type) {
        VarInts.writeUnsignedInt(buffer, type.ordinal());
    }

    protected void writeTransactionBody(ByteBuf buffer, BedrockCodecHelper helper, InventoryTransactionPacket packet, InventoryTransactionType type, Object transaction) {
        switch (type) {
            case NORMAL:
                this.writeNormalTransactionData(buffer, helper, packet, (NormalTransactionData) transaction);
                break;
            case MISMATCH:
                this.writeInventoryMismatchData(buffer, helper, packet, (InventoryMismatchData) transaction);
                break;
            case ITEM_USE:
                helper.writeItemUseInventoryTransaction(buffer, (ItemUseInventoryTransaction) transaction);
                break;
            case ITEM_USE_ON_ACTOR:
                this.writeItemUseOnActorInventoryTransaction(buffer, helper, packet, (ItemUseOnActorInventoryTransaction) transaction);
                break;
            case ITEM_RELEASE:
                this.writeItemReleaseInventoryTransaction(buffer, helper, packet, (ItemReleaseInventoryTransaction) transaction);
                break;
        }
    }

    protected void readTransaction(ByteBuf buffer, BedrockCodecHelper helper, InventoryTransactionPacket packet) {
        final InventoryTransactionType type = this.readTransactionIndex(buffer, helper, packet);
        packet.setTransaction(this.readTransactionBody(buffer, helper, packet, type));
    }

    protected InventoryTransactionType readTransactionIndex(ByteBuf buffer, BedrockCodecHelper helper, InventoryTransactionPacket packet) {
        return InventoryTransactionType.values()[VarInts.readUnsignedInt(buffer)];
    }

    protected InventoryTransactionData readTransactionBody(ByteBuf buffer, BedrockCodecHelper helper, InventoryTransactionPacket packet, InventoryTransactionType type) {
        switch (type) {
            case NORMAL:
                return this.readNormalTransactionData(buffer, helper, packet);
            case MISMATCH:
                return this.readInventoryMismatchData(buffer, helper, packet);
            case ITEM_USE:
                return helper.readItemUseInventoryTransaction(buffer);
            case ITEM_USE_ON_ACTOR:
                return this.readItemUseOnActorInventoryTransaction(buffer, helper, packet);
            case ITEM_RELEASE:
                return this.readItemReleaseInventoryTransaction(buffer, helper, packet);
        }
        return null;
    }

    protected void writeNormalTransactionData(ByteBuf buffer, BedrockCodecHelper helper, InventoryTransactionPacket packet, NormalTransactionData data) {
        helper.writeInventoryTransactions(buffer, data.getActions());
    }

    protected NormalTransactionData readNormalTransactionData(ByteBuf buffer, BedrockCodecHelper helper, InventoryTransactionPacket packet) {
        final InventoryTransaction actions = new InventoryTransaction();
        helper.readInventoryTransactions(buffer, actions);
        final NormalTransactionData data = new NormalTransactionData();
        data.setActions(actions);
        return data;
    }

    protected void writeInventoryMismatchData(ByteBuf buffer, BedrockCodecHelper helper, InventoryTransactionPacket packet, InventoryMismatchData data) {
        helper.writeInventoryTransactions(buffer, data.getActions());
    }

    protected InventoryMismatchData readInventoryMismatchData(ByteBuf buffer, BedrockCodecHelper helper, InventoryTransactionPacket packet) {
        final InventoryTransaction actions = new InventoryTransaction();
        helper.readInventoryTransactions(buffer, actions);
        final InventoryMismatchData data = new InventoryMismatchData();
        data.setActions(actions);
        return data;
    }

    protected void writeItemUseOnActorInventoryTransaction(ByteBuf buffer, BedrockCodecHelper helper, InventoryTransactionPacket packet, ItemUseOnActorInventoryTransaction transaction) {
        helper.writeInventoryTransactions(buffer, transaction.getActions());
        VarInts.writeUnsignedLong(buffer, transaction.getRuntimeId());
        VarInts.writeUnsignedInt(buffer, transaction.getActionType().ordinal());
        VarInts.writeInt(buffer, transaction.getSlot());
        helper.writeItem(buffer, transaction.getItem());
        helper.writeVector3f(buffer, transaction.getFromPosition());
        helper.writeVector3f(buffer, transaction.getHitPosition());
    }

    protected ItemUseOnActorInventoryTransaction readItemUseOnActorInventoryTransaction(ByteBuf buffer, BedrockCodecHelper helper, InventoryTransactionPacket packet) {
        final InventoryTransaction actions = new InventoryTransaction();
        helper.readInventoryTransactions(buffer, actions);
        final ItemUseOnActorInventoryTransaction transaction = new ItemUseOnActorInventoryTransaction();
        transaction.setActions(actions);
        transaction.setRuntimeId(VarInts.readUnsignedLong(buffer));
        transaction.setActionType(ItemUseOnActorActionType.from(VarInts.readUnsignedInt(buffer)));
        transaction.setSlot(VarInts.readInt(buffer));
        transaction.setItem(helper.readItem(buffer));
        transaction.setFromPosition(helper.readVector3f(buffer));
        transaction.setHitPosition(helper.readVector3f(buffer));
        return transaction;
    }

    protected void writeItemReleaseInventoryTransaction(ByteBuf buffer, BedrockCodecHelper helper, InventoryTransactionPacket packet, ItemReleaseInventoryTransaction transaction) {
        helper.writeInventoryTransactions(buffer, transaction.getActions());
        VarInts.writeUnsignedInt(buffer, transaction.getActionType().ordinal());
        VarInts.writeInt(buffer, transaction.getSlot());
        helper.writeItem(buffer, transaction.getItem());
        helper.writeVector3f(buffer, transaction.getFromPosition());
    }

    protected ItemReleaseInventoryTransaction readItemReleaseInventoryTransaction(ByteBuf buffer, BedrockCodecHelper helper, InventoryTransactionPacket packet) {
        final InventoryTransaction actions = new InventoryTransaction();
        helper.readInventoryTransactions(buffer, actions);
        final ItemReleaseInventoryTransaction transaction = new ItemReleaseInventoryTransaction();
        transaction.setActions(actions);
        transaction.setActionType(ItemReleaseActionType.from(VarInts.readUnsignedInt(buffer)));
        transaction.setSlot(VarInts.readInt(buffer));
        transaction.setItem(helper.readItem(buffer));
        transaction.setFromPosition(helper.readVector3f(buffer));
        return transaction;
    }
}