package org.cloudburstmc.protocol.bedrock.codec.v998;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import org.cloudburstmc.protocol.bedrock.codec.ActorDataTypeMap;
import org.cloudburstmc.protocol.bedrock.codec.v975.BedrockCodecHelper_v975;
import org.cloudburstmc.protocol.bedrock.data.AbilitiesIndex;
import org.cloudburstmc.protocol.bedrock.data.inventory.ContainerEnumName;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.TextProcessingEventOrigin;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.action.ItemStackRequestActionType;
import org.cloudburstmc.protocol.bedrock.data.payload.inventory.transaction.InventoryAction;
import org.cloudburstmc.protocol.bedrock.data.payload.inventory.transaction.InventorySource;
import org.cloudburstmc.protocol.common.util.TypeMap;
import org.cloudburstmc.protocol.common.util.VarInts;

import java.util.Arrays;

/**
 * @author Kaooot
 */
public class BedrockCodecHelper_v998 extends BedrockCodecHelper_v975 {

    public BedrockCodecHelper_v998(ActorDataTypeMap entityData, TypeMap<Class<?>> gameRulesTypes, TypeMap<ItemStackRequestActionType> stackRequestActionTypes, TypeMap<ContainerEnumName> containerSlotTypes, TypeMap<AbilitiesIndex> abilities, TypeMap<TextProcessingEventOrigin> textProcessingEventOrigins) {
        super(entityData, gameRulesTypes, stackRequestActionTypes, containerSlotTypes, abilities, textProcessingEventOrigins);
    }

    @Override
    protected InventorySource readInventorySource(ByteBuf buffer) {
        buffer.readUnsignedByte();
        buffer.readUnsignedByte();
        buffer.readUnsignedByte();
        buffer.readUnsignedByte();
        buffer.readUnsignedByte();
        buffer.readUnsignedByte();
        return new InventorySource();
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

    private void dump(ByteBuf buffer) {
        final ByteBuf copy = buffer.copy();
        System.out.println(ByteBufUtil.hexDump(copy));
        final byte[] data = new byte[copy.readableBytes()];
        copy.readBytes(data);
        System.out.println(Arrays.toString(data));
        System.out.println(new String(data));
    }
}