package org.cloudburstmc.protocol.bedrock.data.payload.skin;

/**
 * @author Kaooot
 */
public enum AnimationExpression {

    LINEAR,
    BLINKING;

    private static final AnimationExpression[] VALUES = values();

    public static AnimationExpression from(int ordinal) {
        if (ordinal >= 0 && ordinal < VALUES.length) {
            return VALUES[ordinal];
        }
        throw new UnsupportedOperationException("Detected unknown persona::AnimationExpression ID: " + ordinal);
    }
}