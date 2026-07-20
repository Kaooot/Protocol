package org.cloudburstmc.protocol.bedrock.data;

import java.lang.UnsupportedOperationException;

/**
 * Auto generated from 1.26.40-beta.31 (v2168)
 */
public enum ItemUseActionType {
  PLACE,

  USE,

  DESTROY,

  USE_AS_ATTACK;

  private static final ItemUseActionType[] VALUES = values();

  public static ItemUseActionType from(int ordinal) {
    if (ordinal >= 0 && ordinal < VALUES.length) {
      return VALUES[ordinal];
    }
    throw new UnsupportedOperationException("Detected unknown ItemUseActionType ID: " + ordinal);
  }
}
