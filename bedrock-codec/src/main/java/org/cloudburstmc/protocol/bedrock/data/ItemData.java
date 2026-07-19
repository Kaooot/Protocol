package org.cloudburstmc.protocol.bedrock.data;

import java.lang.String;
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
public class ItemData {
  private String hashedstring;

  private int ItemId;

  private boolean IsComponentBased;

  private ItemVersion ItemVersion;

  private NbtMap ItemComponentData;
}
