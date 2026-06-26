package org.cloudburstmc.protocol.bedrock.codec.v407.serializer;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.v340.serializer.InventoryTransactionSerializer_v340;
import org.cloudburstmc.protocol.bedrock.data.payload.inventory.net.ItemStackLegacyRequestId;
import org.cloudburstmc.protocol.bedrock.data.payload.inventory.transaction.LegacySetSlot;
import org.cloudburstmc.protocol.bedrock.packet.InventoryTransactionPacket;
import org.cloudburstmc.protocol.common.util.VarInts;

@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class InventoryTransactionSerializer_v407 extends InventoryTransactionSerializer_v340 {
    public static final InventoryTransactionSerializer_v407 INSTANCE = new InventoryTransactionSerializer_v407();

    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, InventoryTransactionPacket packet) {
        this.writeLegacyRequestId(buffer, helper, packet.getLegacyRequestID());

        if (packet.getLegacyRequestID().getID() < -1 && (packet.getLegacyRequestID().getID() & 1) == 0) {
            helper.writeArray(buffer, packet.getLegacySetItemSlots(), this::writeLegacySetSlot);
        }

        this.writeInventoryTransactionVariant(buffer, helper, packet.getTransaction());
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, InventoryTransactionPacket packet) {
        packet.setLegacyRequestID(this.readLegacyRequestId(buffer, helper));

        if (packet.getLegacyRequestID().getID() < -1 && (packet.getLegacyRequestID().getID() & 1) == 0) {
            helper.readArray(buffer, packet.getLegacySetItemSlots(), this::readLegacySetSlot);
        }

        packet.setTransaction(this.readInventoryTransactionVariant(buffer, helper));
    }

    protected void writeLegacyRequestId(ByteBuf buffer, BedrockCodecHelper helper, ItemStackLegacyRequestId id) {
        VarInts.writeInt(buffer, id.getID());
    }

    protected ItemStackLegacyRequestId readLegacyRequestId(ByteBuf buffer, BedrockCodecHelper helper) {
        return new ItemStackLegacyRequestId(VarInts.readInt(buffer));
    }

    protected void writeLegacySetSlot(ByteBuf buffer, BedrockCodecHelper helper, LegacySetSlot slot) {
        helper.writeContainerEnumName(buffer, slot.getContainerEnum());
        helper.writeByteArray(buffer, slot.getSlots());
    }

    protected LegacySetSlot readLegacySetSlot(ByteBuf buffer, BedrockCodecHelper helper) {
        final LegacySetSlot slot = new LegacySetSlot();
        slot.setContainerEnum(helper.readContainerEnumName(buffer));
        slot.setSlots(helper.readByteArray(buffer));
        return slot;
    }
}