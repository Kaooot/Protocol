package org.cloudburstmc.protocol.bedrock.data.actor.link;

import lombok.Data;
import org.cloudburstmc.protocol.bedrock.annotation.Added;

/**
 * An ActorLink describes the actor's mounting state, which actors are involved
 * and other data required for correct rendering on the client side
 */
@Data
public class ActorLink {

    /**
     * The passenger's ActorUniqueID
     */
    private long targetA;
    /**
     * The rider's ActorUniqueID
     */
    private long targetB;
    /**
     * 'RIDING' is generally used to describe a mounted state,
     * whilst 'NONE' should be used for dismounting
     */
    private ActorLinkType type;
    /**
     * Whether the ActorLink is established immediately. Defaults to false
     */
    private boolean immediate;
    /**
     * Whether the ActorLink was initiated by the passenger
     */
    @Added("1.16.0")
    private boolean passengerInitiated;
    /**
     * The angular velocity of the vehicle. Sent when riding a vehicle such as a boat. Defaults to 0
     */
    @Added("1.21.20")
    private float vehicleAngularVelocity;
}