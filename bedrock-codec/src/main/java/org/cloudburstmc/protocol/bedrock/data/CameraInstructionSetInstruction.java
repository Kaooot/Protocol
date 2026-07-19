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
public class CameraInstructionSetInstruction {
  private int preset;

  private CameraInstructionSetInstructionEaseOption ease;

  private CameraInstructionSetInstructionPosOption pos;

  private CameraInstructionSetInstructionRotOption rot;

  private CameraInstructionSetInstructionFacingOption facing;

  private CameraInstructionSetInstructionViewOffsetOption viewoffset;

  private CameraInstructionSetInstructionEntityOffsetOption entityoffset;

  private boolean defaultValue;

  private boolean removeIgnoreStartingValuesComponent;
}
