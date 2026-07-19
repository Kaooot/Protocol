package org.cloudburstmc.protocol.bedrock.data;

import java.lang.UnsupportedOperationException;

/**
 * Auto generated from 1.26.40-beta.31 (v2168)
 */
public enum SharedTypesLegacyArmorSlot {
  /**
   * Head
   */
  HEAD,

  /**
   * Torso
   */
  TORSO,

  /**
   * Legs
   */
  LEGS,

  /**
   * Feet
   */
  FEET,

  /**
   * Body
   */
  BODY;

  private static final SharedTypesLegacyArmorSlot[] VALUES = values();

  public static SharedTypesLegacyArmorSlot from(int ordinal) {
    if (ordinal >= 0 && ordinal < VALUES.length) {
      return VALUES[ordinal];
    }
    throw new UnsupportedOperationException("Detected unknown SharedTypesLegacyArmorSlot ID: " + ordinal);
  }
}
