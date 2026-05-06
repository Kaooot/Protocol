package org.cloudburstmc.protocol.bedrock.data.payload.configuration;

import lombok.Data;

/**
 * @author Kaooot
 */
@Data
public class PresenceConfiguration {

    private String experienceName;
    private String worldName;
    /**
     * @since v986
     */
    private String richPresenceId;
}