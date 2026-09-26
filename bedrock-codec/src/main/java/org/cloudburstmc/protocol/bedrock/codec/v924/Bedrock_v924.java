package org.cloudburstmc.protocol.bedrock.codec.v924;

import org.cloudburstmc.protocol.bedrock.codec.BedrockCodec;
import org.cloudburstmc.protocol.bedrock.codec.ActorDataTypeMap;
import org.cloudburstmc.protocol.bedrock.codec.v786.serializer.LevelSoundEventSerializer_v786;
import org.cloudburstmc.protocol.bedrock.codec.v898.Bedrock_v898;
import org.cloudburstmc.protocol.bedrock.codec.v924.serializer.*;
import org.cloudburstmc.protocol.bedrock.data.PacketRecipient;
import org.cloudburstmc.protocol.bedrock.data.sound.LevelSoundEvent;
import org.cloudburstmc.protocol.bedrock.data.actor.ActorDataFormat;
import org.cloudburstmc.protocol.bedrock.data.actor.ActorDataTypes;
import org.cloudburstmc.protocol.bedrock.packet.*;
import org.cloudburstmc.protocol.common.util.TypeMap;

public class Bedrock_v924 extends Bedrock_v898 {

    protected static final ActorDataTypeMap ENTITY_DATA = Bedrock_v898.ENTITY_DATA
            .toBuilder()
            .insert(ActorDataTypes.ARROW_SHOOTER_ID, 17, ActorDataFormat.LONG)
            .insert(ActorDataTypes.FIREWORK_DIRECTION, 17, ActorDataFormat.VECTOR3F)
            .insert(ActorDataTypes.FIREWORK_SHOOTER_ID, 18, ActorDataFormat.LONG)
            .insert(ActorDataTypes.AIM_ASSIST_PRIORITY_PRESET_ID, 136, ActorDataFormat.INT)
            .insert(ActorDataTypes.AIM_ASSIST_PRIORITY_CATEGORY_ID, 137, ActorDataFormat.INT)
            .insert(ActorDataTypes.AIM_ASSIST_PRIORITY_ACTOR_ID, 138, ActorDataFormat.INT)
            .build();

    protected static final TypeMap<LevelSoundEvent> SOUND_EVENTS = Bedrock_v898.SOUND_EVENTS
            .toBuilder()
            .replace(578, LevelSoundEvent.SADDLE_IN_WATER)
            .insert(579, LevelSoundEvent.STONE_SPEAR_ATTACK_HIT)
            .insert(580, LevelSoundEvent.IRON_SPEAR_ATTACK_HIT)
            .insert(581, LevelSoundEvent.COPPER_SPEAR_ATTACK_HIT)
            .insert(582, LevelSoundEvent.GOLDEN_SPEAR_ATTACK_HIT)
            .insert(583, LevelSoundEvent.DIAMOND_SPEAR_ATTACK_HIT)
            .insert(584, LevelSoundEvent.NETHERITE_SPEAR_ATTACK_HIT)
            .insert(585, LevelSoundEvent.STONE_SPEAR_ATTACK_MISS)
            .insert(586, LevelSoundEvent.IRON_SPEAR_ATTACK_MISS)
            .insert(587, LevelSoundEvent.COPPER_SPEAR_ATTACK_MISS)
            .insert(588, LevelSoundEvent.GOLDEN_SPEAR_ATTACK_MISS)
            .insert(589, LevelSoundEvent.DIAMOND_SPEAR_ATTACK_MISS)
            .insert(590, LevelSoundEvent.NETHERITE_SPEAR_ATTACK_MISS)
            .insert(591, LevelSoundEvent.STONE_SPEAR_USE)
            .insert(592, LevelSoundEvent.IRON_SPEAR_USE)
            .insert(593, LevelSoundEvent.COPPER_SPEAR_USE)
            .insert(594, LevelSoundEvent.GOLDEN_SPEAR_USE)
            .insert(595, LevelSoundEvent.DIAMOND_SPEAR_USE)
            .insert(596, LevelSoundEvent.NETHERITE_SPEAR_USE)
            .insert(597, LevelSoundEvent.UNDEFINED)

            .build();

    public static final BedrockCodec CODEC = Bedrock_v898.CODEC.toBuilder()
            .protocolVersion(924)
            .minecraftVersion("1.26.0")
            .helper(() -> new BedrockCodecHelper_v924(ENTITY_DATA, GAME_RULE_TYPES, ITEM_STACK_REQUEST_TYPES, CONTAINER_SLOT_TYPES, PLAYER_ABILITIES, TEXT_PROCESSING_ORIGINS))
            .updateSerializer(BiomeDefinitionListPacket.class, BiomeDefinitionListSerializer_v924.INSTANCE)
            .updateSerializer(BookEditPacket.class, BookEditSerializer_v924.INSTANCE)
            .updateSerializer(CameraAimAssistPresetsPacket.class, CameraAimAssistPresetsSerializer_v924.INSTANCE)
            .updateSerializer(CameraInstructionPacket.class, CameraInstructionSerializer_v924.INSTANCE)
            .updateSerializer(ClientboundDataStorePacket.class, ClientboundDataStoreSerializer_v924.INSTANCE)
            .updateSerializer(PrimitiveShapesPacket.class, PrimitiveShapesSerializer_v924.INSTANCE)
            .updateSerializer(GraphicsOverrideParameterPacket.class, GraphicsOverrideParameterSerializer_v924.INSTANCE)
            .updateSerializer(LevelSoundEventPacket.class, new LevelSoundEventSerializer_v786(SOUND_EVENTS))
            .updateSerializer(ServerboundDataStorePacket.class, ServerboundDataStoreSerializer_v924.INSTANCE)
            .updateSerializer(ServerboundDiagnosticsPacket.class, ServerboundDiagnosticsSerializer_v924.INSTANCE)
            .updateSerializer(StartGamePacket.class, StartGameSerializer_v924.INSTANCE)
            .updateSerializer(TextPacket.class, TextSerializer_v924.INSTANCE)
            .registerPacket(ClientboundDataDrivenUIShowScreenPacket::new, ClientboundDataDrivenUIShowScreenSerializer_v924.INSTANCE, 333, PacketRecipient.CLIENT)
            .registerPacket(ClientboundDataDrivenUICloseScreenPacket::new, ClientboundDataDrivenUICloseScreenSerializer_v924.INSTANCE, 334, PacketRecipient.CLIENT)
            .registerPacket(ClientboundDataDrivenUIReloadPacket::new, ClientboundDataDrivenUIReloadSerializer_v924.INSTANCE, 335, PacketRecipient.CLIENT)
            .registerPacket(ClientboundTextureShiftPacket::new, ClientboundTextureShiftSerializer_v924.INSTANCE, 336, PacketRecipient.CLIENT)
            .registerPacket(VoxelShapesPacket::new, VoxelShapesSerializer_v924.INSTANCE, 337, PacketRecipient.CLIENT)
            .registerPacket(CameraSplinePacket::new, CameraSplineSerializer_v924.INSTANCE, 338, PacketRecipient.CLIENT)
            .registerPacket(CameraAimAssistActorPriorityPacket::new, CameraAimAssistActorPrioritySerializer_v924.INSTANCE, 339, PacketRecipient.CLIENT)
            .build();
}
