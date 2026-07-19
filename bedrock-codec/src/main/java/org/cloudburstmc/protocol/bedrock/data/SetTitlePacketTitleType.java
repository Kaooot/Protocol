package org.cloudburstmc.protocol.bedrock.data;

import java.lang.UnsupportedOperationException;

/**
 * Auto generated from 1.26.40-beta.31 (v2168)
 */
public enum SetTitlePacketTitleType {
  /**
   * Clear
   */
  CLEAR,

  /**
   * Reset
   */
  RESET,

  /**
   * Title
   */
  TITLE,

  /**
   * Subtitle
   */
  SUBTITLE,

  /**
   * Actionbar
   */
  ACTIONBAR,

  /**
   * Times
   */
  TIMES,

  /**
   * TitleTextObject
   */
  TITLE_TEXT_OBJECT,

  /**
   * SubtitleTextObject
   */
  SUBTITLE_TEXT_OBJECT,

  /**
   * ActionbarTextObject
   */
  ACTIONBAR_TEXT_OBJECT;

  private static final SetTitlePacketTitleType[] VALUES = values();

  public static SetTitlePacketTitleType from(int ordinal) {
    if (ordinal >= 0 && ordinal < VALUES.length) {
      return VALUES[ordinal];
    }
    throw new UnsupportedOperationException("Detected unknown SetTitlePacketTitleType ID: " + ordinal);
  }
}
