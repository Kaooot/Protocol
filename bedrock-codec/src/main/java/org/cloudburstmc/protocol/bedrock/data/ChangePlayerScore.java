package org.cloudburstmc.protocol.bedrock.data;

import java.lang.String;
import lombok.Data;

@Data
public class ChangePlayerScore {
  private ScorePacketEntryAction action;

  private ScoreboardId scoreboardId;

  private String objectiveName;

  private int scoreValue;

  private PlayerScoreboardId playerUniqueId;
}
