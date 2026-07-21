package org.cloudburstmc.protocol.bedrock.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
