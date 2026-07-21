package org.cloudburstmc.protocol.bedrock.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LocatorBarWaypointPayload {
  private WaypointGroupWaypointHandle groupHandle;

  private ServerWaypointPayload serverWaypointPayload;

  private ServerWaypointGroupAction actionFlag;
}
