package org.cloudburstmc.protocol.bedrock.packet;

import java.lang.AssertionError;
import java.lang.CloneNotSupportedException;
import java.lang.Override;
import java.lang.String;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.cloudburstmc.protocol.bedrock.data.NpcRequestPacketPayloadRequestType;
import org.cloudburstmc.protocol.common.PacketSignal;

/**
 * Auto generated from 1.26.40-beta.31 (v2168)
 *
 * Packet ID: 98 (0x62)
 * A request is made from the client during an interaction with an NPC then the request is processed by the server. 
 * Actor MUST have the NPCComponent to be handled. 
 * We currently only use this for EDU, but the goal was to expose the NPC Component to creators.
 */
@Data
@EqualsAndHashCode(
    doNotUseGetters = true
)
@ToString(
    doNotUseGetters = true
)
public class NpcRequestPacket implements BedrockPacket {
  private long npcRuntimeID;

  private NpcRequestPacketPayloadRequestType requestType;

  private String actions;

  private int actionIndex;

  private String sceneName;

  @Override
  public final PacketSignal handle(BedrockPacketHandler handler) {
    return handler.handle(this);
  }

  @Override
  public BedrockPacketType getPacketType() {
    return BedrockPacketType.NPC_REQUEST;
  }

  @Override
  public NpcRequestPacket clone() {
    try {
      return (NpcRequestPacket) super.clone();
    } catch (CloneNotSupportedException e) {
      throw new AssertionError(e);
    }
  }
}
