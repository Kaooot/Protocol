package org.cloudburstmc.protocol.bedrock.data;

import java.lang.Object;
import java.lang.String;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemStackRequestNetworkItemInstanceDescriptor {
  private Object itemDescriptor;

  private int stackSize;

  private int blockRuntimeId;

  private String userDataBuffer;
}
