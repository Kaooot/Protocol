package org.cloudburstmc.protocol.bedrock.data;

import java.lang.UnsupportedOperationException;

/**
 * Auto generated from 1.26.40-beta.31 (v2168)
 */
public enum AgentAnimation {
  ARM_SWING,

  SHRUG;

  private static final AgentAnimation[] VALUES = values();

  public static AgentAnimation from(int ordinal) {
    if (ordinal >= 0 && ordinal < VALUES.length) {
      return VALUES[ordinal];
    }
    throw new UnsupportedOperationException("Detected unknown AgentAnimation ID: " + ordinal);
  }
}
