package org.cloudburstmc.protocol.bedrock.data.payload.inventory.transaction.data;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import lombok.Data;
import org.cloudburstmc.protocol.bedrock.data.payload.inventory.net.ItemStackLegacyRequestId;
import org.cloudburstmc.protocol.bedrock.data.payload.inventory.transaction.InventoryAction;
import org.cloudburstmc.protocol.bedrock.data.payload.inventory.transaction.LegacySetSlot;

import java.util.List;

/**
 * @author Kaooot
 */
@Data
public class PackedLegacyItemUseInventoryTransaction {

    private ItemStackLegacyRequestId legacyRequestID;
    private final List<LegacySetSlot> legacySetItemSlots = new ObjectArrayList<>();
    private final List<InventoryAction> actions = new ObjectArrayList<>();
    private ItemUseInventoryTransaction transaction;
}