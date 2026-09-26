package org.cloudburstmc.protocol.bedrock.codec.v818;

import org.cloudburstmc.protocol.bedrock.codec.BedrockCodec;
import org.cloudburstmc.protocol.bedrock.codec.ActorDataTypeMap;
import org.cloudburstmc.protocol.bedrock.codec.v776.BedrockCodecHelper_v776;
import org.cloudburstmc.protocol.bedrock.codec.v786.serializer.LevelSoundEventSerializer_v786;
import org.cloudburstmc.protocol.bedrock.codec.v800.Bedrock_v800;
import org.cloudburstmc.protocol.bedrock.codec.v818.serializer.*;
import org.cloudburstmc.protocol.bedrock.data.PacketRecipient;
import org.cloudburstmc.protocol.bedrock.data.sound.LevelSoundEvent;
import org.cloudburstmc.protocol.bedrock.data.actor.ActorDataTypes;
import org.cloudburstmc.protocol.bedrock.data.actor.ActorFlags;
import org.cloudburstmc.protocol.bedrock.packet.*;
import org.cloudburstmc.protocol.bedrock.transformer.FlagTransformer;
import org.cloudburstmc.protocol.common.util.TypeMap;

public class Bedrock_v818 extends Bedrock_v800 {

    protected static final TypeMap<ActorFlags> ENTITY_FLAGS = Bedrock_v800.ENTITY_FLAGS
            .toBuilder()
            .insert(124, ActorFlags.BODY_ROTATION_ALWAYS_FOLLOWS_HEAD)
            .build();

    protected static final ActorDataTypeMap ENTITY_DATA = Bedrock_v800.ENTITY_DATA
            .toBuilder()
            .update(ActorDataTypes.FLAGS, new FlagTransformer(ENTITY_FLAGS, 0))
            .update(ActorDataTypes.FLAGS_2, new FlagTransformer(ENTITY_FLAGS, 1))
            .build();

    protected static final TypeMap<LevelSoundEvent> SOUND_EVENTS = Bedrock_v800.SOUND_EVENTS
            .toBuilder()
            .replace(555, LevelSoundEvent.RECORD_TEARS)
            .insert(556, LevelSoundEvent.THE_END_LIGHT_FLASH)
            .insert(557, LevelSoundEvent.LEAD_LEASH)
            .insert(558, LevelSoundEvent.LEAD_UNLEASH)
            .insert(559, LevelSoundEvent.LEAD_BREAK)
            .insert(560, LevelSoundEvent.UNSADDLE)
            .insert(561, LevelSoundEvent.UNDEFINED)
            .build();

    public static final BedrockCodec CODEC = Bedrock_v800.CODEC.toBuilder()
            .raknetProtocolVersion(11)
            .protocolVersion(818)
            .minecraftVersion("1.21.90")
            .helper(() -> new BedrockCodecHelper_v776(ENTITY_DATA, GAME_RULE_TYPES, ITEM_STACK_REQUEST_TYPES, CONTAINER_SLOT_TYPES, PLAYER_ABILITIES, TEXT_PROCESSING_ORIGINS))
            .deregisterPacket(SetMovementAuthorityPacket.class)
            .updateSerializer(LoginPacket.class, LoginSerializer_v818.INSTANCE)
            .updateSerializer(SubClientLoginPacket.class, SubClientLoginSerializer_v818.INSTANCE)
            .updateSerializer(LevelSoundEventPacket.class, new LevelSoundEventSerializer_v786(SOUND_EVENTS))
            .updateSerializer(ResourcePacksInfoPacket.class, ResourcePacksInfoSerializer_v818.INSTANCE)
            .updateSerializer(SubChunkPacket.class, SubChunkSerializer_v818.INSTANCE)
            .updateSerializer(CameraPresetsPacket.class, CameraPresetsSerializer_v818.INSTANCE)
            .updateSerializer(CameraInstructionPacket.class, CameraInstructionSerializer_v818.INSTANCE)
            .updateSerializer(StartGamePacket.class, StartGameSerializer_v818.INSTANCE)
            .registerPacket(PrimitiveShapesPacket::new, PrimitiveShapesSerializer_v818.INSTANCE, 328, PacketRecipient.CLIENT)
            .build();
}
