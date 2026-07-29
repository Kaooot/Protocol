package org.cloudburstmc.protocol.bedrock.data.actor;

import java.lang.String;
import java.util.HashMap;
import java.util.Map;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Sent within the AnimatePacket to specify the source when AnimatePacketPayload::Action is set to 'Swing'
 */
@Getter
@RequiredArgsConstructor
public enum ActorSwingSource {
  NONE("none"),

  BUILD("build"),

  MINE("mine"),

  INTERACT("interact"),

  ATTACK("attack"),

  USE_ITEM("useitem"),

  THROW_ITEM("throwitem"),

  DROP_ITEM("dropitem"),

  EVENT("event");

  private static final Map<String, ActorSwingSource> SERIALIZE_NAMES = new HashMap<>(values().length);

  static {
    for (ActorSwingSource value : values()) {
      SERIALIZE_NAMES.put(value.getSerializeName(), value);
    }
  }

  private final String serializeName;

  public static ActorSwingSource fromName(String serializeName) {
    return SERIALIZE_NAMES.get(serializeName);
  }
}
