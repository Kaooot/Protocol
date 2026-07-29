package org.cloudburstmc.protocol.bedrock.data.shape;

import java.lang.Float;
import lombok.Data;

@Data
public class PyramidDataPayload {
  private float width;

  private Float depth;

  private float height;
}
