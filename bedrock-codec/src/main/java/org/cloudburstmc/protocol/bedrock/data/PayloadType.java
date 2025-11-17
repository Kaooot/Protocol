package org.cloudburstmc.protocol.bedrock.data;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum PayloadType {

    INVALID("Invalid"),
    CLEAR_DEBUG_MARKERS("ClearDebugMarkers"),
    ADD_DEBUG_MARKER_CUBE("AddDebugMarkerCube");

    private final String id;

    private static final PayloadType[] VALUES = PayloadType.values();

    public static PayloadType from(String id) {
        for (PayloadType value : VALUES) {
            if (value.getId().equalsIgnoreCase(id)) {
                return value;
            }
        }
        throw new UnsupportedOperationException("Detected unknown PayloadType ID: " + id);
    }
}