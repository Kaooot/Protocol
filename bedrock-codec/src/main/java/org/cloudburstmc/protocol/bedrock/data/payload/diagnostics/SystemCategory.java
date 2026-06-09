package org.cloudburstmc.protocol.bedrock.data.payload.diagnostics;

import lombok.Data;

/**
 * @author Kaooot
 */
@Data
public class SystemCategory {

    private String categoryName;
    private long systemIndex;
}