package org.cloudburstmc.protocol.bedrock.data.world;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import lombok.Data;

import java.util.List;

@Data
public class GameRulesChangedPacketData {

    private final List<GameRule> rulesList = new ObjectArrayList<>();
}