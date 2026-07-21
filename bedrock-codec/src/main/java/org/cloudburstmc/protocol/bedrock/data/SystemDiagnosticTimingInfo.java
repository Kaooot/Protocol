package org.cloudburstmc.protocol.bedrock.data;

import java.lang.String;
import lombok.Data;

@Data
public class SystemDiagnosticTimingInfo {
  private String displayName;

  private long systemIndex;

  private long timeInNs;

  private int percentOfTotal;
}
