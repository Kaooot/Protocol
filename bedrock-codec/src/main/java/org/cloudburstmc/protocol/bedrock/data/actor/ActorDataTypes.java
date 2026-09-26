package org.cloudburstmc.protocol.bedrock.data.actor;

import lombok.experimental.UtilityClass;
import org.cloudburstmc.math.vector.Vector3f;
import org.cloudburstmc.math.vector.Vector3i;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.protocol.bedrock.data.world.event.ParticleType;
import org.cloudburstmc.protocol.bedrock.data.definitions.BlockDefinition;

import java.util.EnumMap;
import java.util.Map;

@UtilityClass
public class ActorDataTypes {

    public static final ActorDataType<EnumMap<ActorFlags, Boolean>> FLAGS = new ActorDataType<EnumMap<ActorFlags, Boolean>>(EnumMap.class, "FLAGS") {
        @Override
        public boolean isInstance(Object value) {
            return value instanceof EnumMap &&
                    (((EnumMap<?, ?>) value).isEmpty() || ((Map<?, ?>) value).keySet().iterator().next() instanceof ActorFlags);
        }
    };
    public static final ActorDataType<Integer> STRUCTURAL_INTEGRITY = new ActorDataType<>(Integer.class, "STRUCTURAL_INTEGRITY");
    public static final ActorDataType<Integer> VARIANT = new ActorDataType<>(Integer.class, "VARIANT");
    public static final ActorDataType<BlockDefinition> BLOCK = new ActorDataType<>(BlockDefinition.class, "BLOCK");
    public static final ActorDataType<Byte> COLOR = new ActorDataType<>(Byte.class, "COLOR");
    public static final ActorDataType<CharSequence> NAME = new ActorDataType<>(CharSequence.class, "NAME");
    /**
     * Unique ID of the entity that owns or created this entity.
     */
    public static final ActorDataType<Long> OWNER_EID = new ActorDataType<>(Long.class, "OWNER_EID");
    public static final ActorDataType<Long> TARGET_EID = new ActorDataType<>(Long.class, "TARGET_EID");
    public static final ActorDataType<Short> AIR_SUPPLY = new ActorDataType<>(Short.class, "AIR_SUPPLY");
    public static final ActorDataType<Integer> EFFECT_COLOR = new ActorDataType<>(Integer.class, "EFFECT_COLOR");
    /**
     * @deprecated since v685
     */
    @Deprecated
    public static final ActorDataType<Byte> EFFECT_AMBIENCE = new ActorDataType<>(Byte.class, "EFFECT_AMBIENCE");
    public static final ActorDataType<Byte> JUMP_DURATION = new ActorDataType<>(Byte.class, "JUMP_DURATION");
    public static final ActorDataType<Integer> HURT_TICKS = new ActorDataType<>(Integer.class, "HURT_TICKS");
    public static final ActorDataType<Integer> HURT_DIRECTION = new ActorDataType<>(Integer.class, "HURT_DIRECTION");
    public static final ActorDataType<Float> ROW_TIME_LEFT = new ActorDataType<>(Float.class, "ROW_TIME_LEFT");
    public static final ActorDataType<Float> ROW_TIME_RIGHT = new ActorDataType<>(Float.class, "ROW_TIME_RIGHT");
    public static final ActorDataType<Integer> VALUE = new ActorDataType<>(Integer.class, "VALUE");
    public static final ActorDataType<Byte> WITHER_SKULL_DANGEROUS = new ActorDataType<>(Byte.class, "WITHER_SKULL_DANGEROUS");
    // Same ID shares three different types -facepalm-
    public static final ActorDataType<Integer> HORSE_FLAGS = new ActorDataType<>(Integer.class, "HORSE_FLAGS");
    public static final ActorDataType<NbtMap> DISPLAY_FIREWORK = new ActorDataType<>(NbtMap.class, "DISPLAY_FIREWORK");
    public static final ActorDataType<BlockDefinition> DISPLAY_BLOCK_STATE = new ActorDataType<>(BlockDefinition.class, "DISPLAY_BLOCK_STATE");
    public static final ActorDataType<Integer> DISPLAY_OFFSET = new ActorDataType<>(Integer.class, "DISPLAY_OFFSET");
    public static final ActorDataType<Byte> CUSTOM_DISPLAY = new ActorDataType<>(Byte.class, "CUSTOM_DISPLAY");
    public static final ActorDataType<Byte> HORSE_TYPE = new ActorDataType<>(Byte.class, "HORSE_TYPE");
    public static final ActorDataType<Integer> OLD_SWELL = new ActorDataType<>(Integer.class, "OLD_SWELL");
    public static final ActorDataType<Integer> SWELL_DIRECTION = new ActorDataType<>(Integer.class, "SWELL_DIRECTION");
    public static final ActorDataType<Byte> CHARGE_AMOUNT = new ActorDataType<>(Byte.class, "CHARGE_AMOUNT");
    /**
     * @deprecated since v827
     */
    public static final ActorDataType<BlockDefinition> CARRY_BLOCK_STATE = new ActorDataType<>(BlockDefinition.class, "CARRY_BLOCK_STATE");
    public static final ActorDataType<Byte> CLIENT_EVENT = new ActorDataType<>(Byte.class, "CLIENT_EVENT");
    public static final ActorDataType<Boolean> USING_ITEM = new ActorDataType<>(Boolean.class, "USING_ITEM");
    public static final ActorDataType<Byte> PLAYER_FLAGS = new ActorDataType<>(Byte.class, "PLAYER_FLAGS");
    public static final ActorDataType<Integer> PLAYER_INDEX = new ActorDataType<>(Integer.class, "PLAYER_INDEX");
    public static final ActorDataType<Vector3i> BED_POSITION = new ActorDataType<>(Vector3i.class, "BED_POSITION");
    /**
     * Power of fireball (x-axis)
     */
    public static final ActorDataType<Float> FIREBALL_POWER_X = new ActorDataType<>(Float.class, "FIREBALL_POWER_X");
    /**
     * Power of fireball (y-axis)
     */
    public static final ActorDataType<Float> FIREBALL_POWER_Y = new ActorDataType<>(Float.class, "FIREBALL_POWER_Y");
    /**
     * Power of fireball (z-axis)
     */
    public static final ActorDataType<Float> FIREBALL_POWER_Z = new ActorDataType<>(Float.class, "FIREBALL_POWER_Z");
    /**
     * Potion aux value used for an Arrow's trail. (Equal to the potion ID - 1)
     */
    public static final ActorDataType<Byte> AUX_POWER = new ActorDataType<>(Byte.class, "AUX_POWER");
    public static final ActorDataType<Float> FISH_X = new ActorDataType<>(Float.class, "FISH_X");
    public static final ActorDataType<Float> FISH_Z = new ActorDataType<>(Float.class, "FISH_Z");
    public static final ActorDataType<Float> FISH_ANGLE = new ActorDataType<>(Float.class, "FISH_ANGLE");
    public static final ActorDataType<Short> AUX_VALUE_DATA = new ActorDataType<>(Short.class, "AUX_VALUE_DATA");
    /**
     * Unique ID for the entity who holds a leash to the current entity.
     */
    public static final ActorDataType<Long> LEASH_HOLDER = new ActorDataType<>(Long.class, "LEASH_HOLDER");
    /**
     * Set the scale of this entity.
     * 1 is the default size defined by {@code EntityDataType#WIDTH} and {@code EntityDataType#HEIGHT}.
     */
    public static final ActorDataType<Float> SCALE = new ActorDataType<>(Float.class, "SCALE");
    public static final ActorDataType<Boolean> HAS_NPC = new ActorDataType<>(Boolean.class, "HAS_NPC");
    public static final ActorDataType<String> NPC_DATA = new ActorDataType<>(String.class, "NPC_DATA");
    public static final ActorDataType<String> ACTIONS = new ActorDataType<>(String.class, "ACTIONS");
    public static final ActorDataType<Short> AIR_SUPPLY_MAX = new ActorDataType<>(Short.class, "AIR_SUPPLY_MAX");
    public static final ActorDataType<Integer> MARK_VARIANT = new ActorDataType<>(Integer.class, "MARK_VARIANT");
    public static final ActorDataType<Byte> CONTAINER_TYPE = new ActorDataType<>(Byte.class, "CONTAINER_TYPE");
    public static final ActorDataType<Integer> CONTAINER_SIZE = new ActorDataType<>(Integer.class, "CONTAINER_SIZE");
    public static final ActorDataType<Integer> CONTAINER_STRENGTH_MODIFIER = new ActorDataType<>(Integer.class, "CONTAINER_STRENGTH_MODIFIER");
    /**
     * Target position of Ender Crystal beam.
     */
    public static final ActorDataType<Vector3i> BLOCK_TARGET_POS = new ActorDataType<>(Vector3i.class, "BLOCK_TARGET_POS");
    public static final ActorDataType<Integer> WITHER_INVULNERABLE_TICKS = new ActorDataType<>(Integer.class, "WITHER_INVULNERABLE_TICKS");
    /**
     * Unique entity ID to target for the left head of a Wither.
     */
    public static final ActorDataType<Long> WITHER_TARGET_A = new ActorDataType<>(Long.class, "WITHER_TARGET_A");
    /**
     * Unique entity ID to target for the middle head of a Wither.
     */
    public static final ActorDataType<Long> WITHER_TARGET_B = new ActorDataType<>(Long.class, "WITHER_TARGET_B");
    /**
     * Unique entity ID to target for the right head of a Wither.
     */
    public static final ActorDataType<Long> WITHER_TARGET_C = new ActorDataType<>(Long.class, "WITHER_TARGET_C");
    public static final ActorDataType<Short> WITHER_AERIAL_ATTACK = new ActorDataType<>(Short.class, "WITHER_AERIAL_ATTACK");
    public static final ActorDataType<Float> WIDTH = new ActorDataType<>(Float.class, "WIDTH");
    public static final ActorDataType<Float> HEIGHT = new ActorDataType<>(Float.class, "HEIGHT");
    public static final ActorDataType<Integer> FUSE_TIME = new ActorDataType<>(Integer.class, "FUSE_TIME");
    public static final ActorDataType<Vector3f> SEAT_OFFSET = new ActorDataType<>(Vector3f.class, "SEAT_OFFSET");
    public static final ActorDataType<Boolean> SEAT_LOCK_RIDER_ROTATION = new ActorDataType<>(Boolean.class, "SEAT_LOCK_RIDER_ROTATION");
    public static final ActorDataType<Float> SEAT_LOCK_RIDER_ROTATION_DEGREES = new ActorDataType<>(Float.class, "SEAT_LOCK_RIDER_ROTATION_DEGREES");
    public static final ActorDataType<Boolean> SEAT_HAS_ROTATION = new ActorDataType<>(Boolean.class, "SEAT_HAS_ROTATION");
    public static final ActorDataType<Float> SEAT_ROTATION_OFFSET_DEGREES = new ActorDataType<>(Float.class, "SEAT_ROTATION_OFFSET_DEGREES");
    /**
     * Radius of Area Effect Cloud
     */
    public static final ActorDataType<Float> AREA_EFFECT_CLOUD_RADIUS = new ActorDataType<>(Float.class, "AREA_EFFECT_CLOUD_RADIUS");
    public static final ActorDataType<Integer> AREA_EFFECT_CLOUD_WAITING = new ActorDataType<>(Integer.class, "AREA_EFFECT_CLOUD_WAITING");
    public static final ActorDataType<ParticleType> AREA_EFFECT_CLOUD_PARTICLE = new ActorDataType<>(ParticleType.class, "AREA_EFFECT_CLOUD_PARTICLE");
    public static final ActorDataType<Integer> SHULKER_PEEK_AMOUNT = new ActorDataType<>(Integer.class, "SHULKER_PEEK_AMOUNT");
    public static final ActorDataType<Integer> SHULKER_ATTACH_FACE = new ActorDataType<>(Integer.class, "SHULKER_ATTACH_FACE");
    public static final ActorDataType<Boolean> SHULKER_ATTACHED = new ActorDataType<>(Boolean.class, "SHULKER_ATTACHED");
    /**
     * Position a Shulker entity is attached from.
     */
    public static final ActorDataType<Vector3i> SHULKER_ATTACH_POS = new ActorDataType<>(Vector3i.class, "SHULKER_ATTACH_POS");
    /**
     * Sets the unique ID of the player that is trading with this entity.
     */
    public static final ActorDataType<Long> TRADE_TARGET_EID = new ActorDataType<>(Long.class, "TRADE_TARGET_EID");
    /**
     * Previously used for the villager V1 entity.
     *
     * @deprecated unused AFAIK
     */
    @Deprecated
    public static final ActorDataType<Integer> CAREER = new ActorDataType<>(Integer.class, "CAREER");
    public static final ActorDataType<Boolean> COMMAND_BLOCK_ENABLED = new ActorDataType<>(Boolean.class, "COMMAND_BLOCK_ENABLED");
    public static final ActorDataType<String> COMMAND_BLOCK_NAME = new ActorDataType<>(String.class, "COMMAND_BLOCK_NAME");
    public static final ActorDataType<String> COMMAND_BLOCK_LAST_OUTPUT = new ActorDataType<>(String.class, "COMMAND_BLOCK_LAST_OUTPUT");
    public static final ActorDataType<Boolean> COMMAND_BLOCK_TRACK_OUTPUT = new ActorDataType<>(Boolean.class, "COMMAND_BLOCK_TRACK_OUTPUT");
    public static final ActorDataType<Byte> CONTROLLING_RIDER_SEAT_INDEX = new ActorDataType<>(Byte.class, "CONTROLLING_RIDER_SEAT_INDEX");
    public static final ActorDataType<Integer> STRENGTH = new ActorDataType<>(Integer.class, "STRENGTH");
    public static final ActorDataType<Integer> STRENGTH_MAX = new ActorDataType<>(Integer.class, "STRENGTH_MAX");
    public static final ActorDataType<Integer> EVOKER_SPELL_CASTING_COLOR = new ActorDataType<>(Integer.class, "EVOKER_SPELL_CASTING_COLOR");
    public static final ActorDataType<Integer> DATA_LIFETIME_TICKS = new ActorDataType<>(Integer.class, "DATA_LIFETIME_TICKS");
    public static final ActorDataType<Integer> ARMOR_STAND_POSE_INDEX = new ActorDataType<>(Integer.class, "ARMOR_STAND_POSE_INDEX");
    public static final ActorDataType<Integer> END_CRYSTAL_TICK_OFFSET = new ActorDataType<>(Integer.class, "END_CRYSTAL_TICK_OFFSET");
    public static final ActorDataType<Byte> NAMETAG_ALWAYS_SHOW = new ActorDataType<>(Byte.class, "NAMETAG_ALWAYS_SHOW");
    public static final ActorDataType<Byte> COLOR_2 = new ActorDataType<>(Byte.class, "COLOR_2");
    public static final ActorDataType<CharSequence> NAME_AUTHOR = new ActorDataType<>(CharSequence.class, "NAME_AUTHOR");
    public static final ActorDataType<CharSequence> SCORE = new ActorDataType<>(CharSequence.class, "SCORE");
    /**
     * Unique entity ID that the balloon string is attached to.
     * Disable by setting value to -1.
     */
    public static final ActorDataType<Long> BALLOON_ANCHOR_EID = new ActorDataType<>(Long.class, "BALLOON_ANCHOR_EID");
    public static final ActorDataType<Byte> PUFFED_STATE = new ActorDataType<>(Byte.class, "PUFFED_STATE");
    public static final ActorDataType<Integer> BOAT_BUBBLE_TIME = new ActorDataType<>(Integer.class, "BOAT_BUBBLE_TIME");
    /**
     * The unique entity ID of the player's Agent. (Education Edition only)
     */
    public static final ActorDataType<Long> AGENT_EID = new ActorDataType<>(Long.class, "AGENT_EID");
    public static final ActorDataType<Float> SITTING_AMOUNT = new ActorDataType<>(Float.class, "SITTING_AMOUNT");
    public static final ActorDataType<Float> SITTING_AMOUNT_PREVIOUS = new ActorDataType<>(Float.class, "SITTING_AMOUNT_PREVIOUS");
    public static final ActorDataType<Integer> EATING_COUNTER = new ActorDataType<>(Integer.class, "EATING_COUNTER");
    public static final ActorDataType<EnumMap<ActorFlags, Boolean>> FLAGS_2 = new ActorDataType<EnumMap<ActorFlags, Boolean>>(EnumMap.class, "FLAGS_2") {
        @Override
        public boolean isInstance(Object value) {
            return value instanceof EnumMap &&
                    (((EnumMap<?, ?>) value).isEmpty() || ((Map<?, ?>) value).keySet().iterator().next() instanceof ActorFlags);
        }
    };
    public static final ActorDataType<Float> LAYING_AMOUNT = new ActorDataType<>(Float.class, "LAYING_AMOUNT");
    public static final ActorDataType<Float> LAYING_AMOUNT_PREVIOUS = new ActorDataType<>(Float.class, "LAYING_AMOUNT_PREVIOUS");
    public static final ActorDataType<Integer> AREA_EFFECT_CLOUD_DURATION = new ActorDataType<>(Integer.class, "AREA_EFFECT_CLOUD_DURATION");
    public static final ActorDataType<Integer> AREA_EFFECT_CLOUD_SPAWN_TIME = new ActorDataType<>(Integer.class, "AREA_EFFECT_CLOUD_SPAWN_TIME");
    /**
     * @deprecated since v685
     */
    @Deprecated
    public static final ActorDataType<Float> AREA_EFFECT_CLOUD_CHANGE_RATE = new ActorDataType<>(Float.class, "AREA_EFFECT_CLOUD_CHANGE_RATE");
    public static final ActorDataType<Float> AREA_EFFECT_CLOUD_CHANGE_ON_PICKUP = new ActorDataType<>(Float.class, "AREA_EFFECT_CLOUD_CHANGE_ON_PICKUP");
    public static final ActorDataType<Integer> AREA_EFFECT_CLOUD_PICKUP_COUNT = new ActorDataType<>(Integer.class, "AREA_EFFECT_CLOUD_PICKUP_COUNT");
    public static final ActorDataType<CharSequence> INTERACT_TEXT = new ActorDataType<>(CharSequence.class, "INTERACT_TEXT");
    public static final ActorDataType<Integer> TRADE_TIER = new ActorDataType<>(Integer.class, "TRADE_TIER");
    public static final ActorDataType<Integer> MAX_TRADE_TIER = new ActorDataType<>(Integer.class, "MAX_TRADE_TIER");
    public static final ActorDataType<Integer> TRADE_EXPERIENCE = new ActorDataType<>(Integer.class, "TRADE_EXPERIENCE");
    public static final ActorDataType<Integer> SKIN_ID = new ActorDataType<>(Integer.class, "SKIN_ID");
    public static final ActorDataType<Integer> SPAWNING_FRAMES = new ActorDataType<>(Integer.class, "SPAWNING_FRAMES");
    public static final ActorDataType<Integer> COMMAND_BLOCK_TICK_DELAY = new ActorDataType<>(Integer.class, "COMMAND_BLOCK_TICK_DELAY");
    public static final ActorDataType<Boolean> COMMAND_BLOCK_EXECUTE_ON_FIRST_TICK = new ActorDataType<>(Boolean.class, "COMMAND_BLOCK_EXECUTE_ON_FIRST_TICK");
    public static final ActorDataType<Float> AMBIENT_SOUND_INTERVAL = new ActorDataType<>(Float.class, "AMBIENT_SOUND_INTERVAL");
    public static final ActorDataType<Float> AMBIENT_SOUND_INTERVAL_RANGE = new ActorDataType<>(Float.class, "AMBIENT_SOUND_INTERVAL_RANGE");
    public static final ActorDataType<String> AMBIENT_SOUND_EVENT_NAME = new ActorDataType<>(String.class, "AMBIENT_SOUND_EVENT_NAME");
    public static final ActorDataType<Float> FALL_DAMAGE_MULTIPLIER = new ActorDataType<>(Float.class, "FALL_DAMAGE_MULTIPLIER");
    public static final ActorDataType<String> NAME_RAW_TEXT = new ActorDataType<>(String.class, "NAME_RAW_TEXT");
    public static final ActorDataType<Boolean> CAN_RIDE_TARGET = new ActorDataType<>(Boolean.class, "CAN_RIDE_TARGET");
    public static final ActorDataType<Integer> LOW_TIER_CURED_TRADE_DISCOUNT = new ActorDataType<>(Integer.class, "LOW_TIER_CURED_TRADE_DISCOUNT");
    public static final ActorDataType<Integer> HIGH_TIER_CURED_TRADE_DISCOUNT = new ActorDataType<>(Integer.class, "HIGH_TIER_CURED_TRADE_DISCOUNT");
    public static final ActorDataType<Integer> NEARBY_CURED_TRADE_DISCOUNT = new ActorDataType<>(Integer.class, "NEARBY_CURED_TRADE_DISCOUNT");
    public static final ActorDataType<Integer> NEARBY_CURED_DISCOUNT_TIME_STAMP = new ActorDataType<>(Integer.class, "NEARBY_CURED_DISCOUNT_TIME_STAMP");

    /**
     * Set custom hitboxes for an entity. This will override the hitbox defined with {@link ActorDataTypes#SCALE},
     * {@link ActorDataTypes#WIDTH} and {@link ActorDataTypes#HEIGHT}, but will not affect the collisions.
     * Setting the hitbox to an empty list will revert to default behaviour.
     * <p>
     * NBT format
     * <pre>
     * {
     *     "Hitboxes": [
     *          {
     *              "MinX": 0f,
     *              "MinY": 0f,
     *              "MinZ": 0f,
     *              "MaxX": 1f,
     *              "MaxY": 1f,
     *              "MaxZ": 1f,
     *              "PivotX": 0f,
     *              "PivotY": 0f,
     *              "PivotZ": 0f,
     *          }
     *     ]
     * }
     * </pre>
     */
    public static final ActorDataType<NbtMap> HITBOX = new ActorDataType<>(NbtMap.class, "HITBOX");
    public static final ActorDataType<Boolean> IS_BUOYANT = new ActorDataType<>(Boolean.class, "IS_BUOYANT");
    public static final ActorDataType<String> BASE_RUNTIME_ID = new ActorDataType<>(String.class, "BASE_RUNTIME_ID");
    /**
     * Custom properties from the <pre>PropertyComponent</pre>.
     *
     * @deprecated v557
     */
    @Deprecated
    public static final ActorDataType<NbtMap> UPDATE_PROPERTIES = new ActorDataType<>(NbtMap.class, "UPDATE_PROPERTIES");
    public static final ActorDataType<Float> FREEZING_EFFECT_STRENGTH = new ActorDataType<>(Float.class, "FREEZING_EFFECT_STRENGTH");
    public static final ActorDataType<String> BUOYANCY_DATA = new ActorDataType<>(String.class, "BUOYANCY_DATA");
    public static final ActorDataType<Integer> GOAT_HORN_COUNT = new ActorDataType<>(Integer.class, "GOAT_HORN_COUNT");
    /**
     * @since v503
     */
    public static final ActorDataType<Float> MOVEMENT_SOUND_DISTANCE_OFFSET = new ActorDataType<>(Float.class, "MOVEMENT_SOUND_DISTANCE_OFFSET");
    /**
     * @since v503
     */
    public static final ActorDataType<Integer> HEARTBEAT_INTERVAL_TICKS = new ActorDataType<>(Integer.class, "HEARTBEAT_INTERVAL_TICKS");
    /**
     * @since v503
     */
    public static final ActorDataType<Integer> HEARTBEAT_SOUND_EVENT = new ActorDataType<>(Integer.class, "HEARTBEAT_SOUND_EVENT");
    /**
     * @since v527
     */
    public static final ActorDataType<Vector3i> PLAYER_LAST_DEATH_POS = new ActorDataType<>(Vector3i.class, "PLAYER_LAST_DEATH_POS");
    /**
     * @since v527
     */
    public static final ActorDataType<Integer> PLAYER_LAST_DEATH_DIMENSION = new ActorDataType<>(Integer.class, "PLAYER_LAST_DEATH_DIMENSION");
    /**
     * @since v527
     */
    public static final ActorDataType<Boolean> PLAYER_HAS_DIED = new ActorDataType<>(Boolean.class, "PLAYER_HAS_DIED");
    /**
     * @since v594
     */
    public static final ActorDataType<Vector3f> COLLISION_BOX = new ActorDataType<>(Vector3f.class, "COLLISION_BOX");
    /**
     * @since v685
     */
    public static final ActorDataType<Long> VISIBLE_MOB_EFFECTS = new ActorDataType<>(Long.class, "VISIBLE_MOB_EFFECTS");
    /**
     * @since v776
     */
    public static final ActorDataType<CharSequence> FILTERED_NAME = new ActorDataType<>(CharSequence.class, "FILTERED_NAME");
    /**
     * @since v776
     */
    public static final ActorDataType<Vector3f> BED_ENTER_POSITION = new ActorDataType<>(Vector3f.class, "BED_ENTER_POSITION");
    /**
     * @since v800
     */
    public static final ActorDataType<Float> SEAT_THIRD_PERSON_CAMERA_RADIUS = new ActorDataType<>(Float.class, "SEAT_THIRD_PERSON_CAMERA_RADIUS");
    /**
     * @since v800
     */
    public static final ActorDataType<Float> SEAT_CAMERA_RELAX_DISTANCE_SMOOTHING = new ActorDataType<>(Float.class, "SEAT_CAMERA_RELAX_DISTANCE_SMOOTHING");
    /**
     * @since v924
     */
    public static final ActorDataType<Integer> AIM_ASSIST_PRIORITY_PRESET_ID = new ActorDataType<>(Integer.class, "AIM_ASSIST_PRIORITY_PRESET_ID");
    /**
     * @since v924
     */
    public static final ActorDataType<Integer> AIM_ASSIST_PRIORITY_CATEGORY_ID = new ActorDataType<>(Integer.class, "AIM_ASSIST_PRIORITY_CATEGORY_ID");
    /**
     * @since v924
     */
    public static final ActorDataType<Integer> AIM_ASSIST_PRIORITY_ACTOR_ID = new ActorDataType<>(Integer.class, "AIM_ASSIST_PRIORITY_ACTOR_ID");
    public static final ActorDataType<Long> ARROW_SHOOTER_ID = new ActorDataType<>(Long.class, "ARROW_SHOOTER_ID");
    public static final ActorDataType<Long> FIREWORK_SHOOTER_ID = new ActorDataType<>(Long.class, "FIREWORK_SHOOTER_ID");
    public static final ActorDataType<Vector3f> FIREWORK_DIRECTION = new ActorDataType<>(Vector3f.class, "FIREWORK_DIRECTION");
    public static final ActorDataType<Integer> UNKNOWN_HORSE_INT_25 = new ActorDataType<>(Integer.class, "UNKNOWN_HORSE_INT_25");
    /**
     * @since v975
     */
    public static final ActorDataType<Long> RESERVED_139 = new ActorDataType<>(Long.class, "RESERVED_139");
    /**
     * @since v975
     */
    public static final ActorDataType<Float> NAMEPLATE_RENDER_DISTANCE_MAX = new ActorDataType<>(Float.class, "NAMEPLATE_RENDER_DISTANCE_MAX");
}
