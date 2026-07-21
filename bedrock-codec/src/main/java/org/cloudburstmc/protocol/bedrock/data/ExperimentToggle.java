package org.cloudburstmc.protocol.bedrock.data;

import java.lang.String;
import lombok.Data;

@Data
public class ExperimentToggle {
  private String name;

  private boolean enabled;
}
