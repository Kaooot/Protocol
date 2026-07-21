package org.cloudburstmc.protocol.bedrock.data;

import java.lang.String;
import lombok.Data;

@Data
public class WhiskerScopeDataSummary {
  private String label;

  private String indentation;

  private long totalHighCostNS;

  private long totalMidCostNS;

  private long totalLowCostNS;
}
