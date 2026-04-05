package org.cloudburstmc.protocol.bedrock.data.inventory;

import lombok.Value;

import java.util.List;

/**
 * @author Kaooot
 */
@Value
public class ItemEnchants {

    int slot;
    List<EnchantmentInstance> enchants0;
    List<EnchantmentInstance> enchants1;
    List<EnchantmentInstance> enchants2;
}