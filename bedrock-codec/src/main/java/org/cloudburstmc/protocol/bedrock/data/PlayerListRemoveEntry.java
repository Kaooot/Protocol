package org.cloudburstmc.protocol.bedrock.data;

import java.util.UUID;
import lombok.Data;

@Data
public class PlayerListRemoveEntry {
  private Action action;

  private UUID uuid;
}
