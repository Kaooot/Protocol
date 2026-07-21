package org.cloudburstmc.protocol.bedrock.data;

import java.lang.String;
import lombok.Data;

@Data
public class AvailableCommandsPacketParamData {
  private String name;

  private int parseSymbol;

  private boolean isOptional;

  private int options;
}
