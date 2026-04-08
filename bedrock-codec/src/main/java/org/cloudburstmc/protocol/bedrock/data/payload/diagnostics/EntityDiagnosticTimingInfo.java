package org.cloudburstmc.protocol.bedrock.data.payload.diagnostics;

import lombok.Data;

/**
 * @author Kaooot
 */
@Data
public class EntityDiagnosticTimingInfo {

    private String displayName;
    private String entity;
    private long timeInNS;
    private int percentOfTotal;
}