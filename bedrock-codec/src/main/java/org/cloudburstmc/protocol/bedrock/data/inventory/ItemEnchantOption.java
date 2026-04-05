package org.cloudburstmc.protocol.bedrock.data.inventory;

import lombok.Value;

/**
 * @author Kaooot
 */
@Value
public class ItemEnchantOption {

    int cost;
    ItemEnchants itemEnchants;
    String enchantName;
    int enchantNetId;
}