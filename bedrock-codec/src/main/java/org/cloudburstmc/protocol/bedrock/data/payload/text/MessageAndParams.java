package org.cloudburstmc.protocol.bedrock.data.payload.text;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import lombok.Data;
import lombok.ToString;
import org.cloudburstmc.protocol.bedrock.data.TextPacketBodyType;

import java.util.List;

/**
 * @author Kaooot
 */
@Data
@ToString
public class MessageAndParams implements TextPacketBody {

    private String message;
    private final List<String> parameterList = new ObjectArrayList<>();

    @Override
    public TextPacketBodyType getType() {
        return TextPacketBodyType.MESSAGE_AND_PARAMS;
    }
}