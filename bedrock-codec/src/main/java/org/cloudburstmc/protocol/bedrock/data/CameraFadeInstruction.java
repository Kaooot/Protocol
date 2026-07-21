package org.cloudburstmc.protocol.bedrock.data;

import lombok.Data;

@Data
public class CameraFadeInstruction {
  private TimeOption time;

  private ColorOption color;
}
