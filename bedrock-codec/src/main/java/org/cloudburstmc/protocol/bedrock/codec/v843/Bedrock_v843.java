package org.cloudburstmc.protocol.bedrock.codec.v843;

import org.cloudburstmc.protocol.bedrock.codec.ActorDataTypeMap;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodec;
import org.cloudburstmc.protocol.bedrock.codec.v291.serializer.LevelEventSerializer_v291;
import org.cloudburstmc.protocol.bedrock.codec.v361.serializer.LevelEventGenericSerializer_v361;
import org.cloudburstmc.protocol.bedrock.codec.v776.BedrockCodecHelper_v776;
import org.cloudburstmc.protocol.bedrock.codec.v786.serializer.LevelSoundEventSerializer_v786;
import org.cloudburstmc.protocol.bedrock.codec.v827.Bedrock_v827;
import org.cloudburstmc.protocol.bedrock.codec.v843.serializer.BiomeDefinitionListSerializer_v843;
import org.cloudburstmc.protocol.bedrock.codec.v843.serializer.GameRulesChangedSerializer_v843;
import org.cloudburstmc.protocol.bedrock.codec.v843.serializer.PlayerArmorDamageSerializer_v843;
import org.cloudburstmc.protocol.bedrock.data.LevelEventType;
import org.cloudburstmc.protocol.bedrock.data.ParticleType;
import org.cloudburstmc.protocol.bedrock.data.SoundEvent;
import org.cloudburstmc.protocol.bedrock.data.actor.ActorDataTypes;
import org.cloudburstmc.protocol.bedrock.data.actor.ActorFlags;
import org.cloudburstmc.protocol.bedrock.packet.*;
import org.cloudburstmc.protocol.bedrock.transformer.FlagTransformer;
import org.cloudburstmc.protocol.common.util.TypeMap;

/**
 * @author Kaooot
 */
public class Bedrock_v843 extends Bedrock_v827 {

    // TODO Add ServerboundPackSettingChangeEventPacket

    protected static final TypeMap<SoundEvent> SOUND_EVENTS = Bedrock_v827.SOUND_EVENTS
            .toBuilder()
            .replace(563, SoundEvent.PLACE_ITEM)
            .insert(564, SoundEvent.SINGLE_ITEM_SWAP)
            .insert(565, SoundEvent.MULTI_ITEM_SWAP)
            .insert(566, SoundEvent.UNDEFINED)
            .build();

    protected static final TypeMap<ActorFlags> ACTOR_FLAGS = Bedrock_v827.ACTOR_FLAGS
            .toBuilder()
            .insert(125, ActorFlags.CAN_USE_VERTICAL_MOVEMENT_ACTION)
            .build();

    protected static final ActorDataTypeMap ACTOR_DATA = Bedrock_v827.ACTOR_DATA
            .toBuilder()
            .update(ActorDataTypes.FLAGS, new FlagTransformer(ACTOR_FLAGS, 0))
            .update(ActorDataTypes.FLAGS_2, new FlagTransformer(ACTOR_FLAGS, 1))
            .build();

    protected static final TypeMap<ParticleType> PARTICLE_TYPES = Bedrock_v827.PARTICLE_TYPES.toBuilder()
            .insert(98, ParticleType.GREEN_FLAME)
            .build();

    protected static final TypeMap<LevelEventType> LEVEL_EVENTS = Bedrock_v827.LEVEL_EVENTS.toBuilder()
            .insert(LEVEL_EVENT_PARTICLE_TYPE, PARTICLE_TYPES)
            .build();

    public static final BedrockCodec CODEC = Bedrock_v827.CODEC.toBuilder()
            .raknetProtocolVersion(11)
            .protocolVersion(843)
            .minecraftVersion("1.21.110")
            .helper(() -> new BedrockCodecHelper_v776(ACTOR_DATA, GAME_RULE_TYPES, ITEM_STACK_REQUEST_TYPES, CONTAINER_SLOT_TYPES, PLAYER_ABILITIES, TEXT_PROCESSING_ORIGINS))
            .updateSerializer(BiomeDefinitionListPacket.class, BiomeDefinitionListSerializer_v843.INSTANCE)
            .updateSerializer(GameRulesChangedPacket.class, new GameRulesChangedSerializer_v843(GAME_RULE_TYPES))
            .updateSerializer(LevelEventPacket.class, new LevelEventSerializer_v291(LEVEL_EVENTS))
            .updateSerializer(LevelEventGenericPacket.class, new LevelEventGenericSerializer_v361(LEVEL_EVENTS))
            .updateSerializer(LevelSoundEventPacket.class, new LevelSoundEventSerializer_v786(SOUND_EVENTS))
            .updateSerializer(PlayerArmorDamagePacket.class, PlayerArmorDamageSerializer_v843.INSTANCE)
            .build();
}