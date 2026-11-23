package org.cloudburstmc.protocol.bedrock.data.prediction;

import it.unimi.dsi.fastutil.objects.ObjectOpenHashSet;
import lombok.Data;
import org.cloudburstmc.protocol.bedrock.annotation.Added;
import org.cloudburstmc.protocol.bedrock.data.actor.ActorFlags;

import java.util.Set;

/**
 * Contains the Actor Flag Bitset. Is used as part of the ClientMovementPredictionSyncPacket
 */
@Data
@Added("1.21.60")
public class ActorDataFlagComponent {

    /**
     * Actor Flag bitset data to inform the server of the client-side ActorFlags
     */
    private final Set<ActorFlags> actorFlagBitsetData = new ObjectOpenHashSet<>();
}