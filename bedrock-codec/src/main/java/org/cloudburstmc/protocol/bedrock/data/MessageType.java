package org.cloudburstmc.protocol.bedrock.data;

import java.lang.UnsupportedOperationException;

/**
 * Auto generated from 1.26.40-beta.31 (v2168)
 */
public enum MessageType {
  TRANSLATE,

  POPUP,

  JUKEBOX_POPUP;

  private static final MessageType[] VALUES = values();

  public static MessageType from(int ordinal) {
    if (ordinal >= 0 && ordinal < VALUES.length) {
      return VALUES[ordinal];
    }
    throw new UnsupportedOperationException("Detected unknown MessageType ID: " + ordinal);
  }
}
