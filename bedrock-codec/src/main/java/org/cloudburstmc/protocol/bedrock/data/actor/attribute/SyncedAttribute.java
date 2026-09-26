package org.cloudburstmc.protocol.bedrock.data.actor.attribute;

import lombok.Data;

@Data
public class SyncedAttribute {

    private String attributeName;
    private float minValue;
    private float currentValue;
    private float maxValue;
}