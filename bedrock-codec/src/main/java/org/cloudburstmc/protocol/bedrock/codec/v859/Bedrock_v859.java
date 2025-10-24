package org.cloudburstmc.protocol.bedrock.codec.v859;

import org.cloudburstmc.protocol.bedrock.codec.BedrockCodec;
import org.cloudburstmc.protocol.bedrock.codec.v291.serializer.ActorEventSerializer_v291;
import org.cloudburstmc.protocol.bedrock.codec.v527.Bedrock_v527;
import org.cloudburstmc.protocol.bedrock.codec.v776.BedrockCodecHelper_v776;
import org.cloudburstmc.protocol.bedrock.codec.v844.Bedrock_v844;
import org.cloudburstmc.protocol.bedrock.codec.v859.serializer.*;
import org.cloudburstmc.protocol.bedrock.data.PacketRecipient;
import org.cloudburstmc.protocol.bedrock.data.actor.ActorEvent;
import org.cloudburstmc.protocol.bedrock.packet.*;
import org.cloudburstmc.protocol.common.util.TypeMap;

/**
 * @author Kaooot
 */
public class Bedrock_v859 extends Bedrock_v844 {

    protected static final TypeMap<ActorEvent> ACTOR_EVENTS = Bedrock_v527.ACTOR_EVENTS.toBuilder()
            .insert(79, ActorEvent.SHAKE_WETNESS_STOP)
            .build();

    public static final BedrockCodec CODEC = Bedrock_v844.CODEC.toBuilder()
            .raknetProtocolVersion(11)
            .protocolVersion(859)
            .minecraftVersion("1.21.120")
            .helper(() -> new BedrockCodecHelper_v776(ACTOR_DATA, GAME_RULE_TYPES, ITEM_STACK_REQUEST_TYPES, CONTAINER_SLOT_TYPES, PLAYER_ABILITIES, TEXT_PROCESSING_ORIGINS))
            .updateSerializer(ActorEventPacket.class, new ActorEventSerializer_v291(ACTOR_EVENTS))
            .updateSerializer(AnimatePacket.class, AnimateSerializer_v859.INSTANCE)
            .updateSerializer(BiomeDefinitionListPacket.class, BiomeDefinitionListSerializer_v859.INSTANCE)
            .updateSerializer(CameraInstructionPacket.class, CameraInstructionSerializer_v859.INSTANCE)
            .updateSerializer(ShowStoreOfferPacket.class, ShowStoreOfferSerializer_v859.INSTANCE)
            .registerPacket(GraphicsOverrideParameterPacket::new, GraphicsParameterOverrideSerializer_v859.INSTANCE, 331, PacketRecipient.CLIENT)
            .build();
}