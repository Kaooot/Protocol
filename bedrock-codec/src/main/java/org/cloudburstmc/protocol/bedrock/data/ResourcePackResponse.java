package org.cloudburstmc.protocol.bedrock.data;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @author Kaooot
 */
@RequiredArgsConstructor
public enum ResourcePackResponse {

    CANCEL("cancel"),
    DOWNLOADING("downloading"),
    DOWNLOADING_FINISHED("downloadingfinished"),
    RESOURCE_PACK_STACK_FINISHED("resourcepackstackfinished");

    @Getter
    private final String id;

    private static final ResourcePackResponse[] VALUES = values();

    public static ResourcePackResponse fromLegacy(int ordinal) {
        // Enum starts at 1
        if (ordinal >= 1 && ordinal < VALUES.length + 1) {
            return VALUES[ordinal - 1];
        }
        throw new UnsupportedOperationException("Detected unknown ResourcePackResponse ID: " + ordinal);
    }

    public static ResourcePackResponse from(int ordinal) {
        if (ordinal >= 0 && ordinal < VALUES.length) {
            return VALUES[ordinal];
        }
        throw new UnsupportedOperationException("Detected unknown ResourcePackResponse ID: " + ordinal);
    }
}