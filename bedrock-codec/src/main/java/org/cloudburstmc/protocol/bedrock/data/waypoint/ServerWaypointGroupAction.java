package org.cloudburstmc.protocol.bedrock.data.waypoint;

public enum ServerWaypointGroupAction {

    NONE,
    ADD,
    REMOVE,
    UPDATE;

    private static final ServerWaypointGroupAction[] VALUES = values();

    public static ServerWaypointGroupAction from(int ordinal) {
        if (ordinal >= 0 && ordinal < VALUES.length) {
            return VALUES[ordinal];
        }
        throw new UnsupportedOperationException("Detected unknown ServerWaypointGroupAction ID: " + ordinal);
    }
}