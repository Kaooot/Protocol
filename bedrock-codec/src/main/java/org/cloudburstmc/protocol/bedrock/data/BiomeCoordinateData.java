package org.cloudburstmc.protocol.bedrock.data;

import lombok.Data;

@Data
public class BiomeCoordinateData {
  private int minValueType;

  private int minValue;

  private int maxValueType;

  private int maxValue;

  private int gridOffset;

  private int gridStepSize;

  private RandomDistributionType distribution;
}
