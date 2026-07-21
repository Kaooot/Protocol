package org.cloudburstmc.protocol.bedrock.data;

import java.lang.String;
import java.util.HashMap;
import java.util.Map;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum HudElement {
  PAPER_DOLL("paperdoll"),

  ARMOR("armor"),

  TOOL_TIPS("tooltips"),

  TOUCH_CONTROLS("touchcontrols"),

  CROSSHAIR("crosshair"),

  HOT_BAR("hotbar"),

  HEALTH("health"),

  PROGRESS_BAR("progressbar"),

  HUNGER("hunger"),

  AIR_BUBBLES("airbubbles"),

  HORSE_HEALTH("horsehealth"),

  STATUS_EFFECTS("statuseffects"),

  ITEM_TEXT("itemtext");

  private static final Map<String, HudElement> SERIALIZE_NAMES = new HashMap<>(values().length);

  static {
    for (HudElement value : values()) {
      SERIALIZE_NAMES.put(value.getSerializeName(), value);
    }
  }

  private final String serializeName;

  public static HudElement fromName(String serializeName) {
    return SERIALIZE_NAMES.get(serializeName);
  }
}
