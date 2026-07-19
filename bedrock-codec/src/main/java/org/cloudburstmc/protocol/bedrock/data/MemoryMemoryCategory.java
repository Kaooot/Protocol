package org.cloudburstmc.protocol.bedrock.data;

import java.lang.UnsupportedOperationException;

/**
 * Auto generated from 1.26.40-beta.31 (v2168)
 */
public enum MemoryMemoryCategory {
  /**
   * Unknown
   */
  UNKNOWN,

  /**
   * Invalid_SizeUnknown
   */
  INVALID_SIZE_UNKNOWN,

  /**
   * Actor
   */
  ACTOR,

  /**
   * ActorAnimation
   */
  ACTOR_ANIMATION,

  /**
   * ActorRendering
   */
  ACTOR_RENDERING,

  /**
   * BlockTickingQueues
   */
  BLOCK_TICKING_QUEUES,

  /**
   * Biome_Storage
   */
  BIOME_STORAGE,

  /**
   * Blobs
   */
  BLOBS,

  /**
   * Cereal
   */
  CEREAL,

  /**
   * CircuitSystem
   */
  CIRCUIT_SYSTEM,

  /**
   * Client
   */
  CLIENT,

  /**
   * Commands
   */
  COMMANDS,

  /**
   * DBStorage
   */
  DBSTORAGE,

  /**
   * Debug
   */
  DEBUG,

  /**
   * Documentation
   */
  DOCUMENTATION,

  /**
   * ECSSystems
   */
  ECSSYSTEMS,

  FMOD,

  /**
   * Fonts
   */
  FONTS,

  /**
   * ImGui
   */
  IM_GUI,

  /**
   * Input
   */
  INPUT,

  /**
   * JsonUI
   */
  JSON_UI,

  /**
   * JsonUI_ControlFactory_Json
   */
  JSON_UI_CONTROL_FACTORY_JSON,

  /**
   * JsonUI_ControlTree
   */
  JSON_UI_CONTROL_TREE,

  /**
   * JsonUI_ControlTree_ControlElement
   */
  JSON_UI_CONTROL_TREE_CONTROL_ELEMENT,

  /**
   * JsonUI_ControlTree_PopulateDataBinding
   */
  JSON_UI_CONTROL_TREE_POPULATE_DATA_BINDING,

  /**
   * JsonUI_ControlTree_PopulateFocus
   */
  JSON_UI_CONTROL_TREE_POPULATE_FOCUS,

  /**
   * JsonUI_ControlTree_PopulateLayout
   */
  JSON_UI_CONTROL_TREE_POPULATE_LAYOUT,

  /**
   * JsonUI_ControlTree_PopulateOther
   */
  JSON_UI_CONTROL_TREE_POPULATE_OTHER,

  /**
   * JsonUI_ControlTree_PopulateSprite
   */
  JSON_UI_CONTROL_TREE_POPULATE_SPRITE,

  /**
   * JsonUI_ControlTree_PopulateText
   */
  JSON_UI_CONTROL_TREE_POPULATE_TEXT,

  /**
   * JsonUI_ControlTree_PopulateTTS
   */
  JSON_UI_CONTROL_TREE_POPULATE_TTS,

  /**
   * JsonUI_ControlTree_Visibility
   */
  JSON_UI_CONTROL_TREE_VISIBILITY,

  /**
   * JsonUI_CreateUI
   */
  JSON_UI_CREATE_UI,

  /**
   * JsonUI_Defs
   */
  JSON_UI_DEFS,

  /**
   * JsonUI_LayoutManager
   */
  JSON_UI_LAYOUT_MANAGER,

  /**
   * JsonUI_LayoutManager_RemoveDependencies
   */
  JSON_UI_LAYOUT_MANAGER_REMOVE_DEPENDENCIES,

  /**
   * JsonUI_LayoutManager_InitVariable
   */
  JSON_UI_LAYOUT_MANAGER_INIT_VARIABLE,

  /**
   * Languages
   */
  LANGUAGES,

  /**
   * Level
   */
  LEVEL,

  /**
   * LevelStructures
   */
  LEVEL_STRUCTURES,

  /**
   * LevelChunk
   */
  LEVEL_CHUNK,

  /**
   * LevelChunkGen
   */
  LEVEL_CHUNK_GEN,

  /**
   * LevelChunkGenThreadLocal
   */
  LEVEL_CHUNK_GEN_THREAD_LOCAL,

  /**
   * LightVolumeManager
   */
  LIGHT_VOLUME_MANAGER,

  /**
   * Network
   */
  NETWORK,

  /**
   * Marketplace
   */
  MARKETPLACE,

  /**
   * Material_DragonCompiledDefinition
   */
  MATERIAL_DRAGON_COMPILED_DEFINITION,

  /**
   * Material_DragonMaterial
   */
  MATERIAL_DRAGON_MATERIAL,

  /**
   * Material_DragonResource
   */
  MATERIAL_DRAGON_RESOURCE,

  /**
   * Material_DragonUniformMap
   */
  MATERIAL_DRAGON_UNIFORM_MAP,

  /**
   * Material_RenderMaterial
   */
  MATERIAL_RENDER_MATERIAL,

  /**
   * Material_RenderMaterialGroup
   */
  MATERIAL_RENDER_MATERIAL_GROUP,

  /**
   * Material_VariationManager
   */
  MATERIAL_VARIATION_MANAGER,

  /**
   * Molang
   */
  MOLANG,

  /**
   * OreUI
   */
  ORE_UI,

  /**
   * OreUI_Client
   */
  ORE_UI_CLIENT,

  /**
   * Persona_Pieces
   */
  PERSONA_PIECES,

  /**
   * Persona_Animations
   */
  PERSONA_ANIMATIONS,

  /**
   * Persona_Textures
   */
  PERSONA_TEXTURES,

  /**
   * Persona_Characters
   */
  PERSONA_CHARACTERS,

  /**
   * Persona_SkinPacks
   */
  PERSONA_SKIN_PACKS,

  /**
   * Persona_Repo
   */
  PERSONA_REPO,

  /**
   * Player
   */
  PLAYER,

  /**
   * RenderChunk
   */
  RENDER_CHUNK,

  /**
   * RenderChunk_IndexBuffer
   */
  RENDER_CHUNK_INDEX_BUFFER,

  /**
   * RenderChunk_VertexBuffer
   */
  RENDER_CHUNK_VERTEX_BUFFER,

  /**
   * Rendering
   */
  RENDERING,

  /**
   * Rendering_BgfxInit
   */
  RENDERING_BGFX_INIT,

  /**
   * Rendering_BgfxStartFrame
   */
  RENDERING_BGFX_START_FRAME,

  /**
   * Rendering_BlockTessellator
   */
  RENDERING_BLOCK_TESSELLATOR,

  /**
   * Rendering_EndFrame
   */
  RENDERING_END_FRAME,

  /**
   * Rendering_GraphicsTasksInit
   */
  RENDERING_GRAPHICS_TASKS_INIT,

  /**
   * Rendering_Library
   */
  RENDERING_LIBRARY,

  /**
   * Rendering_PolygonOperatorPool
   */
  RENDERING_POLYGON_OPERATOR_POOL,

  /**
   * Rendering_PBRTextureData
   */
  RENDERING_PBRTEXTURE_DATA,

  /**
   * Rendering_RenderRegistry
   */
  RENDERING_RENDER_REGISTRY,

  /**
   * Rendering_Setup
   */
  RENDERING_SETUP,

  /**
   * Rendering_Vertices
   */
  RENDERING_VERTICES,

  /**
   * RequestLog
   */
  REQUEST_LOG,

  /**
   * ResourcePacks
   */
  RESOURCE_PACKS,

  /**
   * Sound
   */
  SOUND,

  /**
   * SubChunk_BiomeData
   */
  SUB_CHUNK_BIOME_DATA,

  /**
   * SubChunk_BlockData
   */
  SUB_CHUNK_BLOCK_DATA,

  /**
   * SubChunk_LightData
   */
  SUB_CHUNK_LIGHT_DATA,

  /**
   * Textures
   */
  TEXTURES,

  /**
   * WeatherRenderer
   */
  WEATHER_RENDERER,

  /**
   * World_Generator
   */
  WORLD_GENERATOR,

  /**
   * Tasks
   */
  TASKS,

  /**
   * Test
   */
  TEST,

  /**
   * Test_LoadTestTags
   */
  TEST_LOAD_TEST_TAGS,

  /**
   * Scripting
   */
  SCRIPTING,

  /**
   * Scripting_Runtime
   */
  SCRIPTING_RUNTIME,

  /**
   * Scripting_Context
   */
  SCRIPTING_CONTEXT,

  /**
   * Scripting_Context_Bindings_MC
   */
  SCRIPTING_CONTEXT_BINDINGS_MC,

  /**
   * Scripting_Context_Bindings_GT
   */
  SCRIPTING_CONTEXT_BINDINGS_GT,

  /**
   * Scripting_Context_Run
   */
  SCRIPTING_CONTEXT_RUN,

  /**
   * DataDrivenUI
   */
  DATA_DRIVEN_UI,

  /**
   * DataDrivenUI_Defs
   */
  DATA_DRIVEN_UI_DEFS,

  /**
   * Gameface
   */
  GAMEFACE,

  /**
   * Gameface_System
   */
  GAMEFACE_SYSTEM,

  /**
   * Gameface_DOM
   */
  GAMEFACE_DOM,

  /**
   * Gameface_CSS
   */
  GAMEFACE_CSS,

  /**
   * Gameface_Display
   */
  GAMEFACE_DISPLAY,

  /**
   * Gameface_TempAllocator
   */
  GAMEFACE_TEMP_ALLOCATOR,

  /**
   * Gameface_PoolAllocator
   */
  GAMEFACE_POOL_ALLOCATOR,

  /**
   * Gameface_Dump
   */
  GAMEFACE_DUMP,

  /**
   * Gameface_Media
   */
  GAMEFACE_MEDIA,

  /**
   * Gameface_JSON
   */
  GAMEFACE_JSON,

  /**
   * Gameface_ScriptEngine
   */
  GAMEFACE_SCRIPT_ENGINE,

  /**
   * Gameface_Script
   */
  GAMEFACE_SCRIPT,

  /**
   * Gameface_Layout
   */
  GAMEFACE_LAYOUT;

  private static final MemoryMemoryCategory[] VALUES = values();

  public static MemoryMemoryCategory from(int ordinal) {
    if (ordinal >= 0 && ordinal < VALUES.length) {
      return VALUES[ordinal];
    }
    throw new UnsupportedOperationException("Detected unknown MemoryMemoryCategory ID: " + ordinal);
  }
}
