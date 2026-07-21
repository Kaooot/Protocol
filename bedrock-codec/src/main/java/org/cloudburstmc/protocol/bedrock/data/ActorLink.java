package org.cloudburstmc.protocol.bedrock.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ActorLink {
  private long targetA;

  private long targetB;

  private ActorLinkType type;

  private boolean immediate;

  private boolean passengerInitiated;

  private float vehicleAngularVelocity;
}
