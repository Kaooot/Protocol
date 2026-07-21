package org.cloudburstmc.protocol.bedrock.data;

import java.lang.Float;
import lombok.Data;

@Data
public class PyramidDataPayload {
  private float width;

  private Float depth;

  private float height;
}
