package org.cloudburstmc.protocol.bedrock.data;

import lombok.Data;

@Data
public class BiomeMountainParamsData {
  private int steepBlock;

  private boolean northSlopes;

  private boolean southSlopes;

  private boolean westSlopes;

  private boolean eastSlopes;

  private boolean topSlideEnabled;
}
