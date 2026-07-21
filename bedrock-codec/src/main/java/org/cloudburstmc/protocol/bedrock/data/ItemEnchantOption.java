package org.cloudburstmc.protocol.bedrock.data;

import java.lang.String;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemEnchantOption {
  private int cost;

  private ItemEnchants enchants;

  private String enchantName;

  private RecipeNetId enchantNetId;
}
