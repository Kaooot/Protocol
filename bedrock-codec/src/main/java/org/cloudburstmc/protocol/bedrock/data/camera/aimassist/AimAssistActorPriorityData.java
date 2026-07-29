package org.cloudburstmc.protocol.bedrock.data.camera.aimassist;

import lombok.Data;

@Data
public class AimAssistActorPriorityData {
  private int presetIndex;

  private int categoryIndex;

  private int actorIndex;

  private int priorityValue;
}
