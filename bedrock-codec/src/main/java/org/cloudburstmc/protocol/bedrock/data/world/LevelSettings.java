package org.cloudburstmc.protocol.bedrock.data.world;

import lombok.Data;
import org.cloudburstmc.math.vector.Vector3i;
import org.cloudburstmc.protocol.bedrock.annotation.Added;
import org.cloudburstmc.protocol.bedrock.annotation.Removed;
import org.cloudburstmc.protocol.bedrock.data.connection.GamePublishSetting;
import org.cloudburstmc.protocol.bedrock.data.editor.EditorLevelMigrationVersion;
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
    @Added("1.20.80")
    private boolean isHardcore;
    private Difficulty gameDifficulty;
    private Vector3i defaultSpawnBlockPosition;
    private boolean achievementsDisabled;
    @Added("1.19.10")
    private EditorWorldType editorWorldType;
    @Added("1.19.80")
    private boolean isCreatedInEditor;
    @Added("1.19.80")
    private boolean isExportedFromEditor;
    @Added("26.60")
    private EditorLevelMigrationVersion editorLevelMigrationVersion;
    private int dayCycleStopTime;
    private EducationEditionOffer educationEditionOffer;
    private boolean educationFeaturesEnabled;
    @Added("1.16.0")
    private String educationProductID;
    private float rainLevel;
    private float lightningLevel;
    @Added("1.9.0")
    private boolean hasConfirmedPlatformLockedContent;
    private boolean multiplayerGameIntent;
    private boolean lanBroadcastIntent;
    private GamePublishSetting xboxLiveBroadcastSetting;
    private GamePublishSetting platformBroadcastSetting;
    private boolean commandsEnabled;
    private boolean texturePacksRequired;
    private final GameRulesChangedPacketData ruleData = new GameRulesChangedPacketData();
    @Added("1.16.100")
    private Experiments experiments;
    private boolean hasBonusChestEnabled;
    private boolean startWithMapEnabled;
    private PlayerPermissionLevel playerPermissions;
    private int serverChunkTickRange;
    private boolean hasLockedBehaviorPack;
    private boolean hasLockedResourcePack;
    private boolean isFromLockedTemplate;
    private boolean useMsaGamertagsOnly;
    @Added("1.8.0")
    private boolean isFromWorldTemplate;
    @Added("1.9.0")
    private boolean isWorldTemplateOptionLocked;
    @Added("1.12.0")
    private boolean onlySpawnV1Villagers;
    @Added("1.19.20")
    private boolean personaDisabled;
    @Added("1.19.20")
    private boolean customSkinsDisabled;
    /**
     * @since v567
     */
    private boolean emoteChatMuted;
    @Added("1.13.0")
    private String baseGameVersion;
    @Added("1.16.0")
    private int limitedWorldWidth;
    @Added("1.16.0")
    private int limitedWorldDepth;
    @Added("1.16.0")
    private boolean netherType;
    @Added("1.17.30")
    private EduSharedUriResource eduSharedUriResource = EduSharedUriResource.EMPTY;
    private OptionalBoolean overrideForceExperimentalGameplay = OptionalBoolean.empty();
    @Added("1.19.20")
    private ChatRestrictionLevel chatRestrictionLevel;
    @Added("1.19.20")
    private boolean disablePlayerInteractions;
    @Added("26.30")
    private ServerEditorConnectionPolicy serverEditorConnectionPolicy;
    @Added("26.30")
    private boolean allowAnonymousBlockDropsInEditorWorlds;
    @Deprecated
    @Removed("1.9.0")
    private boolean trustingPlayers;
    @Deprecated
    @Added("1.21.0")
    @Removed("26.0")
    private String serverId;
    @Deprecated
    @Added("1.21.0")
    @Removed("26.0")
    private String worldId;
    @Deprecated
    @Added("1.21.0")
    @Removed("26.0")
    private String scenarioId;
    @Deprecated
    @Added("1.21.90")
    @Removed("26.0")
    private String ownerId;
}