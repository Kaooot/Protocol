package org.cloudburstmc.protocol.bedrock.packet;

import java.lang.AssertionError;
import java.lang.CloneNotSupportedException;
import java.lang.Override;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.cloudburstmc.protocol.bedrock.data.AdventureSettings;
import org.cloudburstmc.protocol.common.PacketSignal;

/**
 * Packet ID: 188 (0xbc)
 */
@Data
@EqualsAndHashCode(
    doNotUseGetters = true
)
@ToString(
    doNotUseGetters = true
)
public class UpdateAdventureSettingsPacket implements BedrockPacket {
  private AdventureSettings adventureSettings;

  @Override
  public final PacketSignal handle(BedrockPacketHandler handler) {
    return handler.handle(this);
  }

  @Override
  public BedrockPacketType getPacketType() {
    return BedrockPacketType.UPDATE_ADVENTURE_SETTINGS;
  }

  @Override
  public UpdateAdventureSettingsPacket clone() {
    try {
      return (UpdateAdventureSettingsPacket) super.clone();
    } catch (CloneNotSupportedException e) {
      throw new AssertionError(e);
    }
  }
}
