package org.cloudburstmc.protocol.bedrock.data;

import java.lang.String;
import java.util.HashMap;
import java.util.Map;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum PlayerAuthInputData {
  ASCEND("ascend"),

  DESCEND("descend"),

  JUMP_DOWN("jumpdown"),

  SPRINT_DOWN("sprintdown"),

  CHANGE_HEIGHT("changeheight"),

  JUMPING("jumping"),

  AUTO_JUMPING_IN_WATER("autojumpinginwater"),

  SNEAKING("sneaking"),

  SNEAK_DOWN("sneakdown"),

  UP("up"),

  DOWN("down"),

  LEFT("left"),

  RIGHT("right"),

  UP_LEFT("upleft"),

  UP_RIGHT("upright"),

  WANT_UP("wantup"),

  WANT_DOWN("wantdown"),

  WANT_DOWN_SLOW("wantdownslow"),

  WANT_UP_SLOW("wantupslow"),

  SPRINTING("sprinting"),

  ASCEND_BLOCK("ascendblock"),

  DESCEND_BLOCK("descendblock"),

  SNEAK_TOGGLE_DOWN("sneaktoggledown"),

  PERSIST_SNEAK("persistsneak"),

  START_SPRINTING("startsprinting"),

  STOP_SPRINTING("stopsprinting"),

  START_SNEAKING("startsneaking"),

  STOP_SNEAKING("stopsneaking"),

  START_SWIMMING("startswimming"),

  STOP_SWIMMING("stopswimming"),

  START_JUMPING("startjumping"),

  START_GLIDING("startgliding"),

  STOP_GLIDING("stopgliding"),

  PERFORM_ITEM_INTERACTION("performiteminteraction"),

  PERFORM_BLOCK_ACTIONS("performblockactions"),

  PERFORM_ITEM_STACK_REQUEST("performitemstackrequest"),

  HANDLED_TELEPORT("handledteleport"),

  EMOTING("emoting"),

  MISSED_SWING("missedswing"),

  START_CRAWLING("startcrawling"),

  STOP_CRAWLING("stopcrawling"),

  START_FLYING("startflying"),

  STOP_FLYING("stopflying"),

  CLIENT_ACK_SERVER_DATA("clientackserverdata"),

  IS_IN_CLIENT_PREDICTED_VEHICLE("isinclientpredictedvehicle"),

  PADDLING_LEFT("paddlingleft"),

  PADDLING_RIGHT("paddlingright"),

  BLOCK_BREAKING_DELAY_ENABLED("blockbreakingdelayenabled"),

  HORIZONTAL_COLLISION("horizontalcollision"),

  VERTICAL_COLLISION("verticalcollision"),

  DOWN_LEFT("downleft"),

  DOWN_RIGHT("downright"),

  START_USING_ITEM("startusingitem"),

  START_SPIN_ATTACK("startspinattack"),

  STOP_SPIN_ATTACK("stopspinattack"),

  IS_HOTBAR_ONLY_TOUCH("ishotbaronlytouch"),

  JUMP_RELEASED_RAW("jumpreleasedraw"),

  JUMP_PRESSED_RAW("jumppressedraw"),

  JUMP_CURRENT_RAW("jumpcurrentraw"),

  SNEAK_RELEASED_RAW("sneakreleasedraw"),

  SNEAK_PRESSED_RAW("sneakpressedraw"),

  SNEAK_CURRENT_RAW("sneakcurrentraw"),

  INTERNAL_UPDATE("internalupdate");

  private static final Map<String, PlayerAuthInputData> SERIALIZE_NAMES = new HashMap<>(values().length);

  static {
    for (PlayerAuthInputData value : values()) {
      SERIALIZE_NAMES.put(value.getSerializeName(), value);
    }
  }

  private final String serializeName;

  public static PlayerAuthInputData fromName(String serializeName) {
    return SERIALIZE_NAMES.get(serializeName);
  }
}
