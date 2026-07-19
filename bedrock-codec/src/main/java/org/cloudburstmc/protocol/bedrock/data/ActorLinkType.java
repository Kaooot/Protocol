package org.cloudburstmc.protocol.bedrock.data;

import java.lang.UnsupportedOperationException;

/**
 * Auto generated from 1.26.40-beta.31 (v2168)
 */
public enum ActorLinkType {
  /**
   * None
   */
  NONE,

  /**
   * Riding
   */
  RIDING,

  /**
   * Passenger
   */
  PASSENGER;

  private static final ActorLinkType[] VALUES = values();

  public static ActorLinkType from(int ordinal) {
    if (ordinal >= 0 && ordinal < VALUES.length) {
      return VALUES[ordinal];
    }
    throw new UnsupportedOperationException("Detected unknown ActorLinkType ID: " + ordinal);
  }
}
