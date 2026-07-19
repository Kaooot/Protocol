package org.cloudburstmc.protocol.bedrock.packet;

import java.lang.AssertionError;
import java.lang.CloneNotSupportedException;
import java.lang.Override;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.protocol.bedrock.data.PositionTrackingDBServerBroadcastPacketPayloadAction;
import org.cloudburstmc.protocol.bedrock.data.PositionTrackingId;
import org.cloudburstmc.protocol.common.PacketSignal;

/**
 * Auto generated from 1.26.40-beta.31 (v2168)
 *
 * Packet ID: 153 (0x99)
 */
@Data
@EqualsAndHashCode(
    doNotUseGetters = true
)
@ToString(
    doNotUseGetters = true
)
public class PositionTrackingDBServerBroadcastPacket implements BedrockPacket {
  private PositionTrackingDBServerBroadcastPacketPayloadAction Action;

  private PositionTrackingId Id;

  private NbtMap Positiontrackingdata;

  @Override
  public final PacketSignal handle(BedrockPacketHandler handler) {
    return handler.handle(this);
  }

  @Override
  public BedrockPacketType getPacketType() {
    return BedrockPacketType.POSITION_TRACKING_D_B_SERVER_BROADCAST;
  }

  @Override
  public PositionTrackingDBServerBroadcastPacket clone() {
    try {
      return (PositionTrackingDBServerBroadcastPacket) super.clone();
    } catch (CloneNotSupportedException e) {
      throw new AssertionError(e);
    }
  }
}
