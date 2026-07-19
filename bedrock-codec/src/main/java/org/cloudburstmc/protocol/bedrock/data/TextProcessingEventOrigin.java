package org.cloudburstmc.protocol.bedrock.data;

import java.lang.UnsupportedOperationException;

/**
 * Auto generated from 1.26.40-beta.31 (v2168)
 */
public enum TextProcessingEventOrigin {
  /**
   * unknown
   */
  UNKNOWN,

  /**
   * ServerChatPublic
   */
  SERVER_CHAT_PUBLIC,

  /**
   * ServerChatWhisper
   */
  SERVER_CHAT_WHISPER,

  /**
   * SignText
   */
  SIGN_TEXT,

  /**
   * AnvilText
   */
  ANVIL_TEXT,

  /**
   * BookAndQuillText
   */
  BOOK_AND_QUILL_TEXT,

  /**
   * CommandBlockText
   */
  COMMAND_BLOCK_TEXT,

  /**
   * BlockActorDataText
   */
  BLOCK_ACTOR_DATA_TEXT,

  /**
   * JoinEventText
   */
  JOIN_EVENT_TEXT,

  /**
   * LeaveEventText
   */
  LEAVE_EVENT_TEXT,

  /**
   * SlashCommandChat
   */
  SLASH_COMMAND_CHAT,

  /**
   * CartographyText
   */
  CARTOGRAPHY_TEXT,

  /**
   * KickCommand
   */
  KICK_COMMAND,

  /**
   * TitleCommand
   */
  TITLE_COMMAND,

  /**
   * SummonCommand
   */
  SUMMON_COMMAND,

  /**
   * ServerForm
   */
  SERVER_FORM,

  /**
   * DataDrivenUI
   */
  DATA_DRIVEN_UI;

  private static final TextProcessingEventOrigin[] VALUES = values();

  public static TextProcessingEventOrigin from(int ordinal) {
    if (ordinal >= 0 && ordinal < VALUES.length) {
      return VALUES[ordinal];
    }
    throw new UnsupportedOperationException("Detected unknown TextProcessingEventOrigin ID: " + ordinal);
  }
}
