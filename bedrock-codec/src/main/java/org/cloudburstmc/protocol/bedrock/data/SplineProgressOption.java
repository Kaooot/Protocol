package org.cloudburstmc.protocol.bedrock.data;

import lombok.Data;

@Data
public class SplineProgressOption {
  private float keyFrameValue;

  private float keyFrameTime;

  private EasingFunction keyFrameEasingFunc;
}
