package org.cloudburstmc.protocol.bedrock.data;

import lombok.Data;

@Data
public class ItemStackRequestMineBlockAction {
  private ItemStackRequestActionType actionType;

  private int slot;

  private int predictedDurability;

  private int netIdVariant;
}
