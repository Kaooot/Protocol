package org.cloudburstmc.protocol.bedrock.data;

public enum PlayerActionType {
    START_DESTROY_BLOCK,
    ABORT_DESTROY_BLOCK,
    STOP_DESTROY_BLOCK,
    /**
     * @deprecated since v1001
     */
    GET_UPDATED_BLOCK,
    /**
     * @deprecated since v1001
     */
    DROP_ITEM,
    START_SLEEPING,
    STOP_SLEEPING,
    RESPAWN,
    START_JUMP,
    START_SPRINTING,
    STOP_SPRINTING,
    START_SNEAKING,
    STOP_SNEAKING,
    CREATIVE_DESTROY_BLOCK,
    CHANGE_DIMENSION_ACK,
    START_GLIDING,
    STOP_GLIDING,
    DENY_DESTROY_BLOCK,
    CRACK_BLOCK,
    /**
     * @deprecated since v1001
     */
    CHANGE_SKIN,
    /**
     * @deprecated since v1001
     */
    DEPRECATED_UPDATED_ENCHANTING_SEED,
    START_SWIMMING,
    STOP_SWIMMING,
    START_SPIN_ATTACK,
    STOP_SPIN_ATTACK,
    /**
     * @deprecated since v1001
     */
    INTERACT_WITH_BLOCK,
    /**
     * @since v428
     */
    PREDICT_DESTROY_BLOCK,
    /**
     * @since v428
     */
    CONTINUE_DESTROY_BLOCK,
    /**
     * @since v527
     */
    START_ITEM_USE_ON,
    /**
     * @since v527
     */
    STOP_ITEM_USE_ON,
    /**
     * @since v567
     */
    HANDLED_TELEPORT,
    /**
     * @since v594
     */
    MISSED_SWING,
    /**
     * @since v594
     */
    START_CRAWLING,
    /**
     * @since v594
     */
    STOP_CRAWLING,
    /**
     * @since v618
     */
    START_FLYING,
    /**
     * @since v618
     */
    STOP_FLYING,
    /**
     * @since v622
     * @deprecated
     * @deprecated since v1001
     */
    DEPRECATED_CLIENT_ACK_SERVER_DATA,
    /**
     * @since v748
     */
    START_USING_ITEM,
    /**
     * @since v2168
     */
    INTERNAL_UPDATE;

    private static final PlayerActionType[] VALUES = values();

    public static PlayerActionType from(int ordinal) {
        if (ordinal >= 0 && ordinal < VALUES.length) {
            return VALUES[ordinal];
        }
        throw new UnsupportedOperationException("Detected unknown PlayerActionType ID: " + ordinal);
    }
}
