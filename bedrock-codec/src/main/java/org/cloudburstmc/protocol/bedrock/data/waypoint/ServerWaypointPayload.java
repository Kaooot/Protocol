package org.cloudburstmc.protocol.bedrock.data.waypoint;

import lombok.Data;
import org.cloudburstmc.math.vector.Vector2f;
import org.cloudburstmc.protocol.bedrock.data.world.WorldPosition;
import org.cloudburstmc.protocol.common.util.OptionalBoolean;

import java.awt.Color;

@Data
public class ServerWaypointPayload {

    private int updateFlag;
    private OptionalBoolean isVisible = OptionalBoolean.empty();
    private WorldPosition worldPosition;
    /**
     * @since v944, replaced by {@link #texturePath} + {@link #iconSize} in v975
     */
    private VanillaWaypointManagerConstants.ImageType textureId;
    private String texturePath;
    private Vector2f iconSize;
    private Color color;
    private OptionalBoolean clientPositionAuthority = OptionalBoolean.empty();
    private Long actorUniqueID;
}