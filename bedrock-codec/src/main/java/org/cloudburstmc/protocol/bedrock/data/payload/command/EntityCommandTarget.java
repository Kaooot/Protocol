package org.cloudburstmc.protocol.bedrock.data.payload.command;

import lombok.Value;

/**
 * @author Kaooot
 */
@Value
public class EntityCommandTarget implements CommandBlockUpdateTarget {

    long targetRuntimeID;

    @Override
    public CommandBlockUpdateTargetType getType() {
        return CommandBlockUpdateTargetType.ENTITY;
    }
}