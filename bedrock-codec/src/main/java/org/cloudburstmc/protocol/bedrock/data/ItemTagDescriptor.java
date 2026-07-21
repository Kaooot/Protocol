package org.cloudburstmc.protocol.bedrock.data;

import java.lang.String;
import lombok.Data;

@Data
public class ItemTagDescriptor {
  private ItemDescriptorType descriptorType;

  private String itemTag;
}
