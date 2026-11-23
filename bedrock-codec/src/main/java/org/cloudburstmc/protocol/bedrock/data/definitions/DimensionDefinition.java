package org.cloudburstmc.protocol.bedrock.data.definitions;

import lombok.Value;
import org.cloudburstmc.protocol.bedrock.annotation.Added;
import org.cloudburstmc.protocol.bedrock.data.world.GeneratorType;
import org.cloudburstmc.protocol.bedrock.data.world.DimensionType;

import java.util.UUID;

@Value
public class DimensionDefinition {

    String name;
    /**
     * maximumY before v2193
     */
    int heightRange;
    int minimumY;
    GeneratorType generatorType;
    @Added("26.10")
    DimensionType dimensionType;
    @Added("26.40")
    UUID packId;
    @Added("26.50")
    String defaultBiome;
    @Added("26.60")
    int cloudHeight;
    @Added("26.60")
    boolean renderClouds;

    /**
     * @since v2193
     */
    public int getMaximumY() {
        return this.minimumY + this.heightRange;
    }
}