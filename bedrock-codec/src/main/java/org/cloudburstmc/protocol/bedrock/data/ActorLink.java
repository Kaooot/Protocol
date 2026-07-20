package org.cloudburstmc.protocol.bedrock.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Auto generated from 1.26.40-beta.31 (v2168)
 */
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
