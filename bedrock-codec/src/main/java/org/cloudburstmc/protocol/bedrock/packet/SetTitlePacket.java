package org.cloudburstmc.protocol.bedrock.packet;

import java.lang.AssertionError;
import java.lang.CloneNotSupportedException;
import java.lang.Override;
import java.lang.String;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.cloudburstmc.protocol.bedrock.data.SetTitlePacketTitleType;
import org.cloudburstmc.protocol.common.PacketSignal;

/**
 * Packet ID: 88 (0x58)
 */
@Data
@EqualsAndHashCode(
    doNotUseGetters = true
)
@ToString(
    doNotUseGetters = true
)
public class SetTitlePacket implements BedrockPacket {
  private SetTitlePacketTitleType titleType;

  private String titleText;

  private int fadeInTime;

  private int stayTime;

  private int fadeOutTime;

  private String xuid;

  private String platformOnlineId;

  private String filteredTitleMessage;

  @Override
  public final PacketSignal handle(BedrockPacketHandler handler) {
    return handler.handle(this);
  }

  @Override
  public BedrockPacketType getPacketType() {
    return BedrockPacketType.SET_TITLE;
  }

  @Override
  public SetTitlePacket clone() {
    try {
      return (SetTitlePacket) super.clone();
    } catch (CloneNotSupportedException e) {
      throw new AssertionError(e);
    }
  }
}
