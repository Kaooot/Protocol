package org.cloudburstmc.protocol.bedrock.data.command;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import lombok.Data;

import java.util.List;

@Data
public class CommandOutputMessage {

    private String messageID;
    private boolean successful;
    private final List<String> parameters = new ObjectArrayList<>();
}