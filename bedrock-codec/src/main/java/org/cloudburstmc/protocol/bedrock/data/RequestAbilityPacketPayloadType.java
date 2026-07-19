package org.cloudburstmc.protocol.bedrock.data;

import java.lang.UnsupportedOperationException;

/**
 * Auto generated from 1.26.40-beta.31 (v2168)
 */
public enum RequestAbilityPacketPayloadType {
  /**
   * Unset
   */
  UNSET,

  /**
   * Bool
   */
  BOOL,

  /**
   * Float
   */
  FLOAT;

  private static final RequestAbilityPacketPayloadType[] VALUES = values();

  public static RequestAbilityPacketPayloadType from(int ordinal) {
    if (ordinal >= 0 && ordinal < VALUES.length) {
      return VALUES[ordinal];
    }
    throw new UnsupportedOperationException("Detected unknown RequestAbilityPacketPayloadType ID: " + ordinal);
  }
}
