package org.cloudburstmc.protocol.bedrock.data.event;

import lombok.Data;
import org.cloudburstmc.protocol.bedrock.data.InteractionType;

/**
 * Provides telemetry data for interactions with actors.
 * For information on when this type is sent to the client, see MinecraftEventing::InteractionType
 */
@Data
public class Interaction {
  /**
   * The ActorUniqueID of the actor with which the client has interacted
   */
  private long interactedEntityID;

  /**
   * The interaction type, see MinecraftEventing::InteractionType
   */
  private InteractionType interactionType;

  /**
   * The type of the actor with which the client has interacted
   */
  private int interactionActorType;

  /**
   * The actor's variant: zero if none is specified
   */
  private int interactionActorVariant;

  /**
   * The actor's color
   */
  private int interactionActorColor;
}
