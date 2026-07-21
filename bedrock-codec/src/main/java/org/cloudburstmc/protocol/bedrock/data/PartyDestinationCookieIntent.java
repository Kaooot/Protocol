package org.cloudburstmc.protocol.bedrock.data;

import java.lang.String;
import java.util.HashMap;
import java.util.Map;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum PartyDestinationCookieIntent {
  NOTIFY("notify"),

  OPT_IN("optin"),

  OPT_OUT("optout");

  private static final Map<String, PartyDestinationCookieIntent> SERIALIZE_NAMES = new HashMap<>(values().length);

  static {
    for (PartyDestinationCookieIntent value : values()) {
      SERIALIZE_NAMES.put(value.getSerializeName(), value);
    }
  }

  private final String serializeName;

  public static PartyDestinationCookieIntent fromName(String serializeName) {
    return SERIALIZE_NAMES.get(serializeName);
  }
}
