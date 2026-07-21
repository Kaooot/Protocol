package org.cloudburstmc.protocol.bedrock.data;

import java.lang.String;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MolangItemDescriptor {
  private ItemDescriptorType descriptorType;

  private String tagExpression;

  private MolangVersion molangVersion;
}
