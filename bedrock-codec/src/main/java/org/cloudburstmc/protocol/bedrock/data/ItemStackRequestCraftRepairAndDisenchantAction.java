package org.cloudburstmc.protocol.bedrock.data;

import lombok.Data;

@Data
public class ItemStackRequestCraftRepairAndDisenchantAction {
  private ItemStackRequestActionType actionType;

  private int recipeNetId;

  private int numberOfRequestedCrafts;

  private int repairCost;
}
