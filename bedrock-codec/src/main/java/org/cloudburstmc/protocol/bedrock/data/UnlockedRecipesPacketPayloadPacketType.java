package org.cloudburstmc.protocol.bedrock.data;

import java.lang.UnsupportedOperationException;

public enum UnlockedRecipesPacketPayloadPacketType {
  EMPTY,

  INITIALLY_UNLOCKED_RECIPES,

  NEWLY_UNLOCKED_RECIPES,

  REMOVE_UNLOCKED_RECIPES,

  REMOVE_ALL_UNLOCKED_RECIPES;

  private static final UnlockedRecipesPacketPayloadPacketType[] VALUES = values();

  public static UnlockedRecipesPacketPayloadPacketType from(int ordinal) {
    if (ordinal >= 0 && ordinal < VALUES.length) {
      return VALUES[ordinal];
    }
    throw new UnsupportedOperationException("Detected unknown UnlockedRecipesPacketPayloadPacketType ID: " + ordinal);
  }
}
