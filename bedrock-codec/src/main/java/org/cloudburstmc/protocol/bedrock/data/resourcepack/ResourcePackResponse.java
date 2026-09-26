package org.cloudburstmc.protocol.bedrock.data.resourcepack;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@Getter
@RequiredArgsConstructor
public enum ResourcePackResponse {

    CANCEL("cancel"),
    DOWNLOADING("downloading"),
    DOWNLOADING_FINISHED("downloadingfinished"),
    RESOURCE_PACK_STACK_FINISHED("resourcepackstackfinished");

    private static final Map<String, ResourcePackResponse> SERIALIZE_NAMES = new HashMap<>(values().length);

    private static final ResourcePackResponse[] VALUES = values();

    static {
        for (ResourcePackResponse value : values()) {
            SERIALIZE_NAMES.put(value.getSerializeName(), value);
        }
    }

    private final String serializeName;

    public static ResourcePackResponse fromName(String serializeName) {
        return SERIALIZE_NAMES.get(serializeName);
    }

    public static ResourcePackResponse from(int ordinal) {
        if (ordinal >= 0 && ordinal < VALUES.length) {
            return VALUES[ordinal];
        }
        throw new UnsupportedOperationException("Detected unknown ResourcePackResponse ID: " + ordinal);
    }

    public static ResourcePackResponse fromLegacy(int ordinal) {
        // Enum starts at 1
        if (ordinal >= 1 && ordinal < VALUES.length + 1) {
            return VALUES[ordinal - 1];
        }
        throw new UnsupportedOperationException("Detected unknown ResourcePackResponse ID: " + ordinal);
    }
}