package org.cloudburstmc.protocol.bedrock.data;

import lombok.Data;

@Data
public class ItemStackRequestDestroyAction {
  private ItemStackRequestActionType actionType;

  private int amount;

  private ItemStackRequestSlotInfo source;
}
