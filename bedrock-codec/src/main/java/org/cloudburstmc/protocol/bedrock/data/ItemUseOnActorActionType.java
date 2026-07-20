package org.cloudburstmc.protocol.bedrock.data;

import java.lang.UnsupportedOperationException;

/**
 * Auto generated from 1.26.40-beta.31 (v2168)
 */
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
