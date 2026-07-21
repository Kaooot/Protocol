package org.cloudburstmc.protocol.bedrock.data;

import lombok.Data;

@Data
public class EnchantmentInstance {
  private EnchantType enchantType;

  private int enchantLevel;
}
