package org.cloudburstmc.protocol.bedrock.data;

import java.lang.UnsupportedOperationException;

/**
 * Auto generated from 1.26.40-beta.31 (v2168)
 */
public enum CommandPermissionLevel {
  /**
   * Any
   */
  ANY,

  /**
   * GameDirectors
   */
  GAME_DIRECTORS,

  /**
   * Admin
   */
  ADMIN,

  /**
   * Host
   */
  HOST,

  /**
   * Owner
   */
  OWNER,

  /**
   * Internal
   */
  INTERNAL;

  private static final CommandPermissionLevel[] VALUES = values();

  public static CommandPermissionLevel from(int ordinal) {
    if (ordinal >= 0 && ordinal < VALUES.length) {
      return VALUES[ordinal];
    }
    throw new UnsupportedOperationException("Detected unknown CommandPermissionLevel ID: " + ordinal);
  }
}
