package org.cloudburstmc.protocol.bedrock.data.event;

import lombok.Data;

@Data
public class AgentCommand {

    private AgentResult result;
    private String command;
    private String dataKey;
    private int dataValue;
    private String output;
}