# Minecraft Bedrock Protocol – CloudburstMC to Mojang Naming Migration

Changes to `packet`, `data` (types) and enum classes on branch `feat/protocol-alignment` (base: protocol 3.0 → target: Minecraft 1.26.50 / protocol 2192).

> Intended as a source for OpenRewrite recipes: **Renamed** sections → `org.openrewrite.java.ChangeType`; **Renamed** field bullets → `org.openrewrite.java.RenameField`.
>
> Auto-generated from the git diff. **Verify before use:**
> - Class renames are matched by content similarity (shown as `%`); anything below ~60% may be a coincidental pairing of an unrelated removed+added class, not a true rename.
> - Field/constant *renames* are only inferred when a class has exactly one removed and one added member of the same type (high confidence); everything else is reported factually as Added/Removed, so some real renames appear as an Added + a Removed pair.

## New Packets

* ActorEventPacket (`org.cloudburstmc.protocol.bedrock.packet.ActorEventPacket`)
* AddActorPacket (`org.cloudburstmc.protocol.bedrock.packet.AddActorPacket`)
* AvailableActorIdentifiersPacket (`org.cloudburstmc.protocol.bedrock.packet.AvailableActorIdentifiersPacket`)
* CameraAimAssistActorPriorityPacket (`org.cloudburstmc.protocol.bedrock.packet.CameraAimAssistActorPriorityPacket`)
* CameraSplinePacket (`org.cloudburstmc.protocol.bedrock.packet.CameraSplinePacket`)
* ClientCameraAimAssistPacket (`org.cloudburstmc.protocol.bedrock.packet.ClientCameraAimAssistPacket`)
* ClientMovementPredictionSyncPacket (`org.cloudburstmc.protocol.bedrock.packet.ClientMovementPredictionSyncPacket`)
* ClientboundDataDrivenUICloseScreenPacket (`org.cloudburstmc.protocol.bedrock.packet.ClientboundDataDrivenUICloseScreenPacket`)
* ClientboundDataDrivenUIReloadPacket (`org.cloudburstmc.protocol.bedrock.packet.ClientboundDataDrivenUIReloadPacket`)
* ClientboundDataDrivenUIShowScreenPacket (`org.cloudburstmc.protocol.bedrock.packet.ClientboundDataDrivenUIShowScreenPacket`)
* ClientboundDataStorePacket (`org.cloudburstmc.protocol.bedrock.packet.ClientboundDataStorePacket`)
* ClientboundMatchmakingStatePacket (`org.cloudburstmc.protocol.bedrock.packet.ClientboundMatchmakingStatePacket`)
* ClientboundStonecutterSetRecipePacket (`org.cloudburstmc.protocol.bedrock.packet.ClientboundStonecutterSetRecipePacket`)
* ClientboundTextureShiftPacket (`org.cloudburstmc.protocol.bedrock.packet.ClientboundTextureShiftPacket`)
* ClientboundUpdateSoundDataPacket (`org.cloudburstmc.protocol.bedrock.packet.ClientboundUpdateSoundDataPacket`)
* GraphicsOverrideParameterPacket (`org.cloudburstmc.protocol.bedrock.packet.GraphicsOverrideParameterPacket`)
* ItemRegistryPacket (`org.cloudburstmc.protocol.bedrock.packet.ItemRegistryPacket`)
* LegacyTelemetryEventPacket (`org.cloudburstmc.protocol.bedrock.packet.LegacyTelemetryEventPacket`)
* LocatorBarPacket (`org.cloudburstmc.protocol.bedrock.packet.LocatorBarPacket`)
* MoveActorAbsolutePacket (`org.cloudburstmc.protocol.bedrock.packet.MoveActorAbsolutePacket`)
* PartyChangedPacket (`org.cloudburstmc.protocol.bedrock.packet.PartyChangedPacket`)
* PartyDestinationCookieResponsePacket (`org.cloudburstmc.protocol.bedrock.packet.PartyDestinationCookieResponsePacket`)
* PlayerToggleCrafterSlotRequestPacket (`org.cloudburstmc.protocol.bedrock.packet.PlayerToggleCrafterSlotRequestPacket`)
* ResourcePacksReadyForValidationPacket (`org.cloudburstmc.protocol.bedrock.packet.ResourcePacksReadyForValidationPacket`)
* SendPartyDestinationCookiePacket (`org.cloudburstmc.protocol.bedrock.packet.SendPartyDestinationCookiePacket`)
* ServerStoreInfoPacket (`org.cloudburstmc.protocol.bedrock.packet.ServerStoreInfoPacket`)
* ServerboundDataDrivenScreenClosedPacket (`org.cloudburstmc.protocol.bedrock.packet.ServerboundDataDrivenScreenClosedPacket`)
* ServerboundDataStorePacket (`org.cloudburstmc.protocol.bedrock.packet.ServerboundDataStorePacket`)
* ServerboundPackSettingChangePacket (`org.cloudburstmc.protocol.bedrock.packet.ServerboundPackSettingChangePacket`)
* ServerboundStonecutterSetRecipePacket (`org.cloudburstmc.protocol.bedrock.packet.ServerboundStonecutterSetRecipePacket`)
* SetActorDataPacket (`org.cloudburstmc.protocol.bedrock.packet.SetActorDataPacket`)
* SetActorLinkPacket (`org.cloudburstmc.protocol.bedrock.packet.SetActorLinkPacket`)
* SetPlayerFurnaceOptionsPacket (`org.cloudburstmc.protocol.bedrock.packet.SetPlayerFurnaceOptionsPacket`)
* SyncActorPropertyPacket (`org.cloudburstmc.protocol.bedrock.packet.SyncActorPropertyPacket`)
* SyncWorldClocksPacket (`org.cloudburstmc.protocol.bedrock.packet.SyncWorldClocksPacket`)
* TakeItemActorPacket (`org.cloudburstmc.protocol.bedrock.packet.TakeItemActorPacket`)
* VoxelShapesPacket (`org.cloudburstmc.protocol.bedrock.packet.VoxelShapesPacket`)

## Removed Packets

* AddEntityPacket (`org.cloudburstmc.protocol.bedrock.packet.AddEntityPacket`)
* AvailableEntityIdentifiersPacket (`org.cloudburstmc.protocol.bedrock.packet.AvailableEntityIdentifiersPacket`)
* CameraAimAssistInstructionPacket (`org.cloudburstmc.protocol.bedrock.packet.CameraAimAssistInstructionPacket`)
* EventPacket (`org.cloudburstmc.protocol.bedrock.packet.EventPacket`)
* MovementPredictionSyncPacket (`org.cloudburstmc.protocol.bedrock.packet.MovementPredictionSyncPacket`)
* ServerScriptDebugDrawerPacket (`org.cloudburstmc.protocol.bedrock.packet.ServerScriptDebugDrawerPacket`)
* SetEntityDataPacket (`org.cloudburstmc.protocol.bedrock.packet.SetEntityDataPacket`)
* SyncEntityPropertyPacket (`org.cloudburstmc.protocol.bedrock.packet.SyncEntityPropertyPacket`)

## Renamed Packets

* `org.cloudburstmc.protocol.bedrock.packet.BlockEntityDataPacket` → `org.cloudburstmc.protocol.bedrock.packet.BlockActorDataPacket` _(72% similar)_
* `org.cloudburstmc.protocol.bedrock.packet.RemoveEntityPacket` → `org.cloudburstmc.protocol.bedrock.packet.RemoveActorPacket` _(70% similar)_
* `org.cloudburstmc.protocol.bedrock.packet.EntityPickRequestPacket` → `org.cloudburstmc.protocol.bedrock.packet.ActorPickRequestPacket` _(69% similar)_
* `org.cloudburstmc.protocol.bedrock.packet.TakeItemEntityPacket` → `org.cloudburstmc.protocol.bedrock.packet.ServerboundMatchmakingCancelPacket` _(67% similar)_
* `org.cloudburstmc.protocol.bedrock.packet.SetEntityMotionPacket` → `org.cloudburstmc.protocol.bedrock.packet.ServerPlayerPostMovePositionPacket` _(65% similar)_
* `org.cloudburstmc.protocol.bedrock.packet.SetEntityLinkPacket` → `org.cloudburstmc.protocol.bedrock.packet.ClientboundAttributeLayerSyncPacket` _(64% similar)_
* `org.cloudburstmc.protocol.bedrock.packet.EntityEventPacket` → `org.cloudburstmc.protocol.bedrock.packet.ServerPresenceInfoPacket` _(61% similar)_
* `org.cloudburstmc.protocol.bedrock.packet.ToggleCrafterSlotRequestPacket` → `org.cloudburstmc.protocol.bedrock.packet.RecordStartedPacket` _(60% similar)_
* `org.cloudburstmc.protocol.bedrock.packet.MoveEntityAbsolutePacket` → `org.cloudburstmc.protocol.bedrock.packet.SetActorMotionPacket` _(60% similar)_
* `org.cloudburstmc.protocol.bedrock.packet.AddItemEntityPacket` → `org.cloudburstmc.protocol.bedrock.packet.AddItemActorPacket` _(59% similar)_
* `org.cloudburstmc.protocol.bedrock.packet.MoveEntityDeltaPacket` → `org.cloudburstmc.protocol.bedrock.packet.MoveActorDeltaPacket` _(55% similar)_
* `org.cloudburstmc.protocol.bedrock.packet.ItemComponentPacket` → `org.cloudburstmc.protocol.bedrock.packet.PrimitiveShapesPacket` _(55% similar)_

## Packet Changes

ActorEventPacket:
* Added `eventID` (ActorEvent)
* Added `fireAtPosition` (Vector3f)
* Added `data` (int)
* Added `targetRuntimeID` (long)

ActorPickRequestPacket:
* Renamed `hotbarSlot` to `maxSlots` (int)
* Renamed `runtimeEntityId` to `actorID` (long)

AddActorPacket:
* Added `actorData` (ActorDataMap)
* Added `actorLinks` (List<ActorLink>)
* Added `attributesList` (List<SyncedAttribute>)
* Added `synchedProperties` (PropertySyncData)
* Added `actorType` (String)
* Added `rotation` (Vector2f)
* Added `position` (Vector3f)
* Added `velocity` (Vector3f)
* Added `yHeadRotation` (float)
* Added `yBodyRotation` (float)
* Added `actorTypeDeprecated` (int)
* Added `targetActorID` (long)
* Added `targetRuntimeID` (long)

AddBehaviorTreePacket:
* Renamed `behaviorTreeJson` to `behaviorTreeStructureJson` (String)

AddEntityPacket:
* Removed `metadata` (EntityDataMap)
* Removed `properties` (EntityProperties)
* Removed `attributes` (List<AttributeData>)
* Removed `entityLinks` (List<EntityLinkData>)
* Removed `identifier` (String)
* Removed `rotation` (Vector2f)
* Removed `position` (Vector3f)
* Removed `motion` (Vector3f)
* Removed `headRotation` (float)
* Removed `bodyRotation` (float)
* Removed `entityType` (int)
* Removed `uniqueEntityId` (long)
* Removed `runtimeEntityId` (long)

AddHangingEntityPacket:
* Added `targetActorID` (long)
* Added `targetRuntimeID` (long)
* Removed `uniqueEntityId` (long)
* Removed `runtimeEntityId` (long)

AddItemActorPacket:
* Renamed `itemInHand` to `item` (ItemData)
* Renamed `motion` to `velocity` (Vector3f)
* Renamed `fromFishing` to `isFromFishing` (boolean)
* Added `entityData` (ActorDataMap)
* Added `targetActorID` (long)
* Added `targetRuntimeID` (long)
* Removed `metadata` (EntityDataMap)
* Removed `uniqueEntityId` (long)
* Removed `runtimeEntityId` (long)

AddPaintingPacket:
* Renamed `motive` to `motif` (String)
* Added `position` (Vector3f)
* Added `direction` (int)
* Added `targetActorID` (long)
* Added `targetRuntimeID` (long)

AddPlayerPacket:
* Renamed `gameType` to `playerGameType` (GameType)
* Renamed `hand` to `carriedItem` (ItemData)
* Added `entityData` (ActorDataMap)
* Added `adventureSettings` (AdventureSettingsPacket)
* Added `buildPlatform` (BuildPlatform)
* Added `actorLinks` (List<ActorLink>)
* Added `synchedProperties` (PropertySyncData)
* Added `abilitiesData` (SerializedAbilitiesData)
* Added `playerName` (String)
* Added `deviceId` (String)
* Added `rotation` (Vector2f)
* Added `velocity` (Vector3f)
* Added `yHeadRotation` (float)
* Added `targetActorID` (long)
* Added `targetRuntimeID` (long)
* Removed `adventureSettings` (AdventureSettingsPacket)
* Removed `metadata` (EntityDataMap)
* Removed `properties` (EntityProperties)
* Removed `abilityLayers` (List<AbilityLayer>)
* Removed `entityLinks` (List<EntityLinkData>)
* Removed `username` (String)
* Removed `deviceId` (String)
* Removed `motion` (Vector3f)
* Removed `rotation` (Vector3f)
* Removed `buildPlatform` (int)
* Removed `uniqueEntityId` (long)
* Removed `runtimeEntityId` (long)

AddVolumeEntityPacket:
* Renamed `data` to `components` (NbtMap)
* Added `dimensionType` (DimensionType)
* Added `entityNetworkId` (EntityNetId)
* Added `jsonIdentifier` (String)
* Added `instanceName` (String)
* Added `engineVersion` (String)
* Removed `engineVersion` (String)
* Removed `identifier` (String)
* Removed `instanceName` (String)
* Removed `id` (int)
* Removed `dimension` (int)

AdventureSettingsPacket:
* Added `commandPermission` (CommandPermissionLevel)
* Added `playerPermission` (PlayerPermissionLevel)
* Removed `commandPermission` (CommandPermission)
* Removed `playerPermission` (PlayerPermission)

AgentActionEventPacket:
* Renamed `actionType` to `action` (AgentActionType)
* Renamed `responseJson` to `response` (String)

AgentAnimationPacket:
* Renamed `runtimeEntityId` to `runtimeId` (long)
* Added `agentAnimation` (AgentAnimation)
* Removed `animation` (byte)

AnimateEntityPacket:
* Renamed `runtimeEntityIds` to `runtimeIds` (LongList)

AnimatePacket:
* Renamed `runtimeEntityId` to `targetActorRuntimeID` (long)
* Added `VALUES` (Action[])
* Added `swingSource` (ActorSwingSource)
* Added `hand` (HandSlot)
* Added `data` (float)
* Added `rowingTime` (float)
* Removed `rowingTime` (float)

AnvilDamagePacket:
* Renamed `position` to `blockPosition` (Vector3i)

AutomationClientConnectPacket:
* Added `webSocketData` (WebSocketPacketData)
* Removed `address` (String)

AvailableActorIdentifiersPacket:
* Added `identifierList` (NbtMap)

AvailableEntityIdentifiersPacket:
* Removed `identifiers` (NbtMap)

AwardAchievementPacket:
* Renamed `achievementId` to `achievementID` (int)

BedrockPacketType:
* Added `UNKNOWN` (BedrockPacketType)
* Added `ACTOR_EVENT` (BedrockPacketType)
* Added `ACTOR_PICK_REQUEST` (BedrockPacketType)
* Added `ADD_ACTOR` (BedrockPacketType)
* Added `ADD_BEHAVIOR_TREE` (BedrockPacketType)
* Added `ADD_HANGING_ENTITY` (BedrockPacketType)
* Added `ADD_ITEM_ACTOR` (BedrockPacketType)
* Added `ADD_PAINTING` (BedrockPacketType)
* Added `ADD_PLAYER` (BedrockPacketType)
* Added `ADD_VOLUME_ENTITY` (BedrockPacketType)
* Added `ADVENTURE_SETTINGS` (BedrockPacketType)
* Added `AGENT_ACTION_EVENT` (BedrockPacketType)
* Added `AGENT_ANIMATION` (BedrockPacketType)
* Added `ANIMATE_ENTITY` (BedrockPacketType)
* Added `ANIMATE` (BedrockPacketType)
* Added `ANVIL_DAMAGE` (BedrockPacketType)
* Added `AUTOMATION_CLIENT_CONNECT` (BedrockPacketType)
* Added `AVAILABLE_ACTOR_IDENTIFIERS` (BedrockPacketType)
* Added `AVAILABLE_COMMANDS` (BedrockPacketType)
* Added `AWARD_ACHIEVEMENT` (BedrockPacketType)
* Added `BIOME_DEFINITION_LIST` (BedrockPacketType)
* Added `BLOCK_ACTOR_DATA` (BedrockPacketType)
* Added `BLOCK_EVENT` (BedrockPacketType)
* Added `BLOCK_PICK_REQUEST` (BedrockPacketType)
* Added `BOOK_EDIT` (BedrockPacketType)
* Added `BOSS_EVENT` (BedrockPacketType)
* Added `CAMERA_AIM_ASSIST_ACTOR_PRIORITY` (BedrockPacketType)
* Added `CAMERA_AIM_ASSIST` (BedrockPacketType)
* Added `CAMERA_AIM_ASSIST_PRESETS` (BedrockPacketType)
* Added `CAMERA_INSTRUCTION` (BedrockPacketType)
* Added `CAMERA` (BedrockPacketType)
* Added `CAMERA_PRESETS` (BedrockPacketType)
* Added `CAMERA_SHAKE` (BedrockPacketType)
* Added `CAMERA_SPLINE` (BedrockPacketType)
* Added `CHANGE_DIMENSION` (BedrockPacketType)
* Added `CHANGE_MOB_PROPERTY` (BedrockPacketType)
* Added `CHUNK_RADIUS_UPDATED` (BedrockPacketType)
* Added `CLIENTBOUND_ATTRIBUTE_LAYER_SYNC` (BedrockPacketType)
* Added `CLIENTBOUND_CLOSE_FORM` (BedrockPacketType)
* Added `CLIENTBOUND_CONTROL_SCHEME_SET` (BedrockPacketType)
* Added `CLIENTBOUND_DATA_DRIVEN_U_I_CLOSE_SCREEN` (BedrockPacketType)
* Added `CLIENTBOUND_DATA_DRIVEN_U_I_RELOAD` (BedrockPacketType)
* Added `CLIENTBOUND_DATA_DRIVEN_U_I_SHOW_SCREEN` (BedrockPacketType)
* Added `CLIENTBOUND_DATA_STORE` (BedrockPacketType)
* Added `CLIENTBOUND_DEBUG_RENDERER` (BedrockPacketType)
* Added `CLIENTBOUND_MAP_ITEM_DATA` (BedrockPacketType)
* Added `CLIENTBOUND_MATCHMAKING_STATE` (BedrockPacketType)
* Added `CLIENTBOUND_STONECUTTER_SET_RECIPE` (BedrockPacketType)
* Added `CLIENTBOUND_TEXTURE_SHIFT` (BedrockPacketType)
* Added `CLIENTBOUND_UPDATE_SOUND_DATA` (BedrockPacketType)
* Added `CLIENT_CACHE_BLOB_STATUS` (BedrockPacketType)
* Added `CLIENT_CACHE_MISS_RESPONSE` (BedrockPacketType)
* Added `CLIENT_CACHE_STATUS` (BedrockPacketType)
* Added `CLIENT_CAMERA_AIM_ASSIST` (BedrockPacketType)
* Added `CLIENT_MOVEMENT_PREDICTION_SYNC` (BedrockPacketType)
* Added `CLIENT_TO_SERVER_HANDSHAKE` (BedrockPacketType)
* Added `CODE_BUILDER` (BedrockPacketType)
* Added `CODE_BUILDER_SOURCE` (BedrockPacketType)
* Added `COMMAND_BLOCK_UPDATE` (BedrockPacketType)
* Added `COMMAND_OUTPUT` (BedrockPacketType)
* Added `COMMAND_REQUEST` (BedrockPacketType)
* Added `COMPLETED_USING_ITEM` (BedrockPacketType)
* Added `COMPRESSED_BIOME_DEFINITION_LIST` (BedrockPacketType)
* Added `CONTAINER_CLOSE` (BedrockPacketType)
* Added `CONTAINER_OPEN` (BedrockPacketType)
* Added `CONTAINER_REGISTRY_CLEANUP` (BedrockPacketType)
* Added `CONTAINER_SET_DATA` (BedrockPacketType)
* Added `CORRECT_PLAYER_MOVE_PREDICTION` (BedrockPacketType)
* Added `CRAFTING_DATA` (BedrockPacketType)
* Added `CREATE_PHOTO` (BedrockPacketType)
* Added `CREATIVE_CONTENT` (BedrockPacketType)
* Added `CURRENT_STRUCTURE_FEATURE` (BedrockPacketType)
* Added `DEATH_INFO` (BedrockPacketType)
* Added `DEBUG_INFO` (BedrockPacketType)
* Added `DIMENSION_DATA` (BedrockPacketType)
* Added `DISCONNECT` (BedrockPacketType)
* Added `EDITOR_NETWORK` (BedrockPacketType)
* Added `EDUCATION_SETTINGS` (BedrockPacketType)
* Added `EDU_URI_RESOURCE` (BedrockPacketType)
* Added `EMOTE_LIST` (BedrockPacketType)
* Added `EMOTE` (BedrockPacketType)
* Added `FEATURE_REGISTRY` (BedrockPacketType)
* Added `FILTER_TEXT` (BedrockPacketType)
* Added `GAME_RULES_CHANGED` (BedrockPacketType)
* Added `GAME_TEST_REQUEST` (BedrockPacketType)
* Added `GAME_TEST_RESULTS` (BedrockPacketType)
* Added `GRAPHICS_OVERRIDE_PARAMETER` (BedrockPacketType)
* Added `GUI_DATA_PICK_ITEM` (BedrockPacketType)
* Added `HURT_ARMOR` (BedrockPacketType)
* Added `INTERACT` (BedrockPacketType)
* Added `INVENTORY_CONTENT` (BedrockPacketType)
* Added `INVENTORY_SLOT` (BedrockPacketType)
* Added `INVENTORY_TRANSACTION` (BedrockPacketType)
* Added `ITEM_REGISTRY` (BedrockPacketType)
* Added `ITEM_STACK_REQUEST` (BedrockPacketType)
* Added `ITEM_STACK_RESPONSE` (BedrockPacketType)
* Added `JIGSAW_STRUCTURE_DATA` (BedrockPacketType)
* Added `LAB_TABLE` (BedrockPacketType)
* Added `LECTERN_UPDATE` (BedrockPacketType)
* Added `LEGACY_TELEMETRY_EVENT` (BedrockPacketType)
* Added `LESSON_PROGRESS` (BedrockPacketType)
* Added `LEVEL_CHUNK` (BedrockPacketType)
* Added `LEVEL_EVENT_GENERIC` (BedrockPacketType)
* Added `LEVEL_EVENT` (BedrockPacketType)
* Added `LEVEL_SOUND_EVENT` (BedrockPacketType)
* Added `LOCATOR_BAR` (BedrockPacketType)
* Added `LOGIN` (BedrockPacketType)
* Added `MAP_CREATE_LOCKED_COPY` (BedrockPacketType)
* Added `MAP_INFO_REQUEST` (BedrockPacketType)
* Added `MOB_ARMOR_EQUIPMENT` (BedrockPacketType)
* Added `MOB_EFFECT` (BedrockPacketType)
* Added `MOB_EQUIPMENT` (BedrockPacketType)
* Added `MODAL_FORM_REQUEST` (BedrockPacketType)
* Added `MODAL_FORM_RESPONSE` (BedrockPacketType)
* Added `MOTION_PREDICTION_HINTS` (BedrockPacketType)
* Added `MOVE_ACTOR_ABSOLUTE` (BedrockPacketType)
* Added `MOVE_ACTOR_DELTA` (BedrockPacketType)
* Added `MOVEMENT_EFFECT` (BedrockPacketType)
* Added `MOVE_PLAYER` (BedrockPacketType)
* Added `MULTIPLAYER_SETTINGS` (BedrockPacketType)
* Added `NETWORK_CHUNK_PUBLISHER_UPDATE` (BedrockPacketType)
* Added `NETWORK_SETTINGS` (BedrockPacketType)
* Added `NETWORK_STACK_LATENCY` (BedrockPacketType)
* Added `NPC_DIALOGUE` (BedrockPacketType)
* Added `NPC_REQUEST` (BedrockPacketType)
* Added `ON_SCREEN_TEXTURE_ANIMATION` (BedrockPacketType)
* Added `OPEN_SIGN` (BedrockPacketType)
* Added `VIOLATION_WARNING` (BedrockPacketType)
* Added `PARTY_CHANGED` (BedrockPacketType)
* Added `PARTY_DESTINATION_COOKIE_RESPONSE` (BedrockPacketType)
* Added `PHOTO_TRANSFER` (BedrockPacketType)
* Added `PLAYER_ACTION` (BedrockPacketType)
* Added `PLAYER_ARMOR_DAMAGE` (BedrockPacketType)
* Added `PLAYER_AUTH_INPUT` (BedrockPacketType)
* Added `PLAYER_ENCHANT_OPTIONS` (BedrockPacketType)
* Added `PLAYER_FOG` (BedrockPacketType)
* Added `PLAYER_HOTBAR` (BedrockPacketType)
* Added `PLAYER_LIST` (BedrockPacketType)
* Added `PLAYER_LOCATION` (BedrockPacketType)
* Added `PLAYER_SKIN` (BedrockPacketType)
* Added `PLAYER_START_ITEM_COOLDOWN` (BedrockPacketType)
* Added `PLAYER_TOGGLE_CRAFTER_SLOT_REQUEST` (BedrockPacketType)
* Added `PLAYER_UPDATE_ENTITY_OVERRIDES` (BedrockPacketType)
* Added `PLAYER_VIDEO_CAPTURE` (BedrockPacketType)
* Added `PLAY_SOUND` (BedrockPacketType)
* Added `PLAY_STATUS` (BedrockPacketType)
* Added `POSITION_TRACKING_D_B_CLIENT_REQUEST` (BedrockPacketType)
* Added `POSITION_TRACKING_D_B_SERVER_BROADCAST` (BedrockPacketType)
* Added `PRIMITIVE_SHAPES` (BedrockPacketType)
* Added `PURCHASE_RECEIPT` (BedrockPacketType)
* Added `RECORD_STARTED` (BedrockPacketType)
* Added `REFRESH_ENTITLEMENTS` (BedrockPacketType)
* Added `REMOVE_ACTOR` (BedrockPacketType)
* Added `REMOVE_OBJECTIVE` (BedrockPacketType)
* Added `REMOVE_VOLUME_ENTITY` (BedrockPacketType)
* Added `REQUEST_ABILITY` (BedrockPacketType)
* Added `REQUEST_CHUNK_RADIUS` (BedrockPacketType)
* Added `REQUEST_NETWORK_SETTINGS` (BedrockPacketType)
* Added `REQUEST_PERMISSIONS` (BedrockPacketType)
* Added `RESOURCE_PACK_CHUNK_DATA` (BedrockPacketType)
* Added `RESOURCE_PACK_CHUNK_REQUEST` (BedrockPacketType)
* Added `RESOURCE_PACK_CLIENT_RESPONSE` (BedrockPacketType)
* Added `RESOURCE_PACK_DATA_INFO` (BedrockPacketType)
* Added `RESOURCE_PACKS_INFO` (BedrockPacketType)
* Added `RESOURCE_PACKS_READY_FOR_VALIDATION` (BedrockPacketType)
* Added `RESOURCE_PACK_STACK` (BedrockPacketType)
* Added `RESPAWN` (BedrockPacketType)
* Added `SCRIPT_MESSAGE` (BedrockPacketType)
* Added `SEND_PARTY_DESTINATION_COOKIE` (BedrockPacketType)
* Added `SERVERBOUND_DATA_DRIVEN_SCREEN_CLOSED` (BedrockPacketType)
* Added `SERVERBOUND_DATA_STORE` (BedrockPacketType)
* Added `SERVERBOUND_DIAGNOSTICS` (BedrockPacketType)
* Added `SERVERBOUND_LOADING_SCREEN` (BedrockPacketType)
* Added `SERVERBOUND_MATCHMAKING_CANCEL` (BedrockPacketType)
* Added `SERVERBOUND_PACK_SETTING_CHANGE` (BedrockPacketType)
* Added `SERVERBOUND_STONECUTTER_SET_RECIPE` (BedrockPacketType)
* Added `SERVER_PLAYER_POST_MOVE_POSITION` (BedrockPacketType)
* Added `SERVER_PRESENCE_INFO` (BedrockPacketType)
* Added `SERVER_SETTINGS_REQUEST` (BedrockPacketType)
* Added `SERVER_SETTINGS_RESPONSE` (BedrockPacketType)
* Added `SERVER_STATS` (BedrockPacketType)
* Added `SERVER_STORE_INFO` (BedrockPacketType)
* Added `SERVER_TO_CLIENT_HANDSHAKE` (BedrockPacketType)
* Added `SET_ACTOR_DATA` (BedrockPacketType)
* Added `SET_ACTOR_LINK` (BedrockPacketType)
* Added `SET_ACTOR_MOTION` (BedrockPacketType)
* Added `SET_COMMANDS_ENABLED` (BedrockPacketType)
* Added `SET_DEFAULT_GAME_TYPE` (BedrockPacketType)
* Added `SET_DIFFICULTY` (BedrockPacketType)
* Added `SET_DISPLAY_OBJECTIVE` (BedrockPacketType)
* Added `SET_HEALTH` (BedrockPacketType)
* Added `SET_HUD` (BedrockPacketType)
* Added `SET_LAST_HURT_BY` (BedrockPacketType)
* Added `SET_LOCAL_PLAYER_AS_INITIALIZED` (BedrockPacketType)
* Added `SET_MOVEMENT_AUTHORITY` (BedrockPacketType)
* Added `SET_PLAYER_FURNACE_OPTIONS` (BedrockPacketType)
* Added `SET_PLAYER_GAME_TYPE` (BedrockPacketType)
* Added `SET_PLAYER_INVENTORY_OPTIONS` (BedrockPacketType)
* Added `SET_SCOREBOARD_IDENTITY` (BedrockPacketType)
* Added `SET_SCORE` (BedrockPacketType)
* Added `SET_SPAWN_POSITION` (BedrockPacketType)
* Added `SET_TIME` (BedrockPacketType)
* Added `SETTINGS_COMMAND` (BedrockPacketType)
* Added `SET_TITLE` (BedrockPacketType)
* Added `SHOW_CREDITS` (BedrockPacketType)
* Added `SHOW_PROFILE` (BedrockPacketType)
* Added `SHOW_STORE_OFFER` (BedrockPacketType)
* Added `SIMPLE_EVENT` (BedrockPacketType)
* Added `SIMULATION_TYPE` (BedrockPacketType)
* Added `SPAWN_EXPERIENCE_ORB` (BedrockPacketType)
* Added `SPAWN_PARTICLE_EFFECT` (BedrockPacketType)
* Added `START_GAME` (BedrockPacketType)
* Added `STOP_SOUND` (BedrockPacketType)
* Added `STRUCTURE_BLOCK_UPDATE` (BedrockPacketType)
* Added `STRUCTURE_TEMPLATE_DATA_REQUEST` (BedrockPacketType)
* Added `STRUCTURE_TEMPLATE_DATA_RESPONSE` (BedrockPacketType)
* Added `SUB_CHUNK` (BedrockPacketType)
* Added `SUB_CHUNK_REQUEST` (BedrockPacketType)
* Added `SUB_CLIENT_LOGIN` (BedrockPacketType)
* Added `SYNC_ACTOR_PROPERTY` (BedrockPacketType)
* Added `SYNC_WORLD_CLOCKS` (BedrockPacketType)
* Added `TAKE_ITEM_ACTOR` (BedrockPacketType)
* Added `TEXT` (BedrockPacketType)
* Added `TICKING_AREAS_LOAD_STATUS` (BedrockPacketType)
* Added `TICK_SYNC` (BedrockPacketType)
* Added `TOAST_REQUEST` (BedrockPacketType)
* Added `TRANSFER` (BedrockPacketType)
* Added `TRIM_DATA` (BedrockPacketType)
* Added `UNLOCKED_RECIPES` (BedrockPacketType)
* Added `UPDATE_ABILITIES` (BedrockPacketType)
* Added `UPDATE_ADVENTURE_SETTINGS` (BedrockPacketType)
* Added `UPDATE_ATTRIBUTES` (BedrockPacketType)
* Added `UPDATE_BLOCK` (BedrockPacketType)
* Added `UPDATE_BLOCK_PROPERTIES` (BedrockPacketType)
* Added `UPDATE_BLOCK_SYNCED` (BedrockPacketType)
* Added `UPDATE_CLIENT_INPUT_LOCKS` (BedrockPacketType)
* Added `UPDATE_CLIENT_OPTIONS` (BedrockPacketType)
* Added `UPDATE_EQUIP` (BedrockPacketType)
* Added `UPDATE_PLAYER_GAME_TYPE` (BedrockPacketType)
* Added `UPDATE_SOFT_ENUM` (BedrockPacketType)
* Added `UPDATE_SUB_CHUNK_BLOCKS` (BedrockPacketType)
* Added `UPDATE_TRADE` (BedrockPacketType)
* Added `VOXEL_SHAPES` (BedrockPacketType)
* Added `ENTITY_FALL` (BedrockPacketType)
* Added `EXPLODE` (BedrockPacketType)
* Added `ITEM_FRAME_DROP_ITEM` (BedrockPacketType)
* Added `VIDEO_STREAM_CONNECT` (BedrockPacketType)
* Added `LEVEL_SOUND_EVENT_1` (BedrockPacketType)
* Added `LEVEL_SOUND_EVENT_2` (BedrockPacketType)
* Added `SCRIPT_CUSTOM_EVENT` (BedrockPacketType)
* Added `RIDER_JUMP` (BedrockPacketType)
* Added `PLAYER_INPUT` (BedrockPacketType)
* Added `CRAFTING_EVENT` (BedrockPacketType)
* Added `CLIENT_CHEAT_ABILITY` (BedrockPacketType)
* Added `PHOTO_INFO_REQUEST` (BedrockPacketType)
* Added `name` (String)

BiomeDefinitionListPacket:
* Added `stringList` (BiomeStringList)
* Added `mapOfBiomeNamesToData` (Int2ObjectMap<BiomeDefinitionData>)
* Added `definitions` (NbtMap)
* Removed `biomes` (BiomeDefinitions)
* Removed `definitions` (NbtMap)

BlockActorDataPacket:
* Renamed `data` to `actorDataTags` (NbtMap)

BlockEventPacket:
* Renamed `eventData` to `eventValue` (int)

BlockPickRequestPacket:
* Renamed `blockPosition` to `position` (Vector3i)
* Renamed `addUserData` to `withData` (boolean)
* Renamed `hotbarSlot` to `maxSlots` (int)

BookEditPacket:
* Added `operation` (Object)
* Added `bookSlot` (int)
* Removed `action` (Action)
* Removed `text` (String)
* Removed `photoName` (String)
* Removed `title` (String)
* Removed `author` (String)
* Removed `xuid` (String)
* Removed `inventorySlot` (int)
* Removed `pageNumber` (int)
* Removed `secondaryPageNumber` (int)

BossEventPacket:
* Renamed `healthPercentage` to `healthPercent` (float)
* Added `color` (BossBarColor)
* Added `overlay` (BossBarOverlay)
* Added `eventType` (BossEventUpdateType)
* Added `name` (CharSequence)
* Added `filteredName` (CharSequence)
* Added `darkenScreen` (int)
* Added `targetActorID` (long)
* Added `playerID` (long)
* Removed `action` (Action)
* Removed `title` (String)
* Removed `filteredTitle` (String)
* Removed `darkenSky` (int)
* Removed `color` (int)
* Removed `overlay` (int)
* Removed `bossUniqueEntityId` (long)
* Removed `playerUniqueEntityId` (long)

CameraAimAssistActorPriorityPacket:
* Added `cameraAimassistActorPriorityList` (List<AimAssistActorPriorityData>)

CameraAimAssistInstructionPacket:
* Removed `action` (AimAssistAction)
* Removed `presetId` (String)
* Removed `allowAimAssist` (boolean)

CameraAimAssistPacket:
* Added `action` (Action)
* Added `VALUES` (Action[])
* Added `targetMode` (TargetMode)
* Added `VALUES` (TargetMode[])
* Added `viewAngle` (Vector2f)
* Added `distance` (float)
* Removed `action` (AimAssistAction)
* Removed `targetMode` (TargetMode)
* Removed `viewAngle` (Vector2f)
* Removed `distance` (float)

CameraAimAssistPresetsPacket:
* Renamed `categories` to `cameraAimAssistCategoriesDeprecated` (List<CameraAimAssistCategories>)
* Renamed `presets` to `cameraAimAssistPresets` (List<CameraAimAssistPresetDefinition>)
* Added `operation` (CameraAimAssistPresetsPacketOperation)
* Added `cameraAimAssistCategories` (List<CameraAimAssistCategoryDefinition>)
* Removed `operation` (CameraAimAssistOperation)
* Removed `categoryDefinitions` (List<CameraAimAssistCategory>)

CameraInstructionPacket:
* Added `cameraInstruction` (CameraInstruction)
* Removed `fadeInstruction` (CameraFadeInstruction)
* Removed `fovInstruction` (CameraFovInstruction)
* Removed `setInstruction` (CameraSetInstruction)
* Removed `targetInstruction` (CameraTargetInstruction)
* Removed `clear` (OptionalBoolean)
* Removed `removeTarget` (OptionalBoolean)

CameraPacket:
* Added `cameraID` (long)
* Added `targetPlayerID` (long)
* Removed `cameraUniqueEntityId` (long)
* Removed `playerUniqueEntityId` (long)

CameraPresetsPacket:
* Added `cameraPresets` (List<CameraPresets>)
* Removed `presets` (List<CameraPreset>)

CameraShakePacket:
* Renamed `duration` to `seconds` (float)

CameraSplinePacket:
* Added `cameraDataSplines` (List<CameraSplineDefinition>)

ChangeDimensionPacket:
* Added `dimensionID` (DimensionType)
* Removed `dimension` (int)

ChangeMobPropertyPacket:
* Renamed `boolValue` to `boolComponentValue` (boolean)
* Renamed `floatValue` to `floatComponentValue` (float)
* Renamed `intValue` to `intComponentValue` (int)
* Renamed `uniqueEntityId` to `actorId` (long)
* Added `propertyName` (String)
* Added `stringComponentValue` (String)
* Removed `property` (String)
* Removed `stringValue` (String)

ChunkRadiusUpdatedPacket:
* Renamed `radius` to `chunkRadius` (int)

ClientCacheBlobStatusPacket:
* Added `missingIds` (LongList)
* Added `foundIds` (LongList)
* Removed `acks` (LongList)
* Removed `naks` (LongList)

ClientCacheMissResponsePacket:
* Added `missingBlobs` (List<MissingBlobData>)
* Removed `blobs` (Long2ObjectMap<ByteBuf>)

ClientCacheStatusPacket:
* Renamed `supported` to `isCacheSupported` (boolean)

ClientCameraAimAssistPacket:
* Added `action` (ClientCameraAimAssistPacketAction)
* Added `cameraPresetId` (String)
* Added `allowAimAssist` (boolean)

ClientCheatAbilityPacket:
* Added `data` (SerializedAbilitiesData)
* Removed `commandPermission` (CommandPermission)
* Removed `abilityLayers` (List<AbilityLayer>)
* Removed `playerPermission` (PlayerPermission)
* Removed `uniqueEntityId` (long)

ClientMovementPredictionSyncPacket:
* Added `actorBoundingBox` (ActorDataBoundingBoxComponent)
* Added `actorDataFlag` (ActorDataFlagComponent)
* Added `movementAttributes` (MovementAttributesComponent)
* Added `actorFlyingState` (boolean)
* Added `actorID` (long)

ClientboundAttributeLayerSyncPacket:
* Added `data` (Object)
* Removed `entityLink` (EntityLinkData)

ClientboundControlSchemeSetPacket:
* Renamed `scheme` to `controlScheme` (ControlScheme)

ClientboundDataDrivenUICloseScreenPacket:
* Added `formId` (Integer)

ClientboundDataDrivenUIShowScreenPacket:
* Added `dataInstanceId` (Integer)
* Added `screenId` (String)
* Added `formId` (int)

ClientboundDataStorePacket:
* Added `updates` (List<Object>)

ClientboundDebugRendererPacket:
* Added `debugMarkerData` (DebugMarkerData)
* Added `type` (PayloadType)
* Removed `debugMarkerType` (ClientboundDebugRendererType)
* Removed `markerText` (String)
* Removed `markerPosition` (Vector3f)
* Removed `markerColorRed` (float)
* Removed `markerColorGreen` (float)
* Removed `markerColorBlue` (float)
* Removed `markerColorAlpha` (float)
* Removed `markerDuration` (long)

ClientboundMapItemDataPacket:
* Renamed `trackedEntityIds` to `creationMapIDs` (LongList)
* Renamed `origin` to `mapOrigin` (Vector3i)
* Renamed `locked` to `isLocked` (boolean)
* Renamed `uniqueMapId` to `mapID` (long)
* Added `dimension` (DimensionType)
* Added `pixels` (IntList)
* Added `scale` (Integer)
* Added `width` (Integer)
* Added `height` (Integer)
* Added `startX` (Integer)
* Added `startY` (Integer)
* Added `decorations` (List<MapDecoration>)
* Added `trackedActorIDs` (List<MapItemTrackedActorUniqueId>)
* Removed `decorations` (List<MapDecoration>)
* Removed `trackedObjects` (List<MapTrackedObject>)
* Removed `dimensionId` (int)
* Removed `scale` (int)
* Removed `height` (int)
* Removed `width` (int)
* Removed `xOffset` (int)
* Removed `yOffset` (int)
* Removed `colors` (int[])

ClientboundMatchmakingStatePacket:
* Added `state` (MatchmakingState)
* Added `destinationName` (String)

ClientboundStonecutterSetRecipePacket:
* Added `containerId` (int)
* Added `recipeIndex` (int)
* Added `playerId` (long)

ClientboundTextureShiftPacket:
* Added `actionID` (Action)
* Added `VALUES` (Action[])
* Added `allSteps` (List<String>)
* Added `collectionName` (String)
* Added `fromStep` (String)
* Added `toStep` (String)
* Added `enabled` (boolean)
* Added `currentLengthInTicks` (long)
* Added `totalLengthInTicks` (long)

ClientboundUpdateSoundDataPacket:
* Added `stop` (Object)
* Added `setVolume` (Object)
* Added `setPitch` (Object)
* Added `fade` (Object)
* Added `seekTo` (Object)
* Added `pause` (Object)
* Added `resume` (Object)
* Added `serverSoundHandle` (ServerSoundHandle)
* Added `soundEvent` (SoundDataEvent)

CodeBuilderPacket:
* Renamed `url` to `URL` (String)
* Renamed `opening` to `shouldOpenCodeBuilder` (boolean)

CodeBuilderSourcePacket:
* Added `codeStatus` (CodeBuilderExecutionStateCodeStatus)
* Added `category` (CodeBuilderStorageQueryOptionsCategory)
* Added `operation` (CodeBuilderStorageQueryOptionsOperation)
* Removed `category` (CodeBuilderCategoryType)
* Removed `codeStatus` (CodeBuilderCodeStatus)
* Removed `operation` (CodeBuilderOperationType)
* Removed `value` (String)

CommandBlockUpdatePacket:
* Added `target` (Object)
* Added `trackOutput` (boolean)
* Added `executeOnFirstTick` (boolean)
* Added `tickDelay` (int)
* Removed `mode` (CommandBlockMode)
* Removed `blockPosition` (Vector3i)
* Removed `block` (boolean)
* Removed `redstoneMode` (boolean)
* Removed `conditional` (boolean)
* Removed `outputTracked` (boolean)
* Removed `executingOnFirstTick` (boolean)
* Removed `minecartRuntimeEntityId` (long)
* Removed `tickDelay` (long)

CommandOutputPacket:
* Renamed `commandOriginData` to `originData` (CommandOriginData)
* Added `output` (CommandOutput)
* Removed `type` (CommandOutputType)
* Removed `messages` (List<CommandOutputMessage>)
* Removed `data` (String)
* Removed `successCount` (int)

CommandRequestPacket:
* Renamed `commandOriginData` to `origin` (CommandOriginData)
* Renamed `internal` to `isInternal` (boolean)
* Added `version` (CurrentCmdVersion)
* Removed `version` (int)

CompletedUsingItemPacket:
* Added `itemUseMethod` (ItemUseMethod)
* Removed `type` (ItemUseType)

ContainerClosePacket:
* Renamed `type` to `containerType` (ContainerType)
* Renamed `serverInitiated` to `serverInitiatedClose` (boolean)
* Added `containerId` (int)
* Removed `id` (byte)

ContainerOpenPacket:
* Renamed `type` to `containerType` (ContainerType)
* Renamed `blockPosition` to `position` (Vector3i)
* Renamed `uniqueEntityId` to `targetActorID` (long)
* Added `containerId` (int)
* Removed `id` (byte)

ContainerRegistryCleanupPacket:
* Renamed `containers` to `removedContainers` (List<FullContainerName>)

ContainerSetDataPacket:
* Added `containerID` (int)
* Added `ID` (int)
* Removed `windowId` (byte)
* Removed `property` (int)

CorrectPlayerMovePredictionPacket:
* Renamed `vehicleRotation` to `rotation` (Vector2f)
* Added `tick` (PlayerInputTick)
* Added `predictionType` (RewindType)
* Added `pos` (Vector3f)
* Added `posDelta` (Vector3f)
* Added `onGround` (boolean)
* Removed `predictionType` (PredictionType)
* Removed `position` (Vector3f)
* Removed `delta` (Vector3f)
* Removed `onGround` (boolean)
* Removed `tick` (long)

CraftingDataPacket:
* Renamed `cleanRecipes` to `clearRecipes` (boolean)
* Added `containerMixes` (List<ContainerMixDataEntry>)
* Added `furnaceRecipes` (List<FurnaceRecipePayload>)
* Added `materialReducers` (List<MaterialReducerDataEntry>)
* Added `multiRecipes` (List<MultiRecipePayload>)
* Added `potionMixes` (List<PotionMixDataEntry>)
* Added `shapedRecipes` (List<ShapedRecipePayload>)
* Added `shapedChemistryRecipes` (List<ShapedRecipePayload>)
* Added `shapelessRecipes` (List<ShapelessRecipePayload>)
* Added `userDataShapelessRecipes` (List<ShapelessRecipePayload>)
* Added `shapelessChemistryRecipes` (List<ShapelessRecipePayload>)
* Added `smithingTransformRecipes` (List<SmithingTransformRecipePayload>)
* Added `smithingTrimRecipes` (List<SmithingTrimRecipePayload>)
* Removed `containerMixData` (List<ContainerMixData>)
* Removed `materialReducers` (List<MaterialReducer>)
* Removed `potionMixData` (List<PotionMixData>)
* Removed `craftingData` (List<RecipeData>)

CreatePhotoPacket:
* Renamed `id` to `rawID` (long)

CreativeContentPacket:
* Added `groups` (List<CreativeGroupInfoPayload>)
* Added `entries` (List<CreativeItemEntryPayload>)
* Removed `contents` (List<CreativeItemData>)
* Removed `groups` (List<CreativeItemGroup>)

DeathInfoPacket:
* Added `deathCauseMessage` (DeathCauseMessageType)
* Removed `messageList` (List<String>)
* Removed `causeAttackName` (String)

DebugInfoPacket:
* Renamed `uniqueEntityId` to `actorId` (long)

DisconnectPacket:
* Added `reason` (DisconnectFailReason)
* Added `messages` (DisconnectPacketMessages)
* Removed `reason` (DisconnectFailReason)
* Removed `kickMessage` (String)
* Removed `filteredMessage` (String)
* Removed `messageSkipped` (boolean)

EditorNetworkPacket:
* Renamed `payload` to `binaryPayload` (Object)
* Added `rawVariantName` (String)
* Added `rawVariantData` (String)
* Added `routeToManager` (boolean)
* Removed `routeToManager` (boolean)

EduUriResourcePacket:
* Renamed `eduSharedUriResource` to `eduSharedURIResource` (EduSharedUriResource)

EducationSettingsPacket:
* Added `educationLevelSettings` (EducationLevelSettings)
* Removed `overrideUri` (Optional<String>)
* Removed `entityCapabilities` (OptionalBoolean)
* Removed `externalLinkSettings` (OptionalBoolean)
* Removed `codeBuilderUri` (String)
* Removed `codeBuilderTitle` (String)
* Removed `postProcessFilter` (String)
* Removed `screenshotBorderPath` (String)
* Removed `canResizeCodeBuilder` (boolean)
* Removed `disableLegacyTitle` (boolean)
* Removed `quizAttached` (boolean)

EmoteListPacket:
* Renamed `pieceIds` to `emotePieceIds` (List<UUID>)
* Renamed `runtimeEntityId` to `runtimeId` (long)

EmotePacket:
* Renamed `emoteDuration` to `emoteLengthTicks` (int)
* Renamed `runtimeEntityId` to `actorRuntimeId` (long)
* Added `emoteId` (String)
* Removed `emoteId` (String)

EventPacket:
* Removed `eventData` (EventData)
* Removed `usePlayerId` (byte)
* Removed `uniqueEntityId` (long)

FeatureRegistryPacket:
* Added `featuresDataList` (List<FeatureRegistryFeatureBinaryJsonFormat>)
* Removed `features` (List<FeatureDefinition>)

GameRulesChangedPacket:
* Added `ruleData` (GameRulesChangedPacketData)
* Removed `gameRules` (List<GameRuleData<?>>)

GameTestRequestPacket:
* Renamed `stoppingOnFailure` to `stopOnFailure` (boolean)
* Added `rotation` (Rotation)
* Removed `rotation` (int)

GameTestResultsPacket:
* Renamed `successful` to `succeeded` (boolean)

GraphicsOverrideParameterPacket:
* Added `floatValue` (Float)
* Added `identifierForParameter` (GraphicsOverrideParameterType)
* Added `parameterKeyframeValues` (Map<Float, Vector3f>)
* Added `biomeIdentifier` (String)
* Added `playerIdentifier` (String)
* Added `vec3Value` (Vector3f)
* Added `resetParameter` (boolean)

GuiDataPickItemPacket:
* Renamed `hotbarSlot` to `slot` (int)
* Added `itemName` (String)
* Added `itemEffectName` (String)
* Removed `description` (String)
* Removed `itemEffects` (String)

InteractPacket:
* Renamed `mousePosition` to `position` (Vector3f)
* Renamed `runtimeEntityId` to `targetRuntimeID` (long)
* Added `VALUES` (Action[])

InventoryContentPacket:
* Renamed `containerNameData` to `fullContainerName` (FullContainerName)
* Renamed `contents` to `slots` (List<ItemData>)
* Added `storageItem` (ItemData)
* Removed `storageItem` (ItemData)

InventorySlotPacket:
* Renamed `containerNameData` to `fullContainerName` (FullContainerName)
* Added `storageItem` (ItemData)
* Added `item` (ItemData)
* Removed `item` (ItemData)
* Removed `storageItem` (ItemData)

InventoryTransactionPacket:
* Added `transaction` (InventoryTransactionData)
* Added `legacyRequestID` (ItemStackLegacyRequestId)
* Added `legacySetItemSlots` (List<LegacySetSlot>)
* Removed `blockDefinition` (BlockDefinition)
* Removed `transactionType` (InventoryTransactionType)
* Removed `itemInHand` (ItemData)
* Removed `clientInteractPrediction` (ItemUseTransaction.PredictedResult)
* Removed `triggerType` (ItemUseTransaction.TriggerType)
* Removed `actions` (List<InventoryActionData>)
* Removed `legacySlots` (List<LegacySetItemSlotData>)
* Removed `playerPosition` (Vector3f)
* Removed `clickPosition` (Vector3f)
* Removed `headPosition` (Vector3f)
* Removed `blockPosition` (Vector3i)
* Removed `usingNetIds` (boolean)
* Removed `legacyRequestId` (int)
* Removed `actionType` (int)
* Removed `blockFace` (int)
* Removed `hotbarSlot` (int)
* Removed `runtimeEntityId` (long)

ItemRegistryPacket:
* Added `itemData` (List<SimpleItemDefinition>)

ItemStackRequestPacket:
* Added `requests` (List<ItemStackRequest>)
* Removed `requests` (List<ItemStackRequest>)

ItemStackResponsePacket:
* Added `responses` (List<ItemStackResponseInfo>)
* Removed `entries` (List<ItemStackResponse>)

LabTablePacket:
* Renamed `reactionType` to `reaction` (LabTableReactionType)
* Added `type` (Type)
* Added `VALUES` (Type[])
* Removed `type` (LabTableType)

LecternUpdatePacket:
* Renamed `blockPosition` to `positionOfLecternToUpdate` (Vector3i)
* Renamed `page` to `newPageToShow` (int)

LegacyTelemetryEventPacket:
* Added `eventType` (LegacyTelemetryEventPacket.Type)
* Added `eventData` (Object)
* Added `VALUES` (Type[])
* Added `usePlayerID` (boolean)
* Added `newId` (int)
* Added `targetActorID` (long)

LessonProgressPacket:
* Renamed `action` to `lessonAction` (LessonAction)

LevelChunkPacket:
* Renamed `data` to `serializedChunkData` (ByteBuf)
* Renamed `blobIds` to `cacheBlobs` (LongList)
* Added `dimension` (DimensionType)
* Added `clientRequestSubChunkLimit` (Integer)
* Added `cacheEnabled` (boolean)
* Added `clientNeedsToRequestSubChunks` (boolean)
* Added `subChunksCount` (int)
* Removed `cachingEnabled` (boolean)
* Removed `requestSubChunks` (boolean)
* Removed `subChunksLength` (int)
* Removed `subChunkLimit` (int)
* Removed `dimension` (int)

LevelEventGenericPacket:
* Renamed `type` to `eventId` (LevelEventType)
* Added `data` (NbtMap)
* Removed `tag` (Object)

LevelEventPacket:
* Renamed `type` to `eventId` (LevelEventType)

LevelSoundEvent1Packet:
* Added `sound` (LevelSoundEvent)
* Removed `sound` (SoundEvent)

LevelSoundEvent2Packet:
* Added `sound` (LevelSoundEvent)
* Removed `sound` (SoundEvent)

LevelSoundEventPacket:
* Renamed `identifier` to `actorIdentifier` (String)
* Renamed `extraData` to `data` (int)
* Renamed `entityUniqueId` to `actorUniqueId` (long)
* Added `sound` (LevelSoundEvent)
* Added `fireAtPosition` (Vector3f)
* Added `isBaby` (boolean)
* Added `isGlobal` (boolean)
* Removed `sound` (SoundEvent)
* Removed `babySound` (boolean)
* Removed `relativeVolumeDisabled` (boolean)

LocatorBarPacket:
* Added `waypoints` (List<LocatorBarWaypointPayload>)

LoginPacket:
* Renamed `protocolVersion` to `clientNetworkVersion` (int)
* Added `chain` (List<String>)
* Added `authenticationType` (PlayerAuthenticationType)
* Added `token` (String)
* Removed `authPayload` (AuthPayload)

MapInfoRequestPacket:
* Renamed `uniqueMapId` to `mapUniqueID` (long)
* Added `clientPixelsList` (List<ClientPixelsProxy>)
* Removed `pixels` (List<MapPixel>)

MobArmorEquipmentPacket:
* Renamed `runtimeEntityId` to `targetRuntimeID` (long)
* Added `head` (ItemData)
* Added `torso` (ItemData)
* Added `legs` (ItemData)
* Added `feet` (ItemData)
* Removed `helmet` (ItemData)
* Removed `chestplate` (ItemData)
* Removed `leggings` (ItemData)
* Removed `boots` (ItemData)

MobEffectPacket:
* Renamed `event` to `eventID` (Event)
* Added `VALUES` (Event[])
* Added `tick` (PlayerInputTick)
* Added `showParticles` (boolean)
* Added `ambient` (boolean)
* Added `effectID` (int)
* Added `effectAmplifier` (int)
* Added `effectDurationTicks` (int)
* Added `targetRuntimeID` (long)
* Removed `particles` (boolean)
* Removed `effectId` (int)
* Removed `amplifier` (int)
* Removed `duration` (int)
* Removed `runtimeEntityId` (long)
* Removed `tick` (long)

MobEquipmentPacket:
* Renamed `runtimeEntityId` to `targetRuntimeID` (long)
* Added `slot` (int)
* Added `selectedSlot` (int)
* Added `containerID` (int)
* Removed `inventorySlot` (int)
* Removed `hotbarSlot` (int)
* Removed `containerId` (int)

ModalFormRequestPacket:
* Renamed `formData` to `formUiJson` (String)
* Renamed `formId` to `formID` (int)

ModalFormResponsePacket:
* Renamed `formData` to `jsonResponse` (String)
* Renamed `formId` to `formID` (int)
* Added `formCancelReason` (ModalFormCancelReason)
* Removed `cancelReason` (Optional<ModalFormCancelReason>)

MotionPredictionHintsPacket:
* Renamed `runtimeEntityId` to `runtimeId` (long)

MoveActorAbsolutePacket:
* Added `moveData` (MoveActorAbsoluteData)

MoveActorDeltaPacket:
* Added `moveData` (MoveActorDeltaData)
* Removed `x` (float)
* Removed `y` (float)
* Removed `z` (float)
* Removed `pitch` (float)
* Removed `yaw` (float)
* Removed `headYaw` (float)
* Removed `runtimeEntityId` (long)

MovePlayerPacket:
* Added `teleportData` (MovePlayerTeleportData)
* Added `positionMode` (PositionMode)
* Added `rotation` (Vector2f)
* Added `yHeadRotation` (float)
* Added `playerRuntimeID` (long)
* Added `ridingRuntimeID` (long)
* Removed `log` (InternalLogger)
* Removed `mode` (Mode)
* Removed `teleportationCause` (TeleportationCause)
* Removed `VALUES` (TeleportationCause[])
* Removed `rotation` (Vector3f)
* Removed `entityType` (int)
* Removed `runtimeEntityId` (long)
* Removed `ridingRuntimeEntityId` (long)

MovementEffectPacket:
* Renamed `effectType` to `effectID` (MovementEffectType)
* Renamed `duration` to `effectDuration` (int)
* Added `tick` (PlayerInputTick)
* Added `targetRuntimeID` (long)
* Removed `entityRuntimeId` (long)
* Removed `tick` (long)

MovementPredictionSyncPacket:
* Removed `flags` (Set<EntityFlag>)
* Removed `boundingBox` (Vector3f)
* Removed `flying` (boolean)
* Removed `speed` (float)
* Removed `underwaterSpeed` (float)
* Removed `lavaSpeed` (float)
* Removed `jumpStrength` (float)
* Removed `health` (float)
* Removed `hunger` (float)
* Removed `runtimeEntityId` (long)

MultiplayerSettingsPacket:
* Added `type` (MultiplayerSettingsPacketType)
* Removed `mode` (MultiplayerMode)

NetworkChunkPublisherUpdatePacket:
* Renamed `position` to `newPositionForView` (Vector3i)
* Renamed `radius` to `newRadiusForView` (int)
* Added `serverBuiltChunksList` (List<ChunkPos>)
* Removed `savedChunks` (List<Vector2i>)

NetworkStackLatencyPacket:
* Renamed `fromServer` to `isFromServer` (boolean)
* Renamed `timestamp` to `creationTime` (long)

NpcDialoguePacket:
* Renamed `uniqueEntityId` to `npcIdRawId` (long)
* Added `npcDialogueActionType` (NpcDialogueActionType)
* Removed `action` (Action)

NpcRequestPacket:
* Renamed `command` to `actions` (String)
* Renamed `actionType` to `actionIndex` (int)
* Renamed `runtimeEntityId` to `npcRuntimeID` (long)
* Added `requestType` (RequestType)
* Added `VALUES` (RequestType[])
* Removed `requestType` (NpcRequestType)

OnScreenTextureAnimationPacket:
* Added `effectId` (int)
* Removed `effectId` (long)

OpenSignPacket:
* Renamed `position` to `pos` (Vector3i)
* Renamed `frontSide` to `isFrontSide` (boolean)

PacketViolationWarningPacket:
* Renamed `severity` to `violationSeverity` (PacketViolationSeverity)
* Renamed `type` to `violationType` (PacketViolationType)
* Renamed `context` to `violationContext` (String)
* Renamed `packetCauseId` to `violationPacketid` (int)

PartyChangedPacket:
* Added `partyInfo` (PlayerPartyInfo)

PartyDestinationCookieResponsePacket:
* Added `cookie` (String)
* Added `accepted` (boolean)

PhotoTransferPacket:
* Renamed `photoType` to `type` (PhotoType)
* Renamed `ownerId` to `ownerID` (long)
* Added `photoName` (String)
* Added `photoData` (String)
* Added `bookID` (String)
* Removed `name` (String)
* Removed `bookId` (String)
* Removed `data` (byte[])

PlaySoundPacket:
* Renamed `sound` to `name` (String)
* Added `playbackPositionSeconds` (Float)
* Added `serverSoundHandle` (ServerSoundHandle)
* Added `bypassListenerRangeCheck` (boolean)
* Added `loopCount` (int)

PlayStatusPacket:
* Added `status` (PlayStatus)
* Removed `status` (Status)

PlayerActionPacket:
* Renamed `resultPosition` to `resultPos` (Vector3i)
* Renamed `runtimeEntityId` to `playerRuntimeID` (long)

PlayerArmorDamagePacket:
* Added `armorSlotAndDamagePairs` (List<ArmorSlotAndDamagePair>)
* Removed `flags` (Set<PlayerArmorDamageFlag>)
* Removed `damage` (int[])

PlayerAuthInputPacket:
* Renamed `playerActions` to `playerBlockActions` (List<PlayerBlockActionData>)
* Added `playMode` (ClientPlayMode)
* Added `clientPredictedVehicle` (Long)
* Added `newInteractionModel` (NewInteractionModel)
* Added `itemUseTransaction` (PackedItemUseLegacyInventoryTransaction)
* Added `clientTick` (PlayerInputTick)
* Added `playerRotation` (Vector2f)
* Added `moveVector` (Vector2f)
* Added `interactRotation` (Vector2f)
* Added `vehicleRotation` (Vector2f)
* Added `posDelta` (Vector3f)
* Added `playerHeadRotation` (float)
* Removed `playMode` (ClientPlayMode)
* Removed `inputInteractionModel` (InputInteractionModel)
* Removed `itemUseTransaction` (ItemUseTransaction)
* Removed `motion` (Vector2f)
* Removed `interactRotation` (Vector2f)
* Removed `vehicleRotation` (Vector2f)
* Removed `rotation` (Vector3f)
* Removed `delta` (Vector3f)
* Removed `tick` (long)
* Removed `predictedVehicle` (long)

PlayerEnchantOptionsPacket:
* Added `options` (List<ItemEnchantOption>)
* Removed `options` (List<EnchantOptionData>)

PlayerHotbarPacket:
* Renamed `selectHotbarSlot` to `shouldSelectSlot` (boolean)
* Added `selectedSlot` (int)
* Added `containerID` (int)
* Removed `selectedHotbarSlot` (int)
* Removed `containerId` (int)

PlayerListPacket:
* Added `entries` (List<Object>)
* Removed `action` (Action)
* Removed `color` (Color)
* Removed `entries` (List<Entry>)
* Removed `skin` (SerializedSkin)
* Removed `name` (String)
* Removed `xuid` (String)
* Removed `platformChatId` (String)
* Removed `uuid` (UUID)
* Removed `teacher` (boolean)
* Removed `host` (boolean)
* Removed `trustedSkin` (boolean)
* Removed `subClient` (boolean)
* Removed `buildPlatform` (int)
* Removed `entityId` (long)

PlayerLocationPacket:
* Renamed `targetEntityId` to `targetActorID` (long)
* Added `location` (Object)
* Removed `type` (Type)
* Removed `position` (Vector3f)

PlayerSkinPacket:
* Renamed `skin` to `serializedSkin` (SerializedSkin)
* Added `localizedNewSkinName` (String)
* Added `localizedOldSkinName` (String)
* Removed `newSkinName` (String)
* Removed `oldSkinName` (String)

PlayerStartItemCooldownPacket:
* Renamed `cooldownDuration` to `durationTicks` (int)

PlayerToggleCrafterSlotRequestPacket:
* Added `isDisabled` (boolean)
* Added `posX` (int)
* Added `posY` (int)
* Added `posZ` (int)
* Added `slotIndex` (int)

PlayerUpdateEntityOverridesPacket:
* Renamed `entityUniqueId` to `targetID` (long)
* Added `id` (String)
* Added `VALUES` (UpdateType[])

PositionTrackingDBClientRequestPacket:
* Added `VALUES` (Action[])
* Added `id` (PositionTrackingId)
* Removed `trackingId` (int)

PositionTrackingDBServerBroadcastPacket:
* Renamed `tag` to `positionTrackingData` (NbtMap)
* Added `VALUES` (Action[])
* Added `id` (PositionTrackingId)
* Removed `trackingId` (int)

PrimitiveShapesPacket:
* Added `shapes` (List<PrimitiveShapeDataPayload>)
* Removed `items` (List<ItemDefinition>)

PurchaseReceiptPacket:
* Renamed `receipts` to `purchaseReceipts` (List<String>)

RecordStartedPacket:
* Added `serverSoundHandle` (ServerSoundHandle)
* Removed `disabled` (boolean)
* Removed `slot` (byte)

RemoveActorPacket:
* Renamed `uniqueEntityId` to `targetActorID` (long)

RemoveObjectivePacket:
* Renamed `objectiveId` to `objectiveName` (String)

RemoveVolumeEntityPacket:
* Added `dimensionType` (DimensionType)
* Added `entityNetworkId` (EntityNetId)
* Removed `id` (int)
* Removed `dimension` (int)

RequestAbilityPacket:
* Renamed `boolValue` to `bool` (boolean)
* Added `ability` (AbilitiesIndex)
* Added `valueType` (Type)
* Added `VALUES` (Type[])
* Removed `ability` (Ability)
* Removed `type` (Ability.Type)

RequestChunkRadiusPacket:
* Added `chunkRadius` (int)
* Added `maxChunkradius` (int)
* Removed `radius` (int)
* Removed `maxRadius` (int)

RequestNetworkSettingsPacket:
* Renamed `protocolVersion` to `clientNetworkVersion` (int)

RequestPermissionsPacket:
* Renamed `customPermissions` to `customPermissionFlags` (int)
* Renamed `uniqueEntityId` to `targetPlayerIdsRawID` (long)
* Added `playerPermissionLevel` (PlayerPermissionLevel)
* Removed `permissions` (PlayerPermission)

ResourcePackChunkDataPacket:
* Renamed `data` to `chunkData` (ByteBuf)
* Renamed `chunkIndex` to `chunkID` (int)
* Renamed `progress` to `byteOffset` (long)

ResourcePackChunkRequestPacket:
* Renamed `chunkIndex` to `chunk` (int)

ResourcePackClientResponsePacket:
* Renamed `packIds` to `downloadingPacks` (List<String>)
* Added `response` (ResourcePackResponse)
* Removed `status` (Status)

ResourcePackDataInfoPacket:
* Renamed `premium` to `isPremiumPack` (boolean)
* Renamed `hash` to `fileHash` (byte[])
* Added `packType` (PackType)
* Added `chunkSize` (long)
* Added `numberOfChunks` (long)
* Added `fileSize` (long)
* Removed `type` (ResourcePackType)
* Removed `maxChunkSize` (long)
* Removed `chunkCount` (long)
* Removed `compressedPackSize` (long)

ResourcePackStackPacket:
* Added `experiments` (Experiments)
* Added `addonList` (List<PackInstanceId>)
* Added `texturePackList` (List<PackInstanceId>)
* Added `baseGameVersion` (String)
* Added `texturePackRequired` (boolean)
* Added `includeEditorPacks` (boolean)
* Removed `behaviorPacks` (List<Entry>)
* Removed `resourcePacks` (List<Entry>)
* Removed `experiments` (List<ExperimentData>)
* Removed `gameVersion` (String)
* Removed `packId` (String)
* Removed `packVersion` (String)
* Removed `subPackName` (String)
* Removed `forcedToAccept` (boolean)
* Removed `experimentsPreviouslyToggled` (boolean)
* Removed `hasEditorPacks` (boolean)

ResourcePacksInfoPacket:
* Added `resourcePacks` (List<PackInfoData>)
* Added `worldTemplateIdAndVersion` (PackIdVersion)
* Added `resourcePackRequired` (boolean)
* Added `hasScripts` (boolean)
* Added `forceDisableVibrantVisuals` (boolean)
* Removed `behaviorPackInfos` (List<Entry>)
* Removed `resourcePackInfos` (List<Entry>)
* Removed `worldTemplateVersion` (String)
* Removed `packVersion` (String)
* Removed `contentKey` (String)
* Removed `subPackName` (String)
* Removed `contentId` (String)
* Removed `cdnUrl` (String)
* Removed `worldTemplateId` (UUID)
* Removed `packId` (UUID)
* Removed `forcedToAccept` (boolean)
* Removed `scriptingEnabled` (boolean)
* Removed `forcingServerPacksEnabled` (boolean)
* Removed `vibrantVisualsForceDisabled` (boolean)
* Removed `scripting` (boolean)
* Removed `raytracingCapable` (boolean)
* Removed `addonPack` (boolean)
* Removed `packSize` (long)

RespawnPacket:
* Renamed `runtimeEntityId` to `playerRuntimeId` (long)
* Added `state` (PlayerRespawnState)
* Removed `state` (State)

ScriptMessagePacket:
* Added `messageId` (String)
* Added `messageValue` (String)
* Removed `channel` (String)
* Removed `message` (String)

SendPartyDestinationCookiePacket:
* Added `intent` (PartyDestinationCookieIntent)
* Added `cookie` (String)
* Added `destinationName` (String)

ServerPlayerPostMovePositionPacket:
* Renamed `motion` to `pos` (Vector3f)
* Removed `runtimeEntityId` (long)
* Removed `tick` (long)

ServerPresenceInfoPacket:
* Added `presenceConfiguration` (PresenceConfig)
* Removed `type` (EntityEventType)
* Removed `data` (int)
* Removed `runtimeEntityId` (long)

ServerScriptDebugDrawerPacket:
* Removed `shapes` (List<DebugShape>)

ServerSettingsResponsePacket:
* Renamed `formData` to `formUiJson` (String)
* Renamed `formId` to `formID` (int)

ServerStoreInfoPacket:
* Added `clientStoreEntryPointConfiguration` (ClientStoreEntryPointConfig)

ServerToClientHandshakePacket:
* Renamed `jwt` to `handshakeWebtoken` (String)

ServerboundDataDrivenScreenClosedPacket:
* Added `closeReason` (DataDrivenScreenClosedReason)
* Added `formId` (Integer)

ServerboundDataStorePacket:
* Added `update` (DataStoreUpdate)

ServerboundDiagnosticsPacket:
* Added `entityDiagnostics` (List<EntityDiagnosticTimingInfo>)
* Added `memoryCategoryValues` (List<MemoryCategoryCounter>)
* Added `systemCategories` (List<SystemCategory>)
* Added `systemDiagnostics` (List<SystemDiagnosticTimingInfo>)
* Added `whiskerScopes` (List<WhiskerScopeDataSummary>)

ServerboundLoadingScreenPacket:
* Added `loadingScreenPacketType` (LoadingScreenPacketType)
* Removed `type` (ServerboundLoadingScreenPacketType)

ServerboundMatchmakingCancelPacket:
* Removed `itemRuntimeEntityId` (long)
* Removed `runtimeEntityId` (long)

ServerboundPackSettingChangePacket:
* Added `packSettingValue` (Object)
* Added `packSettingName` (String)
* Added `packId` (UUID)

ServerboundStonecutterSetRecipePacket:
* Added `containerId` (int)
* Added `recipeIndex` (int)

SetActorDataPacket:
* Added `actorData` (ActorDataMap)
* Added `tick` (PlayerInputTick)
* Added `synchedProperties` (PropertySyncData)
* Added `targetRuntimeID` (long)

SetActorLinkPacket:
* Added `link` (ActorLink)

SetActorMotionPacket:
* Renamed `runtimeEntityId` to `targetRuntimeID` (long)
* Added `tick` (PlayerInputTick)
* Added `motion` (Vector3f)
* Removed `position` (Vector3f)
* Removed `rotation` (Vector3f)
* Removed `onGround` (boolean)
* Removed `teleported` (boolean)
* Removed `forceMove` (boolean)

SetDefaultGameTypePacket:
* Added `defaultGameType` (DefaultGameType)
* Removed `gamemode` (int)

SetDisplayObjectivePacket:
* Added `objectiveDisplayName` (CharSequence)
* Added `sortOrder` (ObjectiveSortOrder)
* Added `displaySlotName` (String)
* Added `objectiveName` (String)
* Added `criteriaName` (String)
* Removed `displaySlot` (String)
* Removed `objectiveId` (String)
* Removed `displayName` (String)
* Removed `criteria` (String)
* Removed `sortOrder` (int)

SetEntityDataPacket:
* Removed `metadata` (EntityDataMap)
* Removed `properties` (EntityProperties)
* Removed `runtimeEntityId` (long)
* Removed `tick` (long)

SetHudPacket:
* Renamed `visibility` to `hudVisible` (HudVisibility)
* Added `hudElement` (List<HudElement>)
* Removed `elements` (Set<HudElement>)

SetLastHurtByPacket:
* Added `lastHurtBy` (ActorType)
* Removed `entityTypeId` (int)

SetLocalPlayerAsInitializedPacket:
* Renamed `runtimeEntityId` to `playerID` (long)

SetMovementAuthorityPacket:
* Added `movementMode` (ServerAuthMovementMode)
* Removed `movementMode` (AuthoritativeMovementMode)

SetPlayerFurnaceOptionsPacket:
* Added `furnaceOptions` (FurnaceOptions)
* Added `furnaceType` (FurnaceType)
* Added `VALUES` (FurnaceType[])

SetPlayerGameTypePacket:
* Added `playerGameType` (GameType)
* Removed `gamemode` (int)

SetPlayerInventoryOptionsPacket:
* Added `inventoryOptions` (InventoryOptions)
* Removed `layout` (InventoryLayout)
* Removed `craftingLayout` (InventoryLayout)
* Removed `leftTab` (InventoryTabLeft)
* Removed `rightTab` (InventoryTabRight)
* Removed `filtering` (boolean)

SetScorePacket:
* Added `scoreInfo` (List<Object>)
* Removed `action` (Action)
* Removed `infos` (List<ScoreInfo>)

SetScoreboardIdentityPacket:
* Added `scoreboardIdentityInfo` (List<ScoreboardIdentityPacketInfo>)
* Added `scoreboardIdentityPacketType` (ScoreboardIdentityPacketType)
* Removed `action` (Action)
* Removed `entries` (List<Entry>)
* Removed `uuid` (UUID)
* Removed `scoreboardId` (long)

SetSpawnPositionPacket:
* Renamed `spawnPosition` to `spawnBlockPos` (Vector3i)
* Added `dimensionType` (DimensionType)
* Added `spawnPositionType` (SpawnPositionType)
* Removed `spawnType` (Type)
* Removed `dimensionId` (int)

SetTitlePacket:
* Added `titleText` (CharSequence)
* Added `filteredTitleMessage` (CharSequence)
* Added `titleType` (TitleType)
* Added `VALUES` (TitleType[])
* Removed `text` (String)
* Removed `filteredTitleText` (String)
* Removed `type` (Type)

SettingsCommandPacket:
* Renamed `suppressingOutput` to `suppressOutput` (boolean)

ShowCreditsPacket:
* Renamed `runtimeEntityId` to `playerRuntimeID` (long)
* Added `creditsState` (CreditsState)
* Added `VALUES` (CreditsState[])
* Removed `status` (Status)

ShowProfilePacket:
* Renamed `xuid` to `playerXuid` (String)

ShowStoreOfferPacket:
* Added `redirectType` (ShowStoreOfferRedirectType)
* Added `offerId` (UUID)
* Added `shownToAll` (boolean)
* Removed `redirectType` (StoreOfferRedirectType)
* Removed `offerId` (String)
* Removed `shownToAll` (boolean)

SimpleEventPacket:
* Added `type` (Subtype)
* Removed `event` (SimpleEventType)

SimulationTypePacket:
* Renamed `type` to `simType` (SimulationType)

SpawnExperienceOrbPacket:
* Renamed `amount` to `xpValue` (int)

SpawnParticleEffectPacket:
* Renamed `uniqueEntityId` to `actorId` (long)
* Added `dimensionId` (DimensionType)
* Added `effectName` (String)
* Added `molangVariables` (String)
* Removed `molangVariablesJson` (Optional<String>)
* Removed `identifier` (String)
* Removed `dimensionId` (int)

StartGamePacket:
* Renamed `worldTemplateId` to `worldTemplateID` (UUID)
* Renamed `playerPosition` to `position` (Vector3f)
* Added `levelName` (CharSequence)
* Added `gameType` (GameType)
* Added `settings` (LevelSettings)
* Added `itemDefinitions` (List<ItemDefinition>)
* Added `blockPalette` (List<NbtMap>)
* Added `blockProperties` (List<ServerBlockProperty>)
* Added `networkPermissions` (NetworkPermissions)
* Added `serverConfigurationJoinInfo` (ServerConfig)
* Added `serverTelemetryData` (ServerTelemetryData)
* Added `levelID` (String)
* Added `templateContentIdentity` (String)
* Added `serverVersion` (String)
* Added `movementSettings` (SyncedPlayerMovementSettings)
* Added `isTrial` (boolean)
* Added `enableItemStackNetManager` (boolean)
* Added `serverEnabledClientsideGeneration` (boolean)
* Added `blockNetworkIdsAreHashes` (boolean)
* Added `tickDeathSystemsEnabled` (boolean)
* Added `isChatLogging` (boolean)
* Added `entityID` (long)
* Added `runtimeID` (long)
* Added `levelCurrentTime` (long)
* Added `serverBlockTypeRegistryChecksum` (long)
* Removed `authoritativeMovementMode` (AuthoritativeMovementMode)
* Removed `chatRestrictionLevel` (ChatRestrictionLevel)
* Removed `eduSharedUriResource` (EduSharedUriResource)
* Removed `xblBroadcastMode` (GamePublishSetting)
* Removed `platformBroadcastMode` (GamePublishSetting)
* Removed `playerGameType` (GameType)
* Removed `levelGameType` (GameType)
* Removed `blockProperties` (List<BlockPropertyData>)
* Removed `experiments` (List<ExperimentData>)
* Removed `gamerules` (List<GameRuleData<?>>)
* Removed `itemDefinitions` (List<ItemDefinition>)
* Removed `blockPalette` (NbtList<NbtMap>)
* Removed `networkPermissions` (NetworkPermissions)
* Removed `forceExperimentalGameplay` (OptionalBoolean)
* Removed `defaultPlayerPermission` (PlayerPermission)
* Removed `spawnBiomeType` (SpawnBiomeType)
* Removed `customBiomeName` (String)
* Removed `educationProductionId` (String)
* Removed `vanillaVersion` (String)
* Removed `levelId` (String)
* Removed `levelName` (String)
* Removed `premiumWorldTemplateId` (String)
* Removed `serverEngine` (String)
* Removed `serverId` (String)
* Removed `worldId` (String)
* Removed `scenarioId` (String)
* Removed `ownerId` (String)
* Removed `defaultSpawn` (Vector3i)
* Removed `achievementsDisabled` (boolean)
* Removed `eduFeaturesEnabled` (boolean)
* Removed `platformLockedContentConfirmed` (boolean)
* Removed `multiplayerGame` (boolean)
* Removed `broadcastingToLan` (boolean)
* Removed `commandsEnabled` (boolean)
* Removed `texturePacksRequired` (boolean)
* Removed `experimentsPreviouslyToggled` (boolean)
* Removed `bonusChestEnabled` (boolean)
* Removed `startingWithMap` (boolean)
* Removed `trustingPlayers` (boolean)
* Removed `behaviorPackLocked` (boolean)
* Removed `resourcePackLocked` (boolean)
* Removed `fromLockedWorldTemplate` (boolean)
* Removed `usingMsaGamertagsOnly` (boolean)
* Removed `fromWorldTemplate` (boolean)
* Removed `worldTemplateOptionLocked` (boolean)
* Removed `onlySpawningV1Villagers` (boolean)
* Removed `netherType` (boolean)
* Removed `disablingPlayerInteractions` (boolean)
* Removed `disablingPersonas` (boolean)
* Removed `disablingCustomSkins` (boolean)
* Removed `trial` (boolean)
* Removed `inventoriesServerAuthoritative` (boolean)
* Removed `worldEditor` (boolean)
* Removed `clientSideGenerationEnabled` (boolean)
* Removed `emoteChatMuted` (boolean)
* Removed `blockNetworkIdsHashed` (boolean)
* Removed `createdInEditor` (boolean)
* Removed `exportedFromEditor` (boolean)
* Removed `hardcore` (boolean)
* Removed `tickDeathSystemsEnabled` (boolean)
* Removed `rainLevel` (float)
* Removed `lightningLevel` (float)
* Removed `dimensionId` (int)
* Removed `generatorId` (int)
* Removed `difficulty` (int)
* Removed `dayCycleStopTime` (int)
* Removed `eduEditionOffers` (int)
* Removed `serverChunkTickRange` (int)
* Removed `limitedWorldWidth` (int)
* Removed `limitedWorldHeight` (int)
* Removed `rewindHistorySize` (int)
* Removed `uniqueEntityId` (long)
* Removed `runtimeEntityId` (long)
* Removed `seed` (long)
* Removed `currentTick` (long)
* Removed `blockRegistryChecksum` (long)

StopSoundPacket:
* Renamed `stoppingAllSound` to `stopAllSounds` (boolean)

StructureBlockUpdatePacket:
* Renamed `editorData` to `structureData` (StructureEditorData)
* Added `trigger` (boolean)
* Added `isWaterlogged` (boolean)
* Removed `powered` (boolean)
* Removed `waterlogged` (boolean)

StructureTemplateDataRequestPacket:
* Renamed `name` to `structureName` (String)
* Renamed `settings` to `structureSettings` (StructureSettings)
* Renamed `operation` to `requestedOperation` (StructureTemplateRequestOperation)
* Renamed `position` to `structurePosition` (Vector3i)

StructureTemplateDataResponsePacket:
* Renamed `tag` to `structuresNbt` (NbtMap)
* Renamed `name` to `structureName` (String)
* Renamed `type` to `responseType` (StructureTemplateResponseType)
* Removed `save` (boolean)

SubChunkPacket:
* Renamed `centerPosition` to `centerPos` (Vector3i)
* Added `dimensionType` (DimensionType)
* Added `subChunkData` (List<SubChunkPacketData>)
* Removed `subChunks` (List<SubChunkData>)
* Removed `dimension` (int)

SubChunkRequestPacket:
* Renamed `positionOffsets` to `subChunkPosOffsetList` (List<Vector3i>)
* Renamed `subChunkPosition` to `centerPos` (Vector3i)
* Added `dimensionType` (DimensionType)
* Removed `dimension` (int)

SubClientLoginPacket:
* Added `chain` (List<String>)
* Added `authenticationType` (PlayerAuthenticationType)
* Added `subClientConnectionRequest` (String)
* Added `token` (String)
* Removed `authPayload` (AuthPayload)

SyncActorPropertyPacket:
* Added `propertyData` (NbtMap)

SyncEntityPropertyPacket:
* Removed `data` (NbtMap)

SyncWorldClocksPacket:
* Added `data` (SyncWorldClocksPayload)

TakeItemActorPacket:
* Added `itemRuntimeID` (long)
* Added `actorRuntimeID` (long)

TextPacket:
* Renamed `needsTranslation` to `localize` (boolean)
* Added `filteredMessage` (CharSequence)
* Added `body` (Object)
* Added `sendersXUID` (String)
* Added `platformId` (String)
* Added `messageType` (TextPacketType)
* Removed `parameters` (List<String>)
* Removed `sourceName` (String)
* Removed `message` (String)
* Removed `xuid` (String)
* Removed `platformChatId` (String)
* Removed `filteredMessage` (String)
* Removed `type` (Type)

TickingAreasLoadStatusPacket:
* Added `waitingForPreload` (boolean)

ToastRequestPacket:
* Added `title` (CharSequence)
* Added `content` (CharSequence)
* Removed `title` (String)
* Removed `content` (String)

TransferPacket:
* Renamed `address` to `serverAddress` (String)
* Renamed `port` to `serverPort` (int)
* Added `gatheringsConfiguration` (GatheringsConfig)

TrimDataPacket:
* Renamed `materials` to `trimMaterialList` (List<TrimMaterial>)
* Renamed `patterns` to `trimPatternList` (List<TrimPattern>)

UnlockedRecipesPacket:
* Renamed `unlockedRecipes` to `unlockedRecipesList` (List<String>)
* Added `type` (PacketType)
* Added `VALUES` (PacketType[])
* Removed `action` (ActionType)

UpdateAbilitiesPacket:
* Added `data` (SerializedAbilitiesData)
* Removed `commandPermission` (CommandPermission)
* Removed `abilityLayers` (List<AbilityLayer>)
* Removed `playerPermission` (PlayerPermission)
* Removed `uniqueEntityId` (long)

UpdateAdventureSettingsPacket:
* Added `adventureSettings` (AdventureSettings)
* Removed `noPvM` (boolean)
* Removed `noMvP` (boolean)
* Removed `immutableWorld` (boolean)
* Removed `showNameTags` (boolean)
* Removed `autoJump` (boolean)

UpdateAttributesPacket:
* Renamed `attributes` to `attributeList` (List<AttributeData>)
* Added `tick` (PlayerInputTick)
* Added `targetRuntimeID` (long)
* Removed `runtimeEntityId` (long)
* Removed `tick` (long)

UpdateBlockSyncedPacket:
* Renamed `runtimeEntityId` to `uniqueActorId` (long)
* Added `actorSyncMessage` (ActorBlockSyncMessageId)
* Removed `entityBlockSyncType` (BlockSyncType)

UpdateClientInputLocksPacket:
* Renamed `serverPosition` to `serverPos` (Vector3f)
* Renamed `lockComponentData` to `inputLockComponentdata` (int)

UpdateClientOptionsPacket:
* Renamed `graphicsMode` to `graphicsModeChange` (GraphicsMode)
* Added `filterProfanityChange` (OptionalBoolean)

UpdateEquipPacket:
* Renamed `tag` to `data` (NbtMap)
* Renamed `uniqueEntityId` to `entityUniqueId` (long)
* Added `containerId` (int)
* Added `type` (int)
* Added `size` (int)
* Removed `size` (int)
* Removed `windowId` (short)
* Removed `windowType` (short)

UpdatePlayerGameTypePacket:
* Renamed `gameType` to `playerGameType` (GameType)
* Added `tick` (PlayerInputTick)
* Added `targetPlayer` (long)
* Removed `entityId` (long)
* Removed `tick` (long)

UpdateSoftEnumPacket:
* Renamed `type` to `updateType` (SoftEnumUpdateType)
* Added `values` (List<String>)
* Added `enumName` (String)
* Removed `softEnum` (CommandEnumData)

UpdateSubChunkBlocksPacket:
* Added `blocksChanged` (UpdateSubChunkBlocksChangedInfo)
* Added `subChunkBlockPosition` (Vector3i)
* Removed `standardBlocks` (List<BlockChangeEntry>)
* Removed `extraBlocks` (List<BlockChangeEntry>)
* Removed `chunkX` (int)
* Removed `chunkY` (int)
* Removed `chunkZ` (int)

UpdateTradePacket:
* Renamed `offers` to `data` (NbtMap)
* Renamed `newTradingUi` to `useNewTradeScreen` (boolean)
* Added `displayName` (CharSequence)
* Added `type` (int)
* Added `size` (int)
* Added `traderTier` (int)
* Added `entityUniqueId` (long)
* Added `lastTradingPlayer` (long)
* Removed `containerType` (ContainerType)
* Removed `displayName` (String)
* Removed `size` (int)
* Removed `tradeTier` (int)
* Removed `traderUniqueEntityId` (long)
* Removed `playerUniqueEntityId` (long)

VoxelShapesPacket:
* Added `shapes` (List<SerializableVoxelShape>)
* Added `nameMap` (Map<String, VoxelShapesRegistryHandle>)
* Added `customShapeCount` (int)


## New Types

* Achievement (`org.cloudburstmc.protocol.bedrock.data.event.Achievement`)
* ActorDataBoundingBoxComponent (`org.cloudburstmc.protocol.bedrock.data.prediction.ActorDataBoundingBoxComponent`)
* ActorDataFlagComponent (`org.cloudburstmc.protocol.bedrock.data.prediction.ActorDataFlagComponent`)
* ActorDataTypes (`org.cloudburstmc.protocol.bedrock.data.actor.ActorDataTypes`)
* ActorDefinition (`org.cloudburstmc.protocol.bedrock.data.event.ActorDefinition`)
* ActorLink (`org.cloudburstmc.protocol.bedrock.data.actor.link.ActorLink`)
* AddPage (`org.cloudburstmc.protocol.bedrock.data.book.AddPage`)
* AddTimeMarkerData (`org.cloudburstmc.protocol.bedrock.data.clock.AddTimeMarkerData`)
* AdventureSettings (`org.cloudburstmc.protocol.bedrock.data.world.AdventureSettings`)
* AgentCapabilities (`org.cloudburstmc.protocol.bedrock.data.education.AgentCapabilities`)
* AgentCommand (`org.cloudburstmc.protocol.bedrock.data.event.AgentCommand`)
* AimAssistActorPriorityData (`org.cloudburstmc.protocol.bedrock.data.camera.aimassist.AimAssistActorPriorityData`)
* AnimatedImageData (`org.cloudburstmc.protocol.bedrock.data.skin.AnimatedImageData`)
* ArmorSlotAndDamagePair (`org.cloudburstmc.protocol.bedrock.data.player.armor.ArmorSlotAndDamagePair`)
* ArrowDataPayload (`org.cloudburstmc.protocol.bedrock.data.shape.ArrowDataPayload`)
* AttributeData (`org.cloudburstmc.protocol.bedrock.data.actor.attribute.AttributeData`)
* AttributeLayerData (`org.cloudburstmc.protocol.bedrock.data.attribute.AttributeLayerData`)
* AttributeModifier (`org.cloudburstmc.protocol.bedrock.data.actor.attribute.AttributeModifier`)
* AuthorAndMessage (`org.cloudburstmc.protocol.bedrock.data.text.AuthorAndMessage`)
* BellUsed (`org.cloudburstmc.protocol.bedrock.data.event.BellUsed`)
* BiomeConsolidatedFeaturesData (`org.cloudburstmc.protocol.bedrock.data.biome.BiomeConsolidatedFeaturesData`)
* BiomeNoiseGradientSurfaceData (`org.cloudburstmc.protocol.bedrock.data.biome.BiomeNoiseGradientSurfaceData`)
* BiomeReplacementData (`org.cloudburstmc.protocol.bedrock.data.biome.BiomeReplacementData`)
* BiomeReplacementsData (`org.cloudburstmc.protocol.bedrock.data.biome.BiomeReplacementsData`)
* BiomeStringList (`org.cloudburstmc.protocol.bedrock.data.biome.BiomeStringList`)
* BiomeSurfaceBuilderData (`org.cloudburstmc.protocol.bedrock.data.biome.BiomeSurfaceBuilderData`)
* BiomeTagsData (`org.cloudburstmc.protocol.bedrock.data.biome.BiomeTagsData`)
* BlockCommandData (`org.cloudburstmc.protocol.bedrock.data.command.BlockCommandData`)
* BoolAttributeData (`org.cloudburstmc.protocol.bedrock.data.attribute.BoolAttributeData`)
* BossKilled (`org.cloudburstmc.protocol.bedrock.data.event.BossKilled`)
* BoxDataPayload (`org.cloudburstmc.protocol.bedrock.data.shape.BoxDataPayload`)
* CameraAimAssistCategories (`org.cloudburstmc.protocol.bedrock.data.camera.aimassist.CameraAimAssistCategories`)
* CameraAimAssistCategoryDefinition (`org.cloudburstmc.protocol.bedrock.data.camera.aimassist.CameraAimAssistCategoryDefinition`)
* CameraAimAssistCategoryPriorities (`org.cloudburstmc.protocol.bedrock.data.camera.aimassist.CameraAimAssistCategoryPriorities`)
* CameraAimAssistCommandDefinition (`org.cloudburstmc.protocol.bedrock.data.camera.aimassist.CameraAimAssistCommandDefinition`)
* CameraAimAssistItemSettings (`org.cloudburstmc.protocol.bedrock.data.camera.aimassist.CameraAimAssistItemSettings`)
* CameraAimAssistPresetExclusionDefinition (`org.cloudburstmc.protocol.bedrock.data.camera.aimassist.CameraAimAssistPresetExclusionDefinition`)
* CameraAttachToEntityInstruction (`org.cloudburstmc.protocol.bedrock.data.camera.CameraAttachToEntityInstruction`)
* CameraInstruction (`org.cloudburstmc.protocol.bedrock.data.camera.CameraInstruction`)
* CameraSplineControlPoint (`org.cloudburstmc.protocol.bedrock.data.camera.CameraSplineControlPoint`)
* CameraSplineDefinition (`org.cloudburstmc.protocol.bedrock.data.camera.CameraSplineDefinition`)
* CameraSplineInstruction (`org.cloudburstmc.protocol.bedrock.data.camera.CameraSplineInstruction`)
* CameraSplineProgressKeyFrame (`org.cloudburstmc.protocol.bedrock.data.camera.CameraSplineProgressKeyFrame`)
* CameraSplineRotationKeyFrame (`org.cloudburstmc.protocol.bedrock.data.camera.CameraSplineRotationKeyFrame`)
* CauldronUsed (`org.cloudburstmc.protocol.bedrock.data.event.CauldronUsed`)
* ChangeEntityScore (`org.cloudburstmc.protocol.bedrock.data.scoreboard.ChangeEntityScore`)
* ChangeFakePlayerScore (`org.cloudburstmc.protocol.bedrock.data.scoreboard.ChangeFakePlayerScore`)
* ChangePlayerScore (`org.cloudburstmc.protocol.bedrock.data.scoreboard.ChangePlayerScore`)
* ChunkPos (`org.cloudburstmc.protocol.bedrock.data.chunk.ChunkPos`)
* ClearOverride (`org.cloudburstmc.protocol.bedrock.data.attribute.ClearOverride`)
* ClientPixelsProxy (`org.cloudburstmc.protocol.bedrock.data.map.ClientPixelsProxy`)
* ClientStoreEntryPointConfig (`org.cloudburstmc.protocol.bedrock.data.connection.ClientStoreEntryPointConfig`)
* CodeBuilderRuntimeAction (`org.cloudburstmc.protocol.bedrock.data.event.CodeBuilderRuntimeAction`)
* CodeBuilderScoreboard (`org.cloudburstmc.protocol.bedrock.data.event.CodeBuilderScoreboard`)
* Color255RGBA (`org.cloudburstmc.protocol.bedrock.data.attribute.Color255RGBA`)
* ColorAttributeData (`org.cloudburstmc.protocol.bedrock.data.attribute.ColorAttributeData`)
* ColorOption (`org.cloudburstmc.protocol.bedrock.data.camera.ColorOption`)
* CommandOutput (`org.cloudburstmc.protocol.bedrock.data.command.CommandOutput`)
* ComposterUsed (`org.cloudburstmc.protocol.bedrock.data.event.ComposterUsed`)
* ConeDataPayload (`org.cloudburstmc.protocol.bedrock.data.shape.ConeDataPayload`)
* ContainerMixDataEntry (`org.cloudburstmc.protocol.bedrock.data.recipe.ContainerMixDataEntry`)
* ContentIdentity (`org.cloudburstmc.protocol.bedrock.data.resourcepack.ContentIdentity`)
* CoordinatesLocation (`org.cloudburstmc.protocol.bedrock.data.location.CoordinatesLocation`)
* CreativeGroupInfoPayload (`org.cloudburstmc.protocol.bedrock.data.item.creative.CreativeGroupInfoPayload`)
* CreativeItemEntryPayload (`org.cloudburstmc.protocol.bedrock.data.item.creative.CreativeItemEntryPayload`)
* CreativeItemNetId (`org.cloudburstmc.protocol.bedrock.data.item.creative.CreativeItemNetId`)
* CylinderDataPayload (`org.cloudburstmc.protocol.bedrock.data.shape.CylinderDataPayload`)
* DataStoreChange (`org.cloudburstmc.protocol.bedrock.data.datastore.DataStoreChange`)
* DataStoreRemoval (`org.cloudburstmc.protocol.bedrock.data.datastore.DataStoreRemoval`)
* DeathCauseMessageType (`org.cloudburstmc.protocol.bedrock.data.text.DeathCauseMessageType`)
* DebugMarkerData (`org.cloudburstmc.protocol.bedrock.data.debug.DebugMarkerData`)
* DeletePage (`org.cloudburstmc.protocol.bedrock.data.book.DeletePage`)
* DisconnectPacketMessages (`org.cloudburstmc.protocol.bedrock.data.connection.DisconnectPacketMessages`)
* DynamicValue (`org.cloudburstmc.protocol.bedrock.data.datastore.DynamicValue`)
* EaseOption (`org.cloudburstmc.protocol.bedrock.data.camera.EaseOption`)
* EduSharedUriResource (`org.cloudburstmc.protocol.bedrock.data.education.EduSharedUriResource`)
* EducationLevelSettings (`org.cloudburstmc.protocol.bedrock.data.education.EducationLevelSettings`)
* EducationLocalLevelSettings (`org.cloudburstmc.protocol.bedrock.data.education.EducationLocalLevelSettings`)
* EllipsoidDataPayload (`org.cloudburstmc.protocol.bedrock.data.shape.EllipsoidDataPayload`)
* Empty (`org.cloudburstmc.protocol.bedrock.data.event.Empty`)
* EmptyDescriptor (`org.cloudburstmc.protocol.bedrock.data.inventory.descriptor.EmptyDescriptor`)
* EnchantmentInstance (`org.cloudburstmc.protocol.bedrock.data.item.EnchantmentInstance`)
* EntityCommandTarget (`org.cloudburstmc.protocol.bedrock.data.command.EntityCommandTarget`)
* EntityDiagnosticTimingInfo (`org.cloudburstmc.protocol.bedrock.data.diagnostics.EntityDiagnosticTimingInfo`)
* EntityNetId (`org.cloudburstmc.protocol.bedrock.data.actor.EntityNetId`)
* EntityOffsetOption (`org.cloudburstmc.protocol.bedrock.data.camera.EntityOffsetOption`)
* EnvironmentAttributeData (`org.cloudburstmc.protocol.bedrock.data.attribute.EnvironmentAttributeData`)
* ExperimentToggle (`org.cloudburstmc.protocol.bedrock.data.world.ExperimentToggle`)
* Experiments (`org.cloudburstmc.protocol.bedrock.data.world.Experiments`)
* ExternalLinkSettings (`org.cloudburstmc.protocol.bedrock.data.education.ExternalLinkSettings`)
* FacingOption (`org.cloudburstmc.protocol.bedrock.data.camera.FacingOption`)
* Fade (`org.cloudburstmc.protocol.bedrock.data.sound.Fade`)
* FeatureRegistryFeatureBinaryJsonFormat (`org.cloudburstmc.protocol.bedrock.data.world.FeatureRegistryFeatureBinaryJsonFormat`)
* Finalize (`org.cloudburstmc.protocol.bedrock.data.book.Finalize`)
* FishBucketed (`org.cloudburstmc.protocol.bedrock.data.event.FishBucketed`)
* FloatAttributeData (`org.cloudburstmc.protocol.bedrock.data.attribute.FloatAttributeData`)
* FloatOverride (`org.cloudburstmc.protocol.bedrock.data.attribute.FloatOverride`)
* FloatRange (`org.cloudburstmc.protocol.bedrock.data.biome.FloatRange`)
* FurnaceOptions (`org.cloudburstmc.protocol.bedrock.data.inventory.FurnaceOptions`)
* FurnaceRecipePayload (`org.cloudburstmc.protocol.bedrock.data.recipe.FurnaceRecipePayload`)
* GameRule (`org.cloudburstmc.protocol.bedrock.data.world.GameRule`)
* GameRulesChangedPacketData (`org.cloudburstmc.protocol.bedrock.data.world.GameRulesChangedPacketData`)
* GatheringsConfig (`org.cloudburstmc.protocol.bedrock.data.connection.GatheringsConfig`)
* HiddenLocation (`org.cloudburstmc.protocol.bedrock.data.location.HiddenLocation`)
* InitializeRegistryData (`org.cloudburstmc.protocol.bedrock.data.clock.InitializeRegistryData`)
* IntOverride (`org.cloudburstmc.protocol.bedrock.data.attribute.IntOverride`)
* Interaction (`org.cloudburstmc.protocol.bedrock.data.event.Interaction`)
* InventoryAction (`org.cloudburstmc.protocol.bedrock.data.inventory.InventoryAction`)
* InventoryMismatchData (`org.cloudburstmc.protocol.bedrock.data.inventory.InventoryMismatchData`)
* InventoryOptions (`org.cloudburstmc.protocol.bedrock.data.inventory.InventoryOptions`)
* InventorySource (`org.cloudburstmc.protocol.bedrock.data.inventory.InventorySource`)
* InventoryTransaction (`org.cloudburstmc.protocol.bedrock.data.inventory.transaction.InventoryTransaction`)
* InventoryTransactionData (`org.cloudburstmc.protocol.bedrock.data.inventory.transaction.InventoryTransactionData`)
* ItemEnchantOption (`org.cloudburstmc.protocol.bedrock.data.item.ItemEnchantOption`)
* ItemEnchants (`org.cloudburstmc.protocol.bedrock.data.item.ItemEnchants`)
* ItemReleaseInventoryTransaction (`org.cloudburstmc.protocol.bedrock.data.inventory.transaction.ItemReleaseInventoryTransaction`)
* ItemStackLegacyRequestId (`org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.ItemStackLegacyRequestId`)
* ItemStackNetId (`org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.ItemStackNetId`)
* ItemStackRequestBeaconPaymentAction (`org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.action.ItemStackRequestBeaconPaymentAction`)
* ItemStackRequestConsumeAction (`org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.action.ItemStackRequestConsumeAction`)
* ItemStackRequestCraftCreativeAction (`org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.action.ItemStackRequestCraftCreativeAction`)
* ItemStackRequestCraftLoomAction (`org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.action.ItemStackRequestCraftLoomAction`)
* ItemStackRequestCraftNonImplementedDeprecatedAction (`org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.action.ItemStackRequestCraftNonImplementedDeprecatedAction`)
* ItemStackRequestCraftRecipeAction (`org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.action.ItemStackRequestCraftRecipeAction`)
* ItemStackRequestCraftRecipeAutoAction (`org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.action.ItemStackRequestCraftRecipeAutoAction`)
* ItemStackRequestCraftRecipeOptionalAction (`org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.action.ItemStackRequestCraftRecipeOptionalAction`)
* ItemStackRequestCraftRepairAndDisenchantAction (`org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.action.ItemStackRequestCraftRepairAndDisenchantAction`)
* ItemStackRequestCraftResultsDeprecatedAction (`org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.action.ItemStackRequestCraftResultsDeprecatedAction`)
* ItemStackRequestCreateAction (`org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.action.ItemStackRequestCreateAction`)
* ItemStackRequestDestroyAction (`org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.action.ItemStackRequestDestroyAction`)
* ItemStackRequestDropAction (`org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.action.ItemStackRequestDropAction`)
* ItemStackRequestId (`org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.ItemStackRequestId`)
* ItemStackRequestLabTableCombineAction (`org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.action.ItemStackRequestLabTableCombineAction`)
* ItemStackRequestMineBlockAction (`org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.action.ItemStackRequestMineBlockAction`)
* ItemStackRequestNetworkItemInstanceDescriptor (`org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.ItemStackRequestNetworkItemInstanceDescriptor`)
* ItemStackRequestPlaceAction (`org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.action.ItemStackRequestPlaceAction`)
* ItemStackRequestSlotInfo (`org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.ItemStackRequestSlotInfo`)
* ItemStackRequestSwapAction (`org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.action.ItemStackRequestSwapAction`)
* ItemStackRequestTakeAction (`org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.action.ItemStackRequestTakeAction`)
* ItemStackResponseContainerInfo (`org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.response.ItemStackResponseContainerInfo`)
* ItemStackResponseInfo (`org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.response.ItemStackResponseInfo`)
* ItemStackResponseSlotInfo (`org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.response.ItemStackResponseSlotInfo`)
* ItemUseInventoryTransaction (`org.cloudburstmc.protocol.bedrock.data.inventory.transaction.ItemUseInventoryTransaction`)
* ItemUseOnActorInventoryTransaction (`org.cloudburstmc.protocol.bedrock.data.inventory.transaction.ItemUseOnActorInventoryTransaction`)
* ItemUsed (`org.cloudburstmc.protocol.bedrock.data.event.ItemUsed`)
* LegacySetSlot (`org.cloudburstmc.protocol.bedrock.data.inventory.LegacySetSlot`)
* LevelEventType (`org.cloudburstmc.protocol.bedrock.data.world.event.LevelEventType`)
* LevelSettings (`org.cloudburstmc.protocol.bedrock.data.world.LevelSettings`)
* LineDataPayload (`org.cloudburstmc.protocol.bedrock.data.shape.LineDataPayload`)
* LocatorBarWaypointPayload (`org.cloudburstmc.protocol.bedrock.data.waypoint.LocatorBarWaypointPayload`)
* MapDecoration (`org.cloudburstmc.protocol.bedrock.data.map.MapDecoration`)
* MapItemTrackedActorUniqueId (`org.cloudburstmc.protocol.bedrock.data.map.MapItemTrackedActorUniqueId`)
* MaterialReducerDataEntry (`org.cloudburstmc.protocol.bedrock.data.recipe.MaterialReducerDataEntry`)
* MaterialReducerEntryOutput (`org.cloudburstmc.protocol.bedrock.data.recipe.MaterialReducerEntryOutput`)
* MemoryCategoryCounter (`org.cloudburstmc.protocol.bedrock.data.diagnostics.MemoryCategoryCounter`)
* MessageAndParams (`org.cloudburstmc.protocol.bedrock.data.text.MessageAndParams`)
* MessageOnly (`org.cloudburstmc.protocol.bedrock.data.text.MessageOnly`)
* MissingBlobData (`org.cloudburstmc.protocol.bedrock.data.chunk.MissingBlobData`)
* MobBorn (`org.cloudburstmc.protocol.bedrock.data.event.MobBorn`)
* MoveActorAbsoluteData (`org.cloudburstmc.protocol.bedrock.data.actor.MoveActorAbsoluteData`)
* MoveActorDeltaData (`org.cloudburstmc.protocol.bedrock.data.actor.MoveActorDeltaData`)
* MovePlayerTeleportData (`org.cloudburstmc.protocol.bedrock.data.player.input.MovePlayerTeleportData`)
* MovementAnomaly (`org.cloudburstmc.protocol.bedrock.data.event.MovementAnomaly`)
* MovementAttributesComponent (`org.cloudburstmc.protocol.bedrock.data.prediction.MovementAttributesComponent`)
* MovementCorrected (`org.cloudburstmc.protocol.bedrock.data.event.MovementCorrected`)
* MultiRecipePayload (`org.cloudburstmc.protocol.bedrock.data.recipe.MultiRecipePayload`)
* NameDescriptor (`org.cloudburstmc.protocol.bedrock.data.inventory.descriptor.NameDescriptor`)
* NetworkPermissions (`org.cloudburstmc.protocol.bedrock.data.ability.NetworkPermissions`)
* NoiseAlignment (`org.cloudburstmc.protocol.bedrock.data.structure.NoiseAlignment`)
* NoiseBlockSpecifier (`org.cloudburstmc.protocol.bedrock.data.biome.NoiseBlockSpecifier`)
* NoiseDescriptor (`org.cloudburstmc.protocol.bedrock.data.structure.NoiseDescriptor`)
* NoiseTransitionAttributeData (`org.cloudburstmc.protocol.bedrock.data.attribute.NoiseTransitionAttributeData`)
* NoiseTransitionSettingsData (`org.cloudburstmc.protocol.bedrock.data.attribute.NoiseTransitionSettingsData`)
* NormalTransactionData (`org.cloudburstmc.protocol.bedrock.data.inventory.transaction.NormalTransactionData`)
* NullType (`org.cloudburstmc.protocol.bedrock.data.datastore.NullType`)
* POICauldronUsed (`org.cloudburstmc.protocol.bedrock.data.event.POICauldronUsed`)
* PackIdVersion (`org.cloudburstmc.protocol.bedrock.data.resourcepack.PackIdVersion`)
* PackInfoData (`org.cloudburstmc.protocol.bedrock.data.resourcepack.PackInfoData`)
* PackInstanceId (`org.cloudburstmc.protocol.bedrock.data.resourcepack.PackInstanceId`)
* PackedItemUseLegacyInventoryTransaction (`org.cloudburstmc.protocol.bedrock.data.inventory.transaction.PackedItemUseLegacyInventoryTransaction`)
* PatternRemoved (`org.cloudburstmc.protocol.bedrock.data.event.PatternRemoved`)
* Pause (`org.cloudburstmc.protocol.bedrock.data.sound.Pause`)
* PetDied (`org.cloudburstmc.protocol.bedrock.data.event.PetDied`)
* PiglinBarter (`org.cloudburstmc.protocol.bedrock.data.event.PiglinBarter`)
* PlayerBlockActionData (`org.cloudburstmc.protocol.bedrock.data.player.PlayerBlockActionData`)
* PlayerInputTick (`org.cloudburstmc.protocol.bedrock.data.player.PlayerInputTick`)
* PlayerListAddEntry (`org.cloudburstmc.protocol.bedrock.data.player.list.PlayerListAddEntry`)
* PlayerListRemoveEntry (`org.cloudburstmc.protocol.bedrock.data.player.list.PlayerListRemoveEntry`)
* PlayerPartyInfo (`org.cloudburstmc.protocol.bedrock.data.player.PlayerPartyInfo`)
* PlayerScoreboardId (`org.cloudburstmc.protocol.bedrock.data.scoreboard.PlayerScoreboardId`)
* PlayerWaxedOrUnwaxedCopper (`org.cloudburstmc.protocol.bedrock.data.event.PlayerWaxedOrUnwaxedCopper`)
* PortalCreated (`org.cloudburstmc.protocol.bedrock.data.event.PortalCreated`)
* PortalUsed (`org.cloudburstmc.protocol.bedrock.data.event.PortalUsed`)
* PosOption (`org.cloudburstmc.protocol.bedrock.data.camera.PosOption`)
* PositionTrackingId (`org.cloudburstmc.protocol.bedrock.data.positiontracking.PositionTrackingId`)
* PotionMixDataEntry (`org.cloudburstmc.protocol.bedrock.data.recipe.PotionMixDataEntry`)
* PresenceConfig (`org.cloudburstmc.protocol.bedrock.data.connection.PresenceConfig`)
* PrimitiveShapeDataPayload (`org.cloudburstmc.protocol.bedrock.data.shape.PrimitiveShapeDataPayload`)
* PropertySyncData (`org.cloudburstmc.protocol.bedrock.data.actor.PropertySyncData`)
* PropertySyncFloatEntry (`org.cloudburstmc.protocol.bedrock.data.actor.PropertySyncFloatEntry`)
* PropertySyncIntEntry (`org.cloudburstmc.protocol.bedrock.data.actor.PropertySyncIntEntry`)
* PyramidDataPayload (`org.cloudburstmc.protocol.bedrock.data.shape.PyramidDataPayload`)
* RaidUpdate (`org.cloudburstmc.protocol.bedrock.data.event.RaidUpdate`)
* RecipeIngredient (`org.cloudburstmc.protocol.bedrock.data.recipe.RecipeIngredient`)
* RecipeNetId (`org.cloudburstmc.protocol.bedrock.data.recipe.RecipeNetId`)
* RecipeUnlockingRequirement (`org.cloudburstmc.protocol.bedrock.data.recipe.RecipeUnlockingRequirement`)
* RedactableString (`org.cloudburstmc.protocol.bedrock.data.misc.RedactableString`)
* RemoveEnvironmentAttributesData (`org.cloudburstmc.protocol.bedrock.data.attribute.RemoveEnvironmentAttributesData`)
* RemoveOverride (`org.cloudburstmc.protocol.bedrock.data.attribute.RemoveOverride`)
* RemoveScore (`org.cloudburstmc.protocol.bedrock.data.scoreboard.RemoveScore`)
* RemoveTimeMarkerData (`org.cloudburstmc.protocol.bedrock.data.clock.RemoveTimeMarkerData`)
* ReplacePage (`org.cloudburstmc.protocol.bedrock.data.book.ReplacePage`)
* ResourcePackClientResponseCancel (`org.cloudburstmc.protocol.bedrock.data.resourcepack.ResourcePackClientResponseCancel`)
* ResourcePackClientResponseDownloading (`org.cloudburstmc.protocol.bedrock.data.resourcepack.ResourcePackClientResponseDownloading`)
* ResourcePackClientResponseDownloadingFinished (`org.cloudburstmc.protocol.bedrock.data.resourcepack.ResourcePackClientResponseDownloadingFinished`)
* ResourcePackClientResponseResourcePackStackFinished (`org.cloudburstmc.protocol.bedrock.data.resourcepack.ResourcePackClientResponseResourcePackStackFinished`)
* Resume (`org.cloudburstmc.protocol.bedrock.data.sound.Resume`)
* RotOption (`org.cloudburstmc.protocol.bedrock.data.camera.RotOption`)
* ScoreboardId (`org.cloudburstmc.protocol.bedrock.data.scoreboard.ScoreboardId`)
* ScoreboardIdentityPacketInfo (`org.cloudburstmc.protocol.bedrock.data.scoreboard.ScoreboardIdentityPacketInfo`)
* SeekTo (`org.cloudburstmc.protocol.bedrock.data.sound.SeekTo`)
* SerializableCells (`org.cloudburstmc.protocol.bedrock.data.block.SerializableCells`)
* SerializableVoxelShape (`org.cloudburstmc.protocol.bedrock.data.block.SerializableVoxelShape`)
* SerializedAbilitiesData (`org.cloudburstmc.protocol.bedrock.data.ability.SerializedAbilitiesData`)
* SerializedAbilitiesDataSerializedLayer (`org.cloudburstmc.protocol.bedrock.data.ability.SerializedAbilitiesDataSerializedLayer`)
* SerializedNoiseBlockSpecifier (`org.cloudburstmc.protocol.bedrock.data.structure.SerializedNoiseBlockSpecifier`)
* SerializedPersonaPieceHandle (`org.cloudburstmc.protocol.bedrock.data.skin.SerializedPersonaPieceHandle`)
* ServerBlockProperty (`org.cloudburstmc.protocol.bedrock.data.world.ServerBlockProperty`)
* ServerConfig (`org.cloudburstmc.protocol.bedrock.data.connection.ServerConfig`)
* ServerSoundHandle (`org.cloudburstmc.protocol.bedrock.data.sound.ServerSoundHandle`)
* ServerTelemetryData (`org.cloudburstmc.protocol.bedrock.data.diagnostics.ServerTelemetryData`)
* ServerWaypointPayload (`org.cloudburstmc.protocol.bedrock.data.waypoint.ServerWaypointPayload`)
* SetPitch (`org.cloudburstmc.protocol.bedrock.data.sound.SetPitch`)
* SetVolume (`org.cloudburstmc.protocol.bedrock.data.sound.SetVolume`)
* ShapedRecipePayload (`org.cloudburstmc.protocol.bedrock.data.recipe.ShapedRecipePayload`)
* ShapelessRecipePayload (`org.cloudburstmc.protocol.bedrock.data.recipe.ShapelessRecipePayload`)
* SkinImage (`org.cloudburstmc.protocol.bedrock.data.skin.SkinImage`)
* SlashCommand (`org.cloudburstmc.protocol.bedrock.data.event.SlashCommand`)
* SmithingTransformRecipePayload (`org.cloudburstmc.protocol.bedrock.data.recipe.SmithingTransformRecipePayload`)
* SmithingTrimRecipePayload (`org.cloudburstmc.protocol.bedrock.data.recipe.SmithingTrimRecipePayload`)
* SpawnSettings (`org.cloudburstmc.protocol.bedrock.data.world.SpawnSettings`)
* SphereDataPayload (`org.cloudburstmc.protocol.bedrock.data.shape.SphereDataPayload`)
* SplineProgressOption (`org.cloudburstmc.protocol.bedrock.data.camera.SplineProgressOption`)
* SplineRotationOption (`org.cloudburstmc.protocol.bedrock.data.camera.SplineRotationOption`)
* StartVideoCapture (`org.cloudburstmc.protocol.bedrock.data.video.StartVideoCapture`)
* Stop (`org.cloudburstmc.protocol.bedrock.data.sound.Stop`)
* StopVideoCapture (`org.cloudburstmc.protocol.bedrock.data.video.StopVideoCapture`)
* SubChunkHeightmapData (`org.cloudburstmc.protocol.bedrock.data.chunk.SubChunkHeightmapData`)
* SubChunkPacketData (`org.cloudburstmc.protocol.bedrock.data.chunk.SubChunkPacketData`)
* SwapPages (`org.cloudburstmc.protocol.bedrock.data.book.SwapPages`)
* SyncStateData (`org.cloudburstmc.protocol.bedrock.data.clock.SyncStateData`)
* SyncWorldClockStateData (`org.cloudburstmc.protocol.bedrock.data.clock.SyncWorldClockStateData`)
* SyncWorldClocksPayload (`org.cloudburstmc.protocol.bedrock.data.clock.SyncWorldClocksPayload`)
* SyncedAttribute (`org.cloudburstmc.protocol.bedrock.data.actor.attribute.SyncedAttribute`)
* SyncedPlayerMovementSettings (`org.cloudburstmc.protocol.bedrock.data.player.input.SyncedPlayerMovementSettings`)
* SystemCategory (`org.cloudburstmc.protocol.bedrock.data.diagnostics.SystemCategory`)
* SystemDiagnosticTimingInfo (`org.cloudburstmc.protocol.bedrock.data.diagnostics.SystemDiagnosticTimingInfo`)
* TargetBlockHit (`org.cloudburstmc.protocol.bedrock.data.event.TargetBlockHit`)
* TextDataPayload (`org.cloudburstmc.protocol.bedrock.data.shape.TextDataPayload`)
* TimeMarkerData (`org.cloudburstmc.protocol.bedrock.data.clock.TimeMarkerData`)
* TimeOption (`org.cloudburstmc.protocol.bedrock.data.camera.TimeOption`)
* TintMapColor (`org.cloudburstmc.protocol.bedrock.data.skin.TintMapColor`)
* TransitionAttributeData (`org.cloudburstmc.protocol.bedrock.data.attribute.TransitionAttributeData`)
* TransitionSettingsData (`org.cloudburstmc.protocol.bedrock.data.attribute.TransitionSettingsData`)
* TrimMaterial (`org.cloudburstmc.protocol.bedrock.data.recipe.TrimMaterial`)
* TrimPattern (`org.cloudburstmc.protocol.bedrock.data.recipe.TrimPattern`)
* UpdateAttributeLayerSettingsData (`org.cloudburstmc.protocol.bedrock.data.attribute.UpdateAttributeLayerSettingsData`)
* UpdateAttributeLayersData (`org.cloudburstmc.protocol.bedrock.data.attribute.UpdateAttributeLayersData`)
* UpdateEnvironmentAttributesData (`org.cloudburstmc.protocol.bedrock.data.attribute.UpdateEnvironmentAttributesData`)
* UpdateSubChunkBlocksChangedInfo (`org.cloudburstmc.protocol.bedrock.data.chunk.UpdateSubChunkBlocksChangedInfo`)
* UpdateSubChunkNetworkBlockInfo (`org.cloudburstmc.protocol.bedrock.data.chunk.UpdateSubChunkNetworkBlockInfo`)
* ViewOffsetOption (`org.cloudburstmc.protocol.bedrock.data.camera.ViewOffsetOption`)
* VoxelShapesRegistryHandle (`org.cloudburstmc.protocol.bedrock.data.block.VoxelShapesRegistryHandle`)
* WaypointGroupWaypointHandle (`org.cloudburstmc.protocol.bedrock.data.waypoint.WaypointGroupWaypointHandle`)
* WebSocketPacketData (`org.cloudburstmc.protocol.bedrock.data.connection.WebSocketPacketData`)
* WhiskerScopeDataSummary (`org.cloudburstmc.protocol.bedrock.data.diagnostics.WhiskerScopeDataSummary`)
* WorldClockData (`org.cloudburstmc.protocol.bedrock.data.clock.WorldClockData`)
* WorldPosition (`org.cloudburstmc.protocol.bedrock.data.world.WorldPosition`)

## Removed Types

* AchievementAwardedEventData (`org.cloudburstmc.protocol.bedrock.data.event.AchievementAwardedEventData`)
* AgentCommandEventData (`org.cloudburstmc.protocol.bedrock.data.event.AgentCommandEventData`)
* AgentCreatedEventData (`org.cloudburstmc.protocol.bedrock.data.event.AgentCreatedEventData`)
* AnimationData (`org.cloudburstmc.protocol.bedrock.data.skin.AnimationData`)
* AttributeData (`org.cloudburstmc.protocol.bedrock.data.AttributeData`)
* AttributeModifierData (`org.cloudburstmc.protocol.bedrock.data.attribute.AttributeModifierData`)
* AuthPayload (`org.cloudburstmc.protocol.bedrock.data.auth.AuthPayload`)
* AutoCraftRecipeAction (`org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.action.AutoCraftRecipeAction`)
* BeaconPaymentAction (`org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.action.BeaconPaymentAction`)
* BellUsedEventData (`org.cloudburstmc.protocol.bedrock.data.event.BellUsedEventData`)
* BlockPropertyData (`org.cloudburstmc.protocol.bedrock.data.BlockPropertyData`)
* BossKilledEventData (`org.cloudburstmc.protocol.bedrock.data.event.BossKilledEventData`)
* CameraAimAssistCategories (`org.cloudburstmc.protocol.bedrock.data.camera.CameraAimAssistCategories`)
* CameraAimAssistCategory (`org.cloudburstmc.protocol.bedrock.data.camera.CameraAimAssistCategory`)
* CameraAimAssistItemSettings (`org.cloudburstmc.protocol.bedrock.data.camera.CameraAimAssistItemSettings`)
* CameraAimAssistPreset (`org.cloudburstmc.protocol.bedrock.data.camera.CameraAimAssistPreset`)
* CameraAimAssistPriority (`org.cloudburstmc.protocol.bedrock.data.camera.CameraAimAssistPriority`)
* CarefulRestorationEventData (`org.cloudburstmc.protocol.bedrock.data.event.CarefulRestorationEventData`)
* CauldronInteractEventData (`org.cloudburstmc.protocol.bedrock.data.event.CauldronInteractEventData`)
* CauldronUsedEventData (`org.cloudburstmc.protocol.bedrock.data.event.CauldronUsedEventData`)
* CertificateChainPayload (`org.cloudburstmc.protocol.bedrock.data.auth.CertificateChainPayload`)
* CodeBuilderActionEventData (`org.cloudburstmc.protocol.bedrock.data.event.CodeBuilderActionEventData`)
* CodeBuilderScoreboardEventData (`org.cloudburstmc.protocol.bedrock.data.event.CodeBuilderScoreboardEventData`)
* CommandSymbolData (`org.cloudburstmc.protocol.bedrock.data.command.CommandSymbolData`)
* ComposterInteractEventData (`org.cloudburstmc.protocol.bedrock.data.event.ComposterInteractEventData`)
* ConsumeAction (`org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.action.ConsumeAction`)
* ContainerMixData (`org.cloudburstmc.protocol.bedrock.data.inventory.crafting.ContainerMixData`)
* CopperWaxedOrUnwaxedEventData (`org.cloudburstmc.protocol.bedrock.data.event.CopperWaxedOrUnwaxedEventData`)
* CraftCreativeAction (`org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.action.CraftCreativeAction`)
* CraftGrindstoneAction (`org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.action.CraftGrindstoneAction`)
* CraftLoomAction (`org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.action.CraftLoomAction`)
* CraftNonImplementedAction (`org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.action.CraftNonImplementedAction`)
* CraftRecipeAction (`org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.action.CraftRecipeAction`)
* CraftRecipeOptionalAction (`org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.action.CraftRecipeOptionalAction`)
* CraftResultsDeprecatedAction (`org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.action.CraftResultsDeprecatedAction`)
* CraftingRecipeData (`org.cloudburstmc.protocol.bedrock.data.inventory.crafting.recipe.CraftingRecipeData`)
* CreateAction (`org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.action.CreateAction`)
* CreativeItemData (`org.cloudburstmc.protocol.bedrock.data.inventory.CreativeItemData`)
* CreativeItemGroup (`org.cloudburstmc.protocol.bedrock.data.inventory.CreativeItemGroup`)
* DestroyAction (`org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.action.DestroyAction`)
* DropAction (`org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.action.DropAction`)
* EduSharedUriResource (`org.cloudburstmc.protocol.bedrock.data.EduSharedUriResource`)
* EnchantData (`org.cloudburstmc.protocol.bedrock.data.inventory.EnchantData`)
* EnchantOptionData (`org.cloudburstmc.protocol.bedrock.data.inventory.EnchantOptionData`)
* EntityDataTypes (`org.cloudburstmc.protocol.bedrock.data.entity.EntityDataTypes`)
* EntityDefinitionTriggerEventData (`org.cloudburstmc.protocol.bedrock.data.event.EntityDefinitionTriggerEventData`)
* EntityInteractEventData (`org.cloudburstmc.protocol.bedrock.data.event.EntityInteractEventData`)
* EntityProperties (`org.cloudburstmc.protocol.bedrock.data.entity.EntityProperties`)
* EventData (`org.cloudburstmc.protocol.bedrock.data.event.EventData`)
* ExperimentData (`org.cloudburstmc.protocol.bedrock.data.ExperimentData`)
* ExtractHoneyEventData (`org.cloudburstmc.protocol.bedrock.data.event.ExtractHoneyEventData`)
* FeatureDefinition (`org.cloudburstmc.protocol.bedrock.data.definitions.FeatureDefinition`)
* FishBucketedEventData (`org.cloudburstmc.protocol.bedrock.data.event.FishBucketedEventData`)
* FurnaceRecipeData (`org.cloudburstmc.protocol.bedrock.data.inventory.crafting.recipe.FurnaceRecipeData`)
* GameRuleData (`org.cloudburstmc.protocol.bedrock.data.GameRuleData`)
* IdentifiableRecipeData (`org.cloudburstmc.protocol.bedrock.data.inventory.crafting.recipe.IdentifiableRecipeData`)
* ImageData (`org.cloudburstmc.protocol.bedrock.data.skin.ImageData`)
* InventoryActionData (`org.cloudburstmc.protocol.bedrock.data.inventory.transaction.InventoryActionData`)
* ItemDescriptorWithCount (`org.cloudburstmc.protocol.bedrock.data.inventory.descriptor.ItemDescriptorWithCount`)
* ItemStackRequestAction (`org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.action.ItemStackRequestAction`)
* ItemStackRequestSlotData (`org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.ItemStackRequestSlotData`)
* ItemStackResponse (`org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.response.ItemStackResponse`)
* ItemStackResponseContainer (`org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.response.ItemStackResponseContainer`)
* ItemStackResponseSlot (`org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.response.ItemStackResponseSlot`)
* ItemUsedEventData (`org.cloudburstmc.protocol.bedrock.data.event.ItemUsedEventData`)
* LabTableCombineAction (`org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.action.LabTableCombineAction`)
* LegacySetItemSlotData (`org.cloudburstmc.protocol.bedrock.data.inventory.transaction.LegacySetItemSlotData`)
* LevelEventType (`org.cloudburstmc.protocol.bedrock.data.LevelEventType`)
* MapDecoration (`org.cloudburstmc.protocol.bedrock.data.MapDecoration`)
* MapPixel (`org.cloudburstmc.protocol.bedrock.data.map.MapPixel`)
* MaterialReducer (`org.cloudburstmc.protocol.bedrock.data.inventory.crafting.MaterialReducer`)
* MineBlockAction (`org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.action.MineBlockAction`)
* MobBornEventData (`org.cloudburstmc.protocol.bedrock.data.event.MobBornEventData`)
* MobKilledEventData (`org.cloudburstmc.protocol.bedrock.data.event.MobKilledEventData`)
* MovementAnomalyEventData (`org.cloudburstmc.protocol.bedrock.data.event.MovementAnomalyEventData`)
* MovementCorrectedEventData (`org.cloudburstmc.protocol.bedrock.data.event.MovementCorrectedEventData`)
* MultiRecipeData (`org.cloudburstmc.protocol.bedrock.data.inventory.crafting.recipe.MultiRecipeData`)
* NetworkPermissions (`org.cloudburstmc.protocol.bedrock.data.NetworkPermissions`)
* NetworkRecipeData (`org.cloudburstmc.protocol.bedrock.data.inventory.crafting.recipe.NetworkRecipeData`)
* PatternRemovedEventData (`org.cloudburstmc.protocol.bedrock.data.event.PatternRemovedEventData`)
* PersonaPieceData (`org.cloudburstmc.protocol.bedrock.data.skin.PersonaPieceData`)
* PersonaPieceTintData (`org.cloudburstmc.protocol.bedrock.data.skin.PersonaPieceTintData`)
* PetDiedEventData (`org.cloudburstmc.protocol.bedrock.data.event.PetDiedEventData`)
* PiglinBarterEventData (`org.cloudburstmc.protocol.bedrock.data.event.PiglinBarterEventData`)
* PlaceAction (`org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.action.PlaceAction`)
* PlayerAbilityHolder (`org.cloudburstmc.protocol.bedrock.data.PlayerAbilityHolder`)
* PlayerBlockActionData (`org.cloudburstmc.protocol.bedrock.data.PlayerBlockActionData`)
* PlayerDiedEventData (`org.cloudburstmc.protocol.bedrock.data.event.PlayerDiedEventData`)
* PortalBuiltEventData (`org.cloudburstmc.protocol.bedrock.data.event.PortalBuiltEventData`)
* PortalUsedEventData (`org.cloudburstmc.protocol.bedrock.data.event.PortalUsedEventData`)
* PotionMixData (`org.cloudburstmc.protocol.bedrock.data.inventory.crafting.PotionMixData`)
* RaidUpdateEventData (`org.cloudburstmc.protocol.bedrock.data.event.RaidUpdateEventData`)
* RecipeData (`org.cloudburstmc.protocol.bedrock.data.inventory.crafting.recipe.RecipeData`)
* RecipeIngredient (`org.cloudburstmc.protocol.bedrock.data.inventory.crafting.RecipeIngredient`)
* RecipeItemStackRequestAction (`org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.action.RecipeItemStackRequestAction`)
* ShapedRecipeData (`org.cloudburstmc.protocol.bedrock.data.inventory.crafting.recipe.ShapedRecipeData`)
* ShapelessRecipeData (`org.cloudburstmc.protocol.bedrock.data.inventory.crafting.recipe.ShapelessRecipeData`)
* SimpleNamedDefinition (`org.cloudburstmc.protocol.bedrock.data.definitions.SimpleNamedDefinition`)
* SlashCommandExecutedEventData (`org.cloudburstmc.protocol.bedrock.data.event.SlashCommandExecutedEventData`)
* SmithingTransformRecipeData (`org.cloudburstmc.protocol.bedrock.data.inventory.crafting.recipe.SmithingTransformRecipeData`)
* SmithingTrimRecipeData (`org.cloudburstmc.protocol.bedrock.data.inventory.crafting.recipe.SmithingTrimRecipeData`)
* SneakCloseToSculkSensorEventData (`org.cloudburstmc.protocol.bedrock.data.event.SneakCloseToSculkSensorEventData`)
* StriderRiddenInLavaInOverworldEventData (`org.cloudburstmc.protocol.bedrock.data.event.StriderRiddenInLavaInOverworldEventData`)
* SubChunkData (`org.cloudburstmc.protocol.bedrock.data.SubChunkData`)
* SwapAction (`org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.action.SwapAction`)
* TaggedCraftingData (`org.cloudburstmc.protocol.bedrock.data.inventory.crafting.recipe.TaggedCraftingData`)
* TakeAction (`org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.action.TakeAction`)
* TargetBlockHitEventData (`org.cloudburstmc.protocol.bedrock.data.event.TargetBlockHitEventData`)
* TokenPayload (`org.cloudburstmc.protocol.bedrock.data.auth.TokenPayload`)
* TransferItemStackRequestAction (`org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.action.TransferItemStackRequestAction`)
* TrimMaterial (`org.cloudburstmc.protocol.bedrock.data.TrimMaterial`)
* TrimPattern (`org.cloudburstmc.protocol.bedrock.data.TrimPattern`)
* UnindexedBiomes (`org.cloudburstmc.protocol.bedrock.data.biome.UnindexedBiomes`)
* UniqueCraftingData (`org.cloudburstmc.protocol.bedrock.data.inventory.crafting.recipe.UniqueCraftingData`)

## Renamed Types

* `org.cloudburstmc.protocol.bedrock.data.camera.CameraAimAssistPresetDefinition` → `org.cloudburstmc.protocol.bedrock.data.camera.aimassist.CameraAimAssistPresetDefinition` _(78% similar)_
* `org.cloudburstmc.protocol.bedrock.data.entity.EntityDataType` → `org.cloudburstmc.protocol.bedrock.data.actor.ActorDataType` _(71% similar)_
* `org.cloudburstmc.protocol.bedrock.data.entity.FloatEntityProperty` → `org.cloudburstmc.protocol.bedrock.data.actor.FloatEntityProperty` _(69% similar)_
* `org.cloudburstmc.protocol.bedrock.data.entity.IntEntityProperty` → `org.cloudburstmc.protocol.bedrock.data.actor.IntEntityProperty` _(68% similar)_
* `org.cloudburstmc.protocol.bedrock.data.entity.EntityDataMap` → `org.cloudburstmc.protocol.bedrock.data.actor.ActorDataMap` _(57% similar)_
* `org.cloudburstmc.protocol.bedrock.data.camera.CameraPreset` → `org.cloudburstmc.protocol.bedrock.data.camera.CameraPresets` _(53% similar)_
* `org.cloudburstmc.protocol.bedrock.data.entity.EntityProperty` → `org.cloudburstmc.protocol.bedrock.data.actor.EntityProperty` _(50% similar)_

## Type Changes

Achievement:
* Added `achievementID` (AchievementIds)

AchievementAwardedEventData:
* Removed `achievementId` (int)

ActorDataBoundingBoxComponent:
* Added `actorDataBoundingBox` (Vector3f)

ActorDataFlagComponent:
* Added `actorFlagBitsetData` (Set<ActorFlags>)

ActorDataMap:
* Added `map` (Map<ActorDataType<?>, Object>)
* Removed `map` (Map<EntityDataType<?>, Object>)

ActorDataTypes:
* Added `BLOCK` (ActorDataType<BlockDefinition>)
* Added `DISPLAY_BLOCK_STATE` (ActorDataType<BlockDefinition>)
* Added `CARRY_BLOCK_STATE` (ActorDataType<BlockDefinition>)
* Added `USING_ITEM` (ActorDataType<Boolean>)
* Added `HAS_NPC` (ActorDataType<Boolean>)
* Added `SEAT_LOCK_RIDER_ROTATION` (ActorDataType<Boolean>)
* Added `SEAT_HAS_ROTATION` (ActorDataType<Boolean>)
* Added `SHULKER_ATTACHED` (ActorDataType<Boolean>)
* Added `COMMAND_BLOCK_ENABLED` (ActorDataType<Boolean>)
* Added `COMMAND_BLOCK_TRACK_OUTPUT` (ActorDataType<Boolean>)
* Added `COMMAND_BLOCK_EXECUTE_ON_FIRST_TICK` (ActorDataType<Boolean>)
* Added `CAN_RIDE_TARGET` (ActorDataType<Boolean>)
* Added `IS_BUOYANT` (ActorDataType<Boolean>)
* Added `PLAYER_HAS_DIED` (ActorDataType<Boolean>)
* Added `COLOR` (ActorDataType<Byte>)
* Added `EFFECT_AMBIENCE` (ActorDataType<Byte>)
* Added `JUMP_DURATION` (ActorDataType<Byte>)
* Added `WITHER_SKULL_DANGEROUS` (ActorDataType<Byte>)
* Added `CUSTOM_DISPLAY` (ActorDataType<Byte>)
* Added `HORSE_TYPE` (ActorDataType<Byte>)
* Added `CHARGE_AMOUNT` (ActorDataType<Byte>)
* Added `CLIENT_EVENT` (ActorDataType<Byte>)
* Added `PLAYER_FLAGS` (ActorDataType<Byte>)
* Added `AUX_POWER` (ActorDataType<Byte>)
* Added `CONTAINER_TYPE` (ActorDataType<Byte>)
* Added `CONTROLLING_RIDER_SEAT_INDEX` (ActorDataType<Byte>)
* Added `NAMETAG_ALWAYS_SHOW` (ActorDataType<Byte>)
* Added `COLOR_2` (ActorDataType<Byte>)
* Added `PUFFED_STATE` (ActorDataType<Byte>)
* Added `NAME` (ActorDataType<CharSequence>)
* Added `NAME_AUTHOR` (ActorDataType<CharSequence>)
* Added `SCORE` (ActorDataType<CharSequence>)
* Added `INTERACT_TEXT` (ActorDataType<CharSequence>)
* Added `FILTERED_NAME` (ActorDataType<CharSequence>)
* Added `FLAGS` (ActorDataType<EnumMap<ActorFlags, Boolean>>)
* Added `FLAGS_2` (ActorDataType<EnumMap<ActorFlags, Boolean>>)
* Added `ROW_TIME_LEFT` (ActorDataType<Float>)
* Added `ROW_TIME_RIGHT` (ActorDataType<Float>)
* Added `FIREBALL_POWER_X` (ActorDataType<Float>)
* Added `FIREBALL_POWER_Y` (ActorDataType<Float>)
* Added `FIREBALL_POWER_Z` (ActorDataType<Float>)
* Added `FISH_X` (ActorDataType<Float>)
* Added `FISH_Z` (ActorDataType<Float>)
* Added `FISH_ANGLE` (ActorDataType<Float>)
* Added `SCALE` (ActorDataType<Float>)
* Added `WIDTH` (ActorDataType<Float>)
* Added `HEIGHT` (ActorDataType<Float>)
* Added `SEAT_LOCK_RIDER_ROTATION_DEGREES` (ActorDataType<Float>)
* Added `SEAT_ROTATION_OFFSET_DEGREES` (ActorDataType<Float>)
* Added `AREA_EFFECT_CLOUD_RADIUS` (ActorDataType<Float>)
* Added `SITTING_AMOUNT` (ActorDataType<Float>)
* Added `SITTING_AMOUNT_PREVIOUS` (ActorDataType<Float>)
* Added `LAYING_AMOUNT` (ActorDataType<Float>)
* Added `LAYING_AMOUNT_PREVIOUS` (ActorDataType<Float>)
* Added `AREA_EFFECT_CLOUD_CHANGE_RATE` (ActorDataType<Float>)
* Added `AREA_EFFECT_CLOUD_CHANGE_ON_PICKUP` (ActorDataType<Float>)
* Added `AMBIENT_SOUND_INTERVAL` (ActorDataType<Float>)
* Added `AMBIENT_SOUND_INTERVAL_RANGE` (ActorDataType<Float>)
* Added `FALL_DAMAGE_MULTIPLIER` (ActorDataType<Float>)
* Added `FREEZING_EFFECT_STRENGTH` (ActorDataType<Float>)
* Added `MOVEMENT_SOUND_DISTANCE_OFFSET` (ActorDataType<Float>)
* Added `SEAT_THIRD_PERSON_CAMERA_RADIUS` (ActorDataType<Float>)
* Added `SEAT_CAMERA_RELAX_DISTANCE_SMOOTHING` (ActorDataType<Float>)
* Added `NAMEPLATE_RENDER_DISTANCE_MAX` (ActorDataType<Float>)
* Added `STRUCTURAL_INTEGRITY` (ActorDataType<Integer>)
* Added `VARIANT` (ActorDataType<Integer>)
* Added `EFFECT_COLOR` (ActorDataType<Integer>)
* Added `HURT_TICKS` (ActorDataType<Integer>)
* Added `HURT_DIRECTION` (ActorDataType<Integer>)
* Added `VALUE` (ActorDataType<Integer>)
* Added `HORSE_FLAGS` (ActorDataType<Integer>)
* Added `DISPLAY_OFFSET` (ActorDataType<Integer>)
* Added `OLD_SWELL` (ActorDataType<Integer>)
* Added `SWELL_DIRECTION` (ActorDataType<Integer>)
* Added `PLAYER_INDEX` (ActorDataType<Integer>)
* Added `MARK_VARIANT` (ActorDataType<Integer>)
* Added `CONTAINER_SIZE` (ActorDataType<Integer>)
* Added `CONTAINER_STRENGTH_MODIFIER` (ActorDataType<Integer>)
* Added `WITHER_INVULNERABLE_TICKS` (ActorDataType<Integer>)
* Added `FUSE_TIME` (ActorDataType<Integer>)
* Added `AREA_EFFECT_CLOUD_WAITING` (ActorDataType<Integer>)
* Added `SHULKER_PEEK_AMOUNT` (ActorDataType<Integer>)
* Added `SHULKER_ATTACH_FACE` (ActorDataType<Integer>)
* Added `CAREER` (ActorDataType<Integer>)
* Added `STRENGTH` (ActorDataType<Integer>)
* Added `STRENGTH_MAX` (ActorDataType<Integer>)
* Added `EVOKER_SPELL_CASTING_COLOR` (ActorDataType<Integer>)
* Added `DATA_LIFETIME_TICKS` (ActorDataType<Integer>)
* Added `ARMOR_STAND_POSE_INDEX` (ActorDataType<Integer>)
* Added `END_CRYSTAL_TICK_OFFSET` (ActorDataType<Integer>)
* Added `BOAT_BUBBLE_TIME` (ActorDataType<Integer>)
* Added `EATING_COUNTER` (ActorDataType<Integer>)
* Added `AREA_EFFECT_CLOUD_DURATION` (ActorDataType<Integer>)
* Added `AREA_EFFECT_CLOUD_SPAWN_TIME` (ActorDataType<Integer>)
* Added `AREA_EFFECT_CLOUD_PICKUP_COUNT` (ActorDataType<Integer>)
* Added `TRADE_TIER` (ActorDataType<Integer>)
* Added `MAX_TRADE_TIER` (ActorDataType<Integer>)
* Added `TRADE_EXPERIENCE` (ActorDataType<Integer>)
* Added `SKIN_ID` (ActorDataType<Integer>)
* Added `SPAWNING_FRAMES` (ActorDataType<Integer>)
* Added `COMMAND_BLOCK_TICK_DELAY` (ActorDataType<Integer>)
* Added `LOW_TIER_CURED_TRADE_DISCOUNT` (ActorDataType<Integer>)
* Added `HIGH_TIER_CURED_TRADE_DISCOUNT` (ActorDataType<Integer>)
* Added `NEARBY_CURED_TRADE_DISCOUNT` (ActorDataType<Integer>)
* Added `NEARBY_CURED_DISCOUNT_TIME_STAMP` (ActorDataType<Integer>)
* Added `GOAT_HORN_COUNT` (ActorDataType<Integer>)
* Added `HEARTBEAT_INTERVAL_TICKS` (ActorDataType<Integer>)
* Added `HEARTBEAT_SOUND_EVENT` (ActorDataType<Integer>)
* Added `PLAYER_LAST_DEATH_DIMENSION` (ActorDataType<Integer>)
* Added `AIM_ASSIST_PRIORITY_PRESET_ID` (ActorDataType<Integer>)
* Added `AIM_ASSIST_PRIORITY_CATEGORY_ID` (ActorDataType<Integer>)
* Added `AIM_ASSIST_PRIORITY_ACTOR_ID` (ActorDataType<Integer>)
* Added `UNKNOWN_HORSE_INT_25` (ActorDataType<Integer>)
* Added `OWNER_EID` (ActorDataType<Long>)
* Added `TARGET_EID` (ActorDataType<Long>)
* Added `LEASH_HOLDER` (ActorDataType<Long>)
* Added `WITHER_TARGET_A` (ActorDataType<Long>)
* Added `WITHER_TARGET_B` (ActorDataType<Long>)
* Added `WITHER_TARGET_C` (ActorDataType<Long>)
* Added `TRADE_TARGET_EID` (ActorDataType<Long>)
* Added `BALLOON_ANCHOR_EID` (ActorDataType<Long>)
* Added `AGENT_EID` (ActorDataType<Long>)
* Added `VISIBLE_MOB_EFFECTS` (ActorDataType<Long>)
* Added `ARROW_SHOOTER_ID` (ActorDataType<Long>)
* Added `FIREWORK_SHOOTER_ID` (ActorDataType<Long>)
* Added `RESERVED_139` (ActorDataType<Long>)
* Added `DISPLAY_FIREWORK` (ActorDataType<NbtMap>)
* Added `HITBOX` (ActorDataType<NbtMap>)
* Added `UPDATE_PROPERTIES` (ActorDataType<NbtMap>)
* Added `AREA_EFFECT_CLOUD_PARTICLE` (ActorDataType<ParticleType>)
* Added `AIR_SUPPLY` (ActorDataType<Short>)
* Added `AUX_VALUE_DATA` (ActorDataType<Short>)
* Added `AIR_SUPPLY_MAX` (ActorDataType<Short>)
* Added `WITHER_AERIAL_ATTACK` (ActorDataType<Short>)
* Added `NPC_DATA` (ActorDataType<String>)
* Added `ACTIONS` (ActorDataType<String>)
* Added `COMMAND_BLOCK_NAME` (ActorDataType<String>)
* Added `COMMAND_BLOCK_LAST_OUTPUT` (ActorDataType<String>)
* Added `AMBIENT_SOUND_EVENT_NAME` (ActorDataType<String>)
* Added `NAME_RAW_TEXT` (ActorDataType<String>)
* Added `BASE_RUNTIME_ID` (ActorDataType<String>)
* Added `BUOYANCY_DATA` (ActorDataType<String>)
* Added `SEAT_OFFSET` (ActorDataType<Vector3f>)
* Added `COLLISION_BOX` (ActorDataType<Vector3f>)
* Added `BED_ENTER_POSITION` (ActorDataType<Vector3f>)
* Added `FIREWORK_DIRECTION` (ActorDataType<Vector3f>)
* Added `BED_POSITION` (ActorDataType<Vector3i>)
* Added `BLOCK_TARGET_POS` (ActorDataType<Vector3i>)
* Added `SHULKER_ATTACH_POS` (ActorDataType<Vector3i>)
* Added `PLAYER_LAST_DEATH_POS` (ActorDataType<Vector3i>)

ActorDefinition:
* Added `eventName` (String)

ActorLink:
* Added `type` (ActorLinkType)
* Added `immediate` (boolean)
* Added `passengerInitiated` (boolean)
* Added `vehicleAngularVelocity` (float)
* Added `targetA` (long)
* Added `targetB` (long)

AddPage:
* Added `pageText` (String)
* Added `photoName` (String)
* Added `pageIndex` (int)

AddTimeMarkerData:
* Added `timeMarkers` (List<TimeMarkerData>)
* Added `clockId` (long)

AdventureSettings:
* Added `noPvm` (boolean)
* Added `noMvp` (boolean)
* Added `immutableWorld` (boolean)
* Added `showNameTags` (boolean)
* Added `autoJump` (boolean)

AgentCapabilities:
* Added `canModifyBlocks` (OptionalBoolean)

AgentCommand:
* Added `result` (AgentResult)
* Added `command` (String)
* Added `dataKey` (String)
* Added `output` (String)
* Added `dataValue` (int)

AgentCommandEventData:
* Removed `result` (AgentResult)
* Removed `command` (String)
* Removed `dataKey` (String)
* Removed `output` (String)
* Removed `dataValue` (int)

AgentCreatedEventData:
* Removed `INSTANCE` (AgentCreatedEventData)

AimAssistActorPriorityData:
* Added `presetIndex` (int)
* Added `categoryIndex` (int)
* Added `actorIndex` (int)
* Added `priorityValue` (int)

AnimatedImageData:
* Added `animatedTextureType` (PersonaAnimatedTextureType)
* Added `animationExpression` (PersonaAnimationExpression)
* Added `skinImage` (SkinImage)
* Added `frames` (float)

AnimationData:
* Removed `textureType` (AnimatedTextureType)
* Removed `expressionType` (AnimationExpressionType)
* Removed `image` (ImageData)
* Removed `frames` (float)

ArmorSlotAndDamagePair:
* Added `armorSlot` (ArmorSlot)
* Added `damage` (int)

ArrowDataPayload:
* Added `arrowHeadLength` (Float)
* Added `arrowHeadRadius` (Float)
* Added `numSegments` (Integer)
* Added `arrowEndLocation` (Vector3f)

AttributeData:
* Added `modifiers` (List<AttributeModifier>)
* Added `name` (String)
* Added `minValue` (float)
* Added `maxValue` (float)
* Added `currentValue` (float)
* Added `defaultMinValue` (float)
* Added `defaultMaxValue` (float)
* Added `defaultValue` (float)

AttributeLayerData:
* Added `settings` (AttributeLayerSettings)
* Added `dimension` (DimensionType)
* Added `attributes` (List<EnvironmentAttributeData>)
* Added `name` (String)
* Added `noiseName` (String)

AuthorAndMessage:
* Added `message` (CharSequence)
* Added `playerName` (String)

BellUsed:
* Added `itemId` (int)

BellUsedEventData:
* Removed `itemId` (int)

BiomeCappedSurfaceData:
* Added `seaBlock` (BlockDefinition)
* Added `foundationBlock` (BlockDefinition)
* Added `beachBlock` (BlockDefinition)
* Added `floorBlocks` (List<BlockDefinition>)
* Added `ceilingBlocks` (List<BlockDefinition>)

BiomeClimateData:
* Added `temperature` (float)
* Added `downfall` (float)
* Added `snowAccumulationMin` (float)
* Added `snowAccumulationMax` (float)

BiomeConditionalTransformationData:
* Added `transformsInto` (List<BiomeWeightedData>)
* Added `conditionJson` (int)
* Added `minPassingNeighbors` (int)

BiomeConsolidatedFeatureData:
* Added `scatter` (BiomeScatterParamData)
* Added `canUseInternalFeature` (boolean)
* Added `feature` (int)
* Added `identifier` (int)
* Added `pass` (int)

BiomeConsolidatedFeaturesData:
* Added `features` (List<BiomeConsolidatedFeatureData>)

BiomeCoordinateData:
* Added `minValueType` (ExpressionOp)
* Added `maxValueType` (ExpressionOp)
* Added `distribution` (RandomDistributionType)
* Added `minValue` (int)
* Added `maxValue` (int)
* Added `gridOffset` (int)
* Added `gridStepSize` (int)

BiomeDefinitionChunkGenData:
* Added `climate` (BiomeClimateData)
* Added `consolidatedFeatures` (BiomeConsolidatedFeaturesData)
* Added `legacyWorldGenRules` (BiomeLegacyWorldGenRulesData)
* Added `mountainParams` (BiomeMountainParamsData)
* Added `multinoiseGenRules` (BiomeMultinoiseGenRulesData)
* Added `overworldGenRules` (BiomeOverworldGenRulesData)
* Added `replacementBiomes` (BiomeReplacementsData)
* Added `surfaceBuilderData` (BiomeSurfaceBuilderData)
* Added `subsurfaceBuilderData` (BiomeSurfaceBuilderData)
* Added `surfaceMaterialAdjustments` (BiomeSurfaceMaterialAdjustmentData)
* Added `villageType` (VillageType)

BiomeDefinitionData:
* Added `chunkGenData` (BiomeDefinitionChunkGenData)
* Added `tags` (BiomeTagsData)
* Added `mapWaterColorArgb` (Color)
* Added `id` (Integer)
* Added `rain` (boolean)
* Added `temperature` (float)
* Added `downfall` (float)
* Added `redSporeDensity` (float)
* Added `blueSporeDensity` (float)
* Added `ashDensity` (float)
* Added `whiteAshDensity` (float)
* Added `foliageSnow` (float)
* Added `depth` (float)
* Added `scale` (float)

BiomeDefinitions:
* Added `definitions` (Map<String, BiomeDefinitionData>)

BiomeElementData:
* Added `adjustedMaterials` (BiomeSurfaceMaterialData)
* Added `heightMinType` (ExpressionOp)
* Added `heightMaxType` (ExpressionOp)
* Added `noiseFreqScale` (float)
* Added `noiseLowerBound` (float)
* Added `noiseUpperBound` (float)
* Added `heightMin` (int)
* Added `heightMax` (int)

BiomeLegacyWorldGenRulesData:
* Added `legacyPreHillsEdge` (List<BiomeConditionalTransformationData>)

BiomeMesaSurfaceData:
* Added `clayMaterial` (BlockDefinition)
* Added `hardClayMaterial` (BlockDefinition)
* Added `brycePillars` (boolean)
* Added `hasForest` (boolean)

BiomeMountainParamsData:
* Added `steepBlock` (BlockDefinition)
* Added `northSlopes` (boolean)
* Added `southSlopes` (boolean)
* Added `westSlopes` (boolean)
* Added `eastSlopes` (boolean)
* Added `topSlideEnabled` (boolean)

BiomeMultinoiseGenRulesData:
* Added `temperature` (float)
* Added `humidity` (float)
* Added `altitude` (float)
* Added `weirdness` (float)
* Added `weight` (float)

BiomeNoiseGradientSurfaceData:
* Added `nonreplaceableBlocks` (List<BlockDefinition>)
* Added `gradientBlocks` (List<SerializedNoiseBlockSpecifier>)
* Added `noise` (NoiseDescriptor)

BiomeOverworldGenRulesData:
* Added `preHillsEdge` (List<BiomeConditionalTransformationData>)
* Added `postShoreEdge` (List<BiomeConditionalTransformationData>)
* Added `hillsTransformations` (List<BiomeWeightedData>)
* Added `mutateTransformations` (List<BiomeWeightedData>)
* Added `riverTransformations` (List<BiomeWeightedData>)
* Added `shoreTransformations` (List<BiomeWeightedData>)
* Added `climate` (List<BiomeWeightedTemperatureData>)

BiomeReplacementData:
* Added `targetBiomes` (List<Integer>)
* Added `amount` (float)
* Added `noiseFrequencyScale` (float)
* Added `replacementBiome` (int)
* Added `dimension` (int)
* Added `replacementIndex` (int)

BiomeReplacementsData:
* Added `biomeReplacements` (List<BiomeReplacementData>)

BiomeScatterParamData:
* Added `evalOrder` (CoordinateEvaluationOrder)
* Added `chancePercentType` (ExpressionOp)
* Added `iterationsType` (ExpressionOp)
* Added `coordinates` (List<BiomeCoordinateData>)
* Added `chancePercent` (int)
* Added `chanceNumerator` (int)
* Added `chanceDenominator` (int)
* Added `iterations` (int)

BiomeStringList:
* Added `strings` (List<String>)

BiomeSurfaceBuilderData:
* Added `cappedSurface` (BiomeCappedSurfaceData)
* Added `mesaSurface` (BiomeMesaSurfaceData)
* Added `noiseGradientSurface` (BiomeNoiseGradientSurfaceData)
* Added `surfaceMaterials` (BiomeSurfaceMaterialData)
* Added `hasDefaultOverworldSurface` (boolean)
* Added `hasSwampSurface` (boolean)
* Added `hasFrozenOceanSurface` (boolean)
* Added `hasTheEndSurface` (boolean)

BiomeSurfaceMaterialAdjustmentData:
* Added `adjustments` (List<BiomeElementData>)

BiomeSurfaceMaterialData:
* Added `topBlock` (BlockDefinition)
* Added `midBlock` (BlockDefinition)
* Added `seaFloorBlock` (BlockDefinition)
* Added `foundationBlock` (BlockDefinition)
* Added `seaBlock` (BlockDefinition)
* Added `seaFloorDepth` (int)

BiomeTagsData:
* Added `tags` (List<Integer>)

BiomeWeightedData:
* Added `biomeIdentifier` (int)
* Added `weight` (int)

BiomeWeightedTemperatureData:
* Added `temperature` (BiomeTemperatureCategory)
* Added `weight` (int)

BlockCommandData:
* Added `commandBlockMode` (CommandBlockMode)
* Added `blockPosition` (Vector3i)
* Added `redstoneMode` (boolean)
* Added `isConditional` (boolean)

BlockPropertyData:
* Removed `properties` (NbtMap)
* Removed `name` (String)

BoolAttributeData:
* Added `operation` (BoolAttributeOperation)
* Added `value` (boolean)

BossKilled:
* Added `partySize` (int)
* Added `bossType` (int)
* Added `bossActorID` (long)

BossKilledEventData:
* Removed `playerPartySize` (int)
* Removed `bossEntityType` (int)
* Removed `bossUniqueEntityId` (long)

BoxDataPayload:
* Added `boxBound` (Vector3f)

CameraAimAssistCategories:
* Removed `categories` (List<CameraAimAssistCategory>)
* Removed `identifier` (String)

CameraAimAssistCategory:
* Removed `entityDefaultPriorities` (Integer)
* Removed `blockDefaultPriorities` (Integer)
* Removed `entityPriorities` (List<CameraAimAssistPriority>)
* Removed `blockPriorities` (List<CameraAimAssistPriority>)
* Removed `name` (String)

CameraAimAssistCategoryDefinition:
* Added `priorities` (CameraAimAssistCategoryPriorities)
* Added `name` (String)

CameraAimAssistCategoryPriorities:
* Added `entityDefault` (Integer)
* Added `blockDefault` (Integer)
* Added `entities` (Map<String, Integer>)
* Added `blocks` (Map<String, Integer>)
* Added `blockTags` (Map<String, Integer>)
* Added `entityTypeFamilies` (Map<String, Integer>)

CameraAimAssistCommandDefinition:
* Added `targetMode` (AimAssistTargetMode)
* Added `distance` (Float)
* Added `presetId` (String)
* Added `viewAngle` (Vector2f)

CameraAimAssistItemSettings:
* Removed `itemId` (String)
* Removed `category` (String)

CameraAimAssistPreset:
* Removed `distance` (Float)
* Removed `targetMode` (Integer)
* Removed `identifier` (String)
* Removed `angle` (Vector2f)

CameraAimAssistPresetDefinition:
* Added `exclusionSettings` (CameraAimAssistPresetExclusionDefinition)
* Removed `exclusionList` (List<String>)

CameraAimAssistPresetExclusionDefinition:
* Added `blocks` (List<String>)
* Added `entities` (List<String>)
* Added `blockTags` (List<String>)
* Added `entityTypeFamilies` (List<String>)

CameraAimAssistPriority:
* Removed `name` (String)
* Removed `priority` (int)

CameraAttachToEntityInstruction:
* Added `entityActorID` (long)

CameraFadeInstruction:
* Added `color` (ColorOption)
* Added `time` (TimeOption)
* Removed `color` (Color)
* Removed `timeData` (TimeData)
* Removed `fadeInTime` (float)
* Removed `waitTime` (float)
* Removed `fadeOutTime` (float)

CameraFovInstruction:
* Renamed `clear` to `fieldOfViewClear` (boolean)
* Added `fovEaseType` (EasingFunction)
* Added `fieldOfView` (float)
* Added `fovEaseTime` (float)
* Removed `easeType` (CameraEase)
* Removed `fov` (float)
* Removed `easeTime` (float)

CameraInstruction:
* Added `attachToEntity` (CameraAttachToEntityInstruction)
* Added `fade` (CameraFadeInstruction)
* Added `fieldOfView` (CameraFovInstruction)
* Added `set` (CameraSetInstruction)
* Added `spline` (CameraSplineInstruction)
* Added `target` (CameraTargetInstruction)
* Added `clear` (OptionalBoolean)
* Added `removeTarget` (OptionalBoolean)
* Added `detachFromEntity` (OptionalBoolean)

CameraPresets:
* Added `listener` (AudioListener)
* Added `aimAssist` (CameraAimAssistCommandDefinition)
* Added `posX` (Float)
* Added `posY` (Float)
* Added `posZ` (Float)
* Added `rotX` (Float)
* Added `rotY` (Float)
* Added `rotationSpeed` (Float)
* Added `blockListeningRadius` (Float)
* Added `radius` (Float)
* Added `yawLimitMin` (Float)
* Added `yawLimitMax` (Float)
* Added `snapToTarget` (OptionalBoolean)
* Added `continueTargeting` (OptionalBoolean)
* Added `playerEffects` (OptionalBoolean)
* Added `alignTargetAndCameraForward` (OptionalBoolean)
* Added `name` (String)
* Added `inheritFrom` (String)
* Added `horizontalRotationLimit` (Vector2f)
* Added `verticalRotationLimit` (Vector2f)
* Added `viewOffset` (Vector2f)
* Added `startingRotation` (Vector2f)
* Added `entityOffset` (Vector3f)
* Added `applyInheritedStartingRotation` (boolean)
* Removed `aimAssistPreset` (CameraAimAssistPreset)
* Removed `listener` (CameraAudioListener)
* Removed `yaw` (Float)
* Removed `pitch` (Float)
* Removed `radius` (Float)
* Removed `minYawLimit` (Float)
* Removed `maxYawLimit` (Float)
* Removed `rotationSpeed` (Float)
* Removed `blockListeningRadius` (Float)
* Removed `playEffect` (OptionalBoolean)
* Removed `snapToTarget` (OptionalBoolean)
* Removed `continueTargeting` (OptionalBoolean)
* Removed `alignTargetAndCameraForward` (OptionalBoolean)
* Removed `identifier` (String)
* Removed `parentPreset` (String)
* Removed `viewOffset` (Vector2f)
* Removed `horizontalRotationLimit` (Vector2f)
* Removed `verticalRotationLimit` (Vector2f)
* Removed `pos` (Vector3f)
* Removed `entityOffset` (Vector3f)

CameraSetInstruction:
* Renamed `defaultPreset` to `defaultValue` (OptionalBoolean)
* Renamed `removeIgnoreStartingValues` to `removeIgnoreStartingValuesComponent` (boolean)
* Added `ease` (EaseOption)
* Added `entityOffset` (EntityOffsetOption)
* Added `facing` (FacingOption)
* Added `pos` (PosOption)
* Added `rot` (RotOption)
* Added `viewOffset` (ViewOffsetOption)
* Removed `easeType` (CameraEase)
* Removed `ease` (EaseData)
* Removed `rot` (Vector2f)
* Removed `viewOffset` (Vector2f)
* Removed `pos` (Vector3f)
* Removed `facing` (Vector3f)
* Removed `entityOffset` (Vector3f)
* Removed `time` (float)

CameraSplineControlPoint:
* Added `position` (Vector3f)

CameraSplineDefinition:
* Added `splineType` (CameraSplineType)
* Added `controlPoints` (List<CameraSplineControlPoint>)
* Added `progressKeyFrames` (List<CameraSplineProgressKeyFrame>)
* Added `rotationKeyFrames` (List<CameraSplineRotationKeyFrame>)
* Added `name` (String)
* Added `splineIdentifier` (String)
* Added `loadFromJson` (boolean)
* Added `totalTime` (float)

CameraSplineInstruction:
* Added `type` (CameraSplineType)
* Added `progressKeyFrames` (List<SplineProgressOption>)
* Added `rotationOption` (List<SplineRotationOption>)
* Added `curve` (List<Vector3f>)
* Added `splineIdentifier` (String)
* Added `loadFromJson` (boolean)
* Added `totalTime` (float)

CameraSplineProgressKeyFrame:
* Added `easing` (EasingFunction)
* Added `progress` (float)
* Added `time` (float)

CameraSplineRotationKeyFrame:
* Added `easing` (EasingFunction)
* Added `rotation` (Vector3f)
* Added `time` (float)

CameraTargetInstruction:
* Renamed `uniqueEntityId` to `targetActorID` (long)

CarefulRestorationEventData:
* Removed `INSTANCE` (CarefulRestorationEventData)

CauldronInteractEventData:
* Removed `blockInteractionType` (BlockInteractionType)
* Removed `itemId` (int)

CauldronUsed:
* Added `contentsColor` (int)
* Added `contentsType` (int)
* Added `fillLevel` (int)

CauldronUsedEventData:
* Removed `potionId` (int)
* Removed `color` (int)
* Removed `fillLevel` (int)

CertificateChainPayload:
* Removed `type` (AuthType)
* Removed `chain` (List<String>)

ChainedSubCommandData:
* Added `second` (int)
* Removed `second` (String)

ChangeEntityScore:
* Added `scoreboardId` (ScoreboardId)
* Added `objectiveName` (String)
* Added `scoreValue` (int)
* Added `actorId` (long)

ChangeFakePlayerScore:
* Added `scoreboardId` (ScoreboardId)
* Added `objectiveName` (String)
* Added `fakePlayerName` (String)
* Added `scoreValue` (int)

ChangePlayerScore:
* Added `playerUniqueId` (PlayerScoreboardId)
* Added `scoreboardId` (ScoreboardId)
* Added `objectiveName` (String)
* Added `scoreValue` (int)

ChunkPos:
* Added `x` (int)
* Added `z` (int)

ClearOverride:
* Added `type` (UpdateType)

ClientPixelsProxy:
* Added `pixel` (int)
* Added `index` (int)

ClientStoreEntryPointConfig:
* Added `storeId` (String)
* Added `storeName` (String)

CodeBuilderActionEventData:
* Removed `action` (String)

CodeBuilderRuntimeAction:
* Added `codeBuilderRuntimeAction` (String)

CodeBuilderScoreboard:
* Added `objectiveName` (String)
* Added `score` (int)

CodeBuilderScoreboardEventData:
* Removed `objectiveName` (String)
* Removed `score` (int)

Color255RGBA:
* Added `stringColor` (String)
* Added `type` (int)
* Added `arrayColor` (int[])

ColorAttributeData:
* Added `color` (Color255RGBA)
* Added `operation` (ColorAttributeOperation)

ColorOption:
* Added `red` (float)
* Added `green` (float)
* Added `blue` (float)

CommandOriginData:
* Renamed `origin` to `type` (CommandOriginType)
* Added `playerId` (Long)
* Added `requestId` (String)
* Added `uuid` (UUID)
* Removed `requestId` (String)
* Removed `uuid` (UUID)
* Removed `event` (long)

CommandOutput:
* Added `outputType` (CommandOutputType)
* Added `outputMessages` (List<CommandOutputMessage>)
* Added `dataSet` (String)
* Added `successCount` (int)

CommandOutputMessage:
* Renamed `messageId` to `messageID` (String)
* Renamed `internal` to `successful` (boolean)
* Added `parameters` (List<String>)
* Removed `parameters` (String[])

CommandParam:
* Added `VAL` (CommandParam)
* Added `RVAL` (CommandParam)
* Added `SELECTION` (CommandParam)
* Added `STANDALONE_SELECTION` (CommandParam)
* Added `WILDCARD_SELECTION` (CommandParam)
* Added `NON_ID_SELECTOR` (CommandParam)
* Added `SCORES_ARG` (CommandParam)
* Added `SCORES_ARGS` (CommandParam)
* Added `INTEGER_RANGE_VAL` (CommandParam)
* Added `INTEGER_RANGE_POST_VAL` (CommandParam)
* Added `INTEGER_RANGE` (CommandParam)
* Added `FULL_INTEGER_RANGE` (CommandParam)
* Added `RATIONAL_RANGE_VAL` (CommandParam)
* Added `RATIONAL_RANGE_POST_VAL` (CommandParam)
* Added `RATIONAL_RANGE` (CommandParam)
* Added `FULL_RATIONAL_RANGE` (CommandParam)
* Added `NAME_ARG` (CommandParam)
* Added `TYPE_ARG` (CommandParam)
* Added `FAMILY_ARG` (CommandParam)
* Added `HAS_PERMISSION_ARG` (CommandParam)
* Added `HAS_PERMISSIONS_ARG` (CommandParam)
* Added `HAS_PERMISSION_SELECTOR` (CommandParam)
* Added `HAS_PERMISSION_ELEMENT` (CommandParam)
* Added `HAS_PERMISSION_ELEMENTS` (CommandParam)
* Added `TAG_ARG` (CommandParam)
* Added `HAS_ITEM_ARG` (CommandParam)
* Added `HAS_ITEM_ARGS` (CommandParam)
* Added `EQUIPMENT_SLOT_ENUM` (CommandParam)
* Added `PROPERTY_VALUE` (CommandParam)
* Added `HAS_PROPERTY_PARAM_VALUE` (CommandParam)
* Added `HAS_PROPERTY_PARAM_ENUM_VALUE` (CommandParam)
* Added `HAS_PROPERTY_ARG` (CommandParam)
* Added `HAS_PROPERTY_ARGS` (CommandParam)
* Added `HAS_PROPERTY_ELEMENT` (CommandParam)
* Added `HAS_PROPERTY_ELEMENTS` (CommandParam)
* Added `HAS_PROPERTY_SELECTOR` (CommandParam)
* Added `ID` (CommandParam)
* Added `POSITION_FLOAT` (CommandParam)
* Added `MESSAGE_EXP` (CommandParam)
* Added `RAW_TEXT` (CommandParam)
* Added `RAW_TEXT_CONT` (CommandParam)
* Added `JSON_OBJECT` (CommandParam)
* Added `BLOCK_STATE_ARRAY` (CommandParam)
* Added `BLOCK_STATE_ARRAY_CONT` (CommandParam)
* Added `CLOCK_TIME_MARKER_NAME` (CommandParam)
* Removed `VALUE` (CommandParam)
* Removed `R_VALUE` (CommandParam)
* Removed `TARGET` (CommandParam)
* Removed `UNKNOWN_STANDALONE` (CommandParam)
* Removed `WILDCARD_TARGET` (CommandParam)
* Removed `UNKNOWN_NON_ID` (CommandParam)
* Removed `SCORE_ARG` (CommandParam)
* Removed `SCORE_ARGS` (CommandParam)
* Removed `INT_RANGE_VAL` (CommandParam)
* Removed `INT_RANGE_POST_VAL` (CommandParam)
* Removed `INT_RANGE` (CommandParam)
* Removed `INT_RANGE_FULL` (CommandParam)
* Removed `NAME` (CommandParam)
* Removed `TYPE` (CommandParam)
* Removed `FAMILY` (CommandParam)
* Removed `PERMISSION` (CommandParam)
* Removed `PERMISSIONS` (CommandParam)
* Removed `PERMISSION_SELECTOR` (CommandParam)
* Removed `PERMISSION_ELEMENT` (CommandParam)
* Removed `PERMISSION_ELEMENTS` (CommandParam)
* Removed `TAG` (CommandParam)
* Removed `HAS_ITEM` (CommandParam)
* Removed `HAS_ITEMS` (CommandParam)
* Removed `EQUIPMENT_SLOTS` (CommandParam)
* Removed `STRING` (CommandParam)
* Removed `BLOCK_POSITION` (CommandParam)
* Removed `MESSAGE_XP` (CommandParam)
* Removed `TEXT` (CommandParam)
* Removed `TEXT_CONT` (CommandParam)
* Removed `JSON` (CommandParam)
* Removed `BLOCK_STATES` (CommandParam)
* Removed `BLOCK_STATES_CONT` (CommandParam)
* Removed `RATIONAL_RANGE_VAL` (CommandParam)
* Removed `RATIONAL_RANGE_POST_VAL` (CommandParam)
* Removed `RATIONAL_RANGE` (CommandParam)
* Removed `RATIONAL_RANGE_FULL` (CommandParam)
* Removed `PROPERTY_VALUE` (CommandParam)
* Removed `HAS_PROPERTY_PARAM_VALUE` (CommandParam)
* Removed `HAS_PROPERTY_PARAM_ENUM_VALUE` (CommandParam)
* Removed `HAS_PROPERTY_ARG` (CommandParam)
* Removed `HAS_PROPERTY_ARGS` (CommandParam)
* Removed `HAS_PROPERTY_ELEMENT` (CommandParam)
* Removed `HAS_PROPERTY_ELEMENTS` (CommandParam)
* Removed `HAS_PROPERTY_SELECTOR` (CommandParam)

CommandSymbolData:
* Removed `commandEnum` (boolean)
* Removed `softEnum` (boolean)
* Removed `postfix` (boolean)
* Removed `ARG_FLAG_VALID` (int)
* Removed `ARG_FLAG_ENUM` (int)
* Removed `ARG_FLAG_POSTFIX` (int)
* Removed `ARG_FLAG_SOFT_ENUM` (int)
* Removed `value` (int)

ComposterInteractEventData:
* Removed `blockInteractionType` (BlockInteractionType)
* Removed `itemId` (int)

ComposterUsed:
* Added `blockInteractionType` (POIBlockInteractionType)
* Added `itemId` (int)

ConeDataPayload:
* Added `radii` (Vector2f)
* Added `height` (float)
* Added `numSegments` (int)

ContainerId:
* Added `DROP_CONTENTS` (int)
* Removed `DROP_CONTENTS` (int)

ContainerMixData:
* Removed `inputId` (int)
* Removed `reagentId` (int)
* Removed `outputId` (int)

ContainerMixDataEntry:
* Added `fromItemId` (int)
* Added `reagentItemId` (int)
* Added `outputItemId` (int)

ContentIdentity:
* Added `identity` (String)

CoordinatesLocation:
* Added `position` (Vector3f)

CopperWaxedOrUnwaxedEventData:
* Removed `definition` (BlockDefinition)

CreativeGroupInfoPayload:
* Added `creativeCategory` (CreativeCategory)
* Added `groupIconItem` (ItemData)
* Added `name` (String)

CreativeItemData:
* Removed `item` (ItemData)
* Removed `netId` (int)
* Removed `groupId` (int)

CreativeItemEntryPayload:
* Added `creativeNetId` (CreativeItemNetId)
* Added `itemInstance` (ItemData)
* Added `groupIndex` (int)

CreativeItemGroup:
* Removed `category` (CreativeItemCategory)
* Removed `icon` (ItemData)
* Removed `name` (String)

CreativeItemNetId:
* Added `ID` (int)

CylinderDataPayload:
* Added `radiusX` (Vector2f)
* Added `radiusZ` (Vector2f)
* Added `height` (float)
* Added `numSegments` (int)

DataStoreChange:
* Added `theNewPropertyValue` (Object)
* Added `dataStoreName` (String)
* Added `property` (String)
* Added `updateCount` (int)

DataStoreRemoval:
* Added `dataStoreName` (String)

DeathCauseMessageType:
* Added `deathCauseMessageList` (List<String>)
* Added `deathCauseAttackName` (String)

DebugMarkerData:
* Added `color` (Color)
* Added `text` (String)
* Added `position` (Vector3f)
* Added `duration` (long)

DeletePage:
* Added `pageIndex` (int)

DisconnectPacketMessages:
* Added `message` (String)
* Added `filteredMessage` (String)

EaseOption:
* Added `type` (EasingFunction)
* Added `time` (float)

EduSharedUriResource:
* Added `EMPTY` (EduSharedUriResource)
* Added `buttonName` (String)
* Added `linkUri` (String)

EduSharedUriResource:
* Removed `EMPTY` (EduSharedUriResource)

EducationLevelSettings:
* Added `agentCapabilities` (AgentCapabilities)
* Added `localSettings` (EducationLocalLevelSettings)
* Added `externalLinkSettings` (ExternalLinkSettings)
* Added `codeBuilderDefaultURI` (String)
* Added `codeBuilderTitle` (String)
* Added `postProcessFilter` (String)
* Added `screenshotBorderResourcePath` (String)
* Added `canResizeCodeBuilder` (boolean)
* Added `disableLegacyTitleBar` (boolean)
* Added `deprecatedAlwaysFalse` (boolean)

EducationLocalLevelSettings:
* Added `codeBuilderOverrideUri` (String)

EllipsoidDataPayload:
* Added `radii` (Vector3f)
* Added `segmentsPerAxis` (int)

EmptyDescriptor:
* Added `INSTANCE` (EmptyDescriptor)

EnchantData:
* Removed `type` (int)
* Removed `level` (int)

EnchantOptionData:
* Removed `enchants0` (List<EnchantData>)
* Removed `enchants1` (List<EnchantData>)
* Removed `enchants2` (List<EnchantData>)
* Removed `enchantName` (String)
* Removed `cost` (int)
* Removed `primarySlot` (int)
* Removed `enchantNetId` (int)

EnchantmentInstance:
* Added `enchantType` (EnchantType)
* Added `enchantLevel` (int)

EncodingSettings:
* Added `maxPlayerBlockActionDataSize` (int)

EntityCommandTarget:
* Added `targetRuntimeID` (long)

EntityDataTypes:
* Removed `BLOCK` (EntityDataType<BlockDefinition>)
* Removed `DISPLAY_BLOCK_STATE` (EntityDataType<BlockDefinition>)
* Removed `CARRY_BLOCK_STATE` (EntityDataType<BlockDefinition>)
* Removed `USING_ITEM` (EntityDataType<Boolean>)
* Removed `HAS_NPC` (EntityDataType<Boolean>)
* Removed `SEAT_LOCK_RIDER_ROTATION` (EntityDataType<Boolean>)
* Removed `SEAT_HAS_ROTATION` (EntityDataType<Boolean>)
* Removed `SHULKER_ATTACHED` (EntityDataType<Boolean>)
* Removed `COMMAND_BLOCK_ENABLED` (EntityDataType<Boolean>)
* Removed `COMMAND_BLOCK_TRACK_OUTPUT` (EntityDataType<Boolean>)
* Removed `COMMAND_BLOCK_EXECUTE_ON_FIRST_TICK` (EntityDataType<Boolean>)
* Removed `CAN_RIDE_TARGET` (EntityDataType<Boolean>)
* Removed `IS_BUOYANT` (EntityDataType<Boolean>)
* Removed `PLAYER_HAS_DIED` (EntityDataType<Boolean>)
* Removed `COLOR` (EntityDataType<Byte>)
* Removed `EFFECT_AMBIENCE` (EntityDataType<Byte>)
* Removed `JUMP_DURATION` (EntityDataType<Byte>)
* Removed `WITHER_SKULL_DANGEROUS` (EntityDataType<Byte>)
* Removed `CUSTOM_DISPLAY` (EntityDataType<Byte>)
* Removed `HORSE_TYPE` (EntityDataType<Byte>)
* Removed `CHARGE_AMOUNT` (EntityDataType<Byte>)
* Removed `CLIENT_EVENT` (EntityDataType<Byte>)
* Removed `PLAYER_FLAGS` (EntityDataType<Byte>)
* Removed `AUX_POWER` (EntityDataType<Byte>)
* Removed `CONTAINER_TYPE` (EntityDataType<Byte>)
* Removed `CONTROLLING_RIDER_SEAT_INDEX` (EntityDataType<Byte>)
* Removed `NAMETAG_ALWAYS_SHOW` (EntityDataType<Byte>)
* Removed `COLOR_2` (EntityDataType<Byte>)
* Removed `PUFFED_STATE` (EntityDataType<Byte>)
* Removed `FLAGS` (EntityDataType<EnumSet<EntityFlag>>)
* Removed `FLAGS_2` (EntityDataType<EnumSet<EntityFlag>>)
* Removed `ROW_TIME_LEFT` (EntityDataType<Float>)
* Removed `ROW_TIME_RIGHT` (EntityDataType<Float>)
* Removed `FIREBALL_POWER_X` (EntityDataType<Float>)
* Removed `FIREBALL_POWER_Y` (EntityDataType<Float>)
* Removed `FIREBALL_POWER_Z` (EntityDataType<Float>)
* Removed `FISH_X` (EntityDataType<Float>)
* Removed `FISH_Z` (EntityDataType<Float>)
* Removed `FISH_ANGLE` (EntityDataType<Float>)
* Removed `SCALE` (EntityDataType<Float>)
* Removed `WIDTH` (EntityDataType<Float>)
* Removed `HEIGHT` (EntityDataType<Float>)
* Removed `SEAT_LOCK_RIDER_ROTATION_DEGREES` (EntityDataType<Float>)
* Removed `SEAT_ROTATION_OFFSET_DEGREES` (EntityDataType<Float>)
* Removed `AREA_EFFECT_CLOUD_RADIUS` (EntityDataType<Float>)
* Removed `SITTING_AMOUNT` (EntityDataType<Float>)
* Removed `SITTING_AMOUNT_PREVIOUS` (EntityDataType<Float>)
* Removed `LAYING_AMOUNT` (EntityDataType<Float>)
* Removed `LAYING_AMOUNT_PREVIOUS` (EntityDataType<Float>)
* Removed `AREA_EFFECT_CLOUD_CHANGE_RATE` (EntityDataType<Float>)
* Removed `AREA_EFFECT_CLOUD_CHANGE_ON_PICKUP` (EntityDataType<Float>)
* Removed `AMBIENT_SOUND_INTERVAL` (EntityDataType<Float>)
* Removed `AMBIENT_SOUND_INTERVAL_RANGE` (EntityDataType<Float>)
* Removed `FALL_DAMAGE_MULTIPLIER` (EntityDataType<Float>)
* Removed `FREEZING_EFFECT_STRENGTH` (EntityDataType<Float>)
* Removed `MOVEMENT_SOUND_DISTANCE_OFFSET` (EntityDataType<Float>)
* Removed `SEAT_THIRD_PERSON_CAMERA_RADIUS` (EntityDataType<Float>)
* Removed `SEAT_CAMERA_RELAX_DISTANCE_SMOOTHING` (EntityDataType<Float>)
* Removed `STRUCTURAL_INTEGRITY` (EntityDataType<Integer>)
* Removed `VARIANT` (EntityDataType<Integer>)
* Removed `EFFECT_COLOR` (EntityDataType<Integer>)
* Removed `HURT_TICKS` (EntityDataType<Integer>)
* Removed `HURT_DIRECTION` (EntityDataType<Integer>)
* Removed `VALUE` (EntityDataType<Integer>)
* Removed `HORSE_FLAGS` (EntityDataType<Integer>)
* Removed `DISPLAY_OFFSET` (EntityDataType<Integer>)
* Removed `OLD_SWELL` (EntityDataType<Integer>)
* Removed `SWELL_DIRECTION` (EntityDataType<Integer>)
* Removed `PLAYER_INDEX` (EntityDataType<Integer>)
* Removed `MARK_VARIANT` (EntityDataType<Integer>)
* Removed `CONTAINER_SIZE` (EntityDataType<Integer>)
* Removed `CONTAINER_STRENGTH_MODIFIER` (EntityDataType<Integer>)
* Removed `WITHER_INVULNERABLE_TICKS` (EntityDataType<Integer>)
* Removed `FUSE_TIME` (EntityDataType<Integer>)
* Removed `AREA_EFFECT_CLOUD_WAITING` (EntityDataType<Integer>)
* Removed `SHULKER_PEEK_AMOUNT` (EntityDataType<Integer>)
* Removed `SHULKER_ATTACH_FACE` (EntityDataType<Integer>)
* Removed `CAREER` (EntityDataType<Integer>)
* Removed `STRENGTH` (EntityDataType<Integer>)
* Removed `STRENGTH_MAX` (EntityDataType<Integer>)
* Removed `EVOKER_SPELL_CASTING_COLOR` (EntityDataType<Integer>)
* Removed `DATA_LIFETIME_TICKS` (EntityDataType<Integer>)
* Removed `ARMOR_STAND_POSE_INDEX` (EntityDataType<Integer>)
* Removed `END_CRYSTAL_TICK_OFFSET` (EntityDataType<Integer>)
* Removed `BOAT_BUBBLE_TIME` (EntityDataType<Integer>)
* Removed `EATING_COUNTER` (EntityDataType<Integer>)
* Removed `AREA_EFFECT_CLOUD_DURATION` (EntityDataType<Integer>)
* Removed `AREA_EFFECT_CLOUD_SPAWN_TIME` (EntityDataType<Integer>)
* Removed `AREA_EFFECT_CLOUD_PICKUP_COUNT` (EntityDataType<Integer>)
* Removed `TRADE_TIER` (EntityDataType<Integer>)
* Removed `MAX_TRADE_TIER` (EntityDataType<Integer>)
* Removed `TRADE_EXPERIENCE` (EntityDataType<Integer>)
* Removed `SKIN_ID` (EntityDataType<Integer>)
* Removed `SPAWNING_FRAMES` (EntityDataType<Integer>)
* Removed `COMMAND_BLOCK_TICK_DELAY` (EntityDataType<Integer>)
* Removed `LOW_TIER_CURED_TRADE_DISCOUNT` (EntityDataType<Integer>)
* Removed `HIGH_TIER_CURED_TRADE_DISCOUNT` (EntityDataType<Integer>)
* Removed `NEARBY_CURED_TRADE_DISCOUNT` (EntityDataType<Integer>)
* Removed `NEARBY_CURED_DISCOUNT_TIME_STAMP` (EntityDataType<Integer>)
* Removed `GOAT_HORN_COUNT` (EntityDataType<Integer>)
* Removed `HEARTBEAT_INTERVAL_TICKS` (EntityDataType<Integer>)
* Removed `HEARTBEAT_SOUND_EVENT` (EntityDataType<Integer>)
* Removed `PLAYER_LAST_DEATH_DIMENSION` (EntityDataType<Integer>)
* Removed `OWNER_EID` (EntityDataType<Long>)
* Removed `TARGET_EID` (EntityDataType<Long>)
* Removed `LEASH_HOLDER` (EntityDataType<Long>)
* Removed `WITHER_TARGET_A` (EntityDataType<Long>)
* Removed `WITHER_TARGET_B` (EntityDataType<Long>)
* Removed `WITHER_TARGET_C` (EntityDataType<Long>)
* Removed `TRADE_TARGET_EID` (EntityDataType<Long>)
* Removed `BALLOON_ANCHOR_EID` (EntityDataType<Long>)
* Removed `AGENT_EID` (EntityDataType<Long>)
* Removed `VISIBLE_MOB_EFFECTS` (EntityDataType<Long>)
* Removed `DISPLAY_FIREWORK` (EntityDataType<NbtMap>)
* Removed `HITBOX` (EntityDataType<NbtMap>)
* Removed `UPDATE_PROPERTIES` (EntityDataType<NbtMap>)
* Removed `AREA_EFFECT_CLOUD_PARTICLE` (EntityDataType<ParticleType>)
* Removed `AIR_SUPPLY` (EntityDataType<Short>)
* Removed `AUX_VALUE_DATA` (EntityDataType<Short>)
* Removed `AIR_SUPPLY_MAX` (EntityDataType<Short>)
* Removed `WITHER_AERIAL_ATTACK` (EntityDataType<Short>)
* Removed `NAME` (EntityDataType<String>)
* Removed `NPC_DATA` (EntityDataType<String>)
* Removed `ACTIONS` (EntityDataType<String>)
* Removed `COMMAND_BLOCK_NAME` (EntityDataType<String>)
* Removed `COMMAND_BLOCK_LAST_OUTPUT` (EntityDataType<String>)
* Removed `NAME_AUTHOR` (EntityDataType<String>)
* Removed `SCORE` (EntityDataType<String>)
* Removed `INTERACT_TEXT` (EntityDataType<String>)
* Removed `AMBIENT_SOUND_EVENT_NAME` (EntityDataType<String>)
* Removed `NAME_RAW_TEXT` (EntityDataType<String>)
* Removed `BASE_RUNTIME_ID` (EntityDataType<String>)
* Removed `BUOYANCY_DATA` (EntityDataType<String>)
* Removed `FILTERED_NAME` (EntityDataType<String>)
* Removed `SEAT_OFFSET` (EntityDataType<Vector3f>)
* Removed `COLLISION_BOX` (EntityDataType<Vector3f>)
* Removed `BED_ENTER_POSITION` (EntityDataType<Vector3f>)
* Removed `BED_POSITION` (EntityDataType<Vector3i>)
* Removed `BLOCK_TARGET_POS` (EntityDataType<Vector3i>)
* Removed `SHULKER_ATTACH_POS` (EntityDataType<Vector3i>)
* Removed `PLAYER_LAST_DEATH_POS` (EntityDataType<Vector3i>)

EntityDefinitionTriggerEventData:
* Removed `eventName` (String)

EntityDiagnosticTimingInfo:
* Added `displayName` (String)
* Added `entity` (String)
* Added `dimension` (String)
* Added `position` (Vector3f)
* Added `percentOfTotal` (int)
* Added `timeInNs` (long)

EntityInteractEventData:
* Removed `interactionType` (int)
* Removed `legacyEntityTypeId` (int)
* Removed `variant` (int)
* Removed `paletteColor` (int)
* Removed `interactedEntityID` (long)

EntityOffsetOption:
* Added `entityOffsetX` (float)
* Added `entityOffsetY` (float)
* Added `entityOffsetZ` (float)

EnvironmentAttributeData:
* Added `easing` (EasingFunction)
* Added `noiseAlignment` (NoiseAlignment)
* Added `fromAttribute` (Object)
* Added `attribute` (Object)
* Added `toAttribute` (Object)
* Added `attributeName` (String)
* Added `noiseTransition` (boolean)
* Added `currentTransitionTicks` (int)
* Added `totalTransitionTicks` (int)
* Added `localTransitionTicks` (int)

ExperimentData:
* Removed `name` (String)
* Removed `enabled` (boolean)

ExperimentToggle:
* Added `name` (String)
* Added `enabled` (boolean)

Experiments:
* Added `toggles` (List<ExperimentToggle>)
* Added `experimentsEverToggled` (boolean)

ExternalLinkSettings:
* Added `URL` (String)
* Added `displayName` (String)

ExtractHoneyEventData:
* Removed `INSTANCE` (ExtractHoneyEventData)

FacingOption:
* Added `pos` (Vector3f)

Fade:
* Added `duration` (float)
* Added `targetVolume` (float)

FeatureRegistryFeatureBinaryJsonFormat:
* Added `featureName` (String)
* Added `binaryJsonOutput` (String)

Finalize:
* Added `title` (String)
* Added `author` (String)
* Added `xUID` (String)

FishBucketed:
* Added `releaseEvent` (boolean)
* Added `pattern` (int)
* Added `preset` (int)
* Added `bucketedEntityType` (int)

FishBucketedEventData:
* Removed `releaseEvent` (boolean)
* Removed `pattern` (int)
* Removed `preset` (int)
* Removed `bucketedEntityType` (int)

FloatAttributeData:
* Added `constraintMin` (Float)
* Added `constraintMax` (Float)
* Added `operation` (FloatAttributeOperation)
* Added `value` (float)

FloatOverride:
* Added `type` (UpdateType)
* Added `value` (float)

FloatRange:
* Added `min` (Float)
* Added `max` (Float)

FullContainerName:
* Renamed `dynamicId` to `dynamicID` (Integer)
* Added `containerName` (ContainerEnumName)
* Removed `container` (ContainerSlotType)

FurnaceOptions:
* Added `layout` (FurnaceLayout)
* Added `leftFurnaceTab` (FurnaceLeftTabIndex)
* Added `filtering` (boolean)

FurnaceRecipeData:
* Removed `type` (CraftingDataType)
* Removed `result` (ItemData)
* Removed `tag` (String)
* Removed `inputId` (int)
* Removed `inputData` (int)

FurnaceRecipePayload:
* Added `result` (ItemData)
* Added `tag` (String)
* Added `inputId` (int)
* Added `auxValue` (int)

GameRule:
* Added `ruleValue` (Object)
* Added `ruleName` (String)
* Added `ruleCanBeModified` (boolean)

GameRulesChangedPacketData:
* Added `rulesList` (List<GameRule>)

GatheringsConfig:
* Added `experienceName` (String)
* Added `worldName` (String)
* Added `creatorId` (String)
* Added `scenarioId` (String)
* Added `serverId` (String)
* Added `experienceId` (UUID)
* Added `worldId` (UUID)
* Added `targetId` (UUID)

ImageData:
* Removed `EMPTY` (ImageData)
* Removed `image` (byte[])
* Removed `PIXEL_SIZE` (int)
* Removed `SINGLE_SKIN_SIZE` (int)
* Removed `DOUBLE_SKIN_SIZE` (int)
* Removed `SKIN_128_64_SIZE` (int)
* Removed `SKIN_128_128_SIZE` (int)
* Removed `SKIN_PERSONA_SIZE` (int)
* Removed `ANIMATION_SIZE` (int)
* Removed `width` (int)
* Removed `height` (int)

InitializeRegistryData:
* Added `clockData` (List<WorldClockData>)

IntOverride:
* Added `type` (UpdateType)
* Added `value` (int)

Interaction:
* Added `interactionType` (InteractionType)
* Added `interactionActorType` (int)
* Added `interactionActorVariant` (int)
* Added `interactionActorColor` (int)
* Added `interactedEntityID` (long)

InventoryAction:
* Added `source` (InventorySource)
* Added `fromItem` (ItemData)
* Added `toItem` (ItemData)
* Added `slot` (int)

InventoryActionData:
* Removed `source` (InventorySource)
* Removed `fromItem` (ItemData)
* Removed `toItem` (ItemData)
* Removed `slot` (int)
* Removed `stackNetworkId` (int)

InventoryMismatchData:
* Added `actions` (InventoryTransaction)

InventoryOptions:
* Added `layoutInv` (InventoryLayout)
* Added `layoutCraft` (InventoryLayout)
* Added `leftInventoryTab` (InventoryLeftTabIndex)
* Added `rightInventoryTab` (InventoryRightTabIndex)
* Added `filtering` (boolean)

InventorySource:
* Added `containerID` (Integer)
* Added `bitFlags` (InventorySourceFlags)
* Added `sourceType` (InventorySourceType)

InventoryTransaction:
* Added `actions` (List<InventoryAction>)

ItemDescriptorWithCount:
* Removed `descriptor` (ItemDescriptor)
* Removed `EMPTY` (ItemDescriptorWithCount)
* Removed `count` (int)

ItemEnchantOption:
* Added `enchants` (ItemEnchants)
* Added `enchantNetId` (RecipeNetId)
* Added `enchantName` (String)
* Added `cost` (int)

ItemEnchants:
* Added `itemEnchants` (List<List<EnchantmentInstance>>)
* Added `slot` (int)

ItemReleaseInventoryTransaction:
* Added `hand` (HandSlot)
* Added `actions` (InventoryTransaction)
* Added `item` (ItemData)
* Added `actionType` (ItemReleaseActionType)
* Added `fromPosition` (Vector3f)
* Added `slot` (int)

ItemStackLegacyRequestId:
* Added `ID` (int)

ItemStackNetId:
* Added `ID` (int)

ItemStackRequest:
* Added `clientRequestId` (ItemStackRequestId)
* Added `actions` (List<Object>)
* Added `stringsToFilter` (List<String>)
* Added `stringsToFilterOrigin` (TextProcessingEventOrigin)

ItemStackRequestBeaconPaymentAction:
* Added `actionType` (ItemStackRequestActionType)
* Added `primaryEffectId` (int)
* Added `secondaryEffectId` (int)

ItemStackRequestConsumeAction:
* Added `actionType` (ItemStackRequestActionType)
* Added `source` (ItemStackRequestSlotInfo)
* Added `amount` (int)

ItemStackRequestCraftCreativeAction:
* Added `creativeItemNetId` (CreativeItemNetId)
* Added `actionType` (ItemStackRequestActionType)
* Added `numberOfRequestedCrafts` (int)

ItemStackRequestCraftLoomAction:
* Added `actionType` (ItemStackRequestActionType)
* Added `patternNameId` (String)
* Added `numCrafts` (int)

ItemStackRequestCraftNonImplementedDeprecatedAction:
* Added `actionType` (ItemStackRequestActionType)

ItemStackRequestCraftRecipeAction:
* Added `actionType` (ItemStackRequestActionType)
* Added `recipeNetId` (RecipeNetId)
* Added `numberOfRequestedCrafts` (int)

ItemStackRequestCraftRecipeAutoAction:
* Added `actionType` (ItemStackRequestActionType)
* Added `ingredients` (List<RecipeIngredient>)
* Added `recipeNetId` (RecipeNetId)
* Added `numberOfRequestedCrafts` (int)
* Added `timesCrafted` (int)

ItemStackRequestCraftRecipeOptionalAction:
* Added `actionType` (ItemStackRequestActionType)
* Added `recipeNetId` (RecipeNetId)
* Added `filteredStringIndex` (int)

ItemStackRequestCraftRepairAndDisenchantAction:
* Added `actionType` (ItemStackRequestActionType)
* Added `recipeNetId` (RecipeNetId)
* Added `numberOfRequestedCrafts` (int)
* Added `repairCost` (int)

ItemStackRequestCraftResultsDeprecatedAction:
* Added `actionType` (ItemStackRequestActionType)
* Added `craftResults` (List<ItemData>)
* Added `numCrafts` (int)

ItemStackRequestCreateAction:
* Added `actionType` (ItemStackRequestActionType)
* Added `resultsIndex` (int)

ItemStackRequestDestroyAction:
* Added `actionType` (ItemStackRequestActionType)
* Added `source` (ItemStackRequestSlotInfo)
* Added `amount` (int)

ItemStackRequestDropAction:
* Added `actionType` (ItemStackRequestActionType)
* Added `source` (ItemStackRequestSlotInfo)
* Added `randomly` (boolean)
* Added `amount` (int)

ItemStackRequestId:
* Added `ID` (int)

ItemStackRequestLabTableCombineAction:
* Added `actionType` (ItemStackRequestActionType)

ItemStackRequestMineBlockAction:
* Added `actionType` (ItemStackRequestActionType)
* Added `slot` (int)
* Added `predictedDurability` (int)
* Added `netIdVariant` (int)

ItemStackRequestNetworkItemInstanceDescriptor:
* Added `itemDescriptor` (Object)
* Added `userDataBuffer` (String)
* Added `stackSize` (int)
* Added `blockRuntimeId` (int)

ItemStackRequestPlaceAction:
* Added `actionType` (ItemStackRequestActionType)
* Added `source` (ItemStackRequestSlotInfo)
* Added `destination` (ItemStackRequestSlotInfo)
* Added `amount` (int)

ItemStackRequestSlotInfo:
* Added `containerEnumName` (ContainerEnumName)
* Added `fullContainerName` (FullContainerName)
* Added `slot` (int)
* Added `netIdVariant` (int)

ItemStackRequestSwapAction:
* Added `actionType` (ItemStackRequestActionType)
* Added `source` (ItemStackRequestSlotInfo)
* Added `destination` (ItemStackRequestSlotInfo)

ItemStackRequestTakeAction:
* Added `actionType` (ItemStackRequestActionType)
* Added `source` (ItemStackRequestSlotInfo)
* Added `destination` (ItemStackRequestSlotInfo)
* Added `amount` (int)

ItemStackResponseContainerInfo:
* Added `fullContainerName` (FullContainerName)
* Added `slots` (List<ItemStackResponseSlotInfo>)

ItemStackResponseInfo:
* Added `result` (ItemStackNetResult)
* Added `clientRequestId` (ItemStackRequestId)
* Added `containers` (List<ItemStackResponseContainerInfo>)

ItemStackResponseSlot:
* Removed `customName` (@NonNull String)
* Removed `filteredCustomName` (String)
* Removed `slot` (int)
* Removed `hotbarSlot` (int)
* Removed `count` (int)
* Removed `stackNetworkId` (int)
* Removed `durabilityCorrection` (int)

ItemStackResponseSlotInfo:
* Added `itemStackNetId` (ItemStackNetId)
* Added `customName` (RedactableString)
* Added `requestedSlot` (int)
* Added `slot` (int)
* Added `amount` (int)
* Added `durabilityCorrection` (int)

ItemUseInventoryTransaction:
* Added `targetBlockId` (BlockDefinition)
* Added `hand` (HandSlot)
* Added `actions` (InventoryTransaction)
* Added `item` (ItemData)
* Added `actionType` (ItemUseActionType)
* Added `clientCooldownState` (ItemUseClientCooldownState)
* Added `clientInteractPrediction` (ItemUsePredictedResult)
* Added `triggerType` (ItemUseTriggerType)
* Added `fromPosition` (Vector3f)
* Added `clickPosition` (Vector3f)
* Added `position` (Vector3i)
* Added `face` (int)
* Added `slot` (int)

ItemUseOnActorInventoryTransaction:
* Added `hand` (HandSlot)
* Added `actions` (InventoryTransaction)
* Added `item` (ItemData)
* Added `actionType` (ItemUseOnActorActionType)
* Added `fromPosition` (Vector3f)
* Added `hitPosition` (Vector3f)
* Added `slot` (int)
* Added `runtimeId` (long)

ItemUsed:
* Added `itemId` (int)
* Added `itemAux` (int)
* Added `useMethod` (int)
* Added `count` (int)

ItemUsedEventData:
* Removed `itemAux` (int)
* Removed `useMethod` (int)
* Removed `useCount` (int)
* Removed `itemId` (short)

LegacySetItemSlotData:
* Removed `slots` (byte[])
* Removed `containerId` (int)

LegacySetSlot:
* Added `containerEnum` (ContainerEnumName)
* Added `slots` (byte[])

LevelSettings:
* Added `chatRestrictionLevel` (ChatRestrictionLevel)
* Added `gameDifficulty` (Difficulty)
* Added `editorWorldType` (EditorWorldType)
* Added `eduSharedUriResource` (EduSharedUriResource)
* Added `educationEditionOffer` (EducationEditionOffer)
* Added `experiments` (Experiments)
* Added `xboxLiveBroadcastSetting` (GamePublishSetting)
* Added `platformBroadcastSetting` (GamePublishSetting)
* Added `ruleData` (GameRulesChangedPacketData)
* Added `gameType` (GameType)
* Added `generatorType` (GeneratorType)
* Added `overrideForceExperimentalGameplay` (OptionalBoolean)
* Added `playerPermissions` (PlayerPermissionLevel)
* Added `serverEditorConnectionPolicy` (ServerEditorConnectionPolicy)
* Added `spawnSettings` (SpawnSettings)
* Added `educationProductID` (String)
* Added `baseGameVersion` (String)
* Added `serverId` (String)
* Added `worldId` (String)
* Added `scenarioId` (String)
* Added `ownerId` (String)
* Added `defaultSpawnBlockPosition` (Vector3i)
* Added `isHardcore` (boolean)
* Added `achievementsDisabled` (boolean)
* Added `isCreatedInEditor` (boolean)
* Added `isExportedFromEditor` (boolean)
* Added `educationFeaturesEnabled` (boolean)
* Added `hasConfirmedPlatformLockedContent` (boolean)
* Added `multiplayerGameIntent` (boolean)
* Added `lanBroadcastIntent` (boolean)
* Added `commandsEnabled` (boolean)
* Added `texturePacksRequired` (boolean)
* Added `hasBonusChestEnabled` (boolean)
* Added `startWithMapEnabled` (boolean)
* Added `hasLockedBehaviorPack` (boolean)
* Added `hasLockedResourcePack` (boolean)
* Added `isFromLockedTemplate` (boolean)
* Added `useMsaGamertagsOnly` (boolean)
* Added `isFromWorldTemplate` (boolean)
* Added `isWorldTemplateOptionLocked` (boolean)
* Added `onlySpawnV1Villagers` (boolean)
* Added `personaDisabled` (boolean)
* Added `customSkinsDisabled` (boolean)
* Added `emoteChatMuted` (boolean)
* Added `netherType` (boolean)
* Added `disablePlayerInteractions` (boolean)
* Added `allowAnonymousBlockDropsInEditorWorlds` (boolean)
* Added `trustingPlayers` (boolean)
* Added `rainLevel` (float)
* Added `lightningLevel` (float)
* Added `dayCycleStopTime` (int)
* Added `serverChunkTickRange` (int)
* Added `limitedWorldWidth` (int)
* Added `limitedWorldDepth` (int)
* Added `seed` (long)

LineDataPayload:
* Added `lineEndLocation` (Vector3f)

LocatorBarWaypointPayload:
* Added `actionFlag` (ServerWaypointGroupAction)
* Added `serverWaypointPayload` (ServerWaypointPayload)
* Added `groupHandle` (WaypointGroupWaypointHandle)

MapDecoration:
* Added `color` (Color)
* Added `imageType` (MapDecorationType)
* Added `label` (String)
* Added `rotation` (int)
* Added `x` (int)
* Added `y` (int)

MapDecoration:
* Removed `label` (String)
* Removed `image` (int)
* Removed `rotation` (int)
* Removed `xOffset` (int)
* Removed `yOffset` (int)
* Removed `color` (int)

MapItemTrackedActorUniqueId:
* Added `entityID` (Long)
* Added `type` (MapItemTrackedActorType)
* Added `blockPosition` (Vector3i)

MaterialReducerDataEntry:
* Added `itemIdsAndCounts` (List<MaterialReducerEntryOutput>)
* Added `fromItemKey` (int)

MaterialReducerEntryOutput:
* Added `itemId` (int)
* Added `itemCount` (int)

MemoryCategoryCounter:
* Added `category` (MemoryCategory)
* Added `currentBytes` (long)

MessageAndParams:
* Added `message` (CharSequence)
* Added `parameterList` (List<String>)

MessageOnly:
* Added `message` (CharSequence)

MissingBlobData:
* Added `blobData` (ByteBuf)
* Added `blobId` (long)

MobBorn:
* Added `bornBabyEntityType` (int)
* Added `bornBabyEntityVariant` (int)
* Added `bornBabyColor` (int)

MobBornEventData:
* Removed `entityType` (int)
* Removed `variant` (int)
* Removed `color` (int)

MobKilledEventData:
* Removed `villagerDisplayName` (String)
* Removed `killerEntityType` (int)
* Removed `entityDamageCause` (int)
* Removed `villagerTradeTier` (int)
* Removed `killerUniqueEntityId` (long)
* Removed `victimUniqueEntityId` (long)

MoveActorAbsoluteData:
* Added `pos` (Vector3f)
* Added `rotation` (Vector3f)
* Added `onGround` (boolean)
* Added `teleported` (boolean)
* Added `forceMove` (boolean)
* Added `forceCompletion` (boolean)
* Added `actorRuntimeID` (long)

MoveActorDeltaData:
* Added `newPositionX` (Float)
* Added `newPositionY` (Float)
* Added `newPositionZ` (Float)
* Added `rotationX` (Float)
* Added `rotationY` (Float)
* Added `rotationYHead` (Float)
* Added `isOnGround` (boolean)
* Added `forceMove` (boolean)
* Added `forceMoveLocalEntity` (boolean)
* Added `forceCompletion` (boolean)
* Added `actorRuntimeID` (long)
* Added `ticks` (long)

MovePlayerTeleportData:
* Added `teleportationCause` (int)
* Added `sourceActorType` (int)

MovementAnomaly:
* Added `cheatingScore` (float)
* Added `averagePositionDelta` (float)
* Added `totalPositionDelta` (float)
* Added `minPositionDelta` (float)
* Added `maxPositionDelta` (float)
* Added `eventType` (int)

MovementAnomalyEventData:
* Removed `cheatingScore` (float)
* Removed `averagePositionDelta` (float)
* Removed `totalPositionDelta` (float)
* Removed `minPositionDelta` (float)
* Removed `maxPositionDelta` (float)
* Removed `eventType` (int)

MovementAttributesComponent:
* Added `movementSpeed` (float)
* Added `underwaterMovementSpeed` (float)
* Added `lavaMovementSpeed` (float)
* Added `jumpStrength` (float)
* Added `health` (float)
* Added `hunger` (float)
* Added `frictionModifier` (float)
* Added `bounciness` (float)
* Added `airDragModifier` (float)

MovementCorrected:
* Added `positionDelta` (float)
* Added `cheatingScore` (float)
* Added `scoreThreshold` (float)
* Added `distanceThreshold` (float)
* Added `durationThreshold` (int)

MovementCorrectedEventData:
* Removed `positionDelta` (float)
* Removed `cheatingScore` (float)
* Removed `scoreThreshold` (float)
* Removed `distanceThreshold` (float)
* Removed `durationThreshold` (int)

MultiRecipeData:
* Removed `uuid` (UUID)
* Removed `netId` (int)

MultiRecipePayload:
* Added `netId` (RecipeNetId)
* Added `multiRecipeUUID` (UUID)

NetworkPermissions:
* Added `serverAuthSoundEnabled` (boolean)

NetworkPermissions:
* Removed `DEFAULT` (NetworkPermissions)

NoiseAlignment:
* Added `type` (NoiseAlignmentType)
* Added `value` (int)

NoiseBlockSpecifier:
* Added `noise` (String)
* Added `threshold` (float)
* Added `rangeMin` (float)
* Added `rangeMax` (float)
* Added `block` (int)

NoiseDescriptor:
* Added `amplitudes` (List<Float>)
* Added `name` (String)
* Added `firstOctave` (int)

NoiseTransitionAttributeData:
* Added `settings` (NoiseTransitionSettingsData)
* Added `fromAttribute` (Object)
* Added `toAttribute` (Object)

NoiseTransitionSettingsData:
* Added `easing` (EasingFunction)
* Added `noiseAlignment` (NoiseAlignment)
* Added `clockName` (String)
* Added `noiseName` (String)
* Added `totalTransitionTicks` (int)
* Added `currentTransitionTicks` (int)
* Added `localTransitionTicks` (int)

NormalTransactionData:
* Added `actions` (InventoryTransaction)

POICauldronUsed:
* Added `blockInteractionType` (POIBlockInteractionType)
* Added `itemId` (int)

PackIdVersion:
* Added `packVersion` (String)
* Added `packUUID` (UUID)

PackInfoData:
* Added `contentIdentity` (ContentIdentity)
* Added `packIdVersion` (PackIdVersion)
* Added `contentKey` (String)
* Added `subpackName` (String)
* Added `cdnURL` (String)
* Added `hasScripts` (boolean)
* Added `isAddonPack` (boolean)
* Added `isRayTracingCapable` (boolean)
* Added `packSize` (long)

PackInstanceId:
* Added `packID` (String)
* Added `version` (String)
* Added `subPackName` (String)

PackedItemUseLegacyInventoryTransaction:
* Added `legacyRequestID` (ItemStackLegacyRequestId)
* Added `transaction` (ItemUseInventoryTransaction)
* Added `actions` (List<InventoryAction>)
* Added `legacySetItemSlots` (List<LegacySetSlot>)

PatternRemoved:
* Added `itemId` (int)
* Added `auxValue` (int)
* Added `patternsSize` (int)
* Added `patternIndex` (int)
* Added `patternColor` (int)

PatternRemovedEventData:
* Removed `itemId` (int)
* Removed `auxValue` (int)
* Removed `patternsSize` (int)
* Removed `patternIndex` (int)
* Removed `patternColor` (int)

PersonaPieceData:
* Removed `id` (String)
* Removed `type` (String)
* Removed `packId` (String)
* Removed `productId` (String)
* Removed `isDefault` (boolean)

PersonaPieceTintData:
* Removed `colors` (List<String>)
* Removed `type` (String)

PetDied:
* Added `killedByOwner` (boolean)
* Added `damageSource` (int)
* Added `petEntityType` (int)
* Added `killerActorID` (long)
* Added `petActorID` (long)

PetDiedEventData:
* Removed `ownerKilled` (boolean)
* Removed `entityDamageCause` (int)
* Removed `petEntityType` (int)
* Removed `killerUniqueEntityId` (long)
* Removed `petUniqueEntityId` (long)

PiglinBarter:
* Added `wasTargetingBarteringPlayer` (boolean)
* Added `itemId` (int)

PiglinBarterEventData:
* Removed `definition` (ItemDefinition)
* Removed `targetingPlayer` (boolean)

PlayerBlockActionData:
* Added `playerActionType` (PlayerActionType)
* Added `position` (Vector3i)
* Added `facing` (int)

PlayerDiedEventData:
* Removed `inRaid` (boolean)
* Removed `attackerEntityId` (int)
* Removed `attackerVariant` (int)
* Removed `entityDamageCause` (int)

PlayerListAddEntry:
* Added `buildPlatform` (BuildPlatform)
* Added `serializedSkin` (SerializedSkin)
* Added `playerName` (String)
* Added `xblXUID` (String)
* Added `playFabID` (String)
* Added `platformOnlineID` (String)
* Added `uuid` (UUID)
* Added `isTeacher` (boolean)
* Added `isHost` (boolean)
* Added `isTrustedSkin` (boolean)
* Added `isSubClient` (boolean)
* Added `playerColor` (int)
* Added `actorUniqueID` (long)

PlayerListRemoveEntry:
* Added `uuid` (UUID)

PlayerPartyInfo:
* Added `partyId` (String)
* Added `isPartyLeader` (boolean)

PlayerWaxedOrUnwaxedCopper:
* Added `playerWaxedOrUnwaxedCopperBlockID` (int)

PortalBuiltEventData:
* Removed `dimensionId` (int)

PortalCreated:
* Added `dimensionID` (int)

PortalUsed:
* Added `sourceDimensionID` (int)
* Added `targetDimensionID` (int)

PortalUsedEventData:
* Removed `fromDimensionId` (int)
* Removed `toDimensionId` (int)

PosOption:
* Added `pos` (Vector3f)

PotionMixData:
* Removed `inputId` (int)
* Removed `inputMeta` (int)
* Removed `reagentId` (int)
* Removed `reagentMeta` (int)
* Removed `outputId` (int)
* Removed `outputMeta` (int)

PotionMixDataEntry:
* Added `fromPotionId` (int)
* Added `fromItemAux` (int)
* Added `reagentItemId` (int)
* Added `reagentItemAux` (int)
* Added `toPotionId` (int)
* Added `toItemAux` (int)

PresenceConfig:
* Added `experienceName` (String)
* Added `worldName` (String)
* Added `richPresenceId` (String)

PrimitiveShapeDataPayload:
* Added `dimension` (DimensionType)
* Added `scale` (Float)
* Added `totalTimeLeft` (Float)
* Added `maximumRenderDistance` (Float)
* Added `color` (Integer)
* Added `attachedToEntityID` (Long)
* Added `extraShapeData` (Object)
* Added `shapeType` (ScriptPrimitiveShapeType)
* Added `location` (Vector3f)
* Added `rotation` (Vector3f)
* Added `networkId` (long)

PropertySyncData:
* Added `floatEntriesList` (List<PropertySyncFloatEntry>)
* Added `intEntriesList` (List<PropertySyncIntEntry>)

PropertySyncFloatEntry:
* Added `data` (float)
* Added `propertyIndex` (int)

PropertySyncIntEntry:
* Added `propertyIndex` (int)
* Added `data` (int)

PyramidDataPayload:
* Added `depth` (Float)
* Added `width` (float)
* Added `height` (float)

RaidUpdate:
* Added `success` (boolean)
* Added `currentWave` (int)
* Added `totalWaves` (int)

RaidUpdateEventData:
* Removed `winner` (boolean)
* Removed `currentWave` (int)
* Removed `totalWaves` (int)

RecipeIngredient:
* Added `descriptor` (ItemDescriptor)
* Added `EMPTY` (RecipeIngredient)
* Added `stackSize` (int)

RecipeIngredient:
* Removed `EMPTY` (RecipeIngredient)
* Removed `id` (int)
* Removed `auxValue` (int)
* Removed `stackSize` (int)

RecipeUnlockingRequirement:
* Added `INVALID` (RecipeUnlockingRequirement)

RedactableString:
* Added `unredacted` (String)
* Added `redacted` (String)

RemoveEnvironmentAttributesData:
* Added `attributeLayerDimension` (DimensionType)
* Added `attributes` (List<String>)
* Added `attributeLayerName` (String)

RemoveOverride:
* Added `type` (UpdateType)

RemoveScore:
* Added `scoreboardId` (ScoreboardId)
* Added `objectiveName` (String)
* Added `scoreValue` (int)

RemoveTimeMarkerData:
* Added `timeMarkerIds` (List<Long>)
* Added `clockId` (long)

ReplacePage:
* Added `pageText` (String)
* Added `photoName` (String)
* Added `pageIndex` (int)

ResourcePackClientResponseCancel:
* Added `responseType` (ResourcePackResponse)

ResourcePackClientResponseDownloading:
* Added `downloadingPacks` (List<String>)
* Added `responseType` (ResourcePackResponse)

ResourcePackClientResponseDownloadingFinished:
* Added `responseType` (ResourcePackResponse)

ResourcePackClientResponseResourcePackStackFinished:
* Added `responseType` (ResourcePackResponse)

RotOption:
* Added `x` (float)
* Added `y` (float)

ScoreboardIdentityPacketInfo:
* Added `uuid` (UUID)
* Added `scoreboardId` (long)
* Added `playerUniqueId` (long)

SeekTo:
* Added `seconds` (float)

SerializableCells:
* Added `storage` (List<Integer>)
* Added `xSize` (int)
* Added `ySize` (int)
* Added `zSize` (int)

SerializableVoxelShape:
* Added `xCoordinates` (List<Float>)
* Added `yCoordinates` (List<Float>)
* Added `zCoordinates` (List<Float>)
* Added `cells` (SerializableCells)

SerializedAbilitiesData:
* Added `commandPermissions` (CommandPermissionLevel)
* Added `layers` (List<SerializedAbilitiesDataSerializedLayer>)
* Added `playerPermissions` (PlayerPermissionLevel)
* Added `targetPlayerRawId` (long)

SerializedAbilitiesDataSerializedLayer:
* Added `serializedLayer` (SerializedLayer)
* Added `abilitiesSet` (Set<AbilitiesIndex>)
* Added `abilityValues` (Set<AbilitiesIndex>)
* Added `flySpeed` (float)
* Added `verticalFlySpeed` (float)
* Added `walkSpeed` (float)

SerializedNoiseBlockSpecifier:
* Added `block` (BlockDefinition)
* Added `threshold` (Float)
* Added `range` (FloatRange)
* Added `noise` (String)

SerializedPersonaPieceHandle:
* Added `pieceType` (PieceType)
* Added `pieceId` (String)
* Added `productId` (String)
* Added `packId` (UUID)
* Added `isDefaultPiece` (boolean)

SerializedSkin:
* Added `armSize` (ArmSizeType)
* Added `animatedImageData` (List<AnimatedImageData>)
* Added `personaPieces` (List<SerializedPersonaPieceHandle>)
* Added `pieceTintColors` (Map<PieceType, TintMapColor>)
* Added `imageData` (SkinImage)
* Added `capeImageData` (SkinImage)
* Added `ID` (String)
* Added `playFabID` (String)
* Added `resourcePatch` (String)
* Added `geometryData` (String)
* Added `geometryDataMinEngineVersion` (String)
* Added `animationData` (String)
* Added `capeID` (String)
* Added `fullID` (String)
* Added `profileHash` (String)
* Added `trustedSkinFlag` (TrustedSkinFlag)
* Added `isPremium` (boolean)
* Added `isPersona` (boolean)
* Added `isPersonaCapeOnClassicSkin` (boolean)
* Added `isPrimaryUser` (boolean)
* Added `overridesPlayerAppearance` (boolean)
* Added `skinColor` (int)
* Removed `skinData` (ImageData)
* Removed `capeData` (ImageData)
* Removed `skinData` (ImageData)
* Removed `capeData` (ImageData)
* Removed `animations` (List<AnimationData>)
* Removed `animations` (List<AnimationData>)
* Removed `personaPieces` (List<PersonaPieceData>)
* Removed `personaPieces` (List<PersonaPieceData>)
* Removed `tintColors` (List<PersonaPieceTintData>)
* Removed `tintColors` (List<PersonaPieceTintData>)
* Removed `skinId` (String)
* Removed `playFabId` (String)
* Removed `geometryName` (String)
* Removed `skinResourcePatch` (String)
* Removed `geometryData` (String)
* Removed `geometryDataEngineVersion` (String)
* Removed `animationData` (String)
* Removed `capeId` (String)
* Removed `fullSkinId` (String)
* Removed `armSize` (String)
* Removed `skinColor` (String)
* Removed `skinId` (String)
* Removed `playFabId` (String)
* Removed `geometryName` (String)
* Removed `skinResourcePatch` (String)
* Removed `geometryData` (String)
* Removed `animationData` (String)
* Removed `capeId` (String)
* Removed `fullSkinId` (String)
* Removed `armSize` (String)
* Removed `skinColor` (String)
* Removed `geometryDataEngineVersion` (String)
* Removed `premium` (boolean)
* Removed `persona` (boolean)
* Removed `capeOnClassic` (boolean)
* Removed `primaryUser` (boolean)
* Removed `overridingPlayerAppearance` (boolean)
* Removed `premium` (boolean)
* Removed `persona` (boolean)
* Removed `capeOnClassic` (boolean)
* Removed `primaryUser` (boolean)
* Removed `overridingPlayerAppearance` (boolean)

ServerBlockProperty:
* Added `blockDefinition` (NbtMap)
* Added `blockName` (String)

ServerConfig:
* Added `clientStoreEntryPoint` (ClientStoreEntryPointConfig)
* Added `gathering` (GatheringsConfig)
* Added `presence` (PresenceConfig)

ServerSoundHandle:
* Added `serverSoundHandle` (long)

ServerTelemetryData:
* Added `serverId` (String)
* Added `scenarioId` (String)
* Added `worldId` (String)
* Added `ownerId` (String)

ServerWaypointPayload:
* Added `color` (Color)
* Added `actorUniqueID` (Long)
* Added `isVisible` (OptionalBoolean)
* Added `clientPositionAuthority` (OptionalBoolean)
* Added `texturePath` (String)
* Added `textureId` (VanillaWaypointManagerConstants.ImageType)
* Added `iconSize` (Vector2f)
* Added `worldPosition` (WorldPosition)
* Added `updateFlag` (int)

SetPitch:
* Added `pitch` (float)

SetVolume:
* Added `volume` (float)

ShapedRecipeData:
* Removed `type` (CraftingDataType)
* Removed `results` (List<ItemData>)
* Removed `ingredients` (List<ItemDescriptorWithCount>)
* Removed `requirement` (RecipeUnlockingRequirement)
* Removed `id` (String)
* Removed `tag` (String)
* Removed `uuid` (UUID)
* Removed `assumeSymetry` (boolean)
* Removed `width` (int)
* Removed `height` (int)
* Removed `priority` (int)
* Removed `netId` (int)

ShapedRecipePayload:
* Added `results` (List<ItemData>)
* Added `ingredients` (List<RecipeIngredient>)
* Added `netId` (RecipeNetId)
* Added `unlockingRequirement` (RecipeUnlockingRequirement)
* Added `recipeId` (String)
* Added `tag` (String)
* Added `uuid` (UUID)
* Added `assumeSymmetry` (boolean)
* Added `width` (int)
* Added `height` (int)
* Added `priority` (int)

ShapelessRecipeData:
* Removed `type` (CraftingDataType)
* Removed `results` (List<ItemData>)
* Removed `ingredients` (List<ItemDescriptorWithCount>)
* Removed `requirement` (RecipeUnlockingRequirement)
* Removed `id` (String)
* Removed `tag` (String)
* Removed `uuid` (UUID)
* Removed `priority` (int)
* Removed `netId` (int)

ShapelessRecipePayload:
* Added `results` (List<ItemData>)
* Added `ingredients` (List<RecipeIngredient>)
* Added `netId` (RecipeNetId)
* Added `unlockingRequirement` (RecipeUnlockingRequirement)
* Added `recipeId` (String)
* Added `tag` (String)
* Added `uuid` (UUID)
* Added `priority` (int)

SkinImage:
* Added `imageBytes` (List<Integer>)
* Added `EMPTY` (SkinImage)
* Added `PIXEL_SIZE` (int)
* Added `SINGLE_SKIN_SIZE` (int)
* Added `DOUBLE_SKIN_SIZE` (int)
* Added `SKIN_128_64_SIZE` (int)
* Added `SKIN_128_128_SIZE` (int)
* Added `SKIN_PERSONA_SIZE` (int)
* Added `ANIMATION_SIZE` (int)
* Added `width` (int)
* Added `height` (int)

SlashCommand:
* Added `commandName` (String)
* Added `errorList` (String)
* Added `successCount` (int)
* Added `errorCount` (int)

SlashCommandExecutedEventData:
* Removed `outputMessages` (List<String>)
* Removed `commandName` (String)
* Removed `successCount` (int)

SmithingTransformRecipeData:
* Removed `result` (ItemData)
* Removed `template` (ItemDescriptorWithCount)
* Removed `base` (ItemDescriptorWithCount)
* Removed `addition` (ItemDescriptorWithCount)
* Removed `id` (String)
* Removed `tag` (String)
* Removed `netId` (int)

SmithingTransformRecipePayload:
* Added `result` (ItemData)
* Added `templateIngredient` (RecipeIngredient)
* Added `baseIngredient` (RecipeIngredient)
* Added `additionIngredient` (RecipeIngredient)
* Added `netId` (RecipeNetId)
* Added `recipeId` (String)
* Added `tag` (String)

SmithingTrimRecipeData:
* Removed `base` (ItemDescriptorWithCount)
* Removed `addition` (ItemDescriptorWithCount)
* Removed `template` (ItemDescriptorWithCount)
* Removed `id` (String)
* Removed `tag` (String)
* Removed `netId` (int)

SmithingTrimRecipePayload:
* Added `templateIngredient` (RecipeIngredient)
* Added `baseIngredient` (RecipeIngredient)
* Added `additionIngredient` (RecipeIngredient)
* Added `netId` (RecipeNetId)
* Added `recipeId` (String)
* Added `tag` (String)

SneakCloseToSculkSensorEventData:
* Removed `INSTANCE` (SneakCloseToSculkSensorEventData)

SpawnSettings:
* Added `dimension` (DimensionType)
* Added `spawnBiomeType` (SpawnBiomeType)
* Added `userDefinedBiomeName` (String)

SphereDataPayload:
* Added `numSegments` (int)

SplineProgressOption:
* Added `keyFrameEasingFunc` (EasingFunction)
* Added `keyFrameValue` (float)
* Added `keyFrameTime` (float)

SplineRotationOption:
* Added `keyFrameEasingFunc` (EasingFunction)
* Added `keyFrameValue` (Vector3f)
* Added `keyFrameTime` (float)

StartVideoCapture:
* Added `filePrefix` (String)
* Added `frameRate` (int)

StriderRiddenInLavaInOverworldEventData:
* Removed `INSTANCE` (StriderRiddenInLavaInOverworldEventData)

StructureEditorData:
* Renamed `type` to `structureBlockType` (StructureBlockType)
* Renamed `settings` to `structureSettings` (StructureSettings)
* Added `structureName` (RedactableString)
* Added `dataField` (String)
* Added `redstoneSaveMode` (StructureRedstoneSaveMode)
* Added `shouldIncludePlayers` (boolean)
* Added `shouldShowBoundingBox` (boolean)
* Removed `name` (String)
* Removed `filteredName` (String)
* Removed `dataField` (String)
* Removed `redstoneSaveMode` (StructureRedstoneSaveMode)
* Removed `includingPlayers` (boolean)
* Removed `boundingBoxVisible` (boolean)

StructureSettings:
* Renamed `paletteName` to `structurePaletteName` (String)
* Renamed `pivot` to `rotationPivot` (Vector3f)
* Renamed `lastEditedByEntityId` to `lastEditPlayer` (long)
* Added `animationMode` (AnimationMode)
* Added `mirror` (Mirror)
* Added `rotation` (Rotation)
* Added `structureSize` (Vector3i)
* Added `structureOffset` (Vector3i)
* Added `shouldIgnoreEntities` (boolean)
* Added `shouldIgnoreBlocks` (boolean)
* Added `shouldAllowNonTickingPlayerAndTickingAreaChunks` (boolean)
* Added `animationSeconds` (float)
* Added `integrityValue` (float)
* Added `integritySeed` (int)
* Removed `animationMode` (StructureAnimationMode)
* Removed `mirror` (StructureMirror)
* Removed `rotation` (StructureRotation)
* Removed `size` (Vector3i)
* Removed `offset` (Vector3i)
* Removed `ignoringEntities` (boolean)
* Removed `ignoringBlocks` (boolean)
* Removed `nonTickingPlayersAndTickingAreasEnabled` (boolean)
* Removed `animationSeconds` (float)
* Removed `integrityValue` (float)
* Removed `integritySeed` (int)

SubChunkData:
* Removed `data` (ByteBuf)
* Removed `heightMapData` (ByteBuf)
* Removed `renderHeightMapData` (ByteBuf)
* Removed `heightMapType` (HeightMapDataType)
* Removed `renderHeightMapType` (HeightMapDataType)
* Removed `result` (SubChunkRequestResult)
* Removed `position` (Vector3i)
* Removed `cacheEnabled` (boolean)
* Removed `blobId` (long)

SubChunkHeightmapData:
* Added `subchunkHeightMap` (ByteBuf)
* Added `subchunkRenderHeightMap` (ByteBuf)
* Added `heightMapType` (HeightMapDataType)
* Added `renderHeightMapType` (HeightMapDataType)

SubChunkPacketData:
* Added `serializedSubChunk` (ByteBuf)
* Added `blobId` (Long)
* Added `heightMapData` (SubChunkHeightmapData)
* Added `subChunkRequestResult` (SubChunkRequestResult)
* Added `subChunkPosOffset` (Vector3i)

SwapPages:
* Added `pageIndex` (int)
* Added `swapWithIndex` (int)

SyncStateData:
* Added `clockData` (List<SyncWorldClockStateData>)

SyncWorldClockStateData:
* Added `isPaused` (boolean)
* Added `time` (int)
* Added `clockId` (long)

SyncedAttribute:
* Added `attributeName` (String)
* Added `minValue` (float)
* Added `currentValue` (float)
* Added `maxValue` (float)

SyncedPlayerMovementSettings:
* Added `authorityMode` (ServerAuthMovementMode)
* Added `serverAuthoritativeBlockBreaking` (boolean)
* Added `rewindHistorySize` (int)

SystemCategory:
* Added `categoryName` (String)
* Added `systemIndex` (long)

SystemDiagnosticTimingInfo:
* Added `displayName` (String)
* Added `percentOfTotal` (int)
* Added `systemIndex` (long)
* Added `timeInNs` (long)

TargetBlockHit:
* Added `redstoneLevel` (int)

TargetBlockHitEventData:
* Removed `redstoneLevel` (int)

TextDataPayload:
* Added `backgroundColor` (Integer)
* Added `text` (String)
* Added `useRotation` (boolean)
* Added `depthTest` (boolean)
* Added `showBackface` (boolean)
* Added `showTextBackface` (boolean)
* Added `lineGapHeight` (float)

TimeMarkerData:
* Added `period` (Integer)
* Added `name` (String)
* Added `time` (int)
* Added `id` (long)

TimeOption:
* Added `fadeInTime` (float)
* Added `holdTime` (float)
* Added `fadeOutTime` (float)

TintMapColor:
* Added `colors` (List<Integer>)

TokenPayload:
* Removed `type` (AuthType)
* Removed `token` (String)

TransitionAttributeData:
* Added `fromAttribute` (Object)
* Added `toAttribute` (Object)
* Added `settings` (TransitionSettingsData)

TransitionSettingsData:
* Added `easing` (EasingFunction)
* Added `clockName` (String)
* Added `totalTransitionTicks` (int)
* Added `currentTransitionTicks` (int)

TrimMaterial:
* Added `materialId` (String)
* Added `color` (String)
* Added `itemName` (String)

TrimMaterial:
* Removed `materialId` (String)
* Removed `color` (String)
* Removed `itemName` (String)

TrimPattern:
* Added `itemName` (String)
* Added `patternId` (String)

TrimPattern:
* Removed `itemName` (String)
* Removed `patternId` (String)

UnindexedBiomes:
* Removed `biomes` (Map<String, BiomeDefinitionData>)

UpdateAttributeLayerSettingsData:
* Added `attributesLayerSettings` (AttributeLayerSettings)
* Added `attributeLayerDimension` (DimensionType)
* Added `attributeLayerName` (String)

UpdateAttributeLayersData:
* Added `attributeLayers` (List<AttributeLayerData>)

UpdateEnvironmentAttributesData:
* Added `attributeLayerDimension` (DimensionType)
* Added `attributes` (List<EnvironmentAttributeData>)
* Added `attributeLayerName` (String)

UpdateSubChunkBlocksChangedInfo:
* Added `blocksChangedStandards` (List<UpdateSubChunkNetworkBlockInfo>)
* Added `blocksChangedExtras` (List<UpdateSubChunkNetworkBlockInfo>)

UpdateSubChunkNetworkBlockInfo:
* Added `definition` (BlockDefinition)
* Added `pos` (Vector3i)
* Added `updateFlags` (int)
* Added `syncMessageMessage` (int)
* Added `syncMessageEntityUniqueID` (long)

ViewOffsetOption:
* Added `x` (float)
* Added `y` (float)

VoxelShapesRegistryHandle:
* Added `value` (int)

WaypointGroupWaypointHandle:
* Added `uuid` (UUID)

WebSocketPacketData:
* Added `websocketServerURI` (String)

WhiskerScopeDataSummary:
* Added `label` (String)
* Added `indentation` (String)
* Added `totalHighCostNS` (long)
* Added `totalMidCostNS` (long)
* Added `totalLowCostNS` (long)

WorldClockData:
* Added `timeMarkers` (List<TimeMarkerData>)
* Added `name` (String)
* Added `isPaused` (boolean)
* Added `time` (int)
* Added `id` (long)

WorldPosition:
* Added `dimensionType` (DimensionType)
* Added `position` (Vector3f)


## New Enums

* AchievementIds (`org.cloudburstmc.protocol.bedrock.data.event.AchievementIds`)
* ActorBlockSyncMessageId (`org.cloudburstmc.protocol.bedrock.data.world.ActorBlockSyncMessageId`)
* ActorEvent (`org.cloudburstmc.protocol.bedrock.data.actor.ActorEvent`)
* ActorLinkType (`org.cloudburstmc.protocol.bedrock.data.actor.link.ActorLinkType`)
* ActorSwingSource (`org.cloudburstmc.protocol.bedrock.data.actor.ActorSwingSource`)
* ActorType (`org.cloudburstmc.protocol.bedrock.data.actor.ActorType`)
* AgentActionType (`org.cloudburstmc.protocol.bedrock.data.education.AgentActionType`)
* AgentAnimation (`org.cloudburstmc.protocol.bedrock.data.education.AgentAnimation`)
* AimAssistTargetMode (`org.cloudburstmc.protocol.bedrock.data.camera.aimassist.AimAssistTargetMode`)
* AnimationMode (`org.cloudburstmc.protocol.bedrock.data.structure.AnimationMode`)
* ArmSizeType (`org.cloudburstmc.protocol.bedrock.data.skin.ArmSizeType`)
* ArmorSlot (`org.cloudburstmc.protocol.bedrock.data.player.armor.ArmorSlot`)
* AttributeDataType (`org.cloudburstmc.protocol.bedrock.data.attribute.AttributeDataType`)
* AttributeLayerSettings (`org.cloudburstmc.protocol.bedrock.data.attribute.AttributeLayerSettings`)
* AttributeModifierOperation (`org.cloudburstmc.protocol.bedrock.data.actor.attribute.AttributeModifierOperation`)
* AttributeOperands (`org.cloudburstmc.protocol.bedrock.data.actor.attribute.AttributeOperands`)
* AudioListener (`org.cloudburstmc.protocol.bedrock.data.camera.AudioListener`)
* BookEditOperation (`org.cloudburstmc.protocol.bedrock.data.book.BookEditOperation`)
* BoolAttributeOperation (`org.cloudburstmc.protocol.bedrock.data.attribute.BoolAttributeOperation`)
* BossBarColor (`org.cloudburstmc.protocol.bedrock.data.boss.BossBarColor`)
* BossBarOverlay (`org.cloudburstmc.protocol.bedrock.data.boss.BossBarOverlay`)
* BossEventUpdateType (`org.cloudburstmc.protocol.bedrock.data.boss.BossEventUpdateType`)
* BuildPlatform (`org.cloudburstmc.protocol.bedrock.data.connection.BuildPlatform`)
* CameraAimAssistPresetsPacketOperation (`org.cloudburstmc.protocol.bedrock.data.camera.aimassist.CameraAimAssistPresetsPacketOperation`)
* CameraShakeAction (`org.cloudburstmc.protocol.bedrock.data.camera.CameraShakeAction`)
* CameraShakeType (`org.cloudburstmc.protocol.bedrock.data.camera.CameraShakeType`)
* CameraSplineType (`org.cloudburstmc.protocol.bedrock.data.camera.CameraSplineType`)
* ChatRestrictionLevel (`org.cloudburstmc.protocol.bedrock.data.text.ChatRestrictionLevel`)
* ClientCameraAimAssistPacketAction (`org.cloudburstmc.protocol.bedrock.data.camera.aimassist.ClientCameraAimAssistPacketAction`)
* ClientPlayMode (`org.cloudburstmc.protocol.bedrock.data.player.input.ClientPlayMode`)
* ClientboundAttributeLayerSyncDataType (`org.cloudburstmc.protocol.bedrock.data.attribute.ClientboundAttributeLayerSyncDataType`)
* CodeBuilderExecutionStateCodeStatus (`org.cloudburstmc.protocol.bedrock.data.education.CodeBuilderExecutionStateCodeStatus`)
* CodeBuilderStorageQueryOptionsCategory (`org.cloudburstmc.protocol.bedrock.data.education.CodeBuilderStorageQueryOptionsCategory`)
* CodeBuilderStorageQueryOptionsOperation (`org.cloudburstmc.protocol.bedrock.data.education.CodeBuilderStorageQueryOptionsOperation`)
* ColorAttributeOperation (`org.cloudburstmc.protocol.bedrock.data.attribute.ColorAttributeOperation`)
* CommandBlockMode (`org.cloudburstmc.protocol.bedrock.data.command.CommandBlockMode`)
* CommandBlockUpdateTargetType (`org.cloudburstmc.protocol.bedrock.data.command.CommandBlockUpdateTargetType`)
* CommandPermissionLevel (`org.cloudburstmc.protocol.bedrock.data.command.CommandPermissionLevel`)
* ContainerEnumName (`org.cloudburstmc.protocol.bedrock.data.inventory.ContainerEnumName`)
* ControlScheme (`org.cloudburstmc.protocol.bedrock.data.player.ControlScheme`)
* CoordinateEvaluationOrder (`org.cloudburstmc.protocol.bedrock.data.structure.CoordinateEvaluationOrder`)
* CraftingDataEntryType (`org.cloudburstmc.protocol.bedrock.data.recipe.CraftingDataEntryType`)
* CreativeCategory (`org.cloudburstmc.protocol.bedrock.data.item.creative.CreativeCategory`)
* CurrentCmdVersion (`org.cloudburstmc.protocol.bedrock.data.command.CurrentCmdVersion`)
* DataDrivenScreenClosedReason (`org.cloudburstmc.protocol.bedrock.data.form.DataDrivenScreenClosedReason`)
* DataStoreType (`org.cloudburstmc.protocol.bedrock.data.datastore.DataStoreType`)
* DataStoreUpdate (`org.cloudburstmc.protocol.bedrock.data.datastore.DataStoreUpdate`)
* DefaultGameType (`org.cloudburstmc.protocol.bedrock.data.world.DefaultGameType`)
* Difficulty (`org.cloudburstmc.protocol.bedrock.data.world.Difficulty`)
* Dimension (`org.cloudburstmc.protocol.bedrock.data.world.Dimension`)
* DimensionType (`org.cloudburstmc.protocol.bedrock.data.world.DimensionType`)
* DisconnectFailReason (`org.cloudburstmc.protocol.bedrock.data.connection.DisconnectFailReason`)
* DynamicValueType (`org.cloudburstmc.protocol.bedrock.data.datastore.DynamicValueType`)
* EasingFunction (`org.cloudburstmc.protocol.bedrock.data.camera.EasingFunction`)
* EditorWorldType (`org.cloudburstmc.protocol.bedrock.data.editor.EditorWorldType`)
* EducationEditionOffer (`org.cloudburstmc.protocol.bedrock.data.education.EducationEditionOffer`)
* EnchantType (`org.cloudburstmc.protocol.bedrock.data.item.EnchantType`)
* ExtraShapeDataType (`org.cloudburstmc.protocol.bedrock.data.shape.ExtraShapeDataType`)
* FloatAttributeOperation (`org.cloudburstmc.protocol.bedrock.data.attribute.FloatAttributeOperation`)
* FurnaceLayout (`org.cloudburstmc.protocol.bedrock.data.inventory.FurnaceLayout`)
* FurnaceLeftTabIndex (`org.cloudburstmc.protocol.bedrock.data.inventory.FurnaceLeftTabIndex`)
* GamePublishSetting (`org.cloudburstmc.protocol.bedrock.data.connection.GamePublishSetting`)
* GameType (`org.cloudburstmc.protocol.bedrock.data.world.GameType`)
* GeneratorType (`org.cloudburstmc.protocol.bedrock.data.world.GeneratorType`)
* GraphicsMode (`org.cloudburstmc.protocol.bedrock.data.connection.GraphicsMode`)
* GraphicsOverrideParameterType (`org.cloudburstmc.protocol.bedrock.data.misc.GraphicsOverrideParameterType`)
* HandSlot (`org.cloudburstmc.protocol.bedrock.data.player.HandSlot`)
* HeightMapDataType (`org.cloudburstmc.protocol.bedrock.data.chunk.HeightMapDataType`)
* HudElement (`org.cloudburstmc.protocol.bedrock.data.player.HudElement`)
* HudVisibility (`org.cloudburstmc.protocol.bedrock.data.player.HudVisibility`)
* InputMode (`org.cloudburstmc.protocol.bedrock.data.player.input.InputMode`)
* InteractionType (`org.cloudburstmc.protocol.bedrock.data.event.InteractionType`)
* InventoryLeftTabIndex (`org.cloudburstmc.protocol.bedrock.data.inventory.InventoryLeftTabIndex`)
* InventoryRightTabIndex (`org.cloudburstmc.protocol.bedrock.data.inventory.InventoryRightTabIndex`)
* InventorySourceFlags (`org.cloudburstmc.protocol.bedrock.data.inventory.InventorySourceFlags`)
* InventorySourceType (`org.cloudburstmc.protocol.bedrock.data.inventory.InventorySourceType`)
* ItemReleaseActionType (`org.cloudburstmc.protocol.bedrock.data.inventory.transaction.ItemReleaseActionType`)
* ItemStackNetResult (`org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.response.ItemStackNetResult`)
* ItemUseActionType (`org.cloudburstmc.protocol.bedrock.data.inventory.transaction.ItemUseActionType`)
* ItemUseClientCooldownState (`org.cloudburstmc.protocol.bedrock.data.inventory.transaction.ItemUseClientCooldownState`)
* ItemUseMethod (`org.cloudburstmc.protocol.bedrock.data.world.ItemUseMethod`)
* ItemUseOnActorActionType (`org.cloudburstmc.protocol.bedrock.data.inventory.transaction.ItemUseOnActorActionType`)
* ItemUsePredictedResult (`org.cloudburstmc.protocol.bedrock.data.inventory.transaction.ItemUsePredictedResult`)
* ItemUseTriggerType (`org.cloudburstmc.protocol.bedrock.data.inventory.transaction.ItemUseTriggerType`)
* ItemVersion (`org.cloudburstmc.protocol.bedrock.data.item.ItemVersion`)
* LabTableReactionType (`org.cloudburstmc.protocol.bedrock.data.education.LabTableReactionType`)
* LevelSoundEvent (`org.cloudburstmc.protocol.bedrock.data.sound.LevelSoundEvent`)
* LoadingScreenPacketType (`org.cloudburstmc.protocol.bedrock.data.connection.LoadingScreenPacketType`)
* MapDecorationType (`org.cloudburstmc.protocol.bedrock.data.map.MapDecorationType`)
* MapItemTrackedActorType (`org.cloudburstmc.protocol.bedrock.data.map.MapItemTrackedActorType`)
* MatchmakingState (`org.cloudburstmc.protocol.bedrock.data.connection.MatchmakingState`)
* MemoryCategory (`org.cloudburstmc.protocol.bedrock.data.diagnostics.MemoryCategory`)
* MemoryTier (`org.cloudburstmc.protocol.bedrock.data.connection.MemoryTier`)
* Mirror (`org.cloudburstmc.protocol.bedrock.data.structure.Mirror`)
* MobKilled (`org.cloudburstmc.protocol.bedrock.data.event.MobKilled`)
* ModalFormCancelReason (`org.cloudburstmc.protocol.bedrock.data.form.ModalFormCancelReason`)
* MolangVersion (`org.cloudburstmc.protocol.bedrock.data.misc.MolangVersion`)
* MovementEffectType (`org.cloudburstmc.protocol.bedrock.data.actor.MovementEffectType`)
* MultiplayerSettingsPacketType (`org.cloudburstmc.protocol.bedrock.data.connection.MultiplayerSettingsPacketType`)
* NewInteractionModel (`org.cloudburstmc.protocol.bedrock.data.player.input.NewInteractionModel`)
* NoiseAlignmentType (`org.cloudburstmc.protocol.bedrock.data.structure.NoiseAlignmentType`)
* NpcDialogueActionType (`org.cloudburstmc.protocol.bedrock.data.form.NpcDialogueActionType`)
* ObjectiveSortOrder (`org.cloudburstmc.protocol.bedrock.data.scoreboard.ObjectiveSortOrder`)
* POIBlockInteractionType (`org.cloudburstmc.protocol.bedrock.data.event.POIBlockInteractionType`)
* PackType (`org.cloudburstmc.protocol.bedrock.data.resourcepack.PackType`)
* PacketCompressionAlgorithm (`org.cloudburstmc.protocol.bedrock.data.connection.PacketCompressionAlgorithm`)
* PacketViolationSeverity (`org.cloudburstmc.protocol.bedrock.data.connection.PacketViolationSeverity`)
* PacketViolationType (`org.cloudburstmc.protocol.bedrock.data.connection.PacketViolationType`)
* PartyDestinationCookieIntent (`org.cloudburstmc.protocol.bedrock.data.connection.party.PartyDestinationCookieIntent`)
* PayloadType (`org.cloudburstmc.protocol.bedrock.data.debug.PayloadType`)
* PersonaAnimatedTextureType (`org.cloudburstmc.protocol.bedrock.data.skin.PersonaAnimatedTextureType`)
* PersonaAnimationExpression (`org.cloudburstmc.protocol.bedrock.data.skin.PersonaAnimationExpression`)
* PhotoType (`org.cloudburstmc.protocol.bedrock.data.education.PhotoType`)
* PieceType (`org.cloudburstmc.protocol.bedrock.data.skin.PieceType`)
* PlatformType (`org.cloudburstmc.protocol.bedrock.data.connection.PlatformType`)
* PlayStatus (`org.cloudburstmc.protocol.bedrock.data.player.PlayStatus`)
* PlayerActionType (`org.cloudburstmc.protocol.bedrock.data.player.input.PlayerActionType`)
* PlayerAuthInputData (`org.cloudburstmc.protocol.bedrock.data.player.input.PlayerAuthInputData`)
* PlayerDied (`org.cloudburstmc.protocol.bedrock.data.event.PlayerDied`)
* PlayerListPacketType (`org.cloudburstmc.protocol.bedrock.data.player.list.PlayerListPacketType`)
* PlayerLocationPacketType (`org.cloudburstmc.protocol.bedrock.data.location.PlayerLocationPacketType`)
* PlayerPermissionLevel (`org.cloudburstmc.protocol.bedrock.data.player.PlayerPermissionLevel`)
* PlayerRespawnState (`org.cloudburstmc.protocol.bedrock.data.player.PlayerRespawnState`)
* PositionMode (`org.cloudburstmc.protocol.bedrock.data.player.input.PositionMode`)
* RandomDistributionType (`org.cloudburstmc.protocol.bedrock.data.structure.RandomDistributionType`)
* RecipeUnlockingContext (`org.cloudburstmc.protocol.bedrock.data.recipe.RecipeUnlockingContext`)
* ResourcePackResponse (`org.cloudburstmc.protocol.bedrock.data.resourcepack.ResourcePackResponse`)
* RewindType (`org.cloudburstmc.protocol.bedrock.data.player.input.RewindType`)
* Rotation (`org.cloudburstmc.protocol.bedrock.data.structure.Rotation`)
* ScorePacketEntryAction (`org.cloudburstmc.protocol.bedrock.data.scoreboard.ScorePacketEntryAction`)
* ScoreboardIdentityPacketType (`org.cloudburstmc.protocol.bedrock.data.scoreboard.ScoreboardIdentityPacketType`)
* ScriptPrimitiveShapeType (`org.cloudburstmc.protocol.bedrock.data.shape.ScriptPrimitiveShapeType`)
* SerializedLayer (`org.cloudburstmc.protocol.bedrock.data.ability.SerializedLayer`)
* ServerAuthMovementMode (`org.cloudburstmc.protocol.bedrock.data.player.input.ServerAuthMovementMode`)
* ServerEditorConnectionPolicy (`org.cloudburstmc.protocol.bedrock.data.editor.ServerEditorConnectionPolicy`)
* ServerWaypointGroupAction (`org.cloudburstmc.protocol.bedrock.data.waypoint.ServerWaypointGroupAction`)
* ShowStoreOfferRedirectType (`org.cloudburstmc.protocol.bedrock.data.misc.ShowStoreOfferRedirectType`)
* SimulationType (`org.cloudburstmc.protocol.bedrock.data.world.SimulationType`)
* SoundDataEvent (`org.cloudburstmc.protocol.bedrock.data.sound.SoundDataEvent`)
* SpawnBiomeType (`org.cloudburstmc.protocol.bedrock.data.biome.SpawnBiomeType`)
* SpawnPositionType (`org.cloudburstmc.protocol.bedrock.data.world.SpawnPositionType`)
* SubChunkRequestResult (`org.cloudburstmc.protocol.bedrock.data.chunk.SubChunkRequestResult`)
* Subtype (`org.cloudburstmc.protocol.bedrock.data.misc.Subtype`)
* TextPacketBodyType (`org.cloudburstmc.protocol.bedrock.data.text.TextPacketBodyType`)
* TextPacketType (`org.cloudburstmc.protocol.bedrock.data.text.TextPacketType`)
* TextProcessingEventOrigin (`org.cloudburstmc.protocol.bedrock.data.text.TextProcessingEventOrigin`)
* TrustedSkinFlag (`org.cloudburstmc.protocol.bedrock.data.skin.TrustedSkinFlag`)
* Type (`org.cloudburstmc.protocol.bedrock.data.command.Type`)
* UpdateType (`org.cloudburstmc.protocol.bedrock.data.attribute.UpdateType`)
* UserInterfaceProfile (`org.cloudburstmc.protocol.bedrock.data.connection.UserInterfaceProfile`)
* VanillaWaypointManagerConstants (`org.cloudburstmc.protocol.bedrock.data.waypoint.VanillaWaypointManagerConstants`)
* VillageType (`org.cloudburstmc.protocol.bedrock.data.world.VillageType`)

## Removed Enums

* AbilityLayer (`org.cloudburstmc.protocol.bedrock.data.AbilityLayer`)
* AgentActionType (`org.cloudburstmc.protocol.bedrock.data.ee.AgentActionType`)
* AimAssistAction (`org.cloudburstmc.protocol.bedrock.data.camera.AimAssistAction`)
* AnimatedTextureType (`org.cloudburstmc.protocol.bedrock.data.skin.AnimatedTextureType`)
* AnimationExpressionType (`org.cloudburstmc.protocol.bedrock.data.skin.AnimationExpressionType`)
* AttributeOperation (`org.cloudburstmc.protocol.bedrock.data.attribute.AttributeOperation`)
* AuthoritativeMovementMode (`org.cloudburstmc.protocol.bedrock.data.AuthoritativeMovementMode`)
* BlockChangeEntry (`org.cloudburstmc.protocol.bedrock.data.BlockChangeEntry`)
* BlockInteractionType (`org.cloudburstmc.protocol.bedrock.data.BlockInteractionType`)
* BlockSyncType (`org.cloudburstmc.protocol.bedrock.data.BlockSyncType`)
* BookEditType (`org.cloudburstmc.protocol.bedrock.data.BookEditType`)
* BuildPlatform (`org.cloudburstmc.protocol.bedrock.data.BuildPlatform`)
* CameraAimAssistOperation (`org.cloudburstmc.protocol.bedrock.data.camera.CameraAimAssistOperation`)
* CameraEase (`org.cloudburstmc.protocol.bedrock.data.camera.CameraEase`)
* CameraShakeAction (`org.cloudburstmc.protocol.bedrock.data.CameraShakeAction`)
* CameraShakeType (`org.cloudburstmc.protocol.bedrock.data.CameraShakeType`)
* ChatRestrictionLevel (`org.cloudburstmc.protocol.bedrock.data.ChatRestrictionLevel`)
* ClientPlayMode (`org.cloudburstmc.protocol.bedrock.data.ClientPlayMode`)
* ClientboundDebugRendererType (`org.cloudburstmc.protocol.bedrock.data.ClientboundDebugRendererType`)
* CodeBuilderCategoryType (`org.cloudburstmc.protocol.bedrock.data.CodeBuilderCategoryType`)
* CodeBuilderCodeStatus (`org.cloudburstmc.protocol.bedrock.data.CodeBuilderCodeStatus`)
* CodeBuilderOperationType (`org.cloudburstmc.protocol.bedrock.data.CodeBuilderOperationType`)
* CommandBlockMode (`org.cloudburstmc.protocol.bedrock.data.CommandBlockMode`)
* CommandPermission (`org.cloudburstmc.protocol.bedrock.data.command.CommandPermission`)
* ContainerSlotType (`org.cloudburstmc.protocol.bedrock.data.inventory.ContainerSlotType`)
* ControlScheme (`org.cloudburstmc.protocol.bedrock.data.ControlScheme`)
* CoordinateEvaluationOrder (`org.cloudburstmc.protocol.bedrock.data.CoordinateEvaluationOrder`)
* CraftingDataType (`org.cloudburstmc.protocol.bedrock.data.inventory.crafting.CraftingDataType`)
* CreativeItemCategory (`org.cloudburstmc.protocol.bedrock.data.inventory.CreativeItemCategory`)
* DebugShape (`org.cloudburstmc.protocol.bedrock.data.DebugShape`)
* DisconnectFailReason (`org.cloudburstmc.protocol.bedrock.data.DisconnectFailReason`)
* EducationEditionOffer (`org.cloudburstmc.protocol.bedrock.data.EducationEditionOffer`)
* EntityDamageCause (`org.cloudburstmc.protocol.bedrock.data.entity.EntityDamageCause`)
* EntityEventType (`org.cloudburstmc.protocol.bedrock.data.entity.EntityEventType`)
* EntityLinkData (`org.cloudburstmc.protocol.bedrock.data.entity.EntityLinkData`)
* EventDataType (`org.cloudburstmc.protocol.bedrock.data.event.EventDataType`)
* GamePublishSetting (`org.cloudburstmc.protocol.bedrock.data.GamePublishSetting`)
* GameType (`org.cloudburstmc.protocol.bedrock.data.GameType`)
* GraphicsMode (`org.cloudburstmc.protocol.bedrock.data.GraphicsMode`)
* HeightMapDataType (`org.cloudburstmc.protocol.bedrock.data.HeightMapDataType`)
* HudElement (`org.cloudburstmc.protocol.bedrock.data.HudElement`)
* HudVisibility (`org.cloudburstmc.protocol.bedrock.data.HudVisibility`)
* InputInteractionModel (`org.cloudburstmc.protocol.bedrock.data.InputInteractionModel`)
* InputMode (`org.cloudburstmc.protocol.bedrock.data.InputMode`)
* InventorySource (`org.cloudburstmc.protocol.bedrock.data.inventory.transaction.InventorySource`)
* InventoryTabLeft (`org.cloudburstmc.protocol.bedrock.data.inventory.InventoryTabLeft`)
* InventoryTabRight (`org.cloudburstmc.protocol.bedrock.data.inventory.InventoryTabRight`)
* ItemStackResponseStatus (`org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.response.ItemStackResponseStatus`)
* ItemUseTransaction (`org.cloudburstmc.protocol.bedrock.data.inventory.transaction.ItemUseTransaction`)
* ItemUseType (`org.cloudburstmc.protocol.bedrock.data.inventory.ItemUseType`)
* ItemVersion (`org.cloudburstmc.protocol.bedrock.data.inventory.ItemVersion`)
* LabTableReactionType (`org.cloudburstmc.protocol.bedrock.data.inventory.LabTableReactionType`)
* LabTableType (`org.cloudburstmc.protocol.bedrock.data.inventory.LabTableType`)
* MapTrackedObject (`org.cloudburstmc.protocol.bedrock.data.MapTrackedObject`)
* ModalFormCancelReason (`org.cloudburstmc.protocol.bedrock.data.ModalFormCancelReason`)
* MovementEffectType (`org.cloudburstmc.protocol.bedrock.data.MovementEffectType`)
* MultiplayerMode (`org.cloudburstmc.protocol.bedrock.data.MultiplayerMode`)
* NpcRequestType (`org.cloudburstmc.protocol.bedrock.data.NpcRequestType`)
* PacketCompressionAlgorithm (`org.cloudburstmc.protocol.bedrock.data.PacketCompressionAlgorithm`)
* PacketViolationSeverity (`org.cloudburstmc.protocol.bedrock.data.PacketViolationSeverity`)
* PacketViolationType (`org.cloudburstmc.protocol.bedrock.data.PacketViolationType`)
* PhotoType (`org.cloudburstmc.protocol.bedrock.data.PhotoType`)
* PlayerActionType (`org.cloudburstmc.protocol.bedrock.data.PlayerActionType`)
* PlayerAuthInputData (`org.cloudburstmc.protocol.bedrock.data.PlayerAuthInputData`)
* PlayerPermission (`org.cloudburstmc.protocol.bedrock.data.PlayerPermission`)
* PredictionType (`org.cloudburstmc.protocol.bedrock.data.PredictionType`)
* RandomDistributionType (`org.cloudburstmc.protocol.bedrock.data.RandomDistributionType`)
* RecipeUnlockingRequirement (`org.cloudburstmc.protocol.bedrock.data.inventory.crafting.RecipeUnlockingRequirement`)
* ResourcePackType (`org.cloudburstmc.protocol.bedrock.data.ResourcePackType`)
* ScoreInfo (`org.cloudburstmc.protocol.bedrock.data.ScoreInfo`)
* ServerboundLoadingScreenPacketType (`org.cloudburstmc.protocol.bedrock.data.ServerboundLoadingScreenPacketType`)
* SimpleEventType (`org.cloudburstmc.protocol.bedrock.data.SimpleEventType`)
* SimulationType (`org.cloudburstmc.protocol.bedrock.data.SimulationType`)
* SoundEvent (`org.cloudburstmc.protocol.bedrock.data.SoundEvent`)
* SpawnBiomeType (`org.cloudburstmc.protocol.bedrock.data.SpawnBiomeType`)
* StoreOfferRedirectType (`org.cloudburstmc.protocol.bedrock.data.StoreOfferRedirectType`)
* StructureAnimationMode (`org.cloudburstmc.protocol.bedrock.data.structure.StructureAnimationMode`)
* StructureMirror (`org.cloudburstmc.protocol.bedrock.data.structure.StructureMirror`)
* StructureRotation (`org.cloudburstmc.protocol.bedrock.data.structure.StructureRotation`)
* SubChunkRequestResult (`org.cloudburstmc.protocol.bedrock.data.SubChunkRequestResult`)
* TextProcessingEventOrigin (`org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.TextProcessingEventOrigin`)
* UserInterfaceProfile (`org.cloudburstmc.protocol.bedrock.data.UserInterfaceProfile`)

## Renamed Enums

* `org.cloudburstmc.protocol.bedrock.data.LevelEvent` → `org.cloudburstmc.protocol.bedrock.data.world.event.LevelEvent` _(98% similar)_
* `org.cloudburstmc.protocol.bedrock.data.auth.AuthType` → `org.cloudburstmc.protocol.bedrock.data.auth.PlayerAuthenticationType` _(91% similar)_
* `org.cloudburstmc.protocol.bedrock.data.AdventureSetting` → `org.cloudburstmc.protocol.bedrock.data.world.AdventureSetting` _(86% similar)_
* `org.cloudburstmc.protocol.bedrock.data.entity.EntityFlag` → `org.cloudburstmc.protocol.bedrock.data.actor.ActorFlags` _(84% similar)_
* `org.cloudburstmc.protocol.bedrock.data.ParticleType` → `org.cloudburstmc.protocol.bedrock.data.world.event.ParticleType` _(83% similar)_
* `org.cloudburstmc.protocol.bedrock.data.PlayerArmorDamageFlag` → `org.cloudburstmc.protocol.bedrock.data.player.armor.PlayerArmorDamageFlag` _(68% similar)_
* `org.cloudburstmc.protocol.bedrock.data.entity.EntityDataFormat` → `org.cloudburstmc.protocol.bedrock.data.actor.ActorDataFormat` _(66% similar)_
* `org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.action.ItemStackRequestActionType` → `org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.ItemStackRequestActionType` _(62% similar)_
* `org.cloudburstmc.protocol.bedrock.data.EmoteFlag` → `org.cloudburstmc.protocol.bedrock.data.player.input.EmoteFlag` _(61% similar)_
* `org.cloudburstmc.protocol.bedrock.data.inventory.transaction.InventoryTransactionType` → `org.cloudburstmc.protocol.bedrock.data.inventory.transaction.InventoryTransactionDataType` _(57% similar)_
* `org.cloudburstmc.protocol.bedrock.data.ee.LessonAction` → `org.cloudburstmc.protocol.bedrock.data.education.LessonAction` _(53% similar)_
* `org.cloudburstmc.protocol.bedrock.data.ExpressionOp` → `org.cloudburstmc.protocol.bedrock.data.biome.ExpressionOp` _(51% similar)_
* `org.cloudburstmc.protocol.bedrock.data.Ability` → `org.cloudburstmc.protocol.bedrock.data.ability.AbilitiesIndex` _(50% similar)_

## Enum Changes

AbilitiesIndex:
* Removed `NONE`
* Removed `BOOLEAN`

AbilityLayer:
* Removed `CACHE`
* Removed `BASE`
* Removed `SPECTATOR`
* Removed `COMMANDS`
* Removed `EDITOR`

AchievementIds:
* Added `CHEST_FULL_OF_COBBLESTONE`
* Added `DIAMOND_FOR_YOU`
* Added `IRON_BELLY`
* Added `IRON_MAN`
* Added `ON_ARAIL`
* Added `OVERKILL`
* Added `RETURN_TO_SENDER`
* Added `SNIPER_DUEL`
* Added `STAYIN_FROSTY`
* Added `TAKE_INVENTORY`
* Added `MAP_ROOM`
* Added `FREIGHT_STATION`
* Added `SMELT_EVERYTHING`
* Added `TASTE_OF_YOUR_OWN_MEDICINE`
* Added `WHEN_PIGS_FLY`
* Added `INCEPTION`
* Added `ARTIFICIAL_SELECTION`
* Added `FREE_DIVER`
* Added `SPAWN_THE_WITHER`
* Added `BEACONATOR`
* Added `GREAT_VIEW`
* Added `SUPER_SONIC`
* Added `THE_END_AGAIN`
* Added `TREASURE_HUNTER`
* Added `SHOOTING_STAR`
* Added `FASHION_SHOW`
* Added `SELF_PUBLISHED_AUTHOR`
* Added `ALTERNATIVE_FUEL`
* Added `SLEEP_WITH_THE_FISHES`
* Added `CASTAWAY`
* Added `IM_AMARINE_BIOLOGIST`
* Added `SAIL_THE7SEAS`
* Added `ME_GOLD`
* Added `AHOY`
* Added `ATLANTIS`
* Added `ONE_PICKLE_TWO_PICKLE_SEA_PICKLE_FOUR`
* Added `DOA_BARREL_ROLL`
* Added `MOSKSTRAUMEN`
* Added `ECHOLOCATION`
* Added `WHERE_HAVE_YOU_BEEN`
* Added `TOP_OF_THE_WORLD`
* Added `FRUIT_ON_THE_LOOM`
* Added `SOUND_THE_ALARM`
* Added `BUY_LOW_SELL_HIGH`
* Added `DISENCHANTED`
* Added `TIME_FOR_STEW`
* Added `BEE_OUR_GUEST`
* Added `TOTAL_BEE_LOCATION`
* Added `STICKY_SITUATION`
* Added `COVER_ME_IN_DEBRIS`
* Added `FLOAT_YOUR_GOAT`
* Added `FRIEND`
* Added `WAX_ON_WAX_OFF`
* Added `STRIDER_RIDDEN_IN_LAVA_IN_OVERWORLD`
* Added `GOAT_HORN_ACQUIRED`
* Added `JUKEBOX_USED_IN_MEADOWS`
* Added `TRADED_AT_WORLD_HEIGHT`
* Added `SURVIVED_FALL_FROM_WORLD_HEIGHT`
* Added `SNEAK_CLOSE_TO_SCULK_SENSOR`
* Added `IT_SPREADS`
* Added `BIRTHDAY_SONG`
* Added `WITH_OUR_POWERS_COMBINED`
* Added `PLANTING_THE_PAST`
* Added `CAREFUL_RESTORATION`
* Added `REVAULTING`
* Added `CRAFTERS_CRAFTING_CRAFTERS`
* Added `WHO_NEEDS_ROCKETS`
* Added `OVER_OVERKILL`
* Added `HEART_TRANSPLANTER`
* Added `STAY_HYDRATED`
* Added `MOB_KABOB`
* Added `ADVENTURING_TIME`
* Added `UH_OH`
* Added `GETTING_WOOD`
* Added `BENCH_MAKING`
* Added `TIME_TO_MINE`
* Added `HOT_TOPIC`
* Added `ACQUIRE_HARDWARE`
* Added `GETTING_AN_UPGRADE`
* Added `MONSTER_HUNTER`
* Added `DIAMONDS`
* Added `PLETHORA_OF_CATS`

ActorBlockSyncMessageId:
* Added `NONE`
* Added `CREATE`

ActorEvent:
* Added `NONE`
* Added `JUMP`
* Added `HURT`
* Added `DEATH`
* Added `START_ATTACKING`
* Added `STOP_ATTACKING`
* Added `TAMING_FAILED`
* Added `TAMING_SUCCEEDED`
* Added `SHAKE_WETNESS`
* Added `USE_ITEM`
* Added `EAT_GRASS`
* Added `FISHHOOK_BUBBLE`
* Added `FISHHOOK_FISHPOS`
* Added `FISHHOOK_HOOKTIME`
* Added `FISHHOOK_TEASE`
* Added `SQUID_FLEEING`
* Added `ZOMBIE_CONVERTING`
* Added `PLAY_AMBIENT`
* Added `SPAWN_ALIVE`
* Added `START_OFFER_FLOWER`
* Added `STOP_OFFER_FLOWER`
* Added `LOVE_HEARTS`
* Added `VILLAGER_ANGRY`
* Added `VILLAGER_HAPPY`
* Added `WITCH_HAT_MAGIC`
* Added `FIREWORKS_EXPLODE`
* Added `IN_LOVE_HEARTS`
* Added `SILVERFISH_MERGE_ANIM`
* Added `GUARDIAN_ATTACK_SOUND`
* Added `DRINK_POTION`
* Added `THROW_POTION`
* Added `PRIME_TNTCART`
* Added `PRIME_CREEPER`
* Added `AIR_SUPPLY`
* Added `DEPRECATED_ADD_PLAYER_LEVELS`
* Added `GUARDIAN_MINING_FATIGUE`
* Added `AGENT_SWING_ARM`
* Added `DRAGON_START_DEATH_ANIM`
* Added `GROUND_DUST`
* Added `SHAKE`
* Added `FEED`
* Added `BABY_AGE`
* Added `INSTANT_DEATH`
* Added `NOTIFY_TRADE`
* Added `LEASH_DESTROYED`
* Added `CARAVAN_UPDATED`
* Added `TALISMAN_ACTIVATE`
* Added `DEPRECATED_UPDATE_STRUCTURE_FEATURE`
* Added `PLAYER_SPAWNED_MOB`
* Added `PUKE`
* Added `UPDATE_STACK_SIZE`
* Added `START_SWIMMING`
* Added `BALLOON_POP`
* Added `TREASURE_HUNT`
* Added `SUMMON_AGENT`
* Added `FINISHED_CHARGING_ITEM`
* Added `ACTOR_GROW_UP`
* Added `VIBRATION_DETECTED`
* Added `DRINK_MILK`
* Added `SHAKE_WETNESS_STOP`
* Added `KINETIC_DAMAGE_DEALT`
* Added `HURT_WITHOUT_RECEIVING_DAMAGE`
* Added `LANDED_ON_GROUND`

ActorFlags:
* Added `BODY_ROTATION_ALWAYS_FOLLOWS_HEAD`
* Added `CAN_USE_VERTICAL_MOVEMENT_ACTION`
* Added `BODY_ROTATION_LOCKED_TO_VEHICLE`
* Added `USES_LEGACY_FRICTION`
* Added `USES_UNIFORM_AIR_DRAG`
* Added `NAMEPLATE_DEPTH_TESTED`
* Added `NOT_PICKABLE_FROM_INSIDE`

ActorLinkType:
* Added `NONE`
* Added `RIDING`
* Added `PASSENGER`

ActorSwingSource:
* Added `NONE`
* Added `BUILD`
* Added `MINE`
* Added `INTERACT`
* Added `ATTACK`
* Added `USE_ITEM`
* Added `THROW_ITEM`
* Added `DROP_ITEM`
* Added `EVENT`

ActorType:
* Added `UNDEFINED`
* Added `MOB`
* Added `PATHFINDER_MOB`
* Added `MONSTER`
* Added `ANIMAL`
* Added `TAMABLE_ANIMAL`
* Added `AMBIENT`
* Added `UNDEAD_MONSTER`
* Added `ZOMBIE_MONSTER`
* Added `ARTHROPOD`
* Added `MINECART`
* Added `SKELETON_MONSTER`
* Added `EQUINE_ANIMAL`
* Added `PROJECTILE`
* Added `ABSTRACT_ARROW`
* Added `WATER_ANIMAL`
* Added `VILLAGER_BASE`
* Added `CHICKEN`
* Added `COW`
* Added `PIG`
* Added `SHEEP`
* Added `WOLF`
* Added `VILLAGER`
* Added `MUSHROOM_COW`
* Added `SQUID`
* Added `RABBIT`
* Added `BAT`
* Added `IRON_GOLEM`
* Added `SNOW_GOLEM`
* Added `OCELOT`
* Added `HORSE`
* Added `POLAR_BEAR`
* Added `LLAMA`
* Added `PARROT`
* Added `DOLPHIN`
* Added `DONKEY`
* Added `MULE`
* Added `SKELETON_HORSE`
* Added `ZOMBIE_HORSE`
* Added `ZOMBIE`
* Added `CREEPER`
* Added `SKELETON`
* Added `SPIDER`
* Added `PIG_ZOMBIE`
* Added `SLIME`
* Added `ENDER_MAN`
* Added `SILVERFISH`
* Added `CAVE_SPIDER`
* Added `GHAST`
* Added `LAVA_SLIME`
* Added `BLAZE`
* Added `ZOMBIE_VILLAGER`
* Added `WITCH`
* Added `STRAY`
* Added `HUSK`
* Added `WITHER_SKELETON`
* Added `GUARDIAN`
* Added `ELDER_GUARDIAN`
* Added `NPC`
* Added `WITHER_BOSS`
* Added `DRAGON`
* Added `SHULKER`
* Added `ENDERMITE`
* Added `AGENT`
* Added `VINDICATOR`
* Added `PHANTOM`
* Added `ILLAGER_BEAST`
* Added `ARMOR_STAND`
* Added `TRIPOD_CAMERA`
* Added `PLAYER`
* Added `ITEM_ENTITY`
* Added `PRIMED_TNT`
* Added `FALLING_BLOCK`
* Added `MOVING_BLOCK`
* Added `EXPERIENCE_POTION`
* Added `EXPERIENCE`
* Added `EYE_OF_ENDER`
* Added `ENDER_CRYSTAL`
* Added `FIREWORKS_ROCKET`
* Added `TRIDENT`
* Added `TURTLE`
* Added `CAT`
* Added `SHULKER_BULLET`
* Added `FISHING_HOOK`
* Added `CHALKBOARD`
* Added `DRAGON_FIREBALL`
* Added `ARROW`
* Added `SNOWBALL`
* Added `THROWN_EGG`
* Added `PAINTING`
* Added `LARGE_FIREBALL`
* Added `THROWN_POTION`
* Added `ENDERPEARL`
* Added `LEASH_KNOT`
* Added `WITHER_SKULL`
* Added `BOAT_RIDEABLE`
* Added `WITHER_SKULL_DANGEROUS`
* Added `LIGHTNING_BOLT`
* Added `SMALL_FIREBALL`
* Added `AREA_EFFECT_CLOUD`
* Added `LINGERING_POTION`
* Added `LLAMA_SPIT`
* Added `EVOCATION_FANG`
* Added `EVOCATION_ILLAGER`
* Added `VEX`
* Added `MINECART_RIDEABLE`
* Added `MINECART_HOPPER`
* Added `MINECART_TNT`
* Added `MINECART_CHEST`
* Added `MINECART_FURNACE`
* Added `MINECART_COMMAND_BLOCK`
* Added `ICE_BOMB`
* Added `BALLOON`
* Added `PUFFERFISH`
* Added `SALMON`
* Added `DROWNED`
* Added `TROPICALFISH`
* Added `FISH`
* Added `PANDA`
* Added `PILLAGER`
* Added `VILLAGER_V2`
* Added `ZOMBIE_VILLAGER_V2`
* Added `SHIELD`
* Added `WANDERING_TRADER`
* Added `LECTERN`
* Added `ELDER_GUARDIAN_GHOST`
* Added `FOX`
* Added `BEE`
* Added `PIGLIN`
* Added `HOGLIN`
* Added `STRIDER`
* Added `ZOGLIN`
* Added `PIGLIN_BRUTE`
* Added `GOAT`
* Added `GLOW_SQUID`
* Added `AXOLOTL`
* Added `WARDEN`
* Added `FROG`
* Added `TADPOLE`
* Added `ALLAY`
* Added `CHEST_BOAT_RIDEABLE`
* Added `TRADER_LLAMA`
* Added `CAMEL`
* Added `SNIFFER`
* Added `BREEZE`
* Added `BREEZE_WIND_CHARGE_PROJECTILE`
* Added `ARMADILLO`
* Added `WIND_CHARGE_PROJECTILE`
* Added `BOGGED`
* Added `OMINOUS_ITEM_SPAWNER`
* Added `CREAKING`
* Added `HAPPY_GHAST`
* Added `COPPER_GOLEM`
* Added `NAUTILUS`
* Added `ZOMBIE_NAUTILUS`
* Added `PARCHED`
* Added `CAMEL_HUSK`
* Added `SULFUR_CUBE`
* Added `CUSHION`

AgentActionType:
* Added `ATTACK`
* Added `COLLECT`
* Added `DESTROY`
* Added `DETECT_REDSTONE`
* Added `DETECT_OBSTACLE`
* Added `DROP`
* Added `DROP_ALL`
* Added `INSPECT`
* Added `INSPECT_DATA`
* Added `INSPECT_ITEM_COUNT`
* Added `INSPECT_ITEM_DETAIL`
* Added `INSPECT_ITEM_SPACE`
* Added `INTERACT`
* Added `MOVE`
* Added `PLACE_BLOCK`
* Added `TILL`
* Added `TRANSFER_ITEM_TO`
* Added `TURN`

AgentActionType:
* Removed `NONE`
* Removed `ATTACK`
* Removed `COLLECT`
* Removed `DESTROY`
* Removed `DETECT_REDSTONE`
* Removed `DETECT_OBSTACLE`
* Removed `DROP`
* Removed `DROP_ALL`
* Removed `INSPECT`
* Removed `INSPECT_DATA`
* Removed `INSPECT_ITEM_COUNT`
* Removed `INSPECT_ITEM_DETAIL`
* Removed `INSPECT_ITEM_SPACE`
* Removed `INTERACT`
* Removed `MOVE`
* Removed `PLACE_BLOCK`
* Removed `TILL`
* Removed `TRANSFER_ITEM_TO`

AgentAnimation:
* Added `ARM_SWING`
* Added `SHRUG`

AgentResult:
* Added `QUERY_RESULT_TRUE`

AimAssistAction:
* Removed `SET`

AimAssistTargetMode:
* Added `ANGLE`
* Added `DISTANCE`

AnimatedTextureType:
* Removed `NONE`
* Removed `FACE`
* Removed `BODY_32X32`
* Removed `BODY_128X128`

AnimationExpressionType:
* Removed `LINEAR`
* Removed `BLINKING`

AnimationMode:
* Added `NONE`
* Added `LAYERS`
* Added `BLOCKS`

ArmSizeType:
* Added `SLIM`
* Added `WIDE`

ArmorSlot:
* Added `HEAD`
* Added `TORSO`
* Added `LEGS`
* Added `FEET`
* Added `BODY`

AttributeDataType:
* Added `BOOL`
* Added `FLOAT`
* Added `COLOR`

AttributeLayerSettings:
* Added `FLOAT`
* Added `STRING`

AttributeModifierOperation:
* Added `OPERATION_ADDITION`
* Added `OPERATION_MULTIPLY_BASE`
* Added `OPERATION_MULTIPLY_TOTAL`
* Added `OPERATION_CAP`
* Added `OPERATION_INVALID`

AttributeOperands:
* Added `OPERAND_MIN`
* Added `OPERAND_MAX`
* Added `OPERAND_CURRENT`
* Added `OPERAND_INVALID`

AttributeOperation:
* Removed `ADDITION`
* Removed `MULTIPLY_BASE`
* Removed `MULTIPLY_TOTAL`
* Removed `CAP`
* Removed `INVALID`

AudioListener:
* Added `CAMERA`
* Added `PLAYER`

AuthoritativeMovementMode:
* Removed `CLIENT`
* Removed `SERVER`

BiomeTemperatureCategory:
* Added `FROZEN`

BlockChangeEntry:
* Removed `NONE`
* Removed `CREATE`

BlockInteractionType:
* Removed `NONE`
* Removed `EXTEND`
* Removed `CLONE`
* Removed `LOCK`
* Removed `CREATE`
* Removed `CREATE_LOCATOR`
* Removed `RENAME`
* Removed `ITEM_PLACED`
* Removed `ITEM_REMOVED`
* Removed `COOKING`
* Removed `DOUSING`
* Removed `LIGHTING`
* Removed `HAYSTACK`
* Removed `FILLED`
* Removed `EMPTIED`
* Removed `ADD_DYE`
* Removed `DYE_ITEM`
* Removed `CLEAR_ITEM`
* Removed `ENCHANT_ARROW`
* Removed `COMPOST_ITEM_PLACE`
* Removed `RECOVERED_BONEMEAL`
* Removed `BOOK_PLACED`
* Removed `BOOK_OPEN`
* Removed `DISENCHANT`
* Removed `REPAIR`

BlockSyncType:
* Removed `NONE`
* Removed `CREATE`

BookEditOperation:
* Added `REPLACE_PAGE`
* Added `ADD_PAGE`
* Added `DELETE_PAGE`
* Added `SWAP_PAGES`
* Added `FINALIZE`

BookEditType:
* Removed `REPLACE_PAGE`
* Removed `ADD_PAGE`
* Removed `DELETE_PAGE`
* Removed `SWAP_PAGES`

BoolAttributeOperation:
* Added `OVERRIDE`
* Added `ALPHA_BLEND`
* Added `AND`
* Added `NAND`
* Added `OR`
* Added `NOR`
* Added `XOR`
* Added `XNOR`

BossBarColor:
* Added `PINK`
* Added `BLUE`
* Added `RED`
* Added `GREEN`
* Added `YELLOW`
* Added `PURPLE`
* Added `REBECCA_PURPLE`
* Added `WHITE`

BossBarOverlay:
* Added `PROGRESS`
* Added `NOTCHED_6`
* Added `NOTCHED_10`
* Added `NOTCHED_12`
* Added `NOTCHED_20`

BossEventUpdateType:
* Added `ADD`
* Added `PLAYER_ADDED`
* Added `REMOVE`
* Added `PLAYER_REMOVED`
* Added `UPDATE_PERCENT`
* Added `UPDATE_NAME`
* Added `UPDATE_PROPERTIES`
* Added `UPDATE_STYLE`
* Added `QUERY`

BuildPlatform:
* Added `GOOGLE`
* Added `IOS`
* Added `OSX`
* Added `AMAZON`
* Added `GEAR_VR`
* Added `HOLOLENS`
* Added `UWP`
* Added `WIN32`
* Added `DEDICATED`
* Added `TV_OS`
* Added `SONY`
* Added `NINTENDO`
* Added `XBOX`
* Added `WINDOWS_PHONE`
* Added `LINUX`
* Added `UNKNOWN`

BuildPlatform:
* Removed `UNDEFINED`
* Removed `GOOGLE`
* Removed `IOS`
* Removed `OSX`
* Removed `AMAZON`
* Removed `GEAR_VR`
* Removed `HOLOLENS`
* Removed `UWP`
* Removed `WIN_32`
* Removed `DEDICATED`
* Removed `TV_OS`
* Removed `SONY`
* Removed `NX`
* Removed `XBOX`
* Removed `WINDOWS_PHONE`
* Removed `LINUX`

CameraAimAssistOperation:
* Removed `SET`

CameraAimAssistPresetsPacketOperation:
* Added `SET`
* Added `ADD_TO_EXISTING`

CameraEase:
* Removed `LINEAR`
* Removed `SPRING`
* Removed `EASE_IN_SINE`
* Removed `EASE_OUT_SINE`
* Removed `EASE_IN_OUT_SINE`
* Removed `EASE_IN_QUAD`
* Removed `EASE_OUT_QUAD`
* Removed `EASE_IN_OUT_QUAD`
* Removed `EASE_IN_CUBIC`
* Removed `EASE_OUT_CUBIC`
* Removed `EASE_IN_OUT_CUBIC`
* Removed `EASE_IN_QUART`
* Removed `EASE_OUT_QUART`
* Removed `EASE_IN_OUT_QUART`
* Removed `EASE_IN_QUINT`
* Removed `EASE_OUT_QUINT`
* Removed `EASE_IN_OUT_QUINT`
* Removed `EASE_IN_EXPO`
* Removed `EASE_OUT_EXPO`
* Removed `EASE_IN_OUT_EXPO`
* Removed `EASE_IN_CIRC`
* Removed `EASE_OUT_CIRC`
* Removed `EASE_IN_OUT_CIRC`
* Removed `EASE_IN_BACK`
* Removed `EASE_OUT_BACK`
* Removed `EASE_IN_OUT_BACK`
* Removed `EASE_IN_ELASTIC`
* Removed `EASE_OUT_ELASTIC`
* Removed `EASE_IN_OUT_ELASTIC`
* Removed `EASE_IN_BOUNCE`
* Removed `EASE_OUT_BOUNCE`
* Removed `EASE_IN_OUT_BOUNCE`

CameraShakeAction:
* Added `ADD`
* Added `STOP`

CameraShakeAction:
* Removed `ADD`

CameraShakeType:
* Added `POSITIONAL`
* Added `ROTATIONAL`

CameraShakeType:
* Removed `POSITIONAL`
* Removed `ROTATIONAL`

CameraSplineType:
* Added `CATMULL_ROM`
* Added `LINEAR`

ChatRestrictionLevel:
* Added `NONE`
* Added `DROPPED`
* Added `DISABLED`

ChatRestrictionLevel:
* Removed `NONE`
* Removed `DROPPED`

ClientCameraAimAssistPacketAction:
* Added `SET_FROM_CAMERA_PRESET`
* Added `CLEAR`

ClientPlayMode:
* Added `NORMAL`
* Added `TEASER`
* Added `SCREEN`
* Added `VIEWER`
* Added `REALITY`
* Added `PLACEMENT`
* Added `LIVING_ROOM`
* Added `EXIT_LEVEL`
* Added `EXIT_LEVEL_LIVING_ROOM`

ClientPlayMode:
* Removed `NORMAL`
* Removed `TEASER`
* Removed `SCREEN`
* Removed `VIEWER`
* Removed `REALITY`
* Removed `PLACEMENT`
* Removed `LIVING_ROOM`
* Removed `EXIT_LEVEL`

ClientboundAttributeLayerSyncDataType:
* Added `UPDATE_ATTRIBUTE_LAYERS`
* Added `UPDATE_ATTRIBUTE_LAYER_SETTINGS`
* Added `UPDATE_ENVIRONMENT_ATTRIBUTES`

ClientboundDebugRendererType:
* Removed `INVALID`
* Removed `CLEAR_DEBUG_MARKERS`

CodeBuilderCategoryType:
* Removed `NONE`
* Removed `CODE_STATUS`

CodeBuilderCodeStatus:
* Removed `NONE`
* Removed `NOT_STARTED`
* Removed `IN_PROGRESS`
* Removed `PAUSED`
* Removed `ERROR`

CodeBuilderExecutionStateCodeStatus:
* Added `NONE`
* Added `NOT_STARTED`
* Added `IN_PROGRESS`
* Added `PAUSED`
* Added `ERROR`
* Added `SUCCEEDED`

CodeBuilderOperationType:
* Removed `NONE`
* Removed `GET`
* Removed `SET`

CodeBuilderStorageQueryOptionsCategory:
* Added `NONE`
* Added `CODE_STATUS`
* Added `INSTANTIATION`

CodeBuilderStorageQueryOptionsOperation:
* Added `NONE`
* Added `GET`
* Added `SET`
* Added `RESET`

ColorAttributeOperation:
* Added `OVERRIDE`
* Added `ALPHA_BLEND`
* Added `ADD`
* Added `SUBTRACT`
* Added `MULTIPLY`

CommandBlockMode:
* Added `NORMAL`
* Added `REPEATING`
* Added `CHAIN`

CommandBlockMode:
* Removed `NORMAL`
* Removed `REPEATING`

CommandBlockUpdateTargetType:
* Added `ENTITY`
* Added `BLOCK`

CommandOriginType:
* Added `COMMAND_BLOCK`
* Added `MINECART_COMMAND_BLOCK`
* Added `SCRIPTING`
* Added `EXECUTE_CONTEXT`
* Removed `BLOCK`
* Removed `MINECART_BLOCK`
* Removed `SCRIPT`

CommandOutputType:
* Added `DATA_SET`

CommandParamType:
* Added `VAL`
* Added `RVAL`
* Added `SELECTION`
* Added `STANDALONE_SELECTION`
* Added `WILDCARD_SELECTION`
* Added `NON_ID_SELECTOR`
* Added `SCORES_ARG`
* Added `SCORES_ARGS`
* Added `INTEGER_RANGE_VAL`
* Added `INTEGER_RANGE_POST_VAL`
* Added `INTEGER_RANGE`
* Added `FULL_INTEGER_RANGE`
* Added `FULL_RATIONAL_RANGE`
* Added `NAME_ARG`
* Added `TYPE_ARG`
* Added `FAMILY_ARG`
* Added `HAS_PERMISSION_ARG`
* Added `HAS_PERMISSIONS_ARG`
* Added `HAS_PERMISSION_SELECTOR`
* Added `HAS_PERMISSION_ELEMENT`
* Added `HAS_PERMISSION_ELEMENTS`
* Added `TAG_ARG`
* Added `HAS_ITEM_ARG`
* Added `HAS_ITEM_ARGS`
* Added `EQUIPMENT_SLOT_ENUM`
* Added `ID`
* Added `POSITION_FLOAT`
* Added `MESSAGE_EXP`
* Added `RAW_TEXT`
* Added `RAW_TEXT_CONT`
* Added `JSON_OBJECT`
* Added `BLOCK_STATE_ARRAY`
* Added `BLOCK_STATE_ARRAY_CONT`
* Added `CLOCK_TIME_MARKER_NAME`
* Removed `VALUE`
* Removed `R_VALUE`
* Removed `TARGET`
* Removed `UNKNOWN_STANDALONE`
* Removed `WILDCARD_TARGET`
* Removed `UNKNOWN_NON_ID`
* Removed `SCORE_ARG`
* Removed `SCORE_ARGS`
* Removed `INT_RANGE_VAL`
* Removed `INT_RANGE_POST_VAL`
* Removed `INT_RANGE`
* Removed `INT_RANGE_FULL`
* Removed `NAME`
* Removed `TYPE`
* Removed `FAMILY`
* Removed `PERMISSION`
* Removed `PERMISSIONS`
* Removed `PERMISSION_SELECTOR`
* Removed `PERMISSION_ELEMENT`
* Removed `PERMISSION_ELEMENTS`
* Removed `TAG`
* Removed `HAS_ITEM`
* Removed `HAS_ITEMS`
* Removed `EQUIPMENT_SLOTS`
* Removed `STRING`
* Removed `BLOCK_POSITION`
* Removed `MESSAGE_XP`
* Removed `TEXT`
* Removed `TEXT_CONT`
* Removed `JSON`
* Removed `BLOCK_STATES`
* Removed `BLOCK_STATES_CONT`
* Removed `RATIONAL_RANGE_FULL`
* Removed `CODE_BUILDER_SELECTOR`

CommandPermission:
* Removed `ANY`
* Removed `GAME_DIRECTORS`
* Removed `ADMIN`
* Removed `HOST`
* Removed `OWNER`

CommandPermissionLevel:
* Added `ANY`
* Added `GAME_DIRECTORS`
* Added `ADMIN`
* Added `HOST`
* Added `OWNER`
* Added `INTERNAL`

ContainerEnumName:
* Added `ANVIL_INPUT_CONTAINER`
* Added `ANVIL_MATERIAL_CONTAINER`
* Added `ANVIL_RESULT_PREVIEW_CONTAINER`
* Added `SMITHING_TABLE_INPUT_CONTAINER`
* Added `SMITHING_TABLE_MATERIAL_CONTAINER`
* Added `SMITHING_TABLE_RESULT_PREVIEW_CONTAINER`
* Added `ARMOR_CONTAINER`
* Added `LEVEL_ENTITY_CONTAINER`
* Added `BEACON_PAYMENT_CONTAINER`
* Added `BREWING_STAND_INPUT_CONTAINER`
* Added `BREWING_STAND_RESULT_CONTAINER`
* Added `BREWING_STAND_FUEL_CONTAINER`
* Added `COMBINED_HOTBAR_AND_INVENTORY_CONTAINER`
* Added `CRAFTING_INPUT_CONTAINER`
* Added `CRAFTING_OUTPUT_PREVIEW_CONTAINER`
* Added `RECIPE_CONSTRUCTION_CONTAINER`
* Added `RECIPE_NATURE_CONTAINER`
* Added `RECIPE_ITEMS_CONTAINER`
* Added `RECIPE_FOOD_CONTAINER`
* Added `RECIPE_BLOCKS_CONTAINER`
* Added `RECIPE_FURNACE_ITEMS_CONTAINER`
* Added `RECIPE_SEARCH_CONTAINER`
* Added `RECIPE_SEARCH_BAR_CONTAINER`
* Added `RECIPE_EQUIPMENT_CONTAINER`
* Added `RECIPE_BOOK_CONTAINER`
* Added `ENCHANTING_INPUT_CONTAINER`
* Added `ENCHANTING_MATERIAL_CONTAINER`
* Added `FURNACE_FUEL_CONTAINER`
* Added `FURNACE_INGREDIENT_CONTAINER`
* Added `FURNACE_RESULT_CONTAINER`
* Added `HORSE_EQUIP_CONTAINER`
* Added `HOTBAR_CONTAINER`
* Added `INVENTORY_CONTAINER`
* Added `SHULKER_BOX_CONTAINER`
* Added `TRADE_INGREDIENT1CONTAINER`
* Added `TRADE_INGREDIENT2CONTAINER`
* Added `TRADE_RESULT_PREVIEW_CONTAINER`
* Added `OFFHAND_CONTAINER`
* Added `COMPOUND_CREATOR_INPUT`
* Added `COMPOUND_CREATOR_OUTPUT_PREVIEW`
* Added `ELEMENT_CONSTRUCTOR_OUTPUT_PREVIEW`
* Added `MATERIAL_REDUCER_INPUT`
* Added `MATERIAL_REDUCER_OUTPUT`
* Added `LAB_TABLE_INPUT`
* Added `LOOM_INPUT_CONTAINER`
* Added `LOOM_DYE_CONTAINER`
* Added `LOOM_MATERIAL_CONTAINER`
* Added `LOOM_RESULT_PREVIEW_CONTAINER`
* Added `BLAST_FURNACE_INGREDIENT_CONTAINER`
* Added `SMOKER_INGREDIENT_CONTAINER`
* Added `TRADE2INGREDIENT1CONTAINER`
* Added `TRADE2INGREDIENT2CONTAINER`
* Added `TRADE2RESULT_PREVIEW_CONTAINER`
* Added `GRINDSTONE_INPUT_CONTAINER`
* Added `GRINDSTONE_ADDITIONAL_CONTAINER`
* Added `GRINDSTONE_RESULT_PREVIEW_CONTAINER`
* Added `STONECUTTER_INPUT_CONTAINER`
* Added `STONECUTTER_RESULT_PREVIEW_CONTAINER`
* Added `CARTOGRAPHY_INPUT_CONTAINER`
* Added `CARTOGRAPHY_ADDITIONAL_CONTAINER`
* Added `CARTOGRAPHY_RESULT_PREVIEW_CONTAINER`
* Added `BARREL_CONTAINER`
* Added `CURSOR_CONTAINER`
* Added `CREATED_OUTPUT_CONTAINER`
* Added `SMITHING_TABLE_TEMPLATE_CONTAINER`
* Added `CRAFTER_LEVEL_ENTITY_CONTAINER`
* Added `DYNAMIC_CONTAINER`

ContainerSlotType:
* Removed `UNKNOWN`
* Removed `ANVIL_INPUT`
* Removed `ANVIL_MATERIAL`
* Removed `ANVIL_RESULT`
* Removed `SMITHING_TABLE_INPUT`
* Removed `SMITHING_TABLE_MATERIAL`
* Removed `SMITHING_TABLE_RESULT`
* Removed `ARMOR`
* Removed `LEVEL_ENTITY`
* Removed `BEACON_PAYMENT`
* Removed `BREWING_INPUT`
* Removed `BREWING_RESULT`
* Removed `BREWING_FUEL`
* Removed `HOTBAR_AND_INVENTORY`
* Removed `CRAFTING_INPUT`
* Removed `CRAFTING_OUTPUT`
* Removed `RECIPE_CONSTRUCTION`
* Removed `RECIPE_NATURE`
* Removed `RECIPE_ITEMS`
* Removed `RECIPE_SEARCH`
* Removed `RECIPE_SEARCH_BAR`
* Removed `RECIPE_EQUIPMENT`
* Removed `ENCHANTING_INPUT`
* Removed `ENCHANTING_MATERIAL`
* Removed `FURNACE_FUEL`
* Removed `FURNACE_INGREDIENT`
* Removed `FURNACE_RESULT`
* Removed `HORSE_EQUIP`
* Removed `HOTBAR`
* Removed `INVENTORY`
* Removed `SHULKER_BOX`
* Removed `TRADE_INGREDIENT_1`
* Removed `TRADE_INGREDIENT_2`
* Removed `TRADE_RESULT`
* Removed `OFFHAND`
* Removed `COMPOUND_CREATOR_INPUT`
* Removed `COMPOUND_CREATOR_OUTPUT`
* Removed `ELEMENT_CONSTRUCTOR_OUTPUT`
* Removed `MATERIAL_REDUCER_INPUT`
* Removed `MATERIAL_REDUCER_OUTPUT`
* Removed `LAB_TABLE_INPUT`
* Removed `LOOM_INPUT`
* Removed `LOOM_DYE`
* Removed `LOOM_MATERIAL`
* Removed `LOOM_RESULT`
* Removed `BLAST_FURNACE_INGREDIENT`
* Removed `SMOKER_INGREDIENT`
* Removed `TRADE2_INGREDIENT_1`
* Removed `TRADE2_INGREDIENT_2`
* Removed `TRADE2_RESULT`
* Removed `GRINDSTONE_INPUT`
* Removed `GRINDSTONE_ADDITIONAL`
* Removed `GRINDSTONE_RESULT`
* Removed `STONECUTTER_INPUT`
* Removed `STONECUTTER_RESULT`
* Removed `CARTOGRAPHY_INPUT`
* Removed `CARTOGRAPHY_ADDITIONAL`
* Removed `CARTOGRAPHY_RESULT`
* Removed `BARREL`
* Removed `CURSOR`
* Removed `CREATED_OUTPUT`
* Removed `RECIPE_BOOK`
* Removed `SMITHING_TABLE_TEMPLATE`
* Removed `CRAFTER_BLOCK_CONTAINER`

ContainerType:
* Added `DATA_DRIVEN_CONTAINER`

ControlScheme:
* Added `LOCKED_PLAYER_RELATIVE_STRAFE`
* Added `CAMERA_RELATIVE`
* Added `CAMERA_RELATIVE_STRAFE`
* Added `PLAYER_RELATIVE`
* Added `PLAYER_RELATIVE_STRAFE`

ControlScheme:
* Removed `LOCKED_PLAYER_RELATIVE_STRAFE`
* Removed `CAMERA_RELATIVE`
* Removed `CAMERA_RELATIVE_STRAFE`
* Removed `PLAYER_RELATIVE`

CoordinateEvaluationOrder:
* Added `XYZ`
* Added `XZY`
* Added `YXZ`
* Added `YZX`
* Added `ZXY`
* Added `ZYX`

CoordinateEvaluationOrder:
* Removed `XYZ`
* Removed `XZY`
* Removed `YXZ`
* Removed `YZX`
* Removed `ZXY`

CraftingDataEntryType:
* Added `SHAPELESS_RECIPE`
* Added `SHAPED_RECIPE`
* Added `FURNACE_RECIPE`
* Added `FURNACE_AUX_RECIPE`
* Added `MULTI`
* Added `USER_DATA_SHAPELESS_RECIPE`
* Added `SHAPELESS_CHEMISTRY_RECIPE`
* Added `SHAPED_CHEMISTRY_RECIPE`
* Added `SMITHING_TRANSFORM_RECIPE`
* Added `SMITHING_TRIM_RECIPE`

CraftingDataType:
* Removed `SHAPELESS`
* Removed `SHAPED`
* Removed `FURNACE`
* Removed `FURNACE_DATA`
* Removed `MULTI`
* Removed `SHULKER_BOX`
* Removed `SHAPELESS_CHEMISTRY`
* Removed `SHAPED_CHEMISTRY`
* Removed `SMITHING_TRANSFORM`
* Removed `SMITHING_TRIM`

CreativeCategory:
* Added `CONSTRUCTION`
* Added `NATURE`
* Added `EQUIPMENT`
* Added `ITEMS`
* Added `ITEM_COMMAND_ONLY`

CreativeItemCategory:
* Removed `ALL`
* Removed `CONSTRUCTION`
* Removed `NATURE`
* Removed `EQUIPMENT`
* Removed `ITEMS`
* Removed `ITEM_COMMAND_ONLY`

CurrentCmdVersion:
* Added `INVALID`
* Added `INITIAL`
* Added `TP_ROTATION_CLAMPING`
* Added `NEW_BEDROCK_CMD_SYSTEM`
* Added `EXECUTE_USES_VEC3`
* Added `CLONE_FIXES`
* Added `UPDATE_AQUATIC`
* Added `ENTITY_SELECTOR_USES_VEC3`
* Added `CONTAINERS_DONT_DROP_ITEMS_ANYMORE`
* Added `FILTERS_OBEY_DIMENSIONS`
* Added `EXECUTE_AND_BLOCK_COMMAND_AND_SELF_SELECTOR_FIXES`
* Added `INSTANT_EFFECTS_USE_TICKS`
* Added `DONT_REGISTER_BROKEN_FUNCTION_COMMANDS`
* Added `CLEAR_SPAWN_POINT_COMMAND`
* Added `CLONE_AND_TELEPORT_ROTATION_FIXES`
* Added `TELEPORT_DIMENSION_FIXES`
* Added `CLONE_UPDATE_BLOCK_AND_TIME_FIXES`
* Added `CLONE_INTERSECT_FIX`
* Added `FUNCTION_EXECUTE_ORDER_AND_CHEST_SLOT_FIX`
* Added `NON_TICKING_AREAS_NO_LONGER_CONSIDERED_LOADED`
* Added `SPREADPLAYERS_HAZARD_AND_RESOLVE_PLAYER_BY_NAME_FIX`
* Added `NEW_EXECUTE_COMMAND_SYNTAX_EXPERIMENT_AND_CHEST_LOOT_TABLE_FIX_AND_TELEPORT_FACING_VERTICAL_UNCLAMPED_AND_LOCATE_BIOME_AND_FEATURE_MERGED`
* Added `WATERLOGGING_ADDED_TO_STRUCTURE_COMMAND`
* Added `SELECTOR_DISTANCE_FILTERED_AND_RELATIVE_ROTATION_FIX`
* Added `NEW_SUMMON_COMMAND_ADDED_ROTATION_OPTIONS_AND_BUBBLE_COLUMN_CLONE_FIX_AND_EXECUTE_IN_DIMENSION_TELEPORT_FIX_AND_NEW_EXECUTE_ROTATION_FIX`
* Added `NEW_EXECUTE_COMMAND_RELEASE_ENCHANT_COMMAND_LEVEL_FIX_AND_HAS_ITEM_DATA_FIX_AND_COMMAND_DEFERRAL`
* Added `EXECUTE_IF_SCORE_FIXES`
* Added `REPLACE_ITEM_AND_LOOT_REPLACE_BLOCK_COMMANDS_DO_NOT_PLACE_ITEMS_INTO_CAULDRONS_FIX`
* Added `CHANGES_TO_COMMAND_ORIGIN_ROTATION`
* Added `REMOVE_AUX_VALUE_PARAMETER_FROM_BLOCK_COMMANDS`
* Added `VOLUME_SELECTOR_FIXES`
* Added `ENABLE_SUMMON_ROTATION`
* Added `SUMMON_COMMAND_DEFAULT_ROTATION`
* Added `POSITIONAL_DIMENSION_FILTERING`
* Added `COMMAND_SELECTOR_HAS_ITEM_FILTER_NO_LONGER_CALLS_SAME_ITEM_FUNCTION`
* Added `AGENT_SWEEPING_BLOCK_TEST`
* Added `BLOCK_STATE_EQUALS`
* Added `COMMAND_POSITION_FIX`
* Added `COMMAND_SELECTOR_HAS_ITEM_FILTER_USES_DATA_AS_DAMAGE_FOR_SELECTING_DAMAGEABLE_ITEMS`
* Added `EXECUTE_DETECT_CONDITION_SUBCOMMAND_NOT_ALLOW_NON_LOADED_BLOCKS`
* Added `REMOVE_SUICIDE_KEYWORD`
* Added `CLONE_CONTAINER_BLOCK_ENTITY_REMOVAL_FIX`
* Added `STOP_SOUND_MUSIC_FIX`
* Added `SPREAD_PLAYERS_STUCK_IN_GROUND_FIX_AND_MAX_HEIGHT_PARAMETER`
* Added `LOCATE_STRUCTURE_OUTPUT`
* Added `POST_BLOCK_FLATTENING`
* Added `TEST_FOR_BLOCK_COMMAND_DOES_NOT_IGNORE_BLOCK_STATE`
* Added `COUNT`
* Added `LATEST`

DataDrivenScreenClosedReason:
* Added `PROGRAMMATIC_CLOSE`
* Added `PROGRAMMATIC_CLOSE_ALL`
* Added `CLIENT_CANCELED`
* Added `USER_BUSY`
* Added `INVALID_FORM`

DataStoreType:
* Added `UPDATE`
* Added `CHANGE`
* Added `REMOVAL`

DataStoreUpdate:
* Added `DOUBLE`
* Added `BOOLEAN`

DebugShape:
* Removed `LINE`
* Removed `BOX`
* Removed `SPHERE`
* Removed `CIRCLE`
* Removed `TEXT`

DefaultGameType:
* Added `SURVIVAL`
* Added `CREATIVE`
* Added `ADVENTURE`
* Added `DEFAULT`
* Added `SPECTATOR`
* Added `WORLD_DEFAULT`

Difficulty:
* Added `PEACEFUL`
* Added `EASY`
* Added `NORMAL`
* Added `HARD`
* Added `COUNT`
* Added `UNKNOWN`

Dimension:
* Added `OVERWORLD`
* Added `NETHER`
* Added `THE_END`
* Added `UNDEFINED`
* Added `CUSTOM`

DisconnectFailReason:
* Added `UNKNOWN`
* Added `CANT_CONNECT_NO_INTERNET`
* Added `NO_PERMISSIONS`
* Added `UNRECOVERABLE_ERROR`
* Added `THIRD_PARTY_BLOCKED`
* Added `THIRD_PARTY_NO_INTERNET`
* Added `THIRD_PARTY_BAD_IP`
* Added `THIRD_PARTY_NO_SERVER_OR_SERVER_LOCKED`
* Added `VERSION_MISMATCH`
* Added `SKIN_ISSUE`
* Added `INVITE_SESSION_NOT_FOUND`
* Added `EDU_LEVEL_SETTINGS_MISSING`
* Added `LOCAL_SERVER_NOT_FOUND`
* Added `LEGACY_DISCONNECT`
* Added `INTERNAL_USER_LEAVE_GAME_ATTEMPTED`
* Added `PLATFORM_LOCKED_SKINS_ERROR`
* Added `REALMS_WORLD_UNASSIGNED`
* Added `REALMS_SERVER_CANT_CONNECT`
* Added `REALMS_SERVER_HIDDEN`
* Added `REALMS_SERVER_DISABLED_BETA`
* Added `REALMS_SERVER_DISABLED`
* Added `CROSS_PLATFORM_DISABLED`
* Added `TESTONLY_CANT_CONNECT`
* Added `SESSION_NOT_FOUND`
* Added `CLIENT_SETTINGS_INCOMPATIBLE_WITH_SERVER`
* Added `SERVER_FULL`
* Added `INVALID_PLATFORM_SKIN`
* Added `EDITION_VERSION_MISMATCH`
* Added `EDITION_MISMATCH`
* Added `LEVEL_NEWER_THAN_EXE_VERSION`
* Added `INTERNAL_NO_FAIL_OCCURRED`
* Added `BANNED_SKIN`
* Added `TIMEOUT`
* Added `SERVER_NOT_FOUND`
* Added `OUTDATED_SERVER`
* Added `OUTDATED_CLIENT`
* Added `NO_PREMIUM_PLATFORM`
* Added `MULTIPLAYER_DISABLED`
* Added `NO_WI_FI`
* Added `WORLD_CORRUPTION`
* Added `NO_REASON`
* Added `DISCONNECTED`
* Added `INVALID_PLAYER`
* Added `LOGGED_IN_OTHER_LOCATION`
* Added `SERVER_ID_CONFLICT`
* Added `NOT_ALLOWED`
* Added `NOT_AUTHENTICATED`
* Added `INVALID_TENANT`
* Added `UNKNOWN_PACKET`
* Added `UNEXPECTED_PACKET`
* Added `INVALID_COMMAND_REQUEST_PACKET`
* Added `HOST_SUSPENDED`
* Added `LOGIN_PACKET_NO_REQUEST`
* Added `LOGIN_PACKET_NO_CERT`
* Added `MISSING_CLIENT`
* Added `KICKED`
* Added `KICKED_FOR_EXPLOIT`
* Added `KICKED_FOR_IDLE`
* Added `RESOURCE_PACK_PROBLEM`
* Added `INCOMPATIBLE_PACK`
* Added `OUT_OF_STORAGE`
* Added `INVALID_LEVEL`
* Added `DISCONNECT_PACKET`
* Added `BLOCK_MISMATCH`
* Added `INVALID_HEIGHTS`
* Added `INVALID_WIDTHS`
* Added `CONNECTION_LOST`
* Added `ZOMBIE_CONNECTION`
* Added `SHUTDOWN`
* Added `REASON_NOT_SET`
* Added `LOADING_STATE_TIMEOUT`
* Added `RESOURCE_PACK_LOADING_FAILED`
* Added `SEARCHING_FOR_SESSION_LOADING_SCREEN_FAILED`
* Added `NETHER_NET_PROTOCOL_VERSION`
* Added `SUBSYSTEM_STATUS_ERROR`
* Added `EMPTY_AUTH_FROM_DISCOVERY`
* Added `EMPTY_URL_FROM_DISCOVERY`
* Added `EXPIRED_AUTH_FROM_DISCOVERY`
* Added `UNKNOWN_SIGNAL_SERVICE_SIGN_IN_FAILURE`
* Added `XBLJOIN_LOBBY_FAILURE`
* Added `UNSPECIFIED_CLIENT_INSTANCE_DISCONNECTION`
* Added `NETHER_NET_SESSION_NOT_FOUND`
* Added `NETHER_NET_CREATE_PEER_CONNECTION`
* Added `NETHER_NET_ICE`
* Added `NETHER_NET_CONNECT_REQUEST`
* Added `NETHER_NET_CONNECT_RESPONSE`
* Added `NETHER_NET_NEGOTIATION_TIMEOUT`
* Added `NETHER_NET_INACTIVITY_TIMEOUT`
* Added `STALE_CONNECTION_BEING_REPLACED`
* Added `REALMS_SESSION_NOT_FOUND`
* Added `BAD_PACKET`
* Added `NETHER_NET_FAILED_TO_CREATE_OFFER`
* Added `NETHER_NET_FAILED_TO_CREATE_ANSWER`
* Added `NETHER_NET_FAILED_TO_SET_LOCAL_DESCRIPTION`
* Added `NETHER_NET_FAILED_TO_SET_REMOTE_DESCRIPTION`
* Added `NETHER_NET_NEGOTIATION_TIMEOUT_WAITING_FOR_RESPONSE`
* Added `NETHER_NET_NEGOTIATION_TIMEOUT_WAITING_FOR_ACCEPT`
* Added `NETHER_NET_INCOMING_CONNECTION_IGNORED`
* Added `NETHER_NET_SIGNALING_PARSING_FAILURE`
* Added `NETHER_NET_SIGNALING_UNKNOWN_ERROR`
* Added `NETHER_NET_SIGNALING_UNICAST_DELIVERY_FAILED`
* Added `NETHER_NET_SIGNALING_BROADCAST_DELIVERY_FAILED`
* Added `NETHER_NET_SIGNALING_GENERIC_DELIVERY_FAILED`
* Added `EDITOR_MISMATCH_EDITOR_WORLD`
* Added `EDITOR_MISMATCH_VANILLA_WORLD`
* Added `WORLD_TRANSFER_NOT_PRIMARY_CLIENT`
* Added `INTERNAL_REQUEST_SERVER_SHUTDOWN`
* Added `CLIENT_GAME_SETUP_CANCELLED`
* Added `CLIENT_GAME_SETUP_FAILED`
* Added `NO_VENUE`
* Added `NETHER_NET_SIGNALING_SIGNIN_FAILED`
* Added `SESSION_ACCESS_DENIED`
* Added `SERVICE_SIGNIN_ISSUE`
* Added `NETHER_NET_NO_SIGNALING_CHANNEL`
* Added `NETHER_NET_NOT_LOGGED_IN`
* Added `NETHER_NET_CLIENT_SIGNALING_ERROR`
* Added `SUB_CLIENT_LOGIN_DISABLED`
* Added `DEEP_LINK_TRYING_TO_OPEN_DEMO_WORLD_WHILE_SIGNED_IN`
* Added `ASYNC_JOIN_TASK_DENIED`
* Added `REALMS_TIMELINE_REQUIRED`
* Added `GUEST_WITHOUT_HOST`
* Added `FAILED_TO_JOIN_EXPERIENCE`
* Added `NETHER_NET_DATA_CHANNEL_CLOSED`
* Added `DISCOVERY_ENVIRONMENT_MISMATCH`
* Added `HOST_WITHOUT_KEYS`
* Added `HOST_SIGNED_OUT`
* Added `SCRIPT_WATCHDOG_EXCEPTION`
* Added `SCRIPT_MEMORY_LIMIT_EXCEEDED`
* Added `STORAGE_LOW_DURING_GAMEPLAY`
* Added `STORAGE_FULL_DURING_GAMEPLAY`
* Added `LEVEL_STORAGE_CORRUPTION`
* Added `EDITION_MISMATCH_VANILLA_TO_EDU`
* Added `EDITION_MISMATCH_EDU_TO_VANILLA`
* Added `EDITOR_MISMATCH_EDITOR_TO_VANILLA`
* Added `EDITOR_MISMATCH_VANILLA_TO_EDITOR`
* Added `DENY_LISTED`
* Added `NONCE_MISSING`
* Added `NONCE_NOT_FOUND`
* Added `NONCE_EXPIRED`
* Added `NONCE_NOT_VALID`
* Added `HOST_DISCONNECTED`
* Added `EDITOR_JOIN_INTENT_POLICY_FAILURE`
* Added `NETHER_NET_IDENTITY_NOT_ALLOWED`
* Added `INVALID_NAME`
* Added `EXPIRED_TOKEN`
* Added `HOST_ACCEPTS_NO_TYPE_OF_AUTH`
* Added `NOT_AUTHENTICATED_FAST_FAIL`
* Added `EDITOR_NOT_ALLOWED`
* Added `MISSING_STRUCTURE_DATA`
* Added `UNSUPPORTED_TRANSPORT`

DisconnectFailReason:
* Removed `UNKNOWN`
* Removed `CANT_CONNECT_NO_INTERNET`
* Removed `NO_PERMISSIONS`
* Removed `UNRECOVERABLE_ERROR`
* Removed `THIRD_PARTY_BLOCKED`
* Removed `THIRD_PARTY_NO_INTERNET`
* Removed `THIRD_PARTY_BAD_IP`
* Removed `THIRD_PARTY_NO_SERVER_OR_SERVER_LOCKED`
* Removed `VERSION_MISMATCH`
* Removed `SKIN_ISSUE`
* Removed `INVITE_SESSION_NOT_FOUND`
* Removed `EDU_LEVEL_SETTINGS_MISSING`
* Removed `LOCAL_SERVER_NOT_FOUND`
* Removed `LEGACY_DISCONNECT`
* Removed `USER_LEAVE_GAME_ATTEMPTED`
* Removed `PLATFORM_LOCKED_SKINS_ERROR`
* Removed `REALMS_WORLD_UNASSIGNED`
* Removed `REALMS_SERVER_CANT_CONNECT`
* Removed `REALMS_SERVER_HIDDEN`
* Removed `REALMS_SERVER_DISABLED_BETA`
* Removed `REALMS_SERVER_DISABLED`
* Removed `CROSS_PLATFORM_DISABLED`
* Removed `CANT_CONNECT`
* Removed `SESSION_NOT_FOUND`
* Removed `CLIENT_SETTINGS_INCOMPATIBLE_WITH_SERVER`
* Removed `SERVER_FULL`
* Removed `INVALID_PLATFORM_SKIN`
* Removed `EDITION_VERSION_MISMATCH`
* Removed `EDITION_MISMATCH`
* Removed `LEVEL_NEWER_THAN_EXE_VERSION`
* Removed `NO_FAIL_OCCURRED`
* Removed `BANNED_SKIN`
* Removed `TIMEOUT`
* Removed `SERVER_NOT_FOUND`
* Removed `OUTDATED_SERVER`
* Removed `OUTDATED_CLIENT`
* Removed `NO_PREMIUM_PLATFORM`
* Removed `MULTIPLAYER_DISABLED`
* Removed `NO_WIFI`
* Removed `WORLD_CORRUPTION`
* Removed `NO_REASON`
* Removed `DISCONNECTED`
* Removed `INVALID_PLAYER`
* Removed `LOGGED_IN_OTHER_LOCATION`
* Removed `SERVER_ID_CONFLICT`
* Removed `NOT_ALLOWED`
* Removed `NOT_AUTHENTICATED`
* Removed `INVALID_TENANT`
* Removed `UNKNOWN_PACKET`
* Removed `UNEXPECTED_PACKET`
* Removed `INVALID_COMMAND_REQUEST_PACKET`
* Removed `HOST_SUSPENDED`
* Removed `LOGIN_PACKET_NO_REQUEST`
* Removed `LOGIN_PACKET_NO_CERT`
* Removed `MISSING_CLIENT`
* Removed `KICKED`
* Removed `KICKED_FOR_EXPLOIT`
* Removed `KICKED_FOR_IDLE`
* Removed `RESOURCE_PACK_PROBLEM`
* Removed `INCOMPATIBLE_PACK`
* Removed `OUT_OF_STORAGE`
* Removed `INVALID_LEVEL`
* Removed `DISCONNECT_PACKET_DEPRECATED`
* Removed `BLOCK_MISMATCH`
* Removed `INVALID_HEIGHTS`
* Removed `INVALID_WIDTHS`
* Removed `CONNECTION_LOST`
* Removed `ZOMBIE_CONNECTION`
* Removed `SHUTDOWN`
* Removed `REASON_NOT_SET`
* Removed `LOADING_STATE_TIMEOUT`
* Removed `RESOURCE_PACK_LOADING_FAILED`
* Removed `SEARCHING_FOR_SESSION_LOADING_SCREEN_FAILED`
* Removed `CONN_PROTOCOL_VERSION`
* Removed `SUBSYSTEM_STATUS_ERROR`
* Removed `EMPTY_AUTH_FROM_DISCOVERY`
* Removed `EMPTY_URL_FROM_DISCOVERY`
* Removed `EXPIRED_AUTH_FROM_DISCOVERY`
* Removed `UNKNOWN_SIGNAL_SERVICE_SIGN_IN_FAILURE`
* Removed `XBL_JOIN_LOBBY_FAILURE`
* Removed `UNSPECIFIED_CLIENT_INSTANCE_DISCONNECTION`
* Removed `CONN_SESSION_NOT_FOUND`
* Removed `CONN_CREATE_PEER_CONNECTION`
* Removed `CONN_ICE`
* Removed `CONN_CONNECT_REQUEST`
* Removed `CONN_CONNECT_RESPONSE`
* Removed `CONN_NEGOTIATION_TIMEOUT`
* Removed `CONN_INACTIVITY_TIMEOUT`
* Removed `STALE_CONNECTION_BEING_REPLACED`
* Removed `REALMS_SESSION_NOT_FOUND_DEPRECATED`
* Removed `BAD_PACKET`
* Removed `CONN_FAILED_TO_CREATE_OFFER`
* Removed `CONN_FAILED_TO_CREATE_ANSWER`
* Removed `CONN_FAILED_TO_SET_LOCAL_DESCRIPTION`
* Removed `CONN_FAILED_TO_SET_REMOTE_DESCRIPTION`
* Removed `CONN_NEGOTIATION_TIMEOUT_WAITING_FOR_RESPONSE`
* Removed `CONN_NEGOTIATION_TIMEOUT_WAITING_FOR_ACCEPT`
* Removed `CONN_INCOMING_CONNECTION_IGNORED`
* Removed `CONN_SIGNALING_PARSING_FAILURE`
* Removed `CONN_SIGNALING_UNKNOWN_ERROR`
* Removed `CONN_SIGNALING_UNICAST_DELIVERY_FAILED`
* Removed `CONN_SIGNALING_BROADCAST_DELIVERY_FAILED`
* Removed `CONN_SIGNALING_GENERIC_DELIVERY_FAILED`
* Removed `EDITOR_MISMATCH_EDITOR_WORLD`
* Removed `EDITOR_MISMATCH_VANILLA_WORLD`
* Removed `WORLD_TRANSFER_NOT_PRIMARY_CLIENT`
* Removed `SERVER_SHUTDOWN`
* Removed `GAME_SETUP_CANCELLED`
* Removed `GAME_SETUP_FAILED`
* Removed `NO_VENUE`
* Removed `CONN_SIGNALING_SIGN_IN_FAILED`
* Removed `SESSION_ACCESS_DENIED`
* Removed `SERVICE_SIGN_IN_ISSUE`
* Removed `CONN_NO_SIGNALING_CHANNEL`
* Removed `CONN_NOT_LOGGED_IN`
* Removed `CONN_CLIENT_SIGNALING_ERROR`
* Removed `SUB_CLIENT_LOGIN_DISABLED`
* Removed `DEEP_LINK_TRYING_TO_OPEN_DEMO_WORLD_WHILE_SIGNED_IN`
* Removed `ASYNC_JOIN_TASK_DENIED`
* Removed `REALMS_TIMELINE_REQUIRED`
* Removed `GUEST_WITHOUT_HOST`
* Removed `FAILED_TO_JOIN_EXPERIENCE`
* Removed `NETHER_NET_DATA_CHANNEL_CLOSED`

DynamicValueType:
* Added `NULL`
* Added `BOOLEAN`
* Added `INTEGER`
* Added `NUMBER`
* Added `STRING`
* Added `ARRAY`
* Added `OBJECT`

EasingFunction:
* Added `LINEAR`
* Added `SPRING`
* Added `IN_QUAD`
* Added `OUT_QUAD`
* Added `IN_OUT_QUAD`
* Added `IN_CUBIC`
* Added `OUT_CUBIC`
* Added `IN_OUT_CUBIC`
* Added `IN_QUART`
* Added `OUT_QUART`
* Added `IN_OUT_QUART`
* Added `IN_QUINT`
* Added `OUT_QUINT`
* Added `IN_OUT_QUINT`
* Added `IN_SINE`
* Added `OUT_SINE`
* Added `IN_OUT_SINE`
* Added `IN_EXPO`
* Added `OUT_EXPO`
* Added `IN_OUT_EXPO`
* Added `IN_CIRC`
* Added `OUT_CIRC`
* Added `IN_OUT_CIRC`
* Added `IN_BOUNCE`
* Added `OUT_BOUNCE`
* Added `IN_OUT_BOUNCE`
* Added `IN_BACK`
* Added `OUT_BACK`
* Added `IN_OUT_BACK`
* Added `IN_ELASTIC`
* Added `OUT_ELASTIC`
* Added `IN_OUT_ELASTIC`

EditorWorldType:
* Added `NON_EDITOR`
* Added `EDITOR_PROJECT`
* Added `EDITOR_TEST_LEVEL`
* Added `EDITOR_REALMS_UPLOAD`

EducationEditionOffer:
* Added `NONE`
* Added `REST_OF_WORLD`
* Added `CHINA_DEPRECATED`

EducationEditionOffer:
* Removed `NONE`
* Removed `REST_OF_WORLD`

EnchantType:
* Added `PROTECTION`
* Added `FIRE_PROTECTION`
* Added `FEATHER_FALLING`
* Added `BLAST_PROTECTION`
* Added `PROJECTILE_PROTECTION`
* Added `THORNS`
* Added `RESPIRATION`
* Added `DEPTH_STRIDER`
* Added `AQUA_AFFINITY`
* Added `SHARPNESS`
* Added `SMITE`
* Added `BANE_OF_ARTHROPODS`
* Added `KNOCKBACK`
* Added `FIRE_ASPECT`
* Added `LOOTING`
* Added `EFFICIENCY`
* Added `SILK_TOUCH`
* Added `UNBREAKING`
* Added `FORTUNE`
* Added `POWER`
* Added `PUNCH`
* Added `FLAME`
* Added `INFINITY`
* Added `LUCK_OF_THE_SEA`
* Added `LURE`
* Added `FROST_WALKER`
* Added `MENDING`
* Added `CURSE_OF_BINDING`
* Added `CURSE_OF_VANISHING`
* Added `IMPALING`
* Added `RIPTIDE`
* Added `LOYALTY`
* Added `CHANNELING`
* Added `MULTISHOT`
* Added `PIERCING`
* Added `QUICK_CHARGE`
* Added `SOUL_SPEED`
* Added `SWIFT_SNEAK`
* Added `WIND_BURST`
* Added `DENSITY`
* Added `BREACH`
* Added `LUNGE`
* Added `NUM_ENCHANTMENTS`
* Added `INVALID_ENCHANTMENT`

EntityDamageCause:
* Removed `OVERRIDE`
* Removed `CONTACT`
* Removed `ENTITY_ATTACK`
* Removed `PROJECTILE`
* Removed `SUFFOCATION`
* Removed `FALL`
* Removed `FIRE`
* Removed `FIRE_TICK`
* Removed `LAVA`
* Removed `DROWNING`
* Removed `BLOCK_EXPLOSION`
* Removed `ENTITY_EXPLOSION`
* Removed `VOID`
* Removed `SUICIDE`
* Removed `MAGIC`
* Removed `WITHER`
* Removed `STARVE`
* Removed `ANVIL`
* Removed `THORNS`
* Removed `FALLING_BLOCK`
* Removed `PISTON`
* Removed `FLY_INTO_WALL`
* Removed `MAGMA`
* Removed `FIREWORKS`
* Removed `LIGHTNING`
* Removed `CHARGING`
* Removed `TEMPERATURE`
* Removed `FREEZING`
* Removed `STALACTITE`
* Removed `STALAGMITE`
* Removed `CAMPFIRE`
* Removed `SOUL_CAMPFIRE`
* Removed `MACE_SMASH`

EntityEventType:
* Removed `NONE`
* Removed `JUMP`
* Removed `HURT`
* Removed `DEATH`
* Removed `ATTACK_START`
* Removed `ATTACK_STOP`
* Removed `TAME_FAILED`
* Removed `TAME_SUCCEEDED`
* Removed `SHAKE_WETNESS`
* Removed `USE_ITEM`
* Removed `EAT_GRASS`
* Removed `FISH_HOOK_BUBBLE`
* Removed `FISH_HOOK_POSITION`
* Removed `FISH_HOOK_TIME`
* Removed `FISH_HOOK_TEASE`
* Removed `SQUID_FLEEING`
* Removed `ZOMBIE_VILLAGER_CURE`
* Removed `PLAY_AMBIENT`
* Removed `RESPAWN`
* Removed `GOLEM_FLOWER_OFFER`
* Removed `GOLEM_FLOWER_WITHDRAW`
* Removed `VILLAGER_ANGRY`
* Removed `LOVE_PARTICLES`
* Removed `VILLAGER_HAPPY`
* Removed `WITCH_HAT_MAGIC`
* Removed `FIREWORK_EXPLODE`
* Removed `IN_LOVE_HEARTS`
* Removed `SILVERFISH_MERGE_WITH_STONE`
* Removed `GUARDIAN_ATTACK_ANIMATION`
* Removed `WITCH_DRINK_POTION`
* Removed `WITCH_THROW_POTION`
* Removed `PRIME_TNT_MINECART`
* Removed `PRIME_CREEPER`
* Removed `AIR_SUPPLY`
* Removed `PLAYER_ADD_XP_LEVELS`
* Removed `ELDER_GUARDIAN_CURSE`
* Removed `AGENT_ARM_SWING`
* Removed `ENDER_DRAGON_DEATH`
* Removed `DUST_PARTICLES`
* Removed `ARROW_SHAKE`
* Removed `EATING_ITEM`
* Removed `BABY_ANIMAL_FEED`
* Removed `DEATH_SMOKE_CLOUD`
* Removed `COMPLETE_TRADE`
* Removed `REMOVE_LEASH`
* Removed `CARAVAN`
* Removed `CONSUME_TOTEM`
* Removed `CHECK_TREASURE_HUNTER_ACHIEVEMENT`
* Removed `ENTITY_SPAWN`
* Removed `DRAGON_FLAMING`
* Removed `UPDATE_ITEM_STACK_SIZE`
* Removed `START_SWIMMING`
* Removed `BALLOON_POP`
* Removed `TREASURE_HUNT`
* Removed `SUMMON_AGENT`
* Removed `FINISHED_CHARGING_ITEM`
* Removed `LANDED_ON_GROUND`
* Removed `ENTITY_GROW_UP`
* Removed `VIBRATION_DETECTED`

EntityLinkData:
* Removed `REMOVE`
* Removed `RIDER`
* Removed `PASSENGER`

EventDataType:
* Removed `ACHIEVEMENT_AWARDED`
* Removed `ENTITY_INTERACT`
* Removed `PORTAL_BUILT`
* Removed `PORTAL_USED`
* Removed `MOB_KILLED`
* Removed `CAULDRON_USED`
* Removed `PLAYER_DIED`
* Removed `BOSS_KILLED`
* Removed `AGENT_COMMAND`
* Removed `AGENT_CREATED`
* Removed `PATTERN_REMOVED`
* Removed `SLASH_COMMAND_EXECUTED`
* Removed `FISH_BUCKETED`
* Removed `MOB_BORN`
* Removed `PET_DIED`
* Removed `CAULDRON_INTERACT`
* Removed `COMPOSTER_INTERACT`
* Removed `BELL_USED`
* Removed `ENTITY_DEFINITION_TRIGGER`
* Removed `RAID_UPDATE`
* Removed `MOVEMENT_ANOMALY`
* Removed `MOVEMENT_CORRECTED`
* Removed `EXTRACT_HONEY`
* Removed `TARGET_BLOCK_HIT`
* Removed `PIGLIN_BARTER`
* Removed `COPPER_WAXED_OR_UNWAXED`
* Removed `CODE_BUILDER_ACTION`
* Removed `CODE_BUILDER_SCOREBOARD`
* Removed `STRIDER_RIDDEN_IN_LAVA_IN_OVERWORLD`
* Removed `SNEAK_CLOSE_TO_SCULK_SENSOR`
* Removed `CAREFUL_RESTORATION`

ExpressionOp:
* Added `NON_EVALUATED_ARRAY`
* Added `INVERSE_LERP`
* Added `EASE_IN_QUAD`
* Added `EASE_OUT_QUAD`
* Added `EASE_IN_OUT_QUAD`
* Added `EASE_IN_CUBIC`
* Added `EASE_OUT_CUBIC`
* Added `EASE_IN_OUT_CUBIC`
* Added `EASE_IN_QUART`
* Added `EASE_OUT_QUART`
* Added `EASE_IN_OUT_QUART`
* Added `EASE_IN_QUINT`
* Added `EASE_OUT_QUINT`
* Added `EASE_IN_OUT_QUINT`
* Added `EASE_IN_SINE`
* Added `EASE_OUT_SINE`
* Added `EASE_IN_OUT_SINE`
* Added `EASE_IN_EXPO`
* Added `EASE_OUT_EXPO`
* Added `EASE_IN_OUT_EXPO`
* Added `EASE_IN_CIRC`
* Added `EASE_OUT_CIRC`
* Added `EASE_IN_OUT_CIRC`
* Added `EASE_IN_BOUNCE`
* Added `EASE_OUT_BOUNCE`
* Added `EASE_IN_OUT_BOUNCE`
* Added `EASE_IN_BACK`
* Added `EASE_OUT_BACK`
* Added `EASE_IN_OUT_BACK`
* Added `EASE_IN_ELASTIC`
* Added `EASE_OUT_ELASTIC`
* Added `EASE_IN_OUT_ELASTIC`

ExtraShapeDataType:
* Added `NONE`
* Added `ARROW`
* Added `TEXT`
* Added `BOX`
* Added `LINE`
* Added `SPHERE`
* Added `CYLINDER`
* Added `PYRAMID`
* Added `ELLIPSOID`
* Added `CONE`

FloatAttributeOperation:
* Added `OVERRIDE`
* Added `ALPHA_BLEND`
* Added `ADD`
* Added `SUBTRACT`
* Added `MULTIPLY`
* Added `MINIMUM`
* Added `MAXIMUM`

FurnaceLayout:
* Added `NONE`
* Added `INVENTORY_ONLY`
* Added `DEFAULT`

FurnaceLeftTabIndex:
* Added `NONE`
* Added `RECIPE_FOOD`
* Added `RECIPE_ITEMS`
* Added `RECIPE_BLOCKS`
* Added `RECIPE_SEARCH`
* Added `INVENTORY`

GamePublishSetting:
* Added `NO_MULTI_PLAY`
* Added `INVITE_ONLY`
* Added `FRIENDS_ONLY`
* Added `FRIENDS_OF_FRIENDS`
* Added `PUBLIC`

GamePublishSetting:
* Removed `NO_MULTI_PLAY`
* Removed `INVITE_ONLY`
* Removed `FRIENDS_ONLY`
* Removed `FRIENDS_OF_FRIENDS`
* Removed `PUBLIC`

GameType:
* Added `UNDEFINED`
* Added `SURVIVAL`
* Added `CREATIVE`
* Added `ADVENTURE`
* Added `DEFAULT`
* Added `SPECTATOR`
* Added `WORLD_DEFAULT`

GameType:
* Removed `SURVIVAL`
* Removed `CREATIVE`
* Removed `ADVENTURE`
* Removed `SURVIVAL_VIEWER`
* Removed `CREATIVE_VIEWER`
* Removed `DEFAULT`
* Removed `SPECTATOR`

GeneratorType:
* Added `LEGACY`
* Added `OVERWORLD`
* Added `FLAT`
* Added `NETHER`
* Added `THE_END`
* Added `VOID`
* Added `UNDEFINED`

GraphicsMode:
* Added `SIMPLE`
* Added `FANCY`
* Added `ADVANCED`
* Added `RAY_TRACED`

GraphicsMode:
* Removed `SIMPLE`
* Removed `FANCY`
* Removed `ADVANCED`
* Removed `RAY_TRACED`

GraphicsOverrideParameterType:
* Added `SKY_ZENITH_COLOR`
* Added `SKY_HORIZON_COLOR`
* Added `HORIZON_BLEND_MIN`
* Added `HORIZON_BLEND_MAX`
* Added `HORIZON_BLEND_START`
* Added `HORIZON_BLEND_MIE_START`
* Added `RAYLEIGH_STRENGTH`
* Added `SUN_MIE_STRENGTH`
* Added `MOON_MIE_STRENGTH`
* Added `SUN_GLARE_SHAPE`
* Added `CHLOROPHYLL`
* Added `CDOM`
* Added `SUSPENDED_SEDIMENT`
* Added `WAVES_DEPTH`
* Added `WAVES_FREQUENCY`
* Added `WAVES_FREQUENCY_SCALING`
* Added `WAVES_SPEED`
* Added `WAVES_SPEED_SCALING`
* Added `WAVES_SHAPE`
* Added `WAVES_OCTAVES`
* Added `WAVES_MIX`
* Added `WAVES_PULL`
* Added `WAVES_DIRECTION_INCREMENT`
* Added `MIDTONES_CONTRAST`
* Added `HIGHLIGHTS_CONTRAST`
* Added `SHADOWS_CONTRAST`
* Added `HIGHLIGHTS_GAIN`
* Added `HIGHLIGHTS_GAMMA`
* Added `HIGHLIGHTS_OFFSET`
* Added `HIGHLIGHTS_SATURATION`
* Added `MIDTONES_GAIN`
* Added `MIDTONES_GAMMA`
* Added `MIDTONES_OFFSET`
* Added `MIDTONES_SATURATION`
* Added `SHADOWS_GAIN`
* Added `SHADOWS_GAMMA`
* Added `SHADOWS_OFFSET`
* Added `SHADOWS_SATURATION`
* Added `HIGHLIGHTS_MIN`
* Added `SHADOWS_MAX`
* Added `TEMPERATURE`
* Added `SUN_COLOR`
* Added `SUN_ILLUMINANCE`
* Added `MOON_COLOR`
* Added `MOON_ILLUMINANCE`
* Added `FLASH_COLOR`
* Added `FLASH_ILLUMINANCE`
* Added `AMBIENT_COLOR`
* Added `AMBIENT_ILLUMINANCE`
* Added `EMISSIVE_DESATURATION`
* Added `SKY_INTENSITY`
* Added `ORBITAL_OFFSET_DEGREES`

HandSlot:
* Added `MAINHAND`
* Added `OFFHAND`

HeightMapDataType:
* Added `NO_DATA`
* Added `HAS_DATA`
* Added `TOO_HIGH`
* Added `TOO_LOW`
* Added `ALL_COPIED`

HeightMapDataType:
* Removed `NO_DATA`
* Removed `HAS_DATA`
* Removed `TOO_HIGH`
* Removed `TOO_LOW`

HudElement:
* Added `PAPER_DOLL`
* Added `ARMOR`
* Added `TOOL_TIPS`
* Added `TOUCH_CONTROLS`
* Added `CROSSHAIR`
* Added `HOT_BAR`
* Added `HEALTH`
* Added `PROGRESS_BAR`
* Added `HUNGER`
* Added `AIR_BUBBLES`
* Added `HORSE_HEALTH`
* Added `STATUS_EFFECTS`
* Added `ITEM_TEXT`

HudElement:
* Removed `PAPER_DOLL`
* Removed `ARMOR`
* Removed `TOOL_TIPS`
* Removed `TOUCH_CONTROLS`
* Removed `CROSSHAIR`
* Removed `HOTBAR`
* Removed `HEALTH`
* Removed `PROGRESS_BAR`
* Removed `FOOD_BAR`
* Removed `AIR_BUBBLES_BAR`
* Removed `VEHICLE_HEALTH`
* Removed `EFFECTS_BAR`
* Removed `ITEM_TEXT_POPUP`

HudVisibility:
* Added `HIDE`
* Added `RESET`

HudVisibility:
* Removed `HIDE`

InputInteractionModel:
* Removed `TOUCH`
* Removed `CROSSHAIR`

InputMode:
* Added `UNDEFINED`
* Added `MOUSE`
* Added `TOUCH`
* Added `GAME_PAD`
* Added `COUNT`

InputMode:
* Removed `UNDEFINED`
* Removed `MOUSE`
* Removed `TOUCH`
* Removed `GAMEPAD`
* Removed `MOTION_CONTROLLER`

InteractionType:
* Added `BREEDING`
* Added `TAMING`
* Added `CURING`
* Added `CRAFTED`
* Added `SHEARING`
* Added `MILKING`
* Added `TRADING`
* Added `FEEDING`
* Added `IGNITING`
* Added `COLORING`
* Added `NAMING`
* Added `LEASHING`
* Added `UNLEASHING`
* Added `PET_SLEEP`
* Added `TRUSTING`
* Added `COMMANDING`
* Added `EQUIPPING`

InventoryLayout:
* Added `INVENTORY_ONLY`
* Added `DEFAULT`
* Added `RECIPE_BOOK_ONLY`
* Removed `SURVIVAL`
* Removed `RECIPE_BOOK`
* Removed `CREATIVE`

InventoryLeftTabIndex:
* Added `NONE`
* Added `RECIPE_CONSTRUCTION`
* Added `RECIPE_EQUIPMENT`
* Added `RECIPE_ITEMS`
* Added `RECIPE_NATURE`
* Added `RECIPE_SEARCH`
* Added `SURVIVAL`

InventoryRightTabIndex:
* Added `NONE`
* Added `FULL_SCREEN`
* Added `CRAFTING`
* Added `ARMOR`

InventorySource:
* Removed `INVALID`
* Removed `CONTAINER`
* Removed `GLOBAL`
* Removed `WORLD_INTERACTION`
* Removed `CREATIVE`
* Removed `UNTRACKED_INTERACTION_UI`
* Removed `NON_IMPLEMENTED_TODO`
* Removed `DROP_ITEM`
* Removed `PICKUP_ITEM`

InventorySourceFlags:
* Added `NO_FLAG`
* Added `WORLD_INTERACTION_RANDOM`

InventorySourceType:
* Added `CONTAINER_INVENTORY`
* Added `GLOBAL_INVENTORY`
* Added `WORLD_INTERACTION`
* Added `CREATIVE_INVENTORY`
* Added `NON_IMPLEMENTED_FEATURE_TODO`

InventoryTabLeft:
* Removed `NONE`
* Removed `RECIPE_CONSTRUCTION`
* Removed `RECIPE_EQUIPMENT`
* Removed `RECIPE_ITEMS`
* Removed `RECIPE_NATURE`
* Removed `RECIPE_SEARCH`
* Removed `SURVIVAL`

InventoryTabRight:
* Removed `NONE`
* Removed `FULL_SCREEN`
* Removed `CRAFTING`
* Removed `ARMOR`

InventoryTransactionDataType:
* Added `MISMATCH`
* Added `ITEM_USE_ON_ACTOR`
* Removed `INVENTORY_MISMATCH`
* Removed `ITEM_USE_ON_ENTITY`

ItemDescriptorType:
* Added `EMPTY`
* Added `NAME`
* Added `COMPLEX_ALIAS`
* Removed `INVALID`
* Removed `DEFAULT`

ItemReleaseActionType:
* Added `RELEASE`
* Added `USE`

ItemStackNetResult:
* Added `SUCCESS`
* Added `ERROR`
* Added `INVALID_REQUEST_ACTION_TYPE`
* Added `ACTION_REQUEST_NOT_ALLOWED`
* Added `SCREEN_HANDLER_END_REQUEST_FAILED`
* Added `ITEM_REQUEST_ACTION_HANDLER_COMMIT_FAILED`
* Added `INVALID_REQUEST_CRAFT_ACTION_TYPE`
* Added `INVALID_CRAFT_REQUEST`
* Added `INVALID_CRAFT_REQUEST_SCREEN`
* Added `INVALID_CRAFT_RESULT`
* Added `INVALID_CRAFT_RESULT_INDEX`
* Added `INVALID_CRAFT_RESULT_ITEM`
* Added `INVALID_ITEM_NET_ID`
* Added `MISSING_CREATED_OUTPUT_CONTAINER`
* Added `FAILED_TO_SET_CREATED_ITEM_OUTPUT_SLOT`
* Added `REQUEST_ALREADY_IN_PROGRESS`
* Added `FAILED_TO_INIT_SPARSE_CONTAINER`
* Added `RESULT_TRANSFER_FAILED`
* Added `EXPECTED_ITEM_SLOT_NOT_FULLY_CONSUMED`
* Added `EXPECTED_ANYWHERE_ITEM_NOT_FULLY_CONSUMED`
* Added `ITEM_ALREADY_CONSUMED_FROM_SLOT`
* Added `CONSUMED_TOO_MUCH_FROM_SLOT`
* Added `MISMATCH_SLOT_EXPECTED_CONSUMED_ITEM`
* Added `MISMATCH_SLOT_EXPECTED_CONSUMED_ITEM_NET_ID_VARIANT`
* Added `FAILED_TO_MATCH_EXPECTED_SLOT_CONSUMED_ITEM`
* Added `FAILED_TO_MATCH_EXPECTED_ALLOWED_ANYWHERE_CONSUMED_ITEM`
* Added `CONSUMED_ITEM_OUT_OF_ALLOWED_SLOT_RANGE`
* Added `CONSUMED_ITEM_NOT_ALLOWED`
* Added `PLAYER_NOT_IN_CREATIVE_MODE`
* Added `INVALID_EXPERIMENTAL_RECIPE_REQUEST`
* Added `FAILED_TO_CRAFT_CREATIVE`
* Added `FAILED_TO_GET_LEVEL_RECIPE`
* Added `FAILED_TO_FIND_RECIPE_BY_NET_ID`
* Added `MISMATCHED_CRAFTING_SIZE`
* Added `MISSING_INPUT_SPARSE_CONTAINER`
* Added `MISMATCHED_RECIPE_FOR_INPUT_GRID_ITEMS`
* Added `EMPTY_CRAFT_RESULTS`
* Added `FAILED_TO_ENCHANT`
* Added `MISSING_INPUT_ITEM`
* Added `INSUFFICIENT_PLAYER_LEVEL_TO_ENCHANT`
* Added `MISSING_MATERIAL_ITEM`
* Added `MISSING_ACTOR`
* Added `UNKNOWN_PRIMARY_EFFECT`
* Added `PRIMARY_EFFECT_OUT_OF_RANGE`
* Added `PRIMARY_EFFECT_UNAVAILABLE`
* Added `SECONDARY_EFFECT_OUT_OF_RANGE`
* Added `SECONDARY_EFFECT_UNAVAILABLE`
* Added `DST_CONTAINER_EQUAL_TO_CREATED_OUTPUT_CONTAINER`
* Added `DST_CONTAINER_AND_SLOT_EQUAL_TO_SRC_CONTAINER_AND_SLOT`
* Added `FAILED_TO_VALIDATE_SRC_SLOT`
* Added `FAILED_TO_VALIDATE_DST_SLOT`
* Added `INVALID_ADJUSTED_AMOUNT`
* Added `INVALID_ITEM_SET_TYPE`
* Added `INVALID_TRANSFER_AMOUNT`
* Added `CANNOT_SWAP_ITEM`
* Added `CANNOT_PLACE_ITEM`
* Added `UNHANDLED_ITEM_SET_TYPE`
* Added `INVALID_REMOVED_AMOUNT`
* Added `INVALID_REGION`
* Added `CANNOT_DROP_ITEM`
* Added `CANNOT_DESTROY_ITEM`
* Added `INVALID_SOURCE_CONTAINER`
* Added `ITEM_NOT_CONSUMED`
* Added `INVALID_NUM_CRAFTS`
* Added `INVALID_CRAFT_RESULT_STACK_SIZE`
* Added `CANNOT_REMOVE_ITEM`
* Added `CANNOT_CONSUME_ITEM`
* Added `SCREEN_STACK_ERROR`

ItemStackRequestActionType:
* Added `SCREEN_LAB_TABLE_COMBINE`
* Added `SCREEN_BEACON_PAYMENT`
* Added `SCREEN_HUD_MINE_BLOCK`
* Added `CRAFT_NON_IMPLEMENTED`
* Added `CRAFT_RESULTS`
* Removed `LAB_TABLE_COMBINE`
* Removed `BEACON_PAYMENT`
* Removed `MINE_BLOCK`
* Removed `CRAFT_NON_IMPLEMENTED_DEPRECATED`
* Removed `CRAFT_RESULTS_DEPRECATED`

ItemStackResponseStatus:
* Removed `OK`

ItemUseActionType:
* Added `PLACE`
* Added `USE`
* Added `DESTROY`
* Added `USE_AS_ATTACK`

ItemUseClientCooldownState:
* Added `OFF`
* Added `ON`

ItemUseMethod:
* Added `UNKNOWN`
* Added `EQUIP_ARMOR`
* Added `EAT`
* Added `ATTACK`
* Added `CONSUME`
* Added `THROW`
* Added `SHOOT`
* Added `PLACE`
* Added `FILL_BOTTLE`
* Added `FILL_BUCKET`
* Added `POUR_BUCKET`
* Added `USE_TOOL`
* Added `INTERACT`
* Added `RETRIEVED`
* Added `DYED`
* Added `TRADED`
* Added `BRUSHING_COMPLETED`
* Added `OPENED_VAULT`

ItemUseOnActorActionType:
* Added `INTERACT`
* Added `ATTACK`
* Added `ITEM_INTERACT`

ItemUsePredictedResult:
* Added `FAILURE`
* Added `SUCCESS`

ItemUseTransaction:
* Removed `FAILURE`
* Removed `UNKNOWN`
* Removed `PLAYER_INPUT`

ItemUseTriggerType:
* Added `UNKNOWN`
* Added `PLAYER_INPUT`
* Added `SIMULATION_TICK`

ItemUseType:
* Removed `UNKNOWN`
* Removed `EQUIP_ARMOR`
* Removed `EAT`
* Removed `ATTACK`
* Removed `CONSUME`
* Removed `THROW`
* Removed `SHOOT`
* Removed `PLACE`
* Removed `FILL_BOTTLE`
* Removed `FILL_BUCKET`
* Removed `POUR_BUCKET`
* Removed `USE_TOOL`
* Removed `INTERACT`
* Removed `RETRIEVED`
* Removed `DYED`
* Removed `TRADED`
* Removed `BRUSHING_COMPLETED`

ItemVersion:
* Added `LEGACY`
* Added `DATA_DRIVEN`
* Added `NONE`

ItemVersion:
* Removed `LEGACY`
* Removed `DATA_DRIVEN`
* Removed `NONE`

LabTableReactionType:
* Added `NONE`
* Added `ICE_BOMB`
* Added `BLEACH`
* Added `ELEPHANT_TOOTHPASTE`
* Added `FERTILIZER`
* Added `HEAT_BLOCK`
* Added `MAGNESIUM_SALTS`
* Added `MISC_FIRE`
* Added `MISC_EXPLOSION`
* Added `MISC_LAVA`
* Added `MISC_MYSTICAL`
* Added `MISC_SMOKE`
* Added `MISC_LARGE_SMOKE`

LabTableReactionType:
* Removed `NONE`
* Removed `ICE_BOMB`
* Removed `BLEACH`
* Removed `ELEPHANT_TOOTHPASTE`
* Removed `FERTILIZER`
* Removed `HEAT_BLOCK`
* Removed `MAGNESIUM_SALTS`
* Removed `MISC_FIRE`
* Removed `MISC_EXPLOSION`
* Removed `MISC_LAVAL`
* Removed `MISC_MYSTICAL`
* Removed `MISC_SMOKE`

LabTableType:
* Removed `START_COMBINE`
* Removed `START_REACTION`

LevelSoundEvent:
* Added `ITEM_USE_ON`
* Added `HIT`
* Added `STEP`
* Added `FLY`
* Added `JUMP`
* Added `BREAK`
* Added `PLACE`
* Added `HEAVY_STEP`
* Added `GALLOP`
* Added `FALL`
* Added `AMBIENT`
* Added `AMBIENT_BABY`
* Added `AMBIENT_IN_WATER`
* Added `BREATHE`
* Added `DEATH`
* Added `DEATH_IN_WATER`
* Added `DEATH_TO_ZOMBIE`
* Added `HURT`
* Added `HURT_IN_WATER`
* Added `MAD`
* Added `BOOST`
* Added `BOW`
* Added `SQUISH_BIG`
* Added `SQUISH_SMALL`
* Added `FALL_BIG`
* Added `FALL_SMALL`
* Added `SPLASH`
* Added `FIZZ`
* Added `FLAP`
* Added `SWIM`
* Added `DRINK`
* Added `EAT`
* Added `TAKEOFF`
* Added `SHAKE`
* Added `PLOP`
* Added `LAND`
* Added `SADDLE`
* Added `ARMOR`
* Added `MOB_ARMOR_STAND_PLACE`
* Added `ADD_CHEST`
* Added `THROW`
* Added `ATTACK`
* Added `ATTACK_NODAMAGE`
* Added `ATTACK_STRONG`
* Added `WARN`
* Added `SHEAR`
* Added `MILK`
* Added `THUNDER`
* Added `EXPLODE`
* Added `FIRE`
* Added `IGNITE`
* Added `FUSE`
* Added `STARE`
* Added `SPAWN`
* Added `SHOOT`
* Added `BREAK_BLOCK`
* Added `LAUNCH`
* Added `BLAST`
* Added `LARGE_BLAST`
* Added `TWINKLE`
* Added `REMEDY`
* Added `UNFECT`
* Added `LEVELUP`
* Added `BOW_HIT`
* Added `BULLET_HIT`
* Added `EXTINGUISH_FIRE`
* Added `ITEM_FIZZ`
* Added `CHEST_OPEN`
* Added `CHEST_CLOSED`
* Added `SHULKERBOX_OPEN`
* Added `SHULKERBOX_CLOSED`
* Added `ENDERCHEST_OPEN`
* Added `ENDERCHEST_CLOSED`
* Added `POWER_ON`
* Added `POWER_OFF`
* Added `ATTACH`
* Added `DETACH`
* Added `DENY`
* Added `TRIPOD`
* Added `POP`
* Added `DROP_SLOT`
* Added `NOTE`
* Added `THORNS`
* Added `PISTON_IN`
* Added `PISTON_OUT`
* Added `PORTAL`
* Added `WATER`
* Added `LAVA_POP`
* Added `LAVA`
* Added `BURP`
* Added `BUCKET_FILL_WATER`
* Added `BUCKET_FILL_LAVA`
* Added `BUCKET_EMPTY_WATER`
* Added `BUCKET_EMPTY_LAVA`
* Added `ARMOR_EQUIP_CHAIN`
* Added `ARMOR_EQUIP_DIAMOND`
* Added `ARMOR_EQUIP_GENERIC`
* Added `ARMOR_EQUIP_GOLD`
* Added `ARMOR_EQUIP_IRON`
* Added `ARMOR_EQUIP_LEATHER`
* Added `ARMOR_EQUIP_ELYTRA`
* Added `RECORD_13`
* Added `RECORD_CAT`
* Added `RECORD_BLOCKS`
* Added `RECORD_CHIRP`
* Added `RECORD_FAR`
* Added `RECORD_MALL`
* Added `RECORD_MELLOHI`
* Added `RECORD_STAL`
* Added `RECORD_STRAD`
* Added `RECORD_WARD`
* Added `RECORD_11`
* Added `RECORD_WAIT`
* Added `STOP_RECORD`
* Added `FLOP`
* Added `ELDERGUARDIAN_CURSE`
* Added `MOB_WARNING`
* Added `MOB_WARNING_BABY`
* Added `TELEPORT`
* Added `SHULKER_OPEN`
* Added `SHULKER_CLOSE`
* Added `HAGGLE`
* Added `HAGGLE_YES`
* Added `HAGGLE_NO`
* Added `HAGGLE_IDLE`
* Added `CHORUS_GROW`
* Added `CHORUS_DEATH`
* Added `GLASS`
* Added `POTION_BREWED`
* Added `CAST_SPELL`
* Added `PREPARE_ATTACK`
* Added `PREPARE_SUMMON`
* Added `PREPARE_WOLOLO`
* Added `FANG`
* Added `CHARGE`
* Added `CAMERA_TAKE_PICTURE`
* Added `LEASHKNOT_PLACE`
* Added `LEASHKNOT_BREAK`
* Added `GROWL`
* Added `WHINE`
* Added `PANT`
* Added `PURR`
* Added `PURREOW`
* Added `DEATH_MIN_VOLUME`
* Added `DEATH_MID_VOLUME`
* Added `IMITATE_BLAZE`
* Added `IMITATE_CAVE_SPIDER`
* Added `IMITATE_CREEPER`
* Added `IMITATE_ELDER_GUARDIAN`
* Added `IMITATE_ENDER_DRAGON`
* Added `IMITATE_ENDERMAN`
* Added `IMITATE_ENDERMITE`
* Added `IMITATE_EVOCATION_ILLAGER`
* Added `IMITATE_GHAST`
* Added `IMITATE_HUSK`
* Added `IMITATE_ILLUSION_ILLAGER`
* Added `IMITATE_MAGMA_CUBE`
* Added `IMITATE_POLAR_BEAR`
* Added `IMITATE_SHULKER`
* Added `IMITATE_SILVERFISH`
* Added `IMITATE_SKELETON`
* Added `IMITATE_SLIME`
* Added `IMITATE_SPIDER`
* Added `IMITATE_STRAY`
* Added `IMITATE_VEX`
* Added `IMITATE_VINDICATION_ILLAGER`
* Added `IMITATE_WITCH`
* Added `IMITATE_WITHER`
* Added `IMITATE_WITHER_SKELETON`
* Added `IMITATE_WOLF`
* Added `IMITATE_ZOMBIE`
* Added `IMITATE_ZOMBIE_PIGMAN`
* Added `IMITATE_ZOMBIE_VILLAGER`
* Added `BLOCK_END_PORTAL_FRAME_FILL`
* Added `BLOCK_END_PORTAL_SPAWN`
* Added `RANDOM_ANVIL_USE`
* Added `BOTTLE_DRAGONBREATH`
* Added `PORTAL_TRAVEL`
* Added `ITEM_TRIDENT_HIT`
* Added `ITEM_TRIDENT_RETURN`
* Added `ITEM_TRIDENT_RIPTIDE_1`
* Added `ITEM_TRIDENT_RIPTIDE_2`
* Added `ITEM_TRIDENT_RIPTIDE_3`
* Added `ITEM_TRIDENT_THROW`
* Added `ITEM_TRIDENT_THUNDER`
* Added `ITEM_TRIDENT_HIT_GROUND`
* Added `DEFAULT`
* Added `ELEMENT_CONSTRUCTOR_OPEN`
* Added `FLETCHING_TABLE_USE`
* Added `ICE_BOMB_HIT`
* Added `BALLOON_POP`
* Added `LT_REACTION_ICE_BOMB`
* Added `LT_REACTION_BLEACH`
* Added `LT_REACTION_E_PASTE`
* Added `LT_REACTION_E_PASTE2`
* Added `LT_REACTION_FERTILIZER`
* Added `LT_REACTION_FIREBALL`
* Added `LT_REACTION_MG_SALT`
* Added `LT_REACTION_MISC_FIRE`
* Added `LT_REACTION_FIRE`
* Added `LT_REACTION_MISC_EXPLOSION`
* Added `LT_REACTION_MISC_MYSTICAL`
* Added `LT_REACTION_MISC_MYSTICAL2`
* Added `LT_REACTION_PRODUCT`
* Added `SPARKLER_USE`
* Added `GLOWSTICK_USE`
* Added `SPARKLER_ACTIVE`
* Added `CONVERT_TO_DROWNED`
* Added `BUCKET_FILL_FISH`
* Added `BUCKET_EMPTY_FISH`
* Added `BUBBLE_UP`
* Added `BUBBLE_DOWN`
* Added `BUBBLE_POP`
* Added `BUBBLE_UP_INSIDE`
* Added `BUBBLE_DOWN_INSIDE`
* Added `BABY_HURT`
* Added `BABY_DEATH`
* Added `BABY_STEP`
* Added `BABY_SPAWN`
* Added `BORN`
* Added `BLOCK_TURTLE_EGG_BREAK`
* Added `BLOCK_TURTLE_EGG_CRACK`
* Added `BLOCK_TURTLE_EGG_HATCH`
* Added `TURTLE_LAY_EGG`
* Added `BLOCK_TURTLE_EGG_ATTACK`
* Added `BEACON_ACTIVATE`
* Added `BEACON_AMBIENT`
* Added `BEACON_DEACTIVATE`
* Added `BEACON_POWER`
* Added `CONDUIT_ACTIVATE`
* Added `CONDUIT_AMBIENT`
* Added `CONDUIT_ATTACK`
* Added `CONDUIT_DEACTIVATE`
* Added `CONDUIT_SHORT`
* Added `SWOOP`
* Added `BLOCK_BAMBOO_SAPLING_PLACE`
* Added `PRE_SNEEZE`
* Added `SNEEZE`
* Added `AMBIENT_TAME`
* Added `SCARED`
* Added `BLOCK_SCAFFOLDING_CLIMB`
* Added `CROSSBOW_LOADING_START`
* Added `CROSSBOW_LOADING_MIDDLE`
* Added `CROSSBOW_LOADING_END`
* Added `CROSSBOW_SHOOT`
* Added `CROSSBOW_QUICK_CHARGE_START`
* Added `CROSSBOW_QUICK_CHARGE_MIDDLE`
* Added `CROSSBOW_QUICK_CHARGE_END`
* Added `AMBIENT_AGGRESSIVE`
* Added `AMBIENT_WORRIED`
* Added `CANT_BREED`
* Added `SHIELD_BLOCK`
* Added `LECTERN_BOOK_PLACE`
* Added `GRINDSTONE_USE`
* Added `BELL`
* Added `CAMPFIRE_CRACKLE`
* Added `SWEET_BERRY_BUSH_HURT`
* Added `SWEET_BERRY_BUSH_PICK`
* Added `ROAR`
* Added `STUN`
* Added `CARTOGRAPHY_TABLE_USE`
* Added `STONECUTTER_USE`
* Added `COMPOSTER_EMPTY`
* Added `COMPOSTER_FILL`
* Added `COMPOSTER_FILL_LAYER`
* Added `COMPOSTER_READY`
* Added `BARREL_OPEN`
* Added `BARREL_CLOSE`
* Added `RAID_HORN`
* Added `LOOM_USE`
* Added `AMBIENT_IN_RAID`
* Added `UI_CARTOGRAPHY_TABLE_USE`
* Added `UI_STONECUTTER_USE`
* Added `UI_LOOM_USE`
* Added `SMOKER_USE`
* Added `BLAST_FURNACE_USE`
* Added `SMITHING_TABLE_USE`
* Added `SCREECH`
* Added `SLEEP`
* Added `FURNACE_USE`
* Added `MOOSHROOM_CONVERT`
* Added `MILK_SUSPICIOUSLY`
* Added `CELEBRATE`
* Added `JUMP_PREVENT`
* Added `AMBIENT_POLLINATE`
* Added `BEEHIVE_DRIP`
* Added `BEEHIVE_ENTER`
* Added `BEEHIVE_EXIT`
* Added `BEEHIVE_WORK`
* Added `BEEHIVE_SHEAR`
* Added `HONEYBOTTLE_DRINK`
* Added `AMBIENT_CAVE`
* Added `RETREAT`
* Added `CONVERT_TO_ZOMBIFIED`
* Added `ADMIRE`
* Added `STEP_LAVA`
* Added `TEMPT`
* Added `PANIC`
* Added `ANGRY`
* Added `AMBIENT_WARPED_FOREST`
* Added `AMBIENT_SOULSAND_VALLEY`
* Added `AMBIENT_NETHER_WASTES`
* Added `AMBIENT_BASALT_DELTAS`
* Added `AMBIENT_CRIMSON_FOREST`
* Added `RESPAWN_ANCHOR_CHARGE`
* Added `RESPAWN_ANCHOR_DEPLETE`
* Added `RESPAWN_ANCHOR_SET_SPAWN`
* Added `RESPAWN_ANCHOR_AMBIENT`
* Added `SOUL_ESCAPE_QUIET`
* Added `SOUL_ESCAPE_LOUD`
* Added `RECORD_PIGSTEP`
* Added `LINK_COMPASS_TO_LODESTONE`
* Added `USE_SMITHING_TABLE`
* Added `EQUIP_NETHERITE`
* Added `AMBIENT_LOOP_WARPED_FOREST`
* Added `AMBIENT_LOOP_SOULSAND_VALLEY`
* Added `AMBIENT_LOOP_NETHER_WASTES`
* Added `AMBIENT_LOOP_BASALT_DELTAS`
* Added `AMBIENT_LOOP_CRIMSON_FOREST`
* Added `AMBIENT_ADDITION_WARPED_FOREST`
* Added `AMBIENT_ADDITION_SOULSAND_VALLEY`
* Added `AMBIENT_ADDITION_NETHER_WASTES`
* Added `AMBIENT_ADDITION_BASALT_DELTAS`
* Added `AMBIENT_ADDITION_CRIMSON_FOREST`
* Added `SCULK_SENSOR_POWER_ON`
* Added `SCULK_SENSOR_POWER_OFF`
* Added `BUCKET_FILL_POWDER_SNOW`
* Added `BUCKET_EMPTY_POWDER_SNOW`
* Added `POINTED_DRIPSTONE_CAULDRON_DRIP_WATER`
* Added `POINTED_DRIPSTONE_CAULDRON_DRIP_LAVA`
* Added `POINTED_DRIPSTONE_DRIP_WATER`
* Added `POINTED_DRIPSTONE_DRIP_LAVA`
* Added `CAVE_VINES_PICK_BERRIES`
* Added `BIG_DRIPLEAF_TILT_DOWN`
* Added `BIG_DRIPLEAF_TILT_UP`
* Added `COPPER_WAX_ON`
* Added `COPPER_WAX_OFF`
* Added `SCRAPE`
* Added `PLAYER_HURT_DROWN`
* Added `PLAYER_HURT_ON_FIRE`
* Added `PLAYER_HURT_FREEZE`
* Added `USE_SPYGLASS`
* Added `STOP_USING_SPYGLASS`
* Added `AMETHYST_BLOCK_CHIME`
* Added `AMBIENT_SCREAMER`
* Added `HURT_SCREAMER`
* Added `DEATH_SCREAMER`
* Added `MILK_SCREAMER`
* Added `JUMP_TO_BLOCK`
* Added `PRE_RAM`
* Added `PRE_RAM_SCREAMER`
* Added `RAM_IMPACT`
* Added `RAM_IMPACT_SCREAMER`
* Added `SQUID_INK_SQUIRT`
* Added `GLOW_SQUID_INK_SQUIRT`
* Added `CONVERT_TO_STRAY`
* Added `CAKE_ADD_CANDLE`
* Added `EXTINGUISH_CANDLE`
* Added `AMBIENT_CANDLE`
* Added `BLOCK_CLICK`
* Added `BLOCK_CLICK_FAIL`
* Added `SCULK_CATALYST_BLOOM`
* Added `SCULK_SHRIEKER_SHRIEK`
* Added `WARDEN_NEARBY_CLOSE`
* Added `WARDEN_NEARBY_CLOSER`
* Added `WARDEN_NEARBY_CLOSEST`
* Added `WARDEN_SLIGHTLY_ANGRY`
* Added `RECORD_OTHERSIDE`
* Added `TONGUE`
* Added `CRACK_IRON_GOLEM`
* Added `REPAIR_IRON_GOLEM`
* Added `LISTENING`
* Added `HEARTBEAT`
* Added `HORN_BREAK`
* Added `SCULK_PLACE`
* Added `SCULK_SPREAD`
* Added `SCULK_CHARGE`
* Added `SCULK_SENSOR_PLACE`
* Added `SCULK_SHRIEKER_PLACE`
* Added `GOAT_CALL_0`
* Added `GOAT_CALL_1`
* Added `GOAT_CALL_2`
* Added `GOAT_CALL_3`
* Added `GOAT_CALL_4`
* Added `GOAT_CALL_5`
* Added `GOAT_CALL_6`
* Added `GOAT_CALL_7`
* Added `GOAT_CALL_8`
* Added `GOAT_CALL_9`
* Added `GOAT_HARMONY_0`
* Added `GOAT_HARMONY_1`
* Added `GOAT_HARMONY_2`
* Added `GOAT_HARMONY_3`
* Added `GOAT_HARMONY_4`
* Added `GOAT_HARMONY_5`
* Added `GOAT_HARMONY_6`
* Added `GOAT_HARMONY_7`
* Added `GOAT_HARMONY_8`
* Added `GOAT_HARMONY_9`
* Added `GOAT_MELODY_0`
* Added `GOAT_MELODY_1`
* Added `GOAT_MELODY_2`
* Added `GOAT_MELODY_3`
* Added `GOAT_MELODY_4`
* Added `GOAT_MELODY_5`
* Added `GOAT_MELODY_6`
* Added `GOAT_MELODY_7`
* Added `GOAT_MELODY_8`
* Added `GOAT_MELODY_9`
* Added `GOAT_BASS_0`
* Added `GOAT_BASS_1`
* Added `GOAT_BASS_2`
* Added `GOAT_BASS_3`
* Added `GOAT_BASS_4`
* Added `GOAT_BASS_5`
* Added `GOAT_BASS_6`
* Added `GOAT_BASS_7`
* Added `GOAT_BASS_8`
* Added `GOAT_BASS_9`
* Added `IMITATE_WARDEN`
* Added `LISTENING_ANGRY`
* Added `ITEM_GIVEN`
* Added `ITEM_TAKEN`
* Added `DISAPPEARED`
* Added `REAPPEARED`
* Added `FROGSPAWN_HATCHED`
* Added `LAY_SPAWN`
* Added `FROGSPAWN_BREAK`
* Added `SONIC_BOOM`
* Added `SONIC_CHARGE`
* Added `ITEM_THROWN`
* Added `RECORD_5`
* Added `CONVERT_TO_FROG`
* Added `MILK_DRINK`
* Added `RECORD_PLAYING`
* Added `ENCHANTING_TABLE_USE`
* Added `BUNDLE_DROP_CONTENTS`
* Added `BUNDLE_INSERT`
* Added `BUNDLE_REMOVE_ONE`
* Added `PRESSURE_PLATE_CLICK_OFF`
* Added `PRESSURE_PLATE_CLICK_ON`
* Added `BUTTON_CLICK_OFF`
* Added `BUTTON_CLICK_ON`
* Added `DOOR_OPEN`
* Added `DOOR_CLOSE`
* Added `TRAPDOOR_OPEN`
* Added `TRAPDOOR_CLOSE`
* Added `FENCE_GATE_OPEN`
* Added `FENCE_GATE_CLOSE`
* Added `INSERT`
* Added `PICKUP`
* Added `INSERT_ENCHANTED`
* Added `PICKUP_ENCHANTED`
* Added `BRUSH`
* Added `BRUSH_COMPLETED`
* Added `SHATTER_DECORATED_POT`
* Added `BREAK_DECORATED_POD`
* Added `SNIFFER_EGG_CRACK`
* Added `SNIFFER_EGG_HATCHED`
* Added `WAXED_SIGN_INTERACT_FAIL`
* Added `RECORD_RELIC`
* Added `BUMP`
* Added `PUMPKIN_CARVE`
* Added `CONVERT_HUSK_TO_ZOMBIE`
* Added `PIG_DEATH`
* Added `HOGLIN_CONVERT_TO_ZOMBIE`
* Added `AMBIENT_UNDERWATER_ENTER`
* Added `AMBIENT_UNDERWATER_EXIT`
* Added `BOTTLE_FILL`
* Added `BOTTLE_EMPTY`
* Added `CRAFTER_CRAFT`
* Added `CRAFTER_FAILED`
* Added `CRAFTER_DISABLE_SLOT`
* Added `DECORATED_POT_INSERT`
* Added `DECORATED_POT_INSERT_FAILED`
* Added `COPPER_BULB_ON`
* Added `COPPER_BULB_OFF`
* Added `TRIAL_SPAWNER_OPEN_SHUTTER`
* Added `TRIAL_SPAWNER_EJECT_ITEM`
* Added `TRIAL_SPAWNER_DETECT_PLAYER`
* Added `TRIAL_SPAWNER_SPAWN_MOB`
* Added `TRIAL_SPAWNER_CLOSE_SHUTTER`
* Added `TRIAL_SPAWNER_AMBIENT`
* Added `AMBIENT_IN_AIR`
* Added `WIND_BURST`
* Added `IMITATE_BREEZE`
* Added `ARMADILLO_BRUSH`
* Added `ARMADILLO_SCUTE_DROP`
* Added `EQUIP_WOLF`
* Added `UNEQUIP_WOLF`
* Added `REFLECT`
* Added `VAULT_OPEN_SHUTTER`
* Added `VAULT_CLOSE_SHUTTER`
* Added `VAULT_EJECT_ITEM`
* Added `VAULT_INSERT_ITEM`
* Added `VAULT_INSERT_ITEM_FAIL`
* Added `VAULT_AMBIENT`
* Added `VAULT_ACTIVATE`
* Added `VAULT_DEACTIVATE`
* Added `HURT_REDUCED`
* Added `WIND_CHARGE_BURST`
* Added `ARMOR_CRACK_WOLF`
* Added `ARMOR_BREAK_WOLF`
* Added `ARMOR_REPAIR_WOLF`
* Added `MACE_SMASH_AIR`
* Added `MACE_SMASH_GROUND`
* Added `MACE_SMASH_HEAVY_GROUND`
* Added `TRIAL_SPAWNER_CHARGE_ACTIVATE`
* Added `TRIAL_SPAWNER_AMBIENT_OMINOUS`
* Added `OMINOUS_ITEM_SPAWNER_SPAWN_ITEM`
* Added `OMINOUS_BOTTLE_END_USE`
* Added `OMINOUS_ITEM_SPAWNER_SPAWN_ITEM_BEGIN`
* Added `APPLY_EFFECT_BAD_OMEN`
* Added `APPLY_EFFECT_RAID_OMEN`
* Added `APPLY_EFFECT_TRIAL_OMEN`
* Added `OMINOUS_ITEM_SPAWNER_ABOUT_TO_SPAWN_ITEM`
* Added `RECORD_CREATOR`
* Added `RECORD_CREATOR_MUSIC_BOX`
* Added `RECORD_PRECIPICE`
* Added `IMITATE_BOGGED`
* Added `VAULT_REJECT_REWARDED_PLAYER`
* Added `IMITATE_DROWNED`
* Added `BUNDLE_INSERT_FAILED`
* Added `IMITATE_CREAKING`
* Added `SPONGE_ABSORB`
* Added `BLOCK_CREAKING_HEART_TRAIL`
* Added `CREAKING_HEART_SPAWN`
* Added `ACTIVATE`
* Added `DEACTIVATE`
* Added `FREEZE`
* Added `UNFREEZE`
* Added `OPEN`
* Added `OPEN_LONG`
* Added `CLOSE`
* Added `CLOSE_LONG`
* Added `IMITATE_PHANTOM`
* Added `IMITATE_ZOGLIN`
* Added `IMITATE_GUARDIAN`
* Added `IMITATE_RAVAGER`
* Added `IMITATE_PILLAGER`
* Added `PLACE_IN_WATER`
* Added `STATE_CHANGE`
* Added `IMITATE_HAPPY_GHAST`
* Added `UNEQUIP_GENERIC`
* Added `RECORD_TEARS`
* Added `THE_END_LIGHT_FLASH`
* Added `LEAD_LEASH`
* Added `LEAD_UNLEASH`
* Added `LEAD_BREAK`
* Added `UNSADDLE`
* Added `RECORD_LAVA_CHICKEN`
* Added `EQUIP_COPPER`
* Added `PLACE_ITEM`
* Added `SINGLE_ITEM_SWAP`
* Added `MULTI_ITEM_SWAP`
* Added `LUNGE_1`
* Added `LUNGE_2`
* Added `LUNGE_3`
* Added `ATTACK_CRITICAL`
* Added `SPEAR_ATTACK_HIT`
* Added `SPEAR_ATTACK_MISS`
* Added `WOODEN_SPEAR_ATTACK_HIT`
* Added `WOODEN_SPEAR_ATTACK_MISS`
* Added `IMITATE_PARCHED`
* Added `IMITATE_CAMEL_HUSK`
* Added `SPEAR_USE`
* Added `WOODEN_SPEAR_USE`
* Added `SADDLE_IN_WATER`
* Added `STONE_SPEAR_ATTACK_HIT`
* Added `IRON_SPEAR_ATTACK_HIT`
* Added `COPPER_SPEAR_ATTACK_HIT`
* Added `GOLDEN_SPEAR_ATTACK_HIT`
* Added `DIAMOND_SPEAR_ATTACK_HIT`
* Added `NETHERITE_SPEAR_ATTACK_HIT`
* Added `STONE_SPEAR_ATTACK_MISS`
* Added `IRON_SPEAR_ATTACK_MISS`
* Added `COPPER_SPEAR_ATTACK_MISS`
* Added `GOLDEN_SPEAR_ATTACK_MISS`
* Added `DIAMOND_SPEAR_ATTACK_MISS`
* Added `NETHERITE_SPEAR_ATTACK_MISS`
* Added `STONE_SPEAR_USE`
* Added `IRON_SPEAR_USE`
* Added `COPPER_SPEAR_USE`
* Added `GOLDEN_SPEAR_USE`
* Added `DIAMOND_SPEAR_USE`
* Added `NETHERITE_SPEAR_USE`
* Added `PAUSE_GROWTH`
* Added `RESET_GROWTH`
* Added `PUSHED_BY_PLAYER`
* Added `BOUNCE`
* Added `SLIME_LANDING`
* Added `ABSORB_BLOCK`
* Added `EJECT_BLOCK`
* Added `GEYSER_ERUPTION_START`
* Added `GEYSER_ERUPTION_ACTIVE`
* Added `RECORD_BOUNCE`
* Added `BUCKET_FILL_LAND_ANIMAL`
* Added `BUCKET_EMPTY_LAND_ANIMAL`
* Added `GEYSER_CONTINUOUS_ERUPTION_START`
* Added `GEYSER_CONTINUOUS_ERUPTION_ACTIVE`
* Added `MOUNT`
* Added `DISMOUNT`
* Added `STRAW_BED_BREAK_LEAVE`
* Added `UNDEFINED`

LoadingScreenPacketType:
* Added `UNKNOWN`
* Added `START_LOADING_SCREEN`
* Added `END_LOADING_SCREEN`

MapDecorationType:
* Added `MARKER_WHITE`
* Added `MARKER_GREEN`
* Added `MARKER_RED`
* Added `MARKER_BLUE`
* Added `XWHITE`
* Added `TRIANGLE_RED`
* Added `SQUARE_WHITE`
* Added `MARKER_SIGN`
* Added `MARKER_PINK`
* Added `MARKER_ORANGE`
* Added `MARKER_YELLOW`
* Added `MARKER_TEAL`
* Added `TRIANGLE_GREEN`
* Added `SMALL_SQUARE_WHITE`
* Added `MANSION`
* Added `MONUMENT`
* Added `NO_DRAW`
* Added `VILLAGE_DESERT`
* Added `VILLAGE_PLAINS`
* Added `VILLAGE_SAVANNA`
* Added `VILLAGE_SNOWY`
* Added `VILLAGE_TAIGA`
* Added `JUNGLE_TEMPLE`
* Added `WITCH_HUT`
* Added `TRIAL_CHAMBERS`
* Added `ABANDONED_CAMP`
* Added `BURIED_ANCIENT_CITY`
* Added `BURIED_MINESHAFT`
* Added `DESERT_PYRAMID`
* Added `WARM_OCEAN_RUINS`
* Added `COUNT`

MapItemTrackedActorType:
* Added `ENTITY`
* Added `BLOCK_ENTITY`
* Added `OTHER`

MapTrackedObject:
* Removed `ENTITY`

MatchmakingState:
* Added `IDLE`
* Added `MATCHMAKING`
* Added `MATCH_FOUND`

MemoryCategory:
* Added `UNKNOWN`
* Added `INVALID_SIZE_UNKNOWN`
* Added `ACTOR`
* Added `ACTOR_ANIMATION`
* Added `ACTOR_RENDERING`
* Added `BLOCK_TICKING_QUEUES`
* Added `BIOME_STORAGE`
* Added `BLOBS`
* Added `CEREAL`
* Added `CIRCUIT_SYSTEM`
* Added `CLIENT`
* Added `COMMANDS`
* Added `DBSTORAGE`
* Added `DEBUG`
* Added `DOCUMENTATION`
* Added `ECSSYSTEMS`
* Added `FMOD`
* Added `FONTS`
* Added `IM_GUI`
* Added `INPUT`
* Added `JSON_UI`
* Added `JSON_UI_CONTROL_FACTORY_JSON`
* Added `JSON_UI_CONTROL_TREE`
* Added `JSON_UI_CONTROL_TREE_CONTROL_ELEMENT`
* Added `JSON_UI_CONTROL_TREE_POPULATE_DATA_BINDING`
* Added `JSON_UI_CONTROL_TREE_POPULATE_FOCUS`
* Added `JSON_UI_CONTROL_TREE_POPULATE_LAYOUT`
* Added `JSON_UI_CONTROL_TREE_POPULATE_OTHER`
* Added `JSON_UI_CONTROL_TREE_POPULATE_SPRITE`
* Added `JSON_UI_CONTROL_TREE_POPULATE_TEXT`
* Added `JSON_UI_CONTROL_TREE_POPULATE_TTS`
* Added `JSON_UI_CONTROL_TREE_VISIBILITY`
* Added `JSON_UI_CREATE_UI`
* Added `JSON_UI_DEFS`
* Added `JSON_UI_LAYOUT_MANAGER`
* Added `JSON_UI_LAYOUT_MANAGER_REMOVE_DEPENDENCIES`
* Added `JSON_UI_LAYOUT_MANAGER_INIT_VARIABLE`
* Added `LANGUAGES`
* Added `LEVEL`
* Added `LEVEL_STRUCTURES`
* Added `LEVEL_CHUNK`
* Added `LEVEL_CHUNK_GEN`
* Added `LEVEL_CHUNK_GEN_THREAD_LOCAL`
* Added `LIGHT_VOLUME_MANAGER`
* Added `NETWORK`
* Added `MARKETPLACE`
* Added `MATERIAL_DRAGON_COMPILED_DEFINITION`
* Added `MATERIAL_DRAGON_MATERIAL`
* Added `MATERIAL_DRAGON_RESOURCE`
* Added `MATERIAL_DRAGON_UNIFORM_MAP`
* Added `MATERIAL_RENDER_MATERIAL`
* Added `MATERIAL_RENDER_MATERIAL_GROUP`
* Added `MATERIAL_VARIATION_MANAGER`
* Added `MOLANG`
* Added `ORE_UI`
* Added `ORE_UI_CLIENT`
* Added `PERSONA_PIECES`
* Added `PERSONA_ANIMATIONS`
* Added `PERSONA_CHARACTERS`
* Added `PERSONA_SKIN_PACKS`
* Added `PERSONA_REPO`
* Added `PLAYER`
* Added `RENDER_CHUNK`
* Added `RENDER_CHUNK_INDEX_BUFFER`
* Added `RENDER_CHUNK_VERTEX_BUFFER`
* Added `RENDERING`
* Added `RENDERING_BGFX_INIT`
* Added `RENDERING_BGFX_START_FRAME`
* Added `RENDERING_BLOCK_TESSELLATOR`
* Added `RENDERING_END_FRAME`
* Added `RENDERING_GRAPHICS_TASKS_INIT`
* Added `RENDERING_LIBRARY`
* Added `RENDERING_POLYGON_OPERATOR_POOL`
* Added `RENDERING_PBRTEXTURE_DATA`
* Added `RENDERING_RENDER_REGISTRY`
* Added `RENDERING_SETUP`
* Added `RENDERING_VERTICES`
* Added `REQUEST_LOG`
* Added `RESOURCE_PACKS`
* Added `SOUND`
* Added `SUB_CHUNK_BIOME_DATA`
* Added `SUB_CHUNK_BLOCK_DATA`
* Added `SUB_CHUNK_LIGHT_DATA`
* Added `TEXTURES`
* Added `WEATHER_RENDERER`
* Added `WORLD_GENERATOR`
* Added `TASKS`
* Added `TEST`
* Added `TEST_LOAD_TEST_TAGS`
* Added `SCRIPTING`
* Added `SCRIPTING_RUNTIME`
* Added `SCRIPTING_CONTEXT`
* Added `SCRIPTING_CONTEXT_BINDINGS_MC`
* Added `SCRIPTING_CONTEXT_BINDINGS_GT`
* Added `SCRIPTING_CONTEXT_RUN`
* Added `DATA_DRIVEN_UI`
* Added `DATA_DRIVEN_UI_DEFS`
* Added `GAMEFACE`
* Added `GAMEFACE_SYSTEM`
* Added `GAMEFACE_DOM`
* Added `GAMEFACE_CSS`
* Added `GAMEFACE_DISPLAY`
* Added `GAMEFACE_TEMP_ALLOCATOR`
* Added `GAMEFACE_POOL_ALLOCATOR`
* Added `GAMEFACE_DUMP`
* Added `GAMEFACE_MEDIA`
* Added `GAMEFACE_JSON`
* Added `GAMEFACE_SCRIPT_ENGINE`
* Added `GAMEFACE_SCRIPT`
* Added `GAMEFACE_LAYOUT`
* Added `EXECUTABLE`

MemoryTier:
* Added `SUPER_LOW`
* Added `LOW`
* Added `MID`
* Added `HIGH`
* Added `SUPER_HIGH`

Mirror:
* Added `NONE`
* Added `XZ`

ModalFormCancelReason:
* Added `USER_CLOSED`
* Added `USER_BUSY`

ModalFormCancelReason:
* Removed `USER_CLOSED`

MolangVersion:
* Added `INVALID`
* Added `BEFORE_VERSIONING`
* Added `INITIAL`
* Added `FIXED_ITEM_REMAINING_USE_DURATION_QUERY`
* Added `EXPRESSION_ERROR_MESSAGES`
* Added `UNEXPECTED_OPERATOR_ERRORS`
* Added `CONDITIONAL_OPERATOR_ASSOCIATIVITY`
* Added `COMPARISON_AND_LOGICAL_OPERATOR_PRECEDENCE`
* Added `DIVIDE_BY_NEGATIVE_VALUE`
* Added `FIXED_CAPE_FLAP_AMOUNT_QUERY`
* Added `QUERY_BLOCK_PROPERTY_RENAMED_TO_STATE`
* Added `DEPRECATE_OLD_BLOCK_QUERY_NAMES`
* Added `DEPRECATED_SNIFFER_AND_CAMEL_QUERIES`
* Added `LEAF_SUPPORTING_IN_FIRST_SOLID_BLOCK_BELOW`
* Added `NUM_VALID_VERSIONS`
* Added `LATEST`
* Added `HARDCODED_MOLANG`

MovementEffectType:
* Added `GLIDE_BOOST`
* Added `DOLPHIN_BOOST`
* Added `GEYSER_BOOST`

MovementEffectType:
* Removed `INVALID`
* Removed `GLIDE_BOOST`

MultiplayerMode:
* Removed `ENABLE_MULTIPLAYER`
* Removed `DISABLE_MULTIPLAYER`

MultiplayerSettingsPacketType:
* Added `ENABLE_MULTIPLAYER`
* Added `DISABLE_MULTIPLAYER`
* Added `REFRESH_JOINCODE`

NewInteractionModel:
* Added `TOUCH`
* Added `CROSSHAIR`
* Added `CLASSIC`
* Added `COUNT`

NoiseAlignmentType:
* Added `MIN_LOCAL_TRANSITION_END`

NpcDialogueActionType:
* Added `OPEN`
* Added `CLOSE`

NpcRequestType:
* Removed `SET_ACTION`
* Removed `EXECUTE_COMMAND_ACTION`
* Removed `EXECUTE_CLOSING_COMMANDS`
* Removed `SET_NAME`
* Removed `SET_SKIN`
* Removed `SET_INTERACTION_TEXT`

ObjectiveSortOrder:
* Added `ASCENDING`
* Added `DESCENDING`

POIBlockInteractionType:
* Added `NONE`
* Added `EXTEND`
* Added `CLONE`
* Added `LOCK`
* Added `CREATE`
* Added `CREATE_LOCATOR`
* Added `RENAME`
* Added `ITEM_PLACED`
* Added `ITEM_REMOVED`
* Added `COOKING`
* Added `DOUSING`
* Added `LIGHTING`
* Added `HAYSTACK`
* Added `FILLED`
* Added `EMPTIED`
* Added `ADD_DYE`
* Added `DYE_ITEM`
* Added `CLEAR_ITEM`
* Added `ENCHANT_ARROW`
* Added `COMPOST_ITEM_PLACED`
* Added `RECOVERED_BONEMEAL`
* Added `BOOK_PLACED`
* Added `BOOK_OPENED`
* Added `DISENCHANT`
* Added `REPAIR`
* Added `DISENCHANT_AND_REPAIR`

PackType:
* Added `INVALID`
* Added `ADDON`
* Added `CACHED`
* Added `COPY_PROTECTED`
* Added `BEHAVIOR`
* Added `PERSONA_PIECE`
* Added `RESOURCES`
* Added `SKINS`

PacketCompressionAlgorithm:
* Added `ZLIB`
* Added `SNAPPY`
* Added `NONE`

PacketCompressionAlgorithm:
* Removed `ZLIB`
* Removed `SNAPPY`

PacketViolationSeverity:
* Added `UNKNOWN`
* Added `WARNING`
* Added `FINAL_WARNING`
* Added `TERMINATING_CONNECTION`

PacketViolationSeverity:
* Removed `UNKNOWN`
* Removed `WARNING`
* Removed `FINAL_WARNING`

PacketViolationType:
* Added `UNKNOWN`
* Added `PACKET_MALFORMED`

PacketViolationType:
* Removed `UNKNOWN`

ParticleType:
* Added `GREEN_FLAME`
* Added `PAUSE_MOB_GROWTH`
* Added `RESET_MOB_GROWTH`
* Added `SULFUR_CUBE`
* Added `ORANGE_POPLAR_LEAVES`
* Added `RED_POPLAR_LEAVES`
* Added `YELLOW_POPLAR_LEAVES`

PartyDestinationCookieIntent:
* Added `NOTIFY`
* Added `OPT_IN`
* Added `OPT_OUT`

PayloadType:
* Added `INVALID`
* Added `CLEAR_DEBUG_MARKERS`
* Added `ADD_DEBUG_MARKER_CUBE`

PersonaAnimatedTextureType:
* Added `NONE`
* Added `FACE`
* Added `BODY32X32`
* Added `BODY128X128`

PersonaAnimationExpression:
* Added `LINEAR`
* Added `BLINKING`

PhotoType:
* Added `PORTFOLIO`
* Added `PHOTO_ITEM`
* Added `BOOK`

PhotoType:
* Removed `PORTFOLIO`
* Removed `PHOTO_ITEM`
* Removed `BOOK`

PieceType:
* Added `UNKNOWN`
* Added `SKELETON`
* Added `BODY`
* Added `SKIN`
* Added `BOTTOM`
* Added `FEET`
* Added `DRESS`
* Added `TOP`
* Added `HIGH_PANTS`
* Added `HANDS`
* Added `OUTERWEAR`
* Added `FACIAL_HAIR`
* Added `MOUTH`
* Added `EYES`
* Added `HAIR`
* Added `HOOD`
* Added `BACK`
* Added `FACE_ACCESSORY`
* Added `HEAD`
* Added `LEGS`
* Added `LEFT_LEG`
* Added `RIGHT_LEG`
* Added `ARMS`
* Added `LEFT_ARM`
* Added `RIGHT_ARM`
* Added `CAPES`
* Added `CLASSIC_SKIN`
* Added `EMOTE`
* Added `CO_CO`
* Added `UNSUPPORTED`

PlatformType:
* Added `DESKTOP`
* Added `CONSOLE`
* Added `MOBILE`

PlayStatus:
* Added `LOGIN_SUCCESS`
* Added `LOGIN_FAILED_CLIENT_OLD`
* Added `LOGIN_FAILED_SERVER_OLD`
* Added `PLAYER_SPAWN`
* Added `LOGIN_FAILED_INVALID_TENANT`
* Added `LOGIN_FAILED_EDITION_MISMATCH_EDU_TO_VANILLA`
* Added `LOGIN_FAILED_EDITION_MISMATCH_VANILLA_TO_EDU`
* Added `LOGIN_FAILED_SERVER_FULL_SUB_CLIENT`
* Added `LOGIN_FAILED_EDITOR_MISMATCH_EDITOR_TO_VANILLA`
* Added `LOGIN_FAILED_EDITOR_MISMATCH_VANILLA_TO_EDITOR`

PlayerActionType:
* Added `UNKNOWN`
* Added `START_DESTROY_BLOCK`
* Added `ABORT_DESTROY_BLOCK`
* Added `STOP_DESTROY_BLOCK`
* Added `START_SLEEPING`
* Added `STOP_SLEEPING`
* Added `RESPAWN`
* Added `START_JUMP`
* Added `START_SPRINTING`
* Added `STOP_SPRINTING`
* Added `START_SNEAKING`
* Added `STOP_SNEAKING`
* Added `CREATIVE_DESTROY_BLOCK`
* Added `CHANGE_DIMENSION_ACK`
* Added `START_GLIDING`
* Added `STOP_GLIDING`
* Added `DENY_DESTROY_BLOCK`
* Added `CRACK_BLOCK`
* Added `START_SWIMMING`
* Added `STOP_SWIMMING`
* Added `START_SPIN_ATTACK`
* Added `STOP_SPIN_ATTACK`
* Added `PREDICT_DESTROY_BLOCK`
* Added `CONTINUE_DESTROY_BLOCK`
* Added `START_ITEM_USE_ON`
* Added `STOP_ITEM_USE_ON`
* Added `HANDLED_TELEPORT`
* Added `MISSED_SWING`
* Added `START_CRAWLING`
* Added `STOP_CRAWLING`
* Added `START_FLYING`
* Added `STOP_FLYING`
* Added `START_USING_ITEM`
* Added `INTERNAL_UPDATE`
* Added `COUNT`

PlayerActionType:
* Removed `START_BREAK`
* Removed `ABORT_BREAK`
* Removed `STOP_BREAK`
* Removed `GET_UPDATED_BLOCK`
* Removed `DROP_ITEM`
* Removed `START_SLEEP`
* Removed `STOP_SLEEP`
* Removed `RESPAWN`
* Removed `JUMP`
* Removed `START_SPRINT`
* Removed `STOP_SPRINT`
* Removed `START_SNEAK`
* Removed `STOP_SNEAK`
* Removed `DIMENSION_CHANGE_REQUEST_OR_CREATIVE_DESTROY_BLOCK`
* Removed `DIMENSION_CHANGE_SUCCESS`
* Removed `START_GLIDE`
* Removed `STOP_GLIDE`
* Removed `BUILD_DENIED`
* Removed `CONTINUE_BREAK`
* Removed `CHANGE_SKIN`
* Removed `SET_ENCHANTMENT_SEED`
* Removed `START_SWIMMING`
* Removed `STOP_SWIMMING`
* Removed `START_SPIN_ATTACK`
* Removed `STOP_SPIN_ATTACK`
* Removed `BLOCK_INTERACT`
* Removed `BLOCK_PREDICT_DESTROY`
* Removed `BLOCK_CONTINUE_DESTROY`
* Removed `START_ITEM_USE_ON`
* Removed `STOP_ITEM_USE_ON`
* Removed `HANDLED_TELEPORT`
* Removed `MISSED_SWING`
* Removed `START_CRAWLING`
* Removed `STOP_CRAWLING`
* Removed `START_FLYING`
* Removed `STOP_FLYING`
* Removed `RECEIVED_SERVER_DATA`

PlayerAuthInputData:
* Added `ASCEND`
* Added `DESCEND`
* Added `JUMP_DOWN`
* Added `SPRINT_DOWN`
* Added `CHANGE_HEIGHT`
* Added `JUMPING`
* Added `AUTO_JUMPING_IN_WATER`
* Added `SNEAKING`
* Added `SNEAK_DOWN`
* Added `UP`
* Added `DOWN`
* Added `LEFT`
* Added `RIGHT`
* Added `UP_LEFT`
* Added `UP_RIGHT`
* Added `WANT_UP`
* Added `WANT_DOWN`
* Added `WANT_DOWN_SLOW`
* Added `WANT_UP_SLOW`
* Added `SPRINTING`
* Added `ASCEND_BLOCK`
* Added `DESCEND_BLOCK`
* Added `SNEAK_TOGGLE_DOWN`
* Added `PERSIST_SNEAK`
* Added `START_SPRINTING`
* Added `STOP_SPRINTING`
* Added `START_SNEAKING`
* Added `STOP_SNEAKING`
* Added `START_SWIMMING`
* Added `STOP_SWIMMING`
* Added `START_JUMPING`
* Added `START_GLIDING`
* Added `STOP_GLIDING`
* Added `PERFORM_ITEM_INTERACTION`
* Added `PERFORM_BLOCK_ACTIONS`
* Added `PERFORM_ITEM_STACK_REQUEST`
* Added `HANDLED_TELEPORT`
* Added `EMOTING`
* Added `MISSED_SWING`
* Added `START_CRAWLING`
* Added `STOP_CRAWLING`
* Added `START_FLYING`
* Added `STOP_FLYING`
* Added `CLIENT_ACK_SERVER_DATA`
* Added `IS_IN_CLIENT_PREDICTED_VEHICLE`
* Added `PADDLING_LEFT`
* Added `PADDLING_RIGHT`
* Added `BLOCK_BREAKING_DELAY_ENABLED`
* Added `HORIZONTAL_COLLISION`
* Added `VERTICAL_COLLISION`
* Added `DOWN_LEFT`
* Added `DOWN_RIGHT`
* Added `START_USING_ITEM`
* Added `START_SPIN_ATTACK`
* Added `STOP_SPIN_ATTACK`
* Added `IS_HOTBAR_ONLY_TOUCH`
* Added `JUMP_RELEASED_RAW`
* Added `JUMP_PRESSED_RAW`
* Added `JUMP_CURRENT_RAW`
* Added `SNEAK_RELEASED_RAW`
* Added `SNEAK_PRESSED_RAW`
* Added `SNEAK_CURRENT_RAW`
* Added `INTERNAL_UPDATE`

PlayerAuthInputData:
* Removed `ASCEND`
* Removed `DESCEND`
* Removed `NORTH_JUMP`
* Removed `JUMP_DOWN`
* Removed `SPRINT_DOWN`
* Removed `CHANGE_HEIGHT`
* Removed `JUMPING`
* Removed `AUTO_JUMPING_IN_WATER`
* Removed `SNEAKING`
* Removed `SNEAK_DOWN`
* Removed `UP`
* Removed `DOWN`
* Removed `LEFT`
* Removed `RIGHT`
* Removed `UP_LEFT`
* Removed `UP_RIGHT`
* Removed `WANT_UP`
* Removed `WANT_DOWN`
* Removed `WANT_DOWN_SLOW`
* Removed `WANT_UP_SLOW`
* Removed `SPRINTING`
* Removed `ASCEND_BLOCK`
* Removed `DESCEND_BLOCK`
* Removed `SNEAK_TOGGLE_DOWN`
* Removed `PERSIST_SNEAK`
* Removed `START_SPRINTING`
* Removed `STOP_SPRINTING`
* Removed `START_SNEAKING`
* Removed `STOP_SNEAKING`
* Removed `START_SWIMMING`
* Removed `STOP_SWIMMING`
* Removed `START_JUMPING`
* Removed `START_GLIDING`
* Removed `STOP_GLIDING`
* Removed `PERFORM_ITEM_INTERACTION`
* Removed `PERFORM_BLOCK_ACTIONS`
* Removed `PERFORM_ITEM_STACK_REQUEST`
* Removed `HANDLE_TELEPORT`
* Removed `EMOTING`
* Removed `MISSED_SWING`
* Removed `START_CRAWLING`
* Removed `STOP_CRAWLING`
* Removed `START_FLYING`
* Removed `STOP_FLYING`
* Removed `RECEIVED_SERVER_DATA`
* Removed `IN_CLIENT_PREDICTED_IN_VEHICLE`
* Removed `PADDLE_LEFT`
* Removed `PADDLE_RIGHT`
* Removed `BLOCK_BREAKING_DELAY_ENABLED`
* Removed `HORIZONTAL_COLLISION`
* Removed `VERTICAL_COLLISION`
* Removed `DOWN_LEFT`
* Removed `DOWN_RIGHT`
* Removed `START_USING_ITEM`
* Removed `CAMERA_RELATIVE_MOVEMENT_ENABLED`
* Removed `ROT_CONTROLLED_BY_MOVE_DIRECTION`
* Removed `START_SPIN_ATTACK`
* Removed `STOP_SPIN_ATTACK`
* Removed `HOTBAR_ONLY_TOUCH`
* Removed `JUMP_RELEASED_RAW`
* Removed `JUMP_PRESSED_RAW`
* Removed `JUMP_CURRENT_RAW`
* Removed `SNEAK_RELEASED_RAW`
* Removed `SNEAK_PRESSED_RAW`
* Removed `SNEAK_CURRENT_RAW`

PlayerListPacketType:
* Added `REMOVE`
* Added `ADD`

PlayerLocationPacketType:
* Added `PLAYER_LOCATION_COORDINATES`
* Added `PLAYER_LOCATION_HIDE`

PlayerPermission:
* Removed `VISITOR`
* Removed `MEMBER`
* Removed `OPERATOR`

PlayerPermissionLevel:
* Added `VISITOR`
* Added `MEMBER`
* Added `OPERATOR`
* Added `CUSTOM`

PlayerRespawnState:
* Added `SEARCHING_FOR_SPAWN`
* Added `READY_TO_SPAWN`
* Added `CLIENT_READY_TO_SPAWN`

PositionMode:
* Added `NORMAL`
* Added `RESPAWN`
* Added `TELEPORT`
* Added `ONLY_HEAD_ROT`

PredictionType:
* Removed `PLAYER`

RandomDistributionType:
* Added `SINGLE_VALUED`
* Added `UNIFORM`
* Added `GAUSSIAN`
* Added `INVERSE_GAUSSIAN`
* Added `FIXED_GRID`
* Added `JITTERED_GRID`
* Added `TRIANGLE`

RandomDistributionType:
* Removed `SINGLE_VALUED`
* Removed `UNIFORM`
* Removed `GAUSSIAN`
* Removed `INVERSE_GAUSSIAN`
* Removed `FIXED_GRID`
* Removed `JITTERED_GRID`

RecipeUnlockingContext:
* Added `NONE`
* Added `ALWAYS_UNLOCKED`
* Added `PLAYER_IN_WATER`
* Added `PLAYER_HAS_MANY_ITEMS`

RecipeUnlockingRequirement:
* Removed `NONE`
* Removed `ALWAYS_UNLOCKED`
* Removed `PLAYER_IN_WATER`
* Removed `PLAYER_HAS_MANY_ITEMS`

ResourcePackResponse:
* Added `CANCEL`
* Added `DOWNLOADING`
* Added `DOWNLOADING_FINISHED`
* Added `RESOURCE_PACK_STACK_FINISHED`

ResourcePackType:
* Removed `INVALID`
* Removed `RESOURCES`
* Removed `DATA_ADD_ON`
* Removed `WORLD_TEMPLATE`
* Removed `ADDON`
* Removed `SKINS`
* Removed `CACHED`
* Removed `COPY_PROTECTED`

RewindType:
* Added `PLAYER`
* Added `VEHICLE`

Rotation:
* Added `NONE`
* Added `ROTATE90`
* Added `ROTATE180`
* Added `ROTATE270`
* Added `CLOCKWISE90`
* Added `CLOCKWISE180`
* Added `COUNTER_CLOCKWISE90`

ScoreInfo:
* Removed `INVALID`
* Removed `PLAYER`
* Removed `ENTITY`

ScorePacketEntryAction:
* Added `REMOVE`
* Added `CHANGE_PLAYER`
* Added `CHANGE_ENTITY`
* Added `CHANGE_FAKE_PLAYER`

ScoreboardIdentityPacketType:
* Added `UPDATE`
* Added `REMOVE`

ScriptPrimitiveShapeType:
* Added `LINE`
* Added `BOX`
* Added `SPHERE`
* Added `CIRCLE`
* Added `TEXT`
* Added `ARROW`
* Added `CYLINDER`
* Added `PYRAMID`
* Added `ELLIPSOID`
* Added `CONE`

SerializedLayer:
* Added `CUSTOM_CACHE`
* Added `BASE`
* Added `SPECTATOR`
* Added `COMMANDS`
* Added `EDITOR`
* Added `LOADING_SCREEN`

ServerAuthMovementMode:
* Added `LEGACY_CLIENT_AUTHORITATIVE_V1_DEPRECATED`
* Added `CLIENT_AUTHORITATIVE_V2`
* Added `SERVER_AUTHORITATIVE_V3`

ServerEditorConnectionPolicy:
* Added `MATCH_WORLD_TYPE`
* Added `EDITOR_ONLY`
* Added `VANILLA_ONLY`
* Added `MIXED`

ServerWaypointGroupAction:
* Added `NONE`
* Added `ADD`
* Added `REMOVE`
* Added `UPDATE`

ServerboundLoadingScreenPacketType:
* Removed `UNKNOWN`
* Removed `START_LOADING_SCREEN`

ShowStoreOfferRedirectType:
* Added `MARKETPLACE_OFFER`
* Added `DRESSING_ROOM_OFFER`
* Added `THIRD_PARTY_SERVER_PAGE`

SimpleEventType:
* Removed `NONE`
* Removed `ENABLE_COMMANDS`
* Removed `DISABLE_COMMANDS`

SimulationType:
* Added `GAME`
* Added `EDITOR`
* Added `TEST`
* Added `INVALID`

SimulationType:
* Removed `GAME`
* Removed `EDITOR`

SoftEnumUpdateType:
* Added `REPLACE`

SoundDataEvent:
* Added `STOP`
* Added `SET_VOLUME`
* Added `SET_PITCH`
* Added `FADE`
* Added `SEEK_TO`
* Added `PAUSE`
* Added `RESUME`

SoundEvent:
* Removed `ITEM_USE_ON`
* Removed `HIT`
* Removed `STEP`
* Removed `FLY`
* Removed `JUMP`
* Removed `BREAK`
* Removed `PLACE`
* Removed `HEAVY_STEP`
* Removed `GALLOP`
* Removed `FALL`
* Removed `AMBIENT`
* Removed `AMBIENT_BABY`
* Removed `AMBIENT_IN_WATER`
* Removed `BREATHE`
* Removed `DEATH`
* Removed `DEATH_IN_WATER`
* Removed `DEATH_TO_ZOMBIE`
* Removed `HURT`
* Removed `HURT_IN_WATER`
* Removed `MAD`
* Removed `BOOST`
* Removed `BOW`
* Removed `SQUISH_BIG`
* Removed `SQUISH_SMALL`
* Removed `FALL_BIG`
* Removed `FALL_SMALL`
* Removed `SPLASH`
* Removed `FIZZ`
* Removed `FLAP`
* Removed `SWIM`
* Removed `DRINK`
* Removed `EAT`
* Removed `TAKEOFF`
* Removed `SHAKE`
* Removed `PLOP`
* Removed `LAND`
* Removed `SADDLE`
* Removed `ARMOR`
* Removed `MOB_ARMOR_STAND_PLACE`
* Removed `ADD_CHEST`
* Removed `THROW`
* Removed `ATTACK`
* Removed `ATTACK_NODAMAGE`
* Removed `ATTACK_STRONG`
* Removed `WARN`
* Removed `SHEAR`
* Removed `MILK`
* Removed `THUNDER`
* Removed `EXPLODE`
* Removed `FIRE`
* Removed `IGNITE`
* Removed `FUSE`
* Removed `STARE`
* Removed `SPAWN`
* Removed `SHOOT`
* Removed `BREAK_BLOCK`
* Removed `LAUNCH`
* Removed `BLAST`
* Removed `LARGE_BLAST`
* Removed `TWINKLE`
* Removed `REMEDY`
* Removed `UNFECT`
* Removed `LEVELUP`
* Removed `BOW_HIT`
* Removed `BULLET_HIT`
* Removed `EXTINGUISH_FIRE`
* Removed `ITEM_FIZZ`
* Removed `CHEST_OPEN`
* Removed `CHEST_CLOSED`
* Removed `SHULKERBOX_OPEN`
* Removed `SHULKERBOX_CLOSED`
* Removed `ENDERCHEST_OPEN`
* Removed `ENDERCHEST_CLOSED`
* Removed `POWER_ON`
* Removed `POWER_OFF`
* Removed `ATTACH`
* Removed `DETACH`
* Removed `DENY`
* Removed `TRIPOD`
* Removed `POP`
* Removed `DROP_SLOT`
* Removed `NOTE`
* Removed `THORNS`
* Removed `PISTON_IN`
* Removed `PISTON_OUT`
* Removed `PORTAL`
* Removed `WATER`
* Removed `LAVA_POP`
* Removed `LAVA`
* Removed `BURP`
* Removed `BUCKET_FILL_WATER`
* Removed `BUCKET_FILL_LAVA`
* Removed `BUCKET_EMPTY_WATER`
* Removed `BUCKET_EMPTY_LAVA`
* Removed `ARMOR_EQUIP_CHAIN`
* Removed `ARMOR_EQUIP_DIAMOND`
* Removed `ARMOR_EQUIP_GENERIC`
* Removed `ARMOR_EQUIP_GOLD`
* Removed `ARMOR_EQUIP_IRON`
* Removed `ARMOR_EQUIP_LEATHER`
* Removed `ARMOR_EQUIP_ELYTRA`
* Removed `RECORD_13`
* Removed `RECORD_CAT`
* Removed `RECORD_BLOCKS`
* Removed `RECORD_CHIRP`
* Removed `RECORD_FAR`
* Removed `RECORD_MALL`
* Removed `RECORD_MELLOHI`
* Removed `RECORD_STAL`
* Removed `RECORD_STRAD`
* Removed `RECORD_WARD`
* Removed `RECORD_11`
* Removed `RECORD_WAIT`
* Removed `STOP_RECORD`
* Removed `FLOP`
* Removed `ELDERGUARDIAN_CURSE`
* Removed `MOB_WARNING`
* Removed `MOB_WARNING_BABY`
* Removed `TELEPORT`
* Removed `SHULKER_OPEN`
* Removed `SHULKER_CLOSE`
* Removed `HAGGLE`
* Removed `HAGGLE_YES`
* Removed `HAGGLE_NO`
* Removed `HAGGLE_IDLE`
* Removed `CHORUS_GROW`
* Removed `CHORUS_DEATH`
* Removed `GLASS`
* Removed `POTION_BREWED`
* Removed `CAST_SPELL`
* Removed `PREPARE_ATTACK`
* Removed `PREPARE_SUMMON`
* Removed `PREPARE_WOLOLO`
* Removed `FANG`
* Removed `CHARGE`
* Removed `CAMERA_TAKE_PICTURE`
* Removed `LEASHKNOT_PLACE`
* Removed `LEASHKNOT_BREAK`
* Removed `GROWL`
* Removed `WHINE`
* Removed `PANT`
* Removed `PURR`
* Removed `PURREOW`
* Removed `DEATH_MIN_VOLUME`
* Removed `DEATH_MID_VOLUME`
* Removed `IMITATE_BLAZE`
* Removed `IMITATE_CAVE_SPIDER`
* Removed `IMITATE_CREEPER`
* Removed `IMITATE_ELDER_GUARDIAN`
* Removed `IMITATE_ENDER_DRAGON`
* Removed `IMITATE_ENDERMAN`
* Removed `IMITATE_ENDERMITE`
* Removed `IMITATE_EVOCATION_ILLAGER`
* Removed `IMITATE_GHAST`
* Removed `IMITATE_HUSK`
* Removed `IMITATE_ILLUSION_ILLAGER`
* Removed `IMITATE_MAGMA_CUBE`
* Removed `IMITATE_POLAR_BEAR`
* Removed `IMITATE_SHULKER`
* Removed `IMITATE_SILVERFISH`
* Removed `IMITATE_SKELETON`
* Removed `IMITATE_SLIME`
* Removed `IMITATE_SPIDER`
* Removed `IMITATE_STRAY`
* Removed `IMITATE_VEX`
* Removed `IMITATE_VINDICATION_ILLAGER`
* Removed `IMITATE_WITCH`
* Removed `IMITATE_WITHER`
* Removed `IMITATE_WITHER_SKELETON`
* Removed `IMITATE_WOLF`
* Removed `IMITATE_ZOMBIE`
* Removed `IMITATE_ZOMBIE_PIGMAN`
* Removed `IMITATE_ZOMBIE_VILLAGER`
* Removed `BLOCK_END_PORTAL_FRAME_FILL`
* Removed `BLOCK_END_PORTAL_SPAWN`
* Removed `RANDOM_ANVIL_USE`
* Removed `BOTTLE_DRAGONBREATH`
* Removed `PORTAL_TRAVEL`
* Removed `ITEM_TRIDENT_HIT`
* Removed `ITEM_TRIDENT_RETURN`
* Removed `ITEM_TRIDENT_RIPTIDE_1`
* Removed `ITEM_TRIDENT_RIPTIDE_2`
* Removed `ITEM_TRIDENT_RIPTIDE_3`
* Removed `ITEM_TRIDENT_THROW`
* Removed `ITEM_TRIDENT_THUNDER`
* Removed `ITEM_TRIDENT_HIT_GROUND`
* Removed `DEFAULT`
* Removed `ELEMENT_CONSTRUCTOR_OPEN`
* Removed `FLETCHING_TABLE_USE`
* Removed `ICE_BOMB_HIT`
* Removed `BALLOON_POP`
* Removed `LT_REACTION_ICE_BOMB`
* Removed `LT_REACTION_BLEACH`
* Removed `LT_REACTION_E_PASTE`
* Removed `LT_REACTION_E_PASTE2`
* Removed `LT_REACTION_FERTILIZER`
* Removed `LT_REACTION_FIREBALL`
* Removed `LT_REACTION_MG_SALT`
* Removed `LT_REACTION_MISC_FIRE`
* Removed `LT_REACTION_FIRE`
* Removed `LT_REACTION_MISC_EXPLOSION`
* Removed `LT_REACTION_MISC_MYSTICAL`
* Removed `LT_REACTION_MISC_MYSTICAL2`
* Removed `LT_REACTION_PRODUCT`
* Removed `SPARKLER_USE`
* Removed `GLOWSTICK_USE`
* Removed `SPARKLER_ACTIVE`
* Removed `CONVERT_TO_DROWNED`
* Removed `BUCKET_FILL_FISH`
* Removed `BUCKET_EMPTY_FISH`
* Removed `BUBBLE_UP`
* Removed `BUBBLE_DOWN`
* Removed `BUBBLE_POP`
* Removed `BUBBLE_UP_INSIDE`
* Removed `BUBBLE_DOWN_INSIDE`
* Removed `BABY_HURT`
* Removed `BABY_DEATH`
* Removed `BABY_STEP`
* Removed `BABY_SPAWN`
* Removed `BORN`
* Removed `BLOCK_TURTLE_EGG_BREAK`
* Removed `BLOCK_TURTLE_EGG_CRACK`
* Removed `BLOCK_TURTLE_EGG_HATCH`
* Removed `TURTLE_LAY_EGG`
* Removed `BLOCK_TURTLE_EGG_ATTACK`
* Removed `BEACON_ACTIVATE`
* Removed `BEACON_AMBIENT`
* Removed `BEACON_DEACTIVATE`
* Removed `BEACON_POWER`
* Removed `CONDUIT_ACTIVATE`
* Removed `CONDUIT_AMBIENT`
* Removed `CONDUIT_ATTACK`
* Removed `CONDUIT_DEACTIVATE`
* Removed `CONDUIT_SHORT`
* Removed `SWOOP`
* Removed `BLOCK_BAMBOO_SAPLING_PLACE`
* Removed `PRE_SNEEZE`
* Removed `SNEEZE`
* Removed `AMBIENT_TAME`
* Removed `SCARED`
* Removed `BLOCK_SCAFFOLDING_CLIMB`
* Removed `CROSSBOW_LOADING_START`
* Removed `CROSSBOW_LOADING_MIDDLE`
* Removed `CROSSBOW_LOADING_END`
* Removed `CROSSBOW_SHOOT`
* Removed `CROSSBOW_QUICK_CHARGE_START`
* Removed `CROSSBOW_QUICK_CHARGE_MIDDLE`
* Removed `CROSSBOW_QUICK_CHARGE_END`
* Removed `AMBIENT_AGGRESSIVE`
* Removed `AMBIENT_WORRIED`
* Removed `CANT_BREED`
* Removed `SHIELD_BLOCK`
* Removed `LECTERN_BOOK_PLACE`
* Removed `GRINDSTONE_USE`
* Removed `BELL`
* Removed `CAMPFIRE_CRACKLE`
* Removed `SWEET_BERRY_BUSH_HURT`
* Removed `SWEET_BERRY_BUSH_PICK`
* Removed `ROAR`
* Removed `STUN`
* Removed `CARTOGRAPHY_TABLE_USE`
* Removed `TABLE_USE`
* Removed `STONECUTTER_USE`
* Removed `COMPOSTER_EMPTY`
* Removed `COMPOSTER_FILL`
* Removed `COMPOSTER_FILL_LAYER`
* Removed `COMPOSTER_READY`
* Removed `BARREL_OPEN`
* Removed `BARREL_CLOSE`
* Removed `RAID_HORN`
* Removed `LOOM_USE`
* Removed `AMBIENT_IN_RAID`
* Removed `UI_CARTOGRAPHY_TABLE_USE`
* Removed `UI_STONECUTTER_USE`
* Removed `UI_LOOM_USE`
* Removed `SMOKER_USE`
* Removed `BLAST_FURNACE_USE`
* Removed `SMITHING_TABLE_USE`
* Removed `SCREECH`
* Removed `SLEEP`
* Removed `FURNACE_USE`
* Removed `MOOSHROOM_CONVERT`
* Removed `MILK_SUSPICIOUSLY`
* Removed `CELEBRATE`
* Removed `JUMP_PREVENT`
* Removed `AMBIENT_POLLINATE`
* Removed `BEEHIVE_DRIP`
* Removed `BEEHIVE_ENTER`
* Removed `BEEHIVE_EXIT`
* Removed `BEEHIVE_WORK`
* Removed `BEEHIVE_SHEAR`
* Removed `HONEYBOTTLE_DRINK`
* Removed `AMBIENT_CAVE`
* Removed `RETREAT`
* Removed `CONVERT_TO_ZOMBIFIED`
* Removed `ADMIRE`
* Removed `STEP_LAVA`
* Removed `TEMPT`
* Removed `PANIC`
* Removed `ANGRY`
* Removed `AMBIENT_WARPED_FOREST`
* Removed `AMBIENT_SOULSAND_VALLEY`
* Removed `AMBIENT_NETHER_WASTES`
* Removed `AMBIENT_BASALT_DELTAS`
* Removed `AMBIENT_CRIMSON_FOREST`
* Removed `RESPAWN_ANCHOR_CHARGE`
* Removed `RESPAWN_ANCHOR_DEPLETE`
* Removed `RESPAWN_ANCHOR_SET_SPAWN`
* Removed `RESPAWN_ANCHOR_AMBIENT`
* Removed `SOUL_ESCAPE_QUIET`
* Removed `SOUL_ESCAPE_LOUD`
* Removed `RECORD_PIGSTEP`
* Removed `LINK_COMPASS_TO_LODESTONE`
* Removed `USE_SMITHING_TABLE`
* Removed `EQUIP_NETHERITE`
* Removed `AMBIENT_LOOP_WARPED_FOREST`
* Removed `AMBIENT_LOOP_SOULSAND_VALLEY`
* Removed `AMBIENT_LOOP_NETHER_WASTES`
* Removed `AMBIENT_LOOP_BASALT_DELTAS`
* Removed `AMBIENT_LOOP_CRIMSON_FOREST`
* Removed `AMBIENT_ADDITION_WARPED_FOREST`
* Removed `AMBIENT_ADDITION_SOULSAND_VALLEY`
* Removed `AMBIENT_ADDITION_NETHER_WASTES`
* Removed `AMBIENT_ADDITION_BASALT_DELTAS`
* Removed `AMBIENT_ADDITION_CRIMSON_FOREST`
* Removed `SCULK_SENSOR_POWER_ON`
* Removed `SCULK_SENSOR_POWER_OFF`
* Removed `BUCKET_FILL_POWDER_SNOW`
* Removed `BUCKET_EMPTY_POWDER_SNOW`
* Removed `POINTED_DRIPSTONE_CAULDRON_DRIP_WATER`
* Removed `POINTED_DRIPSTONE_CAULDRON_DRIP_LAVA`
* Removed `POINTED_DRIPSTONE_DRIP_WATER`
* Removed `POINTED_DRIPSTONE_DRIP_LAVA`
* Removed `CAVE_VINES_PICK_BERRIES`
* Removed `BIG_DRIPLEAF_TILT_DOWN`
* Removed `BIG_DRIPLEAF_TILT_UP`
* Removed `COPPER_WAX_ON`
* Removed `COPPER_WAX_OFF`
* Removed `SCRAPE`
* Removed `PLAYER_HURT_DROWN`
* Removed `PLAYER_HURT_ON_FIRE`
* Removed `PLAYER_HURT_FREEZE`
* Removed `USE_SPYGLASS`
* Removed `STOP_USING_SPYGLASS`
* Removed `AMETHYST_BLOCK_CHIME`
* Removed `AMBIENT_SCREAMER`
* Removed `HURT_SCREAMER`
* Removed `DEATH_SCREAMER`
* Removed `MILK_SCREAMER`
* Removed `JUMP_TO_BLOCK`
* Removed `PRE_RAM`
* Removed `PRE_RAM_SCREAMER`
* Removed `RAM_IMPACT`
* Removed `RAM_IMPACT_SCREAMER`
* Removed `SQUID_INK_SQUIRT`
* Removed `GLOW_SQUID_INK_SQUIRT`
* Removed `CONVERT_TO_STRAY`
* Removed `CAKE_ADD_CANDLE`
* Removed `EXTINGUISH_CANDLE`
* Removed `AMBIENT_CANDLE`
* Removed `BLOCK_CLICK`
* Removed `BLOCK_CLICK_FAIL`
* Removed `SCULK_CATALYST_BLOOM`
* Removed `SCULK_SHRIEKER_SHRIEK`
* Removed `WARDEN_NEARBY_CLOSE`
* Removed `WARDEN_NEARBY_CLOSER`
* Removed `WARDEN_NEARBY_CLOSEST`
* Removed `WARDEN_SLIGHTLY_ANGRY`
* Removed `RECORD_OTHERSIDE`
* Removed `TONGUE`
* Removed `CRACK_IRON_GOLEM`
* Removed `REPAIR_IRON_GOLEM`
* Removed `LISTENING`
* Removed `HEARTBEAT`
* Removed `HORN_BREAK`
* Removed `SCULK_PLACE`
* Removed `SCULK_SPREAD`
* Removed `SCULK_CHARGE`
* Removed `SCULK_SENSOR_PLACE`
* Removed `SCULK_SHRIEKER_PLACE`
* Removed `GOAT_CALL_0`
* Removed `GOAT_CALL_1`
* Removed `GOAT_CALL_2`
* Removed `GOAT_CALL_3`
* Removed `GOAT_CALL_4`
* Removed `GOAT_CALL_5`
* Removed `GOAT_CALL_6`
* Removed `GOAT_CALL_7`
* Removed `GOAT_CALL_8`
* Removed `GOAT_CALL_9`
* Removed `GOAT_HARMONY_0`
* Removed `GOAT_HARMONY_1`
* Removed `GOAT_HARMONY_2`
* Removed `GOAT_HARMONY_3`
* Removed `GOAT_HARMONY_4`
* Removed `GOAT_HARMONY_5`
* Removed `GOAT_HARMONY_6`
* Removed `GOAT_HARMONY_7`
* Removed `GOAT_HARMONY_8`
* Removed `GOAT_HARMONY_9`
* Removed `GOAT_MELODY_0`
* Removed `GOAT_MELODY_1`
* Removed `GOAT_MELODY_2`
* Removed `GOAT_MELODY_3`
* Removed `GOAT_MELODY_4`
* Removed `GOAT_MELODY_5`
* Removed `GOAT_MELODY_6`
* Removed `GOAT_MELODY_7`
* Removed `GOAT_MELODY_8`
* Removed `GOAT_MELODY_9`
* Removed `GOAT_BASS_0`
* Removed `GOAT_BASS_1`
* Removed `GOAT_BASS_2`
* Removed `GOAT_BASS_3`
* Removed `GOAT_BASS_4`
* Removed `GOAT_BASS_5`
* Removed `GOAT_BASS_6`
* Removed `GOAT_BASS_7`
* Removed `GOAT_BASS_8`
* Removed `GOAT_BASS_9`
* Removed `IMITATE_WARDEN`
* Removed `LISTENING_ANGRY`
* Removed `ITEM_GIVEN`
* Removed `ITEM_TAKEN`
* Removed `DISAPPEARED`
* Removed `REAPPEARED`
* Removed `FROGSPAWN_HATCHED`
* Removed `LAY_SPAWN`
* Removed `FROGSPAWN_BREAK`
* Removed `SONIC_BOOM`
* Removed `SONIC_CHARGE`
* Removed `ITEM_THROWN`
* Removed `RECORD_5`
* Removed `CONVERT_TO_FROG`
* Removed `MILK_DRINK`
* Removed `RECORD_PLAYING`
* Removed `ENCHANTING_TABLE_USE`
* Removed `BUNDLE_DROP_CONTENTS`
* Removed `BUNDLE_INSERT`
* Removed `BUNDLE_REMOVE_ONE`
* Removed `PRESSURE_PLATE_CLICK_OFF`
* Removed `PRESSURE_PLATE_CLICK_ON`
* Removed `BUTTON_CLICK_OFF`
* Removed `BUTTON_CLICK_ON`
* Removed `DOOR_OPEN`
* Removed `DOOR_CLOSE`
* Removed `TRAPDOOR_OPEN`
* Removed `TRAPDOOR_CLOSE`
* Removed `FENCE_GATE_OPEN`
* Removed `FENCE_GATE_CLOSE`
* Removed `INSERT`
* Removed `PICKUP`
* Removed `INSERT_ENCHANTED`
* Removed `PICKUP_ENCHANTED`
* Removed `BRUSH`
* Removed `BRUSH_COMPLETED`
* Removed `SHATTER_DECORATED_POT`
* Removed `BREAK_DECORATED_POD`
* Removed `SNIFFER_EGG_CRACK`
* Removed `SNIFFER_EGG_HATCHED`
* Removed `WAXED_SIGN_INTERACT_FAIL`
* Removed `RECORD_RELIC`
* Removed `BUMP`
* Removed `PUMPKIN_CARVE`
* Removed `CONVERT_HUSK_TO_ZOMBIE`
* Removed `PIG_DEATH`
* Removed `HOGLIN_CONVERT_TO_ZOMBIE`
* Removed `AMBIENT_UNDERWATER_ENTER`
* Removed `AMBIENT_UNDERWATER_EXIT`
* Removed `BOTTLE_FILL`
* Removed `BOTTLE_EMPTY`
* Removed `CRAFTER_CRAFT`
* Removed `CRAFTER_FAILED`
* Removed `CRAFTER_DISABLE_SLOT`
* Removed `DECORATED_POT_INSERT`
* Removed `DECORATED_POT_INSERT_FAILED`
* Removed `COPPER_BULB_ON`
* Removed `COPPER_BULB_OFF`
* Removed `TRIAL_SPAWNER_OPEN_SHUTTER`
* Removed `TRIAL_SPAWNER_EJECT_ITEM`
* Removed `TRIAL_SPAWNER_DETECT_PLAYER`
* Removed `TRIAL_SPAWNER_SPAWN_MOB`
* Removed `TRIAL_SPAWNER_CLOSE_SHUTTER`
* Removed `TRIAL_SPAWNER_AMBIENT`
* Removed `AMBIENT_IN_AIR`
* Removed `WIND_BURST`
* Removed `IMITATE_BREEZE`
* Removed `ARMADILLO_BRUSH`
* Removed `ARMADILLO_SCUTE_DROP`
* Removed `EQUIP_WOLF`
* Removed `UNEQUIP_WOLF`
* Removed `REFLECT`
* Removed `VAULT_OPEN_SHUTTER`
* Removed `VAULT_CLOSE_SHUTTER`
* Removed `VAULT_EJECT_ITEM`
* Removed `VAULT_INSERT_ITEM`
* Removed `VAULT_INSERT_ITEM_FAIL`
* Removed `VAULT_AMBIENT`
* Removed `VAULT_ACTIVATE`
* Removed `VAULT_DEACTIVATE`
* Removed `HURT_REDUCED`
* Removed `WIND_CHARGE_BURST`
* Removed `ARMOR_CRACK_WOLF`
* Removed `ARMOR_BREAK_WOLF`
* Removed `ARMOR_REPAIR_WOLF`
* Removed `MACE_SMASH_AIR`
* Removed `MACE_SMASH_GROUND`
* Removed `MACE_SMASH_HEAVY_GROUND`
* Removed `TRIAL_SPAWNER_CHARGE_ACTIVATE`
* Removed `TRIAL_SPAWNER_AMBIENT_OMINOUS`
* Removed `OMINOUS_ITEM_SPAWNER_SPAWN_ITEM`
* Removed `OMINOUS_BOTTLE_END_USE`
* Removed `OMINOUS_ITEM_SPAWNER_SPAWN_ITEM_BEGIN`
* Removed `APPLY_EFFECT_BAD_OMEN`
* Removed `APPLY_EFFECT_RAID_OMEN`
* Removed `APPLY_EFFECT_TRIAL_OMEN`
* Removed `OMINOUS_ITEM_SPAWNER_ABOUT_TO_SPAWN_ITEM`
* Removed `RECORD_CREATOR`
* Removed `RECORD_CREATOR_MUSIC_BOX`
* Removed `RECORD_PRECIPICE`
* Removed `IMITATE_BOGGED`
* Removed `VAULT_REJECT_REWARDED_PLAYER`
* Removed `IMITATE_DROWNED`
* Removed `BUNDLE_INSERT_FAILED`
* Removed `IMITATE_CREAKING`
* Removed `SPONGE_ABSORB`
* Removed `BLOCK_CREAKING_HEART_TRAIL`
* Removed `CREAKING_HEART_SPAWN`
* Removed `ACTIVATE`
* Removed `DEACTIVATE`
* Removed `FREEZE`
* Removed `UNFREEZE`
* Removed `OPEN`
* Removed `OPEN_LONG`
* Removed `CLOSE`
* Removed `CLOSE_LONG`
* Removed `IMITATE_PHANTOM`
* Removed `IMITATE_ZOGLIN`
* Removed `IMITATE_GUARDIAN`
* Removed `IMITATE_RAVAGER`
* Removed `IMITATE_PILLAGER`
* Removed `PLACE_IN_WATER`
* Removed `STATE_CHANGE`
* Removed `IMITATE_HAPPY_GHAST`
* Removed `UNEQUIP_GENERIC`
* Removed `RECORD_TEARS`
* Removed `THE_END_LIGHT_FLASH`
* Removed `LEAD_LEASH`
* Removed `LEAD_UNLEASH`
* Removed `LEAD_BREAK`
* Removed `UNSADDLE`
* Removed `RECORD_LAVA_CHICKEN`
* Removed `EQUIP_COPPER`

SpawnBiomeType:
* Added `DEFAULT`
* Added `USER_DEFINED`

SpawnBiomeType:
* Removed `DEFAULT`
* Removed `USER_DEFINED`

SpawnPositionType:
* Added `PLAYER_RESPAWN`
* Added `WORLD_SPAWN`

StoreOfferRedirectType:
* Removed `MARKETPLACE`
* Removed `DRESSING_ROOM`

StructureAnimationMode:
* Removed `NONE`
* Removed `LAYER`
* Removed `BLOCKS`

StructureMirror:
* Removed `NONE`
* Removed `XZ`

StructureRotation:
* Removed `NONE`
* Removed `ROTATE_90`
* Removed `ROTATE_180`
* Removed `ROTATE_270`

StructureTemplateRequestOperation:
* Added `EXPORT_FROM_SAVE_MODE`
* Removed `EXPORT_FROM_SAVED_MODE`
* Removed `IMPORT`

StructureTemplateResponseType:
* Removed `IMPORT`

SubChunkRequestResult:
* Added `UNDEFINED`
* Added `SUCCESS`
* Added `LEVEL_CHUNK_DOESNT_EXIST`
* Added `WRONG_DIMENSION`
* Added `PLAYER_DOESNT_EXIST`
* Added `INDEX_OUT_OF_BOUNDS`
* Added `SUCCESS_ALL_AIR`

SubChunkRequestResult:
* Removed `UNDEFINED`
* Removed `SUCCESS`
* Removed `CHUNK_NOT_FOUND`
* Removed `INVALID_DIMENSION`
* Removed `PLAYER_NOT_FOUND`
* Removed `INDEX_OUT_OF_BOUNDS`

Subtype:
* Added `UNINITIALIZED_SUBTYPE`
* Added `ENABLE_COMMANDS`
* Added `DISABLE_COMMANDS`
* Added `UNLOCK_WORLD_TEMPLATE_SETTINGS`

TextPacketBodyType:
* Added `MESSAGE_ONLY`
* Added `AUTHOR_AND_MESSAGE`
* Added `MESSAGE_AND_PARAMS`

TextPacketType:
* Added `RAW`
* Added `CHAT`
* Added `TRANSLATE`
* Added `POPUP`
* Added `JUKEBOX_POPUP`
* Added `TIP`
* Added `SYSTEM_MESSAGE`
* Added `WHISPER`
* Added `ANNOUNCEMENT`
* Added `TEXT_OBJECT_WHISPER`
* Added `TEXT_OBJECT`
* Added `TEXT_OBJECT_ANNOUNCEMENT`

TextProcessingEventOrigin:
* Added `UNKNOWN`
* Added `SERVER_CHAT_PUBLIC`
* Added `SERVER_CHAT_WHISPER`
* Added `SIGN_TEXT`
* Added `ANVIL_TEXT`
* Added `BOOK_AND_QUILL_TEXT`
* Added `COMMAND_BLOCK_TEXT`
* Added `BLOCK_ACTOR_DATA_TEXT`
* Added `JOIN_EVENT_TEXT`
* Added `LEAVE_EVENT_TEXT`
* Added `SLASH_COMMAND_CHAT`
* Added `CARTOGRAPHY_TEXT`
* Added `KICK_COMMAND`
* Added `TITLE_COMMAND`
* Added `SUMMON_COMMAND`
* Added `PASS_THROUGH_WITHOUT_SIFT`
* Added `SERVER_FORM`
* Added `DATA_DRIVEN_UI`

TextProcessingEventOrigin:
* Removed `SERVER_CHAT_PUBLIC`
* Removed `SERVER_CHAT_WHISPER`
* Removed `SIGN_TEXT`
* Removed `ANVIL_TEXT`
* Removed `BOOK_AND_QUILL_TEXT`
* Removed `COMMAND_BLOCK_TEXT`
* Removed `BLOCK_ENTITY_DATA_TEXT`
* Removed `JOIN_EVENT_TEXT`
* Removed `LEAVE_EVENT_TEXT`
* Removed `SLASH_COMMAND_TEXT`
* Removed `CARTOGRAPHY_TEXT`
* Removed `SLASH_COMMAND_NON_CHAT`
* Removed `SCOREBOARD_TEXT`
* Removed `TICKING_AREA_TEXT`
* Removed `KICK_COMMAND`
* Removed `TITLE_COMMAND`
* Removed `SUMMON_COMMAND`
* Removed `PASS_THROUGH_WITHOUT_SIFT`

TrustedSkinFlag:
* Added `UNSET`
* Added `FALSE`
* Added `TRUE`

Type:
* Added `PLAYER`
* Added `DEV_CONSOLE`
* Added `TEST`
* Added `AUTOMATION_PLAYER`

UpdateType:
* Added `CLEAR_OVERRIDES`
* Added `REMOVE_OVERRIDE`
* Added `SET_INT_OVERRIDE`
* Added `SET_FLOAT_OVERRIDE`

UserInterfaceProfile:
* Added `CLASSIC`
* Added `POCKET`
* Added `NONE`

UserInterfaceProfile:
* Removed `CLASSIC`
* Removed `POCKET`
* Removed `NONE`

VanillaWaypointManagerConstants:
* Added `SQUARE`
* Added `CIRCLE`
* Added `SMALL_SQUARE`
* Added `SMALL_STAR`
* Added `TINY_SQUARE`
* Added `TINY_STAR`
* Added `WORLD_POS`
* Added `VISIBILITY`
* Added `TEXTURE`
* Added `COLOR`
* Added `CLIENT_POSITION_AUTHORITY`

VillageType:
* Added `DESERT`
* Added `ICE`
* Added `SAVANNA`
* Added `TAIGA`
* Added `DEFAULT`

