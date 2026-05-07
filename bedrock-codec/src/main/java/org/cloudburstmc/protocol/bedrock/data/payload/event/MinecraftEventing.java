package org.cloudburstmc.protocol.bedrock.data.payload.event;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @author Kaooot
 */
public final class MinecraftEventing {

    @Getter
    @RequiredArgsConstructor
    public enum AchievementIds {

        CHEST_FULL_OF_COBBLESTONE(7),
        DIAMOND_FOR_YOU(10),
        IRON_BELLY(20),
        IRON_MAN(21),
        ON_A_RAIL(29),
        OVERKILL(30),
        RETURN_TO_SENDER(37),
        SNIPER_DUEL(38),
        STAYIN_FROSTY(39),
        TAKE_INVENTORY(40),
        MAP_ROOM(50),
        FREIGHT_STATION(52),
        SMELT_EVERYTHING(53),
        TASTE_OF_YOUR_OWN_MEDICINE(54),
        WHEN_PIGS_FLY(56),
        INCEPTION(58),
        ARTIFICIAL_SELECTION(60),
        FREE_DIVER(61),
        SPAWN_THE_WITHER(62),
        BEACONATOR(63),
        GREAT_VIEW(64),
        SUPER_SONIC(65),
        THE_END_AGAIN(66),
        TREASURE_HUNTER(67),
        SHOOTING_STAR(68),
        FASHION_SHOW(69),
        SELF_PUBLISHED_AUTHOR(71),
        ALTERNATIVE_FUEL(72),
        SLEEP_WITH_THE_FISHES(73),
        CASTAWAY(74),
        IM_A_MARINE_BIOLOGIST(75),
        SAIL_THE_7_SEAS(76),
        ME_GOLD(77),
        AHOY(78),
        ATLANTIS(79),
        ONE_PICKLE_TWO_PICKLE_SEA_PICKLE_FOUR(80),
        DO_A_BARREL_ROLL(81),
        MOSKSTRAUMEN(82),
        ECHOLOCATION(83),
        WHERE_HAVE_YOU_BEEN(84),
        TOP_OF_THE_WORLD(85),
        FRUIT_ON_THE_LOOM(86),
        SOUND_THE_ALARM(87),
        BUY_LOW_SELL_HIGH(88),
        DISENCHANTED(89),
        TIME_FOR_STEW(90),
        BEE_OUR_GUEST(91),
        TOTAL_BEE_LOCATION(92),
        STICKY_SITUATION(93),
        COVER_ME_IN_DEBRIS(94),
        FLOAT_YOUR_GOAT(95),
        FRIEND(96),
        WAX_ON_WAX_OFF(97),
        STRIDER_RIDDEN_IN_LAVA_IN_OVERWORLD(98),
        GOAT_HORN_ACQUIRED(99),
        JUKEBOX_USED_IN_MEADOWS(100),
        TRADED_AT_WORLD_HEIGHT(101),
        SURVIVED_FALL_FROM_WORLD_HEIGHT(102),
        SNEAK_CLOSE_TO_SCULK_SENSOR(103),
        IT_SPREADS(104),
        BIRTHDAY_SONG(105),
        WITH_OUR_POWERS_COMBINED(106),
        PLANTING_THE_PAST(107),
        CAREFUL_RESTORATION(108),
        REVAULTING(109),
        CRAFTERS_CRAFTING_CRAFTERS(110),
        WHO_NEEDS_ROCKETS(111),
        OVER_OVERKILL(112),
        HEART_TRANSPLANTER(113),
        STAY_HYDRATED(114),
        MOB_KABOB(115);

        private final int id;

        private static final AchievementIds[] VALUES = values();

        public static AchievementIds from(int id) {
            for (AchievementIds value : VALUES) {
                if (value.getId() == id) {
                    return value;
                }
            }
            throw new UnsupportedOperationException("Detected unknown MinecraftEventing::AchievementIds ID: " + id);
        }
    }

    public enum InteractionType {

        BREEDING,
        TAMING,
        CURING,
        CRAFTED,
        SHEARING,
        MILKING,
        TRADING,
        FEEDING,
        IGNITING,
        COLORING,
        NAMING,
        LEASHING,
        UNLEASHING,
        PET_SLEEP,
        TRUSTING,
        COMMANDING,
        /**
         * @since v990
         */
        EQUIPPING;

        private static final InteractionType[] VALUES = values();

        public static InteractionType from(int id) {
            final int ordinal = id - 1;
            if (ordinal < 0 || ordinal >= VALUES.length) {
                throw new UnsupportedOperationException("Detected unknown MinecraftEventing::InteractionType ID: " + id);
            }
            return VALUES[ordinal];
        }
    }

    public enum POIBlockInteractionType {

        NONE,
        EXTEND,
        CLONE,
        LOCK,
        CREATE,
        CREATE_LOCATOR,
        RENAME,
        ITEM_PLACED,
        ITEM_REMOVED,
        COOKING,
        DOUSING,
        LIGHTING,
        HAYSTACK,
        FILLED,
        EMPTIED,
        ADD_DYE,
        DYE_ITEM,
        CLEAR_ITEM,
        ENCHANT_ARROW,
        COMPOST_ITEM_PLACED,
        RECOVERED_BONEMEAL,
        BOOK_PLACED,
        BOOK_OPENED,
        DISENCHANT,
        REPAIR,
        DISENCHANT_AND_REPAIR;

        private static final POIBlockInteractionType[] VALUES = values();

        public static POIBlockInteractionType from(int ordinal) {
            if (ordinal < 0 || ordinal >= VALUES.length) {
                throw new UnsupportedOperationException("Detected unknown MinecraftEventing::POIBlockInteractionType ID: " + ordinal);
            }
            return VALUES[ordinal];
        }
    }
}