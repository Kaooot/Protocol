package org.cloudburstmc.protocol.bedrock.codec.v2177;

import org.cloudburstmc.protocol.bedrock.codec.BedrockCodec;
import org.cloudburstmc.protocol.bedrock.codec.v2168.BedrockCodecHelper_v2168;
import org.cloudburstmc.protocol.bedrock.codec.v2168.Bedrock_v2168;
import org.cloudburstmc.protocol.bedrock.codec.v2177.serializer.*;
import org.cloudburstmc.protocol.bedrock.data.PacketRecipient;
import org.cloudburstmc.protocol.bedrock.data.payload.skin.AnimatedTextureType;
import org.cloudburstmc.protocol.bedrock.packet.*;
import org.cloudburstmc.protocol.common.util.TypeMap;

/**
 * @author Kaooot
 */
public class Bedrock_v2177 extends Bedrock_v2168 {

    protected static final TypeMap<AnimatedTextureType> ANIMATED_TEXTURE_TYPES = Bedrock_v2168.ANIMATED_TEXTURE_TYPES
            .toBuilder()
            .shift(0, 1)
            .insert(0, AnimatedTextureType.NONE)
            .build();

    public static final BedrockCodec CODEC = Bedrock_v2168.CODEC.toBuilder()
            .raknetProtocolVersion(11)
            .protocolVersion(2177)
            .minecraftVersion("1.26.50-beta.22")
            .helper(() -> new BedrockCodecHelper_v2168(ACTOR_DATA, GAME_RULE_TYPES, ITEM_STACK_REQUEST_TYPES, CONTAINER_SLOT_TYPES, PLAYER_ABILITIES, TEXT_PROCESSING_ORIGINS, ANIMATED_TEXTURE_TYPES))
            .updateSerializer(CameraPresetsPacket.class, CameraPresetsSerializer_v2177.INSTANCE)
            .updateSerializer(MoveActorDeltaPacket.class, MoveActorDeltaSerializer_v2177.INSTANCE)
            .updateSerializer(PrimitiveShapesPacket.class, PrimitiveShapesSerializer_v2177.INSTANCE)
            .updateSerializer(ServerboundDiagnosticsPacket.class, new ServerboundDiagnosticsSerializer_v2177(MEMORY_CATEGORY_TYPES))
            .registerPacket(SetPlayerFurnaceOptionsPacket::new, SetPlayerFurnaceOptionsSerializer_v2177.INSTANCE, 351, PacketRecipient.BOTH)
            .build();
}