package org.cloudburstmc.protocol.bedrock.data.command;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import lombok.Data;
import org.cloudburstmc.protocol.bedrock.annotation.Added;
import org.cloudburstmc.protocol.bedrock.data.attribute.StringAttributeData;

import java.util.List;

@Data
public class CommandOutputMessage {

    private String messageID;
    private boolean successful;
    private final List<String> parameters = new ObjectArrayList<>();
    @Added("26.70")
    private String rawMessage;
}