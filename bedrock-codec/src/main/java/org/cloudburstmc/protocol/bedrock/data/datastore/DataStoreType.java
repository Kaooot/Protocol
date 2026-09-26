package org.cloudburstmc.protocol.bedrock.data.datastore;

public enum DataStoreType {

    UPDATE,
    CHANGE,
    REMOVAL;

    private static final DataStoreType[] VALUES = values();

    public static DataStoreType from(int ordinal) {
        if (ordinal < 0 || ordinal >= VALUES.length) {
            throw new UnsupportedOperationException("Detected unknown DataStoreType ID: " + ordinal);
        }
        return VALUES[ordinal];
    }
}