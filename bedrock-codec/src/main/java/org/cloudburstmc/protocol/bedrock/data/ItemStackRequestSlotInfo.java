package org.cloudburstmc.protocol.bedrock.data;

import lombok.Data;

@Data
public class ItemStackRequestSlotInfo {
  private FullContainerName fullContainerName;

  private int slot;

  private int netIdVariant;
}
