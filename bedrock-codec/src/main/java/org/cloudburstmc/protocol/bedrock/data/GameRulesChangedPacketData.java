package org.cloudburstmc.protocol.bedrock.data;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import lombok.Data;

import java.util.List;

/**
 * @author Kaooot
 */
@Data
public class GameRulesChangedPacketData {

    private final List<GameRuleData<?>> rulesList = new ObjectArrayList<>();
}