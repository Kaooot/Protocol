package org.cloudburstmc.protocol.bedrock.data.payload.map;

/**
 * @author Kaooot
 */
public enum MapItemTrackedActorType {

    ENTITY,
    BLOCK_ENTITY,
    OTHER;

    private static final MapItemTrackedActorType[] VALUES = values();

    public static MapItemTrackedActorType from(int ordinal) {
        if (ordinal >= 0 && ordinal < VALUES.length) {
            return VALUES[ordinal];
        }
        throw new UnsupportedOperationException("Detected unknown MapItemTrackedActorType ID: " + ordinal);
    }
}