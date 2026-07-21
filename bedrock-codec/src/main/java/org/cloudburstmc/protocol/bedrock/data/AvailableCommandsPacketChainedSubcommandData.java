package org.cloudburstmc.protocol.bedrock.data;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import java.lang.String;
import java.util.List;
import lombok.Data;

@Data
public class AvailableCommandsPacketChainedSubcommandData {
  private String name;

  private final List<AvailableCommandsPacketChainedSubcommandRelationship> subCommandValues = new ObjectArrayList<>();
}
