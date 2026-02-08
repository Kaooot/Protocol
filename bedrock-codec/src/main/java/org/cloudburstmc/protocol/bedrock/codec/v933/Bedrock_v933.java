package org.cloudburstmc.protocol.bedrock.codec.v933;

import org.cloudburstmc.protocol.bedrock.codec.BedrockCodec;
import org.cloudburstmc.protocol.bedrock.codec.v924.Bedrock_v924;
import org.cloudburstmc.protocol.bedrock.codec.v933.serializer.*;
import org.cloudburstmc.protocol.bedrock.data.PacketRecipient;
import org.cloudburstmc.protocol.bedrock.packet.*;

/**
 * @author Kaooot
 */
public class Bedrock_v933 extends Bedrock_v924 {

    public static final BedrockCodec CODEC = Bedrock_v924.CODEC.toBuilder()
            .raknetProtocolVersion(11)
            .protocolVersion(933)
            .minecraftVersion("1.26.10")
            .helper(() -> new BedrockCodecHelper_v933(ACTOR_DATA, GAME_RULE_TYPES, ITEM_STACK_REQUEST_TYPES, CONTAINER_SLOT_TYPES, PLAYER_ABILITIES, TEXT_PROCESSING_ORIGINS))
            .deregisterPacket(ClientboundDataDrivenUICloseAllScreensPacket.class)
            .updateSerializer(ClientboundDataDrivenUIShowScreenPacket.class, ClientboundDataDrivenUIShowScreenSerializer_v933.INSTANCE)
            .updateSerializer(PlayerAuthInputPacket.class, PlayerAuthInputSerializer_v933.INSTANCE)
            .updateSerializer(VoxelShapesPacket.class, VoxelShapesSerializer_v933.INSTANCE)
            .registerPacket(ClientboundDataDrivenUICloseScreenPacket::new, ClientboundDataDrivenUICloseScreenSerializer_v933.INSTANCE, 334, PacketRecipient.CLIENT)
            .registerPacket(ResourcePacksReadyForValidationPacket::new, ResourcePacksReadyForValidationSerializer_v933.INSTANCE, 340, PacketRecipient.SERVER)
            .registerPacket(LocatorBarPacket::new, LocatorBarSerializer_v933.INSTANCE, 341, PacketRecipient.CLIENT)
            .registerPacket(PartyChangedPacket::new, PartyChangedSerializer_v933.INSTANCE, 342, PacketRecipient.CLIENT)
            .registerPacket(ServerboundDataDrivenScreenClosedPacket::new, ServerboundDataDrivenScreenClosedSerializer_v933.INSTANCE, 343, PacketRecipient.CLIENT)
            .build();
}