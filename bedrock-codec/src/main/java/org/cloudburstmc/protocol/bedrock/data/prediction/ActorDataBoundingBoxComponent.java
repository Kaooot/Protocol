package org.cloudburstmc.protocol.bedrock.data.prediction;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import java.lang.Float;
import java.util.List;
import lombok.Data;

/**
 * Contains the Actor Data Bounding Box. Is used as part of the ClientMovementPredictionSyncPacket
 *
 * @since v776
 */
@Data
public class ActorDataBoundingBoxComponent {
  /**
   * The actor's bounding box, contains 3 elements: the x, y and z components
   */
  private final List<Float> actorDataBoundingBox = new ObjectArrayList<>();
}
