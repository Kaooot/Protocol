package org.cloudburstmc.protocol.bedrock.data;

import java.lang.String;
import java.util.HashMap;
import java.util.Map;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ColorAttributeOperation {
  OVERRIDE("override"),

  ALPHA_BLEND("alpha_blend"),

  ADD("add"),

  SUBTRACT("subtract"),

  MULTIPLY("multiply");

  private static final Map<String, ColorAttributeOperation> SERIALIZE_NAMES = new HashMap<>(values().length);

  static {
    for (ColorAttributeOperation value : values()) {
      SERIALIZE_NAMES.put(value.getSerializeName(), value);
    }
  }

  private final String serializeName;

  public static ColorAttributeOperation fromName(String serializeName) {
    return SERIALIZE_NAMES.get(serializeName);
  }
}
