package org.cloudburstmc.protocol.bedrock.data.world;

import lombok.Data;
import org.cloudburstmc.protocol.bedrock.annotation.Added;

/**
 * AdventureSettings for UpdateAdventureSettingsPacket
 */
@Data
@Added("1.19.10")
public class AdventureSettings {

    /**
     * Whether player vs mob is enabled
     */
    private boolean noPvm;
    /**
     * Whether mob vs player is enabled
     */
    private boolean noMvp;
    /**
     * True when the world is immutable, false otherwise
     */
    private boolean immutableWorld;
    /**
     * If name tags should be shown
     */
    private boolean showNameTags;
    /**
     * Whether auto jumping should be allowed
     */
    private boolean autoJump;
}