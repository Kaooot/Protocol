package org.cloudburstmc.protocol.bedrock.data.player.armor;

import lombok.Data;
import org.cloudburstmc.protocol.bedrock.annotation.Added;

/**
 * Used to represent an ArmorSlot and its damage value, see PlayerArmorDamagePacket
 */
@Data
@Added("1.21.111")
public class ArmorSlotAndDamagePair {

    private ArmorSlot armorSlot;
    private int damage;
}