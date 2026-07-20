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
 * Packet ID: 38 (0x26)
 * Sends the damage taken after armor is taken into account. This looks like it is trying to be phased out, this is not sent while the ItemStackNetManagerServer is active. From the server.
 */
@Data
@EqualsAndHashCode(
    doNotUseGetters = true
)
@ToString(
    doNotUseGetters = true
)
public class HurtArmorPacket implements BedrockPacket {
  private int cause;

  private int damage;

  private long armorSlots;

  @Override
  public final PacketSignal handle(BedrockPacketHandler handler) {
    return handler.handle(this);
  }

  @Override
  public BedrockPacketType getPacketType() {
    return BedrockPacketType.HURT_ARMOR;
  }

  @Override
  public HurtArmorPacket clone() {
    try {
      return (HurtArmorPacket) super.clone();
    } catch (CloneNotSupportedException e) {
      throw new AssertionError(e);
    }
  }
}
