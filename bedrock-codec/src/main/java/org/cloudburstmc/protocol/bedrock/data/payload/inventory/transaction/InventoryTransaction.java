package org.cloudburstmc.protocol.bedrock.data.payload.inventory.transaction;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import lombok.Data;

import java.util.List;

/**
 * @author Kaooot
 */
@Data
public class InventoryTransaction {

    private final List<InventoryAction> actions = new ObjectArrayList<>();
}