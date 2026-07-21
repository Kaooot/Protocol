package org.cloudburstmc.protocol.bedrock.data;

import java.lang.String;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.cloudburstmc.math.vector.Vector3i;
import org.cloudburstmc.protocol.common.util.OptionalBoolean;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LevelSettings {
  private long seed;

  private SpawnSettings spawnSettings;

  private GeneratorType generatorType;

  private GameType gameType;

  private boolean isHardcore;

  private Difficulty gameDifficulty;

  private Vector3i defaultSpawnBlockPosition;

  private boolean achievementsDisabled;

  private EditorWorldType editorWorldType;

  private boolean isCreatedInEditor;

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

  private GameRulesChangedPacketData ruleData;

  private Experiments experiments;

  private boolean hasBonusChestEnabled;

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

  private boolean personaDisabled;

  private boolean customSkinsDisabled;

  private boolean emoteChatMuted;

  private String baseGameVersion;

  private int limitedWorldWidth;

  private int limitedWorldDepth;

  private boolean netherType;

  private EduSharedUriResource eduSharedUriResource;

  private OptionalBoolean overrideForceExperimentalGameplay = OptionalBoolean.empty();

  private ChatRestrictionLevel chatRestrictionLevel;

  private boolean disablePlayerInteractions;

  private ServerEditorConnectionPolicy serverEditorConnectionPolicy;

  private boolean allowAnonymousBlockDropsInEditorWorlds;
}
