package org.cloudburstmc.protocol.bedrock.packet;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.cloudburstmc.protocol.bedrock.data.inventory.LegacySetSlot;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.ItemStackLegacyRequestId;
import org.cloudburstmc.protocol.bedrock.data.inventory.transaction.InventoryTransactionData;
import org.cloudburstmc.protocol.common.PacketSignal;

import java.util.List;

@Data
@EqualsAndHashCode(doNotUseGetters = true)
@ToString(doNotUseGetters = true)
public class InventoryTransactionPacket implements BedrockPacket {

    private ItemStackLegacyRequestId legacyRequestID;
    private final List<LegacySetSlot> legacySetItemSlots = new ObjectArrayList<>();
    private InventoryTransactionData transaction;

    @Override
    public final PacketSignal handle(BedrockPacketHandler handler) {
        return handler.handle(this);
    }

    @Override
    public BedrockPacketType getPacketType() {
        return BedrockPacketType.INVENTORY_TRANSACTION;
    }

    @Override
    public InventoryTransactionPacket clone() {
        try {
            return (InventoryTransactionPacket) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
    }
}