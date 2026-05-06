package org.cloudburstmc.protocol.bedrock.data.payload.boss;

/**
 * @author Kaooot
 */
public enum BossBarOverlay {

    PROGRESS,
    NOTCHED_6,
    NOTCHED_10,
    NOTCHED_12,
    NOTCHED_20;

    private static final BossBarOverlay[] VALUES = values();

    public static BossBarOverlay from(int ordinal) {
        if (ordinal >= 0 && ordinal < VALUES.length) {
            return VALUES[ordinal];
        }
        throw new UnsupportedOperationException("Detected unknown BossBarOverlay ID: " + ordinal);
    }
}