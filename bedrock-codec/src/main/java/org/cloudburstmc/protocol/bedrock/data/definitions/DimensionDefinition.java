package org.cloudburstmc.protocol.bedrock.data.definitions;

import lombok.Value;
import org.cloudburstmc.protocol.bedrock.data.world.GeneratorType;
import org.cloudburstmc.protocol.bedrock.data.world.DimensionType;

import java.util.UUID;

@Value
public class DimensionDefinition {

    String name;
    /**
     * maximumY before v2193
     * @since v2193: maximumY = minimumY + heightRange
     */
    int heightRange;
    int minimumY;
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
     * @since v2193
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