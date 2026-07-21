package org.cloudburstmc.protocol.bedrock.data;

import java.lang.String;
import java.util.HashMap;
import java.util.Map;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum CurrentCmdVersion {
  INVALID("invalid"),

  INITIAL("initial"),

  TP_ROTATION_CLAMPING("tprotationclamping"),

  NEW_BEDROCK_CMD_SYSTEM("newbedrockcmdsystem"),

  EXECUTE_USES_VEC3("executeusesvec3"),

  CLONE_FIXES("clonefixes"),

  UPDATE_AQUATIC("updateaquatic"),

  ENTITY_SELECTOR_USES_VEC3("entityselectorusesvec3"),

  CONTAINERS_DONT_DROP_ITEMS_ANYMORE("containersdontdropitemsanymore"),

  FILTERS_OBEY_DIMENSIONS("filtersobeydimensions"),

  EXECUTE_AND_BLOCK_COMMAND_AND_SELF_SELECTOR_FIXES("executeandblockcommandandselfselectorfixes"),

  INSTANT_EFFECTS_USE_TICKS("instanteffectsuseticks"),

  DONT_REGISTER_BROKEN_FUNCTION_COMMANDS("dontregisterbrokenfunctioncommands"),

  CLEAR_SPAWN_POINT_COMMAND("clearspawnpointcommand"),

  CLONE_AND_TELEPORT_ROTATION_FIXES("cloneandteleportrotationfixes"),

  TELEPORT_DIMENSION_FIXES("teleportdimensionfixes"),

  CLONE_UPDATE_BLOCK_AND_TIME_FIXES("cloneupdateblockandtimefixes"),

  CLONE_INTERSECT_FIX("cloneintersectfix"),

  FUNCTION_EXECUTE_ORDER_AND_CHEST_SLOT_FIX("functionexecuteorderandchestslotfix"),

  NON_TICKING_AREAS_NO_LONGER_CONSIDERED_LOADED("nontickingareasnolongerconsideredloaded"),

  SPREADPLAYERS_HAZARD_AND_RESOLVE_PLAYER_BY_NAME_FIX("spreadplayershazardandresolveplayerbynamefix"),

  NEW_EXECUTE_COMMAND_SYNTAX_EXPERIMENT_AND_CHEST_LOOT_TABLE_FIX_AND_TELEPORT_FACING_VERTICAL_UNCLAMPED_AND_LOCATE_BIOME_AND_FEATURE_MERGED("newexecutecommandsyntaxexperimentandchestloottablefixandteleportfacingverticalunclampedandlocatebiomeandfeaturemerged"),

  WATERLOGGING_ADDED_TO_STRUCTURE_COMMAND("waterloggingaddedtostructurecommand"),

  SELECTOR_DISTANCE_FILTERED_AND_RELATIVE_ROTATION_FIX("selectordistancefilteredandrelativerotationfix"),

  NEW_SUMMON_COMMAND_ADDED_ROTATION_OPTIONS_AND_BUBBLE_COLUMN_CLONE_FIX_AND_EXECUTE_IN_DIMENSION_TELEPORT_FIX_AND_NEW_EXECUTE_ROTATION_FIX("newsummoncommandaddedrotationoptionsandbubblecolumnclonefixandexecuteindimensionteleportfixandnewexecuterotationfix"),

  NEW_EXECUTE_COMMAND_RELEASE_ENCHANT_COMMAND_LEVEL_FIX_AND_HAS_ITEM_DATA_FIX_AND_COMMAND_DEFERRAL("newexecutecommandreleaseenchantcommandlevelfixandhasitemdatafixandcommanddeferral"),

  EXECUTE_IF_SCORE_FIXES("executeifscorefixes"),

  REPLACE_ITEM_AND_LOOT_REPLACE_BLOCK_COMMANDS_DO_NOT_PLACE_ITEMS_INTO_CAULDRONS_FIX("replaceitemandlootreplaceblockcommandsdonotplaceitemsintocauldronsfix"),

  CHANGES_TO_COMMAND_ORIGIN_ROTATION("changestocommandoriginrotation"),

  REMOVE_AUX_VALUE_PARAMETER_FROM_BLOCK_COMMANDS("removeauxvalueparameterfromblockcommands"),

  VOLUME_SELECTOR_FIXES("volumeselectorfixes"),

  ENABLE_SUMMON_ROTATION("enablesummonrotation"),

  SUMMON_COMMAND_DEFAULT_ROTATION("summoncommanddefaultrotation"),

  POSITIONAL_DIMENSION_FILTERING("positionaldimensionfiltering"),

  COMMAND_SELECTOR_HAS_ITEM_FILTER_NO_LONGER_CALLS_SAME_ITEM_FUNCTION("commandselectorhasitemfilternolongercallssameitemfunction"),

  AGENT_SWEEPING_BLOCK_TEST("agentsweepingblocktest"),

  BLOCK_STATE_EQUALS("blockstateequals"),

  COMMAND_POSITION_FIX("commandpositionfix"),

  COMMAND_SELECTOR_HAS_ITEM_FILTER_USES_DATA_AS_DAMAGE_FOR_SELECTING_DAMAGEABLE_ITEMS("commandselectorhasitemfilterusesdataasdamageforselectingdamageableitems"),

  EXECUTE_DETECT_CONDITION_SUBCOMMAND_NOT_ALLOW_NON_LOADED_BLOCKS("executedetectconditionsubcommandnotallownonloadedblocks"),

  REMOVE_SUICIDE_KEYWORD("removesuicidekeyword"),

  CLONE_CONTAINER_BLOCK_ENTITY_REMOVAL_FIX("clonecontainerblockentityremovalfix"),

  STOP_SOUND_MUSIC_FIX("stopsoundmusicfix"),

  SPREAD_PLAYERS_STUCK_IN_GROUND_FIX_AND_MAX_HEIGHT_PARAMETER("spreadplayersstuckingroundfixandmaxheightparameter"),

  LOCATE_STRUCTURE_OUTPUT("locatestructureoutput"),

  POST_BLOCK_FLATTENING("postblockflattening"),

  TEST_FOR_BLOCK_COMMAND_DOES_NOT_IGNORE_BLOCK_STATE("testforblockcommanddoesnotignoreblockstate"),

  COUNT("count"),

  LATEST("latest");

  private static final Map<String, CurrentCmdVersion> SERIALIZE_NAMES = new HashMap<>(values().length);

  static {
    for (CurrentCmdVersion value : values()) {
      SERIALIZE_NAMES.put(value.getSerializeName(), value);
    }
  }

  private final String serializeName;

  public static CurrentCmdVersion fromName(String serializeName) {
    return SERIALIZE_NAMES.get(serializeName);
  }
}
