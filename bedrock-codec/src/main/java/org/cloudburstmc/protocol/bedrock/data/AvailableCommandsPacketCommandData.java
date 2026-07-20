package org.cloudburstmc.protocol.bedrock.data;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import java.lang.Integer;
import java.lang.String;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Auto generated from 1.26.40-beta.31 (v2168)
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AvailableCommandsPacketCommandData {
  private String name;

  private String description;

  private int flags;

  private CommandPermissionLevel permissionLevel;

  private int aliasEnum;

  private final List<Integer> commandDataChainedSubcommandIndexes = new ObjectArrayList<>();

  private final List<AvailableCommandsPacketOverloadData> overloads = new ObjectArrayList<>();
}
