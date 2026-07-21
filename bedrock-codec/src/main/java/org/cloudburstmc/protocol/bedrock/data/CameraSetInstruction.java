package org.cloudburstmc.protocol.bedrock.data;

import lombok.Data;

@Data
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
