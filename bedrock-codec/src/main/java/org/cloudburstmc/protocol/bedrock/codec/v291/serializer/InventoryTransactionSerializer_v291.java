package org.cloudburstmc.protocol.bedrock.codec.v291.serializer;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.BedrockPacketSerializer;
import org.cloudburstmc.protocol.bedrock.codec.VariantCodec;
import org.cloudburstmc.protocol.bedrock.data.inventory.InventoryMismatchData;
import org.cloudburstmc.protocol.bedrock.data.inventory.transaction.*;
import org.cloudburstmc.protocol.bedrock.packet.InventoryTransactionPacket;
import org.cloudburstmc.protocol.common.util.VarInts;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class InventoryTransactionSerializer_v291 implements BedrockPacketSerializer<InventoryTransactionPacket> {
    public static final InventoryTransactionSerializer_v291 INSTANCE = new InventoryTransactionSerializer_v291();

    protected VariantCodec<InventoryTransactionPacket> inventoryTransactionVariant = VariantCodec.<InventoryTransactionType, InventoryTransactionPacket>builder(InventoryTransactionType::ordinal)
            .add(
                    InventoryTransactionType.NORMAL,
                    NormalTransactionData.class,
                    this::writeNormalTransactionData,
                    this::readNormalTransactionData
            )
            .add(
                    InventoryTransactionType.MISMATCH,
                    InventoryMismatchData.class,
                    this::writeInventoryMismatchData,
                    this::readInventoryMismatchData
            )
            .add(
                    InventoryTransactionType.ITEM_USE,
                    ItemUseInventoryTransaction.class,
                    (buffer, helper, owner, value) -> helper.writeItemUseInventoryTransaction(buffer, value),
                    (buffer, helper, owner) -> helper.readItemUseInventoryTransaction(buffer)
            )
            .add(
                    InventoryTransactionType.ITEM_USE_ON_ACTOR,
                    ItemUseOnActorInventoryTransaction.class,
                    this::writeItemUseOnActorInventoryTransaction,
                    this::readItemUseOnActorInventoryTransaction
            )
            .add(
                    InventoryTransactionType.ITEM_RELEASE,
                    ItemReleaseInventoryTransaction.class,
                    this::writeItemReleaseInventoryTransaction,
                    this::readItemReleaseInventoryTransaction
            )
            .build();

    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, InventoryTransactionPacket packet) {
        this.inventoryTransactionVariant.write(buffer, helper, packet, packet.getTransaction());
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, InventoryTransactionPacket packet) {
        packet.setTransaction(this.inventoryTransactionVariant.read(buffer, helper, packet));
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