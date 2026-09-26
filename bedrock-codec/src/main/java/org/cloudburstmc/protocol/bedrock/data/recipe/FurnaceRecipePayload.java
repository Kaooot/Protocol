package org.cloudburstmc.protocol.bedrock.data.recipe;

import lombok.Data;
import org.cloudburstmc.protocol.bedrock.data.inventory.ItemData;

/**
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