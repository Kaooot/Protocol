package org.cloudburstmc.protocol.bedrock.data.payload.clock;

/**
 * @author Kaooot
 */
public enum ClockPayloadDataType {

    SYNC_STATE_DATA,
    INITIALIZE_REGISTRY_DATA,
    ADD_TIME_MARKER_DATA,
    REMOVE_TIME_MARKER_DATA;

    private static final ClockPayloadDataType[] VALUES = values();

    public static ClockPayloadDataType from(int ordinal) {
        if (ordinal >= 0 && ordinal < VALUES.length) {
            return VALUES[ordinal];
        }
        throw new UnsupportedOperationException("Detected unknown ClockPayloadDataType ID: " + ordinal);
    }
}