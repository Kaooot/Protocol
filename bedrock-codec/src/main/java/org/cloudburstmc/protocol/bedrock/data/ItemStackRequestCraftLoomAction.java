package org.cloudburstmc.protocol.bedrock.data;

import java.lang.String;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemStackRequestCraftLoomAction {
  private ItemStackRequestActionType actionType;

  private String patternNameId;

  private int numCrafts;
}
