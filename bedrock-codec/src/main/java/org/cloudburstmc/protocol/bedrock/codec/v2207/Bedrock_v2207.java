package org.cloudburstmc.protocol.bedrock.codec.v2207;

import org.cloudburstmc.protocol.bedrock.codec.BedrockCodec;
import org.cloudburstmc.protocol.bedrock.codec.v2192.Bedrock_v2192;
import org.cloudburstmc.protocol.bedrock.codec.v2192.serializer.ServerboundDiagnosticsSerializer_v2192;
import org.cloudburstmc.protocol.bedrock.codec.v2207.serializer.AnimateSerializer_v2207;
import org.cloudburstmc.protocol.bedrock.codec.v2207.serializer.ClientboundAttributeLayerSyncSerializer_v2207;
import org.cloudburstmc.protocol.bedrock.codec.v2207.serializer.ClientboundMatchmakingStateSerializer_v2207;
import org.cloudburstmc.protocol.bedrock.codec.v2207.serializer.ClientboundStonecutterSetRecipeSerializer_v2207;
import org.cloudburstmc.protocol.bedrock.codec.v2207.serializer.DimensionDataSerializer_v2207;
import org.cloudburstmc.protocol.bedrock.codec.v2207.serializer.InventoryTransactionSerializer_v2207;
import org.cloudburstmc.protocol.bedrock.codec.v2207.serializer.PlayerListSerializer_v2207;
import org.cloudburstmc.protocol.bedrock.codec.v2207.serializer.ServerboundMatchmakingCancelSerializer_v2207;
import org.cloudburstmc.protocol.bedrock.codec.v2207.serializer.ServerboundStonecutterSetRecipeSerializer_v2207;
import org.cloudburstmc.protocol.bedrock.data.PacketRecipient;
import org.cloudburstmc.protocol.bedrock.data.payload.diagnostics.MemoryCategory;
import org.cloudburstmc.protocol.bedrock.packet.AnimatePacket;
import org.cloudburstmc.protocol.bedrock.packet.ClientboundAttributeLayerSyncPacket;
import org.cloudburstmc.protocol.bedrock.packet.ClientboundMatchmakingStatePacket;
import org.cloudburstmc.protocol.bedrock.packet.ClientboundStonecutterSetRecipePacket;
import org.cloudburstmc.protocol.bedrock.packet.DimensionDataPacket;
import org.cloudburstmc.protocol.bedrock.packet.InventoryTransactionPacket;
import org.cloudburstmc.protocol.bedrock.packet.PlayerListPacket;
import org.cloudburstmc.protocol.bedrock.packet.ServerboundDiagnosticsPacket;
import org.cloudburstmc.protocol.bedrock.packet.ServerboundMatchmakingCancelPacket;
import org.cloudburstmc.protocol.bedrock.packet.ServerboundStonecutterSetRecipePacket;
import org.cloudburstmc.protocol.common.util.TypeMap;

/**
 * @author Kaooot
 */
public class Bedrock_v2207 extends Bedrock_v2192 {

    protected static final TypeMap<MemoryCategory> MEMORY_CATEGORY_TYPES = Bedrock_v2192.MEMORY_CATEGORY_TYPES
            .toBuilder()
            .insert(110, MemoryCategory.EXECUTABLE)
            .build();

    public static final BedrockCodec CODEC = Bedrock_v2192.CODEC.toBuilder()
            .protocolVersion(2207)
            .minecraftVersion("1.26.60-beta.21")
            .helper(() -> new BedrockCodecHelper_v2207(ACTOR_DATA, GAME_RULE_TYPES, ITEM_STACK_REQUEST_TYPES, CONTAINER_SLOT_TYPES, PLAYER_ABILITIES, TEXT_PROCESSING_ORIGINS))
            .updateSerializer(AnimatePacket.class, AnimateSerializer_v2207.INSTANCE)
            .updateSerializer(DimensionDataPacket.class, DimensionDataSerializer_v2207.INSTANCE)
            .updateSerializer(ServerboundDiagnosticsPacket.class, new ServerboundDiagnosticsSerializer_v2192(MEMORY_CATEGORY_TYPES))
            .updateSerializer(InventoryTransactionPacket.class, InventoryTransactionSerializer_v2207.INSTANCE)
            .updateSerializer(PlayerListPacket.class, PlayerListSerializer_v2207.INSTANCE)
            .updateSerializer(ClientboundAttributeLayerSyncPacket.class, ClientboundAttributeLayerSyncSerializer_v2207.INSTANCE)
            .registerPacket(ClientboundMatchmakingStatePacket::new, ClientboundMatchmakingStateSerializer_v2207.INSTANCE, 353, PacketRecipient.CLIENT)
            .registerPacket(ServerboundStonecutterSetRecipePacket::new, ServerboundStonecutterSetRecipeSerializer_v2207.INSTANCE, 354, PacketRecipient.SERVER)
            .registerPacket(ClientboundStonecutterSetRecipePacket::new, ClientboundStonecutterSetRecipeSerializer_v2207.INSTANCE, 355, PacketRecipient.CLIENT)
            .registerPacket(ServerboundMatchmakingCancelPacket::new, ServerboundMatchmakingCancelSerializer_v2207.INSTANCE, 356, PacketRecipient.SERVER)
            .build();
}