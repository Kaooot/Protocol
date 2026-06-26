package org.cloudburstmc.protocol.bedrock.data.payload.crafting;

import lombok.Data;

/**
 * @author Kaooot
 */
@Data
public class PotionMixDataEntry {

    private int fromPotionId;
    private int fromItemAux;
    private int reagentItemId;
    private int reagentItemAux;
    private int toPotionId;
    private int toItemAux;
}