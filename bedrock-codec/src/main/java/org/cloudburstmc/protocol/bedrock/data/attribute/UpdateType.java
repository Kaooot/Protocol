package org.cloudburstmc.protocol.bedrock.data.attribute;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@Getter
@RequiredArgsConstructor
public enum UpdateType {

    CLEAR_OVERRIDES("clearoverrides"),
    REMOVE_OVERRIDE("removeoverride"),
    SET_INT_OVERRIDE("setintoverride"),
    SET_FLOAT_OVERRIDE("setfloatoverride");

    private static final Map<String, UpdateType> SERIALIZE_NAMES = new HashMap<>(values().length);

    static {
        for (UpdateType value : values()) {
            SERIALIZE_NAMES.put(value.getSerializeName(), value);
        }
    }

    private final String serializeName;

    public static UpdateType fromName(String serializeName) {
        return SERIALIZE_NAMES.get(serializeName);
    }
}