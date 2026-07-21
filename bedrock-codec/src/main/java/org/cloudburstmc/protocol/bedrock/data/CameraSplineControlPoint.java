package org.cloudburstmc.protocol.bedrock.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.cloudburstmc.math.vector.Vector3f;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CameraSplineControlPoint {
  private Vector3f position;
}
