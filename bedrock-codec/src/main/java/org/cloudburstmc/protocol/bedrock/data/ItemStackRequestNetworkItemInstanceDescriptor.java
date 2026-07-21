package org.cloudburstmc.protocol.bedrock.data;

import java.lang.Object;
import java.lang.String;
import lombok.Data;

@Data
public class ItemStackRequestNetworkItemInstanceDescriptor {
  private Object itemDescriptor;

  private int stackSize;

  private int blockRuntimeId;

  private String userDataBuffer;
}
