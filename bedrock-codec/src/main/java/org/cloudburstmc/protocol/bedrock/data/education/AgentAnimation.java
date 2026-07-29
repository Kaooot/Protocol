package org.cloudburstmc.protocol.bedrock.data.education;

import java.lang.UnsupportedOperationException;

/**
 * Edu only, see AgentAnimationPacket
 *
 * @since v594
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
