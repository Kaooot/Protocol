package org.cloudburstmc.protocol.bedrock.data.payload.chunk;

public enum HeightMapDataType {

    NO_DATA,
    HAS_DATA,
    TOO_HIGH,
    TOO_LOW,
    ALL_COPIED;

    private static final HeightMapDataType[] VALUES = values();

    public static HeightMapDataType from(int ordinal) {
        if (ordinal >= 0 && ordinal < VALUES.length) {
            return VALUES[ordinal];
        }
        throw new UnsupportedOperationException("Detected unknown HeightMapDataType ID: " + ordinal);
    }
}