package org.cloudburstmc.protocol.bedrock.data;

import java.lang.UnsupportedOperationException;

/**
 * Auto generated from 1.26.40-beta.31 (v2168)
 */
public enum InventorySourceFlags {
  /**
   * No Flag
   */
  NO_FLAG,

  /**
   * World Interaction Random
   */
  WORLD_INTERACTION_RANDOM;

  private static final InventorySourceFlags[] VALUES = values();

  public static InventorySourceFlags from(int ordinal) {
    if (ordinal >= 0 && ordinal < VALUES.length) {
      return VALUES[ordinal];
    }
    throw new UnsupportedOperationException("Detected unknown InventorySourceFlags ID: " + ordinal);
  }
}
