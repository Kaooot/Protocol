package org.cloudburstmc.protocol.bedrock.data;

/**
 * @author Kaooot
 */
public enum ActorLinkType {

    NONE,
    RIDING,
    PASSENGER;

    private static final ActorLinkType[] VALUES = values();

    public static ActorLinkType from(int ordinal) {
        if (ordinal >= 0 && ordinal < VALUES.length) {
            return VALUES[ordinal];
        }
        throw new UnsupportedOperationException("Detected unknown ActorLinkType ID: " + ordinal);
    }
}