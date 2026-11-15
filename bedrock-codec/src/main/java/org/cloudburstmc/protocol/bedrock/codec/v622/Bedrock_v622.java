package org.cloudburstmc.protocol.bedrock.codec.v622;

import org.cloudburstmc.protocol.bedrock.codec.ActorDataTypeMap;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodec;
import org.cloudburstmc.protocol.bedrock.codec.v291.serializer.LevelSoundEvent1Serializer_v291;
import org.cloudburstmc.protocol.bedrock.codec.v313.serializer.LevelSoundEvent2Serializer_v313;
import org.cloudburstmc.protocol.bedrock.codec.v332.serializer.LevelSoundEventSerializer_v332;
import org.cloudburstmc.protocol.bedrock.codec.v527.serializer.PlayerActionSerializer_v527;
import org.cloudburstmc.protocol.bedrock.codec.v575.BedrockCodecHelper_v575;
import org.cloudburstmc.protocol.bedrock.codec.v575.serializer.PlayerAuthInputSerializer_v575;
import org.cloudburstmc.protocol.bedrock.codec.v618.Bedrock_v618;
import org.cloudburstmc.protocol.bedrock.codec.v622.serializer.DisconnectSerializer_v622;
import org.cloudburstmc.protocol.bedrock.data.actor.ActorDataTypes;
import org.cloudburstmc.protocol.bedrock.data.actor.ActorFlags;
import org.cloudburstmc.protocol.bedrock.data.player.input.PlayerActionType;
import org.cloudburstmc.protocol.bedrock.data.sound.LevelSoundEvent;
import org.cloudburstmc.protocol.bedrock.packet.*;
import org.cloudburstmc.protocol.bedrock.transformer.FlagTransformer;
import org.cloudburstmc.protocol.common.util.TypeMap;

public class Bedrock_v622 extends Bedrock_v618 {

    protected static final TypeMap<ActorFlags> ACTOR_FLAGS = Bedrock_v618.ACTOR_FLAGS
            .toBuilder()
            .insert(115, ActorFlags.TIMER_FLAG_1)
            .insert(116, ActorFlags.TIMER_FLAG_2)
            .insert(117, ActorFlags.TIMER_FLAG_3)
            .build();

    protected static final ActorDataTypeMap ACTOR_DATA = Bedrock_v618.ACTOR_DATA
            .toBuilder()
            .update(ActorDataTypes.FLAGS, new FlagTransformer(ACTOR_FLAGS, 0))
            .update(ActorDataTypes.FLAGS_2, new FlagTransformer(ACTOR_FLAGS, 1))
            .build();

    protected static final TypeMap<LevelSoundEvent> SOUND_EVENTS = Bedrock_v618.SOUND_EVENTS
            .toBuilder()
            .replace(477, LevelSoundEvent.BOTTLE_FILL)
            .insert(478, LevelSoundEvent.BOTTLE_EMPTY)
            .insert(479, LevelSoundEvent.UNDEFINED)
            .build();

    protected static final TypeMap<PlayerActionType> PLAYER_ACTION_TYPES = Bedrock_v618.PLAYER_ACTION_TYPES.toBuilder()
            .replace(36, PlayerActionType.DEPRECATED_CLIENT_ACK_SERVER_DATA)
            .insert(37, PlayerActionType.COUNT)
            .build();

    public static final BedrockCodec CODEC = Bedrock_v618.CODEC.toBuilder()
            .raknetProtocolVersion(11)
            .protocolVersion(622)
            .minecraftVersion("1.20.40")
            .helper(() -> new BedrockCodecHelper_v575(ACTOR_DATA, GAME_RULE_TYPES, ITEM_STACK_REQUEST_TYPES, CONTAINER_ENUM_NAMES, PLAYER_ABILITIES, TEXT_PROCESSING_ORIGINS))
            .updateSerializer(DisconnectPacket.class, DisconnectSerializer_v622.INSTANCE)
            .updateSerializer(LevelSoundEvent1Packet.class, new LevelSoundEvent1Serializer_v291(SOUND_EVENTS))
            .updateSerializer(LevelSoundEvent2Packet.class, new LevelSoundEvent2Serializer_v313(SOUND_EVENTS))
            .updateSerializer(LevelSoundEventPacket.class, new LevelSoundEventSerializer_v332(SOUND_EVENTS))
            .updateSerializer(PlayerActionPacket.class, new PlayerActionSerializer_v527(PLAYER_ACTION_TYPES))
            .updateSerializer(PlayerAuthInputPacket.class, new PlayerAuthInputSerializer_v575(PLAYER_ACTION_TYPES))
            .build();
}