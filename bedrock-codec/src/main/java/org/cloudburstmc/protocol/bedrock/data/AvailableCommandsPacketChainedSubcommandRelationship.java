package org.cloudburstmc.protocol.bedrock.data;

import lombok.Data;

@Data
public class AvailableCommandsPacketChainedSubcommandRelationship {
  private int subCommandFirstValue;

  private int subCommandSecondValue;
}
