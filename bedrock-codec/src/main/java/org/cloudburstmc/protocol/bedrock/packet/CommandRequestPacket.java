package org.cloudburstmc.protocol.bedrock.packet;

import java.lang.AssertionError;
import java.lang.CloneNotSupportedException;
import java.lang.Override;
import java.lang.String;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.cloudburstmc.protocol.bedrock.data.CommandOriginData;
import org.cloudburstmc.protocol.bedrock.data.CurrentCmdVersion;
import org.cloudburstmc.protocol.common.PacketSignal;

/**
 * Auto generated from 1.26.40-beta.31 (v2168)
 *
 * Packet ID: 77 (0x4d)
 */
@Data
@EqualsAndHashCode(
    doNotUseGetters = true
)
@ToString(
    doNotUseGetters = true
)
public class CommandRequestPacket implements BedrockPacket {
  private String command;

  private CommandOriginData origin;

  private boolean isInternal;

  private CurrentCmdVersion version;

  @Override
  public final PacketSignal handle(BedrockPacketHandler handler) {
    return handler.handle(this);
  }

  @Override
  public BedrockPacketType getPacketType() {
    return BedrockPacketType.COMMAND_REQUEST;
  }

  @Override
  public CommandRequestPacket clone() {
    try {
      return (CommandRequestPacket) super.clone();
    } catch (CloneNotSupportedException e) {
      throw new AssertionError(e);
    }
  }
}
