package org.cloudburstmc.protocol.bedrock.data;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import java.lang.String;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AvailableCommandsPacketChainedSubcommandData {
  private String name;

  private final List<AvailableCommandsPacketChainedSubcommandRelationship> subCommandValues = new ObjectArrayList<>();
}
