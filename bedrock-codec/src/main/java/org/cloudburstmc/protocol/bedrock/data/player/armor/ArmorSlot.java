package org.cloudburstmc.protocol.bedrock.data.player.armor;

import org.cloudburstmc.protocol.bedrock.annotation.Added;

/**
 * All available armor slots. 'Body' is used for horses etc
 */
@Added("1.21.111")
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