package org.cloudburstmc.protocol.bedrock.data;

import java.lang.String;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Auto generated from 1.26.40-beta.31 (v2168)
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class NetworkItemInstanceDescriptorData {
  private int id;

  private int stackSize;

  private int auxValue;

  private int blockRuntimeId;

  private String userDataBuffer;
}
