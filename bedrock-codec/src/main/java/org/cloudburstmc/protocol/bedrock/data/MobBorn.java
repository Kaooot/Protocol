package org.cloudburstmc.protocol.bedrock.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MobBorn {
  private int bornBabyEntityType;

  private int bornBabyEntityVariant;

  private int bornBabyColor;
}
