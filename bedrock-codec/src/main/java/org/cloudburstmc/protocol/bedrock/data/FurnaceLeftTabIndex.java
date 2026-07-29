package org.cloudburstmc.protocol.bedrock.data;

import java.lang.UnsupportedOperationException;

public enum FurnaceLeftTabIndex {
  NONE,

  RECIPE_FOOD,

  RECIPE_ITEMS,

  RECIPE_BLOCKS,

  RECIPE_SEARCH,

  INVENTORY;

  private static final FurnaceLeftTabIndex[] VALUES = values();

  public static FurnaceLeftTabIndex from(int ordinal) {
    if (ordinal >= 0 && ordinal < VALUES.length) {
      return VALUES[ordinal];
    }
    throw new UnsupportedOperationException("Detected unknown FurnaceLeftTabIndex ID: " + ordinal);
  }
}
