package org.cloudburstmc.protocol.bedrock.data.inventory;

import lombok.Value;

@Value
public class EnchantData {
    private final int enchantType;
    private final int enchantLevel;
}
