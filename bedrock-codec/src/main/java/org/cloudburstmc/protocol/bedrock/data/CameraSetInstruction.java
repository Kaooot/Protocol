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
public class CameraSetInstruction {
  private int preset;

  private EaseOption ease;

  private PosOption pos;

  private RotOption rot;

  private FacingOption facing;

  private ViewOffsetOption viewOffset;

  private EntityOffsetOption entityOffset;

  private boolean defaultValue;

  private boolean removeIgnoreStartingValuesComponent;
}
