package org.cloudburstmc.protocol.bedrock.codec.v898;

import org.cloudburstmc.protocol.bedrock.codec.v898.serializer.LegacyTelemetryEventSerializer_v898;

import org.cloudburstmc.protocol.bedrock.packet.LegacyTelemetryEventPacket;

import org.cloudburstmc.protocol.bedrock.codec.BedrockCodec;
import org.cloudburstmc.protocol.bedrock.codec.v291.serializer.ActorEventSerializer_v291;
import org.cloudburstmc.protocol.bedrock.codec.v786.serializer.LevelSoundEventSerializer_v786;
import org.cloudburstmc.protocol.bedrock.codec.v860.Bedrock_v860;
import org.cloudburstmc.protocol.bedrock.codec.v898.serializer.*;
import org.cloudburstmc.protocol.bedrock.data.PacketRecipient;
import org.cloudburstmc.protocol.bedrock.data.sound.LevelSoundEvent;
import org.cloudburstmc.protocol.bedrock.data.actor.ActorEvent;
import org.cloudburstmc.protocol.bedrock.data.actor.ActorEvent;
import org.cloudburstmc.protocol.bedrock.packet.*;
import org.cloudburstmc.protocol.common.util.TypeMap;

public class Bedrock_v898 extends Bedrock_v860 {

    protected static final TypeMap<ActorEvent> ACTOR_EVENTS = Bedrock_v860.ACTOR_EVENTS.toBuilder()
            .insert(80, ActorEvent.KINETIC_DAMAGE_DEALT)
            .build();

    protected static final TypeMap<LevelSoundEvent> SOUND_EVENTS = Bedrock_v860.SOUND_EVENTS
            .toBuilder()
            .replace(566, LevelSoundEvent.LUNGE_1)
            .insert(567, LevelSoundEvent.LUNGE_2)
            .insert(568, LevelSoundEvent.LUNGE_3)
            .insert(569, LevelSoundEvent.ATTACK_CRITICAL)
            .insert(570, LevelSoundEvent.SPEAR_ATTACK_HIT)
            .insert(571, LevelSoundEvent.SPEAR_ATTACK_MISS)
            .insert(572, LevelSoundEvent.WOODEN_SPEAR_ATTACK_HIT)
            .insert(573, LevelSoundEvent.WOODEN_SPEAR_ATTACK_MISS)
            .insert(574, LevelSoundEvent.IMITATE_PARCHED)
            .insert(575, LevelSoundEvent.IMITATE_CAMEL_HUSK)
            .insert(576, LevelSoundEvent.SPEAR_USE)
            .insert(577, LevelSoundEvent.WOODEN_SPEAR_USE)
            .insert(578, LevelSoundEvent.UNDEFINED)
            .build();

    public static final BedrockCodec CODEC = Bedrock_v860.CODEC.toBuilder()
            .protocolVersion(898)
            .minecraftVersion("1.21.130")
            .helper(() -> new BedrockCodecHelper_v898(ENTITY_DATA, GAME_RULE_TYPES, ITEM_STACK_REQUEST_TYPES, CONTAINER_SLOT_TYPES, PLAYER_ABILITIES, TEXT_PROCESSING_ORIGINS))
            .updateSerializer(AnimatePacket.class, AnimateSerializer_v898.INSTANCE)
            .updateSerializer(AvailableCommandsPacket.class, new AvailableCommandsSerializer_v898(COMMAND_PARAMS))
            .updateSerializer(CameraAimAssistPresetsPacket.class, CameraAimAssistPresetsSerializer_v898.INSTANCE)
            .updateSerializer(CommandOutputPacket.class, CommandOutputSerializer_v898.INSTANCE)
            .updateSerializer(CommandRequestPacket.class, CommandRequestSerializer_v898.INSTANCE)
            .updateSerializer(ActorEventPacket.class, new ActorEventSerializer_v291(ACTOR_EVENTS))
            .updateSerializer(LegacyTelemetryEventPacket.class, LegacyTelemetryEventSerializer_v898.INSTANCE)
            .updateSerializer(InteractPacket.class, InteractSerializer_v898.INSTANCE)
            .updateSerializer(LevelSoundEventPacket.class, new LevelSoundEventSerializer_v786(SOUND_EVENTS))
            .updateSerializer(MobEffectPacket.class, MobEffectSerializer_v898.INSTANCE)
            .updateSerializer(ResourcePackStackPacket.class, ResourcePackStackSerializer_v898.INSTANCE)
            .updateSerializer(StartGamePacket.class, StartGameSerializer_v898.INSTANCE)
            .updateSerializer(TextPacket.class, TextSerializer_v898.INSTANCE)
            .registerPacket(ClientboundDataStorePacket::new, ClientboundDataStoreSerializer_v898.INSTANCE, 330, PacketRecipient.CLIENT)
            .registerPacket(ServerboundDataStorePacket::new, ServerboundDataStoreSerializer_v898.INSTANCE, 332, PacketRecipient.SERVER)
            .build();
}
