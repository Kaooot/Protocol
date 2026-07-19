package org.cloudburstmc.protocol.bedrock.packet;

import java.lang.AssertionError;
import java.lang.CloneNotSupportedException;
import java.lang.Object;
import java.lang.Override;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.cloudburstmc.protocol.common.PacketSignal;

/**
 * Auto generated from 1.26.40-beta.31 (v2168)
 *
 * Packet ID: 325 (0x145)
 * Updates client entity property override data. Sets/removes an override for the indicated property for a specific entity on a client or clears all overrides for that entity.
 */
@Data
@EqualsAndHashCode(
    doNotUseGetters = true
)
@ToString(
    doNotUseGetters = true
)
public class PlayerUpdateEntityOverridesPacket implements BedrockPacket {
  private long TargetID;

  private int PropertyIndex;

  private Object Update;

  @Override
  public final PacketSignal handle(BedrockPacketHandler handler) {
    return handler.handle(this);
  }

  @Override
  public BedrockPacketType getPacketType() {
    return BedrockPacketType.PLAYER_UPDATE_ENTITY_OVERRIDES;
  }

  @Override
  public PlayerUpdateEntityOverridesPacket clone() {
    try {
      return (PlayerUpdateEntityOverridesPacket) super.clone();
    } catch (CloneNotSupportedException e) {
      throw new AssertionError(e);
    }
  }
}
