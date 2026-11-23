package org.cloudburstmc.protocol.bedrock.data.recipe;

import lombok.Data;
import org.cloudburstmc.protocol.bedrock.annotation.Added;
import org.cloudburstmc.protocol.bedrock.annotation.Removed;
import org.cloudburstmc.protocol.bedrock.data.inventory.ItemData;

@Data
@Deprecated
@Added("1.11.0")
@Removed("26.20")
public class FurnaceRecipePayload {

    private int inputId;
    private int auxValue;
    private ItemData result;
    @Added("1.11.0")
    private String tag;
}