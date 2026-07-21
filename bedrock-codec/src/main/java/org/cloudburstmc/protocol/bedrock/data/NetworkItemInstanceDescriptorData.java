package org.cloudburstmc.protocol.bedrock.data;

import java.lang.String;
import lombok.Data;

@Data
public class NetworkItemInstanceDescriptorData {
  private int id;

  private int stackSize;

  private int auxValue;

  private int blockRuntimeId;

  private String userDataBuffer;
}
