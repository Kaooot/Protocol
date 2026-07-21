package org.cloudburstmc.protocol.bedrock.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.cloudburstmc.math.vector.Vector3f;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SplineRotationOption {
  private Vector3f keyFrameValue;

  private float keyFrameTime;

  private EasingFunction keyFrameEasingFunc;
}
