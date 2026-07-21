package org.cloudburstmc.protocol.bedrock.data;

import java.lang.UnsupportedOperationException;

public enum InventorySourceType {
  CONTAINER_INVENTORY,

  GLOBAL_INVENTORY,

  WORLD_INTERACTION,

  CREATIVE_INVENTORY,

  NON_IMPLEMENTED_FEATURE_TODO;

  private static final InventorySourceType[] VALUES = values();

  public static InventorySourceType from(int ordinal) {
    if (ordinal >= 0 && ordinal < VALUES.length) {
      return VALUES[ordinal];
    }
    throw new UnsupportedOperationException("Detected unknown InventorySourceType ID: " + ordinal);
  }
}
