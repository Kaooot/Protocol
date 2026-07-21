package org.cloudburstmc.protocol.bedrock.data;

import lombok.Data;

@Data
public class SerializedAbilitiesDataSerializedLayer {
  private int serializedLayer;

  private int abilitiesSet;

  private int abilityValues;

  private float flySpeed;

  private float verticalFlySpeed;

  private float walkSpeed;
}
