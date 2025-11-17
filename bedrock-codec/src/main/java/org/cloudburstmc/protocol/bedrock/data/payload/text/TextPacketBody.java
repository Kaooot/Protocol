package org.cloudburstmc.protocol.bedrock.data.payload.text;

import org.cloudburstmc.protocol.bedrock.data.TextPacketBodyType;

/**
 * @author Kaooot
 */
public interface TextPacketBody {

    String getMessage();

    void setMessage(String message);

    TextPacketBodyType getType();
}