package org.cloudburstmc.protocol.bedrock.codec.v2181;

import org.cloudburstmc.protocol.bedrock.codec.BedrockCodec;
import org.cloudburstmc.protocol.bedrock.codec.v2168.BedrockCodecHelper_v2168;
import org.cloudburstmc.protocol.bedrock.codec.v2168.Bedrock_v2168;
import org.cloudburstmc.protocol.bedrock.codec.v2168.serializer.ClientboundMapItemDataSerializer_v2168;
import org.cloudburstmc.protocol.bedrock.codec.v2181.serializer.*;
import org.cloudburstmc.protocol.bedrock.data.PacketRecipient;
import org.cloudburstmc.protocol.bedrock.data.payload.diagnostics.MemoryCategory;
import org.cloudburstmc.protocol.bedrock.data.payload.map.MapDecoration;
import org.cloudburstmc.protocol.bedrock.data.payload.skin.AnimatedTextureType;
import org.cloudburstmc.protocol.bedrock.packet.*;
import org.cloudburstmc.protocol.common.util.TypeMap;

/**
 * @author Kaooot
 */
public class Bedrock_v2181 extends Bedrock_v2168 {

    protected static final TypeMap<MemoryCategory> MEMORY_CATEGORY_TYPES = Bedrock_v2168.MEMORY_CATEGORY_TYPES.toBuilder()
            .remove(58)
            .shift(58, -1)
            .build();

    protected static final TypeMap<MapDecoration.Type> MAP_DECORATION_TYPES = Bedrock_v2168.MAP_DECORATION_TYPES
            .toBuilder()
            .remove(25)
            .insert(25, MapDecoration.Type.ABANDONED_CAMP)
            .insert(26, MapDecoration.Type.BURIED_ANCIENT_CITY)
            .insert(27, MapDecoration.Type.BURIED_MINESHAFT)
            .insert(28, MapDecoration.Type.DESERT_PYRAMID)
            .insert(29, MapDecoration.Type.WARM_OCEAN_RUINS)
            .insert(30, MapDecoration.Type.COUNT)
            .build();

    public static final BedrockCodec CODEC = Bedrock_v2168.CODEC.toBuilder()
            .raknetProtocolVersion(11)
            .protocolVersion(2181)
            .minecraftVersion("1.26.50-beta.24")
            .helper(() -> new BedrockCodecHelper_v2168(ACTOR_DATA, GAME_RULE_TYPES, ITEM_STACK_REQUEST_TYPES, CONTAINER_SLOT_TYPES, PLAYER_ABILITIES, TEXT_PROCESSING_ORIGINS))
            .updateSerializer(CameraPresetsPacket.class, CameraPresetsSerializer_v2181.INSTANCE)
            .updateSerializer(ClientboundAttributeLayerSyncPacket.class, ClientboundAttributeLayerSyncSerializer_v2181.INSTANCE)
            .updateSerializer(ClientboundMapItemDataPacket.class, new ClientboundMapItemDataSerializer_v2168(MAP_DECORATION_TYPES))
            .updateSerializer(MoveActorDeltaPacket.class, MoveActorDeltaSerializer_v2181.INSTANCE)
            .updateSerializer(PrimitiveShapesPacket.class, PrimitiveShapesSerializer_v2181.INSTANCE)
            .updateSerializer(ServerboundDiagnosticsPacket.class, new ServerboundDiagnosticsSerializer_v2181(MEMORY_CATEGORY_TYPES))
            .registerPacket(SetPlayerFurnaceOptionsPacket::new, SetPlayerFurnaceOptionsSerializer_v2181.INSTANCE, 351, PacketRecipient.BOTH)
            .build();
}