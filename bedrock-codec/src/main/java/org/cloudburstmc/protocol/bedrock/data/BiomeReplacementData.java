package org.cloudburstmc.protocol.bedrock.data;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import java.lang.Integer;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
