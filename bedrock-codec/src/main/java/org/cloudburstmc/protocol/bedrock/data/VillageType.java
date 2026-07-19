package org.cloudburstmc.protocol.bedrock.data;

import java.lang.UnsupportedOperationException;

/**
 * Auto generated from 1.26.40-beta.31 (v2168)
 */
public enum VillageType {
  /**
   * Desert
   */
  DESERT,

  /**
   * Ice
   */
  ICE,

  /**
   * Savanna
   */
  SAVANNA,

  /**
   * Taiga
   */
  TAIGA,

  /**
   * Default
   */
  DEFAULT;

  private static final VillageType[] VALUES = values();

  public static VillageType from(int ordinal) {
    if (ordinal >= 0 && ordinal < VALUES.length) {
      return VALUES[ordinal];
    }
    throw new UnsupportedOperationException("Detected unknown VillageType ID: " + ordinal);
  }
}
