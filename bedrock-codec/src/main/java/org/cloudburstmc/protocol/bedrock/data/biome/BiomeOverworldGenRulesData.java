package org.cloudburstmc.protocol.bedrock.data.biome;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import java.util.List;
import lombok.Data;

@Data
public class BiomeOverworldGenRulesData {
  private final List<BiomeWeightedData> hillsTransformations = new ObjectArrayList<>();

  private final List<BiomeWeightedData> mutateTransformations = new ObjectArrayList<>();

  private final List<BiomeWeightedData> riverTransformations = new ObjectArrayList<>();

  private final List<BiomeWeightedData> shoreTransformations = new ObjectArrayList<>();

  private final List<BiomeConditionalTransformationData> preHillsEdge = new ObjectArrayList<>();

  private final List<BiomeConditionalTransformationData> postShoreEdge = new ObjectArrayList<>();

  private final List<BiomeWeightedTemperatureData> climate = new ObjectArrayList<>();
}
