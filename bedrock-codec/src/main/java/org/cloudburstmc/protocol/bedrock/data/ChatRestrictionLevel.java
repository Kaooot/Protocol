package org.cloudburstmc.protocol.bedrock.data;

import java.lang.UnsupportedOperationException;

public enum ChatRestrictionLevel {
  NONE,

  DROPPED,

  DISABLED;

  private static final ChatRestrictionLevel[] VALUES = values();

  public static ChatRestrictionLevel from(int ordinal) {
    if (ordinal >= 0 && ordinal < VALUES.length) {
      return VALUES[ordinal];
    }
    throw new UnsupportedOperationException("Detected unknown ChatRestrictionLevel ID: " + ordinal);
  }
}
