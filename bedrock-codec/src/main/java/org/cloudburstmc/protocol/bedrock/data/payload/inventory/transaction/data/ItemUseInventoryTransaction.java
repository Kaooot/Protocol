package org.cloudburstmc.protocol.bedrock.data.payload.inventory.transaction.data;

import lombok.Data;
import org.cloudburstmc.math.vector.Vector3f;
import org.cloudburstmc.math.vector.Vector3i;
import org.cloudburstmc.protocol.bedrock.data.HandSlot;
import org.cloudburstmc.protocol.bedrock.data.definitions.BlockDefinition;
import org.cloudburstmc.protocol.bedrock.data.inventory.ItemData;
import org.cloudburstmc.protocol.bedrock.data.payload.inventory.transaction.*;

/**
 * @author Kaooot
 */
@Data
public class ItemUseInventoryTransaction implements InventoryTransactionData {

    private InventoryTransaction actions;
    private ItemUseActionType actionType;
    private ItemUseTriggerType triggerType;
    private Vector3i position;
    private int face;
    private int slot;
    /**
     * @since v2192
     */
    private HandSlot hand;
    private ItemData item;
    private Vector3f fromPosition;
    private Vector3f clickPosition;
    private BlockDefinition targetBlockId;
    private ItemUsePredictedResult clientInteractPrediction;
    private ItemUseClientCooldownState clientCooldownState;

    @Override
    public InventoryTransactionDataType getType() {
        return InventoryTransactionDataType.ITEM_USE;
    }
}