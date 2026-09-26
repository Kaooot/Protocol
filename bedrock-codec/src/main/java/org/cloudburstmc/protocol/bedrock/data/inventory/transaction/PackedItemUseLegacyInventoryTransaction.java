package org.cloudburstmc.protocol.bedrock.data.inventory.transaction;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import lombok.Data;
import org.cloudburstmc.protocol.bedrock.data.inventory.InventoryAction;
import org.cloudburstmc.protocol.bedrock.data.inventory.LegacySetSlot;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.ItemStackLegacyRequestId;

import java.util.List;

@Data
public class PackedItemUseLegacyInventoryTransaction {

    private ItemStackLegacyRequestId legacyRequestID;
    private final List<LegacySetSlot> legacySetItemSlots = new ObjectArrayList<>();
    private final List<InventoryAction> actions = new ObjectArrayList<>();
    private ItemUseInventoryTransaction transaction;
}