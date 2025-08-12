package org.cloudburstmc.protocol.bedrock.data;

public enum SubChunkRequestResult {
    UNDEFINED,
    SUCCESS,
    LEVEL_CHUNK_DOESNT_EXIST,
    WRONG_DIMENSION,
    PLAYER_DOESNT_EXIST,
    INDEX_OUT_OF_BOUNDS,
    SUCCESS_ALL_AIR
}
