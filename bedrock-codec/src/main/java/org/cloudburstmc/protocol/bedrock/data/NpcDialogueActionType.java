package org.cloudburstmc.protocol.bedrock.data;

import java.lang.UnsupportedOperationException;

public enum NpcDialogueActionType {
  OPEN,

  CLOSE;

  private static final NpcDialogueActionType[] VALUES = values();

  public static NpcDialogueActionType from(int ordinal) {
    if (ordinal >= 0 && ordinal < VALUES.length) {
      return VALUES[ordinal];
    }
    throw new UnsupportedOperationException("Detected unknown NpcDialogueActionType ID: " + ordinal);
  }
}
