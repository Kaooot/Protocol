package org.cloudburstmc.protocol.bedrock.data;

import lombok.Data;

@Data
public class CreativeItemEntryPayload {
  private CreativeItemNetId creativeNetId;

  private NetworkItemInstanceDescriptor itemInstance;

  private int groupIndex;
}
