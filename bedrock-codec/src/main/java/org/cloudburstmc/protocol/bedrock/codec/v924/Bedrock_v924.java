package org.cloudburstmc.protocol.bedrock.codec.v924;

import org.cloudburstmc.protocol.bedrock.codec.ActorDataTypeMap;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodec;
import org.cloudburstmc.protocol.bedrock.codec.v786.Bedrock_v786;
import org.cloudburstmc.protocol.bedrock.codec.v786.serializer.LevelSoundEventSerializer_v786;
import org.cloudburstmc.protocol.bedrock.codec.v898.Bedrock_v898;
import org.cloudburstmc.protocol.bedrock.codec.v924.serializer.*;
import org.cloudburstmc.protocol.bedrock.data.MemoryCategory;
import org.cloudburstmc.protocol.bedrock.data.PacketRecipient;
import org.cloudburstmc.protocol.bedrock.data.SoundEvent;
import org.cloudburstmc.protocol.bedrock.data.actor.ActorDataFormat;
import org.cloudburstmc.protocol.bedrock.data.actor.ActorDataTypes;
import org.cloudburstmc.protocol.bedrock.packet.*;
import org.cloudburstmc.protocol.bedrock.transformer.FlagTransformer;
import org.cloudburstmc.protocol.common.util.TypeMap;

/**
 * @author Kaooot
 */
public class Bedrock_v924 extends Bedrock_v898 {

    protected static final ActorDataTypeMap ACTOR_DATA = Bedrock_v786.ACTOR_DATA
            .toBuilder()
            .update(ActorDataTypes.FLAGS, new FlagTransformer(ACTOR_FLAGS, 0))
            .update(ActorDataTypes.FLAGS_2, new FlagTransformer(ACTOR_FLAGS, 1))
            .insert(ActorDataTypes.AIM_ASSIST_PRIORITY_PRESET_ID, 136, ActorDataFormat.INT)
            .insert(ActorDataTypes.AIM_ASSIST_PRIORITY_CATEGORY_ID, 137, ActorDataFormat.INT)
            .insert(ActorDataTypes.AIM_ASSIST_PRIORITY_ACTOR_ID, 138, ActorDataFormat.INT)
            .build();

    protected static final TypeMap<SoundEvent> SOUND_EVENTS = Bedrock_v898.SOUND_EVENTS
            .toBuilder()
            .replace(578, SoundEvent.SADDLE_IN_WATER)
            .insert(579, SoundEvent.STONE_SPEAR_ATTACK_HIT)
            .insert(580, SoundEvent.IRON_SPEAR_ATTACK_HIT)
            .insert(581, SoundEvent.COPPER_SPEAR_ATTACK_HIT)
            .insert(582, SoundEvent.GOLDEN_SPEAR_ATTACK_HIT)
            .insert(583, SoundEvent.DIAMOND_SPEAR_ATTACK_HIT)
            .insert(584, SoundEvent.NETHERITE_SPEAR_ATTACK_HIT)
            .insert(585, SoundEvent.STONE_SPEAR_ATTACK_MISS)
            .insert(586, SoundEvent.IRON_SPEAR_ATTACK_MISS)
            .insert(587, SoundEvent.COPPER_SPEAR_ATTACK_MISS)
            .insert(588, SoundEvent.GOLDEN_SPEAR_ATTACK_MISS)
            .insert(589, SoundEvent.DIAMOND_SPEAR_ATTACK_MISS)
            .insert(590, SoundEvent.NETHERITE_SPEAR_ATTACK_MISS)
            .insert(591, SoundEvent.STONE_SPEAR_USE)
            .insert(592, SoundEvent.IRON_SPEAR_USE)
            .insert(593, SoundEvent.COPPER_SPEAR_USE)
            .insert(594, SoundEvent.GOLDEN_SPEAR_USE)
            .insert(595, SoundEvent.DIAMOND_SPEAR_USE)
            .insert(596, SoundEvent.NETHERITE_SPEAR_USE)
            .insert(597, SoundEvent.UNDEFINED)
            .build();

    protected static final TypeMap<MemoryCategory> MEMORY_CATEGORY_TYPES = TypeMap.builder(MemoryCategory.class)
            .insert(0, MemoryCategory.UNKNOWN)
            .insert(1, MemoryCategory.INVALID_SIZE_UNKNOWN)
            .insert(2, MemoryCategory.ACTOR)
            .insert(3, MemoryCategory.ACTOR_ANIMATION)
            .insert(4, MemoryCategory.ACTOR_RENDERING)
            .insert(5, MemoryCategory.BALANCER)
            .insert(6, MemoryCategory.BLOCK_TICKING_QUEUES)
            .insert(7, MemoryCategory.BIOME_STORAGE)
            .insert(8, MemoryCategory.CEREAL)
            .insert(9, MemoryCategory.CIRCUIT_SYSTEM)
            .insert(10, MemoryCategory.CLIENT)
            .insert(11, MemoryCategory.COMMANDS)
            .insert(12, MemoryCategory.DB_STORAGE)
            .insert(13, MemoryCategory.DEBUG)
            .insert(14, MemoryCategory.DOCUMENTATION)
            .insert(15, MemoryCategory.ECS_SYSTEMS)
            .insert(16, MemoryCategory.FMOD)
            .insert(17, MemoryCategory.FONTS)
            .insert(18, MemoryCategory.IM_GUI)
            .insert(19, MemoryCategory.INPUT)
            .insert(20, MemoryCategory.JSON_UI)
            .insert(21, MemoryCategory.JSON_UI_CONTROL_FACTORY_JSON)
            .insert(22, MemoryCategory.JSON_UI_CONTROL_TREE)
            .insert(23, MemoryCategory.JSON_UI_CONTROL_TREE_CONTROL_ELEMENT)
            .insert(24, MemoryCategory.JSON_UI_CONTROL_TREE_POPULATE_DATA_BINDING)
            .insert(25, MemoryCategory.JSON_UI_CONTROL_TREE_POPULATE_FOCUS)
            .insert(26, MemoryCategory.JSON_UI_CONTROL_TREE_POPULATE_LAYOUT)
            .insert(27, MemoryCategory.JSON_UI_CONTROL_TREE_POPULATE_OTHER)
            .insert(28, MemoryCategory.JSON_UI_CONTROL_TREE_POPULATE_SPRITE)
            .insert(29, MemoryCategory.JSON_UI_CONTROL_TREE_POPULATE_TEXT)
            .insert(30, MemoryCategory.JSON_UI_CONTROL_TREE_POPULATE_TTS)
            .insert(31, MemoryCategory.JSON_UI_CONTROL_TREE_VISIBILITY)
            .insert(32, MemoryCategory.JSON_UI_CREATE_UI)
            .insert(33, MemoryCategory.JSON_UI_DEFS)
            .insert(34, MemoryCategory.JSON_UI_LAYOUT_MANAGER)
            .insert(35, MemoryCategory.JSON_UI_LAYOUT_MANAGER_REMOVE_DEPENDENCIES)
            .insert(36, MemoryCategory.JSON_UI_LAYOUT_MANAGER_INIT_VARIABLE)
            .insert(37, MemoryCategory.LANGUAGES)
            .insert(38, MemoryCategory.LEVEL)
            .insert(39, MemoryCategory.LEVEL_STRUCTURES)
            .insert(40, MemoryCategory.LEVEL_CHUNK)
            .insert(41, MemoryCategory.LEVEL_CHUNK_GEN)
            .insert(42, MemoryCategory.LEVEL_CHUNK_GEN_THREAD_LOCAL)
            .insert(43, MemoryCategory.NETWORK)
            .insert(44, MemoryCategory.MARKETPLACE)
            .insert(45, MemoryCategory.MATERIAL_DRAGON_COMPILED_DEFINITION)
            .insert(46, MemoryCategory.MATERIAL_DRAGON_MATERIAL)
            .insert(47, MemoryCategory.MATERIAL_DRAGON_RESOURCE)
            .insert(48, MemoryCategory.MATERIAL_DRAGON_UNIFORM_MAP)
            .insert(49, MemoryCategory.MATERIAL_RENDER_MATERIAL)
            .insert(50, MemoryCategory.MATERIAL_RENDER_MATERIAL_GROUP)
            .insert(51, MemoryCategory.MATERIAL_VARIATION_MANAGER)
            .insert(52, MemoryCategory.MOLANG)
            .insert(53, MemoryCategory.ORE_UI)
            .insert(54, MemoryCategory.PERSONA)
            .insert(55, MemoryCategory.PLAYER)
            .insert(56, MemoryCategory.RENDER_CHUNK)
            .insert(57, MemoryCategory.RENDER_CHUNK_INDEX_BUFFER)
            .insert(58, MemoryCategory.RENDER_CHUNK_VERTEX_BUFFER)
            .insert(59, MemoryCategory.RENDERING)
            .insert(60, MemoryCategory.RENDERING_LIBRARY)
            .insert(61, MemoryCategory.REQUEST_LOG)
            .insert(62, MemoryCategory.RESOURCE_PACKS)
            .insert(63, MemoryCategory.SOUND)
            .insert(64, MemoryCategory.SUB_CHUNK_BIOME_DATA)
            .insert(65, MemoryCategory.SUB_CHUNK_BLOCK_DATA)
            .insert(66, MemoryCategory.SUB_CHUNK_LIGHT_DATA)
            .insert(67, MemoryCategory.TEXTURES)
            .insert(68, MemoryCategory.VR)
            .insert(69, MemoryCategory.WEATHER_RENDERER)
            .insert(70, MemoryCategory.WORLD_GENERATOR)
            .insert(71, MemoryCategory.TASKS)
            .insert(72, MemoryCategory.TEST)
            .insert(73, MemoryCategory.SCRIPTING)
            .insert(74, MemoryCategory.SCRIPTING_RUNTIME)
            .insert(75, MemoryCategory.SCRIPTING_CONTEXT)
            .insert(76, MemoryCategory.SCRIPTING_CONTEXT_BINDINGS_MC)
            .insert(77, MemoryCategory.SCRIPTING_CONTEXT_BINDINGS_GT)
            .insert(78, MemoryCategory.SCRIPTING_CONTEXT_RUN)
            .insert(79, MemoryCategory.DATA_DRIVEN_UI)
            .insert(80, MemoryCategory.DATA_DRIVEN_UI_DEFS)
            .build();

    public static final BedrockCodec CODEC = Bedrock_v898.CODEC.toBuilder()
            .raknetProtocolVersion(11)
            .protocolVersion(924)
            .minecraftVersion("1.26.0")
            .helper(() -> new BedrockCodecHelper_v924(ACTOR_DATA, GAME_RULE_TYPES, ITEM_STACK_REQUEST_TYPES, CONTAINER_SLOT_TYPES, PLAYER_ABILITIES, TEXT_PROCESSING_ORIGINS))
            .updateSerializer(BookEditPacket.class, BookEditSerializer_v924.INSTANCE)
            .updateSerializer(BiomeDefinitionListPacket.class, BiomeDefinitionListSerializer_v924.INSTANCE)
            .updateSerializer(CameraAimAssistPresetsPacket.class, CameraAimAssistPresetsSerializer_v924.INSTANCE)
            .updateSerializer(CameraInstructionPacket.class, CameraInstructionSerializer_v924.INSTANCE)
            .updateSerializer(DebugDrawerPacket.class, DebugDrawerSerializer_v924.INSTANCE)
            .updateSerializer(GraphicsOverrideParameterPacket.class, GraphicsParameterOverrideSerializer_v924.INSTANCE)
            .updateSerializer(LevelSoundEventPacket.class, new LevelSoundEventSerializer_v786(SOUND_EVENTS))
            .updateSerializer(ServerboundDiagnosticsPacket.class, new ServerboundDiagnosticsSerializer_v924(MEMORY_CATEGORY_TYPES))
            .updateSerializer(StartGamePacket.class, StartGameSerializer_v924.INSTANCE)
            .updateSerializer(TextPacket.class, TextSerializer_v924.INSTANCE)
            .registerPacket(ClientboundDataDrivenUIShowScreenPacket::new, ClientboundDataDrivenUIShowScreenSerializer_v924.INSTANCE, 333, PacketRecipient.CLIENT)
            .registerPacket(ClientboundDataDrivenUICloseAllScreensPacket::new, ClientboundDataDrivenUICloseAllScreensSerializer_v924.INSTANCE, 334, PacketRecipient.CLIENT)
            .registerPacket(ClientboundDataDrivenUIReloadPacket::new, ClientboundDataDrivenUIReloadSerializer_v924.INSTANCE, 335, PacketRecipient.CLIENT)
            .registerPacket(ClientboundTextureShiftPacket::new, ClientboundTextureShiftSerializer_v924.INSTANCE, 336, PacketRecipient.CLIENT)
            .registerPacket(VoxelShapesPacket::new, VoxelShapesSerializer_v924.INSTANCE, 337, PacketRecipient.CLIENT)
            .registerPacket(CameraSplinePacket::new, CameraSplineSerializer_v924.INSTANCE, 338, PacketRecipient.CLIENT)
            .registerPacket(CameraAimAssistActorPriorityPacket::new, CameraAimAssistActorPrioritySerializer_v924.INSTANCE, 339, PacketRecipient.CLIENT)
            .build();
}