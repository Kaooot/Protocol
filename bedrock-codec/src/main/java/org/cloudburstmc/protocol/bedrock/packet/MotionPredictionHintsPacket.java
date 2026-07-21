package org.cloudburstmc.protocol.bedrock.packet;

import java.lang.AssertionError;
import java.lang.CloneNotSupportedException;
import java.lang.Override;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.cloudburstmc.math.vector.Vector3f;
import org.cloudburstmc.protocol.common.PacketSignal;

/**
 * Packet ID: 157 (0x9d)
 * This is from the server when spatial optimizations are enabled and the server does not send a spatial update.
 */
@Data
@EqualsAndHashCode(
    doNotUseGetters = true
)
@ToString(
    doNotUseGetters = true
)
public class MotionPredictionHintsPacket implements BedrockPacket {
  private long mRuntimeId;

  private Vector3f mMotion;

  private boolean mOnGround;

  @Override
  public final PacketSignal handle(BedrockPacketHandler handler) {
    return handler.handle(this);
  }

  @Override
  public BedrockPacketType getPacketType() {
    return BedrockPacketType.MOTION_PREDICTION_HINTS;
  }

  @Override
  public MotionPredictionHintsPacket clone() {
    try {
      return (MotionPredictionHintsPacket) super.clone();
    } catch (CloneNotSupportedException e) {
      throw new AssertionError(e);
    }
  }
}
