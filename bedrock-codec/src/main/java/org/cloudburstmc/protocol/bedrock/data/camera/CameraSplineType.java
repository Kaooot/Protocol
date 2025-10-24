package org.cloudburstmc.protocol.bedrock.data.camera;

/**
 * @author Kaooot
 */
public enum CameraSplineType {

    CATMULL_ROM,
    LINEAR;

    private static final CameraSplineType[] VALUES = values();

    public static CameraSplineType from(int ordinal) {
        return VALUES[ordinal];
    }
}