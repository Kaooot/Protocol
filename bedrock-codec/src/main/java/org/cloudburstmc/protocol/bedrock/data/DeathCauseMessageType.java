package org.cloudburstmc.protocol.bedrock.data;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import java.lang.String;
import java.util.List;
import lombok.Data;

@Data
public class DeathCauseMessageType {
  private String deathCauseAttackName;

  private final List<String> deathCauseMessageList = new ObjectArrayList<>();
}
