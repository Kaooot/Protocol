package org.cloudburstmc.protocol.bedrock.data.prediction;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import java.lang.Integer;
import java.util.List;
import lombok.Data;

/**
 * Contains the Actor Flag Bitset. Is used as part of the ClientMovementPredictionSyncPacket
 *
 * @since v776
 */
@Data
public class ActorDataFlagComponent {
  /**
   * Actor Flag bitset data to inform the server of the client-side ActorFlags
   */
  private final List<Integer> actorFlagBitsetData = new ObjectArrayList<>();
}
