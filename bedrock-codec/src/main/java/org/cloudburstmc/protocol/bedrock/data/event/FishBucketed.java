package org.cloudburstmc.protocol.bedrock.data.event;

import lombok.Data;

@Data
public class FishBucketed {

    private int pattern;
    private int preset;
    private int bucketedEntityType;
    private boolean releaseEvent;
}