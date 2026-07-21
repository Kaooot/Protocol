package org.cloudburstmc.protocol.bedrock.data;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MultiRecipePayload {
  private UUID multiRecipeUUID;

  private RecipeNetId netId;
}
