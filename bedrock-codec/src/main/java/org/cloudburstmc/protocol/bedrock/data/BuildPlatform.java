package org.cloudburstmc.protocol.bedrock.data;

import java.lang.UnsupportedOperationException;

public enum BuildPlatform {
  GOOGLE,

  I_OS,

  OSX,

  AMAZON,

  WIN32,

  DEDICATED,

  SONY,

  NX,

  XBOX,

  LINUX,

  UNKNOWN;

  private static final BuildPlatform[] VALUES = values();

  public static BuildPlatform from(int ordinal) {
    if (ordinal >= 0 && ordinal < VALUES.length) {
      return VALUES[ordinal];
    }
    throw new UnsupportedOperationException("Detected unknown BuildPlatform ID: " + ordinal);
  }
}
