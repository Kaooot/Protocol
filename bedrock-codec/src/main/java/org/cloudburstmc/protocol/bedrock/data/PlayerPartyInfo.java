package org.cloudburstmc.protocol.bedrock.data;

import java.lang.String;
import lombok.Data;

@Data
public class PlayerPartyInfo {
  private String partyId;

  private boolean isPartyLeader;
}
