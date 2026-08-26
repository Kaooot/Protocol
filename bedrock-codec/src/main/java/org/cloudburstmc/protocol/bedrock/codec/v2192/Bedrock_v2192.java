package org.cloudburstmc.protocol.bedrock.codec.v2192;

import org.cloudburstmc.protocol.bedrock.codec.BedrockCodec;
import org.cloudburstmc.protocol.bedrock.codec.v2168.Bedrock_v2168;
import org.cloudburstmc.protocol.bedrock.codec.v2168.serializer.ClientboundMapItemDataSerializer_v2168;
import org.cloudburstmc.protocol.bedrock.codec.v2192.serializer.*;
import org.cloudburstmc.protocol.bedrock.data.PacketRecipient;
import org.cloudburstmc.protocol.bedrock.data.payload.diagnostics.MemoryCategory;
import org.cloudburstmc.protocol.bedrock.data.payload.map.MapDecoration;
import org.cloudburstmc.protocol.bedrock.packet.*;
import org.cloudburstmc.protocol.common.util.TypeMap;

/**
 * @author Kaooot
 */
public class Bedrock_v2192 extends Bedrock_v2168 {

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
            .protocolVersion(2192)
            .minecraftVersion("1.26.50")
            .helper(() -> new BedrockCodecHelper_v2192(ACTOR_DATA, GAME_RULE_TYPES, ITEM_STACK_REQUEST_TYPES, CONTAINER_SLOT_TYPES, PLAYER_ABILITIES, TEXT_PROCESSING_ORIGINS))
            .updateSerializer(CameraPresetsPacket.class, CameraPresetsSerializer_v2192.INSTANCE)
            .updateSerializer(ClientboundAttributeLayerSyncPacket.class, ClientboundAttributeLayerSyncSerializer_v2192.INSTANCE)
            .updateSerializer(ClientboundMapItemDataPacket.class, new ClientboundMapItemDataSerializer_v2168(MAP_DECORATION_TYPES))
            .updateSerializer(InventoryTransactionPacket.class, InventoryTransactionSerializer_v2192.INSTANCE)
            .updateSerializer(ItemStackResponsePacket.class, ItemStackResponseSerializer_v2192.INSTANCE)
            .updateSerializer(MoveActorDeltaPacket.class, MoveActorDeltaSerializer_v2192.INSTANCE)
            .updateSerializer(PlayerAuthInputPacket.class, PlayerAuthInputSerializer_v2192.INSTANCE)
            .updateSerializer(PlaySoundPacket.class, PlaySoundSerializer_v2192.INSTANCE)
            .updateSerializer(PrimitiveShapesPacket.class, PrimitiveShapesSerializer_v2192.INSTANCE)
            .updateSerializer(ServerboundDiagnosticsPacket.class, new ServerboundDiagnosticsSerializer_v2192(MEMORY_CATEGORY_TYPES))
            .updateSerializer(SubChunkPacket.class, SubChunkSerializer_v2192.INSTANCE)
            .registerPacket(SetPlayerFurnaceOptionsPacket::new, SetPlayerFurnaceOptionsSerializer_v2192.INSTANCE, 351, PacketRecipient.BOTH)
            .registerPacket(RecordStartedPacket::new, RecordStartedSerializer_v2192.INSTANCE, 352, PacketRecipient.BOTH)
            .build();
}