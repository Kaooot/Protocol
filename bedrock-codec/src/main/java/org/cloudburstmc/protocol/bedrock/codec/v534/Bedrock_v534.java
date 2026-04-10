package org.cloudburstmc.protocol.bedrock.codec.v534;

import org.cloudburstmc.protocol.bedrock.codec.ActorDataTypeMap;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodec;
import org.cloudburstmc.protocol.bedrock.codec.v291.serializer.ActorEventSerializer_v291;
import org.cloudburstmc.protocol.bedrock.codec.v291.serializer.LevelSoundEvent1Serializer_v291;
import org.cloudburstmc.protocol.bedrock.codec.v313.serializer.LevelSoundEvent2Serializer_v313;
import org.cloudburstmc.protocol.bedrock.codec.v332.serializer.LevelSoundEventSerializer_v332;
import org.cloudburstmc.protocol.bedrock.codec.v527.Bedrock_v527;
import org.cloudburstmc.protocol.bedrock.codec.v534.serializer.*;
import org.cloudburstmc.protocol.bedrock.data.AbilitiesIndex;
import org.cloudburstmc.protocol.bedrock.data.PacketRecipient;
import org.cloudburstmc.protocol.bedrock.data.SoundEvent;
import org.cloudburstmc.protocol.bedrock.data.actor.ActorDataTypes;
import org.cloudburstmc.protocol.bedrock.data.actor.ActorEvent;
import org.cloudburstmc.protocol.bedrock.packet.*;
import org.cloudburstmc.protocol.bedrock.transformer.TypeMapTransformer;
import org.cloudburstmc.protocol.common.util.TypeMap;

public class Bedrock_v534 extends Bedrock_v527 {

    protected static final TypeMap<ActorEvent> ACTOR_EVENTS = Bedrock_v527.ACTOR_EVENTS.toBuilder()
            .insert(78, ActorEvent.DRINK_MILK)
            .build();

    protected static final TypeMap<SoundEvent> SOUND_EVENTS = Bedrock_v527.SOUND_EVENTS.toBuilder()
            .insert(432, SoundEvent.MILK_DRINK)
            .replace(441, SoundEvent.RECORD_PLAYING)
            .insert(442, SoundEvent.UNDEFINED)
            .build();

    protected static final TypeMap<AbilitiesIndex> PLAYER_ABILITIES = TypeMap.builder(AbilitiesIndex.class)
            .insert(-1, AbilitiesIndex.INVALID)
            .insert(0, AbilitiesIndex.BUILD)
            .insert(1, AbilitiesIndex.MINE)
            .insert(2, AbilitiesIndex.DOORS_AND_SWITCHES)
            .insert(3, AbilitiesIndex.OPEN_CONTAINERS)
            .insert(4, AbilitiesIndex.ATTACK_PLAYERS)
            .insert(5, AbilitiesIndex.ATTACK_MOBS)
            .insert(6, AbilitiesIndex.OPERATOR_COMMANDS)
            .insert(7, AbilitiesIndex.TELEPORT)
            .insert(8, AbilitiesIndex.INVULNERABLE)
            .insert(9, AbilitiesIndex.FLYING)
            .insert(10, AbilitiesIndex.MAY_FLY)
            .insert(11, AbilitiesIndex.INSTABUILD)
            .insert(12, AbilitiesIndex.LIGHTNING)
            .insert(13, AbilitiesIndex.FLY_SPEED)
            .insert(14, AbilitiesIndex.WALK_SPEED)
            .insert(15, AbilitiesIndex.MUTED)
            .insert(16, AbilitiesIndex.WORLD_BUILDER)
            .insert(17, AbilitiesIndex.NO_CLIP)
            .build();

    protected static final ActorDataTypeMap ACTOR_DATA = Bedrock_v527.ACTOR_DATA.toBuilder()
            .update(ActorDataTypes.HEARTBEAT_SOUND_EVENT, new TypeMapTransformer<>(SOUND_EVENTS))
            .build();

    public static final BedrockCodec CODEC = Bedrock_v527.CODEC.toBuilder()
            .protocolVersion(534)
            .minecraftVersion("1.19.10")
            .helper(() -> new BedrockCodecHelper_v534(ACTOR_DATA, GAME_RULE_TYPES, ITEM_STACK_REQUEST_TYPES, CONTAINER_SLOT_TYPES, PLAYER_ABILITIES))
            .updateSerializer(StartGamePacket.class, StartGameSerializer_v534.INSTANCE)
            .updateSerializer(AddActorPacket.class, AddActorSerializer_v534.INSTANCE)
            .updateSerializer(AddPlayerPacket.class, AddPlayerSerializer_v534.INSTANCE)
            .updateSerializer(ActorEventPacket.class, new ActorEventSerializer_v291(ACTOR_EVENTS))
            .updateSerializer(LevelSoundEvent1Packet.class, new LevelSoundEvent1Serializer_v291(SOUND_EVENTS))
            .updateSerializer(LevelSoundEvent2Packet.class, new LevelSoundEvent2Serializer_v313(SOUND_EVENTS))
            .updateSerializer(LevelSoundEventPacket.class, new LevelSoundEventSerializer_v332(SOUND_EVENTS))
            .registerPacket(UpdateAbilitiesPacket::new, UpdateAbilitiesSerializer_v534.INSTANCE, 187, PacketRecipient.CLIENT)
            .registerPacket(UpdateAdventureSettingsPacket::new, UpdateAdventureSettingsSerializer_v534.INSTANCE, 188, PacketRecipient.CLIENT)
            .registerPacket(DeathInfoPacket::new, DeathInfoSerializer_v534.INSTANCE, 189, PacketRecipient.CLIENT)
            .registerPacket(EditorNetworkPacket::new, EditorNetworkSerializer_v534.INSTANCE, 190, PacketRecipient.BOTH)
            .build();
}
