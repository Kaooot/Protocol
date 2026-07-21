package org.cloudburstmc.protocol.bedrock.data;

import lombok.Data;

@Data
public class Interaction {
  private long interactedEntityID;

  private InteractionType interactionType;

  private int interactionActorType;

  private int interactionActorVariant;

  private int interactionActorColor;
}
