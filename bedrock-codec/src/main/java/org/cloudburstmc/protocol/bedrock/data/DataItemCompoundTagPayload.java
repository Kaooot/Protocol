package org.cloudburstmc.protocol.bedrock.data;

import lombok.Data;
import org.cloudburstmc.nbt.NbtMap;

@Data
public class DataItemCompoundTagPayload {
  private DataItemType type;

  private NbtMap value;
}
