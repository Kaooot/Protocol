package org.cloudburstmc.protocol.bedrock.data;

import java.lang.String;
import lombok.Data;

@Data
public class SlashCommand {
  private int successCount;

  private int errorCount;

  private String commandName;

  private String errorList;
}
