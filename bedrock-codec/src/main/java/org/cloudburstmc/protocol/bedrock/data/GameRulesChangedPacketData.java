package org.cloudburstmc.protocol.bedrock.data;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import java.util.List;
import lombok.Data;

@Data
public class GameRulesChangedPacketData {
  private final List<GameRule> rulesList = new ObjectArrayList<>();
}
