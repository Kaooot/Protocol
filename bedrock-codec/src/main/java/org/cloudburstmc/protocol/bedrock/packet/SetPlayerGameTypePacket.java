package org.cloudburstmc.protocol.bedrock.packet;

import java.lang.AssertionError;
import java.lang.CloneNotSupportedException;
import java.lang.Override;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.cloudburstmc.protocol.bedrock.data.GameType;
import org.cloudburstmc.protocol.common.PacketSignal;

/**
 * Auto generated from 1.26.40-beta.31 (v2168)
 *
 * Packet ID: 62 (0x3e)
 * The client handles the change of the UI element (the gametype dropdown, although this can be avoided by changing via command or on the server), then the client sends a packet to the server, then the server changes the player's gametype and sends a packet back (UpdatePlayerGameTypePacket) to make sure it matches on the client.
 */
@Data
@EqualsAndHashCode(
    doNotUseGetters = true
)
@ToString(
    doNotUseGetters = true
)
public class SetPlayerGameTypePacket implements BedrockPacket {
  private GameType PlayerGameType;

  @Override
  public final PacketSignal handle(BedrockPacketHandler handler) {
    return handler.handle(this);
  }

  @Override
  public BedrockPacketType getPacketType() {
    return BedrockPacketType.SET_PLAYER_GAME_TYPE;
  }

  @Override
  public SetPlayerGameTypePacket clone() {
    try {
      return (SetPlayerGameTypePacket) super.clone();
    } catch (CloneNotSupportedException e) {
      throw new AssertionError(e);
    }
  }
}
