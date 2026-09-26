package org.cloudburstmc.protocol.bedrock.data.datastore;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum DynamicValueType {

    NULL,
    BOOLEAN,
    INTEGER,
    NUMBER,
    STRING,
    ARRAY,
    OBJECT;

    private static final DynamicValueType[] VALUES = values();

    public static DynamicValueType from(int ordinal) {
        if (ordinal < 0 || ordinal >= VALUES.length) {
            throw new UnsupportedOperationException("Received unknown DataStorePropertyValueType ID: " + ordinal);
        }
        return VALUES[ordinal];
    }
}