package org.cloudburstmc.protocol.bedrock.data.text;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import java.lang.String;
import java.util.List;
import lombok.Data;
import org.cloudburstmc.protocol.bedrock.data.MessageType;

@Data
public class MessageAndParams {
  private MessageType messageType;

  private String message;

  private final List<String> parameterList = new ObjectArrayList<>();
}
