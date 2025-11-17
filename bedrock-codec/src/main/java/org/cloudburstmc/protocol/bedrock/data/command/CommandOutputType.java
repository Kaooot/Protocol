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

    private final String id;

    private static final CommandOutputType[] VALUES = values();

    public static CommandOutputType from(String id) {
        for (CommandOutputType value : VALUES) {
            if (value.getId().equalsIgnoreCase(id)) {
                return value;
            }
        }
        throw new UnsupportedOperationException("Detected unknown CommandOutputType ID: " + id);
    }
}
