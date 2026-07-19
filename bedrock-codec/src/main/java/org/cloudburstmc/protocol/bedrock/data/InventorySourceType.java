package org.cloudburstmc.protocol.bedrock.data;

import java.lang.UnsupportedOperationException;

/**
 * Auto generated from 1.26.40-beta.31 (v2168)
 */
public enum InventorySourceType {
  /**
   * Container Inventory
   */
  CONTAINER_INVENTORY,

  /**
   * Global Inventory
   */
  GLOBAL_INVENTORY,

  /**
   * World Interaction
   */
  WORLD_INTERACTION,

  /**
   * Creative Inventory
   */
  CREATIVE_INVENTORY,

  /**
   * Non Implemented Feature TODO
   */
  NON_IMPLEMENTED_FEATURE_TODO;

  private static final InventorySourceType[] VALUES = values();

  public static InventorySourceType from(int ordinal) {
    if (ordinal >= 0 && ordinal < VALUES.length) {
      return VALUES[ordinal];
    }
    throw new UnsupportedOperationException("Detected unknown InventorySourceType ID: " + ordinal);
  }
}
