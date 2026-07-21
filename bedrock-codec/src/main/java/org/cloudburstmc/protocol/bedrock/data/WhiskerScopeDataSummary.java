package org.cloudburstmc.protocol.bedrock.data;

import java.lang.String;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WhiskerScopeDataSummary {
  private String label;

  private String indentation;

  private long totalHighCostNS;

  private long totalMidCostNS;

  private long totalLowCostNS;
}
