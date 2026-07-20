package org.cloudburstmc.protocol.bedrock.packet;

import java.lang.AssertionError;
import java.lang.CloneNotSupportedException;
import java.lang.Override;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.cloudburstmc.protocol.common.PacketSignal;

/**
 * Auto generated from 1.26.40-beta.31 (v2168)
 *
 * Packet ID: 130 (0x82)
 * Sent from the player (and in one case from the village) to make those really cool animated effects for the hero of the village and the totem saving you. Just an id (unsigned int). At least thats what the code suggests. May be obsolete / deprecated.
 */
@Data
@EqualsAndHashCode(
    doNotUseGetters = true
)
@ToString(
    doNotUseGetters = true
)
public class OnScreenTextureAnimationPacket implements BedrockPacket {
  private int effectId;

  @Override
  public final PacketSignal handle(BedrockPacketHandler handler) {
    return handler.handle(this);
  }

  @Override
  public BedrockPacketType getPacketType() {
    return BedrockPacketType.ON_SCREEN_TEXTURE_ANIMATION;
  }

  @Override
  public OnScreenTextureAnimationPacket clone() {
    try {
      return (OnScreenTextureAnimationPacket) super.clone();
    } catch (CloneNotSupportedException e) {
      throw new AssertionError(e);
    }
  }
}
