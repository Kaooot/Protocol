package org.cloudburstmc.protocol.bedrock.data.payload.connection;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author Kaooot
 */
@Data
@AllArgsConstructor
public class DisconnectPacketMessages {

    private String message = "";
    /**
     * @since v712
     */
    private String filteredMessage = "";
}