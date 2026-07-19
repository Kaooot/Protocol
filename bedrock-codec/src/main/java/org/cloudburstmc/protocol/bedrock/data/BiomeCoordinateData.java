package org.cloudburstmc.protocol.bedrock.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Auto generated from 1.26.40-beta.31 (v2168)
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BiomeCoordinateData {
  private int minvaluetype;

  private int minvalue;

  private int maxvaluetype;

  private int maxvalue;

  private int gridoffset;

  private int gridstepsize;

  private RandomDistributionType distribution;
}
