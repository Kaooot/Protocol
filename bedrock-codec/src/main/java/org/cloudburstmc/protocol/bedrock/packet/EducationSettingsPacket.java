package org.cloudburstmc.protocol.bedrock.packet;

import java.lang.AssertionError;
import java.lang.CloneNotSupportedException;
import java.lang.Override;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.cloudburstmc.protocol.bedrock.data.EducationLevelSettings;
import org.cloudburstmc.protocol.common.PacketSignal;

/**
 * Packet ID: 137 (0x89)
 * Currently transmits EducationLevelSettings to all clients when the game is starting.
 */
@Data
@EqualsAndHashCode(
    doNotUseGetters = true
)
@ToString(
    doNotUseGetters = true
)
public class EducationSettingsPacket implements BedrockPacket {
  private EducationLevelSettings educationLevelSettings;

  @Override
  public final PacketSignal handle(BedrockPacketHandler handler) {
    return handler.handle(this);
  }

  @Override
  public BedrockPacketType getPacketType() {
    return BedrockPacketType.EDUCATION_SETTINGS;
  }

  @Override
  public EducationSettingsPacket clone() {
    try {
      return (EducationSettingsPacket) super.clone();
    } catch (CloneNotSupportedException e) {
      throw new AssertionError(e);
    }
  }
}
