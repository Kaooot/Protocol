package org.cloudburstmc.protocol.bedrock.data;

import java.lang.String;
import lombok.Data;
import org.cloudburstmc.math.vector.Vector2f;

@Data
public class CameraAimAssistCommandDefinition {
  private String presetId;

  private AimAssistTargetMode targetMode;

  private Vector2f viewAngle;

  private float distance;
}
