package org.cloudburstmc.protocol.bedrock.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.cloudburstmc.math.vector.Vector3f;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArrowDataPayload {
  private Vector3f arrowEndLocation;

  private float arrowHeadLength;

  private float arrowHeadRadius;

  private int numSegments;
}
