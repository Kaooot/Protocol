package org.cloudburstmc.protocol.bedrock.data.payload.chunk;

public enum SubChunkRequestResult {

    UNDEFINED, //TODO check
    SUCCESS,
    LEVEL_CHUNK_DOESNT_EXIST,
    WRONG_DIMENSION,
    PLAYER_DOESNT_EXIST,
    INDEX_OUT_OF_BOUNDS,
    SUCCESS_ALL_AIR;

    private static final SubChunkRequestResult[] VALUES = values();

    public static SubChunkRequestResult from(int ordinal) {
        if (ordinal >= 0 && ordinal < VALUES.length) {
            return VALUES[ordinal];
        }
        throw new UnsupportedOperationException("Detected unknown SubChunkRequestResult ID: " + ordinal);
    }
}