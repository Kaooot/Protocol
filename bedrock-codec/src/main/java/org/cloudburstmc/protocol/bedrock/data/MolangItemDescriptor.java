package org.cloudburstmc.protocol.bedrock.data;

import java.lang.String;
import lombok.Data;

@Data
public class MolangItemDescriptor {
  private ItemDescriptorType descriptorType;

  private String tagExpression;

  private MolangVersion molangVersion;
}
