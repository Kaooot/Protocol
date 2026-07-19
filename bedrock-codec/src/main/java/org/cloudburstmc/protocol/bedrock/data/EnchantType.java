package org.cloudburstmc.protocol.bedrock.data;

import java.lang.UnsupportedOperationException;

/**
 * Auto generated from 1.26.40-beta.31 (v2168)
 */
public enum EnchantType {
  /**
   * Protection
   */
  PROTECTION,

  /**
   * FireProtection
   */
  FIRE_PROTECTION,

  /**
   * FeatherFalling
   */
  FEATHER_FALLING,

  /**
   * BlastProtection
   */
  BLAST_PROTECTION,

  /**
   * ProjectileProtection
   */
  PROJECTILE_PROTECTION,

  /**
   * Thorns
   */
  THORNS,

  /**
   * Respiration
   */
  RESPIRATION,

  /**
   * DepthStrider
   */
  DEPTH_STRIDER,

  /**
   * AquaAffinity
   */
  AQUA_AFFINITY,

  /**
   * Sharpness
   */
  SHARPNESS,

  /**
   * Smite
   */
  SMITE,

  /**
   * BaneOfArthropods
   */
  BANE_OF_ARTHROPODS,

  /**
   * Knockback
   */
  KNOCKBACK,

  /**
   * FireAspect
   */
  FIRE_ASPECT,

  /**
   * Looting
   */
  LOOTING,

  /**
   * Efficiency
   */
  EFFICIENCY,

  /**
   * SilkTouch
   */
  SILK_TOUCH,

  /**
   * Unbreaking
   */
  UNBREAKING,

  /**
   * Fortune
   */
  FORTUNE,

  /**
   * Power
   */
  POWER,

  /**
   * Punch
   */
  PUNCH,

  /**
   * Flame
   */
  FLAME,

  /**
   * Infinity
   */
  INFINITY,

  /**
   * LuckOfTheSea
   */
  LUCK_OF_THE_SEA,

  /**
   * Lure
   */
  LURE,

  /**
   * FrostWalker
   */
  FROST_WALKER,

  /**
   * Mending
   */
  MENDING,

  /**
   * CurseOfBinding
   */
  CURSE_OF_BINDING,

  /**
   * CurseOfVanishing
   */
  CURSE_OF_VANISHING,

  /**
   * Impaling
   */
  IMPALING,

  /**
   * Riptide
   */
  RIPTIDE,

  /**
   * Loyalty
   */
  LOYALTY,

  /**
   * Channeling
   */
  CHANNELING,

  /**
   * Multishot
   */
  MULTISHOT,

  /**
   * Piercing
   */
  PIERCING,

  /**
   * QuickCharge
   */
  QUICK_CHARGE,

  /**
   * SoulSpeed
   */
  SOUL_SPEED,

  /**
   * SwiftSneak
   */
  SWIFT_SNEAK,

  /**
   * WindBurst
   */
  WIND_BURST,

  /**
   * Density
   */
  DENSITY,

  /**
   * Breach
   */
  BREACH,

  /**
   * Lunge
   */
  LUNGE,

  /**
   * NumEnchantments
   */
  NUM_ENCHANTMENTS,

  /**
   * InvalidEnchantment
   */
  INVALID_ENCHANTMENT;

  private static final EnchantType[] VALUES = values();

  public static EnchantType from(int ordinal) {
    if (ordinal >= 0 && ordinal < VALUES.length) {
      return VALUES[ordinal];
    }
    throw new UnsupportedOperationException("Detected unknown EnchantType ID: " + ordinal);
  }
}
