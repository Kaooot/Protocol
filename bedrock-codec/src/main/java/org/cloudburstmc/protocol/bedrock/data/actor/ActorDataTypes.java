package org.cloudburstmc.protocol.bedrock.data.actor;

import lombok.experimental.UtilityClass;
import org.cloudburstmc.math.vector.Vector3f;
import org.cloudburstmc.math.vector.Vector3i;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.protocol.bedrock.data.ParticleType;
import org.cloudburstmc.protocol.bedrock.data.SoundEvent;
import org.cloudburstmc.protocol.bedrock.data.definitions.BlockDefinition;

import java.util.EnumSet;
import java.util.Set;

@UtilityClass
public class ActorDataTypes {

    public static final ActorDataType<EnumSet<ActorFlags>> FLAGS = new ActorDataType<EnumSet<ActorFlags>>(EnumSet.class, "FLAGS") {
        @Override
        public boolean isInstance(Object value) {
            return value instanceof EnumSet &&
                    (((EnumSet<?>) value).isEmpty() || ((Set<?>) value).iterator().next() instanceof ActorFlags);
        }
    };
    /**
     * The amount of damage the vehicle actor has taken?
     */
    public static final ActorDataType<Integer> STRUCTURAL_INTEGRITY = new ActorDataType<>(Integer.class, "STRUCTURAL_INTEGRITY");
    /**
     * Used to set the variant of an actor which can have different appearance variations.
     */
    public static final ActorDataType<Integer> VARIANT = new ActorDataType<>(Integer.class, "VARIANT");
    /**
     * The purpose of the field is unclear but seems to have values between 0 and 1, defaults to zero -
     * probably isn't a block runtime id. It's default value is 0.
     */
    public static final ActorDataType<BlockDefinition> BLOCK = new ActorDataType<>(BlockDefinition.class, "BLOCK");
    /**
     * The color index used for various actors that can have different colors, e.g. tropical fish.
     * Defaults to zero for actors that don't have color variants.
     */
    public static final ActorDataType<Byte> COLOR_INDEX = new ActorDataType<>(Byte.class, "COLOR_INDEX");
    /**
     * Name of the actor shown in the name plate renderer. Defaults to an empty string.
     */
    public static final ActorDataType<String> NAME = new ActorDataType<>(String.class, "NAME");
    /**
     * Unique ID of the entity that owns or created this entity.
     */
    public static final ActorDataType<Long> OWNER = new ActorDataType<>(Long.class, "OWNER");
    /**
     * ActorUniqueID of the entity that is targeted by this actor, used for Mob AI combat targeting purposes.
     */
    public static final ActorDataType<Long> TARGET = new ActorDataType<>(Long.class, "TARGET");
    /**
     * The number of air supply ticks.
     */
    public static final ActorDataType<Short> AIR_SUPPLY = new ActorDataType<>(Short.class, "AIR_SUPPLY");
    /**
     * Used to change the effect color if this actor has a certain status effect.
     */
    public static final ActorDataType<Integer> EFFECT_COLOR = new ActorDataType<>(Integer.class, "EFFECT_COLOR");
    /**
     * Sets the ambience value of the actor's status effect.
     *
     * @deprecated since v685
     */
    @Deprecated
    public static final ActorDataType<Byte> EFFECT_AMBIENCE = new ActorDataType<>(Byte.class, "EFFECT_AMBIENCE");
    /**
     * Sets the actor's jump duration value.
     */
    @Deprecated
    public static final ActorDataType<Byte> JUMP_DURATION = new ActorDataType<>(Byte.class, "JUMP_DURATION");
    /**
     * Defines the hurt time in ticks.
     */
    public static final ActorDataType<Integer> HURT = new ActorDataType<>(Integer.class, "HURT");
    /**
     * Sets the actor's hurt direction.
     */
    public static final ActorDataType<Integer> HURT_DIR = new ActorDataType<>(Integer.class, "HURT_DIR");
    /**
     * Sets the row time for the left side when the actor is in a boat.
     */
    public static final ActorDataType<Float> ROW_TIME_LEFT = new ActorDataType<>(Float.class, "ROW_TIME_LEFT");
    /**
     * Sets the row time for the right side when the actor is in a boat.
     */
    public static final ActorDataType<Float> ROW_TIME_RIGHT = new ActorDataType<>(Float.class, "ROW_TIME_RIGHT");
    /**
     * Xp orb actor experience amount value
     */
    public static final ActorDataType<Integer> VALUE = new ActorDataType<>(Integer.class, "VALUE");
    /**
     * Used to determine whether the wither skull actor is dangerous or not.
     * btw. the owner id of the wither skull actor is set to the wither actor's unique identifier.
     */
    public static final ActorDataType<Byte> WITHER_SKULL_DANGEROUS = new ActorDataType<>(Byte.class, "WITHER_SKULL_DANGEROUS");
    /**
     * Not sure what those are. In one case this value contained the air block runtime id in combination with an
     * air DISPLAY_TILE_RUNTIME_ID.
     */
    public static final ActorDataType<Integer> HORSE_FLAGS = new ActorDataType<>(Integer.class, "HORSE_FLAGS");
    /**
     * The fireworks nbt used to correctly display the firework actor's explosion particle.
     */
    public static final ActorDataType<NbtMap> DISPLAY_FIREWORK = new ActorDataType<>(NbtMap.class, "DISPLAY_FIREWORK");
    /**
     * The display tile block runtime id.
     */
    public static final ActorDataType<BlockDefinition> DISPLAY_TILE_RUNTIME_ID = new ActorDataType<>(BlockDefinition.class, "DISPLAY_TILE_RUNTIME_ID");
    /**
     * The display offset (y) value, used for mounting.
     */
    public static final ActorDataType<Integer> DISPLAY_OFFSET = new ActorDataType<>(Integer.class, "DISPLAY_OFFSET");
    /**
     * Whether the actor has a custom display. Doesn't seem to be related to the name plate rendering.
     */
    public static final ActorDataType<Byte> CUSTOM_DISPLAY = new ActorDataType<>(Byte.class, "CUSTOM_DISPLAY");
    public static final ActorDataType<Byte> SWELL = new ActorDataType<>(Byte.class, "SWELL");
    public static final ActorDataType<Integer> OLD_SWELL = new ActorDataType<>(Integer.class, "OLD_SWELL");
    public static final ActorDataType<Integer> SWELL_DIR = new ActorDataType<>(Integer.class, "SWELL_DIR");
    /**
     * Used for shooting, e.g. Ghast actors or crossbow arrow shooting. Defaults to 0.
     */
    public static final ActorDataType<Byte> CHARGE_AMOUNT = new ActorDataType<>(Byte.class, "CHARGE_AMOUNT");
    /**
     * Used to define the block that an actor is carrying, see Enderman block carrying.
     *
     * @deprecated since v827
     */
    public static final ActorDataType<BlockDefinition> CARRY_BLOCK_RUNTIME_ID = new ActorDataType<>(BlockDefinition.class, "CARRY_BLOCK_RUNTIME_ID");
    public static final ActorDataType<Byte> CLIENT_EVENT = new ActorDataType<>(Byte.class, "CLIENT_EVENT");
    /**
     * Whether the actor currently uses an item.
     */
    public static final ActorDataType<Boolean> USING_ITEM = new ActorDataType<>(Boolean.class, "USING_ITEM");
    /**
     * Player flags for players only. 0x2 corresponds to the player sleeping flag.
     */
    public static final ActorDataType<Byte> PLAYER_FLAGS = new ActorDataType<>(Byte.class, "PLAYER_FLAGS");
    /**
     * The index of a player. Used for players only.
     */
    public static final ActorDataType<Integer> PLAYER_INDEX = new ActorDataType<>(Integer.class, "PLAYER_INDEX");
    /**
     * The actor's bed position.
     */
    public static final ActorDataType<Vector3i> BED_POSITION = new ActorDataType<>(Vector3i.class, "BED_POSITION");
    /**
     * Power of a fireball actor along the x-axis
     */
    public static final ActorDataType<Float> X_POWER = new ActorDataType<>(Float.class, "X_POWER");
    /**
     * Power of a fireball actor along the y-axis
     */
    public static final ActorDataType<Float> Y_POWER = new ActorDataType<>(Float.class, "Y_POWER");
    /**
     * Power of a fireball actor along the z-axis
     */
    public static final ActorDataType<Float> Z_POWER = new ActorDataType<>(Float.class, "Z_POWER");
    /**
     * Potion aux value used for an Arrow's trail. (Equal to the potion ID - 1)
     */
    public static final ActorDataType<Byte> AUX_POWER = new ActorDataType<>(Byte.class, "AUX_POWER");
    /**
     * Fish x coordinate relative to the position of the fishing hook.
     */
    public static final ActorDataType<Float> FISH_X = new ActorDataType<>(Float.class, "FISHX");
    /**
     * Fish z coordinate relative to the position of the fishing hook.
     */
    public static final ActorDataType<Float> FISH_Z = new ActorDataType<>(Float.class, "FISHZ");
    /**
     * Fish angle. Set when a fish actor is present.
     */
    public static final ActorDataType<Float> FISH_ANGLE = new ActorDataType<>(Float.class, "FISHANGLE");
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
    /**
     * Whether the actor has a npc or not.
     */
    public static final ActorDataType<Boolean> HAS_NPC = new ActorDataType<>(Boolean.class, "HAS_NPC");
    /**
     * The npc dialogue data used for npc actors.
     */
    public static final ActorDataType<String> NPC_DATA = new ActorDataType<>(String.class, "NPC_DATA");
    /**
     * Probably related to npc actions?
     */
    public static final ActorDataType<String> ACTIONS = new ActorDataType<>(String.class, "ACTIONS");
    /**
     * The maximum number of air supply ticks.
     */
    public static final ActorDataType<Short> AIR_SUPPLY_MAX = new ActorDataType<>(Short.class, "AIR_SUPPLY_MAX");
    /**
     * Used to set the mark variant of an actor that has mark variants.
     */
    public static final ActorDataType<Integer> MARK_VARIANT = new ActorDataType<>(Integer.class, "MARK_VARIANT");
    /**
     * The container type value for actor's that can have containers. Defaults to 0.
     */
    public static final ActorDataType<Byte> CONTAINER_TYPE = new ActorDataType<>(Byte.class, "CONTAINER_TYPE");
    /**
     * The container size value for actor's that can have containers. Defaults to 0.
     */
    public static final ActorDataType<Integer> CONTAINER_SIZE = new ActorDataType<>(Integer.class, "CONTAINER_SIZE");
    /**
     * The container strength modifier value for actor's that can have containers. Defaults to 0. Related to Llama actors.
     */
    public static final ActorDataType<Integer> CONTAINER_STRENGTH_MODIFIER = new ActorDataType<>(Integer.class, "CONTAINER_STRENGTH_MODIFIER");
    /**
     * The target position of the Ender Crystal beam.
     */
    public static final ActorDataType<Vector3i> BLOCK_TARGET = new ActorDataType<>(Vector3i.class, "BLOCK_TARGET");
    /**
     * Invulnerable ticks of a Wither actor.
     */
    public static final ActorDataType<Integer> INV = new ActorDataType<>(Integer.class, "INV");
    /**
     * ActorUniqueID to target for the left head of a Wither.
     */
    public static final ActorDataType<Long> TARGET_A = new ActorDataType<>(Long.class, "TARGET_A");
    /**
     * ActorUniqueID to target for the middle head of a Wither.
     */
    public static final ActorDataType<Long> TARGET_B = new ActorDataType<>(Long.class, "TARGET_B");
    /**
     * ActorUniqueID to target for the right head of a Wither.
     */
    public static final ActorDataType<Long> TARGET_C = new ActorDataType<>(Long.class, "TARGET_C");
    /**
     * Wither aerial attack value
     */
    public static final ActorDataType<Short> AERIAL_ATTACK = new ActorDataType<>(Short.class, "AERIAL_ATTACK");
    /**
     * The width of the actor, can be scaled using the scale actor data type.
     */
    public static final ActorDataType<Float> WIDTH = new ActorDataType<>(Float.class, "WIDTH");
    /**
     * The height of the actor, can be scaled using the scale actor data type.
     */
    public static final ActorDataType<Float> HEIGHT = new ActorDataType<>(Float.class, "HEIGHT");
    /**
     * Fuse time in ticks, used for primed tnt actors
     */
    public static final ActorDataType<Integer> FUSE_TIME = new ActorDataType<>(Integer.class, "FUSE_TIME");
    /**
     * The seat offset value for a rider.
     */
    public static final ActorDataType<Vector3f> SEAT_OFFSET = new ActorDataType<>(Vector3f.class, "SEAT_OFFSET");
    /**
     * Whether to lock the passenger rotation on a mounted actor
     */
    public static final ActorDataType<Boolean> SEAT_LOCK_PASSENGER_ROTATION = new ActorDataType<>(Boolean.class, "SEAT_LOCK_PASSENGER_ROTATION");
    /**
     * Defines passenger rotation lock degrees
     */
    public static final ActorDataType<Float> SEAT_LOCK_PASSENGER_ROTATION_DEGREES = new ActorDataType<>(Float.class, "SEAT_LOCK_PASSENGER_ROTATION_DEGREES");
    /**
     * Whether a seat rotation offset is present
     */
    public static final ActorDataType<Boolean> SEAT_ROTATION_OFFSET = new ActorDataType<>(Boolean.class, "SEAT_ROTATION_OFFSET");
    /**
     * Defines seat rotation offset degrees
     */
    public static final ActorDataType<Float> SEAT_ROTATION_OFFSET_DEGREES = new ActorDataType<>(Float.class, "SEAT_ROTATION_OFFSET_DEGREES");
    /**
     * Represents the radius of an Area Effect Cloud actor
     */
    public static final ActorDataType<Float> DATA_RADIUS = new ActorDataType<>(Float.class, "DATA_RADIUS");
    /**
     * The waiting time in ticks of an Area Effect Cloud actor.
     */
    public static final ActorDataType<Integer> DATA_WAITING = new ActorDataType<>(Integer.class, "DATA_WAITING");
    /**
     * The particle type used for the Area Effect Cloud actor.
     */
    public static final ActorDataType<ParticleType> DATA_PARTICLE = new ActorDataType<>(ParticleType.class, "DATA_PARTICLE");
    /**
     * Updated when a Shulker actor peeks. Defaults to 0.
     */
    public static final ActorDataType<Integer> PEEK_ID = new ActorDataType<>(Integer.class, "PEEK_ID");
    /**
     * Shulker actor attach face.
     */
    public static final ActorDataType<Integer> ATTACH_FACE = new ActorDataType<>(Integer.class, "ATTACH_FACE");
    /**
     * Whether the shulker actor is attached.
     */
    public static final ActorDataType<Boolean> ATTACHED = new ActorDataType<>(Boolean.class, "ATTACHED");
    /**
     * Position a Shulker entity is attached from.
     */
    public static final ActorDataType<Vector3i> ATTACH_POS = new ActorDataType<>(Vector3i.class, "ATTACH_POS");
    /**
     * Sets the unique ID of the player that is trading with this entity.
     */
    public static final ActorDataType<Long> TRADE_TARGET = new ActorDataType<>(Long.class, "TRADE_TARGET");
    /**
     * Previously used for the villager V1 entity.
     *
     * @deprecated unused AFAIK
     */
    @Deprecated
    public static final ActorDataType<Integer> CAREER = new ActorDataType<>(Integer.class, "CAREER");
    /**
     * Whether the actor has a command block. True for e.g. command block minecarts.
     */
    public static final ActorDataType<Boolean> HAS_COMMAND_BLOCK = new ActorDataType<>(Boolean.class, "HAS_COMMAND_BLOCK");
    /**
     * The command name of the actor's command block
     */
    public static final ActorDataType<String> COMMAND_NAME = new ActorDataType<>(String.class, "COMMAND_NAME");
    /**
     * The last command output of the actor's command block
     */
    public static final ActorDataType<String> LAST_COMMAND_OUTPUT = new ActorDataType<>(String.class, "LAST_COMMAND_OUTPUT");
    /**
     * Whether to track the output for the actor's command block
     */
    public static final ActorDataType<Boolean> TRACK_COMMAND_OUTPUT = new ActorDataType<>(Boolean.class, "TRACK_COMMAND_OUTPUT");
    /**
     * Defines seat index for the rider that controls the vehicle.
     */
    public static final ActorDataType<Byte> CONTROLLING_RIDER_SEAT_INDEX = new ActorDataType<>(Byte.class, "CONTROLLING_RIDER_SEAT_INDEX");
    /**
     * Defaults to zero. Used for the Llama strength.
     */
    public static final ActorDataType<Integer> STRENGTH = new ActorDataType<>(Integer.class, "STRENGTH");
    /**
     * Defaults to zero. Set's the LLama's max strength value.
     */
    public static final ActorDataType<Integer> STRENGTH_MAX = new ActorDataType<>(Integer.class, "STRENGTH_MAX");
    /**
     * Used for Evokers to define the color of the casting spell.
     */
    public static final ActorDataType<Integer> DATA_SPELL_CASTING_COLOR = new ActorDataType<>(Integer.class, "DATA_SPELL_CASTING_COLOR");
    /**
     * Used for Evokers to define the lifetime ticks of the cast spell.
     */
    public static final ActorDataType<Integer> DATA_LIFETIME_TICKS = new ActorDataType<>(Integer.class, "DATA_LIFETIME_TICKS");
    /**
     * Used to define the pose index for Armor Stands.
     */
    public static final ActorDataType<Integer> POSE_INDEX = new ActorDataType<>(Integer.class, "POSE_INDEX");
    /**
     * Used for End Crystals.
     */
    public static final ActorDataType<Integer> DATA_TICK_OFFSET = new ActorDataType<>(Integer.class, "DATA_TICK_OFFSET");
    /**
     * Whether the actor's name tag should be rendered.
     */
    public static final ActorDataType<Byte> NAMETAG_ALWAYS_SHOW = new ActorDataType<>(Byte.class, "NAMETAG_ALWAYS_SHOW");
    /**
     * The second color index used for various actors that can have multiple different colors, e.g. tropical fish.
     * Not set for actors which do not support having a 2nd color.
     */
    public static final ActorDataType<Byte> COLOR_2_INDEX = new ActorDataType<>(Byte.class, "COLOR_2_INDEX");
    public static final ActorDataType<String> NAME_AUTHOR = new ActorDataType<>(String.class, "NAME_AUTHOR");
    /**
     * The score tag as shown on the actor's nameplate.
     */
    public static final ActorDataType<String> SCORE = new ActorDataType<>(String.class, "SCORE");
    /**
     * Unique entity ID that the balloon string is attached to. Balloons are edu only
     * Disable by setting value to -1.
     */
    public static final ActorDataType<Long> BALLOON_ANCHOR = new ActorDataType<>(Long.class, "BALLOON_ANCHOR");
    /**
     * Probably edu only?
     */
    public static final ActorDataType<Byte> PUFFED_STATE = new ActorDataType<>(Byte.class, "PUFFED_STATE");
    /**
     * Used for Boats.
     */
    public static final ActorDataType<Integer> BUBBLE_TIME = new ActorDataType<>(Integer.class, "BUBBLE_TIME");
    /**
     * The unique entity ID of the player's Agent. (Education Edition only)
     */
    public static final ActorDataType<Long> AGENT = new ActorDataType<>(Long.class, "AGENT");
    /**
     * Used to track sitting data for actor's that support sitting.
     */
    public static final ActorDataType<Float> SITTING_AMOUNT = new ActorDataType<>(Float.class, "SITTING_AMOUNT");
    public static final ActorDataType<Float> SITTING_AMOUNT_PREVIOUS = new ActorDataType<>(Float.class, "SITTING_AMOUNT_PREVIOUS");
    public static final ActorDataType<Integer> EATING_COUNTER = new ActorDataType<>(Integer.class, "EATING_COUNTER");
    public static final ActorDataType<EnumSet<ActorFlags>> FLAGS_2 = new ActorDataType<>(EnumSet.class, "FLAGS_2");
    /**
     * Used to track laying data for actor's that support laying.
     */
    public static final ActorDataType<Float> LAYING_AMOUNT = new ActorDataType<>(Float.class, "LAYING_AMOUNT");
    public static final ActorDataType<Float> LAYING_AMOUNT_PREVIOUS = new ActorDataType<>(Float.class, "LAYING_AMOUNT_PREVIOUS");
    /**
     * Used for Area Effect Cloud data duration.
     */
    public static final ActorDataType<Integer> DATA_DURATION = new ActorDataType<>(Integer.class, "DATA_DURATION");
    /**
     * Used for Area Effect Cloud spawn time.
     */
    @Deprecated
    public static final ActorDataType<Integer> DATA_SPAWN_TIME_DEPRECATED = new ActorDataType<>(Integer.class, "DATA_SPAWN_TIME_deprecated");
    /**
     * Used for Area Effect Cloud change rate.
     *
     * @deprecated since v685
     */
    @Deprecated
    public static final ActorDataType<Float> DATA_CHANGE_RATE = new ActorDataType<>(Float.class, "DATA_CHANGE_RATE");
    /**
     * Used for Area Effect Cloud change on pickup.
     */
    public static final ActorDataType<Float> DATA_CHANGE_ON_PICKUP = new ActorDataType<>(Float.class, "DATA_CHANGE_ON_PICKUP");
    /**
     * Used for Area Effect Cloud pickup count.
     */
    public static final ActorDataType<Integer> DATA_PICKUP_COUNT = new ActorDataType<>(Integer.class, "DATA_PICKUP_COUNT");
    /**
     * Set the interact text of the actor.
     */
    public static final ActorDataType<String> INTERACT_TEXT = new ActorDataType<>(String.class, "INTERACT_TEXT");
    /**
     * Trade tier, Villagers only.
     */
    public static final ActorDataType<Integer> TRADE_TIER = new ActorDataType<>(Integer.class, "TRADE_TIER");
    /**
     * Max trade tier, Villagers only.
     */
    public static final ActorDataType<Integer> MAX_TRADE_TIER = new ActorDataType<>(Integer.class, "MAX_TRADE_TIER");
    /**
     * Required trading experience, Villagers only.
     */
    public static final ActorDataType<Integer> TRADE_EXPERIENCE = new ActorDataType<>(Integer.class, "TRADE_EXPERIENCE");
    /**
     * The skin id, NPC only.
     */
    public static final ActorDataType<Integer> SKIN_ID = new ActorDataType<>(Integer.class, "SKIN_ID");
    public static final ActorDataType<Integer> SPAWNING_FRAMES = new ActorDataType<>(Integer.class, "SPAWNING_FRAMES");
    /**
     * The tick delay for the actor's command block
     */
    public static final ActorDataType<Integer> COMMAND_BLOCK_TICK_DELAY = new ActorDataType<>(Integer.class, "COMMAND_BLOCK_TICK_DELAY");
    /**
     * Whether the actor's command block should execute on the first tick
     */
    public static final ActorDataType<Boolean> COMMAND_BLOCK_EXECUTE_ON_FIRST_TICK = new ActorDataType<>(Boolean.class, "COMMAND_BLOCK_EXECUTE_ON_FIRST_TICK");
    /**
     * The ambient sound interval for the actor
     */
    public static final ActorDataType<Float> AMBIENT_SOUND_INTERVAL = new ActorDataType<>(Float.class, "AMBIENT_SOUND_INTERVAL");
    /**
     * Used for the actor's the ambient sound interval range
     */
    public static final ActorDataType<Float> AMBIENT_SOUND_INTERVAL_RANGE = new ActorDataType<>(Float.class, "AMBIENT_SOUND_INTERVAL_RANGE");
    /**
     * Actor ambient sound event name
     */
    public static final ActorDataType<String> AMBIENT_SOUND_EVENT_NAME = new ActorDataType<>(String.class, "AMBIENT_SOUND_EVENT_NAME");
    /**
     * The actor's fall damage multiplier, defaults to 1.0.
     */
    public static final ActorDataType<Float> FALL_DAMAGE_MULTIPLIER = new ActorDataType<>(Float.class, "FALL_DAMAGE_MULTIPLIER");
    public static final ActorDataType<String> NAME_RAW_TEXT = new ActorDataType<>(String.class, "NAME_RAW_TEXT");
    /**
     * Whether the actor can ride the defined target actor
     */
    public static final ActorDataType<Boolean> CAN_RIDE_TARGET = new ActorDataType<>(Boolean.class, "CAN_RIDE_TARGET");
    /**
     * Trade discount stuff related to Villagers.
     */
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
    /**
     * Whether this actor is buoyant.
     */
    public static final ActorDataType<Boolean> IS_BUOYANT = new ActorDataType<>(Boolean.class, "IS_BUOYANT");
    public static final ActorDataType<String> BASE_RUNTIME_ID = new ActorDataType<>(String.class, "BASE_RUNTIME_ID");
    /**
     * Custom properties from the <pre>PropertyComponent</pre>.
     *
     * @deprecated v557
     */
    @Deprecated
    public static final ActorDataType<NbtMap> UPDATE_PROPERTIES = new ActorDataType<>(NbtMap.class, "UPDATE_PROPERTIES");
    /**
     * Set the strength of the freezing effect
     */
    public static final ActorDataType<Float> FREEZING_EFFECT_STRENGTH = new ActorDataType<>(Float.class, "FREEZING_EFFECT_STRENGTH");
    /**
     * Buoyancy JSON actor data, used for actors that move in liquid.
     */
    public static final ActorDataType<String> BUOYANCY_DATA = new ActorDataType<>(String.class, "BUOYANCY_DATA");
    public static final ActorDataType<Integer> GOAT_HORN_COUNT = new ActorDataType<>(Integer.class, "GOAT_HORN_COUNT");
    /**
     * @since v503
     */
    public static final ActorDataType<Float> MOVEMENT_SOUND_DISTANCE_OFFSET = new ActorDataType<>(Float.class, "MOVEMENT_SOUND_DISTANCE_OFFSET");
    /**
     * Warden only.
     *
     * @since v503
     */
    public static final ActorDataType<Integer> HEARTBEAT_INTERVAL_TICKS = new ActorDataType<>(Integer.class, "HEARTBEAT_INTERVAL_TICKS");
    /**
     * Warden only.
     *
     * @since v503
     */
    public static final ActorDataType<SoundEvent> HEARTBEAT_SOUND_EVENT = new ActorDataType<>(SoundEvent.class, "HEARTBEAT_SOUND_EVENT");
    /**
     * The player's last death position.
     *
     * @since v527
     */
    public static final ActorDataType<Vector3i> PLAYER_LAST_DEATH_POS = new ActorDataType<>(Vector3i.class, "PLAYER_LAST_DEATH_POS");
    /**
     * The player's last death dimension type.
     *
     * @since v527
     */
    public static final ActorDataType<Integer> PLAYER_LAST_DEATH_DIMENSION = new ActorDataType<>(Integer.class, "PLAYER_LAST_DEATH_DIMENSION");
    /**
     * Whether the player has died.
     *
     * @since v527
     */
    public static final ActorDataType<Boolean> PLAYER_HAS_DIED = new ActorDataType<>(Boolean.class, "PLAYER_HAS_DIED");
    /**
     * @since v594
     */
    public static final ActorDataType<Vector3f> COLLISION_BOX = new ActorDataType<>(Vector3f.class, "COLLISION_BOX");
    /**
     * Visible mob effects data.
     *
     * @since v685
     */
    public static final ActorDataType<Long> VISIBLE_MOB_EFFECTS = new ActorDataType<>(Long.class, "VISIBLE_MOB_EFFECTS");
    /**
     * The filtered name of this actor, see profanity filtering.
     *
     * @since v776
     */
    public static final ActorDataType<String> FILTERED_NAME = new ActorDataType<>(String.class, "FILTERED_NAME");
    /**
     * The actor's bed enter position.
     *
     * @since v776
     */
    public static final ActorDataType<Vector3f> BED_ENTER_POSITION = new ActorDataType<>(Vector3f.class, "BED_ENTER_POSITION");
    /**
     * Camera stuff.
     *
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
    /**
     * @since v975
     */
    public static final ActorDataType<Long> RESERVED_139 = new ActorDataType<>(Long.class, "RESERVED_139");
    /**
     * The actor's maximum nameplate render distance in blocks. The actor's nameplate won't be rendered for the client
     * when the defined render distance max value is exceeded. Defaults to 64.0.
     *
     * @since v975
     */
    public static final ActorDataType<Float> NAMEPLATE_RENDER_DISTANCE_MAX = new ActorDataType<>(Float.class, "NAMEPLATE_RENDER_DISTANCE_MAX");
}
