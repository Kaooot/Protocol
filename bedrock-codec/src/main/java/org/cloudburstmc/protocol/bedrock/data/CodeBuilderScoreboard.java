package org.cloudburstmc.protocol.bedrock.data;

import java.lang.String;
import lombok.Data;

@Data
public class CodeBuilderScoreboard {
  private String objectiveName;

  private int score;
}
