package org.cloudburstmc.protocol.bedrock.data.camera;

import java.util.HashMap;
import java.util.Map;

public enum CameraSplineType {

    CATMULL_ROM("catmullrom"),
    LINEAR("linear");

    private static final Map<String, CameraSplineType> serializeNames = new HashMap<>(values().length, 1);

    static {
        for (CameraSplineType value : values()) {
            serializeNames.put(value.getSerializeName(), value);
        }
    }

    private static final CameraSplineType[] VALUES = values();

    private final String serializeName;

    CameraSplineType(String serializeName) {
        this.serializeName = serializeName;
    }

    public String getSerializeName() {
        return this.serializeName;
    }

    public static CameraSplineType fromName(String serializeName) {
        return serializeNames.get(serializeName);
    }

    public static CameraSplineType from(int ordinal) {
        if (ordinal >= 0 && ordinal < VALUES.length) {
            return VALUES[ordinal];
        }
        throw new UnsupportedOperationException("Detected unknown CameraSplineType ID: " + ordinal);
    }
}
