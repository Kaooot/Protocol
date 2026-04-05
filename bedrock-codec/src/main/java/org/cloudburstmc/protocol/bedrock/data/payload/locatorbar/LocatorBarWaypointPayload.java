package org.cloudburstmc.protocol.bedrock.data.payload.locatorbar;

import lombok.Data;

import java.util.UUID;

/**
 * @author Kaooot
 */
@Data
public class LocatorBarWaypointPayload {

    private UUID groupHandle;
    private ServerWaypointPayload serverWaypointPayload;
    private ServerWaypointGroupAction actionFlag;
}