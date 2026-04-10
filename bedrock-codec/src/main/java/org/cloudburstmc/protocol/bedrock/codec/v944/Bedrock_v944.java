package org.cloudburstmc.protocol.bedrock.codec.v944;

import org.cloudburstmc.protocol.bedrock.codec.ActorDataTypeMap;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodec;
import org.cloudburstmc.protocol.bedrock.codec.v291.serializer.LevelEventSerializer_v291;
import org.cloudburstmc.protocol.bedrock.codec.v361.serializer.LevelEventGenericSerializer_v361;
import org.cloudburstmc.protocol.bedrock.codec.v786.serializer.LevelSoundEventSerializer_v786;
import org.cloudburstmc.protocol.bedrock.codec.v924.Bedrock_v924;
import org.cloudburstmc.protocol.bedrock.codec.v924.serializer.ServerboundDiagnosticsSerializer_v924;
import org.cloudburstmc.protocol.bedrock.codec.v944.serializer.*;
import org.cloudburstmc.protocol.bedrock.data.LevelEventType;
import org.cloudburstmc.protocol.bedrock.data.PacketRecipient;
import org.cloudburstmc.protocol.bedrock.data.ParticleType;
import org.cloudburstmc.protocol.bedrock.data.SoundEvent;
import org.cloudburstmc.protocol.bedrock.data.actor.ActorDataTypes;
import org.cloudburstmc.protocol.bedrock.data.inventory.ContainerEnumName;
import org.cloudburstmc.protocol.bedrock.data.payload.diagnostics.MemoryCategory;
import org.cloudburstmc.protocol.bedrock.packet.*;
import org.cloudburstmc.protocol.bedrock.transformer.TypeMapTransformer;
import org.cloudburstmc.protocol.common.util.TypeMap;

/**
 * @author Kaooot
 */
public class Bedrock_v944 extends Bedrock_v924 {

    protected static final TypeMap<ContainerEnumName> CONTAINER_SLOT_TYPES = Bedrock_v924.CONTAINER_SLOT_TYPES.toBuilder()
            .insert(64, ContainerEnumName.RECIPE_FOOD_CONTAINER)
            .insert(65, ContainerEnumName.RECIPE_BLOCKS_CONTAINER)
            .insert(66, ContainerEnumName.RECIPE_FURNACE_ITEMS_CONTAINER)
            .build();

    protected static final TypeMap<MemoryCategory> MEMORY_CATEGORY_TYPES = Bedrock_v924.MEMORY_CATEGORY_TYPES.toBuilder()
            .remove(5)
            .shift(6, 43, -1)
            .insert(42, MemoryCategory.LIGHT_VOLUME_MANAGER)
            .insert(81, MemoryCategory.GAMEFACE)
            .insert(82, MemoryCategory.GAMEFACE_SYSTEM)
            .insert(83, MemoryCategory.GAMEFACE_DOM)
            .insert(84, MemoryCategory.GAMEFACE_CSS)
            .insert(85, MemoryCategory.GAMEFACE_DISPLAY)
            .insert(86, MemoryCategory.GAMEFACE_TEMP_ALLOCATOR)
            .insert(87, MemoryCategory.GAMEFACE_POOL_ALLOCATOR)
            .insert(88, MemoryCategory.GAMEFACE_DUMP)
            .insert(89, MemoryCategory.GAMEFACE_MEDIA)
            .insert(90, MemoryCategory.GAMEFACE_JSON)
            .insert(91, MemoryCategory.GAMEFACE_SCRIPT_ENGINE)
            .build();

    protected static final TypeMap<SoundEvent> SOUND_EVENTS = Bedrock_v924.SOUND_EVENTS.toBuilder()
            .replace(597, SoundEvent.PAUSE_GROWTH)
            .insert(598, SoundEvent.RESET_GROWTH)
            .insert(599, SoundEvent.UNDEFINED)
            .build();

    protected static final TypeMap<ParticleType> PARTICLE_TYPES = Bedrock_v924.PARTICLE_TYPES.toBuilder()
            .insert(99, ParticleType.PAUSE_MOB_GROWTH)
            .insert(100, ParticleType.RESET_MOB_GROWTH)
            .build();

    protected static final TypeMap<LevelEventType> LEVEL_EVENTS = Bedrock_v924.LEVEL_EVENTS.toBuilder()
            .insert(LEVEL_EVENT_PARTICLE_TYPE, PARTICLE_TYPES)
            .build();

    protected static final ActorDataTypeMap ACTOR_DATA = Bedrock_v924.ACTOR_DATA
            .toBuilder()
            .update(ActorDataTypes.DATA_PARTICLE, new TypeMapTransformer<>(PARTICLE_TYPES))
            .update(ActorDataTypes.HEARTBEAT_SOUND_EVENT, new TypeMapTransformer<>(SOUND_EVENTS))
            .build();

    public static final BedrockCodec CODEC = Bedrock_v924.CODEC.toBuilder()
            .raknetProtocolVersion(11)
            .protocolVersion(944)
            .minecraftVersion("1.26.10")
            .helper(() -> new BedrockCodecHelper_v944(ACTOR_DATA, GAME_RULE_TYPES, ITEM_STACK_REQUEST_TYPES, CONTAINER_SLOT_TYPES, PLAYER_ABILITIES, TEXT_PROCESSING_ORIGINS))
            .deregisterPacket(ClientboundDataDrivenUICloseAllScreensPacket.class)
            .updateSerializer(ClientboundDataDrivenUIShowScreenPacket.class, ClientboundDataDrivenUIShowScreenSerializer_v944.INSTANCE)
            .updateSerializer(EditorNetworkPacket.class, EditorNetworkSerializer_v944.INSTANCE)
            .updateSerializer(LevelEventPacket.class, new LevelEventSerializer_v291(LEVEL_EVENTS))
            .updateSerializer(LevelEventGenericPacket.class, new LevelEventGenericSerializer_v361(LEVEL_EVENTS))
            .updateSerializer(LevelSoundEventPacket.class, new LevelSoundEventSerializer_v786(SOUND_EVENTS))
            .updateSerializer(PlayerAuthInputPacket.class, PlayerAuthInputSerializer_v944.INSTANCE)
            .updateSerializer(ServerboundDiagnosticsPacket.class, new ServerboundDiagnosticsSerializer_v924(MEMORY_CATEGORY_TYPES))
            .updateSerializer(StartGamePacket.class, StartGameSerializer_v944.INSTANCE)
            .updateSerializer(UpdateClientInputLocksPacket.class, UpdateClientInputLocksSerializer_v944.INSTANCE)
            .updateSerializer(VoxelShapesPacket.class, VoxelShapesSerializer_v944.INSTANCE)
            .registerPacket(ClientboundDataDrivenUICloseScreenPacket::new, ClientboundDataDrivenUICloseScreenSerializer_v944.INSTANCE, 334, PacketRecipient.CLIENT)
            .registerPacket(ResourcePacksReadyForValidationPacket::new, ResourcePacksReadyForValidationSerializer_v944.INSTANCE, 340, PacketRecipient.SERVER)
            .registerPacket(LocatorBarPacket::new, LocatorBarSerializer_v944.INSTANCE, 341, PacketRecipient.CLIENT)
            .registerPacket(PartyChangedPacket::new, PartyChangedSerializer_v944.INSTANCE, 342, PacketRecipient.SERVER)
            .registerPacket(ServerboundDataDrivenScreenClosedPacket::new, ServerboundDataDrivenScreenClosedSerializer_v944.INSTANCE, 343, PacketRecipient.CLIENT)
            .registerPacket(SyncWorldClocksPacket::new, SyncWorldClocksSerializer_v944.INSTANCE, 344, PacketRecipient.CLIENT)
            .registerPacket(ClientboundAttributeLayerSyncPacket::new, ClientboundAttributeLayerSyncSerializer_v944.INSTANCE, 345, PacketRecipient.CLIENT)
            .build();
}