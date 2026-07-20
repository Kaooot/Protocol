package org.cloudburstmc.protocol.bedrock.data;

import java.lang.UnsupportedOperationException;

/**
 * Auto generated from 1.26.40-beta.31 (v2168)
 */
public enum MultiplayerSettingsPacketType {
  ENABLE_MULTIPLAYER,

  DISABLE_MULTIPLAYER,

  REFRESH_JOINCODE;

  private static final MultiplayerSettingsPacketType[] VALUES = values();

  public static MultiplayerSettingsPacketType from(int ordinal) {
    if (ordinal >= 0 && ordinal < VALUES.length) {
      return VALUES[ordinal];
    }
    throw new UnsupportedOperationException("Detected unknown MultiplayerSettingsPacketType ID: " + ordinal);
  }
}
