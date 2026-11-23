package org.cloudburstmc.protocol.bedrock.data.inventory;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum InventorySourceType {

    CONTAINER_INVENTORY(0),
    GLOBAL_INVENTORY(1),
    WORLD_INTERACTION(2),
    CREATIVE_INVENTORY(3),
    UNTRACKED_INTERACTION_UI(100),
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