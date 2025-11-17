package org.cloudburstmc.protocol.bedrock.data.payload.text;

import lombok.Data;
import lombok.ToString;
import org.cloudburstmc.protocol.bedrock.data.TextPacketBodyType;

/**
 * @author Kaooot
 */
@Data
@ToString
public class AuthorAndMessage implements TextPacketBody {

    private String playerName;
    private String message;

    @Override
    public TextPacketBodyType getType() {
        return TextPacketBodyType.AUTHOR_AND_MESSAGE;
    }
}