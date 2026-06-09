package org.cloudburstmc.protocol.bedrock.codec.v1011;

import org.cloudburstmc.protocol.bedrock.codec.BedrockCodec;
import org.cloudburstmc.protocol.bedrock.codec.v1001.Bedrock_v1001;
import org.cloudburstmc.protocol.bedrock.codec.v1011.serializer.*;
import org.cloudburstmc.protocol.bedrock.data.payload.diagnostics.MemoryCategory;
import org.cloudburstmc.protocol.bedrock.packet.*;
import org.cloudburstmc.protocol.common.util.TypeMap;

/**
 * @author Kaooot
 */
public class Bedrock_v1011 extends Bedrock_v1001 {

    private static final TypeMap<MemoryCategory> MEMORY_CATEGORY_TYPES = Bedrock_v1001.MEMORY_CATEGORY_TYPES.toBuilder()
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

    public static final BedrockCodec CODEC = Bedrock_v1001.CODEC.toBuilder()
            .raknetProtocolVersion(11)
            .protocolVersion(1011)
            .minecraftVersion("1.26.40-beta.20")
            .helper(() -> new BedrockCodecHelper_v1011(ACTOR_DATA, GAME_RULE_TYPES, ITEM_STACK_REQUEST_TYPES, CONTAINER_SLOT_TYPES, PLAYER_ABILITIES, TEXT_PROCESSING_ORIGINS))
            .updateSerializer(AddActorPacket.class, AddActorSerializer_v1011.INSTANCE)
            .updateSerializer(PlaySoundPacket.class, PlaySoundSerializer_v1011.INSTANCE)
            .updateSerializer(ServerboundDiagnosticsPacket.class, new ServerboundDiagnosticsSerializer_v1011(MEMORY_CATEGORY_TYPES))
            .updateSerializer(ServerPresenceInfoPacket.class, ServerPresenceInfoSerializer_v1011.INSTANCE)
            .updateSerializer(StartGamePacket.class, StartGameSerializer_v1011.INSTANCE)
            .updateSerializer(TransferPacket.class, TransferSerializer_v1011.INSTANCE)
            .build();
}