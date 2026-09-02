package org.cloudburstmc.protocol.bedrock.data.payload.attribute.eas;

/**
 * @author Kaooot
 */
public enum EnvironmentAttributePayloadType {

    CONSTANT,
    TRANSITION,
    NOISE_TRANSITION;

    private static final EnvironmentAttributePayloadType[] VALUES = values();

    public static EnvironmentAttributePayloadType from(int ordinal) {
        if (ordinal >= 0 && ordinal < VALUES.length) {
            return VALUES[ordinal];
        }
        throw new IllegalArgumentException("Unknown EnvironmentAttributePayloadType: " + ordinal);
    }
}