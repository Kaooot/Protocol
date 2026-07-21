package org.cloudburstmc.protocol.bedrock.data;

import java.lang.String;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AvailableCommandsPacketParamData {
  private String name;

  private int parseSymbol;

  private boolean isOptional;

  private int options;
}
