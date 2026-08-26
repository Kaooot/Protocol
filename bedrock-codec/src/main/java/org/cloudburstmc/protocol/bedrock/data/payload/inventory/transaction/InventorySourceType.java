package org.cloudburstmc.protocol.bedrock.data.payload.inventory.transaction;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @author Kaooot
 */
@Getter
@RequiredArgsConstructor
public enum InventorySourceType {

    CONTAINER_INVENTORY(0),
    GLOBAL_INVENTORY(1),
    WORLD_INTERACTION(2),
    CREATIVE_INVENTORY(3),
    NON_IMPLEMENTED_FEATURE_TODO(99999);

    private final int id;

    private static final InventorySourceType[] VALUES = values();

    public static InventorySourceType from(int ordinal) {
        for (InventorySourceType value : VALUES) {
            if (value.getId() == ordinal) {
                return value;
            }
        }
        throw new UnsupportedOperationException("Detected unknown InventorySourceType ID: " + ordinal);
    }
}