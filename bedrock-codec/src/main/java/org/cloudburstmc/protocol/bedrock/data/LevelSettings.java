package org.cloudburstmc.protocol.bedrock.data;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import lombok.Data;
import lombok.ToString;
import org.cloudburstmc.math.vector.Vector3i;
import org.cloudburstmc.protocol.common.util.OptionalBoolean;

import java.util.List;

/**
 * @author Kaooot
 */
@Data
@ToString
public class LevelSettings {

    private long seed;
    private SpawnSettings spawnSettings = new SpawnSettings();
    private GeneratorType generatorType;
    private GameType gameType;
    /**
     * @since v671
     */
    private boolean isHardcoreModeEnabled;
    private Difficulty gameDifficulty;
    private Vector3i defaultSpawnBlockPosition;
    private boolean achievementsDisabled;
    /**
     * @since v534
     */
    private EditorWorldType editorWorldType;
    /**
     * @since v582
     */
    private boolean isCreatedInEditor;
    /**
     * @since v582
     */
    private boolean isExportedFromEditor;
    private int dayCycleStopTime;
    private EducationEditionOffer educationEditionOffer;
    private boolean areEducationFeaturesEnabled;
    private String educationProductionId;
    private float rainLevel;
    private float lightningLevel;
    private boolean hasConfirmedPlatformLockedContent;
    private boolean wasMultiplayerIntendedToBeEnabled;
    private boolean wasLANBroadcastingIntendedToBeEnabled;
    private GamePublishSetting xboxLiveBroadcastSetting;
    private GamePublishSetting platformBroadcastSetting;
    private boolean commandsEnabled;
    private boolean texturePacksRequired;
    private final GameRulesChangedPacketData ruleData = new GameRulesChangedPacketData();
    private final List<Experiment> experiments = new ObjectArrayList<>();
    private boolean wereAnyExperimentsEverToggled;
    private boolean hasBonusChestEnabled;
    private boolean trustingPlayers;
    private boolean startWithMapEnabled;
    private PlayerPermissionLevel playerPermissions;
    private int serverChunkTickRange;
    private boolean hasLockedBehaviorPack;
    private boolean hasLockedResourcePack;
    private boolean isFromLockedWorldTemplate;
    private boolean useMsaGamertagsOnly;
    private boolean isFromWorldTemplate;
    private boolean isWorldTemplateOptionLocked;
    private boolean onlySpawnV1Villagers;
    /**
     * @since 1.19.20
     */
    private boolean personaDisabled;
    /**
     * @since 1.19.20
     */
    private boolean customSkinsDisabled;
    /**
     * @since v567
     */
    private boolean emoteChatMuted;
    private String baseGameVersion;
    private int limitedWorldWidth;
    private int limitedWorldDepth;
    private boolean netherType;
    /**
     * @since v465
     */
    private EduSharedUriResource eduSharedUriResource = EduSharedUriResource.EMPTY;
    private OptionalBoolean forceExperimentalGameplay;
    /**
     * @since 1.19.20
     */
    private ChatRestrictionLevel chatRestrictionLevel;
    /**
     * @since 1.19.20
     */
    private boolean disablePlayerInteractions;
    /**
     * @since v685
     * @deprecated since v924
     */
    private String serverId;
    /**
     * @since v685
     * @deprecated since v924
     */
    private String worldId;
    /**
     * @since v685
     * @deprecated since v924
     */
    private String scenarioId;
    /**
     * @since v818
     * @deprecated since v924
     */
    private String ownerId;
    /**
     * @since v1001
     */
    private int serverEditorConnectionPolicy;
    /**
     * @since v1001
     */
    private boolean allowAnonymousBlockDropsInEditorWorlds;
}