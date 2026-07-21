package org.cloudburstmc.protocol.bedrock.data;

import lombok.Data;

@Data
public class CameraSplineProgressKeyFrame {
  private float progress;

  private float time;

  private EasingFunction easing;
}
