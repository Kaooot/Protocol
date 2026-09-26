package org.cloudburstmc.protocol.bedrock.codec.v428;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodec;
import org.cloudburstmc.protocol.bedrock.codec.ActorDataTypeMap;
import org.cloudburstmc.protocol.bedrock.codec.v291.serializer.LevelEventSerializer_v291;
import org.cloudburstmc.protocol.bedrock.codec.v291.serializer.LevelSoundEvent1Serializer_v291;
import org.cloudburstmc.protocol.bedrock.codec.v313.serializer.LevelSoundEvent2Serializer_v313;
import org.cloudburstmc.protocol.bedrock.codec.v332.serializer.LevelSoundEventSerializer_v332;
import org.cloudburstmc.protocol.bedrock.codec.v361.serializer.LevelEventGenericSerializer_v361;
import org.cloudburstmc.protocol.bedrock.codec.v388.serializer.AvailableCommandsSerializer_v388;
import org.cloudburstmc.protocol.bedrock.codec.v422.Bedrock_v422;
import org.cloudburstmc.protocol.bedrock.codec.v428.serializer.CameraShakeSerializer_v428;
import org.cloudburstmc.protocol.bedrock.codec.v428.serializer.ClientboundDebugRendererSerializer_v428;
import org.cloudburstmc.protocol.bedrock.codec.v428.serializer.PlayerAuthInputSerializer_v428;
import org.cloudburstmc.protocol.bedrock.codec.v428.serializer.StartGameSerializer_v428;
import org.cloudburstmc.protocol.bedrock.data.world.event.LevelEvent;
import org.cloudburstmc.protocol.bedrock.data.world.event.LevelEventType;
import org.cloudburstmc.protocol.bedrock.data.PacketRecipient;
import org.cloudburstmc.protocol.bedrock.data.sound.LevelSoundEvent;
import org.cloudburstmc.protocol.bedrock.data.command.CommandParam;
import org.cloudburstmc.protocol.bedrock.data.actor.ActorDataFormat;
import org.cloudburstmc.protocol.bedrock.data.actor.ActorDataTypes;
import org.cloudburstmc.protocol.bedrock.data.actor.ActorFlags;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.ItemStackRequestActionType;
import org.cloudburstmc.protocol.bedrock.packet.*;
import org.cloudburstmc.protocol.bedrock.transformer.FlagTransformer;
import org.cloudburstmc.protocol.common.util.TypeMap;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Bedrock_v428 extends Bedrock_v422 {

    protected static final TypeMap<ActorFlags> ENTITY_FLAGS = Bedrock_v422.ENTITY_FLAGS.toBuilder()
            .insert(96, ActorFlags.RAM_ATTACK)
            .build();

    protected static final ActorDataTypeMap ENTITY_DATA = Bedrock_v422.ENTITY_DATA.toBuilder()
            .update(ActorDataTypes.FLAGS, new FlagTransformer(ENTITY_FLAGS, 0))
            .update(ActorDataTypes.FLAGS_2, new FlagTransformer(ENTITY_FLAGS, 1))
            .shift(60, 1)
            .insert(ActorDataTypes.SEAT_ROTATION_OFFSET_DEGREES, 60, ActorDataFormat.FLOAT)
            .shift(120, 1)
            .insert(ActorDataTypes.FREEZING_EFFECT_STRENGTH, 120, ActorDataFormat.FLOAT)
            .insert(ActorDataTypes.GOAT_HORN_COUNT, 122, ActorDataFormat.INT)
            .insert(ActorDataTypes.BASE_RUNTIME_ID, 123, ActorDataFormat.STRING)
            .build();

    protected static final TypeMap<LevelEventType> LEVEL_EVENTS = Bedrock_v422.LEVEL_EVENTS.toBuilder()
            .insert(LEVEL_EVENT_PARTICLE + 27, LevelEvent.PARTICLE_VIBRATION_SIGNAL)
            .insert(LEVEL_EVENT_BLOCK + 14, LevelEvent.CAULDRON_FILL_POWDER_SNOW)
            .insert(LEVEL_EVENT_BLOCK + 15, LevelEvent.CAULDRON_TAKE_POWDER_SNOW)
            .build();

    protected static final TypeMap<CommandParam> COMMAND_PARAMS = Bedrock_v422.COMMAND_PARAMS.toBuilder()
            .shift(2, 1)
            .shift(57, 6)
            .insert(60, CommandParam.BLOCK_STATE_ARRAY)
            .build();

    protected static final TypeMap<LevelSoundEvent> SOUND_EVENTS = Bedrock_v422.SOUND_EVENTS.toBuilder()
            .replace(318, LevelSoundEvent.AMBIENT_LOOP_WARPED_FOREST)
            .insert(319, LevelSoundEvent.AMBIENT_LOOP_SOULSAND_VALLEY)
            .insert(320, LevelSoundEvent.AMBIENT_LOOP_NETHER_WASTES)
            .insert(321, LevelSoundEvent.AMBIENT_LOOP_BASALT_DELTAS)
            .insert(322, LevelSoundEvent.AMBIENT_LOOP_CRIMSON_FOREST)
            .insert(323, LevelSoundEvent.AMBIENT_ADDITION_WARPED_FOREST)
            .insert(324, LevelSoundEvent.AMBIENT_ADDITION_SOULSAND_VALLEY)
            .insert(325, LevelSoundEvent.AMBIENT_ADDITION_NETHER_WASTES)
            .insert(326, LevelSoundEvent.AMBIENT_ADDITION_BASALT_DELTAS)
            .insert(327, LevelSoundEvent.AMBIENT_ADDITION_CRIMSON_FOREST)
            .insert(328, LevelSoundEvent.SCULK_SENSOR_POWER_ON)
            .insert(329, LevelSoundEvent.SCULK_SENSOR_POWER_OFF)
            .insert(330, LevelSoundEvent.BUCKET_FILL_POWDER_SNOW)
            .insert(331, LevelSoundEvent.BUCKET_EMPTY_POWDER_SNOW)
            .insert(332, LevelSoundEvent.UNDEFINED)
            .build();

    protected static final TypeMap<ItemStackRequestActionType> ITEM_STACK_REQUEST_TYPES = Bedrock_v422.ITEM_STACK_REQUEST_TYPES.toBuilder()
            .shift(9, 1)
            .insert(9, ItemStackRequestActionType.SCREEN_HUD_MINE_BLOCK)
            .build();

    public static final BedrockCodec CODEC = Bedrock_v422.CODEC.toBuilder()
            .protocolVersion(428)
            .minecraftVersion("1.16.210")
            .helper(() -> new BedrockCodecHelper_v428(ENTITY_DATA, GAME_RULE_TYPES, ITEM_STACK_REQUEST_TYPES, CONTAINER_SLOT_TYPES))
            .updateSerializer(StartGamePacket.class, StartGameSerializer_v428.INSTANCE)
            .updateSerializer(PlayerAuthInputPacket.class, PlayerAuthInputSerializer_v428.INSTANCE)
            .updateSerializer(CameraShakePacket.class, CameraShakeSerializer_v428.INSTANCE)
            .updateSerializer(LevelSoundEvent1Packet.class, new LevelSoundEvent1Serializer_v291(SOUND_EVENTS))
            .updateSerializer(LevelSoundEvent2Packet.class, new LevelSoundEvent2Serializer_v313(SOUND_EVENTS))
            .updateSerializer(LevelSoundEventPacket.class, new LevelSoundEventSerializer_v332(SOUND_EVENTS))
            .updateSerializer(AvailableCommandsPacket.class, new AvailableCommandsSerializer_v388(COMMAND_PARAMS))
            .updateSerializer(LevelEventPacket.class, new LevelEventSerializer_v291(LEVEL_EVENTS))
            .updateSerializer(LevelEventGenericPacket.class, new LevelEventGenericSerializer_v361(LEVEL_EVENTS))
            .registerPacket(ClientboundDebugRendererPacket::new, ClientboundDebugRendererSerializer_v428.INSTANCE, 164, PacketRecipient.CLIENT)
            .build();
}
