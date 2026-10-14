package org.cloudburstmc.protocol.bedrock.codec.v2193.serializer;

import org.cloudburstmc.protocol.bedrock.codec.v1001.serializer.InventoryTransactionSerializer_v1001;
import org.cloudburstmc.protocol.bedrock.data.inventory.transaction.InventoryTransactionType;

public class InventoryTransactionSerializer_v2193 extends InventoryTransactionSerializer_v1001 {
    public static final InventoryTransactionSerializer_v2193 INSTANCE = new InventoryTransactionSerializer_v2193();

    protected InventoryTransactionSerializer_v2193() {
        this.inventoryTransactionVariant = this.inventoryTransactionVariant.toBuilder(InventoryTransactionType::ordinal)
                .removePrefix()
                .build();
    }
}