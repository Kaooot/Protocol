package org.cloudburstmc.protocol.bedrock.data.education;

import org.cloudburstmc.protocol.bedrock.annotation.Added;

/**
 * Edu only, see AgentAnimationPacket
 */
@Added("1.20.10")
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