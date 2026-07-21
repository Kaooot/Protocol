package org.cloudburstmc.protocol.bedrock.packet;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import java.lang.AssertionError;
import java.lang.CloneNotSupportedException;
import java.lang.Long;
import java.lang.Override;
import java.lang.String;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.cloudburstmc.protocol.common.PacketSignal;

/**
 * Packet ID: 158 (0x9e)
 */
@Data
@EqualsAndHashCode(
    doNotUseGetters = true
)
@ToString(
    doNotUseGetters = true
)
public class AnimateEntityPacket implements BedrockPacket {
  private String mAnimation;

  private String mNextState;

  private String mStopExpression;

  private int mStopExpressionVersion;

  private String mController;

  private float mBlendOutTime;

  private final List<Long> mRuntimeIds = new ObjectArrayList<>();

  @Override
  public final PacketSignal handle(BedrockPacketHandler handler) {
    return handler.handle(this);
  }

  @Override
  public BedrockPacketType getPacketType() {
    return BedrockPacketType.ANIMATE_ENTITY;
  }

  @Override
  public AnimateEntityPacket clone() {
    try {
      return (AnimateEntityPacket) super.clone();
    } catch (CloneNotSupportedException e) {
      throw new AssertionError(e);
    }
  }
}
