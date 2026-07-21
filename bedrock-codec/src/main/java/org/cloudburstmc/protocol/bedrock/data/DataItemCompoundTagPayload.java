package org.cloudburstmc.protocol.bedrock.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.cloudburstmc.nbt.NbtMap;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DataItemCompoundTagPayload {
  private DataItemType type;

  private NbtMap value;
}
