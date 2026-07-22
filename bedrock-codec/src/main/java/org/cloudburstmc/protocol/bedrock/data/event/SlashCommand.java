package org.cloudburstmc.protocol.bedrock.data.event;

import java.lang.String;
import lombok.Data;

/**
 * Sent as soon as a slash command is executed
 */
@Data
public class SlashCommand {
  /**
   * number of successful executions
   */
  private int successCount;

  /**
   * number of failed executions, see Error List for details
   */
  private int errorCount;

  /**
   * The name of the command which has been executed
   */
  private String commandName;

  /**
   * List of localized error messages, empty if Error Count = 0
   */
  private String errorList;
}
