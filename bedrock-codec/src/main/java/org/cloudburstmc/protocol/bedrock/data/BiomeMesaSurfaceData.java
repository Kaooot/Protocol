package org.cloudburstmc.protocol.bedrock.data;

import lombok.Data;

@Data
public class BiomeMesaSurfaceData {
  private int clayMaterial;

  private int hardClayMaterial;

  private boolean brycePillars;

  private boolean hasForest;
}
