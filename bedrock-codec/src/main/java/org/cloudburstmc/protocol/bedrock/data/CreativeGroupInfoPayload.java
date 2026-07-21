package org.cloudburstmc.protocol.bedrock.data;

import java.lang.String;
import lombok.Data;

@Data
public class CreativeGroupInfoPayload {
  private CreativeCategory creativeCategory;

  private String name;

  private NetworkItemInstanceDescriptor groupIconItem;
}
