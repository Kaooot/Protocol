package org.cloudburstmc.protocol.bedrock.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BiomeConsolidatedFeatureData {
  private BiomeScatterParamData scatter;

  private int feature;

  private int identifier;

  private int pass;

  private boolean canUseInternalFeature;
}
