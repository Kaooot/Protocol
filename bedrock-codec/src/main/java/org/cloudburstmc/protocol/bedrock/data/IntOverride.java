package org.cloudburstmc.protocol.bedrock.data;

import lombok.Data;

@Data
public class IntOverride {
  private UpdateType type;

  private int value;
}
