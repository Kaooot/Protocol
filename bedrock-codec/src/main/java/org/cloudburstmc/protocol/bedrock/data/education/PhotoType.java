package org.cloudburstmc.protocol.bedrock.data.education;

public enum PhotoType {

    PORTFOLIO,
    PHOTO_ITEM,
    BOOK;

    private static final PhotoType[] VALUES = values();

    public static PhotoType from(int ordinal) {
        if (ordinal >= 0 && ordinal < VALUES.length) {
            return VALUES[ordinal];
        }
        throw new UnsupportedOperationException("Detected unknown PhotoType ID: " + ordinal);
    }
}