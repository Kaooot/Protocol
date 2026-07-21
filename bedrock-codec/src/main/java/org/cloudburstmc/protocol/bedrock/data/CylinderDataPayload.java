package org.cloudburstmc.protocol.bedrock.data;

import lombok.Data;
import org.cloudburstmc.math.vector.Vector2f;

@Data
public class CylinderDataPayload {
  private Vector2f radiusX;

  private Vector2f radiusZ;

  private float height;

  private int numSegments;
}
