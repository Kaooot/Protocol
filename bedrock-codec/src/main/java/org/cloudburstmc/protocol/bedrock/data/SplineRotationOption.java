package org.cloudburstmc.protocol.bedrock.data;

import lombok.Data;
import org.cloudburstmc.math.vector.Vector3f;

@Data
public class SplineRotationOption {
  private Vector3f keyFrameValue;

  private float keyFrameTime;

  private EasingFunction keyFrameEasingFunc;
}
