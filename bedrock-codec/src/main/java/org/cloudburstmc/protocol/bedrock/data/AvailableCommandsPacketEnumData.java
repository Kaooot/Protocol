package org.cloudburstmc.protocol.bedrock.data;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import java.lang.Integer;
import java.lang.String;
import java.util.List;
import lombok.Data;

@Data
public class AvailableCommandsPacketEnumData {
  private String name;

  private final List<Integer> values = new ObjectArrayList<>();
}
