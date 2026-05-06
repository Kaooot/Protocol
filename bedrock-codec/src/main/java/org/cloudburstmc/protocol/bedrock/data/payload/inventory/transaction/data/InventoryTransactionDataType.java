package org.cloudburstmc.protocol.bedrock.data.payload.inventory.transaction.data;

/**
 * @author Kaooot
 */
public enum InventoryTransactionDataType {

    NORMAL,
    MISMATCH,
    ITEM_USE,
    ITEM_USE_ON_ACTOR,
    ITEM_RELEASE
}