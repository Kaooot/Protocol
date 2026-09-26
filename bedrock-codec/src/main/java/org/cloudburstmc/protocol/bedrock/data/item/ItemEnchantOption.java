package org.cloudburstmc.protocol.bedrock.data.item;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.bedrock.data.recipe.RecipeNetId;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemEnchantOption {

    private int cost;
    private ItemEnchants enchants;
    private String enchantName;
    private RecipeNetId enchantNetId;
}