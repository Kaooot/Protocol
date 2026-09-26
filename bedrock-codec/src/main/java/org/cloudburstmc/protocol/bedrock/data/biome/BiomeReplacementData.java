package org.cloudburstmc.protocol.bedrock.data.biome;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BiomeReplacementData {

    private int replacementBiome;
    private int dimension;
    private final List<Integer> targetBiomes = new ObjectArrayList<>();
    private float amount;
    private float noiseFrequencyScale;
    private int replacementIndex;
}