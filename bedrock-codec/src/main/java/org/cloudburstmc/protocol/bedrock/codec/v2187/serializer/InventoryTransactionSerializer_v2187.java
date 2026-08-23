package org.cloudburstmc.protocol.bedrock.codec.v2187.serializer;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.v1001.serializer.InventoryTransactionSerializer_v1001;
import org.cloudburstmc.protocol.bedrock.data.payload.inventory.transaction.InventoryTransaction;
import org.cloudburstmc.protocol.bedrock.data.payload.inventory.transaction.data.*;
import org.cloudburstmc.protocol.common.util.VarInts;

/**
 * @author Kaooot
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class InventoryTransactionSerializer_v2187 extends InventoryTransactionSerializer_v1001 {
    public static final InventoryTransactionSerializer_v2187 INSTANCE = new InventoryTransactionSerializer_v2187();

    @Override
    protected void writeInventoryTransactionVariant(ByteBuf buffer, BedrockCodecHelper helper, InventoryTransactionData transaction) {
        VarInts.writeUnsignedInt(buffer, transaction.getType().ordinal());
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
}