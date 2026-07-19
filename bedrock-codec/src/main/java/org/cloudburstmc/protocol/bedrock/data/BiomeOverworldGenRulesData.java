package org.cloudburstmc.protocol.bedrock.data;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Auto generated from 1.26.40-beta.31 (v2168)
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BiomeOverworldGenRulesData {
  private final List<BiomeWeightedData> hillstransformations = new ObjectArrayList<>();

  private final List<BiomeWeightedData> mutatetransformations = new ObjectArrayList<>();

  private final List<BiomeWeightedData> rivertransformations = new ObjectArrayList<>();

  private final List<BiomeWeightedData> shoretransformations = new ObjectArrayList<>();

  private final List<BiomeConditionalTransformationData> prehillsedge = new ObjectArrayList<>();

  private final List<BiomeConditionalTransformationData> postshoreedge = new ObjectArrayList<>();

  private final List<BiomeWeightedTemperatureData> climate = new ObjectArrayList<>();
}
