package org.cloudburstmc.protocol.bedrock.data;

import java.lang.String;
import lombok.Data;

@Data
public class ItemStackRequestCraftLoomAction {
  private ItemStackRequestActionType actionType;

  private String patternNameId;

  private int numCrafts;
}
