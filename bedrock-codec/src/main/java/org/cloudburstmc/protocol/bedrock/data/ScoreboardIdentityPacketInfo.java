package org.cloudburstmc.protocol.bedrock.data;

import java.lang.Long;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ScoreboardIdentityPacketInfo {
  private ScoreboardId scoreboardId;

  private Long playerUniqueId;
}
