package org.cloudburstmc.protocol.bedrock.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemStackRequestCraftRepairAndDisenchantAction {
  private ItemStackRequestActionType actionType;

  private int recipeNetId;

  private int numberOfRequestedCrafts;

  private int repairCost;
}
