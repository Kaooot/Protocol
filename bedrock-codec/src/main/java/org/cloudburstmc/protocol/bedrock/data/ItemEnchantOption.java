package org.cloudburstmc.protocol.bedrock.data;

import java.lang.String;
import lombok.Data;

@Data
public class ItemEnchantOption {
  private int cost;

  private ItemEnchants enchants;

  private String enchantName;

  private RecipeNetId enchantNetId;
}
