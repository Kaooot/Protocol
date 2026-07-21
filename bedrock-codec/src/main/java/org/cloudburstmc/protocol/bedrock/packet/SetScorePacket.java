package org.cloudburstmc.protocol.bedrock.packet;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import java.lang.AssertionError;
import java.lang.CloneNotSupportedException;
import java.lang.Object;
import java.lang.Override;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.cloudburstmc.protocol.common.PacketSignal;

/**
 * Packet ID: 108 (0x6c)
 */
@Data
@EqualsAndHashCode(
    doNotUseGetters = true
)
@ToString(
    doNotUseGetters = true
)
public class SetScorePacket implements BedrockPacket {
  private final List<Object> scoreInfo = new ObjectArrayList<>();

  @Override
  public final PacketSignal handle(BedrockPacketHandler handler) {
    return handler.handle(this);
  }

  @Override
  public BedrockPacketType getPacketType() {
    return BedrockPacketType.SET_SCORE;
  }

  @Override
  public SetScorePacket clone() {
    try {
      return (SetScorePacket) super.clone();
    } catch (CloneNotSupportedException e) {
      throw new AssertionError(e);
    }
  }
}
