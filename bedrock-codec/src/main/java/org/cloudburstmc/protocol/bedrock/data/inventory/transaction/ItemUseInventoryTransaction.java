package org.cloudburstmc.protocol.bedrock.data.inventory.transaction;

import lombok.Data;
import org.cloudburstmc.math.vector.Vector3f;
import org.cloudburstmc.math.vector.Vector3i;
import org.cloudburstmc.protocol.bedrock.annotation.Added;
import org.cloudburstmc.protocol.bedrock.data.definitions.BlockDefinition;
import org.cloudburstmc.protocol.bedrock.data.inventory.ItemData;
import org.cloudburstmc.protocol.bedrock.data.player.HandSlot;

@Data
public class ItemUseInventoryTransaction implements InventoryTransactionData {

    @Override
    public InventoryTransactionType getType() {
        return InventoryTransactionType.ITEM_USE;
    }


    private InventoryTransaction actions;
    private ItemUseActionType actionType;
    @Added("1.21.20")
    private ItemUseTriggerType triggerType;
    private Vector3i position;
    private int face;
    private int slot;
    @Added("26.50")
    private HandSlot hand;
    private ItemData item;
    private Vector3f fromPosition;
    private Vector3f clickPosition;
    @Added("1.10.0")
    private BlockDefinition targetBlockId;
    @Added("1.21.20")
    private ItemUsePredictedResult clientInteractPrediction;
    @Added("26.10")
    private ItemUseClientCooldownState clientCooldownState;
}