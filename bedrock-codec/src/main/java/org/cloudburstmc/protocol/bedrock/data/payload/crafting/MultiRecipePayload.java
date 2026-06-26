package org.cloudburstmc.protocol.bedrock.data.payload.crafting;

import lombok.Data;

import java.util.UUID;

/**
 * @author Kaooot
 */
@Data
public class MultiRecipePayload {

    private UUID multiRecipeUUID;
    private RecipeNetId netId;
}