package org.cloudburstmc.protocol.bedrock.data;

import java.lang.UnsupportedOperationException;

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
