package org.cloudburstmc.protocol.bedrock.data.text;

import java.lang.String;
import lombok.Data;
import org.cloudburstmc.protocol.bedrock.data.MessageType;

@Data
public class MessageOnly {
  private MessageType messageType;

  private String message;
}
