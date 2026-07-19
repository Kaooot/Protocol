package org.cloudburstmc.protocol.bedrock.data;

import java.lang.UnsupportedOperationException;

/**
 * Auto generated from 1.26.40-beta.31 (v2168)
 */
public enum BossEventUpdateType {
  /**
   * Add
   */
  ADD,

  /**
   * PlayerAdded
   */
  PLAYER_ADDED,

  /**
   * Remove
   */
  REMOVE,

  /**
   * PlayerRemoved
   */
  PLAYER_REMOVED,

  /**
   * Update_Percent
   */
  UPDATE_PERCENT,

  /**
   * Update_Name
   */
  UPDATE_NAME,

  /**
   * Update_Properties
   */
  UPDATE_PROPERTIES,

  /**
   * Update_Style
   */
  UPDATE_STYLE,

  /**
   * Query
   */
  QUERY;

  private static final BossEventUpdateType[] VALUES = values();

  public static BossEventUpdateType from(int ordinal) {
    if (ordinal >= 0 && ordinal < VALUES.length) {
      return VALUES[ordinal];
    }
    throw new UnsupportedOperationException("Detected unknown BossEventUpdateType ID: " + ordinal);
  }
}
