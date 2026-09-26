package org.cloudburstmc.protocol.bedrock.data.biome;

public enum BiomeTemperatureCategory {
    MEDIUM,
    WARM,
    LUKEWARM,
    COLD,
    FROZEN;

    private static final BiomeTemperatureCategory[] VALUES = values();

    public static BiomeTemperatureCategory from(int ordinal) {
        if (ordinal >= 0 && ordinal < VALUES.length) {
            return VALUES[ordinal];
        }
        throw new UnsupportedOperationException("Detected unknown BiomeTemperatureCategory ID: " + ordinal);
    }
}