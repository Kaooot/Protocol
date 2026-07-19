package org.cloudburstmc.protocol.bedrock.data;

import java.lang.UnsupportedOperationException;

/**
 * Auto generated from 1.26.40-beta.31 (v2168)
 */
public enum LabTablePacketPayloadType {
  /**
   * StartCombine
   */
  START_COMBINE,

  /**
   * StartReaction
   */
  START_REACTION,

  /**
   * Reset
   */
  RESET;

  private static final LabTablePacketPayloadType[] VALUES = values();

  public static LabTablePacketPayloadType from(int ordinal) {
    if (ordinal >= 0 && ordinal < VALUES.length) {
      return VALUES[ordinal];
    }
    throw new UnsupportedOperationException("Detected unknown LabTablePacketPayloadType ID: " + ordinal);
  }
}
