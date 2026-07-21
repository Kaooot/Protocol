package org.cloudburstmc.protocol.bedrock.packet;

import java.lang.AssertionError;
import java.lang.CloneNotSupportedException;
import java.lang.Override;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.cloudburstmc.math.vector.Vector3f;
import org.cloudburstmc.protocol.bedrock.data.ActorEvent;
import org.cloudburstmc.protocol.common.PacketSignal;

/**
 * Packet ID: 27 (0x1b)
 */
@Data
@EqualsAndHashCode(
    doNotUseGetters = true
)
@ToString(
    doNotUseGetters = true
)
public class ActorEventPacket implements BedrockPacket {
  private long targetRuntimeID;

  private ActorEvent eventID;

  private int data;

  private Vector3f fireAtPosition;

  @Override
  public final PacketSignal handle(BedrockPacketHandler handler) {
    return handler.handle(this);
  }

  @Override
  public BedrockPacketType getPacketType() {
    return BedrockPacketType.ACTOR_EVENT;
  }

  @Override
  public ActorEventPacket clone() {
    try {
      return (ActorEventPacket) super.clone();
    } catch (CloneNotSupportedException e) {
      throw new AssertionError(e);
    }
  }
}
