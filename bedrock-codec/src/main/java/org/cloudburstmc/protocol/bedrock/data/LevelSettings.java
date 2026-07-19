package org.cloudburstmc.protocol.bedrock.data;

import java.lang.String;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.cloudburstmc.math.vector.Vector3i;
import org.cloudburstmc.protocol.common.util.OptionalBoolean;

/**
 * Auto generated from 1.26.40-beta.31 (v2168)
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LevelSettings {
  private long Seed;

  private SpawnSettings SpawnSettings;

  private String GeneratorType;

  private String GameType;

  private boolean IsHardcore;

  private String GameDifficulty;

  private Vector3i DefaultSpawnBlockPosition;

  private boolean AchievementsDisabled;

  private String EditorWorldType;

  private boolean IsCreatedInEditor;

  private boolean IsExportedFromEditor;

  private int DayCycleStopTime;

  private String EducationEditionOffer;

  private boolean EducationFeaturesEnabled;

  private String EducationProductID;

  private float RainLevel;

  private float LightningLevel;

  private boolean HasConfirmedPlatformLockedContent;

  private boolean MultiplayerGameIntent;

  private boolean LANBroadcastIntent;

  private String XboxLiveBroadcastSetting;

  private String PlatformBroadcastSetting;

  private boolean CommandsEnabled;

  private boolean TexturePacksRequired;

  private GameRulesChangedPacketData RuleData;

  private Experiments Experiments;

  private boolean HasBonusChestEnabled;

  private boolean StartWithMapEnabled;

  private String PlayerPermissions;

  private int ServerChunkTickRange;

  private boolean HasLockedBehaviorPack;

  private boolean HasLockedResourcePack;

  private boolean IsFromLockedTemplate;

  private boolean UseMsaGamertagsOnly;

  private boolean IsFromWorldTemplate;

  private boolean IsWorldTemplateOptionLocked;

  private boolean OnlySpawnV1Villagers;

  private boolean PersonaDisabled;

  private boolean CustomSkinsDisabled;

  private boolean EmoteChatMuted;

  private String BaseGameVersion;

  private int LimitedWorldWidth;

  private int LimitedWorldDepth;

  private boolean NetherType;

  private EduSharedUriResource EduSharedUriResource;

  private OptionalBoolean OverrideForceExperimentalGameplay = OptionalBoolean.empty();

  private String ChatRestrictionLevel;

  private boolean DisablePlayerInteractions;

  private String ServerEditorConnectionPolicy;

  private boolean AllowAnonymousBlockDropsInEditorWorlds;
}
