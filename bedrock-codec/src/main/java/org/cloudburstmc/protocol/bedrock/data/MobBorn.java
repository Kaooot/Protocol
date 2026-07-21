package org.cloudburstmc.protocol.bedrock.data;

import lombok.Data;

@Data
public class MobBorn {
  private int bornBabyEntityType;

  private int bornBabyEntityVariant;

  private int bornBabyColor;
}
