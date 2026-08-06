package org.cloudburstmc.protocol.bedrock.data;

import lombok.Data;

@Data
public class NoiseAlignment {
  private NoiseAlignmentType type;

  private int value;
}
