package org.cloudburstmc.protocol.bedrock.data.ability;

public enum AbilitiesIndex {

    BUILD,
    MINE,
    DOORS_AND_SWITCHES,
    OPEN_CONTAINERS,
    ATTACK_PLAYERS,
    ATTACK_MOBS,
    OPERATOR_COMMANDS,
    TELEPORT,
    INVULNERABLE,
    FLYING,
    MAY_FLY,
    INSTABUILD,
    LIGHTNING,
    FLY_SPEED,
    WALK_SPEED,
    MUTED,
    WORLD_BUILDER,
    NO_CLIP,
    /**
     * @since v575
     */
    PRIVILEGED_BUILDER,
    /**
     * @since v776
     */
    VERTICAL_FLY_SPEED;

    private static final AbilitiesIndex[] VALUES = values();

    public static AbilitiesIndex from(int ordinal) {
        if (ordinal >= 0 && ordinal < VALUES.length) {
            return VALUES[ordinal];
        }
        throw new UnsupportedOperationException("Detected unknown AbilitiesIndex ID: " + ordinal);
    }
}