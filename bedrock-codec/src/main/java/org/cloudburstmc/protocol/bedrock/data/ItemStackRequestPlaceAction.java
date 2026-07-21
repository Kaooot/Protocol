package org.cloudburstmc.protocol.bedrock.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemStackRequestPlaceAction {
  private ItemStackRequestActionType actionType;

  private int amount;

  private ItemStackRequestSlotInfo source;

  private ItemStackRequestSlotInfo destination;
}
