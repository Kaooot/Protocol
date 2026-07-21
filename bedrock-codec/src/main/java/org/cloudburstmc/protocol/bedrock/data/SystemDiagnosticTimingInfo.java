package org.cloudburstmc.protocol.bedrock.data;

import java.lang.String;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SystemDiagnosticTimingInfo {
  private String displayName;

  private long systemIndex;

  private long timeInNs;

  private int percentOfTotal;
}
