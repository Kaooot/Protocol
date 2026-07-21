package org.cloudburstmc.protocol.bedrock.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.cloudburstmc.math.vector.Vector2f;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CylinderDataPayload {
  private Vector2f radiusX;

  private Vector2f radiusZ;

  private float height;

  private int numSegments;
}
