package org.cloudburstmc.protocol.bedrock.data.inventory;

import lombok.Value;

/**
 * @author Kaooot
 */
@Value
public class EnchantmentInstance {

    int enchantType;
    int enchantLevel;
}