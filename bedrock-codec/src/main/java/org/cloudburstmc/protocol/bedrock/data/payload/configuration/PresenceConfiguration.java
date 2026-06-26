package org.cloudburstmc.protocol.bedrock.data.payload.configuration;

import lombok.Data;

/**
 * @author Kaooot
 */
@Data
public class PresenceConfiguration {

    /**
     * @deprecated since v2164
     */
    private String experienceName;
    /**
     * @deprecated since v2164
     */
    private String worldName;
    /**
     * @since v1001
     */
    private String richPresenceId;
}