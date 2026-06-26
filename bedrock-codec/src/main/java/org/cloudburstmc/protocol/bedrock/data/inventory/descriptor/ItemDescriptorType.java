package org.cloudburstmc.protocol.bedrock.data.inventory.descriptor;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ItemDescriptorType {
    EMPTY("empty"),
    NAME("name"),
    MOLANG("molang"),
    ITEM_TAG("item_tag"),
    DEFERRED("deferred"),
    /**
     * @since v575
     */
    COMPLEX_ALIAS("complex_alias");

    private final String id;

    private static final ItemDescriptorType[] VALUES = values();

    public static ItemDescriptorType from(String value) {
        for (ItemDescriptorType itemDescriptorType : VALUES) {
            if (itemDescriptorType.getId().equalsIgnoreCase(value)) {
                return itemDescriptorType;
            }
        }
        throw new UnsupportedOperationException("Detected unknown ItemDescriptorType ID: " + value);
    }

    public static ItemDescriptorType from(int ordinal) {
        if (ordinal >= 0 && ordinal < VALUES.length) {
            return VALUES[ordinal];
        }
        throw new UnsupportedOperationException("Detected unknown ItemDescriptorType ID: " + ordinal);
    }
}