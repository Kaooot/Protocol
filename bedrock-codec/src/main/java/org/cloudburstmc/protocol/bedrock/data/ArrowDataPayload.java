package org.cloudburstmc.protocol.bedrock.data;

import lombok.Data;
import org.cloudburstmc.math.vector.Vector3f;

@Data
public class ArrowDataPayload {
  private Vector3f arrowEndLocation;

  private float arrowHeadLength;

  private float arrowHeadRadius;

  private int numSegments;
}
