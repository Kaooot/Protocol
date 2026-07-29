package org.cloudburstmc.protocol.bedrock.data.biome;

import lombok.Data;

@Data
public class BiomeSurfaceMaterialData {
  private int topBlock;

  private int midBlock;

  private int seaFloorBlock;

  private int foundationBlock;

  private int seaBlock;

  private int seaFloorDepth;
}
