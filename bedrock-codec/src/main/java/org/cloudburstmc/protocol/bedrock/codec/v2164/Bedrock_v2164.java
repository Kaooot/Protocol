package org.cloudburstmc.protocol.bedrock.codec.v2164;

import org.cloudburstmc.protocol.bedrock.codec.BedrockCodec;
import org.cloudburstmc.protocol.bedrock.codec.v1001.Bedrock_v1001;
import org.cloudburstmc.protocol.bedrock.codec.v2164.serializer.*;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.action.ItemStackRequestActionType;
import org.cloudburstmc.protocol.bedrock.data.payload.diagnostics.MemoryCategory;
import org.cloudburstmc.protocol.bedrock.packet.*;
import org.cloudburstmc.protocol.common.util.TypeMap;

/**
 * @author Kaooot
 */
public class Bedrock_v2164 extends Bedrock_v1001 {

    protected static final TypeMap<MemoryCategory> MEMORY_CATEGORY_TYPES = Bedrock_v1001.MEMORY_CATEGORY_TYPES.toBuilder()
            .shift(7, 55, 1)
            .insert(7, MemoryCategory.BLOBS)
            .shift(55, 6)
            .insert(55, MemoryCategory.PERSONA_PIECES)
            .insert(56, MemoryCategory.PERSONA_ANIMATIONS)
            .insert(57, MemoryCategory.PERSONA_TEXTURES)
            .insert(58, MemoryCategory.PERSONA_CHARACTERS)
            .insert(59, MemoryCategory.PERSONA_SKIN_PACKS)
            .insert(60, MemoryCategory.PERSONA_REPO)
            .remove(66)
            .shift(66, 4)
            .insert(66, MemoryCategory.RENDERING_BGFX_INIT)
            .insert(67, MemoryCategory.RENDERING_BGFX_START_FRAME)
            .insert(68, MemoryCategory.RENDERING_BGFX_TESSELLATOR)
            .insert(69, MemoryCategory.RENDERING_BGFX_END_FRAME)
            .insert(70, MemoryCategory.RENDERING_BGFX_GRAPHICS_TASKS_INIT)
            .shift(72, 5)
            .insert(72, MemoryCategory.RENDERING_POLYGON_OPERATOR_POOL)
            .insert(73, MemoryCategory.RENDERING_PBR_TEXTURE_DATA)
            .insert(74, MemoryCategory.RENDERING_RENDER_REGISTRY) // moved from index 66
            .insert(75, MemoryCategory.RENDERING_SETUP)
            .insert(76, MemoryCategory.RENDERING_VERTICES)
            .shift(88, 1)
            .insert(88, MemoryCategory.TEST_LOAD_TEST_FLAGS)
            .insert(108, MemoryCategory.GAMEFACE_SCRIPT)
            .insert(109, MemoryCategory.GAMEFACE_LAYOUT)
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

    // TODO scoreboard packets
    // TODO debug attributes issue
    public static final BedrockCodec CODEC = Bedrock_v1001.CODEC.toBuilder()
            .raknetProtocolVersion(11)
            .protocolVersion(2164)
            .minecraftVersion("1.26.40-beta.27")
            .helper(() -> new BedrockCodecHelper_v2164(ACTOR_DATA, GAME_RULE_TYPES, ITEM_STACK_REQUEST_TYPES, CONTAINER_SLOT_TYPES, PLAYER_ABILITIES, TEXT_PROCESSING_ORIGINS))
            .updateSerializer(AddActorPacket.class, AddActorSerializer_v2164.INSTANCE)
            .updateSerializer(AddItemActorPacket.class, AddItemActorSerializer_v2164.INSTANCE)
            .updateSerializer(AddPlayerPacket.class, AddPlayerSerializer_v2164.INSTANCE)
            .updateSerializer(AnvilDamagePacket.class, AnvilDamageSerializer_v2164.INSTANCE)
            .updateSerializer(ClientboundMapItemDataPacket.class, ClientboundMapItemDataSerializer_v2164.INSTANCE) // TODO implement, backwards compatibility
            .updateSerializer(ClientboundUpdateSoundDataPacket.class, ClientboundUpdateSoundDataSerializer_v2164.INSTANCE)
            .updateSerializer(CraftingDataPacket.class, CraftingDataSerializer_v2164.INSTANCE) // TODO backwards compatibility
            .updateSerializer(CreativeContentPacket.class, CreativeContentSerializer_v2164.INSTANCE)
            .updateSerializer(DimensionDataPacket.class, DimensionDataSerializer_v2164.INSTANCE)
            .updateSerializer(ItemStackResponsePacket.class, ItemStackResponseSerializer_v2164.INSTANCE)
            .updateSerializer(LevelChunkPacket.class, LevelChunkSerializer_v2164.INSTANCE)
            .updateSerializer(MoveActorDeltaPacket.class, MoveActorDeltaSerializer_v2164.INSTANCE)
            .updateSerializer(MovePlayerPacket.class, MovePlayerSerializer_v2164.INSTANCE)
            .updateSerializer(PlayerAuthInputPacket.class, PlayerAuthInputSerializer_v2164.INSTANCE)
            .updateSerializer(PlayerListPacket.class, PlayerListSerializer_v2164.INSTANCE) // TODO fix, backwards compatibility
            .updateSerializer(PlayerLocationPacket.class, PlayerLocationSerializer_v2164.INSTANCE)
            .updateSerializer(PlayerUpdateEntityOverridesPacket.class, PlayerUpdateEntityOverridesSerializer_v2164.INSTANCE)
            .updateSerializer(PlaySoundPacket.class, PlaySoundSerializer_v2164.INSTANCE)
            .updateSerializer(ResourcePackClientResponsePacket.class, ResourcePackClientResponseSerializer_v2164.INSTANCE)
            .updateSerializer(ResourcePacksInfoPacket.class, ResourcePacksInfoSerializer_v2164.INSTANCE)
            .updateSerializer(ServerboundDiagnosticsPacket.class, new ServerboundDiagnosticsSerializer_v2164(MEMORY_CATEGORY_TYPES))
            .updateSerializer(ServerPresenceInfoPacket.class, ServerPresenceInfoSerializer_v2164.INSTANCE)
            .updateSerializer(StartGamePacket.class, StartGameSerializer_v2164.INSTANCE)
            .updateSerializer(StructureBlockUpdatePacket.class, StructureBlockUpdateSerializer_v2164.INSTANCE)
            .updateSerializer(SubChunkPacket.class, SubChunkSerializer_v2164.INSTANCE) // TODO backwards compatibility
            .updateSerializer(TransferPacket.class, TransferSerializer_v2164.INSTANCE)
            .build();
}