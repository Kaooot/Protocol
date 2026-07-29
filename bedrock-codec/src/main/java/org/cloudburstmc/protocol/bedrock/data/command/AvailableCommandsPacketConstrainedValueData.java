package org.cloudburstmc.protocol.bedrock.data.command;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import java.lang.Integer;
import java.util.List;
import lombok.Data;

@Data
public class AvailableCommandsPacketConstrainedValueData {
  private int enumValueSymbol;

  private int enumSymbol;

  private final List<Integer> constraintIndices = new ObjectArrayList<>();
}
