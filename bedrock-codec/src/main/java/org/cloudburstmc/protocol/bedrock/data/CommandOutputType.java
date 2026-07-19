package org.cloudburstmc.protocol.bedrock.data;

import java.lang.String;
import java.util.HashMap;
import java.util.Map;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Auto generated from 1.26.40-beta.31 (v2168)
 */
@Getter
@RequiredArgsConstructor
public enum CommandOutputType {
  NONE("none"),

  LAST_OUTPUT("lastoutput"),

  SILENT("silent"),

  ALL_OUTPUT("alloutput"),

  DATA_SET("dataset");

  private static final Map<String, CommandOutputType> SERIALIZE_NAMES = new HashMap<>(values().length);

  static {
    for (CommandOutputType value : values()) {
      SERIALIZE_NAMES.put(value.getSerializeName(), value);
    }
  }

  private final String serializeName;

  public static CommandOutputType fromName(String serializeName) {
    return SERIALIZE_NAMES.get(serializeName);
  }
}
