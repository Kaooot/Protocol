package org.cloudburstmc.protocol.bedrock.codec.v407;

import org.cloudburstmc.protocol.bedrock.codec.BedrockCodec;
import org.cloudburstmc.protocol.bedrock.codec.ActorDataTypeMap;
import org.cloudburstmc.protocol.bedrock.codec.v291.serializer.ActorEventSerializer_v291;
import org.cloudburstmc.protocol.bedrock.codec.v291.serializer.LevelEventSerializer_v291;
import org.cloudburstmc.protocol.bedrock.codec.v291.serializer.LevelSoundEvent1Serializer_v291;
import org.cloudburstmc.protocol.bedrock.codec.v313.serializer.LevelSoundEvent2Serializer_v313;
import org.cloudburstmc.protocol.bedrock.codec.v332.serializer.LevelSoundEventSerializer_v332;
import org.cloudburstmc.protocol.bedrock.codec.v361.serializer.LevelEventGenericSerializer_v361;
import org.cloudburstmc.protocol.bedrock.codec.v390.Bedrock_v390;
import org.cloudburstmc.protocol.bedrock.codec.v407.serializer.*;
import org.cloudburstmc.protocol.bedrock.data.*;
import org.cloudburstmc.protocol.bedrock.data.actor.ActorDataFormat;
import org.cloudburstmc.protocol.bedrock.data.actor.ActorDataTypes;
import org.cloudburstmc.protocol.bedrock.data.actor.ActorEvent;
import org.cloudburstmc.protocol.bedrock.data.actor.ActorFlags;
import org.cloudburstmc.protocol.bedrock.data.inventory.ContainerEnumName;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.action.ItemStackRequestActionType;
import org.cloudburstmc.protocol.bedrock.packet.*;
import org.cloudburstmc.protocol.bedrock.transformer.BooleanTransformer;
import org.cloudburstmc.protocol.bedrock.transformer.FlagTransformer;
import org.cloudburstmc.protocol.bedrock.transformer.TypeMapTransformer;
import org.cloudburstmc.protocol.common.util.TypeMap;

public class Bedrock_v407 extends Bedrock_v390 {

    protected static final TypeMap<ActorFlags> ACTOR_FLAGS = Bedrock_v390.ACTOR_FLAGS.toBuilder()
            .shift(86, 1)
            .insert(86, ActorFlags.IS_AVOIDING_BLOCK)
            .shift(93, 2)
            .insert(93, ActorFlags.ADMIRING)
            .insert(94, ActorFlags.CELEBRATING_SPECIAL)
            .build();

    protected static final TypeMap<ParticleType> PARTICLE_TYPES = Bedrock_v390.PARTICLE_TYPES.toBuilder()
            .insert(68, ParticleType.BLUE_FLAME)
            .insert(69, ParticleType.SOUL)
            .insert(70, ParticleType.OBSIDIAN_TEAR)
            .build();

    protected static final ActorDataTypeMap ACTOR_DATA = Bedrock_v390.ACTOR_DATA.toBuilder()
            .update(ActorDataTypes.FLAGS, new FlagTransformer(ACTOR_FLAGS, 0))
            .update(ActorDataTypes.FLAGS_2, new FlagTransformer(ACTOR_FLAGS, 1))
            .update(ActorDataTypes.DATA_PARTICLE, new TypeMapTransformer<>(PARTICLE_TYPES))
            .insert(ActorDataTypes.LOW_TIER_CURED_TRADE_DISCOUNT, 113, ActorDataFormat.INT)
            .insert(ActorDataTypes.HIGH_TIER_CURED_TRADE_DISCOUNT, 114, ActorDataFormat.INT)
            .insert(ActorDataTypes.NEARBY_CURED_TRADE_DISCOUNT, 115, ActorDataFormat.INT)
            .insert(ActorDataTypes.NEARBY_CURED_DISCOUNT_TIME_STAMP, 116, ActorDataFormat.INT)
            .insert(ActorDataTypes.HITBOX, 117, ActorDataFormat.NBT)
            .insert(ActorDataTypes.IS_BUOYANT, 118, ActorDataFormat.BYTE, BooleanTransformer.INSTANCE)
            .insert(ActorDataTypes.BUOYANCY_DATA, 119, ActorDataFormat.STRING)
            .build();

    protected static final TypeMap<ActorEvent> ACTOR_EVENTS = Bedrock_v390.ACTOR_EVENTS.toBuilder()
            .insert(75, ActorEvent.LANDED_ON_GROUND)
            .build();

    protected static final TypeMap<LevelEventType> LEVEL_EVENTS = Bedrock_v390.LEVEL_EVENTS.toBuilder()
            .insert(LEVEL_EVENT_SOUND + 50, LevelEvent.SOUND_CAMERA)
            .insert(LEVEL_EVENT_BLOCK + 100, LevelEvent.BLOCK_START_BREAK)
            .insert(LEVEL_EVENT_BLOCK + 101, LevelEvent.BLOCK_STOP_BREAK)
            .insert(LEVEL_EVENT_BLOCK + 102, LevelEvent.BLOCK_UPDATE_BREAK)
            .insert(4000, LevelEvent.SET_DATA)
            .insert(9800, LevelEvent.ALL_PLAYERS_SLEEPING)
            .insert(LEVEL_EVENT_PARTICLE_TYPE, PARTICLE_TYPES)
            .build();

    protected static final TypeMap<SoundEvent> SOUND_EVENTS = Bedrock_v390.SOUND_EVENTS.toBuilder()
            .replace(287, SoundEvent.JUMP_PREVENT)
            .insert(288, SoundEvent.AMBIENT_POLLINATE)
            .insert(289, SoundEvent.BEEHIVE_DRIP)
            .insert(290, SoundEvent.BEEHIVE_ENTER)
            .insert(291, SoundEvent.BEEHIVE_EXIT)
            .insert(292, SoundEvent.BEEHIVE_WORK)
            .insert(293, SoundEvent.BEEHIVE_SHEAR)
            .insert(294, SoundEvent.HONEYBOTTLE_DRINK)
            .insert(295, SoundEvent.AMBIENT_CAVE)
            .insert(296, SoundEvent.RETREAT)
            .insert(297, SoundEvent.CONVERT_TO_ZOMBIFIED)
            .insert(298, SoundEvent.ADMIRE)
            .insert(299, SoundEvent.STEP_LAVA)
            .insert(300, SoundEvent.TEMPT)
            .insert(301, SoundEvent.PANIC)
            .insert(302, SoundEvent.ANGRY)
            .insert(303, SoundEvent.AMBIENT_WARPED_FOREST)
            .insert(304, SoundEvent.AMBIENT_SOULSAND_VALLEY)
            .insert(305, SoundEvent.AMBIENT_NETHER_WASTES)
            .insert(306, SoundEvent.AMBIENT_BASALT_DELTAS)
            .insert(307, SoundEvent.AMBIENT_CRIMSON_FOREST)
            .insert(308, SoundEvent.RESPAWN_ANCHOR_CHARGE)
            .insert(309, SoundEvent.RESPAWN_ANCHOR_DEPLETE)
            .insert(310, SoundEvent.RESPAWN_ANCHOR_SET_SPAWN)
            .insert(311, SoundEvent.RESPAWN_ANCHOR_AMBIENT)
            .insert(312, SoundEvent.SOUL_ESCAPE_QUIET)
            .insert(313, SoundEvent.SOUL_ESCAPE_LOUD)
            .insert(314, SoundEvent.RECORD_PIGSTEP)
            .insert(315, SoundEvent.LINK_COMPASS_TO_LODESTONE)
            .insert(316, SoundEvent.USE_SMITHING_TABLE)
            .insert(317, SoundEvent.UNDEFINED)
            .build();

    protected static final TypeMap<ItemStackRequestActionType> ITEM_STACK_REQUEST_TYPES = TypeMap.builder(ItemStackRequestActionType.class)
            .insert(0, ItemStackRequestActionType.TAKE)
            .insert(1, ItemStackRequestActionType.PLACE)
            .insert(2, ItemStackRequestActionType.SWAP)
            .insert(3, ItemStackRequestActionType.DROP)
            .insert(4, ItemStackRequestActionType.DESTROY)
            .insert(5, ItemStackRequestActionType.CONSUME)
            .insert(6, ItemStackRequestActionType.CREATE)
            .insert(7, ItemStackRequestActionType.LAB_TABLE_COMBINE)
            .insert(8, ItemStackRequestActionType.BEACON_PAYMENT)
            .insert(9, ItemStackRequestActionType.CRAFT_RECIPE)
            .insert(10, ItemStackRequestActionType.CRAFT_RECIPE_AUTO)
            .insert(11, ItemStackRequestActionType.CRAFT_CREATIVE)
            .insert(12, ItemStackRequestActionType.CRAFT_NON_IMPLEMENTED_DEPRECATED)
            .insert(13, ItemStackRequestActionType.CRAFT_RESULTS_DEPRECATED)
            .build();

    protected static final TypeMap<ContainerEnumName> CONTAINER_SLOT_TYPES = TypeMap.builder(ContainerEnumName.class)
            .insert(0, ContainerEnumName.ANVIL_INPUT_CONTAINER)
            .insert(1, ContainerEnumName.ANVIL_MATERIAL_CONTAINER)
            .insert(2, ContainerEnumName.ANVIL_RESULT_PREVIEW_CONTAINER)
            .insert(3, ContainerEnumName.SMITHING_TABLE_INPUT_CONTAINER)
            .insert(4, ContainerEnumName.SMITHING_TABLE_MATERIAL_CONTAINER)
            .insert(5, ContainerEnumName.SMITHING_TABLE_RESULT_PREVIEW_CONTAINER)
            .insert(6, ContainerEnumName.ARMOR_CONTAINER)
            .insert(7, ContainerEnumName.LEVEL_ENTITY_CONTAINER)
            .insert(8, ContainerEnumName.BEACON_PAYMENT_CONTAINER)
            .insert(9, ContainerEnumName.BREWING_STAND_INPUT_CONTAINER)
            .insert(10, ContainerEnumName.BREWING_STAND_RESULT_CONTAINER)
            .insert(11, ContainerEnumName.BREWING_STAND_FUEL_CONTAINER)
            .insert(12, ContainerEnumName.COMBINED_HOTBAR_AND_INVENTORY_CONTAINER)
            .insert(13, ContainerEnumName.CRAFTING_INPUT_CONTAINER)
            .insert(14, ContainerEnumName.CRAFTING_OUTPUT_PREVIEW_CONTAINER)
            .insert(15, ContainerEnumName.RECIPE_CONSTRUCTION_CONTAINER)
            .insert(16, ContainerEnumName.RECIPE_NATURE_CONTAINER)
            .insert(17, ContainerEnumName.RECIPE_ITEMS_CONTAINER)
            .insert(18, ContainerEnumName.RECIPE_SEARCH_CONTAINER)
            .insert(19, ContainerEnumName.RECIPE_SEARCH_BAR_CONTAINER)
            .insert(20, ContainerEnumName.RECIPE_EQUIPMENT_CONTAINER)
            .insert(21, ContainerEnumName.ENCHANTING_INPUT_CONTAINER)
            .insert(22, ContainerEnumName.ENCHANTING_MATERIAL_CONTAINER)
            .insert(23, ContainerEnumName.FURNACE_FUEL_CONTAINER)
            .insert(24, ContainerEnumName.FURNACE_INGREDIENT_CONTAINER)
            .insert(25, ContainerEnumName.FURNACE_RESULT_CONTAINER)
            .insert(26, ContainerEnumName.HORSE_EQUIP_CONTAINER)
            .insert(27, ContainerEnumName.HOTBAR_CONTAINER)
            .insert(28, ContainerEnumName.INVENTORY_CONTAINER)
            .insert(29, ContainerEnumName.SHULKER_BOX_CONTAINER)
            .insert(30, ContainerEnumName.TRADE_INGREDIENT1_CONTAINER)
            .insert(31, ContainerEnumName.TRADE_INGREDIENT2_CONTAINER)
            .insert(32, ContainerEnumName.TRADE_RESULT_PREVIEW_CONTAINER)
            .insert(33, ContainerEnumName.OFFHAND_CONTAINER)
            .insert(34, ContainerEnumName.COMPOUND_CREATOR_INPUT)
            .insert(35, ContainerEnumName.COMPOUND_CREATOR_OUTPUT_PREVIEW)
            .insert(36, ContainerEnumName.ELEMENT_CONSTRUCTOR_OUTPUT_PREVIEW)
            .insert(37, ContainerEnumName.MATERIAL_REDUCER_INPUT)
            .insert(38, ContainerEnumName.MATERIAL_REDUCER_OUTPUT)
            .insert(39, ContainerEnumName.LAB_TABLE_INPUT)
            .insert(40, ContainerEnumName.LOOM_INPUT_CONTAINER)
            .insert(41, ContainerEnumName.LOOM_DYE_CONTAINER)
            .insert(42, ContainerEnumName.LOOM_MATERIAL_CONTAINER)
            .insert(43, ContainerEnumName.LOOM_RESULT_PREVIEW_CONTAINER)
            .insert(44, ContainerEnumName.BLAST_FURNACE_INGREDIENT_CONTAINER)
            .insert(45, ContainerEnumName.SMOKER_INGREDIENT_CONTAINER)
            .insert(46, ContainerEnumName.TRADE2_INGREDIENT1_CONTAINER)
            .insert(47, ContainerEnumName.TRADE2_INGREDIENT2_CONTAINER)
            .insert(48, ContainerEnumName.TRADE2_RESULT_PREVIEW_CONTAINER)
            .insert(49, ContainerEnumName.GRINDSTONE_INPUT_CONTAINER)
            .insert(50, ContainerEnumName.GRINDSTONE_ADDITIONAL_CONTAINER)
            .insert(51, ContainerEnumName.GRINDSTONE_RESULT_PREVIEW_CONTAINER)
            .insert(52, ContainerEnumName.STONECUTTER_INPUT_CONTAINER)
            .insert(53, ContainerEnumName.STONECUTTER_RESULT_PREVIEW_CONTAINER)
            .insert(54, ContainerEnumName.CARTOGRAPHY_INPUT_CONTAINER)
            .insert(55, ContainerEnumName.CARTOGRAPHY_ADDITIONAL_CONTAINER)
            .insert(56, ContainerEnumName.CARTOGRAPHY_RESULT_PREVIEW_CONTAINER)
            .insert(57, ContainerEnumName.BARREL_CONTAINER)
            .insert(58, ContainerEnumName.CURSOR_CONTAINER)
            .insert(59, ContainerEnumName.CREATED_OUTPUT_CONTAINER)
            .build();

    public static BedrockCodec CODEC = Bedrock_v390.CODEC.toBuilder()
            .protocolVersion(407)
            .minecraftVersion("1.16.0")
            .helper(() -> new BedrockCodecHelper_v407(ACTOR_DATA, GAME_RULE_TYPES, ITEM_STACK_REQUEST_TYPES, CONTAINER_SLOT_TYPES))
            .deregisterPacket(VideoStreamConnectPacket.class)
            .updateSerializer(StartGamePacket.class, StartGameSerializer_v407.INSTANCE)
            .updateSerializer(InventoryTransactionPacket.class, InventoryTransactionSerializer_v407.INSTANCE)
            .updateSerializer(HurtArmorPacket.class, HurtArmorSerializer_v407.INSTANCE)
            .updateSerializer(SetSpawnPositionPacket.class, SetSpawnPositionSerializer_v407.INSTANCE)
            .updateSerializer(InventoryContentPacket.class, InventoryContentSerializer_v407.INSTANCE)
            .updateSerializer(InventorySlotPacket.class, InventorySlotSerializer_v407.INSTANCE)
            .updateSerializer(CraftingDataPacket.class, CraftingDataSerializer_v407.INSTANCE)
            .updateSerializer(LevelSoundEvent1Packet.class, new LevelSoundEvent1Serializer_v291(SOUND_EVENTS))
            .updateSerializer(LevelSoundEvent2Packet.class, new LevelSoundEvent2Serializer_v313(SOUND_EVENTS))
            .updateSerializer(LevelSoundEventPacket.class, new LevelSoundEventSerializer_v332(SOUND_EVENTS))
            .updateSerializer(ActorEventPacket.class, new ActorEventSerializer_v291(ACTOR_EVENTS))
            .updateSerializer(LevelEventPacket.class, new LevelEventSerializer_v291(LEVEL_EVENTS))
            .updateSerializer(LevelEventGenericPacket.class, new LevelEventGenericSerializer_v361(LEVEL_EVENTS))
            .updateSerializer(EducationSettingsPacket.class, EducationSettingsSerializer_v407.INSTANCE)
            .registerPacket(CreativeContentPacket::new, CreativeContentSerializer_v407.INSTANCE, 145, PacketRecipient.CLIENT)
            .registerPacket(PlayerEnchantOptionsPacket::new, PlayerEnchantOptionsSerializer_v407.INSTANCE, 146, PacketRecipient.CLIENT)
            .registerPacket(ItemStackRequestPacket::new, ItemStackRequestSerializer_v407.INSTANCE, 147, PacketRecipient.SERVER)
            .registerPacket(ItemStackResponsePacket::new, ItemStackResponseSerializer_v407.INSTANCE, 148, PacketRecipient.CLIENT)
            .registerPacket(PlayerArmorDamagePacket::new, PlayerArmorDamageSerializer_v407.INSTANCE, 149, PacketRecipient.CLIENT)
            .registerPacket(CodeBuilderPacket::new, CodeBuilderSerializer_v407.INSTANCE, 150, PacketRecipient.CLIENT)
            .registerPacket(UpdatePlayerGameTypePacket::new, UpdatePlayerGameTypeSerializer_v407.INSTANCE, 151, PacketRecipient.CLIENT)
            .registerPacket(EmoteListPacket::new, EmoteListSerializer_v407.INSTANCE, 152, PacketRecipient.BOTH)
            .registerPacket(PositionTrackingDBServerBroadcastPacket::new, PositionTrackingDBServerBroadcastSerializer_v407.INSTANCE, 153, PacketRecipient.CLIENT)
            .registerPacket(PositionTrackingDBClientRequestPacket::new, PositionTrackingDBClientRequestSerializer_v407.INSTANCE, 154, PacketRecipient.SERVER)
            .registerPacket(DebugInfoPacket::new, DebugInfoSerializer_v407.INSTANCE, 155, PacketRecipient.BOTH)
            .registerPacket(PacketViolationWarningPacket::new, PacketViolationWarningSerializer_v407.INSTANCE, 156, PacketRecipient.SERVER)
            .build();
}
