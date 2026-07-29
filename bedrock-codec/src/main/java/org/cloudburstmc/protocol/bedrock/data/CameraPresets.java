package org.cloudburstmc.protocol.bedrock.data;

import java.lang.String;
import lombok.Data;
import org.cloudburstmc.math.vector.Vector2f;
import org.cloudburstmc.math.vector.Vector3f;
import org.cloudburstmc.protocol.bedrock.data.camera.AudioListener;

@Data
public class CameraPresets {
  private String name;

  private String inheritFrom;

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
