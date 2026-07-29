package org.cloudburstmc.protocol.bedrock.data.text;

import java.lang.String;
import lombok.Data;
import org.cloudburstmc.protocol.bedrock.data.MessageType;

@Data
public class AuthorAndMessage {
  private MessageType messageType;

  private String playerName;

  private String message;
}
