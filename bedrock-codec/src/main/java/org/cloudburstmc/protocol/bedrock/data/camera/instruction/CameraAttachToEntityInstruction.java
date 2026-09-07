package org.cloudburstmc.protocol.bedrock.data.camera.instruction;

import lombok.Data;

@Data
public class CameraAttachToEntityInstruction {
  private long entityActorID;
}
