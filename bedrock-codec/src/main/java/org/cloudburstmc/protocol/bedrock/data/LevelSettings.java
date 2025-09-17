package org.cloudburstmc.protocol.bedrock.data;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import lombok.Data;
import lombok.ToString;
import org.cloudburstmc.math.vector.Vector3i;
import org.cloudburstmc.protocol.bedrock.docs.DataType;
import org.cloudburstmc.protocol.bedrock.docs.Docs;
import org.cloudburstmc.protocol.bedrock.docs.Ignore;
import org.cloudburstmc.protocol.common.util.OptionalBoolean;

import java.util.List;

/**
 * @author Kaooot
 */
@Docs
@Data
@ToString
public class LevelSettings {

    @Docs(type = DataType.UNSIGNED_INT64)
    private long seed;
    private SpawnSettings spawnSettings = new SpawnSettings();
    @Docs(type = DataType.VARINT)
    private GeneratorType generatorType;
    @Docs(type = DataType.VARINT)
    private GameType gameType;
    /**
     * @since v671
     */
    private boolean isHardcoreModeEnabled;
    @Docs(type = DataType.VARINT)
    private Difficulty gameDifficulty;
    private Vector3i defaultSpawnBlockPosition;
    private boolean achievementsDisabled;
    /**
     * @since v534
     */
    @Docs(type = DataType.VARINT)
    private EditorWorldType editorWorldType;
    /**
     * @since v582
     */
    private boolean isCreatedInEditor;
    /**
     * @since v582
     */
    private boolean isExportedFromEditor;
    @Docs(type = DataType.VARINT)
    private int dayCycleStopTime;
    @Docs(type = DataType.VARINT)
    private EducationEditionOffer educationEditionOffer;
    private boolean areEducationFeaturesEnabled;
    private String educationProductionId;
    private float rainLevel;
    private float lightningLevel;
    private boolean hasConfirmedPlatformLockedContent;
    private boolean wasMultiplayerIntendedToBeEnabled;
    @Docs(name = "Was LAN Broadcasting Intended To Be Enabled")
    private boolean wasLANBroadcastingIntendedToBeEnabled;
    @Docs(type = DataType.VARINT)
    private GamePublishSetting xboxLiveBroadcastSetting;
    @Docs(type = DataType.VARINT)
    private GamePublishSetting platformBroadcastSetting;
    private boolean commandsEnabled;
    private boolean texturePacksRequired;
    private final GameRulesChangedPacketData ruleData = new GameRulesChangedPacketData();
    @Docs(type = DataType.ARRAY, linkType = Docs.LinkType.MEMBERS)
    private final List<Experiment> experiments = new ObjectArrayList<>();
    private boolean wereAnyExperimentsEverToggled;
    private boolean hasBonusChestEnabled;
    @Ignore
    private boolean trustingPlayers;
    private boolean startWithMapEnabled;
    @Docs(type = DataType.VARINT)
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
    @Docs(isOptional = true)
    private OptionalBoolean forceExperimentalGameplay;
    /**
     * @since 1.19.20
     */
    @Docs(type = DataType.BYTE)
    private ChatRestrictionLevel chatRestrictionLevel;
    /**
     * @since 1.19.20
     */
    private boolean disablePlayerInteractions;
    /**
     * @since v685
     */
    private String serverId;
    /**
     * @since v685
     */
    private String worldId;
    /**
     * @since v685
     */
    private String scenarioId;
    /**
     * @since v818
     */
    private String ownerId;
}