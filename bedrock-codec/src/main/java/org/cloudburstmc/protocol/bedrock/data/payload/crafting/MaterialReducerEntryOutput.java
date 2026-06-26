package org.cloudburstmc.protocol.bedrock.data.payload.crafting;

import lombok.Data;

/**
 * @author Kaooot
 */
@Data
public class MaterialReducerEntryOutput {

    private int itemId;
    private int itemCount;
}