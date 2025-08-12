package org.cloudburstmc.protocol.bedrock.data;

public enum EducationEditionOffer {

    NONE,
    REST_OF_WORLD,
    CHINA_DEPRECATED;

    private static final EducationEditionOffer[] VALUES = values();

    public static EducationEditionOffer from(int ordinal) {
        if (ordinal >= 0 && ordinal < VALUES.length) {
            return VALUES[ordinal];
        }
        throw new UnsupportedOperationException("Detected unknown EducationEditionOffer ID: " + ordinal);
    }
}