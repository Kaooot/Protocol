package org.cloudburstmc.protocol.bedrock.data.biome;

/**
 * @author Kaooot
 */
public enum VillageType {

    DESERT,
    ICE,
    SAVANNA,
    TAIGA,
    DEFAULT;

    private static final VillageType[] VALUES = values();

    public static VillageType from(int ordinal) {
        if (ordinal >= VALUES.length || ordinal < 0) {
            throw new UnsupportedOperationException("Detected unknown VillageType ID: " + ordinal);
        }
        return VALUES[ordinal];
    }
}