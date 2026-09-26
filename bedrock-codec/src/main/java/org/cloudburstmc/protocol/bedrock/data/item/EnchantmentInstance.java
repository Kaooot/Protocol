package org.cloudburstmc.protocol.bedrock.data.item;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EnchantmentInstance {

    private EnchantType enchantType;
    private int enchantLevel;
}