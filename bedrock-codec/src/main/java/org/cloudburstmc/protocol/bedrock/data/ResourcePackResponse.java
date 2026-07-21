package org.cloudburstmc.protocol.bedrock.data;

import java.lang.String;
import java.util.HashMap;
import java.util.Map;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ResourcePackResponse {
  CANCEL("cancel"),

  DOWNLOADING("downloading"),

  DOWNLOADING_FINISHED("downloadingfinished"),

  RESOURCE_PACK_STACK_FINISHED("resourcepackstackfinished");

  private static final Map<String, ResourcePackResponse> SERIALIZE_NAMES = new HashMap<>(values().length);

  static {
    for (ResourcePackResponse value : values()) {
      SERIALIZE_NAMES.put(value.getSerializeName(), value);
    }
  }

  private final String serializeName;

  public static ResourcePackResponse fromName(String serializeName) {
    return SERIALIZE_NAMES.get(serializeName);
  }
}
