package org.cloudburstmc.protocol.bedrock.data.actor;

import lombok.Data;

@Data
public class PropertySyncFloatEntry {

    private int propertyIndex;
    private float data;
}