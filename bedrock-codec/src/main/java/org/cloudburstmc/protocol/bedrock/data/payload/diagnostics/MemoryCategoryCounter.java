package org.cloudburstmc.protocol.bedrock.data.payload.diagnostics;

import lombok.Data;

/**
 * @author Kaooot
 */
@Data
public class MemoryCategoryCounter {

    private MemoryCategory category;
    private long currentBytes;
}