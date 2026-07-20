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
public enum FloatAttributeOperation {
  OVERRIDE("override"),

  ALPHA_BLEND("alpha_blend"),

  ADD("add"),

  SUBTRACT("subtract"),

  MULTIPLY("multiply"),

  MINIMUM("minimum"),

  MAXIMUM("maximum");

  private static final Map<String, FloatAttributeOperation> SERIALIZE_NAMES = new HashMap<>(values().length);

  static {
    for (FloatAttributeOperation value : values()) {
      SERIALIZE_NAMES.put(value.getSerializeName(), value);
    }
  }

  private final String serializeName;

  public static FloatAttributeOperation fromName(String serializeName) {
    return SERIALIZE_NAMES.get(serializeName);
  }
}
