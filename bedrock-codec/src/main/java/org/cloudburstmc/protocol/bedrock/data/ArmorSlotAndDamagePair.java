package org.cloudburstmc.protocol.bedrock.data;

import lombok.Value;

/**
 * @author Kaooot
 */
@Value
public class ArmorSlotAndDamagePair {

    ArmorSlot slot;
    short damage;
}