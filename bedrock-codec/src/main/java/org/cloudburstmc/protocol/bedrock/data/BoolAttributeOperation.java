package org.cloudburstmc.protocol.bedrock.data;

import java.lang.String;
import java.util.HashMap;
import java.util.Map;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum BoolAttributeOperation {
  OVERRIDE("override"),

  ALPHA_BLEND("alpha_blend"),

  AND("and"),

  NAND("nand"),

  OR("or"),

  NOR("nor"),

  XOR("xor"),

  XNOR("xnor");

  private static final Map<String, BoolAttributeOperation> SERIALIZE_NAMES = new HashMap<>(values().length);

  static {
    for (BoolAttributeOperation value : values()) {
      SERIALIZE_NAMES.put(value.getSerializeName(), value);
    }
  }

  private final String serializeName;

  public static BoolAttributeOperation fromName(String serializeName) {
    return SERIALIZE_NAMES.get(serializeName);
  }
}
