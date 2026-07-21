package org.cloudburstmc.protocol.bedrock.data;

import lombok.Data;

@Data
public class CameraInstruction {
  private CameraSetInstruction set;

  private boolean clear;

  private CameraFadeInstruction fade;

  private CameraTargetInstruction target;

  private boolean removeTarget;

  private CameraFovInstruction fieldOfView;

  private CameraSplineInstruction spline;

  private CameraAttachToEntityInstruction attachToEntity;

  private boolean detachFromEntity;
}
