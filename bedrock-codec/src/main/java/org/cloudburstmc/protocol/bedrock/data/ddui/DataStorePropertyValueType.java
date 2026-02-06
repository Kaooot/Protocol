package org.cloudburstmc.protocol.bedrock.data.ddui;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @author Kaooot
 */
@Getter
@RequiredArgsConstructor
public enum DataStorePropertyValueType {

    NONE(0),
    BOOL(1),
    INT64(2),
    STRING(4),
    TYPE(6);

    private static final DataStorePropertyValueType[] VALUES = values();

    private final int id;

    public static DataStorePropertyValueType from(int id) {
        for (DataStorePropertyValueType value : VALUES) {
            if (value.getId() == id) {
                return value;
            }
        }
        throw new UnsupportedOperationException("Detected unknown DataStorePropertyValueType ID: " + id);
    }
}