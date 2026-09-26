package org.cloudburstmc.protocol.bedrock.data.world;

import lombok.Data;
import org.cloudburstmc.protocol.bedrock.data.biome.SpawnBiomeType;

@Data
public class SpawnSettings {

    /**
     * @since v407
     */
    private SpawnBiomeType spawnBiomeType;
    /**
     * @since v407
     */
    private String userDefinedBiomeName;
    private DimensionType dimension;
}