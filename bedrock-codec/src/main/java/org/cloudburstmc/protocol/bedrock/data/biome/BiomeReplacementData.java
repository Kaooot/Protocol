package org.cloudburstmc.protocol.bedrock.data.biome;

import lombok.Value;

import java.util.List;

/**
 * @author Kaooot
 */
@Value
public class BiomeReplacementData {

    int biome;
    int dimension;
    List<Short> targetBiomes;
    float amount;
    float noiseFrequencyScale;
    int replacementIndex;
}