package org.cloudburstmc.protocol.bedrock.data.world;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GameRule {

    private String ruleName;
    private boolean ruleCanBeModified;
    private Object ruleValue;
}