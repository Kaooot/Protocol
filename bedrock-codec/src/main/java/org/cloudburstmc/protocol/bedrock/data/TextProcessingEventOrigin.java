package org.cloudburstmc.protocol.bedrock.data;

import java.lang.UnsupportedOperationException;

/**
 * Auto generated from 1.26.40-beta.31 (v2168)
 */
public enum TextProcessingEventOrigin {
  UNKNOWN,

  SERVER_CHAT_PUBLIC,

  SERVER_CHAT_WHISPER,

  SIGN_TEXT,

  ANVIL_TEXT,

  BOOK_AND_QUILL_TEXT,

  COMMAND_BLOCK_TEXT,

  BLOCK_ACTOR_DATA_TEXT,

  JOIN_EVENT_TEXT,

  LEAVE_EVENT_TEXT,

  SLASH_COMMAND_CHAT,

  CARTOGRAPHY_TEXT,

  KICK_COMMAND,

  TITLE_COMMAND,

  SUMMON_COMMAND,

  SERVER_FORM,

  DATA_DRIVEN_UI;

  private static final TextProcessingEventOrigin[] VALUES = values();

  public static TextProcessingEventOrigin from(int ordinal) {
    if (ordinal >= 0 && ordinal < VALUES.length) {
      return VALUES[ordinal];
    }
    throw new UnsupportedOperationException("Detected unknown TextProcessingEventOrigin ID: " + ordinal);
  }
}
