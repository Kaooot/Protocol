package org.cloudburstmc.protocol.bedrock.packet;

import java.lang.AssertionError;
import java.lang.CloneNotSupportedException;
import java.lang.Override;
import java.lang.String;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.cloudburstmc.protocol.bedrock.data.AgentActionType;
import org.cloudburstmc.protocol.common.PacketSignal;

/**
 * Packet ID: 181 (0xb5)
 */
@Data
@EqualsAndHashCode(
    doNotUseGetters = true
)
@ToString(
    doNotUseGetters = true
)
public class AgentActionEventPacket implements BedrockPacket {
  private String requestId;

  private AgentActionType action;

  private String response;

  @Override
  public final PacketSignal handle(BedrockPacketHandler handler) {
    return handler.handle(this);
  }

  @Override
  public BedrockPacketType getPacketType() {
    return BedrockPacketType.AGENT_ACTION_EVENT;
  }

  @Override
  public AgentActionEventPacket clone() {
    try {
      return (AgentActionEventPacket) super.clone();
    } catch (CloneNotSupportedException e) {
      throw new AssertionError(e);
    }
  }
}
