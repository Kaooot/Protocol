package org.cloudburstmc.protocol.bedrock.data;

import java.lang.UnsupportedOperationException;

/**
 * Auto generated from 1.26.40-beta.31 (v2168)
 */
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
