package org.cloudburstmc.protocol.bedrock.codec.v800;

import org.cloudburstmc.protocol.bedrock.codec.BedrockCodec;
import org.cloudburstmc.protocol.bedrock.codec.ActorDataTypeMap;
import org.cloudburstmc.protocol.bedrock.codec.v776.BedrockCodecHelper_v776;
import org.cloudburstmc.protocol.bedrock.codec.v786.Bedrock_v786;
import org.cloudburstmc.protocol.bedrock.codec.v786.serializer.LevelSoundEventSerializer_v786;
import org.cloudburstmc.protocol.bedrock.codec.v800.serializer.*;
import org.cloudburstmc.protocol.bedrock.data.PacketRecipient;
import org.cloudburstmc.protocol.bedrock.data.sound.LevelSoundEvent;
import org.cloudburstmc.protocol.bedrock.data.actor.ActorDataFormat;
import org.cloudburstmc.protocol.bedrock.data.actor.ActorDataTypes;
import org.cloudburstmc.protocol.bedrock.data.actor.ActorFlags;
import org.cloudburstmc.protocol.bedrock.packet.*;
import org.cloudburstmc.protocol.bedrock.transformer.FlagTransformer;
import org.cloudburstmc.protocol.common.util.TypeMap;

public class Bedrock_v800 extends Bedrock_v786 {

    protected static final TypeMap<ActorFlags> ENTITY_FLAGS = Bedrock_v786.ENTITY_FLAGS
            .toBuilder()
            .insert(123, ActorFlags.DOES_SERVER_AUTH_ONLY_DISMOUNT)
            .build();

    protected static final ActorDataTypeMap ENTITY_DATA = Bedrock_v786.ENTITY_DATA
            .toBuilder()
            .update(ActorDataTypes.FLAGS, new FlagTransformer(ENTITY_FLAGS, 0))
            .update(ActorDataTypes.FLAGS_2, new FlagTransformer(ENTITY_FLAGS, 1))
            .insert(ActorDataTypes.SEAT_THIRD_PERSON_CAMERA_RADIUS, 134, ActorDataFormat.FLOAT)
            .insert(ActorDataTypes.SEAT_CAMERA_RELAX_DISTANCE_SMOOTHING, 135, ActorDataFormat.FLOAT)
            .build();

    protected static final TypeMap<LevelSoundEvent> SOUND_EVENTS = Bedrock_v786.SOUND_EVENTS
            .toBuilder()
            .replace(546, LevelSoundEvent.IMITATE_PHANTOM)
            .insert(547, LevelSoundEvent.IMITATE_ZOGLIN)
            .insert(548, LevelSoundEvent.IMITATE_GUARDIAN)
            .insert(549, LevelSoundEvent.IMITATE_RAVAGER)
            .insert(550, LevelSoundEvent.IMITATE_PILLAGER)
            .insert(551, LevelSoundEvent.PLACE_IN_WATER)
            .insert(552, LevelSoundEvent.STATE_CHANGE)
            .insert(553, LevelSoundEvent.IMITATE_HAPPY_GHAST)
            .insert(554, LevelSoundEvent.UNEQUIP_GENERIC)
            .insert(555, LevelSoundEvent.UNDEFINED)
            .build();

    @SuppressWarnings("deprecation")
    public static final BedrockCodec CODEC = Bedrock_v786.CODEC.toBuilder()
            .raknetProtocolVersion(11)
            .protocolVersion(800)
            .minecraftVersion("1.21.80")
            .helper(() -> new BedrockCodecHelper_v776(ENTITY_DATA, GAME_RULE_TYPES, ITEM_STACK_REQUEST_TYPES, CONTAINER_SLOT_TYPES, PLAYER_ABILITIES, TEXT_PROCESSING_ORIGINS))
            .updateSerializer(LevelSoundEventPacket.class, new LevelSoundEventSerializer_v786(SOUND_EVENTS))
            .updateSerializer(BiomeDefinitionListPacket.class, BiomeDefinitionListSerializer_v800.INSTANCE)
            .updateSerializer(CameraPresetsPacket.class, CameraPresetsSerializer_v800.INSTANCE)
            .updateSerializer(PlayerListPacket.class, PlayerListSerializer_v800.INSTANCE)
            .updateSerializer(CameraAimAssistPresetsPacket.class, CameraAimAssistPresetsSerializer_v800.INSTANCE)
            .registerPacket(PlayerLocationPacket::new, PlayerLocationSerializer_v800.INSTANCE, 326, PacketRecipient.BOTH)
            .registerPacket(ClientboundControlSchemeSetPacket::new, ClientboundControlSchemeSetSerializer_v800.INSTANCE, 327, PacketRecipient.CLIENT)
            .deregisterPacket(CompressedBiomeDefinitionListPacket.class)
            .deregisterPacket(PlayerInputPacket.class)
            .deregisterPacket(RiderJumpPacket.class)
            .build();
}
