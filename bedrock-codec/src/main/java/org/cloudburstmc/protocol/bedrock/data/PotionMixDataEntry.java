package org.cloudburstmc.protocol.bedrock.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PotionMixDataEntry {
  private int fromPotionId;

  private int fromItemAux;

  private int reagentItemId;

  private int reagentItemAux;

  private int toPotionId;

  private int toItemAux;
}
