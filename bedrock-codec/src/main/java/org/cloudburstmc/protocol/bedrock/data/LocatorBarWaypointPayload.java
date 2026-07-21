package org.cloudburstmc.protocol.bedrock.data;

import lombok.Data;

@Data
public class LocatorBarWaypointPayload {
  private WaypointGroupWaypointHandle groupHandle;

  private ServerWaypointPayload serverWaypointPayload;

  private ServerWaypointGroupAction actionFlag;
}
