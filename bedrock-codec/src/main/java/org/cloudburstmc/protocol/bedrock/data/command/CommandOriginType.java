package org.cloudburstmc.protocol.bedrock.data.command;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum CommandOriginType {
    PLAYER("Player"),
    COMMAND_BLOCK("CommandBlock"),
    MINECART_COMMAND_BLOCK("MinecartCommandBlock"),
    DEV_CONSOLE("DevConsole"),
    TEST("Test"),
    AUTOMATION_PLAYER("AutomationPlayer"),
    CLIENT_AUTOMATION("ClientAutomation"),
    DEDICATED_SERVER("DedicatedServer"),
    ENTITY("Entity"),
    VIRTUAL("Virtual"),
    GAME_ARGUMENT("GameArgument"),
    ENTITY_SERVER("EntityServer"),
    PRECOMPILED("Precompiled"),
    GAME_DIRECTOR_ENTITY_SERVER("GameDirectorEntityServer"),
    SCRIPTING("Scripting"),
    EXECUTE_CONTEXT("ExecuteContext");

    private final String id;

    private static final CommandOriginType[] VALUES = values();

    public static CommandOriginType from(String id) {
        for (CommandOriginType value : VALUES) {
            if (value.getId().equalsIgnoreCase(id)) {
                return value;
            }
        }
        throw new UnsupportedOperationException("Detected unknown CommandOriginType ID: " + id);
    }
}