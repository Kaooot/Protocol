package org.cloudburstmc.protocol.bedrock.data;

import lombok.Data;

@Data
public class ItemStackRequestBeaconPaymentAction {
  private ItemStackRequestActionType actionType;

  private int primaryEffectId;

  private int secondaryEffectId;
}
