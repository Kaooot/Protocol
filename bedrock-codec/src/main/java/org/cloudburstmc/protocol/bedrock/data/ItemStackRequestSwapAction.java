package org.cloudburstmc.protocol.bedrock.data;

import lombok.Data;

@Data
public class ItemStackRequestSwapAction {
  private ItemStackRequestActionType actionType;

  private ItemStackRequestSlotInfo source;

  private ItemStackRequestSlotInfo destination;
}
