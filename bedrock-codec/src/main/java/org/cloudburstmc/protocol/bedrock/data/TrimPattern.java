package org.cloudburstmc.protocol.bedrock.data;

import java.lang.String;
import lombok.Data;

@Data
public class TrimPattern {
  private String itemName;

  private String patternId;
}
