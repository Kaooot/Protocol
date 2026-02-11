package org.cloudburstmc.protocol.bedrock.codec.v935;

import org.cloudburstmc.protocol.bedrock.codec.BedrockCodec;
import org.cloudburstmc.protocol.bedrock.codec.v924.Bedrock_v924;
import org.cloudburstmc.protocol.bedrock.codec.v935.serializer.*;
import org.cloudburstmc.protocol.bedrock.data.PacketRecipient;
import org.cloudburstmc.protocol.bedrock.data.inventory.ContainerEnumName;
import org.cloudburstmc.protocol.bedrock.packet.*;
import org.cloudburstmc.protocol.common.util.TypeMap;

/**
 * @author Kaooot
 */
public class Bedrock_v935 extends Bedrock_v924 {

    protected static final TypeMap<ContainerEnumName> CONTAINER_SLOT_TYPES = Bedrock_v924.CONTAINER_SLOT_TYPES.toBuilder()
            .shift(18, 3)
            .insert(18, ContainerEnumName.RECIPE_FOOD_CONTAINER)
            .insert(19, ContainerEnumName.RECIPE_BLOCKS_CONTAINER)
            .insert(20, ContainerEnumName.RECIPE_FURNACE_ITEMS_CONTAINER)
            .build();

    public static final BedrockCodec CODEC = Bedrock_v924.CODEC.toBuilder()
            .raknetProtocolVersion(11)
            .protocolVersion(935)
            .minecraftVersion("1.26.10")
            .helper(() -> new BedrockCodecHelper_v935(ACTOR_DATA, GAME_RULE_TYPES, ITEM_STACK_REQUEST_TYPES, CONTAINER_SLOT_TYPES, PLAYER_ABILITIES, TEXT_PROCESSING_ORIGINS))
            .deregisterPacket(ClientboundDataDrivenUICloseAllScreensPacket.class)
            .updateSerializer(ClientboundDataDrivenUIShowScreenPacket.class, ClientboundDataDrivenUIShowScreenSerializer_v935.INSTANCE)
            .updateSerializer(EditorNetworkPacket.class, EditorNetworkSerializer_v935.INSTANCE)
            .updateSerializer(PlayerAuthInputPacket.class, PlayerAuthInputSerializer_v935.INSTANCE)
            .updateSerializer(StartGamePacket.class, StartGameSerializer_v935.INSTANCE)
            .updateSerializer(VoxelShapesPacket.class, VoxelShapesSerializer_v935.INSTANCE)
            .registerPacket(ClientboundDataDrivenUICloseScreenPacket::new, ClientboundDataDrivenUICloseScreenSerializer_v935.INSTANCE, 334, PacketRecipient.CLIENT)
            .registerPacket(ResourcePacksReadyForValidationPacket::new, ResourcePacksReadyForValidationSerializer_v935.INSTANCE, 340, PacketRecipient.SERVER)
            .registerPacket(LocatorBarPacket::new, LocatorBarSerializer_v935.INSTANCE, 341, PacketRecipient.CLIENT)
            .registerPacket(PartyChangedPacket::new, PartyChangedSerializer_v935.INSTANCE, 342, PacketRecipient.CLIENT)
            .registerPacket(ServerboundDataDrivenScreenClosedPacket::new, ServerboundDataDrivenScreenClosedSerializer_v935.INSTANCE, 343, PacketRecipient.CLIENT)
            .build();
}