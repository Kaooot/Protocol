package org.cloudburstmc.protocol.bedrock.data;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import java.util.List;
import lombok.Data;

@Data
public class AvailableCommandsPacketOverloadData {
  private boolean isChaining;

  private final List<AvailableCommandsPacketParamData> parameterData = new ObjectArrayList<>();
}
