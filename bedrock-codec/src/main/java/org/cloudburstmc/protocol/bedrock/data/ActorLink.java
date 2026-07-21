package org.cloudburstmc.protocol.bedrock.data;

import lombok.Data;

@Data
public class ActorLink {
  private long targetA;

  private long targetB;

  private ActorLinkType type;

  private boolean immediate;

  private boolean passengerInitiated;

  private float vehicleAngularVelocity;
}
