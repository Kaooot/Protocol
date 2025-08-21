package org.cloudburstmc.protocol.bedrock.codec.v361;

import org.cloudburstmc.protocol.bedrock.codec.BedrockCodec;
import org.cloudburstmc.protocol.bedrock.codec.ActorDataTypeMap;
import org.cloudburstmc.protocol.bedrock.codec.v291.serializer.LevelEventSerializer_v291;
import org.cloudburstmc.protocol.bedrock.codec.v354.Bedrock_v354;
import org.cloudburstmc.protocol.bedrock.codec.v354.serializer.LecternUpdateSerializer_v354;
import org.cloudburstmc.protocol.bedrock.codec.v361.serializer.*;
import org.cloudburstmc.protocol.bedrock.data.*;
import org.cloudburstmc.protocol.bedrock.data.actor.ActorDataFormat;
import org.cloudburstmc.protocol.bedrock.data.actor.ActorDataTypes;
import org.cloudburstmc.protocol.bedrock.data.actor.ActorFlags;
import org.cloudburstmc.protocol.bedrock.packet.*;
import org.cloudburstmc.protocol.bedrock.transformer.BooleanTransformer;
import org.cloudburstmc.protocol.bedrock.transformer.FlagTransformer;
import org.cloudburstmc.protocol.bedrock.transformer.TypeMapTransformer;
import org.cloudburstmc.protocol.common.util.TypeMap;

public class Bedrock_v361 extends Bedrock_v354 {

    protected static final TypeMap<ActorFlags> ACTOR_FLAGS = Bedrock_v354.ACTOR_FLAGS.toBuilder()
            .insert(87, ActorFlags.HIDDEN_WHEN_INVISIBLE)
            .build();

    protected static final TypeMap<ParticleType> PARTICLE_TYPES = Bedrock_v354.PARTICLE_TYPES.toBuilder()
            .shift(2, 1)
            .insert(2, ParticleType.BUBBLE_MANUAL)
            .shift(22, 1)
            .insert(22, ParticleType.MOB_PORTAL)
            .shift(24, 1)
            .insert(24, ParticleType.WATER_SPLASH_MANUAL)
            .build();

    protected static final ActorDataTypeMap ACTOR_DATA = Bedrock_v354.ACTOR_DATA.toBuilder()
            .update(ActorDataTypes.FLAGS, new FlagTransformer(ACTOR_FLAGS, 0))
            .update(ActorDataTypes.FLAGS_2, new FlagTransformer(ACTOR_FLAGS, 1))
            .update(ActorDataTypes.DATA_PARTICLE, new TypeMapTransformer<>(PARTICLE_TYPES))
            .replace(ActorDataTypes.NPC_DATA, 40, ActorDataFormat.STRING)
            .insert(ActorDataTypes.SKIN_ID, 103, ActorDataFormat.INT)
            .insert(ActorDataTypes.SPAWNING_FRAMES, 104, ActorDataFormat.INT)
            .insert(ActorDataTypes.COMMAND_BLOCK_TICK_DELAY, 105, ActorDataFormat.INT)
            .insert(ActorDataTypes.COMMAND_BLOCK_EXECUTE_ON_FIRST_TICK, 106, ActorDataFormat.BYTE, BooleanTransformer.INSTANCE)
            .build();

    protected static final TypeMap<LevelEventType> LEVEL_EVENTS = Bedrock_v354.LEVEL_EVENTS.toBuilder()
            .insert(LEVEL_EVENT_PARTICLE + 23, LevelEvent.PARTICLE_TELEPORT_TRAIL)
            .insert(LEVEL_EVENT_PARTICLE_TYPE, PARTICLE_TYPES)
            .build();

    protected static final TypeMap<PackType> RESOURCE_PACK_TYPES = TypeMap.builder(PackType.class)
            .insert(0, PackType.INVALID)
            .insert(1, PackType.RESOURCES)
            .insert(2, PackType.BEHAVIOR)
            .insert(3, PackType.WORLD_TEMPLATE)
            .insert(4, PackType.ADDON)
            .insert(5, PackType.SKINS)
            .insert(6, PackType.CACHED)
            .insert(7, PackType.COPY_PROTECTED)
            .build();

    public static BedrockCodec CODEC = Bedrock_v354.CODEC.toBuilder()
            .protocolVersion(361)
            .minecraftVersion("1.12.0")
            .helper(() -> new BedrockCodecHelper_v361(ACTOR_DATA, GAME_RULE_TYPES))
            .deregisterPacket(AddHangingEntityPacket.class)
            .deregisterPacket(LecternUpdatePacket.class)
            .deregisterPacket(VideoStreamConnectPacket.class)
            .updateSerializer(StartGamePacket.class, StartGameSerializer_v361.INSTANCE)
            .updateSerializer(AddPaintingPacket.class, AddPaintingSerializer_v361.INSTANCE)
            .updateSerializer(LevelEventPacket.class, new LevelEventSerializer_v291(LEVEL_EVENTS))                                  
            .updateSerializer(CraftingDataPacket.class, CraftingDataSerializer_v361.INSTANCE)
            .updateSerializer(LevelChunkPacket.class, LevelChunkSerializer_v361.INSTANCE)
            .updateSerializer(CommandBlockUpdatePacket.class, CommandBlockUpdateSerializer_v361.INSTANCE)
            .updateSerializer(ResourcePackDataInfoPacket.class, new ResourcePackDataInfoSerializer_v361(RESOURCE_PACK_TYPES))
            .updateSerializer(StructureBlockUpdatePacket.class, StructureBlockUpdateSerializer_v361.INSTANCE)
            .registerPacket(LevelEventGenericPacket::new, new LevelEventGenericSerializer_v361(LEVEL_EVENTS), 124, PacketRecipient.CLIENT)
            .registerPacket(LecternUpdatePacket::new, LecternUpdateSerializer_v354.INSTANCE, 125, PacketRecipient.SERVER)
            .registerPacket(VideoStreamConnectPacket::new, VideoStreamConnectSerializer_v361.INSTANCE, 126, PacketRecipient.CLIENT)
            // AddEntityPacket 127
            // RemoveActorPacket 128
            .registerPacket(ClientCacheStatusPacket::new, ClientCacheStatusSerializer_v361.INSTANCE, 129, PacketRecipient.SERVER)
            .registerPacket(StructureTemplateDataRequestPacket::new, StructureTemplateDataRequestSerializer_v361.INSTANCE, 132, PacketRecipient.SERVER)
            .registerPacket(StructureTemplateDataResponsePacket::new, StructureTemplateDataResponseSerializer_v361.INSTANCE, 133, PacketRecipient.CLIENT)
            .registerPacket(UpdateBlockPropertiesPacket::new, UpdateBlockPropertiesSerializer_v361.INSTANCE, 134, PacketRecipient.CLIENT)
            .registerPacket(ClientCacheBlobStatusPacket::new, ClientCacheBlobStatusSerializer_v361.INSTANCE, 135, PacketRecipient.SERVER)
            .registerPacket(ClientCacheMissResponsePacket::new, ClientCacheMissResponseSerializer_v361.INSTANCE, 136, PacketRecipient.CLIENT)
            .build();
}
