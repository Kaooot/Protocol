package org.cloudburstmc.protocol.bedrock.data.connection;

import lombok.Data;

@Data
public class PresenceConfig {

    /**
     * @deprecated since v2168
     */
    private String experienceName;
    /**
     * @deprecated since v2168
     */
    private String worldName;
    /**
     * @since v1001
     */
    private String richPresenceId;
}