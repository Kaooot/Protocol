package org.cloudburstmc.protocol.bedrock.data;

import java.util.UUID;
import lombok.Data;

@Data
public class MultiRecipePayload {
  private UUID multiRecipeUUID;

  private RecipeNetId netId;
}
