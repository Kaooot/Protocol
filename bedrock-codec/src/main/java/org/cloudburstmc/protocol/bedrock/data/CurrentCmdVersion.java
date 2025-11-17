package org.cloudburstmc.protocol.bedrock.data;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @author Kaooot
 */
@Getter
@RequiredArgsConstructor
public enum CurrentCmdVersion {

    INVALID("Invalid"),
    INITIAL("Initial"),
    TP_ROTATION_CLAMPING("TpRotationClamping"),
    NEW_BEDROCK_CMD_SYSTEM("NewBedrockCmdSystem"),
    EXECUTE_USES_VEC3("ExecuteUsesVec3"),
    CLONE_FIXES("CloneFixes"),
    UPDATE_AQUATIC("UpdateAquatic"),
    ENTITY_SELECTOR_USES_VEC3("EntitySelectorUsesVec3"),
    CONTAINERS_DONT_DROP_ITEMS_ANYMORE("ContainersDontDropItemsAnymore"),
    FILTERS_OBEY_DIMENSIONS("FiltersObeyDimensions"),
    EXECUTE_AND_BLOCK_COMMAND_AND_SELF_SELECTOR_FIXES("ExecuteAndBlockCommandAndSelfSelectorFixes"),
    INSTANT_EFFECTS_USE_TICKS("InstantEffectsUseTicks"),
    DONT_REGISTER_BROKEN_FUNCTION_COMMANDS("DontRegisterBrokenFunctionCommands"),
    CLEAR_SPAWN_POINT_COMMAND("ClearSpawnPointCommand"),
    CLONE_AND_TELEPORT_ROTATION_FIXES("CloneAndTeleportRotationFixes"),
    TELEPORT_DIMENSION_FIXES("TeleportDimensionFixes"),
    CLONE_UPDATE_BLOCK_AND_TIME_FIXES("CloneUpdateBlockAndTimeFixes"),
    CLONE_INTERSECT_FIX("CloneIntersectFix"),
    FUNCTION_EXECUTE_ORDER_AND_CHEST_SLOT_FIX("FunctionExecuteOrderAndChestSlotFix"),
    NON_TICKING_AREAS_NO_LONGER_CONSIDERED_LOADED("NonTickingAreasNoLongerConsideredLoaded"),
    SPREADPLAYERS_HAZARD_AND_RESOLVE_PLAYER_BY_NAME_FIX("SpreadplayersHazardAndResolvePlayerByNameFix"),
    NEW_EXECUTE_COMMAND_SYNTAX_EXPERIMENT_AND_CHEST_LOOT_TABLE_FIX_AND_TELEPORT_FACING_VERTICAL_UNCLAMPED_AND_LOCATE_BIOME_AND_FEATURE_MERGED("NewExecuteCommandSyntaxExperimentAndChestLootTableFixAndTeleportFacingVerticalUnclampedAndLocateBiomeAndFeatureMerged"),
    WATERLOGGING_ADDED_TO_STRUCTURE_COMMAND("WaterloggingAddedToStructureCommand"),
    SELECTOR_DISTANCE_FILTERED_AND_RELATIVE_ROTATION_FIX("SelectorDistanceFilteredAndRelativeRotationFix"),
    NEW_SUMMON_COMMAND_ADDED_ROTATION_OPTIONS_AND_BUBBLE_COLUMN_CLONE_FIX_AND_EXECUTE_IN_DIMENSION_TELEPORT_FIX_AND_NEW_EXECUTE_ROTATION_FIX("NewSummonCommandAddedRotationOptionsAndBubbleColumnCloneFixAndExecuteInDimensionTeleportFixAndNewExecuteRotationFix"),
    NEW_EXECUTE_COMMAND_RELEASE_ENCHANT_COMMAND_LEVEL_FIX_AND_HAS_ITEM_DATA_FIX_AND_COMMAND_DEFERRAL("NewExecuteCommandReleaseEnchantCommandLevelFixAndHasItemDataFixAndCommandDeferral"),
    EXECUTE_IF_SCORE_FIXES("ExecuteIfScoreFixes"),
    REPLACE_ITEM_AND_LOOT_REPLACE_BLOCK_COMMANDS_DO_NOT_PLACE_ITEMS_INTO_CAULDRONS_FIX("ReplaceItemAndLootReplaceBlockCommandsDoNotPlaceItemsIntoCauldronsFix"),
    CHANGES_TO_COMMAND_ORIGIN_ROTATION("ChangesToCommandOriginRotation"),
    REMOVE_AUX_VALUE_PARAMETER_FROM_BLOCK_COMMANDS("RemoveAuxValueParameterFromBlockCommands"),
    VOLUME_SELECTOR_FIXES("VolumeSelectorFixes"),
    ENABLE_SUMMON_ROTATION("EnableSummonRotation"),
    SUMMON_COMMAND_DEFAULT_ROTATION("SummonCommandDefaultRotation"),
    POSITIONAL_DIMENSION_FILTERING("PositionalDimensionFiltering"),
    COMMAND_SELECTOR_HAS_ITEM_FILTER_NO_LONGER_CALLS_SAME_ITEM_FUNCTION("CommandSelectorHasItemFilterNoLongerCallsSameItemFunction"),
    AGENT_SWEEPING_BLOCK_TEST("AgentSweepingBlockTest"),
    BLOCK_STATE_EQUALS("BlockStateEquals"),
    COMMAND_POSITION_FIX("CommandPositionFix"),
    COMMAND_SELECTOR_HAS_ITEM_FILTER_USES_DATA_AS_DAMAGE_FOR_SELECTING_DAMAGEABLE_ITEMS("CommandSelectorHasItemFilterUsesDataAsDamageForSelectingDamageableItems"),
    EXECUTE_DETECT_CONDITION_SUBCOMMAND_NOT_ALLOW_NON_LOADED_BLOCKS("ExecuteDetectConditionSubcommandNotAllowNonLoadedBlocks"),
    REMOVE_SUICIDE_KEYWORD("RemoveSuicideKeyword"),
    CLONE_CONTAINER_BLOCK_ENTITY_REMOVAL_FIX("CloneContainerBlockEntityRemovalFix"),
    STOP_SOUND_MUSIC_FIX("StopSoundMusicFix"),
    SPREAD_PLAYERS_STUCK_IN_GROUND_FIX_AND_MAX_HEIGHT_PARAMETER("SpreadPlayersStuckInGroundFixAndMaxHeightParameter"),
    LOCATE_STRUCTURE_OUTPUT("LocateStructureOutput"),
    POST_BLOCK_FLATTENING("PostBlockFlattening"),
    TEST_FOR_BLOCK_COMMAND_DOES_NOT_IGNORE_BLOCK_STATE("TestForBlockCommandDoesNotIgnoreBlockState"),
    COUNT("Count"),
    LATEST("Latest");

    private final String id;

    private static final CurrentCmdVersion[] VALUES = values();

    public static CurrentCmdVersion from(String id) {
        for (CurrentCmdVersion value : VALUES) {
            if (value.getId().equalsIgnoreCase(id)) {
                return value;
            }
        }
        throw new UnsupportedOperationException("Detected unknown CurrentCmdVersion ID: " + id);
    }
}