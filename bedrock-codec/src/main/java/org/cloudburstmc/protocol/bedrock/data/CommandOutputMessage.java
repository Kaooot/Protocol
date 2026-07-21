package org.cloudburstmc.protocol.bedrock.data;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import java.lang.String;
import java.util.List;
import lombok.Data;

@Data
public class CommandOutputMessage {
  private String messageID;

  private boolean successful;

  private final List<String> parameters = new ObjectArrayList<>();
}
