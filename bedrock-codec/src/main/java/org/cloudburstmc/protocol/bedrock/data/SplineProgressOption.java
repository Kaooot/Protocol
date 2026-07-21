package org.cloudburstmc.protocol.bedrock.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SplineProgressOption {
  private float keyFrameValue;

  private float keyFrameTime;

  private EasingFunction keyFrameEasingFunc;
}
