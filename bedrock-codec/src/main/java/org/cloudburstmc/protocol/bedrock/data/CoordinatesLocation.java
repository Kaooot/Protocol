package org.cloudburstmc.protocol.bedrock.data;

import lombok.Data;
import org.cloudburstmc.math.vector.Vector3f;

@Data
public class CoordinatesLocation {
  private PacketType packetType;

  private Vector3f position;
}
