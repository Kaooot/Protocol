package org.cloudburstmc.protocol.bedrock.data;

import java.lang.Long;
import lombok.Data;

@Data
public class ScoreboardIdentityPacketInfo {
  private ScoreboardId scoreboardId;

  private Long playerUniqueId;
}
