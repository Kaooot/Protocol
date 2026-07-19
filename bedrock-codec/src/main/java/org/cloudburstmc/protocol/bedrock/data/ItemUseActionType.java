package org.cloudburstmc.protocol.bedrock.data;

import java.lang.UnsupportedOperationException;

/**
 * Auto generated from 1.26.40-beta.31 (v2168)
 */
public enum ItemUseActionType {
  /**
   * Place
   */
  PLACE,

  /**
   * Use
   */
  USE,

  /**
   * Destroy
   */
  DESTROY,

  /**
   * Use As Attack
   */
  USE AS ATTACK;

  private static final ItemUseActionType[] VALUES = values();

  public static ItemUseActionType from(int ordinal) {
    if (ordinal >= 0 && ordinal < VALUES.length) {
      return VALUES[ordinal];
    }
    throw new UnsupportedOperationException("Detected unknown ItemUseActionType ID: " + ordinal);
  }
}
