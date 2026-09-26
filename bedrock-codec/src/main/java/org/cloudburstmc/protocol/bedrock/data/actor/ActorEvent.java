package org.cloudburstmc.protocol.bedrock.data.actor;

/**
 * Various actor events
 */
public enum ActorEvent {

    NONE,
    JUMP,
    HURT,
    DEATH,
    START_ATTACKING,
    STOP_ATTACKING,
    TAMING_FAILED,
    TAMING_SUCCEEDED,
    SHAKE_WETNESS,
    USE_ITEM,
    EAT_GRASS,
    FISHHOOK_BUBBLE,
    FISHHOOK_FISHPOS,
    FISHHOOK_HOOKTIME,
    FISHHOOK_TEASE,
    SQUID_FLEEING,
    ZOMBIE_CONVERTING,
    PLAY_AMBIENT,
    SPAWN_ALIVE,
    START_OFFER_FLOWER,
    STOP_OFFER_FLOWER,
    LOVE_HEARTS,
    VILLAGER_ANGRY,
    VILLAGER_HAPPY,
    WITCH_HAT_MAGIC,
    FIREWORKS_EXPLODE,
    IN_LOVE_HEARTS,
    SILVERFISH_MERGE_ANIM,
    GUARDIAN_ATTACK_SOUND,
    DRINK_POTION,
    THROW_POTION,
    PRIME_TNTCART,
    PRIME_CREEPER,
    AIR_SUPPLY,
    DEPRECATED_ADD_PLAYER_LEVELS,
    GUARDIAN_MINING_FATIGUE,
    AGENT_SWING_ARM,
    DRAGON_START_DEATH_ANIM,
    GROUND_DUST,
    SHAKE,
    FEED,
    BABY_AGE,
    INSTANT_DEATH,
    NOTIFY_TRADE,
    LEASH_DESTROYED,
    /**
     * Join or leave caravan
     * Data: Caravan size
     */
    CARAVAN_UPDATED,
    TALISMAN_ACTIVATE,
    /**
     * Microjang hack to check if achievement is successful sent every 4 seconds on a vanilla server.
     */
    DEPRECATED_UPDATE_STRUCTURE_FEATURE,
    /**
     * Entity spawn
     * Data: {@code MobSpawnMethod | (entityId << 16)}
     */
    PLAYER_SPAWNED_MOB,
    PUKE,
    UPDATE_STACK_SIZE,
    START_SWIMMING,
    BALLOON_POP,
    TREASURE_HUNT,
    /**
     * @since v313
     */
    SUMMON_AGENT,
    /**
     * @since v388
     */
    FINISHED_CHARGING_ITEM,
    /**
     * @since v465
     */
    ACTOR_GROW_UP,
    /**
     * @since v503
     */
    VIBRATION_DETECTED,
    /**
     * @since v534
     */
    DRINK_MILK,
    /**
     * @since v859
     */
    SHAKE_WETNESS_STOP,
    /**
     * @since v897
     */
    KINETIC_DAMAGE_DEALT,
    /**
     * @since v975
     */
    HURT_WITHOUT_RECEIVING_DAMAGE,
    LANDED_ON_GROUND;

    private static final ActorEvent[] VALUES = values();

    public static ActorEvent from(int ordinal) {
        if (ordinal >= 0 && ordinal < VALUES.length) {
            return VALUES[ordinal];
        }
        throw new UnsupportedOperationException("Detected unknown ActorEvent ID: " + ordinal);
    }
}