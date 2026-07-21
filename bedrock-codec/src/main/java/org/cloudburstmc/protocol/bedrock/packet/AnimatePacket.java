package org.cloudburstmc.protocol.bedrock.packet;

import java.lang.AssertionError;
import java.lang.CloneNotSupportedException;
import java.lang.Override;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.cloudburstmc.protocol.bedrock.data.ActorSwingSource;
import org.cloudburstmc.protocol.bedrock.data.AnimatePacketPayloadAction;
import org.cloudburstmc.protocol.common.PacketSignal;

/**
 * Packet ID: 44 (0x2c)
 * Combination of server bound and client bound packets to trigger animations.
 */
@Data
@EqualsAndHashCode(
    doNotUseGetters = true
)
@ToString(
    doNotUseGetters = true
)
public class AnimatePacket implements BedrockPacket {
  private AnimatePacketPayloadAction action;

  private long targetActorRuntimeID;

  private float data;

  private ActorSwingSource swingSource;

  @Override
  public final PacketSignal handle(BedrockPacketHandler handler) {
    return handler.handle(this);
  }

  @Override
  public BedrockPacketType getPacketType() {
    return BedrockPacketType.ANIMATE;
  }

  @Override
  public AnimatePacket clone() {
    try {
      return (AnimatePacket) super.clone();
    } catch (CloneNotSupportedException e) {
      throw new AssertionError(e);
    }
  }
}
