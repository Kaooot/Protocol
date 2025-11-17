package org.cloudburstmc.protocol.bedrock.data.ddui;

/**
 * @author Kaooot
 */
public enum DataStorePropertyType {

    DOUBLE,
    BOOLEAN,
    STRING;

    private static final DataStorePropertyType[] VALUES = values();

    public static DataStorePropertyType from(int ordinal) {
        if (ordinal < 0 || ordinal >= VALUES.length) {
            throw new UnsupportedOperationException("Received unknown DataStorePropertyType ID: " + ordinal);
        }
        return VALUES[ordinal];
    }
}