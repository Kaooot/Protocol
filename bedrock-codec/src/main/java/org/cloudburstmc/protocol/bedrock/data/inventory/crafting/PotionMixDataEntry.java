package org.cloudburstmc.protocol.bedrock.data.inventory.crafting;

import lombok.Value;

/**
 * Represents a potion mixing recipe which may be used in a brewing stand.
 */
@Value
public class PotionMixDataEntry {
    // Potion to be put in
    private final int inputPotionId;
    private final int inputPotionItemAux;

    // Item to be added to the brewing stand to brew the output potion
    private final int reagentItemId;
    private final int reagentItemAux;

    // Output Potion
    private final int outputPotionId;
    private final int outputPotionAux;
}
