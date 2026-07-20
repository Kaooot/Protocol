package org.cloudburstmc.protocol.bedrock.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.cloudburstmc.nbt.NbtMap;

/**
 * Auto generated from 1.26.40-beta.31 (v2168)
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DataItemCompoundTagPayload {
  private DataItemType type;

  private NbtMap value;
}
