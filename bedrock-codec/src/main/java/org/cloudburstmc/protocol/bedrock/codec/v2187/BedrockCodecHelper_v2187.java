package org.cloudburstmc.protocol.bedrock.codec.v2187;

import io.netty.buffer.ByteBuf;
import org.cloudburstmc.protocol.bedrock.codec.ActorDataTypeMap;
import org.cloudburstmc.protocol.bedrock.codec.v2168.BedrockCodecHelper_v2168;
import org.cloudburstmc.protocol.bedrock.data.AbilitiesIndex;
import org.cloudburstmc.protocol.bedrock.data.inventory.ContainerEnumName;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.TextProcessingEventOrigin;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.action.ItemStackRequestActionType;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.response.ItemStackResponseSlotInfo;
import org.cloudburstmc.protocol.bedrock.data.payload.common.RedactableString;
import org.cloudburstmc.protocol.bedrock.data.payload.inventory.net.ItemStackNetId;
import org.cloudburstmc.protocol.common.util.TypeMap;
import org.cloudburstmc.protocol.common.util.VarInts;

/**
 * @author Kaooot
 */
public class BedrockCodecHelper_v2187 extends BedrockCodecHelper_v2168 {

    public BedrockCodecHelper_v2187(ActorDataTypeMap entityData, TypeMap<Class<?>> gameRulesTypes, TypeMap<ItemStackRequestActionType> stackRequestActionTypes, TypeMap<ContainerEnumName> containerSlotTypes, TypeMap<AbilitiesIndex> abilities, TypeMap<TextProcessingEventOrigin> textProcessingEventOrigins) {
        super(entityData, gameRulesTypes, stackRequestActionTypes, containerSlotTypes, abilities, textProcessingEventOrigins);
    }

    @Override
    protected void writeItemEntry(ByteBuf buffer, ItemStackResponseSlotInfo itemEntry) {
        buffer.writeByte(itemEntry.getRequestedSlot());
        buffer.writeByte(itemEntry.getSlot());
        buffer.writeByte(itemEntry.getAmount());
        this.writeOptionalNull(
                buffer,
                itemEntry.getItemStackNetId(),
                (buf, helper, itemStackNetId) -> VarInts.writeInt(buf, itemStackNetId.getID())
        );
        this.writeRedactableString(buffer, itemEntry.getCustomName());
        VarInts.writeInt(buffer, itemEntry.getDurabilityCorrection());
    }

    @Override
    protected ItemStackResponseSlotInfo readItemEntry(ByteBuf buffer) {
        int slot = buffer.readUnsignedByte();
        int requestedSlot = buffer.readUnsignedByte();
        int amount = buffer.readUnsignedByte();
        ItemStackNetId stackNetworkId = this.readOptional(
                buffer,
                null,
                (buf, helper) -> new ItemStackNetId(VarInts.readInt(buf))
        );
        final RedactableString customName = this.readRedactableString(buffer);
        int durabilityCorrection = VarInts.readInt(buffer);
        return new ItemStackResponseSlotInfo(
                slot,
                requestedSlot,
                amount,
                stackNetworkId,
                customName,
                durabilityCorrection
        );
    }
}