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
 * Auto generated from 1.26.40-beta.31 (v2168)
 *
 * Packet ID: 139 (0x8b)
 * This is used by EDU for joining players and removing players from your session,
 * the settings (there is only one) is an enum for enabling/disabling/refreshing multiplayer join codes.
 * Starts on the client, and a response to the client is issued from the server.
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
