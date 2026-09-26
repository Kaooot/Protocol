package org.cloudburstmc.protocol.bedrock.data.prediction;

import lombok.Data;
import org.cloudburstmc.math.vector.Vector3f;

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
    private Vector3f actorDataBoundingBox;
}