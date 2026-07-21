package org.cloudburstmc.protocol.bedrock.data;

import java.lang.String;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.cloudburstmc.nbt.NbtMap;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ServerBlockProperty {
  private String blockName;

  private NbtMap blockDefinition;
}
