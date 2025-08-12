package org.cloudburstmc.protocol.bedrock.data.actor;

import lombok.AllArgsConstructor;
import lombok.Value;
import org.cloudburstmc.protocol.bedrock.data.ActorLinkType;

@Value
@AllArgsConstructor
public class ActorLink {
    private final long targetA;
    private final long targetB;
    private final ActorLinkType type;
    private final boolean immediate;
    private final boolean passengerInitiated;
    /**
     * @since v712
     */
    private final float vehicleAngularVelocity;

    @Deprecated
    public ActorLink(long targetA, long targetB, ActorLinkType type, boolean immediate) {
        this(targetA, targetB, type, immediate, false, 0f);
    }

    @Deprecated
    public ActorLink(long targetA, long targetB, ActorLinkType type, boolean immediate, boolean passengerInitiated) {
        this(targetA, targetB, type, immediate, passengerInitiated, 0f);
    }
}