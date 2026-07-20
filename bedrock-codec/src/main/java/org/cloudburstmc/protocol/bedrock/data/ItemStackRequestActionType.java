package org.cloudburstmc.protocol.bedrock.data;

import java.lang.UnsupportedOperationException;

/**
 * Auto generated from 1.26.40-beta.31 (v2168)
 */
public enum ItemStackRequestActionType {
  TAKE,

  PLACE,

  SWAP,

  DROP,

  DESTROY,

  CONSUME,

  CREATE,

  SCREEN_LAB_TABLE_COMBINE,

  SCREEN_BEACON_PAYMENT,

  SCREEN_HUDMINE_BLOCK,

  CRAFT_RECIPE,

  CRAFT_RECIPE_AUTO,

  CRAFT_CREATIVE,

  CRAFT_RECIPE_OPTIONAL,

  CRAFT_REPAIR_AND_DISENCHANT,

  CRAFT_LOOM,

  CRAFT_NON_IMPLEMENTED,

  CRAFT_RESULTS;

  private static final ItemStackRequestActionType[] VALUES = values();

  public static ItemStackRequestActionType from(int ordinal) {
    if (ordinal >= 0 && ordinal < VALUES.length) {
      return VALUES[ordinal];
    }
    throw new UnsupportedOperationException("Detected unknown ItemStackRequestActionType ID: " + ordinal);
  }
}
