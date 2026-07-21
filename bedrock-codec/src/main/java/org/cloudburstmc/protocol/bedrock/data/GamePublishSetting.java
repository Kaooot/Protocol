package org.cloudburstmc.protocol.bedrock.data;

import java.lang.UnsupportedOperationException;

public enum GamePublishSetting {
  NO_MULTI_PLAY,

  INVITE_ONLY,

  FRIENDS_ONLY,

  FRIENDS_OF_FRIENDS,

  PUBLIC;

  private static final GamePublishSetting[] VALUES = values();

  public static GamePublishSetting from(int ordinal) {
    if (ordinal >= 0 && ordinal < VALUES.length) {
      return VALUES[ordinal];
    }
    throw new UnsupportedOperationException("Detected unknown GamePublishSetting ID: " + ordinal);
  }
}
