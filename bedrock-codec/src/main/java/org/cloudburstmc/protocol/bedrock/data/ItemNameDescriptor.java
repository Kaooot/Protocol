package org.cloudburstmc.protocol.bedrock.data;

import java.lang.String;
import lombok.Data;

@Data
public class ItemNameDescriptor {
  private ItemDescriptorType descriptorType;

  private String fullName;

  private int auxValue;
}
