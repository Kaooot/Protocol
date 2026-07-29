package org.cloudburstmc.protocol.bedrock.data.command;

import lombok.Data;

@Data
public class AvailableCommandsPacketChainedSubcommandRelationship {
  private int subCommandFirstValue;

  private int subCommandSecondValue;
}
