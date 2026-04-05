package org.cloudburstmc.protocol.bedrock.data.payload.locatorbar;

/**
 * @author Kaooot
 */
public class VanillaWaypointManagerConstants {

    public enum ImageType {
        SQUARE,
        CIRCLE,
        SMALL_SQUARE,
        SMALL_STAR,
        TINY_SQUARE,
        TINY_STAR;

        private static final ImageType[] VALUES = values();

        public static ImageType from(int ordinal) {
            if (ordinal >= 0 && ordinal < VALUES.length) {
                return VALUES[ordinal];
            }
            throw new UnsupportedOperationException("Detected unknown VanillaWaypointManagerConstants.ImageType ID: " + ordinal);
        }
    }

    public enum UpdateFlag {
        WORLD_POS,
        VISIBILITY,
        TEXTURE,
        COLOR,
        CLIENT_POSITION_AUTHORITY;

        public static final UpdateFlag[] VALUES = values();

        public static UpdateFlag from(int ordinal) {
            if (ordinal >= 0 && ordinal < VALUES.length) {
                return VALUES[ordinal];
            }
            throw new UnsupportedOperationException("Detected unknown VanillaWaypointManagerConstants.UpdateFlag ID: " + ordinal);
        }
    }
}