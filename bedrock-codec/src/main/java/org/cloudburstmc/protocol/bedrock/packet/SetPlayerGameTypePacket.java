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
 * Packet ID: 62 (0x3e)
 */
@Data
@EqualsAndHashCode(
    doNotUseGetters = true
)
@ToString(
    doNotUseGetters = true
)
public class SetPlayerGameTypePacket implements BedrockPacket {
  private GameType playerGameType;

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
