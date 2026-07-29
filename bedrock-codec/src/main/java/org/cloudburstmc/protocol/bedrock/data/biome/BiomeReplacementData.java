package org.cloudburstmc.protocol.bedrock.data.biome;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import java.lang.Integer;
import java.util.List;
import lombok.Data;

@Data
public class BiomeReplacementData {
  private int replacementBiome;

  private int dimension;

  private final List<Integer> targetBiomes = new ObjectArrayList<>();

  private float amount;

  private float noiseFrequencyScale;

  private int replacementIndex;
}
