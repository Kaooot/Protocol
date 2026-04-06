package org.cloudburstmc.protocol.bedrock.data.biome;

import lombok.Value;
import org.cloudburstmc.protocol.common.util.index.Indexable;

import java.util.List;

/**
 * @author Kaooot
 */
@Value
public class BiomeReplacementData {

    Indexable<String> biome;
    Indexable<String> dimension;
    Indexable<List<String>> targetBiomes;
    float amount;
    float noiseFrequencyScale;
    int replacementIndex;

    public String getBiome() {
        return this.biome.get();
    }

    public String getDimension() {
        return this.dimension.get();
    }

    public List<String> getTargetBiomes() {
        return this.targetBiomes.get();
    }
}