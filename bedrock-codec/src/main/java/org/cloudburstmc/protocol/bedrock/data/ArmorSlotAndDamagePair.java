package org.cloudburstmc.protocol.bedrock.data;

import lombok.Data;

@Data
public class ArmorSlotAndDamagePair {
  private ArmorSlot armorSlot;

  private int damage;
}
