package org.cloudburstmc.protocol.bedrock.data.world;

import lombok.Data;
import org.cloudburstmc.protocol.bedrock.annotation.Added;
import org.cloudburstmc.protocol.bedrock.data.biome.SpawnBiomeType;

@Data
public class SpawnSettings {

    @Added("1.16.0")
    private SpawnBiomeType spawnBiomeType;
    @Added("1.16.0")
    private String userDefinedBiomeName;
    private DimensionType dimension;
}