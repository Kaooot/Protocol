package org.cloudburstmc.protocol.bedrock.codec.v897;

import org.cloudburstmc.protocol.bedrock.codec.ActorDataTypeMap;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodec;
import org.cloudburstmc.protocol.bedrock.codec.v291.serializer.ActorEventSerializer_v291;
import org.cloudburstmc.protocol.bedrock.codec.v786.serializer.LevelSoundEventSerializer_v786;
import org.cloudburstmc.protocol.bedrock.codec.v818.serializer.StartGameSerializer_v818;
import org.cloudburstmc.protocol.bedrock.codec.v859.Bedrock_v859;
import org.cloudburstmc.protocol.bedrock.codec.v897.serializer.*;
import org.cloudburstmc.protocol.bedrock.data.PacketRecipient;
import org.cloudburstmc.protocol.bedrock.data.SoundEvent;
import org.cloudburstmc.protocol.bedrock.data.actor.ActorDataTypes;
import org.cloudburstmc.protocol.bedrock.data.actor.ActorEvent;
import org.cloudburstmc.protocol.bedrock.data.actor.ActorFlags;
import org.cloudburstmc.protocol.bedrock.packet.*;
import org.cloudburstmc.protocol.bedrock.transformer.FlagTransformer;
import org.cloudburstmc.protocol.common.util.TypeMap;

/**
 * @author Kaooot
 */
public class Bedrock_v897 extends Bedrock_v859 {

    protected static final TypeMap<SoundEvent> SOUND_EVENTS = Bedrock_v859.SOUND_EVENTS
            .toBuilder()
            .replace(566, SoundEvent.LUNGE_1) // "item.enchant.lunge1"
            .insert(567, SoundEvent.LUNGE_2) // "item.enchant.lunge2"
            .insert(568, SoundEvent.LUNGE_3) // "item.enchant.lunge3"
            .insert(569, SoundEvent.ATTACK_CRITICAL) // "attack.critical"
            .insert(570, SoundEvent.SPEAR_ATTACK_HIT) // "item.spear.attack_hit"
            .insert(571, SoundEvent.SPEAR_ATTACK_MISS) // "item.spear.attack_miss"
            .insert(572, SoundEvent.WOODEN_SPEAR_ATTACK_HIT) // "item.wooden_spear.attack_hit"
            .insert(573, SoundEvent.WOODEN_SPEAR_ATTACK_MISS) // "item.wooden_spear.attack_miss"
            .insert(574, SoundEvent.IMITATE_PARCHED) // "imitate.parched"
            .insert(575, SoundEvent.IMITATE_CAMEL_HUSK) // "imitate.camel_husk"
            .insert(576, SoundEvent.SPEAR_USE) // "item.spear.use"
            .insert(577, SoundEvent.WOODEN_SPEAR_USE) // "item.wooden_spear.use"
            .insert(578, SoundEvent.UNDEFINED)
            .build();

    protected static final TypeMap<ActorEvent> ACTOR_EVENTS = Bedrock_v859.ACTOR_EVENTS.toBuilder()
            .insert(80, ActorEvent.KINETIC_DAMAGE_DEALT)
            .build();

    protected static final TypeMap<ActorFlags> ACTOR_FLAGS = Bedrock_v859.ACTOR_FLAGS
            .toBuilder()
            .insert(126, ActorFlags.ROTATION_LOCKED_TO_VEHICLE)
            .build();

    protected static final ActorDataTypeMap ACTOR_DATA = Bedrock_v859.ACTOR_DATA
            .toBuilder()
            .update(ActorDataTypes.FLAGS, new FlagTransformer(ACTOR_FLAGS, 0))
            .update(ActorDataTypes.FLAGS_2, new FlagTransformer(ACTOR_FLAGS, 1))
            .build();

    public static final BedrockCodec CODEC = Bedrock_v859.CODEC.toBuilder()
            .raknetProtocolVersion(11)
            .protocolVersion(897)
            .minecraftVersion("1.21.130")
            .helper(() -> new BedrockCodecHelper_v897(ACTOR_DATA, GAME_RULE_TYPES, ITEM_STACK_REQUEST_TYPES, CONTAINER_SLOT_TYPES, PLAYER_ABILITIES, TEXT_PROCESSING_ORIGINS))
            .updateSerializer(ActorEventPacket.class, new ActorEventSerializer_v291(ACTOR_EVENTS))
            .updateSerializer(AnimatePacket.class, AnimateSerializer_v897.INSTANCE)
            .updateSerializer(AvailableCommandsPacket.class, new AvailableCommandsSerializer_v897(COMMAND_PARAMS))
            .updateSerializer(CameraAimAssistPresetsPacket.class, CameraAimAssistPresetsSerializer_v897.INSTANCE)
            .updateSerializer(ClientboundDebugRendererPacket.class, ClientboundDebugRendererSerializer_v897.INSTANCE)
            .updateSerializer(CommandOutputPacket.class, CommandOutputSerializer_v897.INSTANCE)
            .updateSerializer(CommandRequestPacket.class, CommandRequestSerializer_v897.INSTANCE)
            .updateSerializer(LevelSoundEventPacket.class, new LevelSoundEventSerializer_v786(SOUND_EVENTS))
            .updateSerializer(InteractPacket.class, InteractSerializer_v897.INSTANCE)
            .updateSerializer(LegacyTelemetryEventPacket.class, LegacyTelemetryEventSerializer_v897.INSTANCE)
            .updateSerializer(MobEffectPacket.class, MobEffectSerializer_v897.INSTANCE)
            .updateSerializer(ResourcePackStackPacket.class, ResourcePackStackSerializer_v897.INSTANCE)
            .updateSerializer(StartGamePacket.class, StartGameSerializer_v818.INSTANCE)
            .updateSerializer(TextPacket.class, TextSerializer_v897.INSTANCE)
            .registerPacket(ClientboundDataStorePacket::new, ClientboundDataStoreSerializer_v897.INSTANCE, 330, PacketRecipient.CLIENT)
            .registerPacket(ServerboundDataStorePacket::new, ServerboundDataStoreSerializer_v897.INSTANCE, 332, PacketRecipient.SERVER)
            .build();
}