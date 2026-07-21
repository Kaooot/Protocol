package org.cloudburstmc.protocol.bedrock.data;

import lombok.Data;

@Data
public class FullContainerName {
  private ContainerEnumName containerName;

  private int dynamicID;
}
