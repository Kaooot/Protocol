package org.cloudburstmc.protocol.bedrock.data;

import java.lang.String;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.cloudburstmc.math.vector.Vector2f;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CameraAimAssistCommandDefinition {
  private String presetId;

  private AimAssistTargetMode targetMode;

  private Vector2f viewAngle;

  private float distance;
}
