package org.cloudburstmc.protocol.bedrock.data;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BiomeLegacyWorldGenRulesData {
  private final List<BiomeConditionalTransformationData> legacyPreHillsEdge = new ObjectArrayList<>();
}
