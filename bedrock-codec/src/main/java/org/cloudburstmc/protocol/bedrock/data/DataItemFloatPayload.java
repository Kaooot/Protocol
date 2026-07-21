package org.cloudburstmc.protocol.bedrock.data;

import lombok.Data;

@Data
public class DataItemFloatPayload {
  private DataItemType type;

  private float value;
}
