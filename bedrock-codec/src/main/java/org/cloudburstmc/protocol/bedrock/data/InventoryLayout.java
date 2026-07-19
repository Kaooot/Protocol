package org.cloudburstmc.protocol.bedrock.data;

import java.lang.UnsupportedOperationException;

/**
 * Auto generated from 1.26.40-beta.31 (v2168)
 */
public enum InventoryLayout {
  /**
   * None
   */
  NONE,

  /**
   * InventoryOnly
   */
  INVENTORY_ONLY,

  /**
   * Default
   */
  DEFAULT,

  /**
   * RecipeBookOnly
   */
  RECIPE_BOOK_ONLY;

  private static final InventoryLayout[] VALUES = values();

  public static InventoryLayout from(int ordinal) {
    if (ordinal >= 0 && ordinal < VALUES.length) {
      return VALUES[ordinal];
    }
    throw new UnsupportedOperationException("Detected unknown InventoryLayout ID: " + ordinal);
  }
}
