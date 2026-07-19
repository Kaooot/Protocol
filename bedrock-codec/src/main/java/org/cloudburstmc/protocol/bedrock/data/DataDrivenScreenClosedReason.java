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
public enum DataDrivenScreenClosedReason {
  PROGRAMMATIC_CLOSE("programmaticclose"),

  PROGRAMMATIC_CLOSE_ALL("programmaticcloseall"),

  CLIENT_CANCELED("clientcanceled"),

  USER_BUSY("userbusy"),

  INVALID_FORM("invalidform");

  private static final Map<String, DataDrivenScreenClosedReason> SERIALIZE_NAMES = new HashMap<>(values().length);

  static {
    for (DataDrivenScreenClosedReason value : values()) {
      SERIALIZE_NAMES.put(value.getSerializeName(), value);
    }
  }

  private final String serializeName;

  public static DataDrivenScreenClosedReason fromName(String serializeName) {
    return SERIALIZE_NAMES.get(serializeName);
  }
}
