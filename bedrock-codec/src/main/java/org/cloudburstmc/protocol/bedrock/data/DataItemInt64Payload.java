package org.cloudburstmc.protocol.bedrock.data;

import lombok.Data;

@Data
public class DataItemInt64Payload {
  private DataItemType type;

  private long value;
}
