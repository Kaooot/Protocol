package org.cloudburstmc.protocol.bedrock.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreativeItemEntryPayload {
  private CreativeItemNetId creativeNetId;

  private NetworkItemInstanceDescriptor itemInstance;

  private int groupIndex;
}
