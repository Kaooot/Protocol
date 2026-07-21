package org.cloudburstmc.protocol.bedrock.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemStackRequestSwapAction {
  private ItemStackRequestActionType actionType;

  private ItemStackRequestSlotInfo source;

  private ItemStackRequestSlotInfo destination;
}
