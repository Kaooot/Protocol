package org.cloudburstmc.protocol.bedrock.data;

import java.lang.UnsupportedOperationException;

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
