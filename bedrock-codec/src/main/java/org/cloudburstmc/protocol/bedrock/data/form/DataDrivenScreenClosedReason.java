package org.cloudburstmc.protocol.bedrock.data.form;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@Getter
@RequiredArgsConstructor
public enum DataDrivenScreenClosedReason {

    PROGRAMMATIC_CLOSE("programmaticclose"),
    PROGRAMMATIC_CLOSE_ALL("programmaticcloseall"),
    CLIENT_CANCELED("clientcanceled"),
    USER_BUSY("userbusy"),
    INVALID_FORM("invalidform");

    private static final Map<String, DataDrivenScreenClosedReason> SERIALIZE_NAMES = new HashMap<>(values().length);

    static {
        for (DataDrivenScreenClosedReason value : values()) {
            SERIALIZE_NAMES.put(value.getSerializeName(), value);
        }
    }

    private final String serializeName;

    public static DataDrivenScreenClosedReason fromName(String serializeName) {
        return SERIALIZE_NAMES.get(serializeName);
    }
}