package org.cloudburstmc.protocol.bedrock.data;

import lombok.Data;

@Data
public class CameraAttachToEntityInstruction {
  private long entityActorID;
}
