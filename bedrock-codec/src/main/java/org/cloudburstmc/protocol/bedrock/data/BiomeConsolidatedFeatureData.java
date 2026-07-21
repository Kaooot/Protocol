package org.cloudburstmc.protocol.bedrock.data;

import lombok.Data;

@Data
public class BiomeConsolidatedFeatureData {
  private BiomeScatterParamData scatter;

  private int feature;

  private int identifier;

  private int pass;

  private boolean canUseInternalFeature;
}
