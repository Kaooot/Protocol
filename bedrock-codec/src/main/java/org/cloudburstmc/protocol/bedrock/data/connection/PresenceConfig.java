package org.cloudburstmc.protocol.bedrock.data.connection;

import lombok.Data;
import org.cloudburstmc.protocol.bedrock.annotation.Added;
import org.cloudburstmc.protocol.bedrock.annotation.Removed;

@Data
public class PresenceConfig {

    @Deprecated
    @Removed("26.40")
    private String experienceName;
    @Deprecated
    @Removed("26.40")
    private String worldName;
    @Added("26.30")
    private String richPresenceId;
}