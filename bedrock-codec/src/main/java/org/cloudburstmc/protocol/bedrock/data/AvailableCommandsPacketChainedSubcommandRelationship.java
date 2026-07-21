package org.cloudburstmc.protocol.bedrock.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AvailableCommandsPacketChainedSubcommandRelationship {
  private int subCommandFirstValue;

  private int subCommandSecondValue;
}
