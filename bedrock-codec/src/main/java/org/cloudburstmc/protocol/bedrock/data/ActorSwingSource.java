package org.cloudburstmc.protocol.bedrock.data;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @author Kaooot
 */
@Getter
@RequiredArgsConstructor
public enum ActorSwingSource {

    NONE("None"),
    BUILD("Build"),
    MINE("Mine"),
    INTERACT("Interact"),
    ATTACK("Attack"),
    USE_ITEM("UseItem"),
    THROW_ITEM("ThrowItem"),
    DROP_ITEM("DropItem"),
    EVENT("Event");

    private final String id;

    private static final ActorSwingSource[] VALUES = values();

    public static ActorSwingSource from(String id) {
        for (ActorSwingSource value : VALUES) {
            if (value.getId().equalsIgnoreCase(id)) {
                return value;
            }
        }
        throw new UnsupportedOperationException("Detected unknown ActorSwingSource ID: " + id);
    }
}