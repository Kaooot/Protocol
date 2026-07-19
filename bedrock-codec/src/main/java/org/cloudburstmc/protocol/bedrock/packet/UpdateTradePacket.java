package org.cloudburstmc.protocol.bedrock.packet;

import java.lang.AssertionError;
import java.lang.CloneNotSupportedException;
import java.lang.Override;
import java.lang.String;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.protocol.common.PacketSignal;

/**
 * Auto generated from 1.26.40-beta.31 (v2168)
 *
 * Packet ID: 80 (0x50)
 */
@Data
@EqualsAndHashCode(
    doNotUseGetters = true
)
@ToString(
    doNotUseGetters = true
)
public class UpdateTradePacket implements BedrockPacket {
  private int ContainerId;

  private int Type;

  private int Size;

  private int TraderTier;

  private long EntityUniqueId;

  private long LastTradingPlayer;

  private String DisplayName;

  private boolean UseNewTradeScreen;

  private boolean UsingEconomyTrade;

  private NbtMap Data;

  @Override
  public final PacketSignal handle(BedrockPacketHandler handler) {
    return handler.handle(this);
  }

  @Override
  public BedrockPacketType getPacketType() {
    return BedrockPacketType.UPDATE_TRADE;
  }

  @Override
  public UpdateTradePacket clone() {
    try {
      return (UpdateTradePacket) super.clone();
    } catch (CloneNotSupportedException e) {
      throw new AssertionError(e);
    }
  }
}
