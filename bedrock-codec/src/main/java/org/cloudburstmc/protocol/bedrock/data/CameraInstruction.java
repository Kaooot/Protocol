package org.cloudburstmc.protocol.bedrock.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Auto generated from 1.26.40-beta.31 (v2168)
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CameraInstruction {
  private CameraInstructionSetInstruction Set;

  private boolean Clear;

  private CameraInstructionFadeInstruction Fade;

  private CameraInstructionTargetInstruction Target;

  private boolean RemoveTarget;

  private CameraInstructionFovInstruction FieldOfView;

  private CameraInstructionSplineInstruction Spline;

  private CameraInstructionAttachToEntityInstruction AttachToEntity;

  private boolean DetachFromEntity;
}
