package org.cloudburstmc.protocol.bedrock.data;

import lombok.Data;

@Data
public class ItemStackRequestTakeAction {
  private ItemStackRequestActionType actionType;

  private int amount;

  private ItemStackRequestSlotInfo source;

  private ItemStackRequestSlotInfo destination;
}
