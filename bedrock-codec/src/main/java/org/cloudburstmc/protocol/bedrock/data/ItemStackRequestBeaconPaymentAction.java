package org.cloudburstmc.protocol.bedrock.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemStackRequestBeaconPaymentAction {
  private ItemStackRequestActionType actionType;

  private int primaryEffectId;

  private int secondaryEffectId;
}
