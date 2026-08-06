package org.cloudburstmc.protocol.bedrock.data.payload.attribute.eas;

import lombok.Data;

@Data
public class NoiseAlignment {
  private NoiseAlignmentType type;

  private int value;
}
