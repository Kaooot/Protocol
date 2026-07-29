package org.cloudburstmc.protocol.bedrock.data.camera;

import java.lang.UnsupportedOperationException;

public enum AudioListener {
  CAMERA,

  PLAYER;

  private static final AudioListener[] VALUES = values();

  public static AudioListener from(int ordinal) {
    if (ordinal >= 0 && ordinal < VALUES.length) {
      return VALUES[ordinal];
    }
    throw new UnsupportedOperationException("Detected unknown AudioListener ID: " + ordinal);
  }
}
