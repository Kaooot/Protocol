package org.cloudburstmc.protocol.bedrock.codec.v941;

import io.netty.buffer.ByteBuf;
import org.cloudburstmc.protocol.bedrock.codec.ActorDataTypeMap;
import org.cloudburstmc.protocol.bedrock.codec.v924.BedrockCodecHelper_v924;
import org.cloudburstmc.protocol.bedrock.data.AbilitiesIndex;
import org.cloudburstmc.protocol.bedrock.data.inventory.ContainerEnumName;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.TextProcessingEventOrigin;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.action.ItemStackRequestActionType;
import org.cloudburstmc.protocol.bedrock.packet.InventoryTransactionPacket;
import org.cloudburstmc.protocol.common.util.TypeMap;

/**
 * @author Kaooot
 */
public class BedrockCodecHelper_v941 extends BedrockCodecHelper_v924 {

    public BedrockCodecHelper_v941(ActorDataTypeMap entityData, TypeMap<Class<?>> gameRulesTypes, TypeMap<ItemStackRequestActionType> stackRequestActionTypes, TypeMap<ContainerEnumName> containerSlotTypes, TypeMap<AbilitiesIndex> abilities, TypeMap<TextProcessingEventOrigin> textProcessingEventOrigins) {
        super(entityData, gameRulesTypes, stackRequestActionTypes, containerSlotTypes, abilities, textProcessingEventOrigins);
    }

    @Override
    public void writeItemUse(ByteBuf buffer, InventoryTransactionPacket packet) {
        super.writeItemUse(buffer, packet);
        buffer.writeByte(packet.getClientCooldownState());
    }

    @Override
    public void readItemUse(ByteBuf buffer, InventoryTransactionPacket packet) {
        super.readItemUse(buffer, packet);
        packet.setClientCooldownState(buffer.readUnsignedByte());
    }
}