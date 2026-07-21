package org.cloudburstmc.protocol.bedrock.data;

import java.lang.UnsupportedOperationException;

public enum Subtype {
  UNINITIALIZED_SUBTYPE,

  ENABLE_COMMANDS,

  DISABLE_COMMANDS,

  UNLOCK_WORLD_TEMPLATE_SETTINGS;

  private static final Subtype[] VALUES = values();

  public static Subtype from(int ordinal) {
    if (ordinal >= 0 && ordinal < VALUES.length) {
      return VALUES[ordinal];
    }
    throw new UnsupportedOperationException("Detected unknown Subtype ID: " + ordinal);
  }
}
