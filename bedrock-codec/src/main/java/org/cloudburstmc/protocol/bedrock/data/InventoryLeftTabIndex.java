package org.cloudburstmc.protocol.bedrock.data;

import java.lang.UnsupportedOperationException;

/**
 * Auto generated from 1.26.40-beta.31 (v2168)
 */
public enum InventoryLeftTabIndex {
  NONE,

  RECIPE_CONSTRUCTION,

  RECIPE_EQUIPMENT,

  RECIPE_ITEMS,

  RECIPE_NATURE,

  RECIPE_SEARCH,

  SURVIVAL;

  private static final InventoryLeftTabIndex[] VALUES = values();

  public static InventoryLeftTabIndex from(int ordinal) {
    if (ordinal >= 0 && ordinal < VALUES.length) {
      return VALUES[ordinal];
    }
    throw new UnsupportedOperationException("Detected unknown InventoryLeftTabIndex ID: " + ordinal);
  }
}
