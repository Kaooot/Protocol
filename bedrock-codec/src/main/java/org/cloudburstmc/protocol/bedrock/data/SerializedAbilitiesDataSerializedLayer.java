package org.cloudburstmc.protocol.bedrock.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SerializedAbilitiesDataSerializedLayer {
  private int serializedLayer;

  private int abilitiesSet;

  private int abilityValues;

  private float flySpeed;

  private float verticalFlySpeed;

  private float walkSpeed;
}
