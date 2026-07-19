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
 * Packet ID: 314 (0x13a)
 * Sends the name of the Structure Feature the player is currently occupying to the client.
 * If the player is not in a structure, this packet contains an empty string.
 */
@Data
@EqualsAndHashCode(
    doNotUseGetters = true
)
@ToString(
    doNotUseGetters = true
)
public class CurrentStructureFeaturePacket implements BedrockPacket {
  private String CurrentStructureFeature;

  @Override
  public final PacketSignal handle(BedrockPacketHandler handler) {
    return handler.handle(this);
  }

  @Override
  public BedrockPacketType getPacketType() {
    return BedrockPacketType.CURRENT_STRUCTURE_FEATURE;
  }

  @Override
  public CurrentStructureFeaturePacket clone() {
    try {
      return (CurrentStructureFeaturePacket) super.clone();
    } catch (CloneNotSupportedException e) {
      throw new AssertionError(e);
    }
  }
}
