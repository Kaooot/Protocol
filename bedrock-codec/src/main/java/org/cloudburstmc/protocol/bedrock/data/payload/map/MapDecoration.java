package org.cloudburstmc.protocol.bedrock.data.payload.map;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Kaooot
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MapDecoration {

    private Type imageType;
    private int rotation;
    private int x;
    private int y;
    private String label;
    private int color;

    public enum Type {
        MARKER_WHITE,
        MARKER_GREEN,
        MARKER_RED,
        MARKER_BLUE,
        X_WHITE,
        TRIANGLE_RED,
        SQUARE_WHITE,
        MARKER_SIGN,
        MARKER_PINK,
        MARKER_ORANGE,
        MARKER_YELLOW,
        MARKER_TEAL,
        TRIANGLE_GREEN,
        SMALL_SQUARE_WHITE,
        MANSION,
        MONUMENT,
        NO_DRAW,
        VILLAGE_DESERT,
        VILLAGE_PLAINS,
        VILLAGE_SAVANNA,
        VILLAGE_SNOWY,
        VILLAGE_TAIGA,
        JUNGLE_TEMPLE,
        WITCH_HUT,
        /**
         * @since v671
         */
        TRIAL_CHAMBERS,
        /**
         * @since v2192
         */
        ABANDONED_CAMP,
        /**
         * @since v2192
         */
        BURIED_ANCIENT_CITY,
        /**
         * @since v2192
         */
        BURIED_MINESHAFT,
        /**
         * @since v2192
         */
        DESERT_PYRAMID,
        /**
         * @since v2192
         */
        WARM_OCEAN_RUINS,
        COUNT
    }
}