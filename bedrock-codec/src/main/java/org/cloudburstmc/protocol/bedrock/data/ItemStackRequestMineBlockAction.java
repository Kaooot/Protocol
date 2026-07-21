package org.cloudburstmc.protocol.bedrock.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemStackRequestMineBlockAction {
  private ItemStackRequestActionType actionType;

  private int slot;

  private int predictedDurability;

  private int netIdVariant;
}
