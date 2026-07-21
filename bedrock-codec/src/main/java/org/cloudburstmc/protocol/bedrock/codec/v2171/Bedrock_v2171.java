package org.cloudburstmc.protocol.bedrock.codec.v2171;

import org.cloudburstmc.protocol.bedrock.codec.BedrockCodec;
import org.cloudburstmc.protocol.bedrock.codec.v2168.BedrockCodecHelper_v2168;
import org.cloudburstmc.protocol.bedrock.codec.v2168.Bedrock_v2168;
import org.cloudburstmc.protocol.bedrock.codec.v2171.serializer.MoveActorDeltaSerializer_v2171;
import org.cloudburstmc.protocol.bedrock.codec.v2171.serializer.PrimitiveShapesSerializer_v2171;
import org.cloudburstmc.protocol.bedrock.codec.v2171.serializer.ServerboundDiagnosticsSerializer_v2171;
import org.cloudburstmc.protocol.bedrock.data.payload.skin.AnimatedTextureType;
import org.cloudburstmc.protocol.bedrock.packet.MoveActorDeltaPacket;
import org.cloudburstmc.protocol.bedrock.packet.PrimitiveShapesPacket;
import org.cloudburstmc.protocol.bedrock.packet.ServerboundDiagnosticsPacket;
import org.cloudburstmc.protocol.common.util.TypeMap;

/**
 * @author Kaooot
 */
public class Bedrock_v2171 extends Bedrock_v2168 {

    protected static final TypeMap<AnimatedTextureType> ANIMATED_TEXTURE_TYPES = Bedrock_v2168.ANIMATED_TEXTURE_TYPES
            .toBuilder()
            .shift(0, 1)
            .insert(0, AnimatedTextureType.NONE)
            .build();

    public static final BedrockCodec CODEC = Bedrock_v2168.CODEC.toBuilder()
            .raknetProtocolVersion(11)
            .protocolVersion(2171)
            .minecraftVersion("1.26.50-beta.20")
            .helper(() -> new BedrockCodecHelper_v2168(ACTOR_DATA, GAME_RULE_TYPES, ITEM_STACK_REQUEST_TYPES, CONTAINER_SLOT_TYPES, PLAYER_ABILITIES, TEXT_PROCESSING_ORIGINS, ANIMATED_TEXTURE_TYPES))
            .updateSerializer(MoveActorDeltaPacket.class, MoveActorDeltaSerializer_v2171.INSTANCE)
            .updateSerializer(PrimitiveShapesPacket.class, PrimitiveShapesSerializer_v2171.INSTANCE)
            .updateSerializer(ServerboundDiagnosticsPacket.class, new ServerboundDiagnosticsSerializer_v2171(MEMORY_CATEGORY_TYPES))
            .build();
}