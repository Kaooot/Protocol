package org.cloudburstmc.protocol.bedrock.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Interaction {
  private long interactedEntityID;

  private InteractionType interactionType;

  private int interactionActorType;

  private int interactionActorVariant;

  private int interactionActorColor;
}
