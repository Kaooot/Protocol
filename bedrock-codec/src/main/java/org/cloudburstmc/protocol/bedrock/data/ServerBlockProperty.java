package org.cloudburstmc.protocol.bedrock.data;

import java.lang.String;
import lombok.Data;
import org.cloudburstmc.nbt.NbtMap;

@Data
public class ServerBlockProperty {
  private String blockName;

  private NbtMap blockDefinition;
}
