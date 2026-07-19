package org.cloudburstmc.protocol.bedrock.packet;

import java.lang.AssertionError;
import java.lang.CloneNotSupportedException;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.cloudburstmc.protocol.common.PacketSignal;

/**
 * Auto generated from 1.26.40-beta.31 (v2168)
 *
 * Packet ID: 78 (0x4e)
 * Sent when you close the command block screen on the client.
 */
@Data
@EqualsAndHashCode(
    doNotUseGetters = true
)
@ToString(
    doNotUseGetters = true
)
public class CommandBlockUpdatePacket implements BedrockPacket {
  private Object Target;

  private String Command;

  private String LastOutput;

  private String Name;

  private String FilteredName;

  private boolean TrackOutput;

  private int TickDelay;

  private boolean ExecuteOnFirstTick;

  @Override
  public final PacketSignal handle(BedrockPacketHandler handler) {
    return handler.handle(this);
  }

  @Override
  public BedrockPacketType getPacketType() {
    return BedrockPacketType.COMMAND_BLOCK_UPDATE;
  }

  @Override
  public CommandBlockUpdatePacket clone() {
    try {
      return (CommandBlockUpdatePacket) super.clone();
    } catch (CloneNotSupportedException e) {
      throw new AssertionError(e);
    }
  }
}
