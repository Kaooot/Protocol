package org.cloudburstmc.protocol.bedrock.data.skin;

public enum PersonaAnimationExpression {

    LINEAR,
    BLINKING;

    private static final PersonaAnimationExpression[] VALUES = values();

    public static PersonaAnimationExpression from(int ordinal) {
        if (ordinal >= 0 && ordinal < VALUES.length) {
            return VALUES[ordinal];
        }
        throw new UnsupportedOperationException("Detected unknown PersonaAnimationExpression ID: " + ordinal);
    }
}