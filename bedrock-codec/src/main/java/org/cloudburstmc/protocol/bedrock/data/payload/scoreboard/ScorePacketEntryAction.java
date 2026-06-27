package org.cloudburstmc.protocol.bedrock.data.payload.scoreboard;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @author Kaooot
 */
@Getter
@RequiredArgsConstructor
public enum ScorePacketEntryAction {

    REMOVE("remove"),
    CHANGE_PLAYER("changeplayer"),
    CHANGE_ENTITY("changeentity"),
    CHANGE_FAKE_PLAYER("changefakeplayer");

    private final String id;

    private static final ScorePacketEntryAction[] VALUES = values();

    public static ScorePacketEntryAction from(int ordinal) {
        if (ordinal >= 0 && ordinal < VALUES.length) {
            return VALUES[ordinal];
        }
        throw new UnsupportedOperationException("Detected unknown ScorePacketEntryAction ID: " + ordinal);
    }

    public static ScorePacketEntryAction from(String value) {
        for (ScorePacketEntryAction action : VALUES) {
            if (action.getId().equalsIgnoreCase(value)) {
                return action;
            }
        }
        throw new UnsupportedOperationException("Detected unknown ScorePacketEntryAction ID: " + value);
    }
}