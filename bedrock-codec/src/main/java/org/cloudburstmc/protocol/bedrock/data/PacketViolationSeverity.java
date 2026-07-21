package org.cloudburstmc.protocol.bedrock.data;

import java.lang.UnsupportedOperationException;

public enum PacketViolationSeverity {
  UNKNOWN,

  WARNING,

  FINAL_WARNING,

  TERMINATING_CONNECTION;

  private static final PacketViolationSeverity[] VALUES = values();

  public static PacketViolationSeverity from(int ordinal) {
    if (ordinal >= 0 && ordinal < VALUES.length) {
      return VALUES[ordinal];
    }
    throw new UnsupportedOperationException("Detected unknown PacketViolationSeverity ID: " + ordinal);
  }
}
