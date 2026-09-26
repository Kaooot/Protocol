package org.cloudburstmc.protocol.bedrock.data.command;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import lombok.Data;

import java.util.List;

@Data
public class CommandOutput {

    private CommandOutputType outputType;
    private int successCount;
    private final List<CommandOutputMessage> outputMessages = new ObjectArrayList<>();
    private String dataSet;
}