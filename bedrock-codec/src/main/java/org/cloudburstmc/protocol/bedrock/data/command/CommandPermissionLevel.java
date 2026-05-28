package org.cloudburstmc.protocol.bedrock.data.command;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum CommandPermissionLevel {

    ANY("any"),
    GAME_DIRECTORS("gamedirectors"),
    ADMIN("admin"),
    HOST("host"),
    OWNER("owner"),
    INTERNAL("internal");

    private final String id;

    private static final CommandPermissionLevel[] VALUES = values();

    public static CommandPermissionLevel from(int ordinal) {
        if (ordinal >= 0 && ordinal < VALUES.length) {
            return VALUES[ordinal];
        }
        throw new UnsupportedOperationException("Detected unknown CommandPermissionLevel ID: " + ordinal);
    }

    public static CommandPermissionLevel from(String name) {
        System.out.println("read command permission level from string: " + name);
        for (CommandPermissionLevel value : VALUES) {
            if (value.getId().equalsIgnoreCase(name)) {
                return value;
            }
        }
        throw new UnsupportedOperationException("Detected unknown CommandPermissionLevel Name: " + name);
    }
}