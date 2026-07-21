package org.cloudburstmc.protocol.bedrock.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BiomeSurfaceMaterialData {
  private int topBlock;

  private int midBlock;

  private int seaFloorBlock;

  private int foundationBlock;

  private int seaBlock;

  private int seaFloorDepth;
}
