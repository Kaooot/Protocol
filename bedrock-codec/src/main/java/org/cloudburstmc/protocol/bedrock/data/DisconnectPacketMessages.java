package org.cloudburstmc.protocol.bedrock.data;

import java.lang.String;
import lombok.Data;

@Data
public class DisconnectPacketMessages {
  private String message;

  private String filteredMessage;
}
