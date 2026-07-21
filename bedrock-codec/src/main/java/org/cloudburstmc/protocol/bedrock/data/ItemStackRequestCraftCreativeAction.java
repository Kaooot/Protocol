package org.cloudburstmc.protocol.bedrock.data;

import lombok.Data;

@Data
public class ItemStackRequestCraftCreativeAction {
  private ItemStackRequestActionType actionType;

  private int creativeItemNetId;

  private int numberOfRequestedCrafts;
}
