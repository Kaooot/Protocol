package org.cloudburstmc.protocol.bedrock.data;

import lombok.Data;
import org.cloudburstmc.math.vector.Vector2f;

@Data
public class ConeDataPayload {
  private Vector2f radii;

  private float height;

  private int numSegments;
}
