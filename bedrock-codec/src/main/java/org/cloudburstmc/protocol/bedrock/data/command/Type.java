package org.cloudburstmc.protocol.bedrock.data.command;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@Getter
@RequiredArgsConstructor
public enum Type {

    PLAYER("player"),
    DEV_CONSOLE("devconsole"),
    TEST("test"),
    AUTOMATION_PLAYER("automationplayer");

    private static final Map<String, Type> SERIALIZE_NAMES = new HashMap<>(values().length);

    static {
        for (Type value : values()) {
            SERIALIZE_NAMES.put(value.getSerializeName(), value);
        }
    }

    private final String serializeName;

    public static Type fromName(String serializeName) {
        return SERIALIZE_NAMES.get(serializeName);
    }
}