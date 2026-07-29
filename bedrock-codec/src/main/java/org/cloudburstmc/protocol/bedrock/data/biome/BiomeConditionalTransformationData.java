package org.cloudburstmc.protocol.bedrock.data.biome;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import java.util.List;
import lombok.Data;

@Data
public class BiomeConditionalTransformationData {
  private final List<BiomeWeightedData> transformsInto = new ObjectArrayList<>();

  private int conditionJson;

  private int minPassingNeighbors;
}
