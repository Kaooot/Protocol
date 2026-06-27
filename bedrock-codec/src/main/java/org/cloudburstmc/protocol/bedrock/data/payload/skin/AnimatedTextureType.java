package org.cloudburstmc.protocol.bedrock.data.payload.skin;

/**
 * @author Kaooot
 */
public enum AnimatedTextureType {

    FACE,
    BODY_32X32,
    BODY_128X128;

    private static final AnimatedTextureType[] VALUES = values();

    public static AnimatedTextureType from(int ordinal) {
        if (ordinal >= 0 && ordinal < VALUES.length) {
            return VALUES[ordinal];
        }
        throw new UnsupportedOperationException("Detected unknown persona::AnimatedTextureType ID: " + ordinal);
    }
}