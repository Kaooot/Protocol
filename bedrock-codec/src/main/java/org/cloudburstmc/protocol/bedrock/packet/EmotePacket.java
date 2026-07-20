package org.cloudburstmc.protocol.bedrock.packet;

import java.lang.AssertionError;
import java.lang.CloneNotSupportedException;
import java.lang.Override;
import java.lang.String;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.cloudburstmc.protocol.common.PacketSignal;

/**
 * Auto generated from 1.26.40-beta.31 (v2168)
 *
 * Packet ID: 138 (0x8a)
 * Sent in both directions; by client to request that an emote is played and then from the server to the clients to indicate which player needs to now emote.
 */
@Data
@EqualsAndHashCode(
    doNotUseGetters = true
)
@ToString(
    doNotUseGetters = true
)
public class EmotePacket implements BedrockPacket {
  private long actorRuntimeId;

  private String emoteId;

  private int emoteLengthTicks;

  private String xuid;

  private String platformId;

  private int flags;

  @Override
  public final PacketSignal handle(BedrockPacketHandler handler) {
    return handler.handle(this);
  }

  @Override
  public BedrockPacketType getPacketType() {
    return BedrockPacketType.EMOTE;
  }

  @Override
  public EmotePacket clone() {
    try {
      return (EmotePacket) super.clone();
    } catch (CloneNotSupportedException e) {
      throw new AssertionError(e);
    }
  }
}
