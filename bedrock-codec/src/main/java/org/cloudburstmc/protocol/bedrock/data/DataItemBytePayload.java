package org.cloudburstmc.protocol.bedrock.data;

import lombok.Data;

@Data
public class DataItemBytePayload {
  private DataItemType type;

  private int value;
}
