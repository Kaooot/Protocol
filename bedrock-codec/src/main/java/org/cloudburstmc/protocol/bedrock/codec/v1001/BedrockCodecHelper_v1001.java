package org.cloudburstmc.protocol.bedrock.codec.v1001;

import io.netty.buffer.ByteBuf;
import org.cloudburstmc.protocol.bedrock.codec.ActorDataTypeMap;
import org.cloudburstmc.protocol.bedrock.codec.v975.BedrockCodecHelper_v975;
import org.cloudburstmc.protocol.bedrock.data.AbilitiesIndex;
import org.cloudburstmc.protocol.bedrock.data.inventory.ContainerEnumName;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.TextProcessingEventOrigin;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.action.ItemStackRequestActionType;
import org.cloudburstmc.protocol.bedrock.data.payload.configuration.PresenceConfiguration;
import org.cloudburstmc.protocol.bedrock.data.payload.inventory.transaction.InventoryAction;
import org.cloudburstmc.protocol.bedrock.data.payload.inventory.transaction.InventorySource;
import org.cloudburstmc.protocol.bedrock.data.payload.inventory.transaction.InventorySourceFlags;
import org.cloudburstmc.protocol.bedrock.data.payload.inventory.transaction.InventorySourceType;
import org.cloudburstmc.protocol.common.util.TypeMap;
import org.cloudburstmc.protocol.common.util.VarInts;

/**
 * @author Kaooot
 */
public class BedrockCodecHelper_v1001 extends BedrockCodecHelper_v975 {

    public BedrockCodecHelper_v1001(ActorDataTypeMap entityData, TypeMap<Class<?>> gameRulesTypes, TypeMap<ItemStackRequestActionType> stackRequestActionTypes, TypeMap<ContainerEnumName> containerSlotTypes, TypeMap<AbilitiesIndex> abilities, TypeMap<TextProcessingEventOrigin> textProcessingEventOrigins) {
        super(entityData, gameRulesTypes, stackRequestActionTypes, containerSlotTypes, abilities, textProcessingEventOrigins);
    }

    @Override
    protected void writeInventorySource(ByteBuf buffer, InventorySource source) {
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
    protected InventorySource readInventorySource(ByteBuf buffer) {
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
    protected void writeInventoryAction(ByteBuf buffer, InventoryAction action) {
        this.writeInventorySource(buffer, action.getSource());
        VarInts.writeUnsignedInt(buffer, action.getSlot());
        this.writeNetworkItemStackDescriptor(buffer, action.getFromItem());
        this.writeNetworkItemStackDescriptor(buffer, action.getToItem());
    }

    @Override
    protected InventoryAction readInventoryAction(ByteBuf buffer) {
        final InventoryAction action = new InventoryAction();
        action.setSource(this.readInventorySource(buffer));
        action.setSlot(VarInts.readUnsignedInt(buffer));
        action.setFromItem(this.readNetworkItemStackDescriptor(buffer));
        action.setToItem(this.readNetworkItemStackDescriptor(buffer));
        return action;
    }

    @Override
    public void writePresenceConfiguration(ByteBuf buffer, PresenceConfiguration configuration) {
        this.writeOptionalNull(buffer, configuration.getExperienceName(), this::writeString);
        this.writeOptionalNull(buffer, configuration.getWorldName(), this::writeString);
        this.writeString(buffer, configuration.getRichPresenceId());
    }

    @Override
    public PresenceConfiguration readPresenceConfiguration(ByteBuf buffer) {
        final PresenceConfiguration configuration = new PresenceConfiguration();
        configuration.setExperienceName(this.readOptional(buffer, null, this::readString));
        configuration.setWorldName(this.readOptional(buffer, null, this::readString));
        configuration.setRichPresenceId(this.readString(buffer));
        return configuration;
    }
}