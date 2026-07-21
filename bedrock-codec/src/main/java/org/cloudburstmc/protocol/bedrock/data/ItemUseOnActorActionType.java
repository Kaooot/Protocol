package org.cloudburstmc.protocol.bedrock.data;

import java.lang.UnsupportedOperationException;

public enum ItemUseOnActorActionType {
  INTERACT,

  ATTACK,

  ITEM_INTERACT;

  private static final ItemUseOnActorActionType[] VALUES = values();

  public static ItemUseOnActorActionType from(int ordinal) {
    if (ordinal >= 0 && ordinal < VALUES.length) {
      return VALUES[ordinal];
    }
    throw new UnsupportedOperationException("Detected unknown ItemUseOnActorActionType ID: " + ordinal);
  }
}
