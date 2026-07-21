package org.cloudburstmc.protocol.bedrock.packet;

import java.lang.AssertionError;
import java.lang.CloneNotSupportedException;
import java.lang.Override;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.cloudburstmc.protocol.bedrock.data.MultiplayerSettingsPacketType;
import org.cloudburstmc.protocol.common.PacketSignal;

/**
 * Packet ID: 139 (0x8b)
 */
@Data
@EqualsAndHashCode(
    doNotUseGetters = true
)
@ToString(
    doNotUseGetters = true
)
public class MultiplayerSettingsPacket implements BedrockPacket {
  private MultiplayerSettingsPacketType packetType;

  @Override
  public final PacketSignal handle(BedrockPacketHandler handler) {
    return handler.handle(this);
  }

  @Override
  public BedrockPacketType getPacketType() {
    return BedrockPacketType.MULTIPLAYER_SETTINGS;
  }

  @Override
  public MultiplayerSettingsPacket clone() {
    try {
      return (MultiplayerSettingsPacket) super.clone();
    } catch (CloneNotSupportedException e) {
      throw new AssertionError(e);
    }
  }
}
