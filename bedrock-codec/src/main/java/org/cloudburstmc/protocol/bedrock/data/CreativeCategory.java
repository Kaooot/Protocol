package org.cloudburstmc.protocol.bedrock.data;

import java.lang.UnsupportedOperationException;

public enum CreativeCategory {
  CONSTRUCTION,

  NATURE,

  EQUIPMENT,

  ITEMS,

  ITEM_COMMAND_ONLY;

  private static final CreativeCategory[] VALUES = values();

  public static CreativeCategory from(int ordinal) {
    if (ordinal >= 0 && ordinal < VALUES.length) {
      return VALUES[ordinal];
    }
    throw new UnsupportedOperationException("Detected unknown CreativeCategory ID: " + ordinal);
  }
}
