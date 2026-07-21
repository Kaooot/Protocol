package org.cloudburstmc.protocol.bedrock.data;

import java.lang.String;
import java.util.HashMap;
import java.util.Map;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum PayloadType {
  INVALID("invalid"),

  CLEAR_DEBUG_MARKERS("cleardebugmarkers"),

  ADD_DEBUG_MARKER_CUBE("adddebugmarkercube");

  private static final Map<String, PayloadType> SERIALIZE_NAMES = new HashMap<>(values().length);

  static {
    for (PayloadType value : values()) {
      SERIALIZE_NAMES.put(value.getSerializeName(), value);
    }
  }

  private final String serializeName;

  public static PayloadType fromName(String serializeName) {
    return SERIALIZE_NAMES.get(serializeName);
  }
}
