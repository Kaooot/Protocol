package org.cloudburstmc.protocol.bedrock.data;

import java.lang.UnsupportedOperationException;

/**
 * Auto generated from 1.26.40-beta.31 (v2168)
 */
public enum PlayerPermissionLevel {
  /**
   * Visitor
   */
  VISITOR,

  /**
   * Member
   */
  MEMBER,

  /**
   * Operator
   */
  OPERATOR,

  /**
   * Custom
   */
  CUSTOM;

  private static final PlayerPermissionLevel[] VALUES = values();

  public static PlayerPermissionLevel from(int ordinal) {
    if (ordinal >= 0 && ordinal < VALUES.length) {
      return VALUES[ordinal];
    }
    throw new UnsupportedOperationException("Detected unknown PlayerPermissionLevel ID: " + ordinal);
  }
}
