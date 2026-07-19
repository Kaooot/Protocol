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
  private String SharedTypesIdentifierSharedTypesAssetTypeCameraPreset;

  private String SharedTypesReferenceSharedTypesAssetTypeCameraPreset;

  private float PosX;

  private float PosY;

  private float PosZ;

  private float RotX;

  private float RotY;

  private float RotationSpeed;

  private boolean SnaptoTarget;

  private Vector2f HorizontalRotationLimit;

  private Vector2f VerticalRotationLimit;

  private boolean ContinueTargeting;

  private float BlockListeningRadius;

  private Vector2f ViewOffset;

  private Vector3f EntityOffset;

  private float Radius;

  private float YawLimitMin;

  private float YawLimitMax;

  private SharedTypesComprehensiveCameraPresetAudioListener Listener;

  private boolean PlayerEffects;

  private SharedTypesComprehensiveCameraAimAssistCommandDefinition AimAssist;

  private ControlScheme ControlScheme;
}
