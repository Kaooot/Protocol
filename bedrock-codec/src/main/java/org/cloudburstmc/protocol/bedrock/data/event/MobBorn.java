package org.cloudburstmc.protocol.bedrock.data.event;

import lombok.Data;
import org.cloudburstmc.protocol.bedrock.annotation.Added;

/**
 * Sent as soon as a new mob is born
 */
@Data
@Added("1.9.0")
public class MobBorn {

    /**
     * The ActorType of the mob
     */
    private int bornBabyEntityType;
    /**
     * Actor variant
     */
    private int bornBabyEntityVariant;
    /**
     * Color of the born mob
     */
    private int bornBabyColor;
}