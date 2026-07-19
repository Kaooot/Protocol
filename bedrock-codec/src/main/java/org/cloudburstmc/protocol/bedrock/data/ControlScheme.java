package org.cloudburstmc.protocol.bedrock.data;

import java.lang.UnsupportedOperationException;

/**
 * Auto generated from 1.26.40-beta.31 (v2168)
 */
public enum ControlScheme {
  /**
   * locked_player_relative_strafe
   */
  LOCKED_PLAYER_RELATIVE_STRAFE,

  /**
   * camera_relative
   */
  CAMERA_RELATIVE,

  /**
   * camera_relative_strafe
   */
  CAMERA_RELATIVE_STRAFE,

  /**
   * player_relative
   */
  PLAYER_RELATIVE,

  /**
   * player_relative_strafe
   */
  PLAYER_RELATIVE_STRAFE;

  private static final ControlScheme[] VALUES = values();

  public static ControlScheme from(int ordinal) {
    if (ordinal >= 0 && ordinal < VALUES.length) {
      return VALUES[ordinal];
    }
    throw new UnsupportedOperationException("Detected unknown ControlScheme ID: " + ordinal);
  }
}
