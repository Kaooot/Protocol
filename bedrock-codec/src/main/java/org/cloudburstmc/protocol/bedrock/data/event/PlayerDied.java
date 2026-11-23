package org.cloudburstmc.protocol.bedrock.data.event;

import lombok.Data;
import org.cloudburstmc.protocol.bedrock.annotation.Added;

/**
 * Provides information about a player death
 */
@Data
public class PlayerDied {

    /**
     * ActorUniqueID of the instigator
     */
    private int instigatorActorID;
    /**
     * Mob variant of the instigator
     */
    private int instigatorMobVariant;
    /**
     * Damage source, TODO: add enum reference
     */
    private int damageSource;
    /**
     * Whether the player died whilst being in a village raid
     */
    @Added("1.14.0")
    private boolean diedInRaid;
}