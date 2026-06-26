package org.cloudburstmc.protocol.bedrock.data;

import lombok.Data;
import lombok.ToString;
import org.cloudburstmc.math.vector.Vector3i;
import org.cloudburstmc.protocol.bedrock.data.payload.editor.ServerEditorConnectionPolicy;
import org.cloudburstmc.protocol.bedrock.data.payload.experiment.Experiments;
import org.cloudburstmc.protocol.common.util.OptionalBoolean;

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
    private boolean isHardcore;
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
    private boolean educationFeaturesEnabled;
    private String educationProductID;
    private float rainLevel;
    private float lightningLevel;
    private boolean hasConfirmedPlatformLockedContent;
    private boolean multiplayerGameIntent;
    private boolean lanBroadcastIntent;
    private GamePublishSetting xboxLiveBroadcastSetting;
    private GamePublishSetting platformBroadcastSetting;
    private boolean commandsEnabled;
    private boolean texturePacksRequired;
    private final GameRulesChangedPacketData ruleData = new GameRulesChangedPacketData();
    private Experiments experiments;
    private boolean wereAnyExperimentsEverToggled;
    private boolean hasBonusChestEnabled;
    private boolean trustingPlayers;
    private boolean startWithMapEnabled;
    private PlayerPermissionLevel playerPermissions;
    private int serverChunkTickRange;
    private boolean hasLockedBehaviorPack;
    private boolean hasLockedResourcePack;
    private boolean isFromLockedTemplate;
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
    private OptionalBoolean overrideForceExperimentalGameplay;
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
    private ServerEditorConnectionPolicy serverEditorConnectionPolicy;
    /**
     * @since v1001
     */
    private boolean allowAnonymousBlockDropsInEditorWorlds;
}