package org.cloudburstmc.protocol.bedrock.data;

import java.lang.UnsupportedOperationException;

/**
 * Auto generated from 1.26.40-beta.31 (v2168)
 */
public enum NpcDialogueActionType {
  /**
   * Open
   */
  OPEN,

  /**
   * Close
   */
  CLOSE;

  private static final NpcDialogueActionType[] VALUES = values();

  public static NpcDialogueActionType from(int ordinal) {
    if (ordinal >= 0 && ordinal < VALUES.length) {
      return VALUES[ordinal];
    }
    throw new UnsupportedOperationException("Detected unknown NpcDialogueActionType ID: " + ordinal);
  }
}
