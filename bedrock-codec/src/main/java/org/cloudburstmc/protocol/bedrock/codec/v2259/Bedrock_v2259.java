package org.cloudburstmc.protocol.bedrock.codec.v2259;

import org.cloudburstmc.protocol.bedrock.codec.ActorDataTypeMap;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodec;
import org.cloudburstmc.protocol.bedrock.codec.v1001.serializer.LevelSoundEventSerializer_v1001;
import org.cloudburstmc.protocol.bedrock.codec.v2225.Bedrock_v2225;
import org.cloudburstmc.protocol.bedrock.codec.v2259.serializer.*;
import org.cloudburstmc.protocol.bedrock.data.PacketRecipient;
import org.cloudburstmc.protocol.bedrock.data.actor.ActorDataFormat;
import org.cloudburstmc.protocol.bedrock.data.actor.ActorDataTypes;
import org.cloudburstmc.protocol.bedrock.data.inventory.ContainerEnumName;
import org.cloudburstmc.protocol.bedrock.data.sound.LevelSoundEvent;
import org.cloudburstmc.protocol.bedrock.packet.*;
import org.cloudburstmc.protocol.bedrock.transformer.BooleanTransformer;
import org.cloudburstmc.protocol.common.util.TypeMap;

public class Bedrock_v2259 extends Bedrock_v2225 {

    protected static final TypeMap<LevelSoundEvent> SOUND_EVENTS = Bedrock_v2225.SOUND_EVENTS
            .toBuilder()
            .replace(614, LevelSoundEvent.CONVERT_TO_FROSTBITE)
            .replace(615, LevelSoundEvent.CONVERT_TO_ZOMBIE)
            .insert(616, LevelSoundEvent.CRACK)
            .insert(617, LevelSoundEvent.ICE_BALL_THROW)
            .insert(618, LevelSoundEvent.RECORD_FLURRY)
            .insert(619, LevelSoundEvent.IMITATE_FROSTBITE)
            .insert(620, LevelSoundEvent.UNDEFINED)
            .build();

    protected static final TypeMap<ContainerEnumName> CONTAINER_SLOT_TYPES = Bedrock_v2225.CONTAINER_SLOT_TYPES
            .toBuilder()
            .insert(71, ContainerEnumName.VIRTUAL_CONTAINER)
            .build();

    protected static final ActorDataTypeMap ENTITY_DATA = Bedrock_v2225.ENTITY_DATA.toBuilder()
            .insert(ActorDataTypes.INTERACTION_RESULT, 141, ActorDataFormat.BYTE, BooleanTransformer.INSTANCE)
            .insert(ActorDataTypes.COUNT, 142, ActorDataFormat.BYTE)
            .build();

    public static final BedrockCodec CODEC = Bedrock_v2225.CODEC.toBuilder()
            .protocolVersion(2259)
            .minecraftVersion("1.26.70")
            .helper(() -> new BedrockCodecHelper_v2259(ENTITY_DATA, GAME_RULE_TYPES, ITEM_STACK_REQUEST_TYPES, CONTAINER_SLOT_TYPES, PLAYER_ABILITIES, TEXT_PROCESSING_ORIGINS))
            .updateSerializer(ClientboundAttributeLayerSyncPacket.class, ClientboundAttributeLayerSyncSerializer_v2259.INSTANCE)
            .updateSerializer(ClientboundDataStorePacket.class, ClientboundDataStoreSerializer_v2259.INSTANCE)
            .updateSerializer(CommandBlockUpdatePacket.class, CommandBlockUpdateSerializer_v2259.INSTANCE)
            .updateSerializer(CommandOutputPacket.class, CommandOutputSerializer_v2259.INSTANCE)
            .updateSerializer(NetworkSettingsPacket.class, NetworkSettingsSerializer_v2259.INSTANCE)
            .updateSerializer(LevelSoundEventPacket.class, new LevelSoundEventSerializer_v1001(SOUND_EVENTS))
            .updateSerializer(PlayerAuthInputPacket.class, new PlayerAuthInputSerializer_v2259(PLAYER_ACTION_TYPES))
            .updateSerializer(ResourcePackStackPacket.class, ResourcePackStackSerializer_v2259.INSTANCE)
            .updateSerializer(StructureBlockUpdatePacket.class, StructureBlockUpdateSerializer_v2259.INSTANCE)
            .updateSerializer(TextPacket.class, TextSerializer_v2259.INSTANCE)
            .updateSerializer(TransferPacket.class, TransferSerializer_v2259.INSTANCE)
            .deregisterPacket(AddVolumeEntityPacket.class)
            .deregisterPacket(RemoveVolumeEntityPacket.class)
            .registerPacket(ChatPacket::new, ChatSerializer_v2259.INSTANCE, 361, PacketRecipient.BOTH)
            .registerPacket(ServerboundRequestOnlineMusicPlaybackPacket::new, ServerboundRequestOnlineMusicPlaybackSerializer_v2259.INSTANCE, 362, PacketRecipient.SERVER)
            .registerPacket(ClientboundOnlineMusicPlaybackResponsePacket::new, ClientboundOnlineMusicPlaybackResponseSerializer_v2259.INSTANCE, 363, PacketRecipient.CLIENT)
            .registerPacket(PlayerToggleArmorVisibilityRequestPacket::new, PlayerToggleArmorVisibilityRequestSerializer_v2259.INSTANCE, 364, PacketRecipient.SERVER)
            .registerPacket(ClientboundMusicPlaybackFailurePacket::new, ClientboundMusicPlaybackFailureSerializer_v2259.INSTANCE, 365, PacketRecipient.CLIENT)
            .registerPacket(VirtualContainerOpenPacket::new, VirtualContainerOpenSerializer_v2259.INSTANCE, 366, PacketRecipient.CLIENT)
            .registerPacket(VirtualContainerClosePacket::new, VirtualContainerCloseSerializer_v2259.INSTANCE, 367, PacketRecipient.SERVER)
            .build();
}