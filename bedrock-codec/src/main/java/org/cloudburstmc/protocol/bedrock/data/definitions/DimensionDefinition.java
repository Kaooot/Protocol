package org.cloudburstmc.protocol.bedrock.data.definitions;

import lombok.Value;
import org.cloudburstmc.protocol.bedrock.data.GeneratorType;
import org.cloudburstmc.protocol.bedrock.data.payload.common.DimensionType;

import java.util.UUID;

@Value
public class DimensionDefinition {
    String name;
    int heightMaximum;
    int heightMinimum;
    GeneratorType generatorType;
    /**
     * @since v975
     */
    DimensionType dimensionType;
    /**
     * @since v2168
     */
    UUID packId;
    /**
     * @since v2192
     */
    String defaultBiome;
    /**
     * @since v2207
     */
    int cloudHeight;
    /**
     * @since v2207
     */
    boolean renderClouds;
}
