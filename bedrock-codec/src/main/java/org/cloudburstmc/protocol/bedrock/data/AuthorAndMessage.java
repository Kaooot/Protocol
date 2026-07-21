package org.cloudburstmc.protocol.bedrock.data;

import java.lang.String;
import lombok.Data;

@Data
public class AuthorAndMessage {
  private MessageType messageType;

  private String playerName;

  private String message;
}
