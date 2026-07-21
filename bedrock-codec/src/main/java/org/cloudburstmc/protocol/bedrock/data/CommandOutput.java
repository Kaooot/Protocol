package org.cloudburstmc.protocol.bedrock.data;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import java.lang.String;
import java.util.List;
import lombok.Data;

@Data
public class CommandOutput {
  private CommandOutputType outputType;

  private int successCount;

  private final List<CommandOutputMessage> outputMessages = new ObjectArrayList<>();

  private String dataSet;
}
