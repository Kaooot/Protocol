package org.cloudburstmc.protocol.bedrock.data;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import lombok.Data;
import org.cloudburstmc.protocol.bedrock.docs.DataType;
import org.cloudburstmc.protocol.bedrock.docs.Docs;

import java.util.List;

/**
 * @author Kaooot
 */
@Docs
@Data
public class GameRulesChangedPacketData {

    @Docs(type = DataType.ARRAY, linkType = Docs.LinkType.MEMBERS)
    private final List<GameRuleData<?>> rulesList = new ObjectArrayList<>();
}