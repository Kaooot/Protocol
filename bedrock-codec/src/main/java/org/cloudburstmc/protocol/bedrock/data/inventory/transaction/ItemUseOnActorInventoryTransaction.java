package org.cloudburstmc.protocol.bedrock.data.inventory.transaction;

import lombok.Data;
import org.cloudburstmc.math.vector.Vector3f;
import org.cloudburstmc.protocol.bedrock.annotation.Added;
import org.cloudburstmc.protocol.bedrock.data.inventory.ItemData;
import org.cloudburstmc.protocol.bedrock.data.player.HandSlot;

@Data
public class ItemUseOnActorInventoryTransaction {

    private InventoryTransaction actions;
    private long runtimeId;
    private ItemUseOnActorActionType actionType;
    private int slot;
    @Added("26.60")
    private HandSlot hand;
    private ItemData item;
    private Vector3f fromPosition;
    private Vector3f hitPosition;
}
