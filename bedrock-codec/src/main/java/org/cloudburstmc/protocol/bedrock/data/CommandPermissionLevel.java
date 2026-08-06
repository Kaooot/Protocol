package org.cloudburstmc.protocol.bedrock.data;

import java.lang.String;
import java.lang.UnsupportedOperationException;
import java.util.HashMap;
import java.util.Map;
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

  private static final CommandPermissionLevel[] VALUES = values();

  private static final Map<String, CommandPermissionLevel> SERIALIZE_NAMES = new HashMap<>(values().length);

  static {
    for (CommandPermissionLevel value : values()) {
      SERIALIZE_NAMES.put(value.getSerializeName(), value);
    }
  }

  private final String serializeName;

  public static CommandPermissionLevel from(int ordinal) {
    if (ordinal >= 0 && ordinal < VALUES.length) {
      return VALUES[ordinal];
    }
    throw new UnsupportedOperationException("Detected unknown CommandPermissionLevel ID: " + ordinal);
  }

  public static CommandPermissionLevel fromName(String serializeName) {
    return SERIALIZE_NAMES.get(serializeName);
  }
}
