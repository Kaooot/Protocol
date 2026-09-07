package org.cloudburstmc.protocol.bedrock.data.camera.instruction;

import lombok.Data;

@Data
public class CameraFadeInstruction {
  private TimeOption time;

  private ColorOption color;
}
