package org.cloudburstmc.protocol.bedrock.data;

import lombok.Data;

@Data
public class ItemStackRequestCreateAction {
  private ItemStackRequestActionType actionType;

  private int resultsIndex;
}
