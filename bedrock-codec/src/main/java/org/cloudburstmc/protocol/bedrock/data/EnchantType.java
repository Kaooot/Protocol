package org.cloudburstmc.protocol.bedrock.data;

import java.lang.UnsupportedOperationException;

public enum EnchantType {
  PROTECTION,

  FIRE_PROTECTION,

  FEATHER_FALLING,

  BLAST_PROTECTION,

  PROJECTILE_PROTECTION,

  THORNS,

  RESPIRATION,

  DEPTH_STRIDER,

  AQUA_AFFINITY,

  SHARPNESS,

  SMITE,

  BANE_OF_ARTHROPODS,

  KNOCKBACK,

  FIRE_ASPECT,

  LOOTING,

  EFFICIENCY,

  SILK_TOUCH,

  UNBREAKING,

  FORTUNE,

  POWER,

  PUNCH,

  FLAME,

  INFINITY,

  LUCK_OF_THE_SEA,

  LURE,

  FROST_WALKER,

  MENDING,

  CURSE_OF_BINDING,

  CURSE_OF_VANISHING,

  IMPALING,

  RIPTIDE,

  LOYALTY,

  CHANNELING,

  MULTISHOT,

  PIERCING,

  QUICK_CHARGE,

  SOUL_SPEED,

  SWIFT_SNEAK,

  WIND_BURST,

  DENSITY,

  BREACH,

  LUNGE,

  NUM_ENCHANTMENTS,

  INVALID_ENCHANTMENT;

  private static final EnchantType[] VALUES = values();

  public static EnchantType from(int ordinal) {
    if (ordinal >= 0 && ordinal < VALUES.length) {
      return VALUES[ordinal];
    }
    throw new UnsupportedOperationException("Detected unknown EnchantType ID: " + ordinal);
  }
}
