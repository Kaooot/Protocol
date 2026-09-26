package org.cloudburstmc.protocol.bedrock.data.command;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@Getter
@RequiredArgsConstructor
public enum CommandOriginType {

    PLAYER("player"),
    COMMAND_BLOCK("commandblock"),
    MINECART_COMMAND_BLOCK("minecartcommandnlock"),
    DEV_CONSOLE("devconsole"),
    TEST("test"),
    AUTOMATION_PLAYER("automationplayer"),
    CLIENT_AUTOMATION("clientautomation"),
    DEDICATED_SERVER("dedicatedserver"),
    ENTITY("entity"),
    VIRTUAL("virtual"),
    GAME_ARGUMENT("gameargument"),
    ENTITY_SERVER("entityserver"),
    PRECOMPILED("precompiled"),
    GAME_DIRECTOR_ENTITY_SERVER("gamedirectorentityserver"),
    SCRIPTING("scripting"),
    EXECUTE_CONTEXT("executecontext");

    private static final Map<String, CommandOriginType> SERIALIZE_NAMES = new HashMap<>(values().length);

    static {
        for (CommandOriginType value : values()) {
            SERIALIZE_NAMES.put(value.getSerializeName(), value);
        }
    }

    private final String serializeName;

    public static CommandOriginType fromName(String serializeName) {
        return SERIALIZE_NAMES.get(serializeName);
    }

    private static final CommandOriginType[] VALUES = values();

    public static CommandOriginType from(int ordinal) {
        if (ordinal >= 0 && ordinal < VALUES.length) {
            return VALUES[ordinal];
        }
        throw new UnsupportedOperationException("Detected unknown CommandOriginType ID: " + ordinal);
    }
}