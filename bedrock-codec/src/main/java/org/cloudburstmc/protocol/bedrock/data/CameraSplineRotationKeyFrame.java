package org.cloudburstmc.protocol.bedrock.data;

import lombok.Data;
import org.cloudburstmc.math.vector.Vector3f;

@Data
public class CameraSplineRotationKeyFrame {
  private Vector3f rotation;

  private float time;

  private EasingFunction easing;
}
