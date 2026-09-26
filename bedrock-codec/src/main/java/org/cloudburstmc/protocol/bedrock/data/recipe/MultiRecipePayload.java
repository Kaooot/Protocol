package org.cloudburstmc.protocol.bedrock.data.recipe;

import lombok.Data;

import java.util.UUID;

@Data
public class MultiRecipePayload {

    private UUID multiRecipeUUID;
    private RecipeNetId netId;
}