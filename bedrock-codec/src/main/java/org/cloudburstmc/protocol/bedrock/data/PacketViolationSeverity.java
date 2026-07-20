package org.cloudburstmc.protocol.bedrock.data;

import java.lang.UnsupportedOperationException;

/**
 * Auto generated from 1.26.40-beta.31 (v2168)
 */
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
