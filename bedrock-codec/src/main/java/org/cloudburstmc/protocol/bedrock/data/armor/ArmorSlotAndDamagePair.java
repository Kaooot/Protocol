package org.cloudburstmc.protocol.bedrock.data.armor;

import lombok.Data;

/**
 * Used to represent an ArmorSlot and its damage value, see PlayerArmorDamagePacket
 *
 * @since v844
 */
@Data
public class ArmorSlotAndDamagePair {
  private ArmorSlot armorSlot;

  private int damage;
}
