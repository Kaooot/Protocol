package org.cloudburstmc.protocol.bedrock.data;

import java.lang.String;
import java.util.HashMap;
import java.util.Map;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ScorePacketEntryAction {
  REMOVE("remove"),

  CHANGE_PLAYER("changeplayer"),

  CHANGE_ENTITY("changeentity"),

  CHANGE_FAKE_PLAYER("changefakeplayer");

  private static final Map<String, ScorePacketEntryAction> SERIALIZE_NAMES = new HashMap<>(values().length);

  static {
    for (ScorePacketEntryAction value : values()) {
      SERIALIZE_NAMES.put(value.getSerializeName(), value);
    }
  }

  private final String serializeName;

  public static ScorePacketEntryAction fromName(String serializeName) {
    return SERIALIZE_NAMES.get(serializeName);
  }
}
