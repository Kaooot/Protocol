package org.cloudburstmc.protocol.bedrock.data;

import java.lang.String;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Auto generated from 1.26.40-beta.31 (v2168)
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WhiskerScopeDataSummary {
  private String Label;

  private String Indentation;

  private long TotalHighCostNS;

  private long TotalMidCostNS;

  private long TotalLowCostNS;
}
