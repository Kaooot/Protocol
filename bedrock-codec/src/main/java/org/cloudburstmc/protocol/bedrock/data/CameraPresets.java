package org.cloudburstmc.protocol.bedrock.data;

import java.lang.String;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.cloudburstmc.math.vector.Vector2f;
import org.cloudburstmc.math.vector.Vector3f;

/**
 * Auto generated from 1.26.40-beta.31 (v2168)
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CameraPresets {
  private String cameraPreset;

  private String cameraPreset;

  private float posX;

  private float posY;

  private float posZ;

  private float rotX;

  private float rotY;

  private float rotationSpeed;

  private boolean snapToTarget;

  private Vector2f horizontalRotationLimit;

  private Vector2f verticalRotationLimit;

  private boolean continueTargeting;

  private float blockListeningRadius;

  private Vector2f viewOffset;

  private Vector3f entityOffset;

  private float radius;

  private float yawLimitMin;

  private float yawLimitMax;

  private AudioListener listener;

  private boolean playerEffects;

  private CameraAimAssistCommandDefinition aimAssist;

  private ControlScheme controlScheme;
}
