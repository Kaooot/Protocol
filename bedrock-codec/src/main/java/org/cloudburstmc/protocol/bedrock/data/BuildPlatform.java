package org.cloudburstmc.protocol.bedrock.data;

import java.lang.UnsupportedOperationException;

/**
 * Auto generated from 1.26.40-beta.31 (v2168)
 */
public enum BuildPlatform {
  /**
   * Google
   */
  GOOGLE,

  /**
   * iOS
   */
  I_OS,

  OSX,

  /**
   * Amazon
   */
  AMAZON,

  /**
   * Win32
   */
  WIN32,

  /**
   * Dedicated
   */
  DEDICATED,

  /**
   * Sony
   */
  SONY,

  /**
   * Nx
   */
  NX,

  /**
   * Xbox
   */
  XBOX,

  /**
   * Linux
   */
  LINUX,

  /**
   * Unknown
   */
  UNKNOWN;

  private static final BuildPlatform[] VALUES = values();

  public static BuildPlatform from(int ordinal) {
    if (ordinal >= 0 && ordinal < VALUES.length) {
      return VALUES[ordinal];
    }
    throw new UnsupportedOperationException("Detected unknown BuildPlatform ID: " + ordinal);
  }
}
