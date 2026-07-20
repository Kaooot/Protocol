package org.cloudburstmc.protocol.bedrock.packet;

import java.lang.AssertionError;
import java.lang.CloneNotSupportedException;
import java.lang.Override;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.cloudburstmc.protocol.bedrock.data.PositionTrackingDBClientRequestPacketPayloadAction;
import org.cloudburstmc.protocol.bedrock.data.PositionTrackingId;
import org.cloudburstmc.protocol.common.PacketSignal;

/**
 * Auto generated from 1.26.40-beta.31 (v2168)
 *
 * Packet ID: 154 (0x9a)
 * Client to server packet for server authoratative runtime database (with persistent LevelStorage backup) designed primarily to track lodestone stuff. See Position Tracking DB Notes.md in bedrock-docs. see PositionTrackingDBServerBroadcastPacket
 */
@Data
@EqualsAndHashCode(
    doNotUseGetters = true
)
@ToString(
    doNotUseGetters = true
)
public class PositionTrackingDBClientRequestPacket implements BedrockPacket {
  private PositionTrackingDBClientRequestPacketPayloadAction action;

  private PositionTrackingId id;

  @Override
  public final PacketSignal handle(BedrockPacketHandler handler) {
    return handler.handle(this);
  }

  @Override
  public BedrockPacketType getPacketType() {
    return BedrockPacketType.POSITION_TRACKING_D_B_CLIENT_REQUEST;
  }

  @Override
  public PositionTrackingDBClientRequestPacket clone() {
    try {
      return (PositionTrackingDBClientRequestPacket) super.clone();
    } catch (CloneNotSupportedException e) {
      throw new AssertionError(e);
    }
  }
}
