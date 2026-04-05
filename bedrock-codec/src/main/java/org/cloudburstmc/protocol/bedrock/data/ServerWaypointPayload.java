package org.cloudburstmc.protocol.bedrock.data;

import lombok.Data;
import org.cloudburstmc.math.vector.Vector2f;
import org.cloudburstmc.protocol.common.util.OptionalBoolean;

/**
 * @author Kaooot
 */
@Data
public class ServerWaypointPayload {

    private int updateFlag;
    private OptionalBoolean isVisible = OptionalBoolean.empty();
    private WorldPosition worldPosition;
    /**
     * @deprecated since v972
     */
    private Integer textureId;
    /**
     * @since v972
     */
    private String texturePath;
    /**
     * @since v972
     */
    private Vector2f iconSize;
    private Integer color;
    private OptionalBoolean clientPositionAuthority = OptionalBoolean.empty();
    private Long actorUniqueID;
}