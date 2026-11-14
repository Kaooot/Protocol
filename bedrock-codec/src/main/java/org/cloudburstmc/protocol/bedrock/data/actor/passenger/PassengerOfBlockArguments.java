package org.cloudburstmc.protocol.bedrock.data.actor.passenger;

import lombok.Data;
import org.cloudburstmc.math.vector.Vector3f;
import org.cloudburstmc.math.vector.Vector3i;

import java.util.UUID;

@Data
public class PassengerOfBlockArguments {

    private Vector3i blockPos;
    private Vector3f offset;
    private float rotation;
    private float rotationLimit;
    private EmoteType emoteType;
    /**
     * @since v2259
     */
    private long blockType;
    /**
     * @since v2259
     */
    private UUID packUUID;

    public enum EmoteType {
        STANDING,
        RIDING,
        LAYING;

        private static final EmoteType[] VALUES = values();

        public static EmoteType from(int ordinal) {
            if (ordinal >= 0 && ordinal < VALUES.length) {
                return VALUES[ordinal];
            }
            throw new UnsupportedOperationException("Detected unknown EmoteType ID: " + ordinal);
        }
    }
}