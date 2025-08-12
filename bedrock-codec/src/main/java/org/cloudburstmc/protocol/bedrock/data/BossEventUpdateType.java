package org.cloudburstmc.protocol.bedrock.data;

/**
 * @author Kaooot
 */
public enum BossEventUpdateType {
    /**
     * Creates the bossbar to the player.
     */
    ADD,
    /**
     * Registers a player to a boss fight.
     */
    PLAYER_ADDED,
    /**
     * Removes the bossbar from the client.
     */
    REMOVE,
    /**
     * Unregisters a player from a boss fight.
     */
    PLAYER_REMOVED,
    /**
     * Appears not to be implemented. Currently bar percentage only appears to change in response to the target entity's health.
     */
    UPDATE_PERCENT,
    /**
     * Also appears to not be implemented. Title clientside sticks as the target entity's nametag, or their entity transactionType name if not set.
     */
    UPDATE_NAME,
    /**
     * Darken the sky when the boss bar is shown.
     */
    UPDATE_PROPERTIES,
    /**
     * Not implemented :( Intended to alter bar appearance, but these currently produce no effect on clientside whatsoever.
     */
    UPDATE_STYLE,
    QUERY;

    private static final BossEventUpdateType[] VALUES = values();

    public static BossEventUpdateType from(int ordinal) {
        if (ordinal >= 0 && ordinal < VALUES.length) {
            return VALUES[ordinal];
        }
        throw new UnsupportedOperationException("Detected unknown BossEventUpdateType ID: " + ordinal);
    }
}