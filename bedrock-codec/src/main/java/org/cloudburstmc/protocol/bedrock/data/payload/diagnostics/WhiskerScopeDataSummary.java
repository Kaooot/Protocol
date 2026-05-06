package org.cloudburstmc.protocol.bedrock.data.payload.diagnostics;

import lombok.Data;

/**
 * @author Kaooot
 */
@Data
public class WhiskerScopeDataSummary {

    private String indentation;
    private String label;
    private long totalHighCostNS;
    private long totalMidCostNS;
    private long totalLowCostNS;
}