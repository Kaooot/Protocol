package org.cloudburstmc.protocol.bedrock.data;

import lombok.Data;

@Data
public class PotionMixDataEntry {
  private int fromPotionId;

  private int fromItemAux;

  private int reagentItemId;

  private int reagentItemAux;

  private int toPotionId;

  private int toItemAux;
}
