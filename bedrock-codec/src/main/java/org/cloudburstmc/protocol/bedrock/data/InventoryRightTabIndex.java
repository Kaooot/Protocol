package org.cloudburstmc.protocol.bedrock.data;

import java.lang.UnsupportedOperationException;

/**
 * Auto generated from 1.26.40-beta.31 (v2168)
 */
public enum InventoryRightTabIndex {
  NONE,

  FULL_SCREEN,

  CRAFTING,

  ARMOR;

  private static final InventoryRightTabIndex[] VALUES = values();

  public static InventoryRightTabIndex from(int ordinal) {
    if (ordinal >= 0 && ordinal < VALUES.length) {
      return VALUES[ordinal];
    }
    throw new UnsupportedOperationException("Detected unknown InventoryRightTabIndex ID: " + ordinal);
  }
}
