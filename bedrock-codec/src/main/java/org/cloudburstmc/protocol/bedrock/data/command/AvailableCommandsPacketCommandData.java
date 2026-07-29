package org.cloudburstmc.protocol.bedrock.data.command;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import java.lang.Integer;
import java.lang.String;
import java.util.List;
import lombok.Data;
import org.cloudburstmc.protocol.bedrock.data.CommandPermissionLevel;

@Data
public class AvailableCommandsPacketCommandData {
  private String name;

  private String description;

  private int flags;

  private CommandPermissionLevel permissionLevel;

  private int aliasEnum;

  private final List<Integer> commandDataChainedSubcommandIndexes = new ObjectArrayList<>();

  private final List<AvailableCommandsPacketOverloadData> overloads = new ObjectArrayList<>();
}
