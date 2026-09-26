package org.cloudburstmc.protocol.bedrock.codec.v1001;

import io.netty.buffer.ByteBuf;
import org.cloudburstmc.protocol.bedrock.codec.ActorDataTypeMap;
import org.cloudburstmc.protocol.bedrock.codec.v975.BedrockCodecHelper_v975;
import org.cloudburstmc.protocol.bedrock.data.ability.AbilitiesIndex;
import org.cloudburstmc.protocol.bedrock.data.connection.PresenceConfig;
import org.cloudburstmc.protocol.bedrock.data.inventory.ContainerEnumName;
import org.cloudburstmc.protocol.bedrock.data.inventory.InventorySource;
import org.cloudburstmc.protocol.bedrock.data.inventory.InventorySourceFlags;
import org.cloudburstmc.protocol.bedrock.data.inventory.InventorySourceType;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.ItemStackRequestActionType;
import org.cloudburstmc.protocol.bedrock.data.inventory.transaction.*;
import org.cloudburstmc.protocol.bedrock.data.text.TextProcessingEventOrigin;
import org.cloudburstmc.protocol.common.util.TypeMap;
import org.cloudburstmc.protocol.common.util.VarInts;

public class BedrockCodecHelper_v1001 extends BedrockCodecHelper_v975 {

    public BedrockCodecHelper_v1001(ActorDataTypeMap entityData, TypeMap<Class<?>> gameRulesTypes, TypeMap<ItemStackRequestActionType> stackRequestActionTypes,
                                    TypeMap<ContainerEnumName> containerEnumNames, TypeMap<AbilitiesIndex> abilities, TypeMap<TextProcessingEventOrigin> textProcessingEventOrigins) {
        super(entityData, gameRulesTypes, stackRequestActionTypes, containerEnumNames, abilities, textProcessingEventOrigins);
    }

    @Override
    public void writePresenceConfig(ByteBuf buffer, PresenceConfig config) {
        this.writeOptionalNull(buffer, config.getExperienceName(), this::writeString);
        this.writeOptionalNull(buffer, config.getWorldName(), this::writeString);
        this.writeString(buffer, config.getRichPresenceId());
    }

    @Override
    public PresenceConfig readPresenceConfig(ByteBuf buffer) {
        final PresenceConfig config = new PresenceConfig();
        config.setExperienceName(this.readOptional(buffer, null, this::readString));
        config.setWorldName(this.readOptional(buffer, null, this::readString));
        config.setRichPresenceId(this.readStringMaxLen(buffer, 50));
        return config;
    }

    @Override
    public void writeInventorySource(ByteBuf buffer, InventorySource source) {
        VarInts.writeUnsignedInt(buffer, source.getSourceType().ordinal());
        final boolean hasContainer = source.getSourceType().equals(InventorySourceType.CONTAINER_INVENTORY) ||
                source.getSourceType().equals(InventorySourceType.NON_IMPLEMENTED_FEATURE_TODO);
        buffer.writeBoolean(hasContainer);
        if (hasContainer) {
            this.writeOptionalNull(buffer, source.getContainerID(), ByteBuf::writeByte);
        }
        final boolean hasBitFlags = source.getBitFlags() != null;
        buffer.writeBoolean(hasBitFlags);
        if (hasBitFlags) {
            this.writeOptionalNull(buffer, source.getBitFlags(),
                    (buf, bitFlags) -> VarInts.writeUnsignedInt(buf, bitFlags.ordinal()));
        }
    }

    @Override
    public InventorySource readInventorySource(ByteBuf buffer) {
        final InventorySource source = new InventorySource();
        source.setSourceType(InventorySourceType.from(VarInts.readUnsignedInt(buffer)));
        if (buffer.readBoolean()) {
            source.setContainerID(this.readOptional(buffer, null, (buf, helper) -> (int) buf.readByte()));
        }
        if (buffer.readBoolean()) {
            source.setBitFlags(this.readOptional(buffer, null, (buf, helper) -> InventorySourceFlags.from(VarInts.readUnsignedInt(buf))));
        }
        return source;
    }

    @Override
    public void writeItemUseInventoryTransaction(ByteBuf buffer, ItemUseInventoryTransaction transaction) {
        VarInts.writeInt(buffer, transaction.getActionType().ordinal());
        buffer.writeByte(transaction.getTriggerType().ordinal());
        this.writeVector3i(buffer, transaction.getPosition());
        buffer.writeByte(transaction.getFace());
        VarInts.writeInt(buffer, transaction.getSlot());
        this.writeNetworkItemStackDescriptor(buffer, transaction.getItem());
        this.writeVector3f(buffer, transaction.getFromPosition());
        this.writeVector3f(buffer, transaction.getClickPosition());
        VarInts.writeUnsignedInt(buffer, transaction.getTargetBlockId().getRuntimeId());
        buffer.writeByte(transaction.getClientInteractPrediction().ordinal());
        buffer.writeByte(transaction.getClientCooldownState().ordinal());
    }

    @Override
    public ItemUseInventoryTransaction readItemUseInventoryTransaction(ByteBuf buffer) {
        final ItemUseInventoryTransaction transaction = new ItemUseInventoryTransaction();
        transaction.setActionType(ItemUseActionType.from(VarInts.readInt(buffer)));
        transaction.setTriggerType(ItemUseTriggerType.from(buffer.readUnsignedByte()));
        transaction.setPosition(this.readVector3i(buffer));
        transaction.setFace(buffer.readByte());
        transaction.setSlot(VarInts.readInt(buffer));
        transaction.setItem(this.readNetworkItemStackDescriptor(buffer));
        transaction.setFromPosition(this.readVector3f(buffer));
        transaction.setClickPosition(this.readVector3f(buffer));
        transaction.setTargetBlockId(this.getBlockDefinitions().getDefinition(VarInts.readUnsignedInt(buffer)));
        transaction.setClientInteractPrediction(ItemUsePredictedResult.from(buffer.readUnsignedByte()));
        transaction.setClientCooldownState(ItemUseClientCooldownState.from(buffer.readUnsignedByte()));
        return transaction;
    }
}
