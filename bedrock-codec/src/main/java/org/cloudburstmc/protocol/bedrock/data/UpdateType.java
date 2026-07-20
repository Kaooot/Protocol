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
public enum UpdateType {
  CLEAR_OVERRIDES("clearoverrides"),

  REMOVE_OVERRIDE("removeoverride"),

  SET_INT_OVERRIDE("setintoverride"),

  SET_FLOAT_OVERRIDE("setfloatoverride");

  private static final Map<String, UpdateType> SERIALIZE_NAMES = new HashMap<>(values().length);

  static {
    for (UpdateType value : values()) {
      SERIALIZE_NAMES.put(value.getSerializeName(), value);
    }
  }

  private final String serializeName;

  public static UpdateType fromName(String serializeName) {
    return SERIALIZE_NAMES.get(serializeName);
  }
}
