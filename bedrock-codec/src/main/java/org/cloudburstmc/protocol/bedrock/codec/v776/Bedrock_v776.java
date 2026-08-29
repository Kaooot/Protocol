package org.cloudburstmc.protocol.bedrock.codec.v776;

import org.cloudburstmc.protocol.bedrock.codec.BedrockCodec;
import org.cloudburstmc.protocol.bedrock.codec.ActorDataTypeMap;
import org.cloudburstmc.protocol.bedrock.codec.v766.Bedrock_v766;
import org.cloudburstmc.protocol.bedrock.codec.v776.serializer.*;
import org.cloudburstmc.protocol.bedrock.data.*;
import org.cloudburstmc.protocol.bedrock.data.actor.ActorDataFormat;
import org.cloudburstmc.protocol.bedrock.data.actor.ActorDataTypes;
import org.cloudburstmc.protocol.bedrock.data.actor.ActorFlags;
import org.cloudburstmc.protocol.bedrock.packet.*;
import org.cloudburstmc.protocol.bedrock.transformer.FlagTransformer;
import org.cloudburstmc.protocol.common.util.TypeMap;

public class Bedrock_v776 extends Bedrock_v766 {

    protected static final TypeMap<ActorFlags> ACTOR_FLAGS = Bedrock_v766.ACTOR_FLAGS
            .toBuilder()
            .insert(119, ActorFlags.RENDERS_WHEN_INVISIBLE)
            .build();

    protected static final ActorDataTypeMap ACTOR_DATA = Bedrock_v766.ACTOR_DATA
            .toBuilder()
            .insert(ActorDataTypes.FILTERED_NAME, 132, ActorDataFormat.STRING)
            .insert(ActorDataTypes.BED_ENTER_POSITION, 133, ActorDataFormat.VECTOR3F)
            .update(ActorDataTypes.FLAGS, new FlagTransformer(ACTOR_FLAGS, 0))
            .update(ActorDataTypes.FLAGS_2, new FlagTransformer(ACTOR_FLAGS, 1))
            .build();

    protected static final TypeMap<AbilitiesIndex> PLAYER_ABILITIES = Bedrock_v766.PLAYER_ABILITIES
            .toBuilder()
            .insert(19, AbilitiesIndex.VERTICAL_FLY_SPEED)
            .build();

    public static final BedrockCodec CODEC = Bedrock_v766.CODEC.toBuilder()
            .protocolVersion(776)
            .minecraftVersion("1.21.60")
            .helper(() -> new BedrockCodecHelper_v776(ACTOR_DATA, GAME_RULE_TYPES, ITEM_STACK_REQUEST_TYPES, CONTAINER_SLOT_TYPES, PLAYER_ABILITIES, TEXT_PROCESSING_ORIGINS))
            .updateSerializer(BossEventPacket.class, BossEventSerializer_v776.INSTANCE)
            .updateSerializer(CameraAimAssistPresetsPacket.class, CameraAimAssistPresetsSerializer_v776.INSTANCE)
            .updateSerializer(CommandBlockUpdatePacket.class, CommandBlockUpdateSerializer_v776.INSTANCE)
            .updateSerializer(CreativeContentPacket.class, CreativeContentSerializer_v776.INSTANCE)
            .updateSerializer(ItemRegistryPacket.class, ItemRegistrySerializer_v776.INSTANCE)
            .updateSerializer(StartGamePacket.class, StartGameSerializer_v776.INSTANCE)
            .updateSerializer(CameraPresetsPacket.class, CameraPresetsSerializer_v776.INSTANCE)
            .updateSerializer(StructureBlockUpdatePacket.class, StructureBlockUpdateSerializer_v776.INSTANCE)
            .registerPacket(ClientCameraAimAssistPacket::new, ClientCameraAimAssistSerializer_v776.INSTANCE, 321, PacketRecipient.SERVER)
            .registerPacket(ClientMovementPredictionSyncPacket::new, ClientMovementPredictionSyncSerializer_v776.INSTANCE, 322, PacketRecipient.SERVER)
            .build();
}