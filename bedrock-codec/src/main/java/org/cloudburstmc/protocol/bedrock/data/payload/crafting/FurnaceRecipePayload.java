package org.cloudburstmc.protocol.bedrock.data.payload.crafting;

import lombok.Data;
import org.cloudburstmc.protocol.bedrock.data.inventory.ItemData;

/**
 * @author Kaooot
 * @deprecated since v975
 */
@Data
@Deprecated
public class FurnaceRecipePayload {

    private int inputId;
    private int auxValue;
    private ItemData result;
    /**
     * @since v354
     */
    private String tag;
}