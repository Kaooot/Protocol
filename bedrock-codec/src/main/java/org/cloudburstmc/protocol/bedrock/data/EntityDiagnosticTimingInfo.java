package org.cloudburstmc.protocol.bedrock.data;

import java.lang.String;
import lombok.Data;

@Data
public class EntityDiagnosticTimingInfo {
  private String displayName;

  private String entity;

  private long timeInNs;

  private int percentOfTotal;
}
