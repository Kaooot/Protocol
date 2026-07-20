package org.cloudburstmc.protocol.bedrock.data;

import java.lang.String;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Auto generated from 1.26.40-beta.31 (v2168)
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChangeFakePlayerScore {
  private ScorePacketEntryAction action;

  private ScoreboardId scoreboardId;

  private String objectiveName;

  private int scoreValue;

  private String fakePlayerName;
}
