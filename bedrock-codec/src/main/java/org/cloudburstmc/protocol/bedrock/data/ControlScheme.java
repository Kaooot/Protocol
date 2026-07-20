package org.cloudburstmc.protocol.bedrock.data;

import java.lang.UnsupportedOperationException;

/**
 * Auto generated from 1.26.40-beta.31 (v2168)
 */
public enum ControlScheme {
  LOCKED_PLAYER_RELATIVE_STRAFE,

  CAMERA_RELATIVE,

  CAMERA_RELATIVE_STRAFE,

  PLAYER_RELATIVE,

  PLAYER_RELATIVE_STRAFE;

  private static final ControlScheme[] VALUES = values();

  public static ControlScheme from(int ordinal) {
    if (ordinal >= 0 && ordinal < VALUES.length) {
      return VALUES[ordinal];
    }
    throw new UnsupportedOperationException("Detected unknown ControlScheme ID: " + ordinal);
  }
}
