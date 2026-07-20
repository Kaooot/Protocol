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
  private int minValueType;

  private int minValue;

  private int maxValueType;

  private int maxValue;

  private int gridOffset;

  private int gridStepSize;

  private RandomDistributionType distribution;
}
