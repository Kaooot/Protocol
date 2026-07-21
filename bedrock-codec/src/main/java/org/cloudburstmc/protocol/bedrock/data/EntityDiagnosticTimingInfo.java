package org.cloudburstmc.protocol.bedrock.data;

import java.lang.String;
import lombok.Data;
import org.cloudburstmc.math.vector.Vector3f;

@Data
public class EntityDiagnosticTimingInfo {
  private String displayName;

  private String entity;

  private long timeInNs;

  private int percentOfTotal;

  private Vector3f position;

  private String dimension;
}
