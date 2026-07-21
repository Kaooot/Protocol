package org.cloudburstmc.protocol.bedrock.data;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BiomeConditionalTransformationData {
  private final List<BiomeWeightedData> transformsInto = new ObjectArrayList<>();

  private int conditionJson;

  private int minPassingNeighbors;
}
