package org.cloudburstmc.protocol.bedrock.codec.v2259;

import io.netty.buffer.ByteBuf;
import org.cloudburstmc.protocol.bedrock.codec.ActorDataTypeMap;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.v2225.BedrockCodecHelper_v2225;
import org.cloudburstmc.protocol.bedrock.data.ability.AbilitiesIndex;
import org.cloudburstmc.protocol.bedrock.data.actor.passenger.PassengerOfBlockArguments;
import org.cloudburstmc.protocol.bedrock.data.connection.ManagedChatJoinInfo;
import org.cloudburstmc.protocol.bedrock.data.connection.ServerConfig;
import org.cloudburstmc.protocol.bedrock.data.datastore.DataStoreUpdate;
import org.cloudburstmc.protocol.bedrock.data.inventory.ContainerEnumName;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.ItemStackRequestActionType;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.action.ItemStackRequestCraftDiscPressAction;
import org.cloudburstmc.protocol.bedrock.data.sound.PlaySoundPacketPayload;
import org.cloudburstmc.protocol.bedrock.data.sound.SoundAttachOptions;
import org.cloudburstmc.protocol.bedrock.data.text.TextProcessingEventOrigin;
import org.cloudburstmc.protocol.common.util.TypeMap;
import org.cloudburstmc.protocol.common.util.VarInts;

public class BedrockCodecHelper_v2259 extends BedrockCodecHelper_v2225 {

    public BedrockCodecHelper_v2259(ActorDataTypeMap entityData, TypeMap<Class<?>> gameRulesTypes, TypeMap<ItemStackRequestActionType> stackRequestActionTypes, TypeMap<ContainerEnumName> containerEnumNames, TypeMap<AbilitiesIndex> abilities, TypeMap<TextProcessingEventOrigin> textProcessingEventOrigins) {
        super(entityData, gameRulesTypes, stackRequestActionTypes, containerEnumNames, abilities, textProcessingEventOrigins);
    }

    @Override
    protected void writeItemStackRequestActionBody(ByteBuf buffer, ItemStackRequestActionType type, Object action) {
        if (type == ItemStackRequestActionType.CRAFT_DISC_PRESS) {
            this.writeItemStackRequestCraftDiscPressAction(buffer, this, type, (ItemStackRequestCraftDiscPressAction) action);
        } else {
            super.writeItemStackRequestActionBody(buffer, type, action);
        }
    }

    @Override
    protected Object readItemStackRequestActionBody(ByteBuf buffer, ItemStackRequestActionType type) {
        if (type == ItemStackRequestActionType.CRAFT_DISC_PRESS) {
            return this.readItemStackRequestCraftDiscPressAction(buffer, this, type);
        }
        return super.readItemStackRequestActionBody(buffer, type);
    }

    @Override
    public void writeServerConfig(ByteBuf buffer, ServerConfig config) {
        super.writeServerConfig(buffer, config);
        this.writeOptionalNull(buffer, config.getManagedChat(), this::writeManagedChatJoinInfo);
    }

    @Override
    public ServerConfig readServerConfig(ByteBuf buffer) {
        final ServerConfig config = super.readServerConfig(buffer);
        config.setManagedChat(this.readOptional(buffer, null, this::readManagedChatJoinInfo));
        return config;
    }

    protected void writeManagedChatJoinInfo(ByteBuf buffer, ManagedChatJoinInfo info) {
        buffer.writeBoolean(info.isEnabled());
    }

    protected ManagedChatJoinInfo readManagedChatJoinInfo(ByteBuf buffer) {
        final ManagedChatJoinInfo info = new ManagedChatJoinInfo();
        info.setEnabled(buffer.readBoolean());
        return info;
    }

    protected void writeItemStackRequestCraftDiscPressAction(ByteBuf buffer, BedrockCodecHelper helper, ItemStackRequestActionType type, ItemStackRequestCraftDiscPressAction action) {
        helper.writeString(buffer, action.getTrackSoundEventId());
        buffer.writeByte(action.getNumCrafts());
    }

    protected ItemStackRequestCraftDiscPressAction readItemStackRequestCraftDiscPressAction(ByteBuf buffer, BedrockCodecHelper helper, ItemStackRequestActionType type) {
        final ItemStackRequestCraftDiscPressAction action = new ItemStackRequestCraftDiscPressAction();
        action.setTrackSoundEventId(helper.readString(buffer));
        action.setNumCrafts(buffer.readUnsignedByte());
        return action;
    }

    @Override
    public void writePassengerOfBlockArguments(ByteBuf buffer, PassengerOfBlockArguments arguments) {
        super.writePassengerOfBlockArguments(buffer, arguments);
        buffer.writeLongLE(arguments.getBlockType());
        this.writeUuid(buffer, arguments.getPackUUID());
    }

    @Override
    public PassengerOfBlockArguments readPassengerOfBlockArguments(ByteBuf buffer) {
        final PassengerOfBlockArguments arguments = super.readPassengerOfBlockArguments(buffer);
        arguments.setBlockType(buffer.readLongLE());
        arguments.setPackUUID(this.readUuid(buffer));
        return arguments;
    }

    @Override
    public void writeDataStoreUpdate(ByteBuf buffer, DataStoreUpdate update) {
        this.writeString(buffer, update.getVariableName());
        this.writeDataStoreUpdateData(buffer, update, update.getData());
        buffer.writeIntLE(update.getUpdateCount());
    }

    @Override
    public DataStoreUpdate readDataStoreUpdate(ByteBuf buffer) {
        final DataStoreUpdate update = new DataStoreUpdate();
        update.setVariableName(this.readString(buffer));
        update.setData(this.readDataStoreUpdateData(buffer, update));
        update.setUpdateCount(buffer.readIntLE());
        return update;
    }

    @Override
    public void writePlaySoundPacketPayload(ByteBuf buffer, PlaySoundPacketPayload payload) {
        super.writePlaySoundPacketPayload(buffer, payload);
        this.writeOptionalNull(buffer, payload.getStartDelaySeconds(), ByteBuf::writeFloatLE);
        this.writeOptionalNull(buffer, payload.getAttachOptions(), this::writeSoundAttachOptions);
    }

    @Override
    public PlaySoundPacketPayload readPlaySoundPacketPayload(ByteBuf buffer) {
        final PlaySoundPacketPayload payload = super.readPlaySoundPacketPayload(buffer);
        payload.setStartDelaySeconds(this.readOptional(buffer, null, ByteBuf::readFloatLE));
        payload.setAttachOptions(this.readOptional(buffer, null, this::readSoundAttachOptions));
        return payload;
    }

    protected void writeSoundAttachOptions(ByteBuf buffer, SoundAttachOptions options) {
        VarInts.writeUnsignedLong(buffer, options.getEntityToAttachToID());
    }

    protected SoundAttachOptions readSoundAttachOptions(ByteBuf buffer) {
        final SoundAttachOptions options = new SoundAttachOptions();
        options.setEntityToAttachToID(VarInts.readUnsignedLong(buffer));
        return options;
    }
}