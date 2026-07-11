package org.cloudburstmc.protocol.bedrock.codec.v2168;

import org.cloudburstmc.protocol.bedrock.codec.ActorDataTypeMap;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodec;
import org.cloudburstmc.protocol.bedrock.codec.v1001.Bedrock_v1001;
import org.cloudburstmc.protocol.bedrock.codec.v2168.serializer.*;
import org.cloudburstmc.protocol.bedrock.data.LevelEventType;
import org.cloudburstmc.protocol.bedrock.data.ParticleType;
import org.cloudburstmc.protocol.bedrock.data.SoundEvent;
import org.cloudburstmc.protocol.bedrock.data.actor.ActorDataTypes;
import org.cloudburstmc.protocol.bedrock.data.actor.ActorFlags;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.action.ItemStackRequestActionType;
import org.cloudburstmc.protocol.bedrock.data.payload.diagnostics.MemoryCategory;
import org.cloudburstmc.protocol.bedrock.packet.*;
import org.cloudburstmc.protocol.bedrock.transformer.FlagTransformer;
import org.cloudburstmc.protocol.bedrock.transformer.TypeMapTransformer;
import org.cloudburstmc.protocol.common.util.TypeMap;

/**
 * @author Kaooot
 */
public class Bedrock_v2168 extends Bedrock_v1001 {

    protected static final TypeMap<MemoryCategory> MEMORY_CATEGORY_TYPES = Bedrock_v1001.MEMORY_CATEGORY_TYPES.toBuilder()
            .shift(7, 55, 1)
            .insert(7, MemoryCategory.BLOBS)
            .shift(55, 7)
            .insert(55, MemoryCategory.ORE_UI_CLIENT)
            .insert(56, MemoryCategory.PERSONA_PIECES)
            .insert(57, MemoryCategory.PERSONA_ANIMATIONS)
            .insert(58, MemoryCategory.PERSONA_TEXTURES)
            .insert(59, MemoryCategory.PERSONA_CHARACTERS)
            .insert(60, MemoryCategory.PERSONA_SKIN_PACKS)
            .insert(61, MemoryCategory.PERSONA_REPO)
            .remove(67)
            .shift(67, 4)
            .insert(67, MemoryCategory.RENDERING_BGFX_INIT)
            .insert(68, MemoryCategory.RENDERING_BGFX_START_FRAME)
            .insert(69, MemoryCategory.RENDERING_BGFX_TESSELLATOR)
            .insert(70, MemoryCategory.RENDERING_BGFX_END_FRAME)
            .insert(71, MemoryCategory.RENDERING_BGFX_GRAPHICS_TASKS_INIT)
            .shift(73, 5)
            .insert(73, MemoryCategory.RENDERING_POLYGON_OPERATOR_POOL)
            .insert(74, MemoryCategory.RENDERING_PBR_TEXTURE_DATA)
            .insert(75, MemoryCategory.RENDERING_RENDER_REGISTRY) // moved from index 66
            .insert(76, MemoryCategory.RENDERING_SETUP)
            .insert(77, MemoryCategory.RENDERING_VERTICES)
            .shift(89, 1)
            .insert(89, MemoryCategory.TEST_LOAD_TEST_FLAGS)
            .insert(109, MemoryCategory.GAMEFACE_SCRIPT)
            .insert(110, MemoryCategory.GAMEFACE_LAYOUT)
            .build();

    protected static final TypeMap<ItemStackRequestActionType> ITEM_STACK_REQUEST_TYPES = TypeMap.builder(ItemStackRequestActionType.class)
            .insert(0, ItemStackRequestActionType.TAKE)
            .insert(1, ItemStackRequestActionType.PLACE)
            .insert(2, ItemStackRequestActionType.SWAP)
            .insert(3, ItemStackRequestActionType.DROP)
            .insert(4, ItemStackRequestActionType.DESTROY)
            .insert(5, ItemStackRequestActionType.CONSUME)
            .insert(6, ItemStackRequestActionType.CREATE)
            .insert(7, ItemStackRequestActionType.SCREEN_LAB_TABLE_COMBINE)
            .insert(8, ItemStackRequestActionType.SCREEN_BEACON_PAYMENT)
            .insert(9, ItemStackRequestActionType.SCREEN_HUD_MINE_BLOCK)
            .insert(10, ItemStackRequestActionType.CRAFT_RECIPE)
            .insert(11, ItemStackRequestActionType.CRAFT_RECIPE_AUTO)
            .insert(12, ItemStackRequestActionType.CRAFT_CREATIVE)
            .insert(13, ItemStackRequestActionType.CRAFT_RECIPE_OPTIONAL)
            .insert(14, ItemStackRequestActionType.CRAFT_REPAIR_AND_DISENCHANT)
            .insert(15, ItemStackRequestActionType.CRAFT_LOOM)
            .insert(16, ItemStackRequestActionType.CRAFT_NON_IMPLEMENTED)
            .insert(17, ItemStackRequestActionType.CRAFT_RESULTS)
            .build();

    protected static final TypeMap<SoundEvent> SOUND_EVENTS = Bedrock_v1001.SOUND_EVENTS.toBuilder()
            .replace(611, SoundEvent.MOUNT)
            .insert(612, SoundEvent.DISMOUNT)
            .insert(613, SoundEvent.STRAW_BED_BREAK_LEAVE)
            .insert(614, SoundEvent.UNDEFINED)
            .build();

    protected static final TypeMap<ParticleType> PARTICLE_TYPES = Bedrock_v1001.PARTICLE_TYPES.toBuilder()
            .insert(102, ParticleType.ORANGE_POPLAR_LEAVES)
            .insert(103, ParticleType.RED_POPLAR_LEAVES)
            .insert(104, ParticleType.YELLOW_POPLAR_LEAVES)
            .build();

    protected static final TypeMap<LevelEventType> LEVEL_EVENTS = Bedrock_v1001.LEVEL_EVENTS.toBuilder()
            .insert(LEVEL_EVENT_PARTICLE_TYPE, PARTICLE_TYPES)
            .build();

    protected static final TypeMap<ActorFlags> ACTOR_FLAGS = Bedrock_v1001.ACTOR_FLAGS
            .toBuilder()
            .insert(130, ActorFlags.NOT_PICKABLE_FROM_INSIDE)
            .build();

    protected static final ActorDataTypeMap ACTOR_DATA = Bedrock_v1001.ACTOR_DATA
            .toBuilder()
            .update(ActorDataTypes.FLAGS, new FlagTransformer(ACTOR_FLAGS, 0))
            .update(ActorDataTypes.FLAGS_2, new FlagTransformer(ACTOR_FLAGS, 1))
            .update(ActorDataTypes.HEARTBEAT_SOUND_EVENT, new TypeMapTransformer<>(SOUND_EVENTS))
            .update(ActorDataTypes.DATA_PARTICLE, new TypeMapTransformer<>(PARTICLE_TYPES))
            .build();

    public static final BedrockCodec CODEC = Bedrock_v1001.CODEC.toBuilder()
            .raknetProtocolVersion(11)
            .protocolVersion(2168)
            .minecraftVersion("1.26.40")
            .helper(() -> new BedrockCodecHelper_v2168(ACTOR_DATA, GAME_RULE_TYPES, ITEM_STACK_REQUEST_TYPES, CONTAINER_SLOT_TYPES, PLAYER_ABILITIES, TEXT_PROCESSING_ORIGINS))
            .updateSerializer(AddActorPacket.class, AddActorSerializer_v2168.INSTANCE)
            .updateSerializer(AddItemActorPacket.class, AddItemActorSerializer_v2168.INSTANCE)
            .updateSerializer(AddPlayerPacket.class, AddPlayerSerializer_v2168.INSTANCE)
            .updateSerializer(AnvilDamagePacket.class, AnvilDamageSerializer_v2168.INSTANCE)
            .updateSerializer(ClientboundMapItemDataPacket.class, ClientboundMapItemDataSerializer_v2168.INSTANCE)
            .updateSerializer(ClientboundUpdateSoundDataPacket.class, ClientboundUpdateSoundDataSerializer_v2168.INSTANCE)
            .updateSerializer(CraftingDataPacket.class, CraftingDataSerializer_v2168.INSTANCE)
            .updateSerializer(CreativeContentPacket.class, CreativeContentSerializer_v2168.INSTANCE)
            .updateSerializer(DimensionDataPacket.class, DimensionDataSerializer_v2168.INSTANCE)
            .updateSerializer(ItemStackResponsePacket.class, ItemStackResponseSerializer_v2168.INSTANCE)
            .updateSerializer(LevelChunkPacket.class, LevelChunkSerializer_v2168.INSTANCE)
            .updateSerializer(MoveActorDeltaPacket.class, MoveActorDeltaSerializer_v2168.INSTANCE)
            .updateSerializer(MovePlayerPacket.class, MovePlayerSerializer_v2168.INSTANCE)
            .updateSerializer(PlayerAuthInputPacket.class, PlayerAuthInputSerializer_v2168.INSTANCE)
            .updateSerializer(PlayerListPacket.class, PlayerListSerializer_v2168.INSTANCE) // TODO fix
            .updateSerializer(PlayerLocationPacket.class, PlayerLocationSerializer_v2168.INSTANCE)
            .updateSerializer(PlayerSkinPacket.class, PlayerSkinSerializer_v2168.INSTANCE)
            .updateSerializer(PlayerUpdateEntityOverridesPacket.class, PlayerUpdateEntityOverridesSerializer_v2168.INSTANCE)
            .updateSerializer(PlaySoundPacket.class, PlaySoundSerializer_v2168.INSTANCE)
            .updateSerializer(ResourcePackClientResponsePacket.class, ResourcePackClientResponseSerializer_v2168.INSTANCE)
            .updateSerializer(ResourcePacksInfoPacket.class, ResourcePacksInfoSerializer_v2168.INSTANCE)
            .updateSerializer(ServerboundDiagnosticsPacket.class, new ServerboundDiagnosticsSerializer_v2168(MEMORY_CATEGORY_TYPES))
            .updateSerializer(SetScoreboardIdentityPacket.class, SetScoreboardIdentitySerializer_v2168.INSTANCE)
            .updateSerializer(SetScorePacket.class, SetScoreSerializer_v2168.INSTANCE)
            .updateSerializer(StartGamePacket.class, StartGameSerializer_v2168.INSTANCE)
            .updateSerializer(StructureBlockUpdatePacket.class, StructureBlockUpdateSerializer_v2168.INSTANCE)
            .updateSerializer(SubChunkPacket.class, SubChunkSerializer_v2168.INSTANCE)
            .updateSerializer(TransferPacket.class, TransferSerializer_v2168.INSTANCE)
            .build();
}