package org.cloudburstmc.protocol.bedrock.data.payload.locatorbar;

import it.unimi.dsi.fastutil.objects.ObjectOpenHashSet;
import lombok.Data;
import org.cloudburstmc.protocol.bedrock.data.WorldPosition;
import org.cloudburstmc.math.vector.Vector2f;
import org.cloudburstmc.protocol.common.util.OptionalBoolean;

import java.util.Set;

/**
 * @author Kaooot
 */
@Data
public class ServerWaypointPayload {

    private final Set<VanillaWaypointManagerConstants.UpdateFlag> updateFlags = new ObjectOpenHashSet<>();
    private OptionalBoolean isVisible = OptionalBoolean.empty();
    private WorldPosition worldPosition;
    /**
     * @deprecated since v974
     */
    private VanillaWaypointManagerConstants.ImageType textureId;
    /**
     * @since v974
     */
    private String texturePath;
    /**
     * @since v974
     */
    private Vector2f iconSize;
    private Integer color;
    private OptionalBoolean clientPositionAuthority = OptionalBoolean.empty();
    private Long actorUniqueID;
}