package org.cloudburstmc.protocol.bedrock.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CameraFovInstruction {
  private float fieldOfView;

  private float fovEaseTime;

  private EasingFunction fovEaseType;

  private boolean fieldOfViewClear;
}
