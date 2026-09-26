package org.cloudburstmc.protocol.bedrock.data.inventory.transaction;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import lombok.Data;
import org.cloudburstmc.protocol.bedrock.data.inventory.InventoryAction;

import java.util.List;

@Data
public class InventoryTransaction {

    private final List<InventoryAction> actions = new ObjectArrayList<>();
}