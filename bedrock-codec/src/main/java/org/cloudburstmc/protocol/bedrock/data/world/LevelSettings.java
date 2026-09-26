package org.cloudburstmc.protocol.bedrock.data.world;

import lombok.Data;
import org.cloudburstmc.math.vector.Vector3i;
import org.cloudburstmc.protocol.bedrock.data.connection.GamePublishSetting;
import org.cloudburstmc.protocol.bedrock.data.editor.EditorWorldType;
import org.cloudburstmc.protocol.bedrock.data.editor.ServerEditorConnectionPolicy;
import org.cloudburstmc.protocol.bedrock.data.education.EduSharedUriResource;
import org.cloudburstmc.protocol.bedrock.data.education.EducationEditionOffer;
import org.cloudburstmc.protocol.bedrock.data.player.PlayerPermissionLevel;
import org.cloudburstmc.protocol.bedrock.data.text.ChatRestrictionLevel;
import org.cloudburstmc.protocol.common.util.OptionalBoolean;

@Data
public class LevelSettings {

    private long seed;
    private final SpawnSettings spawnSettings = new SpawnSettings();
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
    /**
     * @since v407
     */
    private String educationProductID;
    private float rainLevel;
    private float lightningLevel;
    /**
     * @since v332
     */
    private boolean hasConfirmedPlatformLockedContent;
    private boolean multiplayerGameIntent;
    private boolean lanBroadcastIntent;
    private GamePublishSetting xboxLiveBroadcastSetting;
    private GamePublishSetting platformBroadcastSetting;
    private boolean commandsEnabled;
    private boolean texturePacksRequired;
    private final GameRulesChangedPacketData ruleData = new GameRulesChangedPacketData();
    /**
     * @since v419
     */
    private Experiments experiments;
    private boolean hasBonusChestEnabled;
    private boolean startWithMapEnabled;
    private PlayerPermissionLevel playerPermissions;
    private int serverChunkTickRange;
    private boolean hasLockedBehaviorPack;
    private boolean hasLockedResourcePack;
    private boolean isFromLockedTemplate;
    private boolean useMsaGamertagsOnly;
    /**
     * @since v313
     */
    private boolean isFromWorldTemplate;
    /**
     * @since v332
     */
    private boolean isWorldTemplateOptionLocked;
    /**
     * @since v361
     */
    private boolean onlySpawnV1Villagers;
    /**
     * @since v544
     */
    private boolean personaDisabled;
    /**
     * @since v544
     */
    private boolean customSkinsDisabled;
    /**
     * @since v567
     */
    private boolean emoteChatMuted;
    /**
     * @since v388
     */
    private String baseGameVersion;
    /**
     * @since v407
     */
    private int limitedWorldWidth;
    /**
     * @since v407
     */
    private int limitedWorldDepth;
    /**
     * @since v407
     */
    private boolean netherType;
    /**
     * @since v465
     */
    private EduSharedUriResource eduSharedUriResource = EduSharedUriResource.EMPTY;
    private OptionalBoolean overrideForceExperimentalGameplay = OptionalBoolean.empty();
    /**
     * @since v544
     */
    private ChatRestrictionLevel chatRestrictionLevel;
    /**
     * @since v544
     */
    private boolean disablePlayerInteractions;
    /**
     * @since v1001
     */
    private ServerEditorConnectionPolicy serverEditorConnectionPolicy;
    /**
     * @since v1001
     */
    private boolean allowAnonymousBlockDropsInEditorWorlds;
    /**
     * @deprecated since v332
     */
    private boolean trustingPlayers;
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
}