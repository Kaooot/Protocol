package org.cloudburstmc.protocol.bedrock.data;

import lombok.Data;
import org.cloudburstmc.protocol.common.util.OptionalBoolean;

/**
 * @author Kaooot
 */
@Data
public class ServerWaypointPayload {

    private int updateFlag;
    private OptionalBoolean isVisible = OptionalBoolean.empty();
    private WorldPosition worldPosition;
    private Integer textureId;
    private Integer color;
    private OptionalBoolean clientPositionAuthority = OptionalBoolean.empty();
    private Long actorUniqueID;
}