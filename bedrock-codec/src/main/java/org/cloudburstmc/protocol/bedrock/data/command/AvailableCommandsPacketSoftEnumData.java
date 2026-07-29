package org.cloudburstmc.protocol.bedrock.data.command;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import java.lang.String;
import java.util.List;
import lombok.Data;

@Data
public class AvailableCommandsPacketSoftEnumData {
  private String enumName;

  private final List<String> enumOptions = new ObjectArrayList<>();
}
