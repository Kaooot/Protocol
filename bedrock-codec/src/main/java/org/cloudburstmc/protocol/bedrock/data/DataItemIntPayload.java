package org.cloudburstmc.protocol.bedrock.data;

import lombok.Data;

@Data
public class DataItemIntPayload {
  private DataItemType type;

  private int value;
}
