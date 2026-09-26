package org.cloudburstmc.protocol.bedrock.codec.v354;

import org.cloudburstmc.protocol.bedrock.codec.v354.serializer.LegacyTelemetryEventSerializer_v354;

import org.cloudburstmc.protocol.bedrock.packet.LegacyTelemetryEventPacket;

import org.cloudburstmc.protocol.bedrock.codec.BedrockCodec;
import org.cloudburstmc.protocol.bedrock.codec.ActorDataTypeMap;
import org.cloudburstmc.protocol.bedrock.codec.v291.serializer.LevelEventSerializer_v291;
import org.cloudburstmc.protocol.bedrock.codec.v291.serializer.LevelSoundEvent1Serializer_v291;
import org.cloudburstmc.protocol.bedrock.codec.v313.serializer.LevelSoundEvent2Serializer_v313;
import org.cloudburstmc.protocol.bedrock.codec.v332.serializer.LevelSoundEventSerializer_v332;
import org.cloudburstmc.protocol.bedrock.codec.v340.BedrockCodecHelper_v340;
import org.cloudburstmc.protocol.bedrock.codec.v340.Bedrock_v340;
import org.cloudburstmc.protocol.bedrock.codec.v354.serializer.*;
import org.cloudburstmc.protocol.bedrock.data.world.event.LevelEvent;
import org.cloudburstmc.protocol.bedrock.data.world.event.LevelEventType;
import org.cloudburstmc.protocol.bedrock.data.PacketRecipient;
import org.cloudburstmc.protocol.bedrock.data.sound.LevelSoundEvent;
import org.cloudburstmc.protocol.bedrock.data.actor.ActorDataFormat;
import org.cloudburstmc.protocol.bedrock.data.actor.ActorDataTypes;
import org.cloudburstmc.protocol.bedrock.data.actor.ActorFlags;
import org.cloudburstmc.protocol.bedrock.packet.*;
import org.cloudburstmc.protocol.bedrock.transformer.FlagTransformer;
import org.cloudburstmc.protocol.common.util.TypeMap;

public class Bedrock_v354 extends Bedrock_v340 {

    protected static final TypeMap<ActorFlags> ENTITY_FLAGS = Bedrock_v340.ENTITY_FLAGS.toBuilder()
            .shift(74, 1)
            .insert(74, ActorFlags.BLOCKED_USING_DAMAGED_SHIELD)
            .insert(81, ActorFlags.IS_ILLAGER_CAPTAIN)
            .insert(82, ActorFlags.STUNNED)
            .insert(83, ActorFlags.ROARING)
            .insert(84, ActorFlags.DELAYED_ATTACK)
            .insert(85, ActorFlags.IS_AVOIDING_MOBS)
            .insert(86, ActorFlags.FACING_TARGET_TO_RANGE_ATTACK)
            .build();

    protected static final ActorDataTypeMap ENTITY_DATA = Bedrock_v340.ENTITY_DATA.toBuilder()
            .update(ActorDataTypes.FLAGS, new FlagTransformer(ENTITY_FLAGS, 0))
            .update(ActorDataTypes.FLAGS_2, new FlagTransformer(ENTITY_FLAGS, 1))
            .insert(ActorDataTypes.TRADE_EXPERIENCE, 102, ActorDataFormat.INT)
            .build();

    protected static final TypeMap<LevelSoundEvent> SOUND_EVENTS = Bedrock_v340.SOUND_EVENTS.toBuilder()
            .insert(187, LevelSoundEvent.FLETCHING_TABLE_USE)
            .replace(257, LevelSoundEvent.GRINDSTONE_USE)
            .insert(258, LevelSoundEvent.BELL)
            .insert(259, LevelSoundEvent.CAMPFIRE_CRACKLE)
            .insert(262, LevelSoundEvent.SWEET_BERRY_BUSH_HURT)
            .insert(263, LevelSoundEvent.SWEET_BERRY_BUSH_PICK)
            .insert(260, LevelSoundEvent.ROAR)
            .insert(261, LevelSoundEvent.STUN)
            .insert(264, LevelSoundEvent.CARTOGRAPHY_TABLE_USE)
            .insert(265, LevelSoundEvent.STONECUTTER_USE)
            .insert(266, LevelSoundEvent.COMPOSTER_EMPTY)
            .insert(267, LevelSoundEvent.COMPOSTER_FILL)
            .insert(268, LevelSoundEvent.COMPOSTER_FILL_LAYER)
            .insert(269, LevelSoundEvent.COMPOSTER_READY)
            .insert(270, LevelSoundEvent.BARREL_OPEN)
            .insert(271, LevelSoundEvent.BARREL_CLOSE)
            .insert(272, LevelSoundEvent.RAID_HORN)
            .insert(273, LevelSoundEvent.LOOM_USE)
            .insert(274, LevelSoundEvent.UNDEFINED)
            .build();

    protected static final TypeMap<LevelEventType> LEVEL_EVENTS = Bedrock_v340.LEVEL_EVENTS.toBuilder()
            .insert(LEVEL_EVENT_PARTICLE + 22, LevelEvent.PARTICLE_KNOCKBACK_ROAR)
            .build();


    public static final BedrockCodec CODEC = Bedrock_v340.CODEC.toBuilder()
            .protocolVersion(354)
            .minecraftVersion("1.11.0")
            .helper(() -> new BedrockCodecHelper_v340(ENTITY_DATA, GAME_RULE_TYPES))
            .updateSerializer(CraftingDataPacket.class, CraftingDataSerializer_v354.INSTANCE)
            .updateSerializer(LegacyTelemetryEventPacket.class, LegacyTelemetryEventSerializer_v354.INSTANCE)
            .updateSerializer(ClientboundMapItemDataPacket.class, ClientboundMapItemDataSerializer_v354.INSTANCE)
            .updateSerializer(UpdateTradePacket.class, UpdateTradeSerializer_v354.INSTANCE)
            .updateSerializer(LecternUpdatePacket.class, LecternUpdateSerializer_v354.INSTANCE)
            .updateSerializer(LevelEventPacket.class, new LevelEventSerializer_v291(LEVEL_EVENTS))
            .updateSerializer(LevelSoundEvent1Packet.class, new LevelSoundEvent1Serializer_v291(SOUND_EVENTS))
            .updateSerializer(LevelSoundEvent2Packet.class, new LevelSoundEvent2Serializer_v313(SOUND_EVENTS))
            .updateSerializer(LevelSoundEventPacket.class, new LevelSoundEventSerializer_v332(SOUND_EVENTS))
            .registerPacket(OnScreenTextureAnimationPacket::new, OnScreenTextureAnimationSerializer_v354.INSTANCE, 130, PacketRecipient.CLIENT)
            .registerPacket(MapCreateLockedCopyPacket::new, MapCreateLockedCopySerializer_v354.INSTANCE, 131, PacketRecipient.SERVER)
            .build();
}
