package org.cloudburstmc.protocol.bedrock.codec.v527;

import org.cloudburstmc.protocol.bedrock.data.world.event.ParticleType;

import org.cloudburstmc.protocol.bedrock.data.world.event.LevelEventType;
import org.cloudburstmc.protocol.bedrock.data.world.event.LevelEvent;

import org.cloudburstmc.protocol.bedrock.data.sound.LevelSoundEvent;

import org.cloudburstmc.protocol.bedrock.codec.BedrockCodec;
import org.cloudburstmc.protocol.bedrock.codec.ActorDataTypeMap;
import org.cloudburstmc.protocol.bedrock.codec.v291.serializer.LevelEventSerializer_v291;
import org.cloudburstmc.protocol.bedrock.codec.v291.serializer.LevelSoundEvent1Serializer_v291;
import org.cloudburstmc.protocol.bedrock.codec.v313.serializer.LevelSoundEvent2Serializer_v313;
import org.cloudburstmc.protocol.bedrock.codec.v332.serializer.LevelSoundEventSerializer_v332;
import org.cloudburstmc.protocol.bedrock.codec.v361.serializer.LevelEventGenericSerializer_v361;
import org.cloudburstmc.protocol.bedrock.codec.v448.serializer.AvailableCommandsSerializer_v448;
import org.cloudburstmc.protocol.bedrock.codec.v503.BedrockCodecHelper_v503;
import org.cloudburstmc.protocol.bedrock.codec.v503.Bedrock_v503;
import org.cloudburstmc.protocol.bedrock.codec.v527.serializer.*;
import org.cloudburstmc.protocol.bedrock.data.command.CommandParam;
import org.cloudburstmc.protocol.bedrock.data.actor.ActorDataFormat;
import org.cloudburstmc.protocol.bedrock.data.actor.ActorDataTypes;
import org.cloudburstmc.protocol.bedrock.data.actor.ActorFlags;
import org.cloudburstmc.protocol.bedrock.data.PacketRecipient;
import org.cloudburstmc.protocol.bedrock.packet.*;
import org.cloudburstmc.protocol.bedrock.transformer.BooleanTransformer;
import org.cloudburstmc.protocol.bedrock.transformer.FlagTransformer;
import org.cloudburstmc.protocol.bedrock.transformer.TypeMapTransformer;
import org.cloudburstmc.protocol.common.util.TypeMap;

public class Bedrock_v527 extends Bedrock_v503 {

    protected static final TypeMap<ActorFlags> ENTITY_FLAGS = Bedrock_v503.ENTITY_FLAGS.toBuilder()
            .insert(106, ActorFlags.SONIC_BOOM)
            .build();

    protected static final TypeMap<ParticleType> PARTICLE_TYPES = Bedrock_v503.PARTICLE_TYPES.toBuilder()
            .insert(84, ParticleType.SONIC_EXPLOSION)
            .build();

    protected static final ActorDataTypeMap ENTITY_DATA = Bedrock_v503.ENTITY_DATA.toBuilder()
            .update(ActorDataTypes.FLAGS, new FlagTransformer(ENTITY_FLAGS, 0))
            .update(ActorDataTypes.FLAGS_2, new FlagTransformer(ENTITY_FLAGS, 1))
            .update(ActorDataTypes.AREA_EFFECT_CLOUD_PARTICLE, new TypeMapTransformer<>(PARTICLE_TYPES))
            .insert(ActorDataTypes.PLAYER_LAST_DEATH_POS, 128, ActorDataFormat.VECTOR3I)
            .insert(ActorDataTypes.PLAYER_LAST_DEATH_DIMENSION, 129, ActorDataFormat.INT)
            .insert(ActorDataTypes.PLAYER_HAS_DIED, 130, ActorDataFormat.BYTE, BooleanTransformer.INSTANCE)
            .build();

    protected static final TypeMap<CommandParam> COMMAND_PARAMS = Bedrock_v503.COMMAND_PARAMS.toBuilder()
            .shift(7, 1)
            .insert(7, CommandParam.COMPARE_OPERATOR)
            .insert(23, CommandParam.INTEGER_RANGE)
            .build();

    protected static final TypeMap<LevelEventType> LEVEL_EVENTS = Bedrock_v503.LEVEL_EVENTS.toBuilder()
            .insert(LEVEL_EVENT_PARTICLE + 39, LevelEvent.SONIC_EXPLOSION)
            .insert(LEVEL_EVENT_PARTICLE_TYPE, PARTICLE_TYPES)
            .build();

    protected static final TypeMap<LevelSoundEvent> SOUND_EVENTS = Bedrock_v503.SOUND_EVENTS.toBuilder()
            .remove(423)
            .insert(426, LevelSoundEvent.IMITATE_WARDEN)
            .insert(427, LevelSoundEvent.LISTENING_ANGRY)
            .insert(428, LevelSoundEvent.ITEM_GIVEN)
            .insert(429, LevelSoundEvent.ITEM_TAKEN)
            .insert(430, LevelSoundEvent.DISAPPEARED)
            .insert(431, LevelSoundEvent.REAPPEARED)
            .insert(433, LevelSoundEvent.FROGSPAWN_HATCHED)
            .insert(434, LevelSoundEvent.LAY_SPAWN)
            .insert(435, LevelSoundEvent.FROGSPAWN_BREAK)
            .insert(436, LevelSoundEvent.SONIC_BOOM)
            .insert(437, LevelSoundEvent.SONIC_CHARGE)
            .insert(438, LevelSoundEvent.ITEM_THROWN)
            .insert(439, LevelSoundEvent.RECORD_5)
            .insert(440, LevelSoundEvent.CONVERT_TO_FROG)
            .insert(441, LevelSoundEvent.UNDEFINED)
            .build();

    public static final BedrockCodec CODEC = Bedrock_v503.CODEC.toBuilder()
            .protocolVersion(527)
            .minecraftVersion("1.19.0")
            .helper(() -> new BedrockCodecHelper_v503(ENTITY_DATA, GAME_RULE_TYPES, ITEM_STACK_REQUEST_TYPES, CONTAINER_SLOT_TYPES))
            .updateSerializer(StartGamePacket.class, new StartGameSerializer_v527())
            .updateSerializer(AvailableCommandsPacket.class, new AvailableCommandsSerializer_v448(COMMAND_PARAMS))
            .updateSerializer(LevelEventPacket.class, new LevelEventSerializer_v291(LEVEL_EVENTS))
            .updateSerializer(LevelEventGenericPacket.class, new LevelEventGenericSerializer_v361(LEVEL_EVENTS))
            .updateSerializer(LevelSoundEvent1Packet.class, new LevelSoundEvent1Serializer_v291(SOUND_EVENTS))
            .updateSerializer(LevelSoundEvent2Packet.class, new LevelSoundEvent2Serializer_v313(SOUND_EVENTS))
            .updateSerializer(LevelSoundEventPacket.class, new LevelSoundEventSerializer_v332(SOUND_EVENTS))
            .updateSerializer(PlayerActionPacket.class, new PlayerActionSerializer_v527())
            .updateSerializer(PlayerAuthInputPacket.class, new PlayerAuthInputSerializer_v527())
            .registerPacket(LessonProgressPacket::new, new LessonProgressSerializer_v527(), 183, PacketRecipient.CLIENT)
            .registerPacket(RequestAbilityPacket::new, new RequestAbilitySerializer_v527(), 184, PacketRecipient.SERVER)
            .registerPacket(RequestPermissionsPacket::new, new RequestPermissionsSerializer_v527(), 185, PacketRecipient.SERVER)
            .registerPacket(ToastRequestPacket::new, new ToastRequestSerializer_v527(), 186, PacketRecipient.CLIENT)
            .build();
}
