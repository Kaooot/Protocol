package org.cloudburstmc.protocol.bedrock.data;

import java.lang.UnsupportedOperationException;

public enum RequestAbilityPacketPayloadType {
  UNSET,

  BOOL,

  FLOAT;

  private static final RequestAbilityPacketPayloadType[] VALUES = values();

  public static RequestAbilityPacketPayloadType from(int ordinal) {
    if (ordinal >= 0 && ordinal < VALUES.length) {
      return VALUES[ordinal];
    }
    throw new UnsupportedOperationException("Detected unknown RequestAbilityPacketPayloadType ID: " + ordinal);
  }
}
