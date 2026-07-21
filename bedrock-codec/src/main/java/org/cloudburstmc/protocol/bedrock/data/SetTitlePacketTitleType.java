package org.cloudburstmc.protocol.bedrock.data;

import java.lang.UnsupportedOperationException;

public enum SetTitlePacketTitleType {
  CLEAR,

  RESET,

  TITLE,

  SUBTITLE,

  ACTIONBAR,

  TIMES,

  TITLE_TEXT_OBJECT,

  SUBTITLE_TEXT_OBJECT,

  ACTIONBAR_TEXT_OBJECT;

  private static final SetTitlePacketTitleType[] VALUES = values();

  public static SetTitlePacketTitleType from(int ordinal) {
    if (ordinal >= 0 && ordinal < VALUES.length) {
      return VALUES[ordinal];
    }
    throw new UnsupportedOperationException("Detected unknown SetTitlePacketTitleType ID: " + ordinal);
  }
}
