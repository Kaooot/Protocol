package org.cloudburstmc.protocol.bedrock.data.payload.inventory.net;

import lombok.Value;

/**
 * @author Kaooot
 */
@Value
public class ItemStackLegacyRequestId implements ItemStackNetIdVariant {
    
    int ID;
}