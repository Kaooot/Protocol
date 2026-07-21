package org.cloudburstmc.protocol.bedrock.data;

import java.lang.String;
import java.util.HashMap;
import java.util.Map;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum TrustedSkinFlag {
  UNSET("unset"),

  FALSE("false"),

  TRUE("true");

  private static final Map<String, TrustedSkinFlag> SERIALIZE_NAMES = new HashMap<>(values().length);

  static {
    for (TrustedSkinFlag value : values()) {
      SERIALIZE_NAMES.put(value.getSerializeName(), value);
    }
  }

  private final String serializeName;

  public static TrustedSkinFlag fromName(String serializeName) {
    return SERIALIZE_NAMES.get(serializeName);
  }
}
