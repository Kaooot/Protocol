package org.cloudburstmc.protocol.bedrock.data.command;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum CommandOutputType {

    NONE("None"),
    LAST_OUTPUT("LastOutput"),
    SILENT("Silent"),
    ALL_OUTPUT("AllOutput"),
    DATA_SET("DataSet");

    private final String serializeName;

    public static CommandOutputType fromName(String serializeName) {
        for (CommandOutputType value : VALUES) {
            if (value.serializeName.equalsIgnoreCase(serializeName)) {
                return value;
            }
        }
        return null;
    }

    private static final CommandOutputType[] VALUES = values();

    public static CommandOutputType from(int ordinal) {
        if (ordinal >= 0 && ordinal < VALUES.length) {
            return VALUES[ordinal];
        }
        throw new UnsupportedOperationException("Detected unknown CommandOutputType ID: " + ordinal);
    }
}