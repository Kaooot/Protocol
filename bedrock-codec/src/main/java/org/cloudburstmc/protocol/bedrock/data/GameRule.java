package org.cloudburstmc.protocol.bedrock.data;

import java.lang.Object;
import java.lang.String;
import lombok.Data;

@Data
public class GameRule {
  private String ruleName;

  private boolean ruleCanBeModified;

  private Object ruleValue;
}
