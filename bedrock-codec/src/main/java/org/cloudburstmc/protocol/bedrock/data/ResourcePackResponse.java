package org.cloudburstmc.protocol.bedrock.data;

/**
 * @author Kaooot
 */
public enum ResourcePackResponse {

    CANCEL,
    DOWNLOADING,
    DOWNLOADING_FINISHED,
    RESOURCE_PACK_STACK_FINISHED;

    private static final ResourcePackResponse[] VALUES = values();

    public static ResourcePackResponse from(int ordinal) {
        // Enum starts at 1
        if (ordinal >= 1 && ordinal < VALUES.length + 1) {
            return VALUES[ordinal - 1];
        }
        throw new UnsupportedOperationException("Detected unknown ResourcePackResponse ID: " + ordinal);
    }
}