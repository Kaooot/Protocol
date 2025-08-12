package org.cloudburstmc.protocol.bedrock.data;

/**
 * @author Kaooot
 */
public enum PlayStatus {

    LOGIN_SUCCESS,
    LOGIN_FAILED_CLIENT_OLD,
    LOGIN_FAILED_SERVER_OLD,
    PLAYER_SPAWN,
    LOGIN_FAILED_INVALID_TENANT,
    LOGIN_FAILED_EDITION_MISMATCH_EDU_TO_VANILLA,
    LOGIN_FAILED_EDITION_MISMATCH_VANILLA_TO_EDU,
    LOGIN_FAILED_SERVER_FULL_SUB_CLIENT,
    LOGIN_FAILED_EDITOR_MISMATCH_EDITOR_TO_VANILLA,
    LOGIN_FAILED_EDITOR_MISMATCH_VANILLA_TO_EDITOR;

    private static final PlayStatus[] VALUES = values();

    public static PlayStatus from(int ordinal) {
        if (ordinal >= 0 && ordinal < VALUES.length) {
            return VALUES[ordinal];
        }
        throw new UnsupportedOperationException("Detected unknown PlayStatus ID: " + ordinal);
    }
}