package org.cloudburstmc.protocol.bedrock.data;

import java.lang.UnsupportedOperationException;

/**
 * Auto generated from 1.26.40-beta.31 (v2168)
 */
public enum LabTableReactionType {
  /**
   * None
   */
  NONE,

  /**
   * IceBomb
   */
  ICE_BOMB,

  /**
   * Bleach
   */
  BLEACH,

  /**
   * ElephantToothpaste
   */
  ELEPHANT_TOOTHPASTE,

  /**
   * Fertilizer
   */
  FERTILIZER,

  /**
   * HeatBlock
   */
  HEAT_BLOCK,

  /**
   * MagnesiumSalts
   */
  MAGNESIUM_SALTS,

  /**
   * MiscFire
   */
  MISC_FIRE,

  /**
   * MiscExplosion
   */
  MISC_EXPLOSION,

  /**
   * MiscLava
   */
  MISC_LAVA,

  /**
   * MiscMystical
   */
  MISC_MYSTICAL,

  /**
   * MiscSmoke
   */
  MISC_SMOKE,

  /**
   * MiscLargeSmoke
   */
  MISC_LARGE_SMOKE;

  private static final LabTableReactionType[] VALUES = values();

  public static LabTableReactionType from(int ordinal) {
    if (ordinal >= 0 && ordinal < VALUES.length) {
      return VALUES[ordinal];
    }
    throw new UnsupportedOperationException("Detected unknown LabTableReactionType ID: " + ordinal);
  }
}
