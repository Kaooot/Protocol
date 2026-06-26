package org.cloudburstmc.protocol.bedrock.data.payload.structure;

public enum Mirror {
    NONE,
    X,
    Z,
    XZ;

    private static final Mirror[] VALUES = Mirror.values();

    public static Mirror from(int id) {
        return VALUES[id];
    }
}
