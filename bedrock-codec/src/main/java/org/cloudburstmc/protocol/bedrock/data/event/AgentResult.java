package org.cloudburstmc.protocol.bedrock.data.event;

public enum AgentResult {

    ACTION_FAIL,
    ACTION_SUCCESS,
    QUERY_RESULT_FALSE,
    QUERY_RESULT_TRUE;

    private static final AgentResult[] VALUES = values();

    public static AgentResult from(int ordinal) {
        if (ordinal >= 0 && ordinal < VALUES.length) {
            return VALUES[ordinal];
        }
        throw new UnsupportedOperationException("Detected unknown AgentResult ID: " + ordinal);
    }
}