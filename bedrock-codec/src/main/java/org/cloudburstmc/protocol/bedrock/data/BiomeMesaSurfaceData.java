package org.cloudburstmc.protocol.bedrock.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BiomeMesaSurfaceData {
  private int clayMaterial;

  private int hardClayMaterial;

  private boolean brycePillars;

  private boolean hasForest;
}
