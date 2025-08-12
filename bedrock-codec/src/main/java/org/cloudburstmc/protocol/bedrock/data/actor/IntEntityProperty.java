package org.cloudburstmc.protocol.bedrock.data.actor;

import lombok.Value;

@Value
public class IntEntityProperty implements EntityProperty {
    int index;
    int value;
}