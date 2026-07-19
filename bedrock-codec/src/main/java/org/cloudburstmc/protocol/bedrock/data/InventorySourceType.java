package org.cloudburstmc.protocol.bedrock.data;

import java.lang.UnsupportedOperationException;

/**
 * Auto generated from 1.26.40-beta.31 (v2168)
 */
public enum InventorySourceType {
  /**
   * Container Inventory
   */
  CONTAINER INVENTORY,

  /**
   * Global Inventory
   */
  GLOBAL INVENTORY,

  /**
   * World Interaction
   */
  WORLD INTERACTION,

  /**
   * Creative Inventory
   */
  CREATIVE INVENTORY,

  /**
   * Non Implemented Feature TODO
   */
  NON IMPLEMENTED FEATURE TODO;

  private static final InventorySourceType[] VALUES = values();

  public static InventorySourceType from(int ordinal) {
    if (ordinal >= 0 && ordinal < VALUES.length) {
      return VALUES[ordinal];
    }
    throw new UnsupportedOperationException("Detected unknown InventorySourceType ID: " + ordinal);
  }
}
