package org.cloudburstmc.protocol.bedrock.data;

import lombok.Data;

@Data
public class ItemStackRequestDropAction {
  private ItemStackRequestActionType actionType;

  private int amount;

  private ItemStackRequestSlotInfo source;

  private boolean randomly;
}
