package org.cloudburstmc.protocol.bedrock.data.actor;

import lombok.Value;

@Value
public class FloatEntityProperty implements EntityProperty {
    int index;
    float value;
}