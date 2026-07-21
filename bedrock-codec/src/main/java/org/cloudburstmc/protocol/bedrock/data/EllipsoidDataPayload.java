package org.cloudburstmc.protocol.bedrock.data;

import lombok.Data;
import org.cloudburstmc.math.vector.Vector3f;

@Data
public class EllipsoidDataPayload {
  private Vector3f radii;

  private int segmentsPerAxis;
}
