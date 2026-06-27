package org.cloudburstmc.protocol.bedrock.data.payload.skin;

/**
 * @author Kaooot
 */
public enum ArmSizeType {

    SLIM,
    WIDE;

    private static final ArmSizeType[] VALUES = values();

    public static ArmSizeType from(int ordinal) {
        if (ordinal >= 0 && ordinal < VALUES.length) {
            return VALUES[ordinal];
        }
        throw new UnsupportedOperationException("Detected unknown persona::ArmSize::Type ID: " + ordinal);
    }
}