package org.cloudburstmc.protocol.bedrock.data.resourcepack;

public enum EditorPackType {

    NONE,
    CORE,
    ANNOTATION;

    public static final EditorPackType[] VALUES = values();

    public static EditorPackType from(int ordinal) {
        if (ordinal >= 0 && ordinal < VALUES.length) {
            return VALUES[ordinal];
        }
        throw new UnsupportedOperationException("Detected unknown EditorPackType ID: " + ordinal);
    }
}