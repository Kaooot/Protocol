package org.cloudburstmc.protocol.bedrock.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemStackRequestCraftCreativeAction {
  private ItemStackRequestActionType actionType;

  private int creativeItemNetId;

  private int numberOfRequestedCrafts;
}
