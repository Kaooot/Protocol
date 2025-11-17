package org.cloudburstmc.protocol.bedrock.codec.v560;

import org.cloudburstmc.protocol.bedrock.codec.BedrockCodec;
import org.cloudburstmc.protocol.bedrock.codec.ActorDataTypeMap;
import org.cloudburstmc.protocol.bedrock.codec.v291.serializer.LevelSoundEvent1Serializer_v291;
import org.cloudburstmc.protocol.bedrock.codec.v313.serializer.LevelSoundEvent2Serializer_v313;
import org.cloudburstmc.protocol.bedrock.codec.v332.serializer.LevelSoundEventSerializer_v332;
import org.cloudburstmc.protocol.bedrock.codec.v557.BedrockCodecHelper_v557;
import org.cloudburstmc.protocol.bedrock.codec.v557.Bedrock_v557;
import org.cloudburstmc.protocol.bedrock.codec.v560.serializer.UpdateClientInputLocksSerializer_v560;
import org.cloudburstmc.protocol.bedrock.data.PacketRecipient;
import org.cloudburstmc.protocol.bedrock.data.SoundEvent;
import org.cloudburstmc.protocol.bedrock.data.actor.ActorDataTypes;
import org.cloudburstmc.protocol.bedrock.data.actor.ActorFlags;
import org.cloudburstmc.protocol.bedrock.data.inventory.ContainerEnumName;
import org.cloudburstmc.protocol.bedrock.packet.LevelSoundEvent1Packet;
import org.cloudburstmc.protocol.bedrock.packet.LevelSoundEvent2Packet;
import org.cloudburstmc.protocol.bedrock.packet.LevelSoundEventPacket;
import org.cloudburstmc.protocol.bedrock.packet.UpdateClientInputLocksPacket;
import org.cloudburstmc.protocol.bedrock.transformer.FlagTransformer;
import org.cloudburstmc.protocol.common.util.TypeMap;

public class Bedrock_v560 extends Bedrock_v557 {

    protected static final TypeMap<ActorFlags> ACTOR_FLAGS = Bedrock_v557.ACTOR_FLAGS.toBuilder()
            .shift(46, 1)
            .insert(46, ActorFlags.CAN_DASH)
            .insert(108, ActorFlags.HAS_DASH_COOLDOWN)
            .insert(109, ActorFlags.PUSH_TOWARDS_CLOSEST_SPACE)
            .build();

    protected static final ActorDataTypeMap ACTOR_DATA = Bedrock_v557.ACTOR_DATA.toBuilder()
            .update(ActorDataTypes.FLAGS, new FlagTransformer(ACTOR_FLAGS, 0))
            .update(ActorDataTypes.FLAGS_2, new FlagTransformer(ACTOR_FLAGS, 1))
            .build();

    protected static final TypeMap<ContainerEnumName> CONTAINER_SLOT_TYPES = Bedrock_v557.CONTAINER_SLOT_TYPES.toBuilder()
            .shift(21, 1)
            .insert(21, ContainerEnumName.RECIPE_BOOK_CONTAINER)
            .build();

    protected static final TypeMap<SoundEvent> SOUND_EVENTS = Bedrock_v557.SOUND_EVENTS.toBuilder()
            .remove(448) // UNDEFINED
            .insert(443, SoundEvent.STEP_SAND)
            .insert(444, SoundEvent.DASH_READY)
            .insert(448, SoundEvent.PRESSURE_PLATE_CLICK_OFF)
            .insert(449, SoundEvent.PRESSURE_PLATE_CLICK_ON)
            .insert(450, SoundEvent.BUTTON_CLICK_OFF)
            .insert(451, SoundEvent.BUTTON_CLICK_ON)
            .insert(452, SoundEvent.DOOR_OPEN)
            .insert(453, SoundEvent.DOOR_CLOSE)
            .insert(454, SoundEvent.TRAPDOOR_OPEN)
            .insert(455, SoundEvent.TRAPDOOR_CLOSE)
            .insert(456, SoundEvent.FENCE_GATE_OPEN)
            .insert(457, SoundEvent.FENCE_GATE_CLOSE)
            .insert(458, SoundEvent.UNDEFINED)
            .build();

    public static final BedrockCodec CODEC = Bedrock_v557.CODEC.toBuilder()
            .raknetProtocolVersion(11)
            .protocolVersion(560)
            .minecraftVersion("1.19.50")
            .helper(() -> new BedrockCodecHelper_v557(ACTOR_DATA, GAME_RULE_TYPES, ITEM_STACK_REQUEST_TYPES, CONTAINER_SLOT_TYPES, PLAYER_ABILITIES, TEXT_PROCESSING_ORIGINS))
            .updateSerializer(LevelSoundEvent1Packet.class, new LevelSoundEvent1Serializer_v291(SOUND_EVENTS))
            .updateSerializer(LevelSoundEvent2Packet.class, new LevelSoundEvent2Serializer_v313(SOUND_EVENTS))
            .updateSerializer(LevelSoundEventPacket.class, new LevelSoundEventSerializer_v332(SOUND_EVENTS))
            .registerPacket(UpdateClientInputLocksPacket::new, new UpdateClientInputLocksSerializer_v560(), 196, PacketRecipient.CLIENT)
            .build();
}
