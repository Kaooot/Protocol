package org.cloudburstmc.protocol.bedrock.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AimAssistActorPriorityData {
  private int presetIndex;

  private int categoryIndex;

  private int actorIndex;

  private int priorityValue;
}
