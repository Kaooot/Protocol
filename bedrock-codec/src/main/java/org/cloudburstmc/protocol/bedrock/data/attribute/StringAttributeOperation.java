package org.cloudburstmc.protocol.bedrock.data.attribute;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@Getter
@RequiredArgsConstructor
public enum StringAttributeOperation {
    OVERRIDE("override");

    private static final Map<String, StringAttributeOperation> SERIALIZE_NAMES = new HashMap<>(values().length);

    static {
        for (StringAttributeOperation value : values()) {
            SERIALIZE_NAMES.put(value.getSerializeName(), value);
        }
    }

    private final String serializeName;

    public static StringAttributeOperation fromName(String serializeName) {
        return SERIALIZE_NAMES.get(serializeName);
    }
}