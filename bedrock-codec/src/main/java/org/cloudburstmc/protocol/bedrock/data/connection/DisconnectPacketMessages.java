package org.cloudburstmc.protocol.bedrock.data.connection;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DisconnectPacketMessages {

    private String message;
    private String filteredMessage;
}