package org.cloudburstmc.protocol.bedrock.data;

import lombok.Data;

@Data
public class ContainerMixDataEntry {
  private int fromItemId;

  private int reagentItemId;

  private int toItemId;
}
