package org.cloudburstmc.protocol.bedrock.packet;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import java.lang.AssertionError;
import java.lang.CloneNotSupportedException;
import java.lang.Override;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.cloudburstmc.protocol.bedrock.data.ScoreboardIdentityPacketInfo;
import org.cloudburstmc.protocol.bedrock.data.ScoreboardIdentityPacketType;
import org.cloudburstmc.protocol.common.PacketSignal;

/**
 * Auto generated from 1.26.40-beta.31 (v2168)
 *
 * Packet ID: 112 (0x70)
 * Send an update packet for a player identity definition iff a tracked player has logged in with a different display name.
 */
@Data
@EqualsAndHashCode(
    doNotUseGetters = true
)
@ToString(
    doNotUseGetters = true
)
public class SetScoreboardIdentityPacket implements BedrockPacket {
  private ScoreboardIdentityPacketType scoreboardIdentityPacketType;

  private final List<ScoreboardIdentityPacketInfo> scoreboardIdentityInfo = new ObjectArrayList<>();

  @Override
  public final PacketSignal handle(BedrockPacketHandler handler) {
    return handler.handle(this);
  }

  @Override
  public BedrockPacketType getPacketType() {
    return BedrockPacketType.SET_SCOREBOARD_IDENTITY;
  }

  @Override
  public SetScoreboardIdentityPacket clone() {
    try {
      return (SetScoreboardIdentityPacket) super.clone();
    } catch (CloneNotSupportedException e) {
      throw new AssertionError(e);
    }
  }
}
