package org.cloudburstmc.protocol.bedrock.data.actor;

import lombok.Data;

@Data
public class PropertySyncIntEntry {

    private int propertyIndex;
    private int data;
}