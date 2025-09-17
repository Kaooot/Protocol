package org.cloudburstmc.protocol.bedrock.data;

/**
 * @author Kaooot
 */
public enum ArmorSlot {

    HEAD,
    TORSO,
    LEGS,
    FEET,
    BODY;

    private static final ArmorSlot[] VALUES = values();

    public static ArmorSlot from(int ordinal) {
        if (ordinal >= 0 && ordinal < VALUES.length) {
            return VALUES[ordinal];
        }
        throw new UnsupportedOperationException("Detected unknown ArmorSlot ID: " + ordinal);
    }
}