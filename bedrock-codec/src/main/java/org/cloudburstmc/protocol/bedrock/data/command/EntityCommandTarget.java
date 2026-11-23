package org.cloudburstmc.protocol.bedrock.data.command;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EntityCommandTarget implements CommandBlockUpdateTarget {

    @Override
    public CommandBlockUpdateTargetType getType() {
        return CommandBlockUpdateTargetType.ENTITY;
    }


    private long targetRuntimeID;
}